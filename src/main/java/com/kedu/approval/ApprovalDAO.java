package com.kedu.approval;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


@Repository
public class ApprovalDAO {

    @Autowired
    private SqlSessionTemplate mybatis;


//    public List<Map<String, Object>> selectAllFromTo(Map<String, Object> param) {
//        return mybatis.selectList("Approval.selectAllFromTo", param);
//    }

//    public int getCount() {
//        return mybatis.selectOne("Approval.getCount");
//    }

    // 상태 + 부서 필터링 리스트 (페이징)
    public List<Map<String, Object>> selectByFilterFromTo(Map<String, Object> param) {
        return mybatis.selectList("Approval.selectByFilterFromTo", param);// status, deptCode가 null 또는 ""이면 Mapper에서 자동 무시됨
    }

    // 상태 + 부서 필터링 총 개수
    public int getCountByFilter(Map<String, Object> param) {
        return mybatis.selectOne("Approval.getCountByFilter", param);
    }

    // 결재 상태 업데이트
    public int updateStatus(Map<String, Object> param) {
        return mybatis.update("Approval.updateStatus", param);
    }
    
    //디테일 페이지로 이동
    public ApprovalDTO toDetailApproval(int approval_seq) {
    	return mybatis.selectOne("Approval.toDetailApproval", approval_seq);
    }
}