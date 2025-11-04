package com.kedu.admin.invite;

import java.util.List;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.kedu.members.member.MemberDTO;
import com.kedu.members.member.MemberService;

@Controller
@RequestMapping("/invite")
public class InviteController {

	@Autowired
	private InviteService inviteService;

	@Autowired
	private MemberService memberService;

	// �쉶�궗 肄붾뱶�뿉 �뵲瑜� 珥덈� + �쉶�썝 �젙蹂� 由ъ뒪�듃 議고쉶 (�쉶�썝 愿�由�)
	@GetMapping("/list")
	public String getInvitesByCompany(@RequestParam(value = "company_code", required = false) String company_code,
			Model model, HttpSession session) {

		if (company_code == null) {
			// company_code = (String) session.getAttribute("company_code");
			company_code = "회사코드요"; // �꽭�뀡�뿉�꽌 媛��졇�삱 �삁�젙
		}

		// DB�뿉�꽌 �듅�씤�맂 珥덈� 由ъ뒪�듃 議고쉶
		List<InviteDTO> invites = inviteService.getInvitesByCompany(company_code);

		// �듅�씤�맂 珥덈��뿉 �빐�떦�븯�뒗 �쉶�썝 �젙蹂� 留ㅽ븨
		for (InviteDTO invite : invites) {
			MemberDTO member = memberService.getMemberByEmailAndCompany(invite.getEmail(), company_code);
			if (member != null) {
				invite.setStatus(member.getStatus()); // �쉶�썝 �긽�깭 諛섏쁺
			}
		}

		model.addAttribute("invites", invites);
		return "/management/invite";
	}

	// 珥덈� �벑濡�
	@PostMapping("/add")
	public String addInvite(@ModelAttribute InviteDTO dto, Model model, HttpSession session) {
		// String company_code = (String) session.getAttribute("company_code");
		dto.setCompany_code("�쉶�궗肄붾뱶�슂"); // �꽭�뀡�뿉�꽌 媛��졇�삱 �삁�젙
		dto.setStatus("誘몄듅�씤");
		int result = inviteService.addInvite(dto);
		model.addAttribute("message", result > 0 ? "珥덈� �벑濡� �꽦怨�" : "珥덈� �벑濡� �떎�뙣");
		return "redirect:/invite/list?company_code=" + dto.getCompany_code();
	}

	// 珥덈� �긽�깭 蹂�寃� (�듅�씤/嫄곗젅) - Ajax �궗�슜
	@PostMapping("/status")
	@ResponseBody
	public String updateInviteStatus(@RequestParam String email, @RequestParam("company_code") String companyCode,
			@RequestParam String status) {
		try {
			MemberDTO member = memberService.getMemberByEmailAndCompany(email, companyCode);
			if (member != null) {
				member.setStatus(status);
				memberService.updateMemberStatus(member);
				return "success";
			}
			return "fail";
		} catch (Exception e) {
			e.printStackTrace();
			return "fail";
		}
	}

}
