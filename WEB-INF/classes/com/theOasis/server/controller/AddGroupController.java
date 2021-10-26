package com.theOasis.server.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.theOasis.controller.GroupController;
import com.theOasis.member.User;
import com.theOasis.member.Userable;

@Controller
public class AddGroupController {
	
	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView onSubmit(HttpServletRequest request, HttpSession session) {
		
		ModelAndView modelAndView = new ModelAndView();
		if(session.getAttribute("loginUser")==null)
		{
			modelAndView.setViewName("redirect:login.html");
			return modelAndView;
		}
		String loginUser = ((Userable)session.getAttribute("loginUser")).getId();
		String[] friends = request.getParameterValues("chkItem");
		String groupName = request.getParameter("groupName");
		if(friends!=null)
		{
			for(String friend : friends){
				GroupController.getInstance().requestGroup(loginUser, friend, groupName);
			}
		}
		
		modelAndView.setViewName("redirect:group.html");

		//�ش� ģ������ ģ�� ��û�� ����
		//System.out.println("��"+GroupController.getInstance().getMyStandByFriend(loginUser));
		//System.out.println(GroupController.getInstance().getMyStandByFriend(id)); //ģ���߰��� ��û�� ģ���� ��û��Ͽ� ��
		
		return modelAndView;
	}
}
