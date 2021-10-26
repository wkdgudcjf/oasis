package com.theOasis.member;


import java.util.*;
/**
 * 
 * 같이 글을 공유하고 이야기 할 수 있도록 원하는 회원들을 여러명 묶어 그룹을 만들수 있는 클래스
 * @author jisu
 *
 */
public class Group extends MemberList {
	
	/**
	 * 그룹 이름
	 */
	private String groupName;
	
	/**
	 * 생성자
	 */
	public Group(){
		
	}
	
	/**
	 * 생성자
	 * @param memberList
	 */
	public Group(List<Userable> memberList){
		super(memberList);
	}
	/**
	 * 
	 * 그룹을 만들기를 원하는 회원들의 리스트와 그룹명을 전달받아 그룹을 생성한다.
	 * @param memberList
	 * @param groupName
	 */
	public Group(List<Userable> memberList, String groupName){
		super(memberList);
		this.groupName = groupName;
	}
	
	/**
	 * 그룹 명을 전달받아 그룹 객체를 생성한다.
	 * @param groupName 그룹을 만들 시 지정할 그룹 명
	 */
	public Group(String groupName){
		super();
		this.groupName = groupName;
	}
	/**
	 * 그룹 명을 반환하는 메소드
	 * @return groupName 그룹의 이름을 반환
	 */
	public String getGroupName(){
		return groupName;
	}
	
	/**
	 * 그룹 명을 갱신하는 메소드
	 * @param groupName 그룹의 이름을 전달받아 저장한다.
	 */
	public void setGroupName(String groupName){
		this.groupName = groupName;
	}

	@Override
	public String toString() {
		return super.toString() + "Group [groupName=" + groupName + "]";
	}
	
	
}
