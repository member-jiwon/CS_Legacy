package approval;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import admin.company.CompanyService;

/*
	전자결재 기능 관련 controller
*/

@Controller
@RequestMapping("")
public class ApprovalController {
	@Autowired
	private CompanyService CompanyService;
}
