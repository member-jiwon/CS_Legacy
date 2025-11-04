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

	/* 공지사항 목록 */
	@GetMapping("/list")
	public String noticeList(Model model) {
		List<NoticeDTO> notices = noticeService.getAllNotices();
		model.addAttribute("notices", notices);
		return "board/boardList";
	}

	/* 공지사항 작성 페이지 */
	@GetMapping("/post")
	public String boardPost() {
		return "board/boardPost";
	}

	/* 공지사항 작성 처리 */
	@PostMapping("/post")
	public String boardPost(@ModelAttribute NoticeDTO dto, HttpSession session) {
		String email = (String) session.getAttribute("loginEmail");
		if (email == null)
			email = "admin@company.com";

		dto.setWriter_email(email);
		dto.setCompany_code("COMP001");

		noticeService.insertNotice(dto);

		return "redirect:/notice/list";
	}

	/* 공지사항 상세 페이지 */
	@GetMapping("/detail")
	public String editNotice(@RequestParam("notice_seq") int notice_seq, Model model) {
		NoticeDTO notice = noticeService.selectNoticeById(notice_seq);
		model.addAttribute("notice", notice);
		return "board/boardDatail";
	}

	/* 공지사항 수정 처리 */
	@PostMapping("/update")
	@ResponseBody
	public String updateNotice(@ModelAttribute NoticeDTO dto, HttpSession session) {
		String email = (String) session.getAttribute("loginEmail");
		if (email == null)
			email = "admin@company.com";

		dto.setWriter_email(email);
		noticeService.updateNotice(dto);

		// "success" 문자열 반환
		return "success";
	}

	/* 공지사항 삭제 처리 */
	@PostMapping("/delete")
	public String deleteNotice(@RequestParam("notice_seq") int noticeSeq) {
		noticeService.deleteNotice(noticeSeq);
		return "redirect:/notice/list";
	}
}