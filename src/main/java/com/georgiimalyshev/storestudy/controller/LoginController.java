package com.georgiimalyshev.storestudy.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.georgiimalyshev.storestudy.service.AppUserService;
import com.georgiimalyshev.storestudy.service.domain.users.AppUser;

@Controller
public class LoginController {
	@Autowired // TODO consider alternatives to field injection
	private AppUserService appUserService;
	private AppUser appUser;
	
	@GetMapping("/login")
	public String loginPage() {
		return "login";
	}

	@PostMapping("/login")
	public ModelAndView login(@RequestParam(value = "email", name = "email", required = false) String email, @RequestParam(value = "password", name = "password", required = false) String password) {
		Optional<AppUser> optionalOfAppUser = appUserService.findAppUserByCredentials(email, password);
		if (optionalOfAppUser.isPresent()) {
			appUser = optionalOfAppUser.get();
		} else {
			// TODO handle "wrong credentials" scenario
		}
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("user", appUser);
		modelAndView.setViewName("home");
		return modelAndView; // TODO consider using Model instead of ModelAndView and returning String
	}
}