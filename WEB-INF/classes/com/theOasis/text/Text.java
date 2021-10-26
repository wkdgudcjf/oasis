package com.theOasis.text;
import java.util.GregorianCalendar;

/**
 * ���ܿ��� �ۼ��ð��� ��ȣ�� Ȯ��� Ŭ����.
 * ����� �� �ִ� ����.
 * @author yewon
 *
 */
public class Text extends Paragraph{
	/**
	 * �ۼ� �ð�
	 */
	private GregorianCalendar time;
	/**
	 * �� ��ȣ
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
	 * �ۼ��ð��� �����Ѵ�.
	 * @return �ۼ��ð�
	 */
	public GregorianCalendar getTime() {
		return time;
	}
	/**
	 * �ۼ��ð��� �����Ѵ�.
	 * @param time ������ �ۼ��ð�
	 */
	public void setTime(GregorianCalendar time) {
		this.time = time;
	}
	/**
	 * �۹�ȣ�� �����Ѵ�.
	 * @return �۹�ȣ
	 */
	public int getNumber() {
		return number;
	}
	/**
	 * �۹�ȣ�� �����Ѵ�.
	 * @param number ������ �۹�ȣ
	 */
	public void setNumber(int number) {
		this.number = number;
	}
	@Override
	public String toString() {
		return "Text ["+ "number=" + number +", content=" + super.getContent() + ", writer=" + super.getWriter()+"]";
	}
	
}
