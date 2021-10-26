package com.theOasis.text.message;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

import com.theOasis.text.Text;

/**
 * Text클래스를 확장한 쪽지 클래스
 * Text의 데이터를 사용하며, 쪽지의 데이터인 확인 여부와 수신자를 멤버로 갖고 있으며,
 * 쪽지를 확인 시 체크 하거나 쪽지의 데이터를 확인 할 수 있다.
 * 
 * @author yewon
 *
 */
public class Message extends Text{
	/**
	 * 확인 여부
	 */
	private boolean isCheck;
	/**
	 * 수신자
	 */
	private String receiver;
	
	/**
	 * default 생성자
	 */
	public Message()
	{
		
	}
	
	/**
	 * 쪽지가 꼭 가져야 할 필수적인 데이터를 전달받아 객체를 생성한다.
	 * @param content 쪽지의 내용
	 * @param writer 발신자
	 * @param isCheck 확인여부
	 * @param receiver 수신자
	 */
	public Message(String content, String writer, boolean isCheck, String receiver)
	{
		super(content, writer);
		this.receiver = receiver;
		this.isCheck = isCheck;
	}
	
	/**
	 * 쪽지에 속한 데이터를 모두 갖고 객체를 생성한다.
	 * @param content 쪽지의 내용
	 * @param writer 발신자
	 * @param number 쪽지 번호
	 * @param isCheck 확인여부
	 * @param receiver 수신자
	 */ 
	public Message(String content, String writer, int number, boolean isCheck, String receiver)
	{
		super(content, writer, number);
		this.isCheck = isCheck;
		this.receiver = receiver;
	}
	
	/**
	 * 쪽지가 꼭 가져야 할 필수적인 데이터를 전달받아 객체를 생성한다.
	 * @param content 쪽지의 내용
	 * @param writer 발신자
	 * @param receiver 수신자
	 */
	public Message(String content,String writer,String receiver)
	{
		this(content, writer, false, receiver);
	}
	
	/**
	 * 쪽지가 가져할 데이터를 전달받아 객체를 생성한다.
	 * @param content 쪽지의 내용
	 * @param writer 발신자
	 * @param number 쪽지 번호
	 * @param receiver 수신자
	 */
	public Message(String content,String writer, int number, String receiver){
		this(content, writer, number, false, receiver);
	}
	
	/**
	 * 쪽지 확인 여부를 반환한다.
	 * @return 확인, 불확인 여부
	 */
	public boolean getIsCheck() {
		return isCheck;
	}
	
	/**
	 * 쪽지 확인 여부를 갱신한다
	 * @param isCheck 쪽지 확인 여부
	 */
	public void setIsCheck(boolean isCheck) {
		this.isCheck = isCheck;
	}
	
	/**
	 * 쪽지의 수신자를 반환한다.
	 * @return 수신자
	 */
	public String getReceiver() {
		return receiver;
	}
	
	/**
	 * 쪽지의 수신자를 갱신한다.
	 * @param receiver 수신자
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
