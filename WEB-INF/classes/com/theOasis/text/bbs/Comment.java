package com.theOasis.text.bbs;
import com.theOasis.text.Text;

/**
 * ���
 * @author yewon
 *
 */
public class Comment extends Text{
	/**
	 * ����� ������ �Խù� ��ȣ
	 */
	private int bbsNumber;
	
	public Comment()
	{
		
	}
	public Comment(String content, String writer, int bbsNumber)
	{
		this(content, writer, -1, bbsNumber);
	}
	public Comment(String content, String writer, int number, int bbsNumber)
	{
		super(content, writer, number);
		this.bbsNumber = bbsNumber;
	}
	/**
	 * �Խù� ��ȣ�� �����Ѵ�.
	 * @return �Խù� ��ȣ
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
	
	@Override
	public String toString() {
		return "Comment [bbsNumber=" + bbsNumber 
				+ ", getNumber()=" + getNumber() + ", getContent()="
				+ getContent() + ", getWriter()=" + getWriter() + "]";
	}
	
}
