package com.kedu.pto_request;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/*
 * 		연차 신청 기능 관련 service
 * */

@Service
public class Pto_requestService {
	@Autowired
	private Pto_requestDAO dao;
}