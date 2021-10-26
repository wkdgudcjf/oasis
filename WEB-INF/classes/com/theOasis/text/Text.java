package com.theOasis.text;
import java.util.GregorianCalendar;

/**
 * 문단에서 작성시간과 번호가 확장된 클래스.
 * 사용할 수 있는 단위.
 * @author yewon
 *
 */
public class Text extends Paragraph{
	/**
	 * 작성 시간
	 */
	private GregorianCalendar time;
	/**
	 * 글 번호
	 */
	private int number;
	
	public Text()
	{
		this("","");
	}
	public Text(String content, String writer, GregorianCalendar time, int number)
	{
		super(content, writer);
		this.time = time;
		this.number = number;
	}
	public Text(String content, String writer, int number)
	{
		this(content, writer, new GregorianCalendar(), number);
	}
	public Text(String content, String writer)
	{
		super(content, writer);
		this.time = new GregorianCalendar();
	}
	/**
	 * 작성시간을 리턴한다.
	 * @return 작성시간
	 */
	public GregorianCalendar getTime() {
		return time;
	}
	/**
	 * 작성시간을 설정한다.
	 * @param time 설정할 작성시간
	 */
	public void setTime(GregorianCalendar time) {
		this.time = time;
	}
	/**
	 * 글번호를 리턴한다.
	 * @return 글번호
	 */
	public int getNumber() {
		return number;
	}
	/**
	 * 글번호를 설정한다.
	 * @param number 설정할 글번호
	 */
	public void setNumber(int number) {
		this.number = number;
	}
	@Override
	public String toString() {
		return "Text ["+ "number=" + number +", content=" + super.getContent() + ", writer=" + super.getWriter()+"]";
	}
	
}
