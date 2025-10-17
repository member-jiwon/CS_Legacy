package admin.company;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/*
  		 회사 정보 이용 기능 구현 DAO
*/

@Repository
public class CompanyDAO {
	@Autowired
	private SqlSessionTemplate mybatis;
}
