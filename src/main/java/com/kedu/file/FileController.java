package com.kedu.file;

import java.net.URLEncoder;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.cloud.storage.Storage;

@Controller
@RequestMapping("/file")
public class FileController {
	private final String bucketName = "hwi_study"; // ���� ��Ŷ �̸�
	@Autowired
    private Storage storage; //cpConfig���� �ڵ� ���Ե�
	@Autowired
    private FileService fileService;
	
	//�ٿ�ε�
    @RequestMapping("/download")
    public ResponseEntity<byte[]> download(String sysname, String file_type) throws Exception {
        Map<String, Object> fileData = fileService.getFileData(sysname, file_type);

        if (fileData == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                                 .body(("File not found").getBytes());
        }

        String oriname = (String) fileData.get("oriname");
        byte[] content = (byte[]) fileData.get("content");

        HttpHeaders headers = new HttpHeaders();
        String encodedName = URLEncoder.encode(oriname, "UTF-8").replaceAll("\\+", "%20");
        headers.setContentDispositionFormData("attachment", encodedName);

        return new ResponseEntity<>(content, headers, HttpStatus.OK);
    }
    
    
    
	
}
