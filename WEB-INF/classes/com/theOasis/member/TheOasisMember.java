package com.theOasis.member;

import java.util.*;

/**
 * 
 * The Oasis�� ȸ���� ���� Ŭ����
 * Userable�������̽��� �����ϰ� �ִ� Member Ŭ������ Ȯ���Ͽ�����, 
 * ȸ���� �̸��� ���̵� ��й�ȣ �׸��� ����, �ڵ�����ȣ, ��������, �������� ���޹޾�
 * The Oasis�� ȸ������ ȸ�� ������ �����ϸ�, �α��ν� ȸ������ Ȯ���Ѵ�.
 * @author jisu
 *
 */
public class TheOasisMember extends Member{
	
	/**
	 * ȸ���� ����
	 */
	private GregorianCalendar birthday;
	
	/**
	 * ȸ���� �ڵ��� ��ȣ
	 */
	private String phoneNumber;
	
	/**
	 * ȸ���� ��й�ȣ�� ã�� ���� ������ ����
	 */
	private String question;
	
	/**
	 * ȸ���� ��й�ȣ�� ã�� ���� ������ ������ ���� ����
	 */
	private String answer;
	
	/**
	 * ȸ���� ������
	 */
	private Profile profile;
	
	/**
	 * default ������
	 */
	public TheOasisMember(){
		
	}
	
	/**
	 * ���̵�� ��й�ȣ �̸��� ���޹޾� ��ü�� �����ϴ� ������
	 * @param id ȸ���� ���̵�
	 * @param password ȸ���� ��й�ȣ
	 * @param name ȸ���� �̸�
	 */
	public TheOasisMember(String id, String password, String name){
		super(id,password,name);
	}
	
	/**
	 * ������ overloading
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
	 * ȸ�����Կ� �ʿ��� ��� ������ ���޹޾� ��ü�� �����ϴ� ������
	 * @param id ȸ���� ���̵�
	 * @param password ȸ���� ��й�ȣ
	 * @param name ȸ���� �̸�
	 * @param birthday ȸ���� ����
	 * @param phoneNumber ȸ���� ��ȭ��ȣ
	 * @param question ����
	 * @param answer ����
	 * @param profile ȸ���� ����������
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
	 * ȸ���� ������ ��ȯ�Ѵ�.
	 * @return ȸ���� ������ ��ȯ
	 */
	public GregorianCalendar getBirthday(){
		return birthday;
	}
	
	/**
	 * ��¥�� ���޹޾� ȸ���� ������ �����Ѵ�.
	 * @param birthday ���Ϸ� �����Ϸ��� ��¥
	 */
	public void setBirthday(GregorianCalendar birthday){
		this.birthday = birthday;
	}
	
	/**
	 * ȸ���� �ڵ��� ��ȣ�� ��ȯ�Ѵ�.
	 * @return ȸ���� �ڵ��� ��ȣ
	 */
	public String getPhoneNumber(){
		return phoneNumber;
	}
	
	/**
	 * ��ȭ��ȣ�� ���޹޾� ȸ���� ��ȭ��ȣ�� �����Ѵ�.
	 * @param phoneNumber ȸ���� ��ȭ��ȣ
	 */
	public void setPhoneNumber(String phoneNumber){
		this.phoneNumber = phoneNumber;
	}
	
	/**
	 * ȸ���� ȸ�����Խ� ������ ������ ��ȯ�Ѵ�. ���̵� ��й�ȣ�� �Ҿ������ �� ���� Ȯ���� ���� ����Ѵ�.
	 * @return ȸ���� ������ ����
	 */
	public String getQuestion(){
		return question;
	}
	
	/**
	 * ȸ���� ������ ������ �����Ѵ�.
	 * @param question ����
	 */
	public void setQuestion(String question){
		this.question = question;
	}
	
	/**
	 * ȸ���� ȸ�� ���� �� �ۼ��� ������ ��ȯ�Ѵ�.
	 * @return ȸ���� ȸ������ �� �ۼ��� ����
	 */
	public String getAnswer(){
		return answer;
	}
	
	/**
	 * ȸ���� ȸ�� ���� �� �ۼ��� ������ �����Ѵ�.
	 * @param answer ���� �ۼ��� ����
	 */
	public void setAnswer(String answer){
		this.answer = answer;
	}
	
	/**
	 * ȸ���� ��� ������ ������ ��ȯ�Ѵ�.
	 * @return ȸ���� ��� ������ ����
	 */
	public Profile getProfile(){
		return profile;
	}
	
	/**
	 * ȸ���� ��� ������ ������ �����Ѵ�.
	 * @param profile ȸ���� ������ ����
	 */
	public void setProfile(Profile profile){
		this.profile = profile;
	}
	
	public String toString(){
		String str = super.toString() + " " + "���� : " + birthday + " ��ȭ��ȣ : " + phoneNumber + " ���� :" + question + " ����  : " + answer + "/n" + " ������ ���� : " + profile;
		return str;
	}
}
