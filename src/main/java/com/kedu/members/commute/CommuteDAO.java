package com.kedu.members.commute;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/*
 * 		근태 / 출근 / 전자결재 통계 DAO
 * 		👉 SqlSession 직접 사용하는 수동 호출 버전
 */

@Repository
public class CommuteDAO {

    @Autowired
    private SqlSession mybatis;

    // 1. 근태관리 통계 (출근 / 결근 / 연차)
    public Map<String, Integer> getAttendanceStats(Map<String, Object> param) {
        // ✅ selectList로 수정 (GROUP BY 결과 여러 행)
        List<Map<String, Object>> list = mybatis.selectList("Commute.getAttendanceStats", param);

        Map<String, Integer> result = new LinkedHashMap<>();
        for (Map<String, Object> row : list) {
            String status = (String) row.get("STATUS");
            int count = ((java.math.BigDecimal) row.get("CNT")).intValue();
            result.put(status, count);
        }
        return result;
    }

    // 2. 출근관리 통계 (정시출근 / 지각)
    @SuppressWarnings("unchecked")
    public Map<String, Integer> getWorkStats(Map<String, Object> param) {
        // Mapper가 한 행(두 컬럼) 반환하므로 selectOne 사용 OK
        return (Map<String, Integer>) mybatis.selectOne("Commute.getWorkStats", param);
    }

 // 3. 전자결재 처리현황 통계 (처리중 / 반려 / 승인)
    public Map<String, Integer> getApprovalStats(Map<String, Object> param) {
        return mybatis.selectOne("Commute.getApprovalStats", param);
    }
}
