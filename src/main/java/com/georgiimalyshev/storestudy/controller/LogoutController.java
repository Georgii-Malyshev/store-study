package com.georgiimalyshev.storestudy.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LogoutController {
	@GetMapping("/logout")
	public String logout(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Model model) {
		HttpSession httpSession = httpServletRequest.getSession(false);
		if (httpSession != null) {
			httpSession.removeAttribute("user");
			httpSession.invalidate();
			// delete all cookies
			for (Cookie cookie : httpServletRequest.getCookies()) {
                String cookieName = cookie.getName();
                Cookie cookieToDelete = new Cookie(cookieName, null);
                cookieToDelete.setMaxAge(0);
                httpServletResponse.addCookie(cookieToDelete);
			}
		} else {
			Logger logger = LogManager.getLogger(LogoutController.class);
			logger.warn("logout method called without a session");
		}
		return "logout-success";
	}
}