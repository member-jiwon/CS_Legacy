package com.kedu.pto_request;

import java.sql.Timestamp;


public class Pto_requestDTO {
private int pto_seq; // 연차 시퀀스
private String member_email; // 멤버이메일
private Timestamp pto_start_at; // 시작시간
private Timestamp pto_end_at; // 끝시간
private int pto_used; // pto사용 "시간" -> 4시간 단위, 이후에 remaining_pto에서는 일자로 계산하기 때문에 /8해서 사용함
private String pto_content; //내용
private String pto_status; // 연차 상황

public Pto_requestDTO() {}
public Pto_requestDTO(int pto_seq, String member_email, Timestamp pto_start_at, Timestamp pto_end_at, int pto_used,
		String pto_content, String pto_status) {
	super();
	this.pto_seq = pto_seq;
	this.member_email = member_email;
	this.pto_start_at = pto_start_at;
	this.pto_end_at = pto_end_at;
	this.pto_used = pto_used;
	this.pto_content = pto_content;
	this.pto_status = pto_status;
}


public int getPto_seq() {
	return pto_seq;
}

public void setPto_seq(int pto_seq) {
	this.pto_seq = pto_seq;
}

public String getMember_email() {
	return member_email;
}

public void setMember_email(String member_email) {
	this.member_email = member_email;
}

public Timestamp getPto_start_at() {
	return pto_start_at;
}

public void setPto_start_at(Timestamp pto_start_at) {
	this.pto_start_at = pto_start_at;
}

public Timestamp getPto_end_at() {
	return pto_end_at;
}

public void setPto_end_at(Timestamp pto_end_at) {
	this.pto_end_at = pto_end_at;
}

public int getPto_used() {
	return pto_used;
}

public void setPto_used(int pto_used) {
	this.pto_used = pto_used;
}

public String getPto_content() {
	return pto_content;
}

public void setPto_content(String pto_content) {
	this.pto_content = pto_content;
}

public String getPto_status() {
	return pto_status;
}

public void setPto_status(String pto_status) {
	this.pto_status = pto_status;
}


}
