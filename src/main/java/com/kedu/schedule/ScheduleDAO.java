package com.kedu.schedule;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/*
 * 일정 (스케줄 관리) 관련 DAO
 */
@Repository
public class ScheduleDAO {
	@Autowired
	private SqlSession mybatis;
}
