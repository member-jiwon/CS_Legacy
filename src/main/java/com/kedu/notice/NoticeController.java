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
	public String boardList() {
		return "board/boardList";
	}
//
//	// JSON 데이터 제공용 API 메서드는 그대로 유지
//	@GetMapping("/api/list")
//	@ResponseBody
//	public List<NoticeDTO> getNoticeList() {
//		return noticeService.getAllNotices();
//	}


}