package com.georgiimalyshev.storestudy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RootController {
	@GetMapping("/")
	public String index(Model model) {
		model.addAttribute("message", "Hello");
		return "hello";
	}
}
