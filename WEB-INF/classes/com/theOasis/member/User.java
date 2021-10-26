package com.theOasis.member;
/**
 * 
 * 회원을 생성 하기 위한 공통적인 데이터와 메소드를 갖고 있는 클래스
 * Userable 인터페이스를 구현하고 있으며 따라서 인터페이스에 맞춰 구현해야할 메소드가 존재한다.
 * @author jisu
 *
 */
public class User implements Userable {
	/**
	 * 회원의 아이디
	 */
	private String id;
	/**
	 * 회원의 비밀번호
	 */
	private String password;
	
	/**
	 * default 생성자
	 */
	public User(){
		
	}
	
	/**
	 * 생성자
	 * @param id
	 * @param password
	 */
	public User(String id, String password){
		this.id = id;
		this.password = password;
	}
	/**
	 * 회원의 아이디를 반환한다.
	 * @return id 회원의 아이디
	 */
	public String getId(){
		return id;
	}
	
	/**
	 * 회원이 아이디를 갱신한다.
	 * @param id 아이디를 받아서 멤버에 저장
	 */
	public void setId(String id){
		this.id = id;
	}
	
	/**
	 * 회원의 비밀번호를 반환한다.
	 *  @return id 회원의 비밀번호
	 */
	public String getPassword(){
		return password;
	}
	
	/**
	 * 회원의 비밀번호를 갱신한다.
	 * @param password 비밀번호
	 */
	public void setPassword(String password){
		this.password = password;
	}
	
	public String toString(){
		String str = "id : " + id + ", password : " + password +", ";
		return str;
	}
}
