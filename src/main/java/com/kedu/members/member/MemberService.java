package com.kedu.members.member;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/*
 * 		사원 회원가입 및 마이페이지 구현 Service
 * */

@Service
public class MemberService {
	@Autowired
	private MemberDAO dao;

	public List<MemberDTO> getAllMembers() {
		return dao.getAllMembers();
	}

	public MemberDTO getMemberByEmailAndCompany(String email, String companyCode) {
		return dao.getMemberByEmailAndCompany(email, companyCode);
	}
	
	public int updateMemberStatus(MemberDTO dto) {
		return dao.updateMemberStatus(dto);
	}

}