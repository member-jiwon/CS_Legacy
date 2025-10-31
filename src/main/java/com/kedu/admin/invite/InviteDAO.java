package com.kedu.admin.invite;

import java.util.List;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class InviteDAO {

	@Autowired
	private SqlSession sqlSession;

	// company_code 기준 모든 초대 + 회원 정보 조회
	public List<InviteDTO> getInvitesByCompany(String company_code) {
		return sqlSession.selectList("InviteMapper.getInvitesByCompany", company_code);
	}

	// 이메일 + 회사코드로 초대 정보 조회
	public InviteDTO getInviteByEmailAndCompany(String email, String companyCode) {
		java.util.Map<String, Object> param = new java.util.HashMap<>();
		param.put("email", email);
		param.put("company_code", companyCode);
		return sqlSession.selectOne("InviteMapper.getInviteByEmailAndCompany", param);
	}

	// 초대 추가
	public int insertInvite(InviteDTO dto) {
		return sqlSession.insert("InviteMapper.insertInvite", dto);
	}

	// 초대 상태 업데이트
	public int updateInviteStatus(InviteDTO dto) {
		return sqlSession.update("InviteMapper.updateInviteStatus", dto);
	}

	// 승인된 초대 + 회원 정보 조회
	public List<InviteDTO> getApprovedInvitesWithMember() {
		return sqlSession.selectList("InviteMapper.getApprovedInvitesWithMember");
	}
}
