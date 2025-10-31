package com.kedu.members.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/*
 * 		사원 회원가입 및 마이페이지 구현 Controller
 * */

@RequestMapping("")
@RestController
public class MemberController {
	@Autowired
	private MemberService memberService;
	// 알아 고쳐쓸려면 알아서 고쳐쓰소
}