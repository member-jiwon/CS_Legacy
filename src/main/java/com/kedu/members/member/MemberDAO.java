package com.kedu.members.member;

import java.util.List;
import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class MemberDAO {

	@Autowired
	private SqlSession sqlSession;

	// 이메일로 이름 찾기
	public String getNameByEmail(String member_email) {
		return sqlSession.selectOne("MemberMapper.getNameByEmail", member_email);
	}

	// 전체 회원 목록 조회
	public List<MemberDTO> getAllMembers() {
		return sqlSession.selectList("MemberMapper.getAllMember");
	}

	// 이메일 + 회사코드로 회원 조회
	public MemberDTO getMemberByEmailAndCompany(String email, String companyCode) {
		Map<String, Object> params = new HashMap<>();
		params.put("email", email);
		params.put("company_code", companyCode);
		return sqlSession.selectOne("MemberMapper.getMemberByEmailAndCompany", params);
	}

	// 회원 상태 업데이트
	public int updateMemberStatus(MemberDTO dto) {
		return sqlSession.update("MemberMapper.updateMemberStatus", dto);
	}
}