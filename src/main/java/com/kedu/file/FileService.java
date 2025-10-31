package com.kedu.file;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileService {

	@Autowired
	private FileDAO fileDAO;

	private final String uploadDir = "C:/uploads/notice"; // 실제 서버 경로로 변경 필요

	// 파일 저장 (실제 업로드 + DB 저장)
	public void saveFile(FileDTO fileDTO, MultipartFile file) {
		try {
			// 디렉토리 없으면 생성
			File dir = new File(uploadDir);
			if (!dir.exists())
				dir.mkdirs();

			// 중복 방지를 위한 UUID 파일명 생성
			String sysname = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();

			// 실제 파일 저장
			File dest = new File(dir, sysname);
			file.transferTo(dest);

			// DB에 저장할 파일명 변경
			fileDTO.setSysname(sysname);

			// DB insert
			fileDAO.insertFile(fileDTO);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// 특정 부모글의 파일 목록 조회
	public List<FileDTO> getFilesByParentSeq(int parent_seq, String file_code) {
		return fileDAO.getFilesByParentSeq(parent_seq, file_code);
	}

	// 특정 부모글의 파일 삭제
	public void deleteFilesByParent(int parent_seq) {
		fileDAO.deleteFilesByParent(parent_seq);
	}
}
