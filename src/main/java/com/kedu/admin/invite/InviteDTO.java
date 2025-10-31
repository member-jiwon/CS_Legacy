package com.kedu.admin.invite;

import java.sql.Timestamp;

public class InviteDTO {
	private Long invite_seq; // 초대 고유번호
	private String email; // 사원 이메일
	private String company_code; // 회사/초대 코드
	private String status; // 초대 상태 (미승인/승인/거절)
	private Timestamp created_at;
	private Timestamp updated_at;

	// member 정보
	private String name;
	private Timestamp signup_at;
	private String dept_code;
	private String level_code;
	
	public InviteDTO() {}
	
	public InviteDTO(Long invite_seq, String email, String company_code, String status, Timestamp created_at,
			Timestamp updated_at, String name, Timestamp signup_at, String dept_code, String level_code) {
		super();
		this.invite_seq = invite_seq;
		this.email = email;
		this.company_code = company_code;
		this.status = status;
		this.created_at = created_at;
		this.updated_at = updated_at;
		this.name = name;
		this.signup_at = signup_at;
		this.dept_code = dept_code;
		this.level_code = level_code;
	}

	public Long getInvite_seq() {
		return invite_seq;
	}

	public void setInvite_seq(Long invite_seq) {
		this.invite_seq = invite_seq;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCompany_code() {
		return company_code;
	}

	public void setCompany_code(String company_code) {
		this.company_code = company_code;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Timestamp getCreated_at() {
		return created_at;
	}

	public void setCreated_at(Timestamp created_at) {
		this.created_at = created_at;
	}

	public Timestamp getUpdated_at() {
		return updated_at;
	}

	public void setUpdated_at(Timestamp updated_at) {
		this.updated_at = updated_at;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Timestamp getSignup_at() {
		return signup_at;
	}

	public void setSignup_at(Timestamp signup_at) {
		this.signup_at = signup_at;
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
}