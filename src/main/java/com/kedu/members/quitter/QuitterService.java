package com.kedu.members.quitter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/*
	퇴사 처리 등 기능 구현 service
*/

@Service
public class QuitterService {
	@Autowired
	private QuitterDAO dao;
}
