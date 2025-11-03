package com.kedu.approval;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * 전자결재 DAO
 * - MyBatis를 통해 DB 접근
 * - 상태/부서별 필터링, 페이징, 상태 업데이트 처리
 */
@Repository
public class ApprovalDAO {

    @Autowired
    private SqlSessionTemplate mybatis;

    /**
     * 전체 리스트 (페이징)
     */
    public List<Map<String, Object>> selectAllFromTo(Map<String, Object> param) {
        return mybatis.selectList("Approval.selectAllFromTo", param);
    }

    /**
     * 전체 데이터 개수
     */
    public int getCount() {
        return mybatis.selectOne("Approval.getCount");
    }

    /**
     * 상태 + 부서 필터링 리스트 (페이징)
     */
    public List<Map<String, Object>> selectByFilterFromTo(Map<String, Object> param) {
        // status, deptCode가 null 또는 ""이면 Mapper에서 자동 무시됨
        return mybatis.selectList("Approval.selectByFilterFromTo", param);
    }

    /**
     * 상태 + 부서 필터링 총 개수
     */
    public int getCountByFilter(Map<String, Object> param) {
        return mybatis.selectOne("Approval.getCountByFilter", param);
    }

    /**
     * 결재 상태 업데이트
     */
    public int updateStatus(Map<String, Object> param) {
        return mybatis.update("Approval.updateStatus", param);
    }
    
    //디테일 페이지로 이동
    public ApprovalDTO toDetailApproval(int approval_seq) {
    	return mybatis.selectOne("Approval.toDetailApproval", approval_seq);
    }
}