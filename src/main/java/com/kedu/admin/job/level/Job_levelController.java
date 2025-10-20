package com.kedu.admin.job.level;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/*
		직급 관련 기능 구현 controller
*/

@Controller
@RequestMapping("")
public class Job_levelController {
	@Autowired
	private Job_levelService Job_levelService;
}
