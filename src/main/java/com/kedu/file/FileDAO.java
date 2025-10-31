package com.kedu.file;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/*
	 파일 기능 관련 구현 DAO
*/
@Repository
public class FileDAO {
	@Autowired
	private SqlSessionTemplate mybatis;

	// 파일 저장
	public void insertFile(FileDTO fileDTO) {
		mybatis.insert("FileMapper.insertFile", fileDTO);
	}

	// 부모 게시글 기준 파일 목록
	public List<FileDTO> getFilesByParentSeq(int parent_seq, String file_code) {
		Map<String, Object> params = new HashMap<>();
		params.put("parent_seq", parent_seq);
		params.put("file_code", file_code);
		return mybatis.selectList("FileMapper.getFilesByParentSeq", params);
	}

	// 부모글 기준 파일 삭제
	public void deleteFilesByParent(int parent_seq) {
		mybatis.delete("FileMapper.deleteFilesByParent", parent_seq);
	}
}
