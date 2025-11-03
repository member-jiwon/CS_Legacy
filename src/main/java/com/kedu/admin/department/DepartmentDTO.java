package com.kedu.admin.department;

import java.util.Date;

public class DepartmentDTO {
	private String dept_code; // 부서 코드
	private String dept_name; // 부서 이름
	private String company_code; // 회사 초대코드

	// 추가 필드
	private int employeeCount; // 부서 직원 수
	private Date create_date; // 부서 생성일

	public DepartmentDTO() {
	}

	public DepartmentDTO(String dept_code, String dept_name, String company_code, int employeeCount, Date create_date) {
		this.dept_code = dept_code;
		this.dept_name = dept_name;
		this.company_code = company_code;
		this.employeeCount = employeeCount;
		this.create_date = create_date;
	}

	// getter & setter
	public String getDept_code() {
		return dept_code;
	}

	public void setDept_code(String dept_code) {
		this.dept_code = dept_code;
	}

	public String getDept_name() {
		return dept_name;
	}

	public void setDept_name(String dept_name) {
		this.dept_name = dept_name;
	}

	public String getCompany_code() {
		return company_code;
	}

	public void setCompany_code(String company_code) {
		this.company_code = company_code;
	}

	public int getEmployeeCount() {
		return employeeCount;
	}

	public void setEmployeeCount(int employeeCount) {
		this.employeeCount = employeeCount;
	}

	public Date getCreate_date() {
		return create_date;
	}

	public void setCreate_date(Date create_date) {
		this.create_date = create_date;
	}
}
