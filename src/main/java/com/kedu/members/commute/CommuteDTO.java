package com.kedu.members.commute;

import java.sql.Timestamp;
import java.time.LocalDate;

/*
 	���� ������ DTO
 
 */

public class CommuteDTO {
	private String member_email; // ��� ���� ���̵�
	private String lateness; // �������� ( default: n , y )
	private String leave_early; // ���𿩺� ( default: n , y )
	private String absence; // ��ٿ��� ( default : ���, ����, ��� )
	private Timestamp work_at; // ��ٽð�
	private Timestamp leave_at; // ��ٽð�
	private LocalDate  commute_at; // ���� ��¥ ( default : sysdate )
	
	public CommuteDTO () {}
	public CommuteDTO(String member_email, String lateness, String leave_early, String absence, Timestamp work_at,
			Timestamp leave_at, LocalDate commute_at) {
		super();
		this.member_email = member_email;
		this.lateness = lateness;
		this.leave_early = leave_early;
		this.absence = absence;
		this.work_at = work_at;
		this.leave_at = leave_at;
		this.commute_at = commute_at;
	}
	public String getMember_email() {
		return member_email;
	}
	public void setMember_email(String member_email) {
		this.member_email = member_email;
	}
	public String getLateness() {
		return lateness;
	}
	public void setLateness(String lateness) {
		this.lateness = lateness;
	}
	public String getLeave_early() {
		return leave_early;
	}
	public void setLeave_early(String leave_early) {
		this.leave_early = leave_early;
	}
	public String getAbsence() {
		return absence;
	}
	public void setAbsence(String absence) {
		this.absence = absence;
	}
	public Timestamp getWork_at() {
		return work_at;
	}
	public void setWork_at(Timestamp work_at) {
		this.work_at = work_at;
	}
	public Timestamp getLeave_at() {
		return leave_at;
	}
	public void setLeave_at(Timestamp leave_at) {
		this.leave_at = leave_at;
	}
	public LocalDate getCommute_at() {
		return commute_at;
	}
	public void setCommute_at(LocalDate commute_at) {
		this.commute_at = commute_at;
	}
	
	
}
