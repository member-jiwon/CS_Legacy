package admin.company;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/*
  		 회사 정보 이용 기능 구현 service
*/

@Service
public class CompanyService {
	@Autowired
	private CompanyDAO dao;
}
