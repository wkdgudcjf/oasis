package com.theOasis.text.bbs;
/**
 * 검색할 카테고리를 지정하는 enum
 * @author JHC
 *
 */
public enum BBSInfo {
	/**
	 * 카테고리 내용
	 */
	COMMENT("내용"), 
	/**
	 * 카테고리 공감
	 */
	AGREE("공감");
	/**
	 * 변환할 문자열.
	 */
	private final String value;
	private BBSInfo(String value)
	{
		this.value = value;
	}
	public String getValue()
	{
		return value;
	}
}
