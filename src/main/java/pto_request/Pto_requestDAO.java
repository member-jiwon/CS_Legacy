package pto_request;
/*
 * 		연차 신청 기능 관련 DAO
 * */

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class Pto_requestDAO {
	@Autowired
	private SqlSession mybatis;
}
