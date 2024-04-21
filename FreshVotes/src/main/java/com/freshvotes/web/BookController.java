package com.freshvotes.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BookController {


	
	@GetMapping("library/books")
	public String addBook(){
		return "library/books";
	}
}
