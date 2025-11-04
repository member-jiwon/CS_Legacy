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

	// 회사 코드에 따른 초대 + 회원 정보 리스트 조회 (회원 관리)
	@GetMapping("/list")
	public String getInvitesByCompany(@RequestParam(value = "company_code", required = false) String company_code,
			Model model, HttpSession session) {

		if (company_code == null) {
			// company_code = (String) session.getAttribute("company_code");
			company_code = "회사코드요"; // 세션에서 가져올 예정
		}

		// DB에서 승인된 초대 리스트 조회
		List<InviteDTO> invites = inviteService.getInvitesByCompany(company_code);

		// 승인된 초대에 해당하는 회원 정보 매핑
		for (InviteDTO invite : invites) {
			MemberDTO member = memberService.getMemberByEmailAndCompany(invite.getEmail(), company_code);
			if (member != null) {
				invite.setStatus(member.getStatus()); // 회원 상태 반영
			}
		}

		model.addAttribute("invites", invites);
		return "/management/invite";
	}

	// 초대 등록
	@PostMapping("/add")
	public String addInvite(@ModelAttribute InviteDTO dto, Model model, HttpSession session) {
		// String company_code = (String) session.getAttribute("company_code");
		dto.setCompany_code("회사코드요"); // 세션에서 가져올 예정
		dto.setStatus("미승인");
		int result = inviteService.addInvite(dto);
		model.addAttribute("message", result > 0 ? "초대 등록 성공" : "초대 등록 실패");
		return "redirect:/invite/list?company_code=" + dto.getCompany_code();
	}

	// 초대 상태 변경 (승인/거절) - Ajax 사용
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
