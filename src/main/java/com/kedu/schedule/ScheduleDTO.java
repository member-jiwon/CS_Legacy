package com.kedu.schedule;

import java.security.Timestamp;

/*
 * 일정 (스케줄 관리) DTO
 */
public class ScheduleDTO {
	private int schedule_seq; // 일정 시퀀스 번호
	private String member_email; // 일정 작성자 이메일 (회원 이메일)
	private String title; // 일정 제목
	private String content; // 일정 내용
	private String all_day; // 종일 여부 (default: y, 부분일 경우: n)
	private String color; // 색상
	private Timestamp start_at; // 시작 날짜
	private Timestamp end_at; // 종료 날짜
	private Timestamp created_at; // 일정 생성일 (default: sysdate)

	public ScheduleDTO() {
	}

	public ScheduleDTO(int schedule_seq, String member_email, String title, String content, String all_day,
			String color, Timestamp start_at, Timestamp end_at, Timestamp created_at) {
		super();
		this.schedule_seq = schedule_seq;
		this.member_email = member_email;
		this.title = title;
		this.content = content;
		this.all_day = all_day;
		this.color = color;
		this.start_at = start_at;
		this.end_at = end_at;
		this.created_at = created_at;
	}

	// getters & setters
	public int getSchedule_seq() {
		return schedule_seq;
	}

	public void setSchedule_seq(int schedule_seq) {
		this.schedule_seq = schedule_seq;
	}

	public String getMember_email() {
		return member_email;
	}

	public void setMember_email(String member_email) {
		this.member_email = member_email;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getAll_day() {
		return all_day;
	}

	public void setAll_day(String all_day) {
		this.all_day = all_day;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public Timestamp getStart_at() {
		return start_at;
	}

	public void setStart_at(Timestamp start_at) {
		this.start_at = start_at;
	}

	public Timestamp getEnd_at() {
		return end_at;
	}

	public void setEnd_at(Timestamp end_at) {
		this.end_at = end_at;
	}

	public Timestamp getCreated_at() {
		return created_at;
	}

	public void setCreated_at(Timestamp created_at) {
		this.created_at = created_at;
	}
}
