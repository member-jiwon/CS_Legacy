package com.kedu.notice;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.kedu.file.FileDTO;
import com.kedu.file.FileService;

@Controller
@RequestMapping("/notice")
public class NoticeController {

	@Autowired
	private NoticeService noticeService;

	@Autowired
	private FileService fileService;

	/* 怨듭��궗�빆 紐⑸줉 */
	@GetMapping("/list")
	public String noticeList(Model model) {
		List<NoticeDTO> notices = noticeService.getAllNotices();
		model.addAttribute("notices", notices);
		return "board/boardList";

	}

	/* 怨듭��궗�빆 �벑濡� �럹�씠吏� */
	@GetMapping("/post")
	public String boardPost() {
		return "board/boardPost";
	}

	/* 怨듭��궗�빆 �벑濡� 泥섎━ */
	@PostMapping("/post")
	public String boardPost(@ModelAttribute NoticeDTO dto,
			@RequestParam(value = "file", required = false) MultipartFile file, HttpSession session) {

		String email = (String) session.getAttribute("loginEmail");
		if (email == null)
			email = "admin@company.com"; // �뀒�뒪�듃�슜
		dto.setWriter_email(email);
		dto.setCompany_code("COMP001");

		noticeService.insertNotice(dto);

		// �뙆�씪 �뾽濡쒕뱶 泥섎━
		if (file != null && !file.isEmpty()) {
			FileDTO fileDTO = new FileDTO();
			fileDTO.setMember_email(email);
			fileDTO.setParent_seq(dto.getNotice_seq());
			fileDTO.setSysname(file.getOriginalFilename()); // �떎�젣�뒗 �꽌踰꾩뿉 ���옣 �썑 �씠由� �꽔�뼱�빞 �븿
			//fileDTO.setOryname(file.getOriginalFilename());
			fileDTO.setFile_type("NOTICE");
			//fileService.saveFile(fileDTO, file);
		}

		return "redirect:/notice/list";
	}

	/* 怨듭��궗�빆 �긽�꽭�럹�씠吏� + 議고쉶�닔 利앷� */
	@GetMapping("/detail")
	public String noticeDetail(@RequestParam("notice_seq") int notice_seq, Model model) {
		noticeService.increaseViewCount(notice_seq); // 議고쉶�닔 利앷�
		NoticeDTO notice = noticeService.selectNoticeById(notice_seq);

		// 泥⑤��뙆�씪 議고쉶 - �뙆�씪 紐⑸줉 異붽� (FileService �궗�슜 �떆)
		List<FileDTO> files = fileService.getFilesByParent(notice_seq, "NOTICE");
		notice.setFiles(files);

		model.addAttribute("notice", notice);
		return "board/boardDatail";
	}

	/* 怨듭��궗�빆 �닔�젙 �럹�씠吏� */
	@GetMapping("/edit")
	public String editNotice(@RequestParam("notice_seq") int notice_seq, Model model) {
		NoticeDTO notice = noticeService.selectNoticeById(notice_seq);
		model.addAttribute("notice", notice);
		return "board/boardDatail";
	}

	/* 怨듭��궗�빆 �닔�젙 泥섎━ */
	@PostMapping("/edit")
	public String updateNotice(@ModelAttribute NoticeDTO dto,
			@RequestParam(value = "file", required = false) MultipartFile file, HttpSession session) {

		String email = (String) session.getAttribute("loginEmail");
		if (email == null)
			email = "admin@company.com"; // �뀒�뒪�듃�슜
		dto.setWriter_email(email);

		noticeService.updateNotice(dto);

		if (file != null && !file.isEmpty()) {
			FileDTO fileDTO = new FileDTO();
			fileDTO.setMember_email(email);
			fileDTO.setParent_seq(dto.getNotice_seq());
			fileDTO.setSysname(file.getOriginalFilename());
			//fileDTO.setOryname(file.getOriginalFilename());
			fileDTO.setFile_type("NOTICE");
			//fileService.saveFile(fileDTO, file);
		}

		return "redirect:/notice/detail?notice_seq=" + dto.getNotice_seq();
	}

	/* 怨듭��궗�빆 �궘�젣 泥섎━ */
	@PostMapping("/delete")
	public String deleteNotice(@RequestParam("notice_seq") int notice_seq) {
		noticeService.deleteNotice(notice_seq);
		return "redirect:/notice/list";
	}
}
