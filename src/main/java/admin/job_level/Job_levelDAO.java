package admin.job_level;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/*
	 직급 관련 기능 구현 DAO
 */
@Repository
public class Job_levelDAO {
	@Autowired
	private SqlSessionTemplate mybatis;
}
