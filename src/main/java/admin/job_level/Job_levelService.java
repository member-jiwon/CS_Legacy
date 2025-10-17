package admin.job_level;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

/*
	 직급 관련 기능 구현 service
 */
@Service
public class Job_levelService {
	@Autowired
	private Job_levelDAO dao;
}
