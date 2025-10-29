package com.kedu.members.commute;

import java.time.LocalDate;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/*
 *  	洹쇳깭愿�由� 湲곕뒫 援ы쁽 Controller
 * */

@RequestMapping("/commute")
@Controller
public class CommuteController {
	@Autowired
	private CommuteService commuteService;
	@Autowired
	private com.google.gson.Gson gson;
	
	@GetMapping("")
	public String dashboard(
	        @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDate,
	        @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDate,
	        @RequestParam(defaultValue = "전체") String type,
	        Model model) {

	    // 기본값: 오늘 날짜
	    LocalDate today = LocalDate.now();
	    LocalDate oneYearAgo = today.minusYears(1);

	    if (startDate == null || endDate == null) {
	        startDate = today;
	        endDate = today;
	    }

	    // 서비스 호출
	    Map<String, Integer> attendanceStats = commuteService.getAttendanceStats(startDate, endDate, type);
	    Map<String, Integer> workStats = commuteService.getWorkStats(startDate, endDate, type);
	    Map<String, Integer> approvalStats = commuteService.getApprovalStats(startDate, endDate, type);

	    model.addAttribute("attendanceStatsJson", gson.toJson(attendanceStats));
	    model.addAttribute("workStatsJson", gson.toJson(workStats));
	    model.addAttribute("approvalStatsJson", gson.toJson(approvalStats));

	    model.addAttribute("startDate", startDate);
	    model.addAttribute("endDate", endDate);
	    model.addAttribute("type", type);

	    // 🔹 min/max용 날짜 추가
	    model.addAttribute("today", today);
	    model.addAttribute("oneYearAgo", oneYearAgo);

	    return "commute/commute"; // JSP 경로
	}

}
