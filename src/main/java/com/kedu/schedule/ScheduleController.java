package com.kedu.schedule;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/*
 * 일정 (스케줄 관리) 관련 컨트롤러
 */

@RequestMapping("")
@RestController
public class ScheduleController {
	@Autowired
	private ScheduleService scheduleService;

	// TODO: 회원별 일정 조회 및 관리 기능 구현
}
