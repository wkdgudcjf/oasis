package com.theOasis.text;

public enum TextInfo {
	CONTENT("내용"), WRITER("작성자");
	private final String value;
	private TextInfo(String value)
	{
		this.value =value;
	}
	public String getValue()
	{
		return this.value;
	}
}
