package com.theOasis.text.bbs;
/**
 * �˻��� ī�װ��� �����ϴ� enum
 * @author JHC
 *
 */
public enum BBSInfo {
	/**
	 * ī�װ� ����
	 */
	COMMENT("����"), 
	/**
	 * ī�װ� ����
	 */
	AGREE("����");
	/**
	 * ��ȯ�� ���ڿ�.
	 */
	private final String value;
	private BBSInfo(String value)
	{
		this.value = value;
	}
	public String getValue()
	{
		return value;
	}
}
