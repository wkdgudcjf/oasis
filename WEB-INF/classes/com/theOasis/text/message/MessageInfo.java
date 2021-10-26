package com.theOasis.text.message;

/**
 * 검색할 카테고리를 지정하는 enum
 * @author jisu
 *
 */
public enum MessageInfo {
	/**
	 * 카테고리는 내용, 발신자, 수신자가 있다.
	 */
	CONTENT("내용"), SENDER("발신자"), RECEIVER("수신자");
	
	/**
	 * 변환할 문자열
	 */
	private final String value;
	
	/**
	 * enum을 생성하는 생성자
	 * @param value value값을 전달받는다
	 */
	private MessageInfo(String value)
	{
		this.value = value;
	}
	
	/**
	 * enum의 값을 반환한다.
	 * @return enum의 값
	 */
	public String getValue()
	{
		return value;
	}
}
