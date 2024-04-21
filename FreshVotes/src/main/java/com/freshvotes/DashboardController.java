package com.freshvotes;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardController {

	@GetMapping("/")
	public String rootView() {
		return "index";
	}
	
	@GetMapping("/login")
	String login() {
		return "login";
	}
}
