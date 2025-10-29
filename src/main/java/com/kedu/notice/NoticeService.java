package com.kedu.notice;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/*
 * 		공지사항 관련 기능 service
 * */
@Service
public class NoticeService {
	@Autowired
	private NoticeDAO dao;
	
	public List<NoticeDTO> getAllNotices(){
		return dao.getAllNotices();
	}
}
