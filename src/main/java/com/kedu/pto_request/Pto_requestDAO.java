package com.kedu.pto_request;
/*
 * 		�뿰李� �떊泥� 湲곕뒫 愿��젴 DAO
 * */

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kedu.approval.ApprovalDTO;

@Repository
public class Pto_requestDAO {
	@Autowired
	private SqlSession mybatis;
	

    /**
     * 전체 리스트 (페이징)
     */
    public List<Map<String, Object>> selectAllFromTo(Map<String, Object> param) {
        return mybatis.selectList("Pto_request.selectAllFromTo", param);
    }

    /**
     * 전체 데이터 개수
     */
    public int getCount() {
        return mybatis.selectOne("Pto_request.getCount");
    }

    /**
     * 상태 + 부서 필터링 리스트 (페이징)
     */
    public List<Map<String, Object>> selectByFilterFromTo(Map<String, Object> param) {
        // status, deptCode가 null 또는 ""이면 Mapper에서 자동 무시됨
        return mybatis.selectList("Pto_request.selectByFilterFromTo", param);
    }

    /**
     * 상태 + 부서 필터링 총 개수
     */
    public int getCountByFilter(Map<String, Object> param) {
        return mybatis.selectOne("Pto_request.getCountByFilter", param);
    }

    /**
     * 결재 상태 업데이트
     */
    public int updateStatus(Map<String, Object> param) {
        return mybatis.update("Pto_request.updateStatus", param);
    }
    
    //디테일 페이지로 이동
    public Pto_requestDTO toDetailPtoRequest(int pto_seq) {
    	return mybatis.selectOne("Pto_request.toDetailPtoRequest", pto_seq);
    }
	
	
}
