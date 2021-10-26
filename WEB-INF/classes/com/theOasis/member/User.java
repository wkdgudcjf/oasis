package com.theOasis.member;
/**
 * 
 * ȸ���� ���� �ϱ� ���� �������� �����Ϳ� �޼ҵ带 ���� �ִ� Ŭ����
 * Userable �������̽��� �����ϰ� ������ ���� �������̽��� ���� �����ؾ��� �޼ҵ尡 �����Ѵ�.
 * @author jisu
 *
 */
public class User implements Userable {
	/**
	 * ȸ���� ���̵�
	 */
	private String id;
	/**
	 * ȸ���� ��й�ȣ
	 */
	private String password;
	
	/**
	 * default ������
	 */
	public User(){
		
	}
	
	/**
	 * ������
	 * @param id
	 * @param password
	 */
	public User(String id, String password){
		this.id = id;
		this.password = password;
	}
	/**
	 * ȸ���� ���̵� ��ȯ�Ѵ�.
	 * @return id ȸ���� ���̵�
	 */
	public String getId(){
		return id;
	}
	
	/**
	 * ȸ���� ���̵� �����Ѵ�.
	 * @param id ���̵� �޾Ƽ� ����� ����
	 */
	public void setId(String id){
		this.id = id;
	}
	
	/**
	 * ȸ���� ��й�ȣ�� ��ȯ�Ѵ�.
	 *  @return id ȸ���� ��й�ȣ
	 */
	public String getPassword(){
		return password;
	}
	
	/**
	 * ȸ���� ��й�ȣ�� �����Ѵ�.
	 * @param password ��й�ȣ
	 */
	public void setPassword(String password){
		this.password = password;
	}
	
	public String toString(){
		String str = "id : " + id + ", password : " + password +", ";
		return str;
	}
}
