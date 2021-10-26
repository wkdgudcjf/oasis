package com.theOasis.text.message;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

import com.theOasis.text.Text;

/**
 * TextŬ������ Ȯ���� ���� Ŭ����
 * Text�� �����͸� ����ϸ�, ������ �������� Ȯ�� ���ο� �����ڸ� ����� ���� ������,
 * ������ Ȯ�� �� üũ �ϰų� ������ �����͸� Ȯ�� �� �� �ִ�.
 * 
 * @author yewon
 *
 */
public class Message extends Text{
	/**
	 * Ȯ�� ����
	 */
	private boolean isCheck;
	/**
	 * ������
	 */
	private String receiver;
	
	/**
	 * default ������
	 */
	public Message()
	{
		
	}
	
	/**
	 * ������ �� ������ �� �ʼ����� �����͸� ���޹޾� ��ü�� �����Ѵ�.
	 * @param content ������ ����
	 * @param writer �߽���
	 * @param isCheck Ȯ�ο���
	 * @param receiver ������
	 */
	public Message(String content, String writer, boolean isCheck, String receiver)
	{
		super(content, writer);
		this.receiver = receiver;
		this.isCheck = isCheck;
	}
	
	/**
	 * ������ ���� �����͸� ��� ���� ��ü�� �����Ѵ�.
	 * @param content ������ ����
	 * @param writer �߽���
	 * @param number ���� ��ȣ
	 * @param isCheck Ȯ�ο���
	 * @param receiver ������
	 */ 
	public Message(String content, String writer, int number, boolean isCheck, String receiver)
	{
		super(content, writer, number);
		this.isCheck = isCheck;
		this.receiver = receiver;
	}
	
	/**
	 * ������ �� ������ �� �ʼ����� �����͸� ���޹޾� ��ü�� �����Ѵ�.
	 * @param content ������ ����
	 * @param writer �߽���
	 * @param receiver ������
	 */
	public Message(String content,String writer,String receiver)
	{
		this(content, writer, false, receiver);
	}
	
	/**
	 * ������ ������ �����͸� ���޹޾� ��ü�� �����Ѵ�.
	 * @param content ������ ����
	 * @param writer �߽���
	 * @param number ���� ��ȣ
	 * @param receiver ������
	 */
	public Message(String content,String writer, int number, String receiver){
		this(content, writer, number, false, receiver);
	}
	
	/**
	 * ���� Ȯ�� ���θ� ��ȯ�Ѵ�.
	 * @return Ȯ��, ��Ȯ�� ����
	 */
	public boolean getIsCheck() {
		return isCheck;
	}
	
	/**
	 * ���� Ȯ�� ���θ� �����Ѵ�
	 * @param isCheck ���� Ȯ�� ����
	 */
	public void setIsCheck(boolean isCheck) {
		this.isCheck = isCheck;
	}
	
	/**
	 * ������ �����ڸ� ��ȯ�Ѵ�.
	 * @return ������
	 */
	public String getReceiver() {
		return receiver;
	}
	
	/**
	 * ������ �����ڸ� �����Ѵ�.
	 * @param receiver ������
	 */
	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}

	@Override
	public String toString() {
		
		return "Message [receiver=" + receiver +super.toString()+ "time="+super.getTime().get(GregorianCalendar.YEAR)
		+ "."
		+ (super.getTime().get(
				GregorianCalendar.MONTH) + 1)
		+ "."
		+ super.getTime().get(
				GregorianCalendar.DAY_OF_MONTH)
				+ "."
		+ super.getTime().get(
				GregorianCalendar.HOUR_OF_DAY)
				+ "."
		+ super.getTime().get(
				GregorianCalendar.MINUTE)
				+ "."
		+ super.getTime().get(
				GregorianCalendar.SECOND)
				
				+ "]\n";
	}
	
}
