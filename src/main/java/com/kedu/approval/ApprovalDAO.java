package com.kedu.approval;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/*
  	전자결재 기능 관련 DAO
*/
@Repository
public class ApprovalDAO {
	@Autowired
	private SqlSessionTemplate mybatis;
}
