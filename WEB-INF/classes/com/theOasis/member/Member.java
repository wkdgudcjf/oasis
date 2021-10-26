package com.theOasis.member;
/**
 * ȸ���� ����Ű�� Ŭ����
 * User Ŭ������ Ȯ���� Ŭ����
 * @author jisu
 *
 */
public class Member extends User {
	
	/**
	 * ȸ���� �̸�
	 */
	private String name;
	
	/**
	 * default ������
	 */
	public Member(){
	}
	
	/**
	 * ������ �����ε�
	 * @param id
	 * @param password
	 */
	public Member(String id, String password, String name){
		super(id,password);
		this.name = name;
	}
	/**
	 * ȸ���� �̸��� ��ȯ�Ѵ�.
	 * @return name ȸ���� �̸��� ��ȯ
	 */
	public String getName(){
		return name;
	}
	
	/**
	 * ȸ���� �̸��� �����Ѵ�.
	 * @param name �̸��� ���޹޾� ȸ���� �̸��� �����Ѵ�.
	 */
	public void setName(String name){
		this.name = name;
	}
	
	public String toString(){
		String str = super.toString() + " name : " + name;
		return str;
	}
}
