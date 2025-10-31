package com.kedu.admin.invite;

import java.util.List;

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

	// 회사 코드 기준 회원 + 초대 상태 리스트 조회 (회원 기준)
	@GetMapping("/list")
	public String getInvitesByCompany(@RequestParam(value = "company_code", required = false) String company_code,
			Model model) {

		if (company_code == null) {
			company_code = "회사코드요"; // 기본값 예시
		}

		// DB에서 최신 초대 리스트 가져오기
		List<InviteDTO> invites = inviteService.getInvitesByCompany(company_code);

		// 초대 상태가 회원 테이블과 동기화 되어 있으면 memberService에서 가져오기
		for (InviteDTO invite : invites) {
			MemberDTO member = memberService.getMemberByEmailAndCompany(invite.getEmail(), company_code);
			if (member != null) {
				invite.setStatus(member.getStatus()); // 최신 상태 반영
			}
		}

		model.addAttribute("invites", invites); // JSP에서 ${invites}로 사용
		return "/management/invite";
	}

	// 초대 추가
	@PostMapping("/add")
	public String addInvite(@ModelAttribute InviteDTO dto, Model model) {
		dto.setStatus("미승인");
		int result = inviteService.addInvite(dto);
		model.addAttribute("message", result > 0 ? "초대 추가 완료" : "초대 추가 실패");
		return "redirect:/invite/list?company_code=" + dto.getCompany_code();
	}

	// 초대 상태 변경 (승인/거절) - Ajax용
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
