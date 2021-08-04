package com.georgiimalyshev.storestudy.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.georgiimalyshev.storestudy.domain.users.Customer;

@Controller
public class HomePageController {
	@GetMapping("/home")
	public String homePage(HttpSession httpSession, Model model) {
		Customer customer = (Customer) httpSession.getAttribute("user");
		model.addAttribute("user", customer);
		return "home";
	}
}
