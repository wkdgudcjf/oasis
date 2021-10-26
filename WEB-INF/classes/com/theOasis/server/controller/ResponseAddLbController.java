package com.theOasis.server.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.theOasis.controller.GroupController;
import com.theOasis.controller.LanguageBuddyController;
import com.theOasis.member.User;
import com.theOasis.member.Userable;

@Controller
public class ResponseAddLbController {

	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView onResponse(HttpServletRequest request, HttpSession session) {
		ModelAndView modelAndView = new ModelAndView();
		if(session.getAttribute("loginUser")==null)
		{
			modelAndView.setViewName("redirect:login.html");
			return modelAndView;
		}
	
		String lbId = request.getParameter("lb");
		String meId = ((Userable)session.getAttribute("loginUser")).getId();
		if(request.getParameter("accept")==null){
			LanguageBuddyController.getInstance().response(false, meId, lbId);
		}
		else{
			LanguageBuddyController.getInstance().response(true, meId, lbId);
		}
		modelAndView.setViewName("redirect:languageBuddy.html");
		return modelAndView;
	
	}

}
