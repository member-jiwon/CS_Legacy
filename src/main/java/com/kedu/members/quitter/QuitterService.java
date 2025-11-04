package com.kedu.members.quitter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kedu.admin.job.level.Job_levelService;

/*
    퇴사자 등록용 Service
*/
@Service
public class QuitterService {

	@Autowired
	private QuitterDAO dao;

	@Autowired
	private Job_levelService job_levelService;

	// 퇴사 처리: 회원 상태 변경 + 퇴사자 DB 등록
	@Transactional
	public int processQuitter(String email, String company_code) {

		// member의 status를 '퇴사'로 변경
		int statusUpdate = job_levelService.updateMemberStatus(email, "퇴사", company_code);

		if (statusUpdate > 0) {
			// 퇴사자 DB 등록 (퇴사일자: 현재 시간)
			QuitterDTO dto = new QuitterDTO();
			dto.setMember_email(email);
			dto.setCompany_code(company_code);
			dto.setQuit_at(new java.sql.Timestamp(System.currentTimeMillis()));

			return dao.insertQuitter(dto);
		}
		return 0;
	}
}
