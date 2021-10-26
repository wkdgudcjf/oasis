package com.theOasis.member;
/**
 * �˻��� ī�װ��� �����ϴ� enum
 * @author jisu
 *
 */
public enum MemberInfo {
	
	/**
	 * ī�װ��δ� ���̵�� �̸��� �����ִ�.
	 */
	ID("���̵�"), NAME("�̸�");
	
	/**
	 * ��ȯ�� ���ڿ�
	 */
	final private String name;
	
	/**
	 * ���ڿ��� ���޹޾� enum�� �����ϴ� private ������
	 * @param name ī�װ��� �̸�(���ڿ�)
	 */
	private MemberInfo(String name){
		this.name = name;
	}
	
	/**
	 * ī�װ��� �̸��� ��ȯ�Ѵ�.
	 * @return ī�װ��� �̸�
	 */
	public String getName(){
		return name;
	}
}
