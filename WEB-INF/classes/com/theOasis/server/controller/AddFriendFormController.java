package com.theOasis.server.controller;

import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.theOasis.controller.GroupController;
import com.theOasis.controller.MemberController;
import com.theOasis.member.MemberInfo;
import com.theOasis.member.TheOasisMember;
import com.theOasis.member.User;
import com.theOasis.member.Userable;

@Controller
public class AddFriendFormController {
	
	@ModelAttribute
	public User setUpForm() {
		return new User();
	}
	
	
	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView onSubmit(User member, HttpServletRequest request, HttpSession session) {
		ModelAndView modelAndView = new ModelAndView();
		if(session.getAttribute("loginUser")==null)
		{
			modelAndView.setViewName("redirect:login.html");
			return modelAndView;
		}
		String loginUser = ((Userable)session.getAttribute("loginUser")).getId();
		String id = request.getParameter("memberId");
		modelAndView.setViewName(request.getParameter("view"));
		GroupController.getInstance().requestFriend(loginUser, id);
		return modelAndView;
	}
}
