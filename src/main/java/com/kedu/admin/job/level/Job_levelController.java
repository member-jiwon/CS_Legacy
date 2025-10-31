package com.kedu.admin.job.level;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.kedu.admin.department.DepartmentDTO;
import com.kedu.admin.department.DepartmentService;
import com.kedu.admin.invite.InviteDTO;
import com.kedu.admin.invite.InviteService;

@Controller
@RequestMapping("/job")
public class Job_levelController {

	@Autowired
	private Job_levelService job_levelService;

	@Autowired
	private DepartmentService departmentService;

	// 회사 초대 코드
	@GetMapping("/invitationCode")
	public String gtInvitationCodePage(Model model) {
		try {

			String companyCode = job_levelService.selectCompanyCode();
			model.addAttribute("companyCode", companyCode);
		} catch (Exception e) {
			e.printStackTrace();
			// companyCode가 null일 경우 model.addAttribute("companyCode", null); 명시 권장
			model.addAttribute("companyCode");
		}
		return "management/invitationCode";
	}

	// 사원 목록 조회
	@GetMapping("/list")
	public String management(Model model) {
		// 부서/직급 이름 포함된 승인된 사원 목록 조회
		List<Map<String, Object>> members = job_levelService.getApprovedMembersWithJobDetail();
		model.addAttribute("members", members);

		// 부서 목록
		List<DepartmentDTO> depts = departmentService.getAllDept();
		model.addAttribute("depts", depts);

		// 직급 목록
		List<Job_levelDTO> levels = job_levelService.getselect();
		model.addAttribute("levels", levels);

		return "management/management";
	}

	// 직급 업데이트
	@PostMapping("/updateLevel")
	@ResponseBody
	public String updateLevel(@RequestParam String email, @RequestParam String level_code,
			@RequestParam String company_code) {
		int result = job_levelService.updateMemberLevel(email, level_code, company_code);
		// 반환 로직 추가
		return result > 0 ? "success" : "fail";
	}

	// 부서 업데이트
	@PostMapping("/updateDept")
	@ResponseBody
	public String updateDept(@RequestParam String email, @RequestParam String dept_code,
			@RequestParam String company_code) {
		int result = job_levelService.updateMemberDept(email, dept_code, company_code);
		// 반환 로직 추가
		return result > 0 ? "success" : "fail";
	}
}
