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

	// 사원 상태 업데이트 (직원/퇴사) - 퇴사 처리 시 이 메서드를 사용하도록 수정
	@PostMapping("/updateStatus")
	@ResponseBody
	public String updateStatus(@RequestParam String email, @RequestParam String status,
			@RequestParam String company_code) {

		int result = 0;

		if ("퇴사".equals(status)) {
			// '퇴사' 버튼 클릭 시: 트랜잭션 서비스 호출 (상태 변경 및 퇴사 인서트 동시 처리)
			result = quitterService.processQuitter(email, company_code);
		} else {
			// 직원 버튼 클릭 시: 기존 상태 변경 로직만 호출 (퇴사 테이블에 영향 없음)
			result = job_levelService.updateMemberStatus(email, status, company_code);
		}

		return result > 0 ? "success" : "fail";
	}
}
