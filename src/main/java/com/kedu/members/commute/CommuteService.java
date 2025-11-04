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
	
    // 1.근태관리 표
    public Map<String, Integer> getAttendanceStats(LocalDate start, LocalDate end, String type, String company_code) {
        Map<String, Object> param = new HashMap<>();
        param.put("startDate", Date.valueOf(start));
        param.put("endDate", Date.valueOf(end));
        param.put("type", type);
        param.put("company_code", company_code);
        return dao.getAttendanceStats(param);
    }

    // 2. 출근관리
    public Map<String, Integer> getWorkStats(LocalDate start, LocalDate end, String type, String company_code) {
        Map<String, Object> param = new HashMap<>();
        param.put("startDate", java.sql.Date.valueOf(start));
        param.put("endDate",   java.sql.Date.valueOf(end));
        param.put("type", type);
        param.put("company_code", company_code);
        return dao.getWorkStats(param);
    }

    // 3.전자결재 진행
    public Map<String, Integer> getApprovalStats(LocalDate start, LocalDate end, String type, String company_code) {
        Map<String, Object> param = new HashMap<>();
        param.put("startDate", Date.valueOf(start)); 
        param.put("endDate", Date.valueOf(end));
        param.put("type", type);
        param.put("company_code", company_code);
        return dao.getApprovalStats(param);
    }
}
