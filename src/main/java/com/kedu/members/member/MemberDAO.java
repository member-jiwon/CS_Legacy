package com.kedu.members.member;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


@Repository
public class MemberDAO {
	@Autowired
	private SqlSession mybatis;
	
	//이메일로 이름찾기
	public String getNameByEmail(String member_email) {
		return mybatis.selectOne("Member.getNameByEmail",member_email);
	}
	
	
	
}
