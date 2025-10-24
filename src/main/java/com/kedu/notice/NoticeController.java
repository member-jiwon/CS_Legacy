package com.kedu.notice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@RequestMapping("/notice")
@Controller
public class NoticeController {

	@Autowired
	private NoticeService noticeService;

	//	보드리스트 이동
	@GetMapping("/list")
	public String getNoticeList(Model model) {

		// 1. Model에 실제 콘텐츠 JSP의 경로를 저장합니다.
		// (이 JSP는 테이블 등 순수한 콘텐츠 내용만 가집니다.)
		model.addAttribute("contentPage", "board/boardList");
		// 2. 레이아웃 템플릿 JSP의 경로를 반환합니다.
		// (이 JSP가 TopBar, Sidebar, Overlay 등의 구조를 포함합니다.)
		return "layout/main-template";
	}

	// JSON 데이터 제공용 API 메서드는 그대로 유지
	@GetMapping("/api/list")
	@ResponseBody
	public List<NoticeDTO> getNoticeList() {
		return noticeService.getAllNotices();
	}

}