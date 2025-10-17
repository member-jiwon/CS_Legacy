package members.quitter;

import java.security.Timestamp;

/*
  	퇴사자 DTO
*/
public class QuitterDTO {
	private String member_email; // 사원 고유 아이디
	private String company_code; // 회사 고유 코드
	private Timestamp quit_at; // 퇴사 날짜 
	
	public QuitterDTO() {}
	public QuitterDTO(String member_email, String company_code, Timestamp quit_at) {
		super();
		this.member_email = member_email;
		this.company_code = company_code;
		this.quit_at = quit_at;
	}
	public String getMember_email() {
		return member_email;
	}
	public void setMember_email(String member_email) {
		this.member_email = member_email;
	}
	public String getCompany_code() {
		return company_code;
	}
	public void setCompany_code(String company_code) {
		this.company_code = company_code;
	}
	public Timestamp getQuit_at() {
		return quit_at;
	}
	public void setQuit_at(Timestamp quit_at) {
		this.quit_at = quit_at;
	}
	
	
}
