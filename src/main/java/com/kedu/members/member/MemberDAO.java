package com.kedu.members.member;

import java.util.List;
import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/*
 * 사원 회원가입 및 마이페이지 구현 DAO
 */
@Repository
public class MemberDAO {

	@Autowired
	private SqlSession sqlSession;

	// 전체 회원 조회
	public List<MemberDTO> getAllMembers() {
		return sqlSession.selectList("MemberMapper.getAllMember");
	}

	// 이메일 + 회사코드 기준으로 회원 조회
	public MemberDTO getMemberByEmailAndCompany(String email, String companyCode) {
		Map<String, Object> params = new HashMap<>();
		params.put("email", email);
		params.put("company_code", companyCode);
		return sqlSession.selectOne("MemberMapper.getMemberByEmailAndCompany", params);
	}
	
	public int updateMemberStatus(MemberDTO dto) {
		return sqlSession.update("MemberMapper.updateMemberStatus", dto);
	}

}
