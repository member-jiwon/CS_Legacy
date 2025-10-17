package file;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
/*
	 파일 기능 관련 구현 DAO
*/
@Repository
public class FileDAO {
	@Autowired
	private SqlSessionTemplate mybatis;
}
