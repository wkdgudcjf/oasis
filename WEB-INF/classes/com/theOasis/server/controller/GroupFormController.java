package com.theOasis.server.controller;

import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.theOasis.controller.GroupController;
import com.theOasis.member.*;

@Controller
public class GroupFormController {
	
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView toMyGroup(HttpSession session) {
		ModelAndView modelAndView = new ModelAndView();
		if(session.getAttribute("loginUser")==null)
		{
			modelAndView.setViewName("redirect:login.html");
			return modelAndView;
		}
		String id = ((Userable)session.getAttribute("loginUser")).getId();
		List<Userable> temp = GroupController.getInstance().getManager().searchGroup(id).get("模备").getMemberList();
		List<TheOasisMember> friendList = new LinkedList<TheOasisMember>(); 
		for(Userable user : temp){
			friendList.add((TheOasisMember)user);
		}
		modelAndView.setViewName("friend");
		modelAndView.addObject("friendList", friendList);
		
		
		List<Group> groupList = new LinkedList<Group>();
		GroupList temp2 = GroupController.getInstance().getManager().searchGroup(id);
		
		for(MemberList groupTemp : temp2.getGroupList()){
			Group group = (Group)groupTemp;
			if(!(group.getGroupName().equals("模备"))){
			groupList.add(group);
			}
		}
		modelAndView.setViewName("group");
		modelAndView.addObject("groupList", groupList);
		return modelAndView;
	}
	

}
