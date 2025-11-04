package com.kedu.members.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/*
 * 		회원 정보 조회 및 관리용 Controller
 */
@RequestMapping("")
@RestController
public class MemberController {

	@Autowired
	private MemberService memberService;

	// 회원 목록 조회, 회원 상태 변경 등 기능 구현 예정
}
