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
import com.georgiimalyshev.storestudy.util.ErrorMessages;

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
	public String login(HttpSession httpSession, @RequestParam(name = "email") String email,
			@RequestParam(name = "password") String password, Model model) {
		String resultString = "error";
		Optional<AppUser> optionalOfAppUser = appUserService.findAppUserByCredentials(email, password);
		if (optionalOfAppUser.isPresent()) {
			try {
				Customer customer = (Customer) optionalOfAppUser.get();
				httpSession.setAttribute("user", customer);
				resultString = "redirect:home";
			} catch (ClassCastException ex) {
				model.addAttribute("errorMessage", ErrorMessages.notACustomerErrorMessage);
			}
		} else {
			model.addAttribute("errorMessage", ErrorMessages.userNotFoundByCredentials);
		}
		return resultString;
	}
}