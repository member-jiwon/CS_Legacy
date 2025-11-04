package com.kedu.approval;

import java.sql.Clob;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ApprovalService {

    @Autowired
    private ApprovalDAO dao;

//DB 결과값을 JSON 직렬화-friendly한 Map으로 변환
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

// 상태값 변환 (필요 시 사용)
//    private void convertStatusForUI(List<Map<String, Object>> list) {
//        if (list == null) return;
//        for (Map<String, Object> row : list) {
//            Object status = row.get("APPROVAL_STATUS");
//            if (status != null) {
//                switch (status.toString()) {
//                    case "w":
//                        row.put("APPROVAL_STATUS", "w"); // 대기중
//                        break;
//                    case "y":
//                        row.put("APPROVAL_STATUS", "y"); // 승인
//                        break;
//                    case "n":
//                        row.put("APPROVAL_STATUS", "n"); // 반려
//                        break;
//                }
//            }
//        }
//    }

// 페이지 범위 내 전체 리스트 조회
//    public List<Map<String, Object>> selectAllFromTo(int start, int end) {
//        Map<String, Object> param = new HashMap<>();
//        param.put("start", start);
//        param.put("end", end);
//
//        List<Map<String, Object>> result = dao.selectAllFromTo(param);
////        convertStatusForUI(result);
//        return sanitizeForJson(result);
//    }

    
// 필터 조건(status, deptCode)에 따른 조회
    public List<Map<String, Object>> selectByFilterFromTo(String status, String deptCode, int start, int end, String company_code) {
        Map<String, Object> param = new HashMap<>();
        param.put("status", status);
        param.put("deptCode", deptCode);
        param.put("start", start);
        param.put("end", end);
        param.put("company_code", company_code);

        List<Map<String, Object>> result = dao.selectByFilterFromTo(param);
//        convertStatusForUI(result);
        return sanitizeForJson(result);
    }

//    /**
//     * 전체 레코드 개수 조회
//     */
//    public int getRecordTotalCount() {
//        return dao.getCount();
//    }

    
// 필터 조건 적용 시 총 개수 조회
    public int getCountByFilter(String status, String deptCode, String company_code) {
        Map<String, Object> param = new HashMap<>();
        param.put("status", status);
        param.put("deptCode", deptCode);
        param.put("company_code", company_code);
        return dao.getCountByFilter(param);
    }

//상태 업데이트
    public int updateStatus(int targetseq, String newStatus) {
        Map<String, Object> param = new HashMap<>();
        param.put("targetseq", targetseq);
        param.put("newStatus", newStatus);
        return dao.updateStatus(param);
    }

    // 디테일 페이지
    public ApprovalDTO toDetailApproval(int seq) {
    	return dao.toDetailApproval(seq);
    }




}
