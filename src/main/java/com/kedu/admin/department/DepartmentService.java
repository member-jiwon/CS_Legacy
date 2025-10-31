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
	
	public List<DepartmentDTO> getAllDept(){
		return dao.getAllDept();
	}
}
