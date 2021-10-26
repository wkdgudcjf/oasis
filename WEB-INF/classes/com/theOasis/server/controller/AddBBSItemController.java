package com.theOasis.server.controller;

import java.util.GregorianCalendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.theOasis.controller.BBSController;
import com.theOasis.text.bbs.BBSItem;

@Controller
public class AddBBSItemController {
	
	@ModelAttribute
	public BBSItem setUpForm()
	{
		return new BBSItem();
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView onSubmit(HttpSession session, HttpServletRequest request, BBSItem item)
	{
		ModelAndView modelAndView = new ModelAndView();
		if(session.getAttribute("loginUser")==null)
		{
			modelAndView.setViewName("redirect:login.html");
			return modelAndView;
		}
		
		item.setTime(new GregorianCalendar());
		item.setWriter(request.getParameter("writer"));
		item.setPostTarget(request.getParameter("owner"));
		item.setContent(request.getParameter("content"));
		
		BBSController.getInstance().getManager().register(request.getParameter("owner"), item);
		modelAndView.setViewName("redirect:personal.html?id="+request.getParameter("owner"));
		return modelAndView;
	}
}
