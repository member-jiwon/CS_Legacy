package com.kedu.admin.department;

import java.util.List;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/*
	부서 관리 관련 등록, 수정, 삭제 기능 처리 컨트롤러
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

	// 부서 등록 페이지 이동
	@GetMapping("/post")
	public String departmentPost(Model model) {
		// 모든 부서 리스트를 조회
		List<DepartmentDTO> departments = departmentService.getAllDept();
		model.addAttribute("allDepartments", departments);
		return "department/departmentPost";
	}

	// 부서 등록
	@PostMapping("/add")
	@ResponseBody
	public String addDepartment(@RequestParam("dept_name") String dept_name, HttpSession session) {
		try {
			// String company_code = (String) session.getAttribute("company_code");
			String company_code = "회사코드요"; // 세션에서 가져올 예정
			departmentService.insertDepartment(dept_name, company_code);
			return "success";
		} catch (Exception e) {
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
