package members.quitter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/*
  	퇴사 처리 등 기능 구현 controller
*/

@Controller
@RequestMapping("")
public class QuitterController {
	@Autowired
	private QuitterService QuitterService;
}
