package com.kedu.admin.department;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/*
		부서 설정 등 관련 기능 구현 service
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

	// 부서 중복 확인
//	public boolean isDepartmentNameDuplicate(String dept_name, String company_code) {
//		// DAO를 호출하여 해당 부서명의 개수를 세는 로직
//		return dao.countDepartmentByName(dept_name, company_code) > 0;
//	}
}
