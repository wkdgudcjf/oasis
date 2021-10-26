package com.theOasis.server.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.theOasis.controller.BBSController;
import com.theOasis.controller.GroupController;
import com.theOasis.controller.MemberController;
import com.theOasis.member.TheOasisMember;
import com.theOasis.member.Userable;

@Controller
public class FriendPageController {
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView toLoginView(HttpServletRequest request, HttpSession session) {
		ModelAndView modelAndView = new ModelAndView();
		
		if(session.getAttribute("loginUser")==null)
		{
			modelAndView.setViewName("redirect:login.html");
			return modelAndView;
		}
		
		String id = (String)request.getParameter("id");
		
		if(id.equals(((Userable)session.getAttribute("loginUser")).getId()))
		{
			TheOasisMember member = (TheOasisMember)session.getAttribute("loginUser");
			modelAndView.addObject("prof",member.getProfile().getProfileInfoList());
			modelAndView.setViewName("redirect:mypage.html");
		}else{
			modelAndView.setViewName("friendPage");
			modelAndView.addObject("id", id);
			modelAndView.addObject("name", MemberController.getInstance().search(id).getName());
			modelAndView.addObject("prof",MemberController.getInstance().search(id).getProfile().getProfileInfoList());
			
			String isFriend;
			if((GroupController.getInstance().getManager().searchGroup((Userable)session.getAttribute("loginUser"), "Ä£±¸")).contains(id)){
				isFriend = "o";
			}
			else{
				isFriend = "x";
			}
			modelAndView.addObject("isFriend", isFriend);
		}
		return modelAndView;
	}

}
