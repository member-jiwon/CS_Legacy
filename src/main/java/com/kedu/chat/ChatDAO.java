package com.kedu.chat;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ChatDAO {

	@Autowired
	private SqlSession mybatis;
	
	
	// 내가 속한 모든 채팅방 시퀀스 리스트 가져오기
    public List<Integer> getAllChatSeq(String member_email) {
        return mybatis.selectList("Chat.getAllChatSeq", member_email);
    }
	
	
	
}
