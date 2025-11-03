package com.kedu.pto_request;

import java.sql.Timestamp;

/*
	�뿰李� �떊泥��슜 DTO	
*/
public class Pto_requestDTO {
private int pto_seq; // �뿰李� 怨좎쑀踰덊샇
private String member_email; // �궗�썝 怨좎쑀 �븘�씠�뵒
private Timestamp pto_start_at; // �뿰李� �떆�옉 �궇吏� 
private Timestamp pto_end_at; // �뿰李� 醫낅즺 �궇吏�
private int pto_used; // �뿰李� 珥� �냼紐� �씪�닔 
private String pto_content; // �뿰李� �궗�쑀
private String all_day; // 醫낆씪 �뿬遺� (default: y, �떒�씪: n)
private String pto_status; // �뿰李� �듅�씤 �긽�깭 ( default: w(��湲�), 諛섎젮: n, �듅�씤: y )

public Pto_requestDTO() {}
public Pto_requestDTO(int pto_seq, String member_email, Timestamp pto_start_at, Timestamp pto_end_at, int pto_used,
		String pto_content, String all_day, String pto_status) {
	super();
	this.pto_seq = pto_seq;
	this.member_email = member_email;
	this.pto_start_at = pto_start_at;
	this.pto_end_at = pto_end_at;
	this.pto_used = pto_used;
	this.pto_content = pto_content;
	this.all_day = all_day;
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
public String getAll_day() {
	return all_day;
}
public void setAll_day(String all_day) {
	this.all_day = all_day;
}
public String getPto_status() {
	return pto_status;
}
public void setPto_status(String pto_status) {
	this.pto_status = pto_status;
}



}
