package com.kedu.pto_request;

import java.sql.Clob;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kedu.approval.ApprovalDTO;


/*
 * 		�뿰李� �떊泥� 湲곕뒫 愿��젴 service
 * */

@Service
public class Pto_requestService {
	@Autowired
	private Pto_requestDAO dao;
	
    /**
     * DB 결과값을 JSON 직렬화-friendly한 Map으로 변환
     * - CLOB, TIMESTAMP 등 안전하게 문자열로 변환
     * - JSP에서 Gson으로 JSON 출력 시 에러 방지
     */
    private List<Map<String, Object>> sanitizeForJson(List<Map<String, Object>> list) {
        List<Map<String, Object>> sanitized = new ArrayList<>();
        if (list == null) return sanitized;

        for (Map<String, Object> row : list) {
            Map<String, Object> clean = new LinkedHashMap<>();
            for (Map.Entry<String, Object> entry : row.entrySet()) {
                String key = entry.getKey();
                Object value = entry.getValue();

                try {
                    if (value == null) {
                        clean.put(key, null);
                    } else if (value instanceof Clob) {
                        Clob clob = (Clob) value;
                        clean.put(key, clob.getSubString(1, (int) clob.length()));
                    } else if (value instanceof Timestamp) {
                        clean.put(key, value.toString());
                    } else if (value instanceof java.sql.Date || value instanceof java.sql.Time) {
                        clean.put(key, value.toString());
                    } else if (value instanceof Number || value instanceof Boolean || value instanceof String) {
                        clean.put(key, value);
                    } else {
                        clean.put(key, String.valueOf(value));
                    }
                } catch (Exception e) {
                    clean.put(key, null);
                }
            }
            sanitized.add(clean);
        }
        return sanitized;
    }
	
    /**
     * 페이지 범위 내 전체 리스트 조회
     */
    public List<Map<String, Object>> selectAllFromTo(int start, int end) {
        Map<String, Object> param = new HashMap<>();
        param.put("start", start);
        param.put("end", end);

        List<Map<String, Object>> result = dao.selectAllFromTo(param);
        return sanitizeForJson(result);
    }
    
    /**
     * 필터 조건(status, deptCode)에 따른 조회
     */
    public List<Map<String, Object>> selectByFilterFromTo(String status, String deptCode, int start, int end) {
        Map<String, Object> param = new HashMap<>();
        param.put("status", status);
        param.put("deptCode", deptCode);
        param.put("start", start);
        param.put("end", end);

        List<Map<String, Object>> result = dao.selectByFilterFromTo(param);
        return sanitizeForJson(result);
    }
    
    /**
     * 전체 레코드 개수 조회
     */
    public int getRecordTotalCount() {
        return dao.getCount();
    }

    /**
     * 필터 조건 적용 시 총 개수 조회
     */
    public int getCountByFilter(String status, String deptCode) {
        Map<String, Object> param = new HashMap<>();
        param.put("status", status);
        param.put("deptCode", deptCode);
        return dao.getCountByFilter(param);
    }
    
    /**
     * 상태 업데이트
     */
    public int updateStatus(int targetseq, String newStatus) {
        Map<String, Object> param = new HashMap<>();
        param.put("targetseq", targetseq);
        param.put("newStatus", newStatus);
        return dao.updateStatus(param);
    }

    // 디테일 페이지
    public Pto_requestDTO toDetailPtoRequest(int seq) {
    	return dao.toDetailPtoRequest(seq);
    }
    
}