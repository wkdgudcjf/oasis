package com.theOasis.server.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.theOasis.controller.GroupController;
import com.theOasis.member.Group;
import com.theOasis.member.User;
import com.theOasis.member.Userable;

@Controller
public class RemoveGroupController {
	@ModelAttribute
	public Group setUpForm() {
		return new Group();
	}
	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView onSubmit(Group group, HttpServletRequest request, HttpSession session) {
		ModelAndView modelAndView = new ModelAndView();
		if(session.getAttribute("loginUser")==null)
		{
			modelAndView.setViewName("redirect:login.html");
			return modelAndView;
		}
		String id = ((Userable)session.getAttribute("loginUser")).getId();
		String groupName = request.getParameter("groupName");
		GroupController.getInstance().getManager().removeGroup(id, groupName);
		modelAndView.setViewName(request.getParameter("view"));
		return modelAndView;
	}
}
