package com.kedu.schedule;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RequestMapping("")
@RestController
public class ScheduleController {
	@Autowired
	private ScheduleService ScheduleService;

}
