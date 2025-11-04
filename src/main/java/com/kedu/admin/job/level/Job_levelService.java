package com.kedu.admin.job.level;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Job_levelService {

	@Autowired
	private Job_levelDAO job_levelDAO;

	// 승인된 회원 목록 조회
	public List<Map<String, Object>> getApprovedMembersWithJobDetail() {
		return job_levelDAO.getApprovedMembersWithJobDetail();
	}

	// 직급 전체 조회
	public List<Job_levelDTO> getselect() {
		return job_levelDAO.getselect();
	}

	// 회사 코드 조회
	public String selectCompanyCode() {
		return job_levelDAO.selectCompanyCode();
	}

	// 회원 부서 변경
	public int updateMemberDept(String email, String dept_code, String company_code) {
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("email", email);
		paramMap.put("dept_code", dept_code);
		paramMap.put("company_code", company_code);

		return job_levelDAO.updateMemberDept(paramMap);
	}

	// 회원 직급 변경
	public int updateMemberLevel(String email, String level_code, String company_code) {
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("email", email);
		paramMap.put("level_code", level_code);
		paramMap.put("company_code", company_code);

		return job_levelDAO.updateMemberLevel(paramMap);
	}

	// 회원 상태 변경 (재직/퇴사)
	public int updateMemberStatus(String email, String status, String company_code) {
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("email", email);
		paramMap.put("status", status);
		paramMap.put("company_code", company_code);

		return job_levelDAO.updateMemberStatus(paramMap);
	}
}
