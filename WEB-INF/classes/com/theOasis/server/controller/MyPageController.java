package com.theOasis.server.controller;

import java.util.List;

import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.theOasis.controller.MemberController;
import com.theOasis.member.MemberInfo;
import com.theOasis.member.ProfileInfo;
import com.theOasis.member.TheOasisMember;
import com.theOasis.text.Readable;


@Controller
public class MyPageController {

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView toLoginView(HttpSession session) {
		ModelAndView modelAndView = new ModelAndView();
		if(session.getAttribute("loginUser")!=null)
		{
			TheOasisMember user = (TheOasisMember) session.getAttribute("loginUser");
			String src = "c:/profiles/"+user.getId()+"/"+user.getProfile().getMainImage();
			//System.out.println(src);
			modelAndView.addObject("mi",src);
			modelAndView.addObject("prof", user.getProfile().getProfileInfoList());	
			modelAndView.setViewName("mypage");					
		}
		else
		{
			modelAndView.setViewName("redirect:login.html");
		}
		return modelAndView;
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView modifyprofile(HttpSession session,
			@RequestParam("1") String a,
			@RequestParam("2") String b,
			@RequestParam("3") String c,
			@RequestParam("4") String d,
			@RequestParam("5") String e,
			@RequestParam("6") String f,
			@RequestParam("7") String g,
			@RequestParam("8") String h,
			@RequestParam("9") String i) 
	{
		ModelAndView modelAndView = new ModelAndView();
		if(session.getAttribute("loginUser")!=null)
		{
			TheOasisMember user = (TheOasisMember) session.getAttribute("loginUser");
			if(a!="")
			{
				user.getProfile().setProfileInfo("관심사", a);
			}
			if(b!="")
			{
				user.getProfile().setProfileInfo("연애상태", b);
			}
			if(c!="")
			{
				user.getProfile().setProfileInfo("출신학교", c);
			}
			if(d!="")
			{
				user.getProfile().setProfileInfo("기분", d);
			}
			if(e!="")
			{
				user.getProfile().setProfileInfo("성별", e);
			}
			if(f!="")
			{
				user.getProfile().setProfileInfo("혈액형", f);
			}
			if(g!="")
			{
				user.getProfile().setProfileInfo("국적", g);
			}
			if(h!="")
			{
				user.getProfile().setProfileInfo("직장", h);
			}
			if(i!="")
			{
				user.getProfile().setProfileInfo("직업", i);
			}
			modelAndView.addObject("prof", user.getProfile().getProfileInfoList());	
			modelAndView.setViewName("mypage");					
		}
		else
		{
			modelAndView.setViewName("redirect:login.html");
		}
		return modelAndView;
	}
	
}
