package com.kedu.members.commute;

import java.sql.Date;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/*
 *  	域뱀눛源��꽴占썹뵳占� 疫꿸퀡�뮟 �뤃�뗭겱 Service
 * */

@Service
public class CommuteService {
	@Autowired
	private CommuteDAO dao;
	
    // 1. 洹쇳깭愿�由� �넻怨�
    public Map<String, Integer> getAttendanceStats(LocalDate start, LocalDate end, String type) {
        Map<String, Object> param = new HashMap<>();
        param.put("startDate", Date.valueOf(start));
        param.put("endDate", Date.valueOf(end));
        param.put("type", type);
        return dao.getAttendanceStats(param);
    }

    // 2. �젙�떆異쒓렐�쑉
    public Map<String, Integer> getWorkStats(LocalDate start, LocalDate end, String type) {
        Map<String, Object> param = new HashMap<>();
        // �쐟 LocalDate �넂 java.sql.Date 濡� 蹂��솚 (Oracle DATE 諛붿씤�뵫)
        param.put("startDate", java.sql.Date.valueOf(start));
        param.put("endDate",   java.sql.Date.valueOf(end));
        param.put("type", type);
        return dao.getWorkStats(param);
    }

 // 3. �쟾�옄寃곗옱 泥섎━�쁽�솴
    public Map<String, Integer> getApprovalStats(LocalDate start, LocalDate end, String type) {
        Map<String, Object> param = new HashMap<>();
        param.put("startDate", Date.valueOf(start)); // �쐟 java.time.LocalDate �넂 java.sql.Date 蹂��솚
        param.put("endDate", Date.valueOf(end));
        param.put("type", type);
        // ��蹂� 議고쉶�슜 �꽭�뀡 遺��꽌 肄붾뱶媛� �엳�떎硫� 媛숈씠 �쟾�떖
        // param.put("sessionDeptCode", sessionDeptCode);
        return dao.getApprovalStats(param);
    }
}
