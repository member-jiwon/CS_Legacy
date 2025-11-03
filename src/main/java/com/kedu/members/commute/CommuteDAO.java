package com.kedu.members.commute;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


@Repository
public class CommuteDAO {

    @Autowired
    private SqlSession mybatis;

    // 1. 근태관리
    public Map<String, Integer> getAttendanceStats(Map<String, Object> param) {
        List<Map<String, Object>> list = mybatis.selectList("Commute.getAttendanceStats", param);

        Map<String, Integer> result = new LinkedHashMap<>();
        for (Map<String, Object> row : list) {
            String status = (String) row.get("STATUS");
            int count = ((java.math.BigDecimal) row.get("CNT")).intValue();
            result.put(status, count);
        }
        return result;
    }

    // 2. 출근관리
    @SuppressWarnings("unchecked")
    public Map<String, Integer> getWorkStats(Map<String, Object> param) {
        // Mapper媛� �븳 �뻾(�몢 而щ읆) 諛섑솚�븯誘�濡� selectOne �궗�슜 OK
        return (Map<String, Integer>) mybatis.selectOne("Commute.getWorkStats", param);
    }

    // 3.전자결재 진행
    public Map<String, Integer> getApprovalStats(Map<String, Object> param) {
        return mybatis.selectOne("Commute.getApprovalStats", param);
    }
}
