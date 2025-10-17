package approval;

import java.security.Timestamp;

/*
  	전자결재 DTO
*/
public class ApprovalDTO {
	private int approval_seq; // 전자 결재 고유번호
	private String member_email; // 사원 고유 아이디 ( 신청인 )
	private String approval_content; // 결재 내용
	private Timestamp approval_at; // 결재 요청 날짜 ( default : sysdate ) 
	private String approval_status; // 결재 승인 상태 ( default : w(대기), 반려: n, 승인: y ) 
	
	public ApprovalDTO() {}
	public ApprovalDTO(int approval_seq, String member_email, String approval_content, Timestamp approval_at,
			String approval_status) {
		super();
		this.approval_seq = approval_seq;
		this.member_email = member_email;
		this.approval_content = approval_content;
		this.approval_at = approval_at;
		this.approval_status = approval_status;
	}
	public int getApproval_seq() {
		return approval_seq;
	}
	public void setApproval_seq(int approval_seq) {
		this.approval_seq = approval_seq;
	}
	public String getMember_email() {
		return member_email;
	}
	public void setMember_email(String member_email) {
		this.member_email = member_email;
	}
	public String getApproval_content() {
		return approval_content;
	}
	public void setApproval_content(String approval_content) {
		this.approval_content = approval_content;
	}
	public Timestamp getApproval_at() {
		return approval_at;
	}
	public void setApproval_at(Timestamp approval_at) {
		this.approval_at = approval_at;
	}
	public String getApproval_status() {
		return approval_status;
	}
	public void setApproval_status(String approval_status) {
		this.approval_status = approval_status;
	}
	
	
}
