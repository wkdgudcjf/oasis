package com.theOasis.server.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
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
public class ResponseAddFormController {
	
	@ModelAttribute
	public User setUpForm() {
		return new User();
	}
	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView onSubmit(User user, HttpServletRequest request, HttpSession session) {
		ModelAndView modelAndView = new ModelAndView();
		if(session.getAttribute("loginUser")==null)
		{
			modelAndView.setViewName("redirect:login.html");
			return modelAndView;
		}
		String friendId = request.getParameter("memberId");
		String meId = ((Userable)session.getAttribute("loginUser")).getId();
		if(request.getParameter("accept")==null){
			GroupController.getInstance().responseFriend(meId, friendId, false);
		}
		else{
		GroupController.getInstance().responseFriend(meId, friendId, true);
		}
		modelAndView.setViewName("redirect:searchFriend.html");
		return modelAndView;
	
	}

}
