package com.kedu.admin.department;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/*
	부서 관리 관련 등록, 수정, 삭제 기능 서비스
*/

@Service
public class DepartmentService {
	@Autowired
	private DepartmentDAO dao;

	public List<DepartmentDTO> getAllDept() {
		return dao.getAllDept();
	}

	public List<DepartmentDTO> getDepartmentsWithEmployeeCount() {
		return dao.selectDepartmentsEmployee();
	}

	public int insertDepartment(String dept_name, String company_code) {
		return dao.insertDepartment(dept_name, company_code);
	}

	public int deleteDepartment(int dept_code) {
		return dao.deleteDepartment(dept_code);
	}

	// 존재하는 부서코드 조회
	public List<DepartmentDTO> getAllDeptCode(String company_code) {
		return dao.getAllDeptCode(company_code);
	}

}
