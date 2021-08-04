package com.georgiimalyshev.storestudy.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LogoutController {
	@GetMapping("/logout")
	public String logout(HttpServletRequest httpServletRequest, Model model) {
		HttpSession httpSession = httpServletRequest.getSession(false);
		if (httpSession != null) {
			httpSession.removeAttribute("user");
			httpSession.invalidate();
		} else {
			Logger logger = LogManager.getLogger(LogoutController.class);
			logger.warn("logout method called without a session");
		}
		return "redirect:logout-success";
	}
}