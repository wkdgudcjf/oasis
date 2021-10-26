package com.theOasis.servlet;

import java.util.*;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.theOasis.member.Userable;

@Controller
public class WebhardSController
{
	@RequestMapping("/webhard.html")
	public ModelAndView webfolder(HttpSession session)
	{
		ModelAndView modelAndview = new ModelAndView();
		if(session.getAttribute("loginUser")==null)
		{
			modelAndview.setViewName("redirect:login.html");
			return modelAndview;
		}
		Userable loginUser = ((Userable)session.getAttribute("loginUser"));
		
		modelAndview.addObject("loginUser",loginUser);
		modelAndview.setViewName("webhard");
		return modelAndview;
	}
}
