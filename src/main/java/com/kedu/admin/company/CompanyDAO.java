package com.kedu.admin.company;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/*
  		 �쉶�궗 �젙蹂� �씠�슜 湲곕뒫 援ы쁽 DAO
*/

@Repository
public class CompanyDAO {
	@Autowired
	private SqlSessionTemplate mybatis;
	
	// dto 반환으로 변경, 회사코드 세션에 저장하기 위해서
	public CompanyDTO login(CompanyDTO dto) {
		return mybatis.selectOne("Admin.login", dto);
	}
	
	// 議댁옱�븯�뒗 愿�由ъ옄�씤吏� 寃��깋
	public int emailAuth(CompanyDTO dto) {
		return mybatis.selectOne("Admin.emailAuth", dto);
	}
	
	// 鍮꾨�踰덊샇 �옱�꽕�젙
	public int updatePw(CompanyDTO dto) {
		return mybatis.update("Admin.updatePw", dto);
	}
}
