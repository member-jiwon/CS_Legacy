package com.kedu.admin.company;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
//import com.kedu.admin.company.EmailAuthService;
/*
  		 �쉶�궗 �젙蹂� �씠�슜 湲곕뒫 援ы쁽 controller
*/

@Controller
@RequestMapping("/admin")
public class CompanyController {
	@Autowired
	private CompanyService CompanyService;
	@Autowired
	private EmailAuthService emailService;
	
	
	// 濡쒓렇�씤
	@RequestMapping("/login")
	public String login(CompanyDTO dto,  Model m, HttpSession session) {
		int result = CompanyService.login(dto);
		if(result > 0) {
			session.setAttribute("id", dto.getAdmin_email());
			//m.addAttribute("id", dto.getAdmin_email()); 모델붙이면 쿼맆라미터로 값 전달되서 지움
			return "redirect:/commute";//지원이 바꿔놓음 : 근태관리 내용가져오는 컨트롤러랑 매핑시킴
		}else {
			return "redirect:/";
		}
	}
	
	// 鍮꾨�踰덊샇 李얘린 �뤌 �씠�룞
	@RequestMapping("/findpwPage")
	public String findpwPage() {
		return "findpw/findpw";
	}
	
	// 議댁옱�븯�뒗 愿�由ъ옄�씤吏� 寃��깋
	@RequestMapping("/emilAuth")
	@ResponseBody
	public int emailAuth(CompanyDTO dto) {
		return CompanyService.emailAuth(dto);
	}
	
	// �씠硫붿씪 �씤利� 諛쒖넚
	@RequestMapping("/emailCheck")
	@ResponseBody
	public String emailCheck(CompanyDTO dto) throws Exception{
		String result = emailService.doPost(dto.getAdmin_email());
		System.out.println(result);
		if(result!=null) {
			return result;
		}else {
			return "�떎�뙣";
		}
	}
	
	// 鍮꾨�踰덊샇 �옱�꽕�젙 �솕硫� �씠�룞
	@RequestMapping("/gnewpw")
	public String gnewpw(CompanyDTO dto, Model m) {
		m.addAttribute("admin_email", dto.getAdmin_email());
		return "findpw/gnewpw";
	}
	
	// 鍮꾨�踰덊샇 �옱�꽕�젙
	@RequestMapping("/updatePw")
	public String updatePw(CompanyDTO dto, Model m) {
		int result = CompanyService.updatePw(dto);
		if(result>0) {
			return "redirect:/";
		}else {
			return "redirect:/emailError";
		}
		
	}
	
	
	// �삁�쇅 泥섎━
    @ExceptionHandler
    public String handleMessagingException(Exception e, Model model) {
        e.printStackTrace();
        model.addAttribute("errorMessage", "�씠硫붿씪 諛쒖넚 以� �삤瑜섍� 諛쒖깮�뻽�뒿�땲�떎.");
        return "emailError"; // emailError.jsp 濡� �룷�썙�뵫
    }
}
