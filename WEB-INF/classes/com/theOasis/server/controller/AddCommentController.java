package com.theOasis.server.controller;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.theOasis.controller.BBSController;
import com.theOasis.member.Userable;
import com.theOasis.text.bbs.Comment;

@Controller
public class AddCommentController {
	@ModelAttribute
	public Comment setUpForm(){
		return new Comment();
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView onSubmit(HttpServletRequest request, HttpSession session, Comment comment)
	{
		ModelAndView modelAndView = new ModelAndView();
		if(session.getAttribute("loginUser")==null)
		{
			modelAndView.setViewName("redirect:login.html");
			return modelAndView;
		}

		try {
			request.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		comment.setWriter(((Userable)session.getAttribute("loginUser")).getId());		
		BBSController.getInstance().getManager().registerComment(request.getParameter("id"), Integer.parseInt(request.getParameter("bbsNo")), comment);
		modelAndView.setViewName(request.getParameter("view"));
		return modelAndView;
	}
	
}
