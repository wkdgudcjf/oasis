package com.theOasis.server.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.theOasis.controller.LanguageBuddyController;
import com.theOasis.member.*;

import com.theOasis.member.languageBuddy.LanguageBuddyProfile;

@Controller
public class ModifyLbProfileController {
	@ModelAttribute
	public LanguageBuddyProfile setUpForm(){
		return new LanguageBuddyProfile();
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView onSubmit(LanguageBuddyProfile profile, HttpSession session) {
		ModelAndView modelAndView = new ModelAndView();
		String id = ((Userable)session.getAttribute("loginUser")).getId();
		LanguageBuddyController.getInstance().getManager().getLanguageBuddies().get(id).setProfile(profile);
		//System.out.println(LanguageBuddyController.getInstance().getManager().getLanguageBuddies().get(id).getProfile().getMotherTongue());
		modelAndView.setViewName("redirect:languageBuddy.html");
		return modelAndView;
	}
}
