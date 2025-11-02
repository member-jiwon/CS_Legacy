package com.kedu.members.commute;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

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

/*
 *  	域뱀눛源��꽴占썹뵳占� 疫꿸퀡�뮟 �뤃�뗭겱 Controller
 * */

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
	        Model model) {

	    // 湲곕낯媛�: �삤�뒛 �궇吏�
	    LocalDate today = LocalDate.now();
	    LocalDate oneYearAgo = today.minusYears(1);

	    if (startDate == null || endDate == null) {
	        startDate = today;
	        endDate = today;
	    }

	    // �꽌鍮꾩뒪 �샇異�
	    Map<String, Integer> attendanceStats = commuteService.getAttendanceStats(startDate, endDate, type);
	    Map<String, Integer> workStats = commuteService.getWorkStats(startDate, endDate, type);
	    Map<String, Integer> approvalStats = commuteService.getApprovalStats(startDate, endDate, type);

	    model.addAttribute("attendanceStatsJson", gson.toJson(attendanceStats));
	    model.addAttribute("workStatsJson", gson.toJson(workStats));
	    model.addAttribute("approvalStatsJson", gson.toJson(approvalStats));

	    model.addAttribute("startDate", startDate);
	    model.addAttribute("endDate", endDate);
	    model.addAttribute("type", type);

	    // �윍� min/max�슜 �궇吏� 異붽�
	    model.addAttribute("today", today);
	    model.addAttribute("oneYearAgo", oneYearAgo);

	    return "commute/commute"; // JSP 寃쎈줈
	}
	
	
	
	// 팀목록 가져오기
	@GetMapping("/teamList")
	@ResponseBody
	public List<DepartmentDTO> list (){
        // 부서 목록 조회
        List<DepartmentDTO> depts = departmentService.getAllDeptCode();
		return depts;
	}
	

}
