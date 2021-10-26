package com.theOasis.text.bbs;
import com.theOasis.text.Text;

/**
 * 댓글
 * @author yewon
 *
 */
public class Comment extends Text{
	/**
	 * 댓글을 포함한 게시물 번호
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
	 * 게시물 번호를 리턴한다.
	 * @return 게시물 번호
	 */
	public int getBbsNumber() {
		return bbsNumber;
	}
	/**
	 * 게시물 번호를 설정한다.
	 * @param bbsNumber 설정할 게시물 번호
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
