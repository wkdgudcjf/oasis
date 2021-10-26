package com.theOasis.member.languageBuddy;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import com.theOasis.member.Member;
import com.theOasis.member.Userable;

/**
 * language buddy를 관리하는 클래스
 * 이 클래스는 회원에게 language buddy 서비스를 편하게 이용할 수 있도록
 * 다양한 메소드를 제공하는 클래스이다.
 * 자신과 조건이 맞는 다른회원을 추천해주어 빠르고 편하게 언어를 공유할 수 있다.
 * language buddy를 등록한 회원들과 맺어진 회원들을 따로 관리하여 
 * 버디매칭 서비스를 제공한다.
 * 
 * @author yewon
 *
 */
public class LanguageBuddyManagement {
	/**
	 * language buddy join list
	 * 현재 버디가 있는 사람들을 모아 놓은 맵객체
	 */
	private HashMap<String, String> joinList;
	/**
	 * language buddy 를 등록한 회원 목록
	 */
	private LanguageBuddyList languageBuddies;
	
	public LanguageBuddyManagement()
	{
		this(null,null);
	}
	public LanguageBuddyManagement(HashMap<String, String> joinList)
	{
		this(joinList,null);
	}
	public LanguageBuddyManagement(LanguageBuddyList languageBuddies)
	{
		this(null, languageBuddies);
	}
	public LanguageBuddyManagement(HashMap<String, String> joinList,LanguageBuddyList languageBuddies )
	{
		if(joinList!=null)
			this.joinList = joinList;
		else
			this.joinList = new HashMap<String, String>();
		
		if(languageBuddies!=null)
			this.languageBuddies = languageBuddies;
		else
			this.languageBuddies = new LanguageBuddyList();
		
	}
	/**
	 * 해당 회원에게 맞는 language buddy 추천 목록을 제공합니다. 
	 * 버디를 등록한 회원목록중 선택하여 제공합니다.
	 * @param profile 프로필
	 * @return 추천 목록
	 */
	public List<LanguageBuddy> recommendedList(LanguageBuddyProfile profile)
	{
		if(profile==null)
			return null;
		
		LinkedList<LanguageBuddy> list = new LinkedList<LanguageBuddy>();
		for(LanguageBuddy lb :languageBuddies.getList())
		{
			if(joinList.containsKey(lb.getMe().getId()))
				continue;
			if(lb.getProfile()==null)
				continue;
			if(lb.getProfile().getMotherTongue().equals(profile.getWannaLearn()))
			{
				if(lb.getProfile().getWannaLearn().equals(profile.getMotherTongue()))
				{
					list.add(lb);
				}
			}
		}
		return list;
	}
	/**
	 * 해당 회원에게 맞는 language buddy 추천 목록을 제공합니다.
	 * 버디를 등록한 회원목록중 랜덤으로 선택하여 제공합니다.
	 * @param id 회원의 id
	 * @return 추천 목록
	 */
	public List<LanguageBuddy> recommendedList(String id)
	{
		return recommendedList(languageBuddies.get(id));
	}
	/**
	 * 해당 회원에게 맞는 language buddy 추천 목록을 제공합니다.
	 * 버디를 등록한 회원목록중 선택하여 제공합니다.
	 * @param me 회원
	 * @return 추천 목록
	 */
	public List<LanguageBuddy> recommendedList(Userable me)
	{
		if(me!=null)
			return recommendedList(languageBuddies.get(me.getId()));
		else return null;
	}
	/**
	 * 해당 회원에게 맞는 language buddy 추천 목록을 제공합니다.
	 * 버디를 등록한 회원목록중 선택하여 제공합니다.
	 * @param lb language buddy 회원
	 * @return 추천 목록
	 */
	public List<LanguageBuddy> recommendedList(LanguageBuddy lb)
	{
		if(lb!=null)
			return recommendedList(lb.getProfile());
		return null;
	}
	/**
	 * language buddy 관계를 끊습니다.
	 * @param id 회원의 아이디
	 * @param lbId 회원의 language buddy의 id
	 * @return 성공 여부
	 */
	public boolean breakLb(String id, String lbId)
	{
		LanguageBuddy me = languageBuddies.get(id);
		LanguageBuddy buddy = languageBuddies.get(lbId);
		if((me==null)||(lbId==null))
			return false;
		if(me.getBuddy().getId().equals(lbId)&&buddy.getBuddy().getId().equals(id))
		{
			joinList.remove(id);
			joinList.remove(lbId);
			me.setBuddy(null);
			buddy.setBuddy(null);
			return true;
		}
		return false;
	}
	/**
	 * language buddy 관계를 끊습니다.
	 * @param id 회원의 아이디
	 * @return 성공 여부
	 */	
	public boolean breakLb(String id)
	{
		if(joinList.containsKey(id))
			return breakLb(id, joinList.get(id));
		return false;
	}

	/**
	 * language buddy 관계를 끊습니다.
	 * @param me 회원
	 * @return 성공 여부
	 */
	public boolean breakLb(Userable me)
	{
		if(me!=null)
			return breakLb(me.getId());
		return false;
	}
	/**
	 * language buddy 관계를 끊습니다.
	 * @param lb 회원
	 * @return 성공 여부
	 */
	public boolean breakLb(LanguageBuddy lb)
	{
		if(lb!=null)
			return breakLb(lb.getMe().getId());
		return false;
	}
	/**
	 * language buddy 관계를 맺습니다.
	 * 요청이 수락되면 매칭목록에 등록시킵니다.
	 * @param id 회원의 id
	 * @param lbId 원하는 language buddy의 id
	 * @return 성공 여부
	 */
	public boolean makeLb(String id, String lbId)
	{
		if(joinList.containsKey(id))
		{
			if(!joinList.get(id).equals(lbId))
				return false;
			else
				return true;
		}
		if(joinList.containsKey(lbId))
		{
			if(!joinList.get(lbId).equals(id))
				return false;
		}
				
		LanguageBuddy me = languageBuddies.get(id);
		LanguageBuddy you = languageBuddies.get(lbId);
		if((me!=null)&&(you!=null))
		{
			joinList.put(id, lbId);
			joinList.put(lbId, id);
			me.setBuddy(you.getMe());
			you.setBuddy(me.getMe());
			return true;
		}
		return false;
	}
	/**
	 * language buddy 관계를 맺습니다
	 * 요청이 수락되면 매칭목록에 등록시킵니다.
	 * @param me 회원
	 * @param buddy 원하는 language buddy
	 * @return 성공 여부
	 */
	public boolean makeLb(Userable me, Userable buddy)
	{
		return makeLb(me.getId(), buddy.getId());
	}
	/**
	 * language buddy 관계를 맺습니다. 
	 * 요청이 수락되면 매칭목록에 등록시킵니다.
	 * @param me 회원
	 * @param buddy 원하는 language buddy
	 * @return 성공 여부
	 */
	public boolean makeLb(LanguageBuddy me, LanguageBuddy buddy)
	{
		return makeLb(me.getMe().getId(), buddy.getMe().getId());
	}
	/**
	 * language buddy 서비스를 신청합니다.
	 * 버디 등록 목록에 회원을 등록시킵니다.
	 * @param user 회원
	 * @param introduction 자기 소개
	 * @param motherTongue 모국어
	 * @param wannaLearn 배우고 싶은 언어
	 * @return 성공 여부
	 */
	public boolean enroll(Userable user, String introduction, String motherTongue, String wannaLearn)
	{
		return enroll(user, new LanguageBuddyProfile(introduction, motherTongue, wannaLearn));
	}
	/**
	 * language buddy 서비스를 신청합니다.
	 * 버디 등록 목록에 회원을 등록시킵니다.
	 * @param user 회원
	 * @param profile 프로필
	 * @return 성공 여부
	 */
	public boolean enroll(Userable user, LanguageBuddyProfile profile)
	{
		if((user==null)||(profile==null))
			return false;
		if(languageBuddies.get(user.getId())!=null)
			return false;
		languageBuddies.add(user, profile);
		return true;
	}
	/**
	 * language buddy 서비스를 탈퇴합니다.
	 * 버디 등록 목록에 회원을 삭제시킵니다.
	 * @param id 회원의 id
	 * @return 성공 여부
	 */
	public boolean unenroll(String id){
		if(joinList.containsKey(id))
		{
			String temp = joinList.get(id);
			joinList.remove(temp);
			joinList.remove(id);
			breakLb(id);
		}
		languageBuddies.remove(id);
		return true;
	}
	/**
	 * language buddy 서비스를 탈퇴합니다.
	 *  버디 등록 목록에 회원을 삭제시킵니다.
	 * @param me 회원
	 * @return 성공 여부
	 */
	public boolean unenroll(Userable me)
	{
		return unenroll(me.getId());
	}
	/**
	 * language buddy 서비스를 탈퇴합니다.
	 *  버디 등록 목록에 회원을 삭제시킵니다.
	 * @param me 회원
	 * @return 성공 여부
	 */
	public boolean unenroll(LanguageBuddy me)
	{
		return unenroll(me.getMe().getId());
	}
	public HashMap<String, String> getJoinList() {
		return joinList;
	}
	public void setJoinList(HashMap<String, String> joinList) {
		this.joinList = joinList;
	}
	public LanguageBuddyList getLanguageBuddies() {
		return languageBuddies;
	}
	public void setLanguageBuddies(LanguageBuddyList languageBuddies) {
		this.languageBuddies = languageBuddies;
	}
	/**
	 * 자신의 프로필을 변경할 수 있습니다.
	 * 등록이 되어있지 않다면 false를 리턴합니다.
	 * @param id 회원
	 * @param introduction 자기소개
	 * @param motherTongue 모국어
	 * @param wannaLearn 배우고 싶은 언어
	 * @return 변경성공여부
	 */
	public boolean modifyProfile(String id,String introduction,String motherTongue,String wannaLearn)
	{
		if(languageBuddies.get(id)==null)
			return false;
		
		languageBuddies.get(id).setProfile(new LanguageBuddyProfile(introduction, motherTongue, wannaLearn));
		return true;
	}
	public static void main(String[] args) {
		LanguageBuddyManagement manager = new LanguageBuddyManagement();
		manager.enroll(new Member("yewon","1234","예원"),"내소개","한국어","영어");
		manager.enroll(new Member("Jisu","1234","지수"),"내소개","영어","한국어");
		manager.enroll(new Member("hc","1234","형철"),"내소개","영어","한국어");
		System.out.println(manager.recommendedList("yewon"));
		manager.makeLb("hc", "Jisu");
		System.out.println(manager.recommendedList("yewon"));
		//manager.breakLb("hc");
		//System.out.println(manager.recommendedList("yewon"));
		manager.unenroll("hc");
		System.out.println(manager.recommendedList("yewon"));
	}
}
