package com.kedu.notice;

import java.util.List;

import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/notice")
public class NoticeController {

	@Autowired
	private NoticeService noticeService;

	/* 怨듭��궗�빆 紐⑸줉 */
	@GetMapping("/list")
	public String noticeList(Model model) {
		List<NoticeDTO> notices = noticeService.getAllNotices();
		model.addAttribute("notices", notices);
		return "board/boardList";
	}

	/* 怨듭��궗�빆 �옉�꽦 �럹�씠吏� */
	@GetMapping("/post")
	public String boardPost() {
		return "board/boardPost";
	}

	/* 怨듭��궗�빆 �옉�꽦 泥섎━ */
	@PostMapping("/post")
	public String boardPost(@ModelAttribute NoticeDTO dto, HttpSession session) {
		String email = (String) session.getAttribute("id");
		String company_code = (String) session.getAttribute("company_code");
		
		dto.setWriter_email(email);
		dto.setCompany_code(company_code);

		noticeService.insertNotice(dto);
		return "redirect:/notice/list";
	}

	/* 怨듭��궗�빆 �긽�꽭 �럹�씠吏� */
	@GetMapping("/detail")
	public String editNotice(@RequestParam("notice_seq") int notice_seq, Model model) {
		NoticeDTO notice = noticeService.selectNoticeById(notice_seq);
		model.addAttribute("notice", notice);
		return "board/boardDatail";
	}

	/* 怨듭��궗�빆 �닔�젙 泥섎━ */
	@PostMapping("/update")
	@ResponseBody
	public String updateNotice(@ModelAttribute NoticeDTO dto, HttpSession session) {
		String email = (String) session.getAttribute("loginEmail");
		if (email == null)
			email = "admin@company.com";

		dto.setWriter_email(email);
		noticeService.updateNotice(dto);

		// "success" 臾몄옄�뿴 諛섑솚
		return "success";
	}

	/* 怨듭��궗�빆 �궘�젣 泥섎━ */
	@PostMapping("/delete")
	public String deleteNotice(@RequestParam("notice_seq") int noticeSeq) {
		noticeService.deleteNotice(noticeSeq);
		return "redirect:/notice/list";
	}
}