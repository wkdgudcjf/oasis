package com.theOasis.server.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LogoutFormController {
	@RequestMapping(method = RequestMethod.GET)
	public String toLogoutView(HttpSession session) {
		session.removeAttribute("loginUser");
		return "redirect:login.html";
	}
}
