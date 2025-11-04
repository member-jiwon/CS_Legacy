package com.kedu.admin.job.level;

import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.kedu.admin.department.DepartmentDTO;
import com.kedu.admin.department.DepartmentService;
import com.kedu.members.quitter.QuitterService;

@Controller
@RequestMapping("/job")
public class Job_levelController {

	@Autowired
	private Job_levelService job_levelService;

	@Autowired
	private DepartmentService departmentService;

	@Autowired
	private QuitterService quitterService;

	// 회사 초대 코드 페이지
	@GetMapping("/invitationCode")
	public String gtInvitationCodePage(Model model) {
		try {
			String companyCode = job_levelService.selectCompanyCode();
			model.addAttribute("companyCode", companyCode);
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("companyCode", null);
		}
		return "management/invitationCode";
	}

	// 회원 목록 조회
	@GetMapping("/list")
	public String management(Model model) {
		List<Map<String, Object>> members = job_levelService.getApprovedMembersWithJobDetail();
		model.addAttribute("members", members);

		List<DepartmentDTO> depts = departmentService.getAllDept();
		model.addAttribute("depts", depts);

		List<Job_levelDTO> levels = job_levelService.getselect();
		model.addAttribute("levels", levels);

		return "management/management";
	}

	// 직급 변경
	@PostMapping("/updateLevel")
	@ResponseBody
	public String updateLevel(@RequestParam String email, @RequestParam String level_code,
			@RequestParam String company_code, HttpSession session) {
		// String company_code = (String) session.getAttribute("company_code");
		company_code = "회사코드요";
		
		int result = job_levelService.updateMemberLevel(email, level_code, company_code);
		return result > 0 ? "success" : "fail";
	}

	// 부서 변경
	@PostMapping("/updateDept")
	@ResponseBody
	public String updateDept(@RequestParam String email, @RequestParam String dept_code,
			@RequestParam String company_code, HttpSession session) {
		// String company_code = (String) session.getAttribute("company_code");
		company_code = "회사코드요";
		
		int result = job_levelService.updateMemberDept(email, dept_code, company_code);
		return result > 0 ? "success" : "fail";
	}

	// 회원 상태 변경 (재직/퇴사)
	@PostMapping("/updateStatus")
	@ResponseBody
	public String updateStatus(@RequestParam String email, @RequestParam String status,
			@RequestParam String company_code, HttpSession session) {
		// String company_code = (String) session.getAttribute("company_code");
		company_code = "회사코드요";
		
		int result = 0;
		if ("퇴사".equals(status)) {
			result = quitterService.processQuitter(email, company_code);
		} else {
			result = job_levelService.updateMemberStatus(email, status, company_code);
		}
		return result > 0 ? "success" : "fail";
	}
}
