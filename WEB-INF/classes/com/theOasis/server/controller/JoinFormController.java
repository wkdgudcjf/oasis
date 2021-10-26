package com.theOasis.server.controller;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import com.theOasis.controller.MemberController;
import com.theOasis.member.MemberInfo;
import com.theOasis.member.TheOasisMember;
import com.theOasis.member.User;
import com.theOasis.validator.*;

@Controller
public class JoinFormController {
	//private JoinValidator joinValidator;
	/*
	public void setUserValidatr(JoinValidator joinValidator){
		this.joinValidator = joinValidator;
	}*/

	@ModelAttribute("member")
	public TheOasisMember setUpForm(){
		TheOasisMember member = new TheOasisMember();
		return member;
	}
	@RequestMapping(method=RequestMethod.GET)
	public String toLoginView() {
		return "login";
	}
	@RequestMapping(method=RequestMethod.POST)
	public ModelAndView onSubmit(@ModelAttribute("member") TheOasisMember member, BindingResult bindingResult) throws Exception{
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("ddddd");
		return modelAndView;
	}

}
