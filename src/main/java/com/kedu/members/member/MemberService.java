package com.kedu.members.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/*
 * 		�궗�썝 �쉶�썝媛��엯 諛� 留덉씠�럹�씠吏� 援ы쁽 Service
 * */

@Service
public class MemberService {
	@Autowired
	private MemberDAO dao;
	
	
	//이메일로 이름 찾기
	public String getNameByEmail(String member_email) {
		return dao.getNameByEmail(member_email);
	}
	
	
}