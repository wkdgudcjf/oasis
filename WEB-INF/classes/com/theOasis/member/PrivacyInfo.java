package com.theOasis.member;

/**
 * �������� ī�װ��� �����ϴ� enum
 * @author jisu
 *
 */
public enum PrivacyInfo {
	/**
	 * ���� ������ ī�װ����� ��ü����, ģ�����Ը� ����, ������� �ִ�.
	 */
	PUBLIC("��ü����"), FRIEND("ģ�����Ը� ����"), PRIVATE("�����");
	
	/**
	 * ��ȯ�� ���ڿ�
	 */
	final private String name;
	
	/**
	 * ���ڿ��� ���޹޾� enum�� �����ϴ� private ������
	 * @param name
	 */
	private PrivacyInfo(String name){
		this.name = name;
	}
	
	/**
	 * ī�װ��� �ش��ϴ� ���ڿ��� ��ȯ�Ѵ�.
	 * @return ī�װ��� ���ڿ�
	 */
	public String getName(){
		return name;
	}
}
