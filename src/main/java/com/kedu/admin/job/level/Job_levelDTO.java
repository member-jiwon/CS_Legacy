package com.kedu.admin.job.level;

/*
    직급 DTO
*/
public class Job_levelDTO {
	private String level_code; // 직급 코드
	private String level_name; // 직급명
	private String company_code; // 회사 코드

	public Job_levelDTO() {
	}

	public Job_levelDTO(String level_code, String level_name, String company_code) {
		super();
		this.level_code = level_code;
		this.level_name = level_name;
		this.company_code = company_code;
	}

	public String getLevel_code() {
		return level_code;
	}

	public void setLevel_code(String level_code) {
		this.level_code = level_code;
	}

	public String getLevel_name() {
		return level_name;
	}

	public void setLevel_name(String level_name) {
		this.level_name = level_name;
	}

	public String getCompany_code() {
		return company_code;
	}

	public void setCompany_code(String company_code) {
		this.company_code = company_code;
	}
}
