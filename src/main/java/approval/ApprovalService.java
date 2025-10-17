package approval;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import admin.company.CompanyDAO;

/*
	전자결재 기능 관련 service
*/

@Service
public class ApprovalService {
	@Autowired
	private CompanyDAO dao;
}
