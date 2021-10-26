package com.theOasis.server.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.theOasis.controller.BBSController;


@Controller
public class DeleteBBSItemController {

	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView onSubmit(HttpSession session, HttpServletRequest request)
	{
		ModelAndView modelAndView = new ModelAndView();
		if(session.getAttribute("loginUser")==null)
		{
			modelAndView.setViewName("redirect:login.html");
			return modelAndView;
		}
		
		BBSController.getInstance().getManager().remove(request.getParameter("owner"), Integer.parseInt(request.getParameter("bbsNo")));
		modelAndView.setViewName("redirect:personal.html?id="+request.getParameter("owner"));
		return modelAndView;
	}
}
