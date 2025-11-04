package com.kedu.admin.company;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

// 회사 정보 이용 관련 기능을 수행하는 Controller

@Controller
@RequestMapping("/admin")
public class CompanyController {

	@Autowired
	private CompanyService CompanyService;

	@Autowired
	private EmailAuthService emailService;

	// 로그인
	@RequestMapping("/login")
	public String login(CompanyDTO dto, Model m, HttpSession session) {
		CompanyDTO result = CompanyService.login(dto);
		if (result != null) {
			session.setAttribute("id", result.getAdmin_email());

			// 세션에 회사 이름(코드)도 저장해야 해당 이름으로 구분할 수 있음
			session.setAttribute("company_code", result.getCompany_code());
			return "redirect:/commute"; // 근태관리 컨트롤러로 리다이렉트 (지원 수정)
		} else {
			return "redirect:/";
		}
	}

	// 비밀번호 찾기 페이지 이동
	@RequestMapping("/findpwPage")
	public String findpwPage() {
		return "findpw/findpw";
	}

	// 존재하는 관리자 이메일인지 확인
	@RequestMapping("/emilAuth")
	@ResponseBody
	public int emailAuth(CompanyDTO dto) {
		return CompanyService.emailAuth(dto);
	}

	// 이메일 인증번호 전송
	@RequestMapping("/emailCheck")
	@ResponseBody
	public String emailCheck(CompanyDTO dto) throws Exception {
		String result = emailService.doPost(dto.getAdmin_email());
		System.out.println(result);
		if (result != null) {
			return result;
		} else {
			return "실패";
		}
	}

	// 비밀번호 재설정 화면 이동
	@RequestMapping("/gnewpw")
	public String gnewpw(CompanyDTO dto, Model m) {
		m.addAttribute("admin_email", dto.getAdmin_email());
		return "findpw/gnewpw";
	}

	// 비밀번호 재설정 처리
	@RequestMapping("/updatePw")
	public String updatePw(CompanyDTO dto, Model m) {
		int result = CompanyService.updatePw(dto);
		if (result > 0) {
			return "redirect:/";
		} else {
			return "redirect:/emailError";
		}
	}

	// 예외 처리 (이메일 전송 중 오류 발생 시)
	@ExceptionHandler
	public String handleMessagingException(Exception e, Model model) {
		e.printStackTrace();
		model.addAttribute("errorMessage", "이메일 전송 중 오류가 발생했습니다.");
		return "emailError"; // emailError.jsp로 포워딩
	}
}
