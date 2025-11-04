package com.kedu.file;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/*
	 �뙆�씪 湲곕뒫 愿��젴 援ы쁽 DAO
*/
@Repository
public class FileDAO {
	@Autowired
	private SqlSessionTemplate mybatis;

	// 부모 시퀀스와 파일 타입으로 파일dto형 배열 가져오기
	public List<FileDTO> selectByParentSeq(Map<String, Object> param) {
		return mybatis.selectList("File.selectByParentSeq", param);
	}

	// 시스네임으로 dto 찾기
	public FileDTO selectBySysname(String sysname) {
		return mybatis.selectOne("File.selectBySysname", sysname);
	}

	// 파일 업로드
	public int upload(FileDTO dto) {
		return mybatis.insert("File.upload", dto);
	}

	// 종류+부모 시퀀스로 삭제하기 : 글 하나 지우면 거기에 딸린 모든 파일 삭제
	public int deleteByParentSeq(Map<String, Object> param) {
		return mybatis.delete("File.deleteByParentSeq", param);
	}

	// 시스네임으로 개별 삭제
	public int deleteBySysname(String sysname) {
		return mybatis.delete("File.deleteBySysname", sysname);
	}
}
