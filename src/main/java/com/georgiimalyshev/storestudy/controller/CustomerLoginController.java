package com.georgiimalyshev.storestudy.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.georgiimalyshev.storestudy.service.AppUserService;
import com.georgiimalyshev.storestudy.service.domain.users.AppUser;
import com.georgiimalyshev.storestudy.service.domain.users.Customer;

@Controller
@SessionAttributes("user") // TODO implement correct handling of sessions
public class CustomerLoginController {
	@Autowired // TODO consider alternatives to field injection
	private AppUserService appUserService;

	@ModelAttribute
	public Customer user() {
		return new Customer();
	}

	@GetMapping("/login")
	public String loginPage() {
		return "login";
	}

	@PostMapping("/login")
	public String login(Model model, @RequestParam(name = "email") String email,
			@RequestParam(name = "password") String password) {
		Optional<AppUser> optionalOfAppUser = appUserService.findAppUserByCredentials(email, password);
		if (optionalOfAppUser.isPresent()) {
			Customer customer = (Customer) optionalOfAppUser.get(); // TODO conversion will fail for subtypes other
																	// than Customer
			model.addAttribute("user", customer);
		} else {
			// TODO handle "wrong credentials" scenario correctly
			// maybe create a method-scoped String variable, set it to either "home" or
			// "login-error" and redirect to it?
		}

		return "redirect:" + "home";
	}

	@GetMapping("/home")
	public String homePage() {
		return "home";
	}
}