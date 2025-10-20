package com.kedu.admin.department;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/*
	부서 설정 등 관련 기능 구현 controller
*/


@Controller
@RequestMapping("")
public class DepartmentController {
	@Autowired
	private DepartmentService DepartmentService;
}
