package com.theOasis.text;
/**
 * 가장 기본이되는 문단.
 * 프로그램내에 모든 글들은 이 클래스를 상속받는다.
 * @author yewon
 *
 */
public abstract class Paragraph implements Readable{
	/**
	 * 내용
	 */
	private String content;
	/**
	 * 작성자
	 */
	private String writer;
	
	public Paragraph()
	{
		this("내용없음", "작자미상"); 
	}
	/**
	 * 
	 * @param content 내용
	 * @param writer 작성자
	 */
	public Paragraph(String content, String writer)
	{
		this.content = content;
		this.writer = writer;
	}
	/**
	 * @return 내용
	 */
	public String getContent()
	{
		return content;
	}
	/**
	 * @param content 내용
	 */
	public void setContent(String content)
	{
		this.content = content;
	}
	public String getWriter()
	{
		return writer;
	}
	/**
	 * @param writer 작성자
	 */
	public void setWriter(String writer)
	{
		this.writer = writer;
	}
	@Override
	public String toString() {
		return "Paragraph [content=" + content + ", writer=" + writer + "]";
	}
	
}
