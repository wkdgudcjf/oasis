package com.theOasis.controller;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import com.theOasis.member.GroupManagement;
import com.theOasis.member.Member;
import com.theOasis.member.MemberManagement;
import com.theOasis.member.TheOasisMember;
import com.theOasis.member.Userable;
import com.theOasis.member.languageBuddy.LanguageBuddy;
import com.theOasis.member.languageBuddy.LanguageBuddyList;
import com.theOasis.member.languageBuddy.LanguageBuddyManagement;
import com.theOasis.member.languageBuddy.LanguageBuddyProfile;
/**
 * 클라이언트와 매개체역할을 하는 클래스
 * language buddy를 관리하는 매니져를 필드로 가지고 있으며 매니져의 메소드를 통해
 * 리턴받은 가공되지 않은 값을 가공시켜 리턴한다.
 * 
 * 기본적으로 모든 파라미터는 문자열 데이터로 종속성을 줄여준다.
 * 이 클래스는 회원에게 language buddy 서비스를 편하게 이용할 수 있도록
 * 다양한 메소드를 제공하는 클래스이다.
 * 자신과 조건이 맞는 다른회원을 추천해주어 빠르고 편하게 언어를 공유할 수 있다.
 * language buddy를 등록한 회원들과 맺어진 회원들을 따로 관리하여 
 * 버디매칭 서비스를 제공한다.
 * 
 * @author JHC
 *
 */
public class LanguageBuddyController
{
	private LanguageBuddyManagement manager;
	private HashMap<String, List<String>> requestList;
	
	
	private static LanguageBuddyController instance;
	static{
		instance = new LanguageBuddyController();
	}
	
	private LanguageBuddyController()
	{
		this.requestList = new HashMap<String, List<String>>();
	}
	
	private boolean check(String id)
	{
		return MemberController.getInstance().getManager().check(id);
	}
	public List<String> getRequest(String id)
	{
		if(!check(id))
			return null;
		
		if(requestList.containsKey(id))
			return requestList.get(id);
		else
			return null;
	}
	public void addRequest(String requestReceiver, String requestSender)
	{
		if(!(check(requestReceiver)&&check(requestSender)))
			return ;
		
		if(requestList.containsKey(requestReceiver))
			requestList.get(requestReceiver).add(requestSender);
		else
		{
			requestList.put(requestReceiver, new LinkedList<String>());
			requestList.get(requestReceiver).add(requestSender);
		}
	}
	
	public void put(String id){
		requestList.put(id, new LinkedList<String>());
	}
	
	public void response(boolean isAccept, String requestReceiver, String requestSender)
	{
		if(!(check(requestReceiver)&&check(requestSender)))
			return ;
		
		requestList.get(requestReceiver).remove(requestSender);
		if(isAccept)
		{
			manager.makeLb(requestReceiver, requestSender);
		}
	}
	
	public LanguageBuddyManagement getManager() {
		return manager;
	}
	public void setManager(LanguageBuddyManagement manager) {
		this.manager = manager;
	}
	public static LanguageBuddyController getInstance() {
		if(instance==null)
			instance = new LanguageBuddyController();
		return instance;
	}
	public static void main(String[] args) {
		MemberController.getInstance().setManager(new MemberManagement());
		MemberController.getInstance().getManager().add("yewon","yewon", "1234");
		MemberController.getInstance().getManager().add("jisu","jisu", "1234");
		MemberController.getInstance().getManager().add("hc","hc", "1234");
		
		LanguageBuddyManagement manager = new LanguageBuddyManagement();
		manager.enroll(new Member("yewon","1234","예원"),"내소개","한국어","영어");
		manager.enroll(new Member("jisu","1234","지수"),"내소개","영어","한국어");
		manager.enroll(new Member("hc","1234","형철"),"내소개","영어","한국어");
		
		LanguageBuddyController.getInstance().setManager(manager);
		LanguageBuddyController.getInstance().addRequest("jisu", "yewon");
		LanguageBuddyController.getInstance().addRequest("jisu", "hc");
		System.out.println(LanguageBuddyController.getInstance().getRequest("jisu"));
		LanguageBuddyController.getInstance().response(false, "jisu", "hc");
		System.out.println(LanguageBuddyController.getInstance().getRequest("jisu"));
		LanguageBuddyController.getInstance().response(true, "jisu", "yewon");
		System.out.println(LanguageBuddyController.getInstance().getRequest("jisu"));
		System.out.println(LanguageBuddyController.getInstance().getManager().getJoinList());
		/*
		System.out.println(manager.recommendedList("yewon"));
		manager.makeLb("hc", "Jisu");
		System.out.println(manager.recommendedList("yewon"));
		//manager.breakLb("hc");
		//System.out.println(manager.recommendedList("yewon"));
		manager.unenroll("hc");
		System.out.println(manager.recommendedList("yewon"));
		*/
	}
	/*
	/**
	 * 해당 회원에게 맞는 language buddy 추천 목록을 제공합니다. 
	 * 버디를 등록한 회원목록중 랜덤으로 선택하여 제공합니다.
	 * @param id 회원의 id
	 * @return 추천 목록
	 */
	/*
	public List<String[]> recommendedList(String id)
	{
		/*
		 * return 값이 null이면 해당 회원이 존재하지 않음.
		 * return된 list가 비어있다면 프로필 설정이 안되어있거나 추천 목록이 없음
		 *//*
		List<String[]> re = new LinkedList<String[]>();
		if(MemberController.getInstance().check(id))
		{
			List<LanguageBuddy> list = manager.recommendedList(id);
			if(list!=null)
			{
				for(LanguageBuddy temp : list)
				{
					/*
					 * 사진, id, 이름, 자기소개
					 *//*
					re.add(new String[]{((TheOasisMember)temp.getMe()).getProfile().getMainImage(),temp.getMe().getId(), ((Member)temp.getMe()).getName(), temp.getProfile().getIntroduction()});
				}
			}
			return re;
		}
		else
			return null;
	}*/
	/*
	/**
	 * language buddy 관계를 끊습니다.
	 * 관계가 끊어지면 등록리스트에 회원들을 다시 등록시켜줍니다.
	 * @param id 회원의 아이디
	 * @return 성공 여부
	 *//*	
	public boolean breakLb(String id)
	{
		if(MemberController.getInstance().check(id))
		{
			return manager.breakLb(id);
		}
		return false;
	}
	*//*
	/**
	 * language buddy 관계를 맺습니다.
	 * 요청이 수락되면 매칭목록에 등록시킵니다.
	 * @param id 회원의 id
	 * @param lbId 원하는 language buddy의 id
	 * @return 성공 여부
	 *//*
	public boolean makeLb(String id, String lbId)
	{
		if(MemberController.getInstance().check(id))
		{
			return manager.makeLb(id, lbId);
		}
		return false;
	}
	*//*
	/**
	 * language buddy 서비스를 신청합니다.
	 * 버디 등록 목록에 회원을 등록시킵니다.
	 * @param user 회원
	 * @param introduction 자기 소개
	 * @param motherTongue 모국어
	 * @param wannaLearn 배우고 싶은 언어
	 * @return 성공 여부
	 *//*
	public boolean enroll(Userable user, String introduction, String motherTongue, String wannaLearn)
	{
		if(MemberController.getInstance().check(user.getId(), user.getPassword()))
		{
			return manager.enroll(user, introduction, motherTongue, wannaLearn);
		}
		return false;
	}
	*//*
	/**
	 * language buddy 서비스를 탈퇴합니다.
	 * 버디 등록 목록에 회원을 삭제시킵니다.
	 * @param id 회원의 id
	 * @return 성공 여부
	 *//*
	public boolean unenroll(String id){
		if(MemberController.getInstance().check(id))
		{
			return manager.unenroll(id);
		}
		return false;
	}*//*
	/**
	 * 내 버디친구 아이디를 리턴합니다.
	 * @param id
	 * @return 버디친구아이디
	 *//*
	public String getBuddy(String id)
	{
		if(MemberController.getInstance().check(id))
		{
			return manager.getLanguageBuddies().get(id).getBuddy().getId();
		}
		return null;
	}
	public HashMap<String, String> getJoinList() {
		return manager.getJoinList();
	}*/
	/*
	/**
	 * 등록된 모든 버디회원을 리턴한다
	 * @return
	 *//*
	public List<String[]> getAllbuddy()
	{
		/*
		 * 사진, 아이디, 이름, 자기소개, 모국어, 배우고 싶은 언어
		 *//*
		LanguageBuddyList list = manager.getLanguageBuddies();
		List<String[]> re = new LinkedList<String[]>();
		for(LanguageBuddy temp : list.getList())
			re.add(new String[]{((TheOasisMember)temp.getMe()).getProfile().getMainImage(),temp.getMe().getId(), ((Member)temp.getMe()).getName(), 
					temp.getProfile().getIntroduction(), temp.getProfile().getMotherTongue(), temp.getProfile().getWannaLearn()
					});
			
		return null;
	}*//*
	/**
	 * 자신의 프로필을 변경할 수 있습니다.
	 * 등록이 되어있지 않다면 false를 리턴합니다.
	 * @param id 회원
	 * @param introduction 자기소개
	 * @param motherTongue 모국어
	 * @param wannaLearn 배우고 싶은 언어
	 * @return 변경성공여부
	 *//*
	public boolean modifyProfile(String id,String introduction,String motherTongue,String wannaLearn)
	{
		if(MemberController.getInstance().check(id))
		{
			return manager.modifyProfile(id, introduction, motherTongue, wannaLearn);
		}
		return false;
	}*/

}
