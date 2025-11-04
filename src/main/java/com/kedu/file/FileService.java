package com.kedu.file;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.google.cloud.storage.Blob;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;

@Service
public class FileService {
    @Autowired
    private FileDAO fileDao;
    @Autowired
    private Storage storage;
   
    @Autowired
    private FileDAO fileDAO;
    private final String bucketName = "hwi_study";
    
    // 1. 파일 타입과 부모 시퀀스로 파일배열 받아오기
    public List<FileDTO> getFilesByParent(int parent_seq,String file_type) {
    	Map<String, Object> param= new HashMap<>();
    	param.put("parent_seq", parent_seq);
    	param.put("file_type", file_type);
    	
        return fileDAO.selectByParentSeq(param);
    }
    
    // 2. 다운로드
    public Map<String, Object> getFileData(String sysname, String file_type) throws Exception {
        Map<String, Object> result = new HashMap<>();

        // 1️. DB 조회
        FileDTO dto = fileDAO.selectBySysname(sysname);
        if (dto == null) return null;

        // 2️. GCS 파일 다운로드
        String targetName = file_type + "/" + sysname;
        Blob blob = storage.get(bucketName, targetName);
        if (blob == null || !blob.exists()) return null;

        byte[] content = blob.getContent();

        // 3️. 합쳐서 반환
        result.put("oriname", dto.getOriname());
        result.put("content", content);
        return result;
    }
	
	//3. 파일 업로드
    public void upload(MultipartFile[] files, int parent_seq, String file_type, String member_email) {
    	if (files == null || files.length == 0) return;

        for (MultipartFile file : files) {
            if (!file.isEmpty()) {
                try {
                    String oriName = file.getOriginalFilename();
                    String sysName = UUID.randomUUID() + "_" + oriName;

                    // 폴더 구조 흉내내기 (approval/uuid_filename)
                    String objectName = file_type + "/" + sysName;

                    BlobInfo blobInfo = BlobInfo.newBuilder(BlobId.of(bucketName, objectName))
                                                .setContentType(file.getContentType())
                                                .build();

                    try (InputStream is = file.getInputStream()) {
                        storage.createFrom(blobInfo, is);
                        System.out.println("파일업로드완료");
                    }
                    // DB에 파일 메타데이터 저장
                    fileDao.upload(new FileDTO(0, member_email, sysName, oriName, null, file_type, parent_seq));
                } catch (Exception e) {
                    e.printStackTrace();
                    throw new RuntimeException("파일 업로드 중 오류 발생", e);
                }
            }
        }
    }
    
    // 3. 종류+부모 시퀀스로 삭제하기 : 글 하나 지우면 거기에 딸린 모든 파일 삭제
    public int deleteFilesByParent(int parent_seq,String file_type ) {
    	// 1. 파일이 없다면 리턴
    	List<FileDTO> files = getFilesByParent(parent_seq, file_type);
        if (files == null || files.isEmpty()) return 0;
        // 2. GCS에서도 파일 삭제
        for (FileDTO f : files) {
            try {
                String objectName = file_type + "/" + f.getSysname();
                storage.delete(BlobId.of(bucketName, objectName));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
     // 4. DB에서도 삭제
        Map<String, Object> param = new HashMap<>();
        param.put("parent_seq", parent_seq);
        param.put("file_type", file_type);

        return fileDao.deleteByParentSeq(param);
    }
    
    //5. 유지할 파일은 제외하고 나머지만 삭제
    public void deleteFilesExcept(int parentSeq, String fileType, List<String> keepFiles) {
        Map<String, Object> param = new HashMap<>();
        param.put("parent_seq", parentSeq);
        param.put("file_type", fileType);

        List<FileDTO> existing = fileDao.selectByParentSeq(param);
        if (existing == null || existing.isEmpty()) return;

        for (FileDTO f : existing) {
            String ori = f.getOriname();
            if (keepFiles != null && keepFiles.contains(ori)) continue;

            try {
                // GCS 삭제
                String objectName = fileType + "/" + f.getSysname();
                storage.delete(BlobId.of(bucketName, objectName));

                // DB 삭제
                fileDao.deleteBySysname(f.getSysname());

                System.out.println("삭제 완료: " + ori);
            } catch (Exception e) {
                e.printStackTrace();
                throw new RuntimeException("파일 삭제 중 오류 발생");
            }
        }
    }
    
    
}
