package com.kedu.members.member;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/*
 * 		회원 서비스
 */
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
