package com.kedu.approval;

import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.kedu.admin.department.DepartmentDTO;
import com.kedu.admin.department.DepartmentService;
import com.kedu.file.FileConstants;
import com.kedu.file.FileDTO;
import com.kedu.file.FileService;

import util.PageConfig;

@Controller
@RequestMapping("/approval")
public class ApprovalController {

    @Autowired
    private ApprovalService approvalService;
    @Autowired
    private DepartmentService departmentService;
    @Autowired
    private FileService fileService;
    @Autowired
    private Gson gson;

    /* 페이지에 맞게 데이터 구성, 필터까지 합쳐서 */
    @RequestMapping("")
    public String approvalList(
        @RequestParam(required = false, defaultValue = "all") String status,
        @RequestParam(required = false, defaultValue = "all") String departmentType,
        @RequestParam(defaultValue = "1") int cpage,
        Model m) throws Exception {

        // 부서 목록 조회
        List<DepartmentDTO> depts = departmentService.getAllDeptCode();

        // 필터링용 값 처리
        String rawStatus = status;
        String rawDept = departmentType;

        if ("all".equalsIgnoreCase(status)) status = null;
        if ("all".equalsIgnoreCase(departmentType)) departmentType = null;

        // 페이징 계산
        int start = cpage * PageConfig.RECORD_COUNT_PER_PAGE - (PageConfig.RECORD_COUNT_PER_PAGE - 1);
        int end = cpage * PageConfig.RECORD_COUNT_PER_PAGE;

        // 결재 리스트 및 총 개수 조회
        List<Map<String, Object>> list = approvalService.selectByFilterFromTo(status, departmentType, start, end);
        int totalCount = approvalService.getCountByFilter(status, departmentType);

        // JSON 변환 및 모델에 담기
        m.addAttribute("list", gson.toJson(list));
        m.addAttribute("depts", gson.toJson(depts));

        // 페이징 정보
        m.addAttribute("recordTotalCount", totalCount);
        m.addAttribute("recordCountPerPage", PageConfig.RECORD_COUNT_PER_PAGE);
        m.addAttribute("naviCountPerPage", PageConfig.NAVI_COUNT_PER_PAGE);
        m.addAttribute("currentPage", cpage);

        // 드롭다운 선택값 유지용
        m.addAttribute("selectedDept", String.valueOf(rawDept));
        m.addAttribute("selectedStatus", rawStatus);

        // 디버깅 로그
        System.out.println("결재 리스트 JSON: " + gson.toJson(list));
        System.out.println("부서 목록 JSON: " + gson.toJson(depts));
        System.out.println("선택된 회사: " + rawDept);
        System.out.println("선택된 상태: " + rawStatus);

        return "/approval/approval";
    }

    /* 상태 업데이트 (비동기 유지) */
    @RequestMapping("/updatestatus")
    @ResponseBody
    public String updateStatus(int targetseq, String newStatus) {
        int result = approvalService.updateStatus(targetseq, newStatus);
        if (result > 0) {
            return newStatus; // 성공 시 변경된 상태 코드 그대로 리턴 ("y", "n" 등)
        } else {
            return "fail";
        }
    }

    /* 디테일 페이지로 이동 */
    @RequestMapping("/detail")
    public String toDetailApproval(@RequestParam int seq, HttpSession session, Model m) {
        // 1. 전자결재 내용
        ApprovalDTO result = approvalService.toDetailApproval(seq);
        m.addAttribute("dtoJson", gson.toJson(result)); // JS 용
        m.addAttribute("dto", result);

        // 2. 파일 리스트 내용
        List<FileDTO> fileList = fileService.getFilesByParent(seq, FileConstants.FA);
        m.addAttribute("dtofiles", gson.toJson(fileList)); // JS 용
        m.addAttribute("files", fileList);
        System.out.println("파일 리스트: " + gson.toJson(fileList));

        return "/approval/approvalDetail";
    }
}