package com.theOasis.text;
/**
 * setter/getter로 이루어진 인터페이스.
 * 배포용으로 작성되었으며,
 * 모든 글들은 인터페이스 타입으로 생성된다.
 * @author yewon
 *
 */
public interface Readable {
	/**
	 * 글의 내용을 제공합니다
	 * @return 글의 내용
	 */
	public String getContent();
	/**
	 * 글의 내용을 변경합니다
	 * @param content 글의 내용
	 */
	public void setContent(String content);
	/**
	 * 글의 작성자를 제공합니다
	 * @return 작성자
	 */
	public String getWriter();
	/**
	 * 글의 작성자를 변경합니다.
	 * @param writer 작성자
	 */
	public void setWriter(String writer);
}
