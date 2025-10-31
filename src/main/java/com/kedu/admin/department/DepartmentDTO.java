package com.kedu.admin.department;

/*
  	부서 DTO
*/

public class DepartmentDTO {
	private String dept_code; // 부서 고유번호
	private String dept_name; // 부서이름
	private String company_code; // 회사 고유 코드

	public DepartmentDTO() {
	}

	public DepartmentDTO(String dept_code, String dept_name, String company_code) {
		super();
		this.dept_code = dept_code;
		this.dept_name = dept_name;
		this.company_code = company_code;
	}

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

//	public DepartmentDTO(int dept_code, String dept_name, String company_code) {
//		super();
//		this.dept_code = dept_code;
//		this.dept_name = dept_name;
//		this.company_code = company_code;
//	}
//
//	public int getDept_code() {
//		return dept_code;
//	}
//
//	public void setDept_code(int dept_code) {
//		this.dept_code = dept_code;
//	}
//
//	public String getDept_name() {
//		return dept_name;
//	}
//
//	public void setDept_name(String dept_name) {
//		this.dept_name = dept_name;
//	}
//
//	public String getCompany_code() {
//		return company_code;
//	}
//
//	public void setCompany_code(String company_code) {
//		this.company_code = company_code;
//	}

}
