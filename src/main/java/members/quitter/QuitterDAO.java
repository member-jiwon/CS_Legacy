package members.quitter;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/*
  	퇴사 처리 등 기능 구현 DAO
*/
@Repository
public class QuitterDAO {
	@Autowired
	private SqlSessionTemplate mybatis;
}
