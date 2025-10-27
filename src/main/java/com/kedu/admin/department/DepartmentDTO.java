package com.kedu.admin.department;

/*
  	遺��꽌 DTO
*/

public class DepartmentDTO {
	private String dept_code; // 遺��꽌 怨좎쑀踰덊샇
	private String dept_name; // 遺��꽌�씠由�
	private String company_code; // �쉶�궗 怨좎쑀 肄붾뱶
	
	public DepartmentDTO() {}
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

	
	
}
