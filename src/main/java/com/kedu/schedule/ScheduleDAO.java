package com.kedu.schedule;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


@Repository
public class ScheduleDAO {
@Autowired
private SqlSession mybatis;

//스케줄 입력
public int insertPtoSchedule(ScheduleDTO dto) {
	return mybatis.insert("Schedule.insertPtoSchedule", dto);
}


}

