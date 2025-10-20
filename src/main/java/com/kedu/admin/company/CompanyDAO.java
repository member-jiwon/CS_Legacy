package com.kedu.admin.company;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/*
  		 회사 정보 이용 기능 구현 DAO
*/

@Repository
public class CompanyDAO {
	@Autowired
	private SqlSessionTemplate mybatis;
	
	// 로그인
	public int login(CompanyDTO dto) {
		return mybatis.selectOne("Admin.login", dto);
	}
	
	// 존재하는 관리자인지 검색
	public int emailAuth(CompanyDTO dto) {
		return mybatis.selectOne("Admin.emailAuth", dto);
	}
	
	// 비밀번호 재설정
	public int updatePw(CompanyDTO dto) {
		return mybatis.update("Admin.updatePw", dto);
	}
}
