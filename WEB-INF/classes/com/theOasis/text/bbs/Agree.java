package com.theOasis.text.bbs;
/**
 * �ܵ����� Ŭ������
 * �Խñ��� ���¸� ǥ�����ش�.
 * ǥ������ good�� bad �� �ִ�.
 * @author yewon
 *
 */
public class Agree {
	/**
	 * Good or bad,
	 * Good�̸� true, Bad�̸� false
	 */
	private boolean isGood;
	/**
	 * ������ ������ �Խù� ��ȣ
	 */
	private int bbsNumber;
	/**
	 * �ۼ���
	 */
	private String writer;
	
	public Agree()
	{
		this(false, -1, "���ڹ̻�");
	}
	public Agree(boolean isGood, int bbsNumber, String writer)
	{
		this.isGood = isGood;
		this.bbsNumber= bbsNumber;
		this.writer = writer;
	}
	/**
	 * good�� �����Ѵ�.
	 * @return good
	 */
	public boolean getIsGood() {
		return isGood;
	}
	/**
	 * good�� �����Ѵ�.
	 * @param isGood ������ good
	 */
	public void setIsGood(boolean isGood) {
		this.isGood = isGood;
	}
	/**
	 * �Խù� ��ȣ�� �����Ѵ�.
	 * @return �Խù� ��ȣ.
	 */
	public int getBbsNumber() {
		return bbsNumber;
	}
	/**
	 * �Խù� ��ȣ�� �����Ѵ�.
	 * @param bbsNumber ������ �Խù� ��ȣ
	 */
	public void setBbsNumber(int bbsNumber) {
		this.bbsNumber = bbsNumber;
	}
	/**
	 * �ۼ��ڸ� �����Ѵ�.
	 * @return
	 */
	public String getWriter() {
		return writer;
	}
	/**
	 * �ۼ��ڸ� �����Ѵ�.
	 * @param writer ������ �ۼ���
	 */
	public void setWriter(String writer) {
		this.writer = writer;
	}
	@Override
	public String toString() {
		return "Agree [isGood=" + isGood + ", bbsNumber=" + bbsNumber
				+ ", writer=" + writer + "]";
	}
	
}
