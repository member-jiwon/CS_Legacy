package com.kedu.notice;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/*
 *  공지사항 관리 Service
 */
@Service
public class NoticeService {

	@Autowired
	private NoticeDAO dao;

	public List<NoticeDTO> getAllNotices() {
		return dao.getAllNotices();
	}

	public NoticeDTO selectNoticeById(int id) {
		return dao.selectNoticeById(id);
	}

	public int increaseViewCount(int notice_seq) {
		return dao.increaseViewCount(notice_seq);
	}

	public int insertNotice(NoticeDTO dto) {
		return dao.insertNotice(dto);
	}

	public int updateNotice(NoticeDTO dto) {
		return dao.updateNotice(dto);
	}

	public int deleteNotice(int id) {
		return dao.deleteNotice(id);
	}

	public int getNoticeCount() {
		return dao.getNoticeCount();
	}
}
