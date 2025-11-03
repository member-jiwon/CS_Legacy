package com.kedu.members.quitter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kedu.admin.job.level.Job_levelService;

/*
	퇴사 처리 등 기능 구현 service
*/

@Service
public class QuitterService {
	@Autowired
	private QuitterDAO dao;

	@Autowired
	private Job_levelService job_levelService;

	// 퇴사 처리 트랜잭션 (MEMBER 상태 변경 + QUITTER 기록)
	// 두 쿼리 모두 성공해야 하므로 트랜잭션 처리
	@Transactional
	public int processQuitter(String email, String company_code) {

		// member 테이블의 status를 '퇴사'로 변경 (기존 로직 재사용)
		int statusUpdate = job_levelService.updateMemberStatus(email, "퇴사", company_code);

		if (statusUpdate > 0) {
			// 퇴사 테이블에 퇴사 정보 기록 (퇴사 날짜는 DB가 SYSDATE로 처리)
			QuitterDTO dto = new QuitterDTO();
			dto.setMember_email(email);
			dto.setCompany_code(company_code);

			return dao.insertQuitter(dto);
		}
		return 0;
	}
}
