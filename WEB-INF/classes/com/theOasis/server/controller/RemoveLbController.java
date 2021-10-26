package com.theOasis.server.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.theOasis.controller.GroupController;
import com.theOasis.controller.LanguageBuddyController;
import com.theOasis.member.User;
import com.theOasis.member.Userable;

@Controller
public class RemoveLbController {
	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView onSubmit(HttpServletRequest request, HttpSession session) {
		ModelAndView modelAndView = new ModelAndView();
		if(session.getAttribute("loginUser")==null)
		{
			modelAndView.setViewName("redirect:login.html");
			return modelAndView;
		}
		String loginUser = ((Userable)session.getAttribute("loginUser")).getId();
		String lbId = request.getParameter("myLbId");
		LanguageBuddyController.getInstance().getManager().breakLb(loginUser, lbId);
		modelAndView.setViewName(request.getParameter("view"));
		
		return modelAndView;
	}
}
