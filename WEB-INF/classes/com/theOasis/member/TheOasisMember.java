package com.theOasis.member;

import java.util.*;

/**
 * 
 * The Oasis의 회원을 위한 클래스
 * Userable인터페이스를 구현하고 있는 Member 클래스를 확장하였으며, 
 * 회원의 이름과 아이디 비밀번호 그리고 생일, 핸드폰번호, 질의응답, 프로필을 전달받아
 * The Oasis의 회원으로 회원 가입이 가능하며, 로그인시 회원임을 확인한다.
 * @author jisu
 *
 */
public class TheOasisMember extends Member{
	
	/**
	 * 회원의 생일
	 */
	private GregorianCalendar birthday;
	
	/**
	 * 회원의 핸드폰 번호
	 */
	private String phoneNumber;
	
	/**
	 * 회원이 비밀번호를 찾기 위해 지정할 질문
	 */
	private String question;
	
	/**
	 * 회원이 비밀번호를 찾기 위해 지정한 질문에 대한 응답
	 */
	private String answer;
	
	/**
	 * 회원의 프로필
	 */
	private Profile profile;
	
	/**
	 * default 생성자
	 */
	public TheOasisMember(){
		
	}
	
	/**
	 * 아이디와 비밀번호 이름을 전달받아 객체를 생성하는 생성자
	 * @param id 회원의 아이디
	 * @param password 회원의 비밀번호
	 * @param name 회원의 이름
	 */
	public TheOasisMember(String id, String password, String name){
		super(id,password,name);
	}
	
	/**
	 * 생성자 overloading
	 * @param id
	 * @param password
	 * @param name
	 * @param birthday
	 * @param phoneNumber
	 * @param question
	 * @param answer
	 */
	public TheOasisMember(String id, String password, String name, GregorianCalendar birthday, String phoneNumber, String question, String answer){
		super(id,password,name);
		this.birthday = birthday;
		this.phoneNumber = phoneNumber;
		this.question = question;
		this.answer = answer;
		this.profile = new Profile();
	}
	
	/**
	 * 회원가입에 필요한 모든 정보를 전달받아 객체를 생성하는 생성자
	 * @param id 회원의 아이디
	 * @param password 회원의 비밀번호
	 * @param name 회원의 이름
	 * @param birthday 회원이 생일
	 * @param phoneNumber 회원의 전화번호
	 * @param question 질문
	 * @param answer 응답
	 * @param profile 회원의 프로필정보
	 */
	public TheOasisMember(String id, String password, String name, GregorianCalendar birthday, String phoneNumber, String question, String answer, Profile profile){
		super(id,password,name);
		this.birthday = birthday;
		this.phoneNumber = phoneNumber;
		this.question = question;
		this.answer = answer;
		this.profile = profile;
	}
	
	/**
	 * 회원의 생일을 반환한다.
	 * @return 회원의 생일을 반환
	 */
	public GregorianCalendar getBirthday(){
		return birthday;
	}
	
	/**
	 * 날짜를 전달받아 회원의 생일을 갱신한다.
	 * @param birthday 생일로 지정하려는 날짜
	 */
	public void setBirthday(GregorianCalendar birthday){
		this.birthday = birthday;
	}
	
	/**
	 * 회원의 핸드폰 번호를 반환한다.
	 * @return 회원의 핸드폰 번호
	 */
	public String getPhoneNumber(){
		return phoneNumber;
	}
	
	/**
	 * 전화번호를 전달받아 회원의 전화번호를 갱신한다.
	 * @param phoneNumber 회원의 전화번호
	 */
	public void setPhoneNumber(String phoneNumber){
		this.phoneNumber = phoneNumber;
	}
	
	/**
	 * 회원이 회원가입시 지정한 질문을 반환한다. 아이디나 비밀번호를 잃어버렸을 시 본인 확인을 위해 사용한다.
	 * @return 회원이 지정한 질문
	 */
	public String getQuestion(){
		return question;
	}
	
	/**
	 * 회원이 지정한 질문을 갱신한다.
	 * @param question 질문
	 */
	public void setQuestion(String question){
		this.question = question;
	}
	
	/**
	 * 회원이 회원 가입 시 작성한 응답을 반환한다.
	 * @return 회원이 회원가입 시 작성한 응답
	 */
	public String getAnswer(){
		return answer;
	}
	
	/**
	 * 회원이 회원 가입 시 작성한 응답을 갱신한다.
	 * @param answer 새로 작성한 응답
	 */
	public void setAnswer(String answer){
		this.answer = answer;
	}
	
	/**
	 * 회원의 모든 프로필 정보를 반환한다.
	 * @return 회원의 모든 프로필 정보
	 */
	public Profile getProfile(){
		return profile;
	}
	
	/**
	 * 회원의 모든 프로필 정보를 갱신한다.
	 * @param profile 회원의 프로필 정보
	 */
	public void setProfile(Profile profile){
		this.profile = profile;
	}
	
	public String toString(){
		String str = super.toString() + " " + "생일 : " + birthday + " 전화번호 : " + phoneNumber + " 질문 :" + question + " 응답  : " + answer + "/n" + " 프로필 정보 : " + profile;
		return str;
	}
}
