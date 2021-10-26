package com.theOasis.server.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.*;
import com.theOasis.controller.GroupController;
import com.theOasis.member.Userable;
import com.theOasis.member.*;

@Controller
public class FriendFormController {
	
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView toMyFriend(HttpSession session) {
		ModelAndView modelAndView = new ModelAndView();
		if(session.getAttribute("loginUser")==null)
		{
			modelAndView.setViewName("redirect:login.html");
			return modelAndView;
		}
		String id = ((Userable)session.getAttribute("loginUser")).getId();
		List<Userable> temp = GroupController.getInstance().getManager().searchGroup(id).get("Ä£±¸").getMemberList();
		List<TheOasisMember> friendList = new LinkedList<TheOasisMember>(); 
		for(Userable user : temp){
			friendList.add((TheOasisMember)user);
		}
		modelAndView.setViewName("friend");
		modelAndView.addObject("friendList", friendList);
		//System.out.println(GroupController.getInstance().getMyStandByFriend(((Userable)session.getAttribute("loginUser")).getId()));
		return modelAndView;
	}
}
