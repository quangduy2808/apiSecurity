package com.news.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller("userController")
public class UserController {
	@GetMapping("/user")
	public String home() {
		return "user/index";
	}
}
