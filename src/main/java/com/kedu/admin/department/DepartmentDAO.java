package com.kedu.admin.department;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/*
  	遺��꽌 �꽕�젙 �벑 愿��젴 湲곕뒫 援ы쁽 DAO
*/

@Repository
public class DepartmentDAO {
	@Autowired
	private SqlSessionTemplate mybatis;
	
	
	//존재하는 모든 부서코드 리스트로 뽑아오기
	public List<DepartmentDTO> getAllDeptCode(String company_code){
		return mybatis.selectList("Department.getAllDeptCode",company_code);
	}
}
