package com.kedu.admin.invite;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kedu.admin.job.level.Job_levelService;

@Service
public class InviteService {

	@Autowired
	private InviteDAO dao;

	@Autowired
	private Job_levelService job_levelService;

	// company_code에 따른 초대 리스트 조회
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
		int result = dao.updateInviteStatus(dto);

		// 상태가 승인인 경우, MEMBER 테이블의 STATUS를 '정회원'으로 변경
		if (result > 0 && "승인".equals(dto.getStatus())) {
			job_levelService.updateMemberStatus(dto.getEmail(), "정직원", dto.getCompany_code());
		}

		return result;
	}

	// 이메일 + 회사 코드로 초대 정보 조회
	public InviteDTO getInviteByEmailAndCompany(String email, String companyCode) {
		return dao.getInviteByEmailAndCompany(email, companyCode);
	}

}
