package com.kedu.admin.department;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/*
		遺��꽌 �꽕�젙 �벑 愿��젴 湲곕뒫 援ы쁽 service
*/

@Service
public class DepartmentService {
	@Autowired
	private DepartmentDAO dao;
	
	
	/*존재하는 부서코드 뽑아오기*/
	public List<DepartmentDTO> getAllDeptCode (String company_code){
		return dao.getAllDeptCode(company_code);
	}
}
