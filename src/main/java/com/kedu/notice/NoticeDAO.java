package com.kedu.notice;

import java.util.List;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/*
 *  공지사항 관리 DAO
 */
@Repository
public class NoticeDAO {

	@Autowired
	private SqlSession mybatis;

	public List<NoticeDTO> getAllNotices() {
		return mybatis.selectList("Notice.getAllNotices");
	}

	public NoticeDTO selectNoticeById(int id) {
		return mybatis.selectOne("Notice.selectNoticeById", id);
	}

	public int increaseViewCount(int notice_seq) {
		return mybatis.update("Notice.increaseViewCount", notice_seq);
	}

	public int insertNotice(NoticeDTO dto) {
		return mybatis.insert("Notice.insertNotice", dto);
	}

	public int updateNotice(NoticeDTO dto) {
		return mybatis.update("Notice.updateNotice", dto);
	}

	public int deleteNotice(int id) {
		return mybatis.delete("Notice.deleteNotice", id);
	}

	public int getNoticeCount() {
		return mybatis.selectOne("Notice.getNoticeCount");
	}
}
