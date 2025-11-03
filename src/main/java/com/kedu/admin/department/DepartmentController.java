package com.kedu.admin.department;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/*
	부서 설정 등 관련 기능 구현 controller
*/

@Controller
@RequestMapping("/department")
public class DepartmentController {
	@Autowired
	private DepartmentService departmentService;

	// 부서 리스트 조회
	@GetMapping("/list")
	public String departmentList(Model model) {
		List<DepartmentDTO> departments = departmentService.getDepartmentsWithEmployeeCount();
		model.addAttribute("departmentList", departments);
		return "department/department";
	}

	// 부서 추가 팝업/모달 뷰
	@GetMapping("/post")
	public String departmentPost(Model model) {
		// 부서 추가 모달에서 기존 부서 목록을 보여주기 위해 다시 리스트를 전달할 수도 있습니다.
		List<DepartmentDTO> departments = departmentService.getAllDept();
		model.addAttribute("allDepartments", departments);
		return "department/departmentPost";
	}

	// 부서 추가
	@PostMapping("/add")
	@ResponseBody
	public String addDepartment(@RequestParam("dept_name") String dept_name) {
		try {
			String company_code = "회사코드요"; // 회사 코드 입력
			departmentService.insertDepartment(dept_name, company_code);
			return "success";
		} catch (Exception e) { 
			// 오류 시
			e.printStackTrace();
			return "failure";
		}
	}

	// 부서 삭제
	@PostMapping("/delete")
	@ResponseBody
	public String deleteDepartment(@RequestParam("dept_code") int dept_code) {
		try {
			departmentService.deleteDepartment(dept_code);
			return "success";
		} catch (Exception e) {
			e.printStackTrace();
			return "failure";
		}
	}
}