package com.theOasis.text.message;

/**
 * �˻��� ī�װ��� �����ϴ� enum
 * @author jisu
 *
 */
public enum MessageInfo {
	/**
	 * ī�װ��� ����, �߽���, �����ڰ� �ִ�.
	 */
	CONTENT("����"), SENDER("�߽���"), RECEIVER("������");
	
	/**
	 * ��ȯ�� ���ڿ�
	 */
	private final String value;
	
	/**
	 * enum�� �����ϴ� ������
	 * @param value value���� ���޹޴´�
	 */
	private MessageInfo(String value)
	{
		this.value = value;
	}
	
	/**
	 * enum�� ���� ��ȯ�Ѵ�.
	 * @return enum�� ��
	 */
	public String getValue()
	{
		return value;
	}
}
