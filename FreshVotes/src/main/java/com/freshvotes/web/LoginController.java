package com.freshvotes.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {
	
	@GetMapping("/customlogin")
	public String login() {
		return "customlogin";
	}
}
