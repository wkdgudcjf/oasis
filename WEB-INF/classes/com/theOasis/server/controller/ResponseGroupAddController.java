package com.theOasis.server.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.theOasis.controller.GroupController;
import com.theOasis.member.User;
import com.theOasis.member.Userable;

@Controller
public class ResponseGroupAddController {
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
		String receiver = request.getParameter("receiver");
		String groupName = request.getParameter("groupName");
		String meId = ((Userable)session.getAttribute("loginUser")).getId();
		GroupController.getInstance().responseGroup(meId, receiver, groupName, true);
		modelAndView.setViewName("redirect:searchFriend.html");
		return modelAndView;
	
	}
}
