package com.kedu.members.quitter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/*
    퇴사자 등록용 Controller
*/
@Controller
@RequestMapping("")
public class QuitterController {

	@Autowired
	private QuitterService quitterService;
}
