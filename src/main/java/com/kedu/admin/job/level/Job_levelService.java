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

	// 사원 목록 조회
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

	// 사원 부서 업데이트
	public int updateMemberDept(String email, String dept_code, String company_code) {
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("email", email);
		paramMap.put("dept_code", dept_code);
		paramMap.put("company_code", company_code);

		return job_levelDAO.updateMemberDept(paramMap);
	}

	// 사원 직급 업데이트
	public int updateMemberLevel(String email, String level_code, String company_code) {
	    Map<String, Object> paramMap = new HashMap<>();
	    paramMap.put("email", email);
	    paramMap.put("level_code", level_code);
	    paramMap.put("company_code", company_code); // 🚨 이 로직을 서비스에서 처리합니다.
	    
	    return job_levelDAO.updateMemberLevel(paramMap);
	}
}
