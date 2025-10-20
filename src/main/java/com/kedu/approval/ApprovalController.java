package com.kedu.approval;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/*
	전자결재 기능 관련 controller
*/

@Controller
@RequestMapping("")
public class ApprovalController {
	@Autowired
	private ApprovalService ApprovalService;
}
