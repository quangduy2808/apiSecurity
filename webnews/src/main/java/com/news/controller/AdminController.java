package com.news.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller("adminController")
public class AdminController {
	@GetMapping("/admin")
	public String home() {
		return "admin/index";
	}
}
