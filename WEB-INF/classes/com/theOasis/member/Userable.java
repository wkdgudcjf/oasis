package com.theOasis.member;

/**
 * ȸ���� �������� ������ ������ ���� �������̽�
 * �������̽��� �����ϰ� �ִ� Ŭ������ �ش� �޼ҵ带 ��� overriding�ؾ� �Ѵ�.
 * @author jisu
 *
 */
public interface Userable {
	
	/**
	 * ȸ���� ���̵� ��ȯ�Ѵ�
	 * @return ȸ���� ���̵�
	 */
	public String getId();
	
	/**
	 * ȸ���� ��й�ȣ�� ��ȯ�Ѵ�.
	 * @return ȸ���� ��й�ȣ
	 */
	public String getPassword();
}
