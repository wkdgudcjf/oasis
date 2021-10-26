package com.theOasis.member;

import java.util.*;
/**
 * 
 * 회원들을 여러명 모아서 저장할 수 있는 자료구조형의 멤버를 갖고 있는 클래스
 * 회원을 여러명 묶어서 관리할 수 있으며 List 자료구조형의 역할과 같이 목록에 회원을 삭제 추가 , 회원이 속해있는지 체크,
 * 목록의 사이즈, 목록에 있는 데이터를 모두 삭제와 같은 역할을 한다.
 * @author jisu
 *
 */
public class MemberList {
	
	/**
	 * 회원들 여러명을 저장해놓을 수 있는 List(친구목록, 그룹)
	 */
	private List<Userable> memberList;
	
	/**
	 * 생성자
	 */
	public MemberList(){
		this(new LinkedList<Userable>());
	}
	
	/**
	 * 회원목록을 전달받아 객체를 생성하는 생성자
	 * @param memberList
	 */
	public MemberList(List<Userable> memberList){
		this.memberList = memberList;
	}
	/**
	 * 아이디를 전달받아 목록에서 찾아서 삭제한다.
	 * @param id 회원 아이디
	 */
	public void remove(String id){
		memberList.remove(get(MemberInfo.ID,id).get(0));
	}
	/**
	 * Userable 객체를 받아 목록에서 찾아서 삭제한다.
	 * @param member 회원 객체
	 */
	public void remove(Userable member){
		memberList.remove(((Member)member).getName());
	}
	
	/**
	 * index를 전달받아 해당하는 회원을 목록에서 삭제한다.
	 * @param index List의 index
	 */
	public void remove(int index){
		memberList.remove(index);
	}
	

	/**
	 * ID나 NAME을 전달받아 ID나 NAME에 포함되는 회원이 있을 시 목록에 담아 반환한다.
	 * @param info ID나 Name enum 정보
	 * @param data 해당하는 데이터
	 * @return 회원목록
	 */
	public List<Userable> get(MemberInfo info, String data){
		List<Userable> tempList = new LinkedList<Userable>();
		if(info==MemberInfo.ID){
			for(Userable temp : memberList){
				User user = (User)temp;
				if(user.getId().equals(data)){ 
					tempList.add(temp);
				}
			}
			return tempList;
		}
		else if(info == MemberInfo.NAME){
			for(Userable temp : memberList){
				Member user = (Member)temp;
				if(user.getName().contains(data)){
					tempList.add(temp);
				}
			}
			return tempList;
		}
		return null; //실패 시
	}
	
	/**
	 * ID, 이름, 비밀번호를 전달받아 해당하는 회원이 목록에 없을 시 회원을 목록에 추가한다.
	 * @param id 아이디
	 * @param name 이름
	 * @param password 비밀번호
	 * @return 성공,실패여부
	 */
	public boolean add(String id, String name, String password){
		if(!contains(id)){
			Userable user = new Member(id,name,password);
			memberList.add(user);
			return true;
		}
		else{
			return false;
		}
	}
	
	/**
	 * 회원을 전달받아 해당하는 회원이 목록에 없을 시 주가한다.
	 * @param member 회원객체
	 * @return 성공,실패여부
	 */
	public boolean add(Userable member){
		if(!contains(member)){
			memberList.add(member);
			return true;
		}
		else{
			return false;
		}
	}
	
	/**
	 * 아이디와 회원 객체를 전달받아 아이디에 해당하는 회원을 전달받은 회원으로 set한다.
	 * @param id 아이디
	 * @param member 회원객체
	 */
	public void set(String id, Userable member){
		for(Userable temp : memberList){
			User user = (User)temp;
			if(user.getId().equals(id)){
				memberList.set(memberList.indexOf(temp),member);
			}
		}
		//실패 시
	}
	/**
	 * 수정하고자 하는 회원을 찾아서 다른 회원으로 set한다.
	 * @param oldMember 수정하고자 하는 회원
	 * @param newMember 새롭게 수정되어질 회원
	 */
	public void set(Userable oldMember, Userable newMember){
		for(Userable temp : memberList){
			User user = (User)temp;
			if(user.equals(oldMember)){
				memberList.set(memberList.indexOf(temp), newMember);
			}
		}
		//실패 시
	}
	
	/**
	 * 회원 객체를 전달받아 해당하는 List의 index를 반환한다.
	 * @param member 회원객체
	 * @return List의 index
	 */
	public int indexOf(Userable member){
		return memberList.indexOf(member);
	}
	
	/**
	 * 아이디를 전달받아 해당하는 List의 index를 반환한다.
	 * @param id 찾고자 하는 회원의 아이디
	 * @return List의 index
	 */
	public int indexOf(String id){
		for(Userable temp : memberList){
			if(temp.getId().equals(id)){
				return memberList.indexOf(temp);
			}
		}
		return -1;//실패 시 
	}
	/**
	 * 회원 객체를 전달받아 해당하는 회원이 있는지 검사한다.
	 * @param member 회원 객체
	 * @return 성공,실패여부
	 */
	public boolean contains(Userable member){
		for(Userable temp : memberList){
			if(temp.getId().equals(member.getId())){
				return true;
			}
		}
		return false;
	}
	
	/**
	 * 회원 아이디를 전달받아 해당하는 회원이 있는지 검사한다.
	 * @param id 회원 아이디
	 * @return 성공,실패여부
	 */
	public boolean contains(String id){
		for(Userable temp : memberList){
			User user = (User)temp;
			if(user.getId().equals(id)){
				return true;
			}
		}
		return false;
	}
	
	/**
	 * 회원 목록을 반환한다.
	 * @return 회원 목록
	 */
	public List<Userable> getMemberList(){
		return memberList;
	}
	
	
	/**
	 * 회원 목록을 갱신한다.
	 * @param memberList 회원 목록
	 */
	public void setMemberlIst(List<Userable> memberList){
		this.memberList = memberList;
	}
	
	/**
	 * List의 size를 반환한다.
	 * @return List의 size
	 */
	public int size(){
		return memberList.size();
	}
	
	/**
	 * List의 정보를 모두 삭제한다.
	 */
	public void clear(){
		memberList.clear();
	}

	@Override
	public String toString() {
		return "MemberList [memberList=" + memberList + "]";
	}
	
	public static void main(String args[]){
		MemberList list = new MemberList();
		Userable temp = new Member("kjs","1234","김지수");
		Userable temp1 = new Member("kjs","1234","김지수");
		Userable temp2 = new Member("kyw","1234","김예원");
		Userable temp3 = new Member("hyj","3333","한유진");
		list.add(temp);
		list.add(temp1);
		list.add(temp2);
		list.add(temp3);
		list.add("jhc","2222","장형철");
		System.out.println(list);
		System.out.println("------------------add Test-------------------"+"\n");
		list.remove("kjs");
		list.remove(temp2);
		System.out.println(list);
		System.out.println("------------------remove Test-------------------"+"\n");
		System.out.println((list.get(MemberInfo.ID,"kjs")));
		System.out.println((list.get(MemberInfo.ID,"jhc")));
		//System.out.println((list.get("kyw")));
		System.out.println("------------------get Test-------------------");
		list.set("jhc", new Member("ksa","2222","김상아"));
		list.set(temp3, new Member("pjs","3333","박지수"));
		System.out.println(list);
		System.out.println("------------------set Test-------------------");
		System.out.println(list.indexOf(temp3)+"존재하지 않을 시 -1 값을 리턴");
		System.out.println(list.indexOf("pjs"));
		System.out.println("------------------indexOf Test-------------------");
		System.out.println(list.contains("pjs"));
		System.out.println(list.contains("kjs"));
		System.out.println("------------------contains Test-------------------");
		System.out.println(list.size());
		System.out.println("------------------size Test-------------------");
		list.clear();
		System.out.println(list);
		System.out.println("------------------clear Test-------------------");
		
	}
}
