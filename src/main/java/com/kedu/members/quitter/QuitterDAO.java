package com.kedu.members.quitter;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/*
  	퇴사 처리 등 기능 구현 DAO
*/
@Repository
public class QuitterDAO {
	@Autowired
	private SqlSessionTemplate mybatis;

	// 퇴사자 정보 DB에 기록
	public int insertQuitter(QuitterDTO dto) {
		return mybatis.insert("Quitter.insertQuitter", dto);
	}
}
