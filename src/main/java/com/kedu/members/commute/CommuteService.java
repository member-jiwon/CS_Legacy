package com.kedu.members.commute;

import java.sql.Date;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/*
 *  	근태관리 Service
 * */

@Service
public class CommuteService {
	@Autowired
	private CommuteDAO dao;
	
    // 1. 근태관리 통계
    public Map<String, Integer> getAttendanceStats(LocalDate start, LocalDate end, String type) {
        Map<String, Object> param = new HashMap<>();
        param.put("startDate", Date.valueOf(start));
        param.put("endDate", Date.valueOf(end));
        param.put("type", type);
        return dao.getAttendanceStats(param);
    }

    // 2. 정시출근율
    public Map<String, Integer> getWorkStats(LocalDate start, LocalDate end, String type) {
        Map<String, Object> param = new HashMap<>();
        // ✅ LocalDate → java.sql.Date 로 변환 (Oracle DATE 바인딩)
        param.put("startDate", java.sql.Date.valueOf(start));
        param.put("endDate",   java.sql.Date.valueOf(end));
        // type은 현재 쿼리에서 사용 안 함(요구 변경사항 반영)
        return dao.getWorkStats(param);
    }

 // 3. 전자결재 처리현황
    public Map<String, Integer> getApprovalStats(LocalDate start, LocalDate end, String type) {
        Map<String, Object> param = new HashMap<>();
        param.put("startDate", Date.valueOf(start)); // ✅ java.time.LocalDate → java.sql.Date 변환
        param.put("endDate", Date.valueOf(end));
        param.put("type", type);
        // 팀별 조회용 세션 부서 코드가 있다면 같이 전달
        // param.put("sessionDeptCode", sessionDeptCode);
        return dao.getApprovalStats(param);
    }
}
