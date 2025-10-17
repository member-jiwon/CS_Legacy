package admin.company;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/*
  		 회사 정보 이용 기능 구현 controller
*/

@Controller
@RequestMapping("")
public class CompanyController {
	@Autowired
	private CompanyService CompanyService;
}
