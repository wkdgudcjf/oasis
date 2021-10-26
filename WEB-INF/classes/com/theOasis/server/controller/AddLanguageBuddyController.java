package com.theOasis.server.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.theOasis.controller.GroupController;
import com.theOasis.controller.LanguageBuddyController;
import com.theOasis.member.Userable;

@Controller
public class AddLanguageBuddyController {
	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView onSubmit(HttpServletRequest request, HttpSession session) {
		
		ModelAndView modelAndView = new ModelAndView();
		if(session.getAttribute("loginUser")==null)
		{
			modelAndView.setViewName("redirect:login.html");
			return modelAndView;
		}
		String loginUser = ((Userable)session.getAttribute("loginUser")).getId();
		String lbid = request.getParameter("lbid");
		modelAndView.setViewName(request.getParameter("view"));
		LanguageBuddyController.getInstance().addRequest(lbid, loginUser);
		//해당 친구에게 친구 요청을 보냄
		//System.out.println("ㅎ"+GroupController.getInstance().getMyStandByFriend(loginUser));
		//System.out.println(GroupController.getInstance().getMyStandByFriend(id)); //친구추가를 요청한 친구의 요청목록에 들어감
		
		return modelAndView;
	}
}
