package com.kedu.members.commute;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kedu.admin.department.DepartmentDTO;
import com.kedu.admin.department.DepartmentService;



@RequestMapping("/commute")
@Controller
public class CommuteController {
	@Autowired
	private CommuteService commuteService;
	@Autowired
	private com.google.gson.Gson gson;
    @Autowired
    private DepartmentService departmentService;
    
	@GetMapping("")
	public String dashboard(
	        @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDate,
	        @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDate,
	        @RequestParam(defaultValue = "전체") String type,
	        Model model,
	        HttpSession session) {

	    //오늘날자, 1년전날자
	    LocalDate today = LocalDate.now();
	    LocalDate oneYearAgo = today.minusYears(1);
	    //파라미터로 받은거 없으면 오늘로 고정
	    if (startDate == null || endDate == null) {
	        startDate = today;
	        endDate = today;
	    }

	    
	    String company_code=(String)session.getAttribute("company_code");
	    // 서비스 호출
	    Map<String, Integer> attendanceStats = commuteService.getAttendanceStats(startDate, endDate, type,company_code);
	    Map<String, Integer> workStats = commuteService.getWorkStats(startDate, endDate, type, company_code);
	    Map<String, Integer> approvalStats = commuteService.getApprovalStats(startDate, endDate, type,company_code);


	    model.addAttribute("attendanceStatsJson", gson.toJson(attendanceStats));
	    model.addAttribute("workStatsJson", gson.toJson(workStats));
	    model.addAttribute("approvalStatsJson", gson.toJson(approvalStats));

	    model.addAttribute("startDate", startDate);
	    model.addAttribute("endDate", endDate);
	    model.addAttribute("type", type);

	    // min/max용 날짜 추가
	    model.addAttribute("today", today);
	    model.addAttribute("oneYearAgo", oneYearAgo);

	    return "commute/commute"; // JSP 寃쎈줈
	}
	
	
	
	// 팀목록 가져오기
	@GetMapping("/teamList")
	@ResponseBody
	public List<DepartmentDTO> list (HttpSession session){
		String company_code= (String)session.getAttribute("company_code");
				
        // 부서 목록 조회
        List<DepartmentDTO> depts = departmentService.getAllDeptCode(company_code);
		return depts;
	}
	

}
