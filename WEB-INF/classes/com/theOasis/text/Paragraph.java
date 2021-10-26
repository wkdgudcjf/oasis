package com.theOasis.text;
/**
 * ���� �⺻�̵Ǵ� ����.
 * ���α׷����� ��� �۵��� �� Ŭ������ ��ӹ޴´�.
 * @author yewon
 *
 */
public abstract class Paragraph implements Readable{
	/**
	 * ����
	 */
	private String content;
	/**
	 * �ۼ���
	 */
	private String writer;
	
	public Paragraph()
	{
		this("�������", "���ڹ̻�"); 
	}
	/**
	 * 
	 * @param content ����
	 * @param writer �ۼ���
	 */
	public Paragraph(String content, String writer)
	{
		this.content = content;
		this.writer = writer;
	}
	/**
	 * @return ����
	 */
	public String getContent()
	{
		return content;
	}
	/**
	 * @param content ����
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
	 * @param writer �ۼ���
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
