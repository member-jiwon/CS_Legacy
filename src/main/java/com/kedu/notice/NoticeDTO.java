package com.kedu.notice;

import java.sql.Timestamp;
import java.util.List;

import com.kedu.file.FileDTO;

/*
	공지사항 DTO
*/
public class NoticeDTO {
	private int notice_seq; // 공지사항 고유번호
	private int view_count; // 조회수 ( default : 0 )
	private String writer_email;
	private String title; // 공지 제목
	private String content; // 공지 내용
	private String company_code; // 회사 코드
	private Timestamp created_at; // 공지 작성일 ( default : sysdate )

    // 파일 목록 추가 - 지원언니꺼 한거 보고 파일 목록 수정예정 우선....ai가 이렇게 하래 오류오져셔
    private List<FileDTO> files;

    public List<FileDTO> getFiles() {
        return files;
    }

    public void setFiles(List<FileDTO> files) {
        this.files = files;
    }
	
	public NoticeDTO() {
	}

	public NoticeDTO(int notice_seq, int view_count, String title, String content, String company_code,
			String writer_email, Timestamp created_at) {
		super();
		this.notice_seq = notice_seq;
		this.view_count = view_count;
		this.title = title;
		this.content = content;
		this.company_code = company_code;
		this.writer_email = writer_email;
		this.created_at = created_at;
	}

	public int getNotice_seq() {
		return notice_seq;
	}

	public void setNotice_seq(int notice_seq) {
		this.notice_seq = notice_seq;
	}

	public int getView_count() {
		return view_count;
	}

	public void setView_count(int view_count) {
		this.view_count = view_count;
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

	public String getCompany_code() {
		return company_code;
	}

	public void setCompany_code(String company_code) {
		this.company_code = company_code;
	}

	public String getWriter_email() {
		return writer_email;
	}

	public void setWriter_email(String writer_email) {
		this.writer_email = writer_email;
	}

	public Timestamp getCreated_at() {
		return created_at;
	}

	public void setCreated_at(Timestamp created_at) {
		this.created_at = created_at;
	}

}