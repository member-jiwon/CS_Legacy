package com.kedu.admin.company;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/*
  		 �쉶�궗 �젙蹂� �씠�슜 湲곕뒫 援ы쁽 service
*/

@Service
public class CompanyService {
	@Autowired
	private CompanyDAO dao;
	
	// dto 반환으로 변경, 회사코드 세션에 저장하기 위해서
	public CompanyDTO login(CompanyDTO dto) {
	    dto.setPw(com.kedu.common.Encryptor.encrypt(dto.getPw()));
	    return dao.login(dto); // 로그인 성공 시 CompanyDTO 객체 반환
	}

	
	// 議댁옱�븯�뒗 愿�由ъ옄�씤吏� 寃��깋
	public int emailAuth(CompanyDTO dto) {
		return dao.emailAuth(dto);
	}
	
	// 鍮꾨�踰덊샇 �옱�꽕�젙
	public int updatePw(CompanyDTO dto) {
		dto.setPw(com.kedu.common.Encryptor.encrypt(dto.getPw()));
		return dao.updatePw(dto);
	}
}
