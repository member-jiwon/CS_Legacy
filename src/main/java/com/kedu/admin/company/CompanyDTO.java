package com.kedu.admin.company;

/*
  		회사 정보 DTO 
*/

public class CompanyDTO {
	private int company_seq; // 회사 고유 코드
	private String admin_email; // 관리자 고유 아이디 ( e-mail )
	private String pw; // 비밀번호
	private String company_name; // 회사 이름
	private String company_code; // 회사 공유 코드
	private String work_at; // 출근시간 ( default : 09:00 )
	private String leave_at; // 퇴근시간 ( default : 18:00 )
	private int pto_date; // 연차 기본값 ( default : 15 )
	
	public CompanyDTO() {}
	public CompanyDTO(int company_seq, String admin_email, String pw, String company_name, String company_code,
			String work_at, String leave_at, int pto_date) {
		super();
		this.company_seq = company_seq;
		this.admin_email = admin_email;
		this.pw = pw;
		this.company_name = company_name;
		this.company_code = company_code;
		this.work_at = work_at;
		this.leave_at = leave_at;
		this.pto_date = pto_date;
	}
	public int getCompany_seq() {
		return company_seq;
	}
	public void setCompany_seq(int company_seq) {
		this.company_seq = company_seq;
	}
	public String getAdmin_email() {
		return admin_email;
	}
	public void setAdmin_email(String admin_email) {
		this.admin_email = admin_email;
	}
	public String getPw() {
		return pw;
	}
	public void setPw(String pw) {
		this.pw = pw;
	}
	public String getCompany_name() {
		return company_name;
	}
	public void setCompany_name(String company_name) {
		this.company_name = company_name;
	}
	public String getCompany_code() {
		return company_code;
	}
	public void setCompany_code(String company_code) {
		this.company_code = company_code;
	}
	public String getWork_at() {
		return work_at;
	}
	public void setWork_at(String work_at) {
		this.work_at = work_at;
	}
	public String getLeave_at() {
		return leave_at;
	}
	public void setLeave_at(String leave_at) {
		this.leave_at = leave_at;
	}
	public int getPto_date() {
		return pto_date;
	}
	public void setPto_date(int pto_date) {
		this.pto_date = pto_date;
	}
	
	
}
