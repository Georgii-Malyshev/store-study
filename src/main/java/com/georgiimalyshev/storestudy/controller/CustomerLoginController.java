package com.georgiimalyshev.storestudy.controller;

import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.georgiimalyshev.storestudy.domain.users.AppUser;
import com.georgiimalyshev.storestudy.domain.users.Customer;
import com.georgiimalyshev.storestudy.service.AppUserService;

@Controller
public class CustomerLoginController {
	public CustomerLoginController(@Autowired AppUserService appUserService) {
		this.appUserService = appUserService;
	}

	private AppUserService appUserService;

	@GetMapping("/login")
	public String loginPage() {
		return "login";
	}

	@PostMapping("/login")
	public String login(HttpSession httpSession,@RequestParam(name = "email") String email,
			@RequestParam(name = "password") String password) {
		String resultString = "login-error"; // TODO redirect to generic error template and pass specific error message instead
		Optional<AppUser> optionalOfAppUser = appUserService.findAppUserByCredentials(email, password);
		if (optionalOfAppUser.isPresent()) {
			Customer customer = (Customer) optionalOfAppUser.get(); // TODO conversion will fail for subtypes other
																	// than Customer
			httpSession.setAttribute("user", customer);
			
			resultString = "home";
		}
		return "redirect:" + resultString;
	}

	@GetMapping("/home")
	public String homePage(HttpSession httpSession, Model model) {
		Customer customer = (Customer) httpSession.getAttribute("user");
		model.addAttribute("user", customer);
		return "home";
	}
}