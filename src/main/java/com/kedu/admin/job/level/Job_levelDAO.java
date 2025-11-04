package com.kedu.admin.job.level;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/*
    직급 관련 데이터 처리 DAO
*/
@Repository
public class Job_levelDAO {

	@Autowired
	private SqlSessionTemplate mybatis;

	// 승인된 회원 목록 조회 (map 형태)
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

	// 회원 부서 변경
	public int updateMemberDept(Map<String, Object> param) {
		return mybatis.update("Job.updateMemberDept", param);
	}

	// 회원 직급 변경
	public int updateMemberLevel(Map<String, Object> param) {
		return mybatis.update("Job.updateMemberLevel", param);
	}

	// 회원 상태 변경 (재직/퇴사)
	public int updateMemberStatus(Map<String, Object> param) {
		return mybatis.update("Job.updateMemberStatus", param);
	}
}
