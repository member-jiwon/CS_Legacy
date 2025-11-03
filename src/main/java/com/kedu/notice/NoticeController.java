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

	/* 공지사항 목록 */
	@GetMapping("/list")
	public String noticeList(Model model) {
		List<NoticeDTO> notices = noticeService.getAllNotices();
		model.addAttribute("notices", notices);
		return "board/boardList";

	}

	/* 공지사항 등록 페이지 */
	@GetMapping("/post")
	public String boardPost() {
		return "board/boardPost";
	}

	/* 공지사항 등록 처리 */
	@PostMapping("/post")
	public String boardPost(@ModelAttribute NoticeDTO dto,
			@RequestParam(value = "file", required = false) MultipartFile file, HttpSession session) {

		String email = (String) session.getAttribute("loginEmail");
		if (email == null)
			email = "admin@company.com"; // 테스트용
		dto.setWriter_email(email);
		dto.setCompany_code("COMP001");

		noticeService.insertNotice(dto);

		// 파일 업로드 처리
		if (file != null && !file.isEmpty()) {
			FileDTO fileDTO = new FileDTO();
			fileDTO.setMember_email(email);
			fileDTO.setParent_seq(dto.getNotice_seq());
			fileDTO.setSysname(file.getOriginalFilename()); // 실제는 서버에 저장 후 이름 넣어야 함
			fileDTO.setOryname(file.getOriginalFilename());
			fileDTO.setFile_type("NOTICE");
			fileService.saveFile(fileDTO, file);
		}

		return "redirect:/notice/list";
	}

	/* 공지사항 상세페이지 + 조회수 증가 */
	@GetMapping("/detail")
	public String noticeDetail(@RequestParam("notice_seq") int notice_seq, Model model) {
		noticeService.increaseViewCount(notice_seq); // 조회수 증가
		NoticeDTO notice = noticeService.selectNoticeById(notice_seq);

		// 첨부파일 조회 - 파일 목록 추가 (FileService 사용 시)
		List<FileDTO> files = fileService.getFilesByParentSeq(notice_seq, "NOTICE");
		notice.setFiles(files);

		model.addAttribute("notice", notice);
		return "board/boardDatail";
	}

	/* 공지사항 수정 페이지 */
	@GetMapping("/edit")
	public String editNotice(@RequestParam("notice_seq") int notice_seq, Model model) {
		NoticeDTO notice = noticeService.selectNoticeById(notice_seq);
		model.addAttribute("notice", notice);
		return "board/boardDatail";
	}

	/* 공지사항 수정 처리 */
	@PostMapping("/edit")
	public String updateNotice(@ModelAttribute NoticeDTO dto,
			@RequestParam(value = "file", required = false) MultipartFile file, HttpSession session) {

		String email = (String) session.getAttribute("loginEmail");
		if (email == null)
			email = "admin@company.com"; // 테스트용
		dto.setWriter_email(email);

		noticeService.updateNotice(dto);

		if (file != null && !file.isEmpty()) {
			FileDTO fileDTO = new FileDTO();
			fileDTO.setMember_email(email);
			fileDTO.setParent_seq(dto.getNotice_seq());
			fileDTO.setSysname(file.getOriginalFilename());
			fileDTO.setOryname(file.getOriginalFilename());
			fileDTO.setFile_type("NOTICE");
			fileService.saveFile(fileDTO, file);
		}

		return "redirect:/notice/detail?notice_seq=" + dto.getNotice_seq();
	}

	/* 공지사항 삭제 처리 */
	@PostMapping("/delete")
	public String deleteNotice(@RequestParam("notice_seq") int notice_seq) {
		noticeService.deleteNotice(notice_seq);
		return "redirect:/notice/list";
	}
}
