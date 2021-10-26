package com.theOasis.member;
/**
 * 회원을 가르키는 클래스
 * User 클래스를 확장한 클래스
 * @author jisu
 *
 */
public class Member extends User {
	
	/**
	 * 회원의 이름
	 */
	private String name;
	
	/**
	 * default 생성자
	 */
	public Member(){
	}
	
	/**
	 * 생성자 오버로딩
	 * @param id
	 * @param password
	 */
	public Member(String id, String password, String name){
		super(id,password);
		this.name = name;
	}
	/**
	 * 회원의 이름을 반환한다.
	 * @return name 회원의 이름을 반환
	 */
	public String getName(){
		return name;
	}
	
	/**
	 * 회원의 이름을 갱신한다.
	 * @param name 이름을 전달받아 회원의 이름에 저장한다.
	 */
	public void setName(String name){
		this.name = name;
	}
	
	public String toString(){
		String str = super.toString() + " name : " + name;
		return str;
	}
}
