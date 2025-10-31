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

	// 공지 조회
	public List<NoticeDTO> getAllNotices() {
		return mybatis.selectList("Notice.getAllNotices");
	}

	// 특정 공지 조회
	public NoticeDTO selectNoticeById(int id) {
		return mybatis.selectOne("Notice.selectNoticeById", id);
	}
	
	// 조회수 증가
	public int increaseViewCount(int notice_seq) {
		return mybatis.update("Notice.increaseViewCount", notice_seq);
	};
	
	// 공지 사항 추가
	public int insertNotice(NoticeDTO dto) {
		return mybatis.insert("Notice.insertNotice", dto);
	}

	// 공지 수정
	public int updateNotice(NoticeDTO dto) {
		return mybatis.update("Notice.updateNotice", dto);
	}

	// 공지 삭제
	public int deleteNotice(int id) {
		return mybatis.delete("Notice.deleteNotice", id);
	}

	// 페이지 갯수
	public int getNoticeCount() {
		return mybatis.selectOne("Notice.getNoticeCount");
	}

}