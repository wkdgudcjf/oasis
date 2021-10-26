package com.theOasis.server.controller;

import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.theOasis.controller.GroupController;
import com.theOasis.controller.MemberController;
import com.theOasis.member.MemberInfo;
import com.theOasis.member.TheOasisMember;
import com.theOasis.member.*;
import com.theOasis.member.Userable;


@Controller
public class SearchFormController {
	
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView toAddFriend(HttpSession session) {
		ModelAndView modelAndView = new ModelAndView();
		if(session.getAttribute("loginUser")==null)
		{
			modelAndView.setViewName("redirect:login.html");
			return modelAndView;
		}
		modelAndView.setViewName("searchFriend");
		modelAndView.addObject("standByGroup", GroupController.getInstance().getMyStandByGroup(((Userable)session.getAttribute("loginUser")).getId()));
		List<String[]> standByFriend = new LinkedList<String[]>();
		if(GroupController.getInstance().getMyStandByFriend(((TheOasisMember)session.getAttribute("loginUser")).getId())!=null){
		for(String id : GroupController.getInstance().getMyStandByFriend(((TheOasisMember)session.getAttribute("loginUser")).getId())){
			String name = ((TheOasisMember)MemberController.getInstance().getManager().search(MemberInfo.ID, id).get(0)).getName();
			//이미지 불러오기 위한 구문
			//String image = ((TheOasisMember)MemberController.getInstance().getManager().search(MemberInfo.ID, id).get(0)).getProfile().getMainImage();
			//arr에 추가
			String[] arr = new String[]{id,name};
			standByFriend.add(arr);
		}
		}
		modelAndView.addObject("standByFriend",standByFriend);
		return modelAndView;
	}
	
	@ModelAttribute
	public User setUpForm() {
		return new User();
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView onSubmit(User user, HttpServletRequest request, HttpSession session) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("searchFriend");
		
		Userable loginUser = ((Userable)session.getAttribute("loginUser"));
		
		String loginUserId = ((Userable)session.getAttribute("loginUser")).getId();
		
		String id = request.getParameter("id");
		
		String idName = request.getParameter("idName");
		List<TheOasisMember> memberList = new LinkedList<TheOasisMember>();
		List<String[]> searchList = new LinkedList<String[]>();
		
		if(idName.equals("id")){
		List<Userable> tempList = MemberController.getInstance().getManager().search(MemberInfo.ID,id);
		for(Userable temp : tempList){
			String isFriend = null;
			
			TheOasisMember temp2 = (TheOasisMember)temp;
			memberList.add(temp2);
			if((GroupController.getInstance().getManager().searchGroup(loginUser, "친구")).contains(temp.getId())){
				isFriend = "친구";
			}
			
			else if((GroupController.getInstance().getMyStandByFriend(temp.getId()).contains(loginUserId))){
				isFriend = "이미요청";
			}
			else{
				//System.out.println(GroupController.getInstance().getMyStandByFriend(loginUserId).contains(temp.getId()));
				isFriend = "친구아님";
			}
			String[] arr = new String[]{temp2.getId(), temp2.getName(), isFriend};
			searchList.add(arr);
			}
		}
		
		else if(idName.equals("name")){
			List<Userable> tempList = MemberController.getInstance().getManager().search(MemberInfo.NAME, id);
			for(Userable temp : tempList){
				String isFriend = null;
				
				TheOasisMember temp2 = (TheOasisMember)temp;
				memberList.add(temp2);
				if((GroupController.getInstance().getManager().searchGroup(loginUser, "친구")).contains(temp.getId())){
					isFriend = "친구";
				}
				else if((GroupController.getInstance().getMyStandByFriend(temp.getId()).contains(loginUserId))){
					isFriend = "이미요청";
				}
				else{
					isFriend = "친구아님";
				}
				String[] arr = new String[]{temp2.getId(), temp2.getName(), isFriend};
				searchList.add(arr);
				}
			}
		modelAndView.addObject("memberList", memberList);
		modelAndView.addObject("searchList", searchList);
		return modelAndView;
		}
	}
