package com.kedu.notice;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/*
 * 		공지사항 관련 기능 DAO
 * */

@Repository
public class NoticeDAO {
	@Autowired
	private SqlSession mybatis;

	public List<NoticeDTO> getAllNotices() {
		return mybatis.selectList("Notice.getAllNotices");
	}
}