package com.theOasis.server.controller;

import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.theOasis.member.languageBuddy.*;
import com.theOasis.member.*;
import com.theOasis.controller.*;

@Controller
public class LanguageBuddyFormController {

	@ModelAttribute("languageBuddyProfile")
	public LanguageBuddyProfile setUpForm() {
		return new LanguageBuddyProfile(); // ModelAttribute에 저장
	}

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView toEnrollView(HttpSession session) {
		ModelAndView modelAndView = new ModelAndView();
		if (session.getAttribute("loginUser") == null) {
			modelAndView.setViewName("redirect:login.html");
			return modelAndView;
		}

		String id = ((Userable) session.getAttribute("loginUser")).getId();
		Userable me = (Userable) session.getAttribute("loginUser"); // TheOasisMember를
																	// Userable로
																	// 업캐스팅하여 담음
		String enroll = null; //
		String lbRequest = null; // 요청목록이 있는지 없는지 검사

		if (LanguageBuddyController.getInstance().getManager()
				.getLanguageBuddies().get(id) == null) {
			modelAndView.setViewName("languageBuddy");
			enroll = "미가입자";
			modelAndView.addObject("enroll", enroll); // 미 가입자 일 시 미 가입자라는
														// String이 저장
			return modelAndView;
		} else if (LanguageBuddyController.getInstance().getManager()
				.getJoinList().get(id) == null) {

			
			// enroll = "가입자";
			LanguageBuddyProfile mylbProfile = LanguageBuddyController
					.getInstance().getManager().getLanguageBuddies().get(id)
					.getProfile();
			modelAndView.addObject("myIntroduction",LanguageBuddyController.getInstance().getManager().getLanguageBuddies().get(id).getProfile().getIntroduction());
			modelAndView.addObject("myMotherTongue", LanguageBuddyController.getInstance().getManager().getLanguageBuddies().get(id).getProfile().getMotherTongue());
			modelAndView.addObject("myWannalearn", LanguageBuddyController.getInstance().getManager().getLanguageBuddies().get(id).getProfile().getWannaLearn());
			// Userable me = (Userable)session.getAttribute("loginUser");
			// //TheOasisMember를 Userable로 업캐스팅하여 담음
			List<LanguageBuddy> tempList = LanguageBuddyController
					.getInstance().getManager().recommendedList(me);
			List<LanguageBuddy> recommendList = new LinkedList<LanguageBuddy>();
			for(LanguageBuddy buddy : tempList){
				//System.out.println(LanguageBuddyController.getInstance().getRequest(buddy.getMe().getId()));
				if(LanguageBuddyController.getInstance().getRequest(buddy.getMe().getId()).contains(id)){
					continue;
				}
				else if(LanguageBuddyController.getInstance().getRequest(id).contains(buddy.getMe().getId())){
					continue;
				}
				recommendList.add(buddy);
			} 
			modelAndView.addObject("recommendList", recommendList);
			List<String> temp = LanguageBuddyController.getInstance()
					.getRequest(id);// 나에게 랭버 요청을 한 사람의 아이디 모음
			// System.out.println("LanguageBuddyFormController : 70" +
			// temp.size());
			List<String[]> requestList = new LinkedList<String[]>();
			// List<TheOasisMember> requestList = new
			// LinkedList<TheOasisMember>();
			// List<LanguageBuddy> requestList2 = new
			// LinkedList<LanguageBuddy>();
			lbRequest = "요청목록없음";

			if (temp != null) {
				if (temp.size() != 0) {
					lbRequest = "요청목록있음";
					for (String lbId : temp) {
						String name = MemberController.getInstance()
								.search(lbId).getName();
						String mainImage = "이미지";
						String introduction = LanguageBuddyController
								.getInstance().getManager()
								.getLanguageBuddies().get(lbId).getProfile()
								.getIntroduction();
						requestList.add(new String[] { lbId, name, mainImage,
								introduction });
						// requestList.add(MemberController.getInstance().search(lbId));
						// requestList2.add(LanguageBuddyController.getInstance().getManager().getLanguageBuddies().get(lbId));
					}
					modelAndView.addObject("requestList", requestList);
					// modelAndView.addObject("requestList2", requestList2);
					// //LanguageBuddy로 이루어짐
					modelAndView.addObject("lbRequest", lbRequest);
					enroll = "LanguageBuddy없음";
					modelAndView.addObject("enroll", enroll);
					modelAndView.setViewName("languageBuddy");
					return modelAndView;
				}
				enroll = "LanguageBuddy없음";
				modelAndView.addObject("enroll", enroll);
				modelAndView.setViewName("languageBuddy");
				return modelAndView;
			}
		} else {
			enroll = "LanguageBuddy있음";
			String lbid = LanguageBuddyController.getInstance().getManager()
					.getJoinList().get(id);
			// languagebuddy를 반환하는 메소드
			TheOasisMember lb = MemberController.getInstance().search(lbid);
			modelAndView.addObject("Lb",LanguageBuddyController.getInstance().getManager().getLanguageBuddies().get(lbid));
			// 사진은 myLB.mainImage로 갖고오면된당
			modelAndView.addObject("myLB", lb);// 나의 랭귀지 버디
			modelAndView.addObject("enroll", enroll);
			modelAndView.setViewName("languageBuddy");
			return modelAndView;
		}
		modelAndView.addObject("myIntroduction",LanguageBuddyController.getInstance().getManager().getLanguageBuddies().get(id).getProfile().getIntroduction());
		modelAndView.addObject("myMotherTongue", LanguageBuddyController.getInstance().getManager().getLanguageBuddies().get(id).getProfile().getMotherTongue());
		modelAndView.addObject("myWannalearn", LanguageBuddyController.getInstance().getManager().getLanguageBuddies().get(id).getProfile().getWannaLearn());
		enroll = "LanguageBuddy없음";
		modelAndView.addObject("enroll", enroll);
		modelAndView.setViewName("languageBuddy");
		return modelAndView;
	}

	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView onSubmit(LanguageBuddyProfile languageBuddyProfile,
			HttpServletRequest request, HttpSession session) {
		//System.out.println("LanguageBuddyFormController:50"
		//		+ languageBuddyProfile);
		ModelAndView modelAndView = new ModelAndView();
		Userable me = (Userable) session.getAttribute("loginUser"); // TheOasisMember를
																	// Userable로
																	// 업캐스팅하여 담음
		String lbRequest = null;
		String id = me.getId();

		LanguageBuddyController.getInstance().getManager()
				.enroll(me, languageBuddyProfile);
		List<LanguageBuddy> recommendList = LanguageBuddyController
				.getInstance().getManager().recommendedList(me);
		modelAndView.addObject("recommendList", recommendList);
		String enroll = "LanguageBuddy없음";
		modelAndView.addObject("enroll", enroll);
		modelAndView.addObject("myIntroduction",LanguageBuddyController.getInstance().getManager().getLanguageBuddies().get(id).getProfile().getIntroduction());
		modelAndView.addObject("myMotherTongue", LanguageBuddyController.getInstance().getManager().getLanguageBuddies().get(id).getProfile().getMotherTongue());
		modelAndView.addObject("myWannalearn", LanguageBuddyController.getInstance().getManager().getLanguageBuddies().get(id).getProfile().getWannaLearn());
		modelAndView.setViewName("languageBuddy");
		return modelAndView;
	}
}