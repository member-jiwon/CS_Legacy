package com.kedu.controllers;

import org.springframework.web.bind.annotation.RequestMapping;

public class HomeController {
	
	@RequestMapping(value = "/")
	public String home() {
		return "home";
	}
	
}
