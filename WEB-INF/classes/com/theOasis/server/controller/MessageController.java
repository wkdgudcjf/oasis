package com.theOasis.server.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.theOasis.member.Member;

@Controller
public class MessageController {
	
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView get(HttpSession session)
	{			
		ModelAndView modelAndView = new ModelAndView();
		if(session.getAttribute("loginUser")==null)
			modelAndView.setViewName("redirect:login.html");
		else
			modelAndView.setViewName("message");
		return modelAndView;
	}
	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView post(HttpServletRequest request, HttpSession session)
	{
		//System.out.println("post");
		ModelAndView modelAndView = new ModelAndView();
		if(session.getAttribute("loginUser")==null)
			modelAndView.setViewName("redirect:login.html");
		else{
			com.theOasis.controller.MessageController.getInstance().getManager().send(request.getParameter("friendId"), ((Member)session.getAttribute("loginUser")).getId(), request.getParameter("content"));
			modelAndView.setViewName(request.getParameter("view"));
		}
		
		return modelAndView;
	}
}
