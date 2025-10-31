package com.kedu.admin.job.level;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/*
     직급 관련 기능 구현 DAO
*/
@Repository
public class Job_levelDAO {

	@Autowired
	private SqlSessionTemplate mybatis;

	// 사원 목록 조회 (map 타입)
	public List<Map<String, Object>> getApprovedMembersWithJobDetail() {
		return mybatis.selectList("Job.getApprovedMembersWithJobDetail");
	}

	// 직급 전체 조회
	public List<Job_levelDTO> getselect() {
		return mybatis.selectList("Job.getselect");
	}

	// 회사 코드 조회
	public String selectCompanyCode() {
		return mybatis.selectOne("Job.selectCompanyCode");
	}

	// 사원 부서 업데이트
	public int updateMemberDept(Map<String, Object> param) {
		return mybatis.update("Job.updateMemberDept", param);
	}

	// 사원 직급 업데이트
	public int updateMemberLevel(Map<String, Object> param) {
		return mybatis.update("Job.updateMemberLevel", param);
	}
}
