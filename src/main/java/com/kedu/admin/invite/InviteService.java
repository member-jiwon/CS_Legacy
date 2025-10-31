package com.kedu.admin.invite;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class InviteService {

	@Autowired
	private InviteDAO dao;

	// company_code 기준 초대 리스트 조회
	public List<InviteDTO> getInvitesByCompany(String company_code) {
		return dao.getInvitesByCompany(company_code);
	}

	// 승인된 초대 + 회원 정보 조회
	public List<InviteDTO> getApprovedInvitesWithMember() {
		return dao.getApprovedInvitesWithMember();
	}

	// 초대 등록
	@Transactional
	public int addInvite(InviteDTO dto) {
		dto.setStatus("미승인");
		return dao.insertInvite(dto);
	}

	// 초대 상태 변경
	@Transactional
	public int changeInviteStatus(InviteDTO dto) {
		return dao.updateInviteStatus(dto);
	}

	// 이메일 + 회사코드로 초대 정보 조회
	public InviteDTO getInviteByEmailAndCompany(String email, String companyCode) {
		return dao.getInviteByEmailAndCompany(email, companyCode);
	}

}
