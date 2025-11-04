package com.kedu.members.member;

import java.sql.Timestamp;

/*
 * 		회원 DTO
 */
public class MemberDTO {
	private String email; // 회원 이메일
	private String dept_code; // 부서 코드 (default: null)
	private String level_code; // 직급 코드 (default: null)
	private String pw; // 비밀번호
	private String name; // 이름
	private String phone; // 전화번호
	private String company_code; // 회사 코드
	private Timestamp signup_at; // 회원 가입 일자
	private String status; // 회원 상태

	public MemberDTO() {
	}

	public MemberDTO(String email, String dept_code, String level_code, String pw, String name, String phone,
			String company_code, Timestamp signup_at, String status) {
		this.email = email;
		this.dept_code = dept_code;
		this.level_code = level_code;
		this.pw = pw;
		this.name = name;
		this.phone = phone;
		this.company_code = company_code;
		this.signup_at = signup_at;
		this.status = status;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDept_code() {
		return dept_code;
	}

	public void setDept_code(String dept_code) {
		this.dept_code = dept_code;
	}

	public String getLevel_code() {
		return level_code;
	}

	public void setLevel_code(String level_code) {
		this.level_code = level_code;
	}

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getCompany_code() {
		return company_code;
	}

	public void setCompany_code(String company_code) {
		this.company_code = company_code;
	}

	public Timestamp getSignup_at() {
		return signup_at;
	}

	public void setSignup_at(Timestamp signup_at) {
		this.signup_at = signup_at;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
