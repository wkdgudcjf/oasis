package com.theOasis.text;
/**
 * setter/getter�� �̷���� �������̽�.
 * ���������� �ۼ��Ǿ�����,
 * ��� �۵��� �������̽� Ÿ������ �����ȴ�.
 * @author yewon
 *
 */
public interface Readable {
	/**
	 * ���� ������ �����մϴ�
	 * @return ���� ����
	 */
	public String getContent();
	/**
	 * ���� ������ �����մϴ�
	 * @param content ���� ����
	 */
	public void setContent(String content);
	/**
	 * ���� �ۼ��ڸ� �����մϴ�
	 * @return �ۼ���
	 */
	public String getWriter();
	/**
	 * ���� �ۼ��ڸ� �����մϴ�.
	 * @param writer �ۼ���
	 */
	public void setWriter(String writer);
}
