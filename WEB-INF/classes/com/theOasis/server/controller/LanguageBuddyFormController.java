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
		return new LanguageBuddyProfile(); // ModelAttribute�� ����
	}

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView toEnrollView(HttpSession session) {
		ModelAndView modelAndView = new ModelAndView();
		if (session.getAttribute("loginUser") == null) {
			modelAndView.setViewName("redirect:login.html");
			return modelAndView;
		}

		String id = ((Userable) session.getAttribute("loginUser")).getId();
		Userable me = (Userable) session.getAttribute("loginUser"); // TheOasisMember��
																	// Userable��
																	// ��ĳ�����Ͽ� ����
		String enroll = null; //
		String lbRequest = null; // ��û����� �ִ��� ������ �˻�

		if (LanguageBuddyController.getInstance().getManager()
				.getLanguageBuddies().get(id) == null) {
			modelAndView.setViewName("languageBuddy");
			enroll = "�̰�����";
			modelAndView.addObject("enroll", enroll); // �� ������ �� �� �� �����ڶ��
														// String�� ����
			return modelAndView;
		} else if (LanguageBuddyController.getInstance().getManager()
				.getJoinList().get(id) == null) {

			
			// enroll = "������";
			LanguageBuddyProfile mylbProfile = LanguageBuddyController
					.getInstance().getManager().getLanguageBuddies().get(id)
					.getProfile();
			modelAndView.addObject("myIntroduction",LanguageBuddyController.getInstance().getManager().getLanguageBuddies().get(id).getProfile().getIntroduction());
			modelAndView.addObject("myMotherTongue", LanguageBuddyController.getInstance().getManager().getLanguageBuddies().get(id).getProfile().getMotherTongue());
			modelAndView.addObject("myWannalearn", LanguageBuddyController.getInstance().getManager().getLanguageBuddies().get(id).getProfile().getWannaLearn());
			// Userable me = (Userable)session.getAttribute("loginUser");
			// //TheOasisMember�� Userable�� ��ĳ�����Ͽ� ����
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
					.getRequest(id);// ������ ���� ��û�� �� ����� ���̵� ����
			// System.out.println("LanguageBuddyFormController : 70" +
			// temp.size());
			List<String[]> requestList = new LinkedList<String[]>();
			// List<TheOasisMember> requestList = new
			// LinkedList<TheOasisMember>();
			// List<LanguageBuddy> requestList2 = new
			// LinkedList<LanguageBuddy>();
			lbRequest = "��û��Ͼ���";

			if (temp != null) {
				if (temp.size() != 0) {
					lbRequest = "��û�������";
					for (String lbId : temp) {
						String name = MemberController.getInstance()
								.search(lbId).getName();
						String mainImage = "�̹���";
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
					// //LanguageBuddy�� �̷����
					modelAndView.addObject("lbRequest", lbRequest);
					enroll = "LanguageBuddy����";
					modelAndView.addObject("enroll", enroll);
					modelAndView.setViewName("languageBuddy");
					return modelAndView;
				}
				enroll = "LanguageBuddy����";
				modelAndView.addObject("enroll", enroll);
				modelAndView.setViewName("languageBuddy");
				return modelAndView;
			}
		} else {
			enroll = "LanguageBuddy����";
			String lbid = LanguageBuddyController.getInstance().getManager()
					.getJoinList().get(id);
			// languagebuddy�� ��ȯ�ϴ� �޼ҵ�
			TheOasisMember lb = MemberController.getInstance().search(lbid);
			modelAndView.addObject("Lb",LanguageBuddyController.getInstance().getManager().getLanguageBuddies().get(lbid));
			// ������ myLB.mainImage�� �������ȴ�
			modelAndView.addObject("myLB", lb);// ���� ������ ����
			modelAndView.addObject("enroll", enroll);
			modelAndView.setViewName("languageBuddy");
			return modelAndView;
		}
		modelAndView.addObject("myIntroduction",LanguageBuddyController.getInstance().getManager().getLanguageBuddies().get(id).getProfile().getIntroduction());
		modelAndView.addObject("myMotherTongue", LanguageBuddyController.getInstance().getManager().getLanguageBuddies().get(id).getProfile().getMotherTongue());
		modelAndView.addObject("myWannalearn", LanguageBuddyController.getInstance().getManager().getLanguageBuddies().get(id).getProfile().getWannaLearn());
		enroll = "LanguageBuddy����";
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
		Userable me = (Userable) session.getAttribute("loginUser"); // TheOasisMember��
																	// Userable��
																	// ��ĳ�����Ͽ� ����
		String lbRequest = null;
		String id = me.getId();

		LanguageBuddyController.getInstance().getManager()
				.enroll(me, languageBuddyProfile);
		List<LanguageBuddy> recommendList = LanguageBuddyController
				.getInstance().getManager().recommendedList(me);
		modelAndView.addObject("recommendList", recommendList);
		String enroll = "LanguageBuddy����";
		modelAndView.addObject("enroll", enroll);
		modelAndView.addObject("myIntroduction",LanguageBuddyController.getInstance().getManager().getLanguageBuddies().get(id).getProfile().getIntroduction());
		modelAndView.addObject("myMotherTongue", LanguageBuddyController.getInstance().getManager().getLanguageBuddies().get(id).getProfile().getMotherTongue());
		modelAndView.addObject("myWannalearn", LanguageBuddyController.getInstance().getManager().getLanguageBuddies().get(id).getProfile().getWannaLearn());
		modelAndView.setViewName("languageBuddy");
		return modelAndView;
	}
}