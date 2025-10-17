package admin.job_level;


/*
		직급 DTO
*/
public class Job_levelDTO {
	private int level_seq; // 직급 고유번호
	private String level_name; // 직급명
	private String company_code; // 회사 고유 코드
	
	public Job_levelDTO() {}
	public Job_levelDTO(int level_seq, String level_name, String company_code) {
		super();
		this.level_seq = level_seq;
		this.level_name = level_name;
		this.company_code = company_code;
	}
	public int getLevel_seq() {
		return level_seq;
	}
	public void setLevel_seq(int level_seq) {
		this.level_seq = level_seq;
	}
	public String getLevel_name() {
		return level_name;
	}
	public void setLevel_name(String level_name) {
		this.level_name = level_name;
	}
	public String getCompany_code() {
		return company_code;
	}
	public void setCompany_code(String company_code) {
		this.company_code = company_code;
	}
	
	
}
