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

	// 부서 리스트
	public List<DepartmentDTO> getAllDept(){
		return mybatis.selectList("Dept.getAllDept");
	}
	
	// 부서별 직원 수 조회
	public List<DepartmentDTO> selectDepartmentsEmployee(){
		return mybatis.selectList("Dept.selectDepartmentsEmployee");
	}
	
	// 부서 추가
	public int insertDepartment(String dept_name, String company_code) {
		return mybatis.insert("Dept.insertDepartment", dept_name);
	}
	
	// 부서 삭제
	public int deleteDepartment(int dept_code) {
		return mybatis.delete("Dept.deleteDepartment", dept_code);
	}
	
	
	//존재하는 모든 부서코드 리스트로 뽑아오기
	public List<DepartmentDTO> getAllDeptCode(String company_code){
		return mybatis.selectList("Department.getAllDeptCode",company_code);
	}

}
