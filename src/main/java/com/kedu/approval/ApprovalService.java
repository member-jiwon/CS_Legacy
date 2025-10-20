package com.kedu.approval;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/*
	전자결재 기능 관련 service
*/

@Service
public class ApprovalService {
	@Autowired
	private ApprovalDAO dao;
}
