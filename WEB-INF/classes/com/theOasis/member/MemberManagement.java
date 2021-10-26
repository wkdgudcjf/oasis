package com.theOasis.member;

import java.util.*;
/**
 * 회원들을 여러명 묶어서 갖고 잇는 자료구조 형태의 memberList를 관리하는 클래스
 * 회원 목록에 회원을 추가하고 삭제하고 수정할 수 있으며, 또는 회원이 속하는지 확인 할 수 있다.
 * @author jisu
 *
 */
public class MemberManagement {
	/**
	 * 회원 목록
	 */
	private MemberList mList;
	
	/**
	 * default생성자
	 */
	public MemberManagement(){
		mList = new MemberList();
	}
	/**
	 * 회원목록을 전달받아 객체를 생성하는 생성자
	 * @param memberList
	 */
	public MemberManagement(MemberList mList){
		this.mList = mList;
	}
	/**
	 * ID나 NAME을 전달받아 목록에서 찾아서 반환한다.
	 * @param info enum의 ID, NAME 여부
	 * @param data 찾고자 하는 회원의 데이터
	 * @return 찾은 회원들
	 */
	public List<Userable> search(MemberInfo info, String data){
		return mList.get(info, data);
	}
	
	/**
	 * 아이디를 전달받아 해당하는 회원을 목록에서 삭제한다.
	 * @param id 아이디
	 */
	public void remove(String id){
		mList.remove(id);
	}
	
	/**
	 * 회원 객체를 전달받아 해당하는 회원을 목록에서 삭제한다.
	 * @param member 회원
	 */
	public void remove(Userable member){
		mList.remove(member.getId());
	}
	
	/**
	 * 아이디와 회원 객체를 전달받아 아이디에 해당하는 회원을 전달받은 회원 객체로 수정한다.
	 * @param id 수정하고자 하는 회원의 아이디
	 * @param member 새로 수정되었으면 하는 회원 객체
	 */
	public void modify(String id, Userable member){
		if(!mList.contains(member)){
			mList.set(id, member);
		}
	}
	
	/**
	 * 아이디와 비밀번호를 전달받아 아이디에 해당하는 회원의 비밀번호를 수정한다.
	 * @param id 비밀번호를 수정하고자 하는 회원의 아이디
	 * @param password 새로 수정되었으면 하는 비밀번호
	 */
	public void modify(String id, String password){
		Member temp = (Member)mList.get(MemberInfo.ID,id).get(0);
		temp.setPassword(password);
	}
	
	public void modifyProfile(String id, String infoName, String infoData, PrivacyInfo privacy){
		TheOasisMember temp = (TheOasisMember)mList.get(MemberInfo.ID, id).get(0);
		temp.getProfile().getProfileInfo(infoName).setInfoName(infoName);
		temp.getProfile().getProfileInfo(infoName).setPrivacy(privacy);
	}
	/**
	 * 회원의 아이디를 전달받아 해당하는 회원이 있는지 검사한다.
	 * @param id 존재하는지 검사하고자 하는 회원의 아이디
	 * @return 존재 여부
	 */
	public boolean check(String id){
		return mList.contains(id);
	}
	
	/**
	 * 회원의 아이디와 회원의 비밀번호를 전달받아 해당하는 회원이 있는지 검사한다.
	 * @param id 존재하는지 검사하고자 하는 회원의 아이디
	 * @param password 존재하는지 검사하고자 하는 회원의 비밀번호
	 * @return 존재 여부
	 */
	public boolean check(String id, String password){
		if(mList.get(MemberInfo.ID,id).size()==0)
			return false;
		if(mList.get(MemberInfo.ID,id).get(0)!= null){
			if(mList.get(MemberInfo.ID,id).get(0).getPassword().equals(password)){
				return true;
			}
		}
		return false;
	}
	
	/**
	 * 회원의 아이디와 이름 비밀번호를 전달받아 목록에 추가한다(회원 가입)
	 * @param id 회원 가입 하고자하는 회원의 아이디
	 * @param name 회원 가입 하고자하는 회원의 이름
	 * @param password 회원 가입 하고자하는 회원의 비밀번호
	 * @return 추가 성공 실패 여부
	 */
	public boolean add(String id, String name, String password){
		return mList.add(id, name, password);
	}
	
	/**
	 * 회원 객체를 전달받아 목록에 추가한다.
	 * @param member 회원객체
	 * @return 추가 성공 실패 여부
	 */
	public boolean add(Userable member){
		return mList.add(member);
	}
	
	/**
	 * 회원 목록을 반환한다.
	 * @return 회원 목록
	 */
	public MemberList getMList() {
		return mList;
	}
	
	/**
	 * 회원 목록을 갱신한다.
	 * @param memberList 회원 목록
	 */
	public void setMList(MemberList mList) {
		this.mList = mList;
	}
	
	@Override
	public String toString() {
		return "MemberManagement [mList=" + mList + "]";
	}
	public static void main(String args[]){
		MemberManagement manager = new MemberManagement();
		System.out.println(manager.add("kjs","1234","김지수"));
		System.out.println(manager.add("kjs","1234","김지수"));
		System.out.println(manager.add("kyw","1234","김예원"));
		System.out.println(manager.add("kyw2","1234","김예원2"));
		Userable temp = new Member("jhc","2222","장형철");
		Userable temp2 = new Member("jhc","3333","장형철");
		System.out.println(manager.add(temp));
		System.out.println(manager.add(temp2));
		System.out.println(manager);
		System.out.println("-----------------------------add Test-----------------------------");
		System.out.println(manager.check("kjs"));
		System.out.println(manager.check("kjs","2222")+"비밀번호가 틀린경우");
		System.out.println(manager.check("kkk")+"아이디가 없는 경우");
		System.out.println("-----------------------------check Test-----------------------------");
		manager.modify("kjs",temp);
		manager.modify("kjs", "555555555");
		System.out.println(manager);
		System.out.println("-----------------------------modify Test-----------------------------");
		manager.remove("kjs");
		manager.remove(temp);
		System.out.println(manager);
		System.out.println("-----------------------------remove Test-----------------------------");
		System.out.println(manager.search(MemberInfo.ID, "kyw2"));
		System.out.println(manager.search(MemberInfo.NAME, "김예원"));
		System.out.println("-----------------------------search Test-----------------------------");
	}
}
