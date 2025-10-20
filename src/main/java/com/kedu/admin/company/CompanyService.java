package com.kedu.admin.company;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/*
  		 회사 정보 이용 기능 구현 service
*/

@Service
public class CompanyService {
	@Autowired
	private CompanyDAO dao;
	
	// 로그인
	public int login(CompanyDTO dto) {
		dto.setPw(com.kedu.common.Encryptor.encrypt(dto.getPw()));
		return dao.login(dto);
	}
	
	// 존재하는 관리자인지 검색
	public int emailAuth(CompanyDTO dto) {
		return dao.emailAuth(dto);
	}
	
	// 비밀번호 재설정
	public int updatePw(CompanyDTO dto) {
		dto.setPw(com.kedu.common.Encryptor.encrypt(dto.getPw()));
		return dao.updatePw(dto);
	}
}
