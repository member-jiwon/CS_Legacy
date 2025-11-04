package com.kedu.members.member;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 사원 및 회원 관련 비즈니스 로직을 처리하는 Service 클래스
 */
@Service
public class MemberService {

	@Autowired
	private MemberDAO dao;

	// 이메일로 이름 찾기
	public String getNameByEmail(String member_email) {
		return dao.getNameByEmail(member_email);
	}

	// 전체 회원 목록 조회
	public List<MemberDTO> getAllMembers() {
		return dao.getAllMembers();
	}

	// 이메일 + 회사코드로 회원 조회
	public MemberDTO getMemberByEmailAndCompany(String email, String companyCode) {
		return dao.getMemberByEmailAndCompany(email, companyCode);
	}

	// 회원 상태 업데이트
	public int updateMemberStatus(MemberDTO dto) {
		return dao.updateMemberStatus(dto);
	}
}