package com.kedu.admin.company;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/*
  		 회사 정보 이용 기능 구현 controller
*/

@Controller
@RequestMapping("/admin")
public class CompanyController {
	@Autowired
	private CompanyService CompanyService;
	@Autowired
	private EmailAuthService emailService;
	
	
	// 로그인
	@RequestMapping("/login")
	public String login(CompanyDTO dto,  Model m, HttpSession session) {
		int result = CompanyService.login(dto);
		if(result > 0) {
			session.setAttribute("id", dto.getAdmin_email());
			m.addAttribute("id", dto.getAdmin_email());
			return "commute/commute";
		}else {
			return "redirect:/";
		}
	}
	
	// 비밀번호 찾기 폼 이동
	@RequestMapping("/findpwPage")
	public String findpwPage() {
		return "findpw/findpw";
	}
	
	// 존재하는 관리자인지 검색
	@RequestMapping("/emilAuth")
	@ResponseBody
	public int emailAuth(CompanyDTO dto) {
		return CompanyService.emailAuth(dto);
	}
	
	// 이메일 인증 발송
	@RequestMapping("/emailCheck")
	@ResponseBody
	public String emailCheck(CompanyDTO dto) throws Exception{
		String result = emailService.doPost(dto.getAdmin_email());
		System.out.println(result);
		if(result!=null) {
			return result;
		}else {
			return "실패";
		}
	}
	
	// 비밀번호 재설정 화면 이동
	@RequestMapping("/gnewpw")
	public String gnewpw(CompanyDTO dto, Model m) {
		m.addAttribute("admin_email", dto.getAdmin_email());
		return "findpw/gnewpw";
	}
	
	// 비밀번호 재설정
	@RequestMapping("/updatePw")
	public String updatePw(CompanyDTO dto, Model m) {
		int result = CompanyService.updatePw(dto);
		if(result>0) {
			return "redirect:/";
		}else {
			return "redirect:/emailError";
		}
		
	}
	
	
	// 예외 처리
    @ExceptionHandler
    public String handleMessagingException(Exception e, Model model) {
        e.printStackTrace();
        model.addAttribute("errorMessage", "이메일 발송 중 오류가 발생했습니다.");
        return "emailError"; // emailError.jsp 로 포워딩
    }
}
