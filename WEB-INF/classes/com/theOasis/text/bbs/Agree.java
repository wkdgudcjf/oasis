package com.theOasis.text.bbs;
/**
 * 단독적인 클래스로
 * 게시글의 상태를 표현해준다.
 * 표현에는 good과 bad 가 있다.
 * @author yewon
 *
 */
public class Agree {
	/**
	 * Good or bad,
	 * Good이면 true, Bad이면 false
	 */
	private boolean isGood;
	/**
	 * 공감을 포함한 게시물 번호
	 */
	private int bbsNumber;
	/**
	 * 작성자
	 */
	private String writer;
	
	public Agree()
	{
		this(false, -1, "작자미상");
	}
	public Agree(boolean isGood, int bbsNumber, String writer)
	{
		this.isGood = isGood;
		this.bbsNumber= bbsNumber;
		this.writer = writer;
	}
	/**
	 * good을 리턴한다.
	 * @return good
	 */
	public boolean getIsGood() {
		return isGood;
	}
	/**
	 * good을 설정한다.
	 * @param isGood 설정할 good
	 */
	public void setIsGood(boolean isGood) {
		this.isGood = isGood;
	}
	/**
	 * 게시물 번호를 리턴한다.
	 * @return 게시물 번호.
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
	/**
	 * 작성자를 리턴한다.
	 * @return
	 */
	public String getWriter() {
		return writer;
	}
	/**
	 * 작성자를 설정한다.
	 * @param writer 설정할 작성자
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
