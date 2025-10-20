package com.kedu.admin.department;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/*
  	부서 설정 등 관련 기능 구현 DAO
*/

@Repository
public class DepartmentDAO {
	@Autowired
	private SqlSessionTemplate mybatis;
}
