package com.theOasis.text;

public enum TextInfo {
	CONTENT("����"), WRITER("�ۼ���");
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
