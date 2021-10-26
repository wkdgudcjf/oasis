package com.theOasis.member;

/**
 * 공개범위 카테고리를 지정하는 enum
 * @author jisu
 *
 */
public enum PrivacyInfo {
	/**
	 * 공개 범위의 카테고리에는 전체공개, 친구에게만 공개, 비공개가 있다.
	 */
	PUBLIC("전체공개"), FRIEND("친구에게만 공개"), PRIVATE("비공개");
	
	/**
	 * 변환할 문자열
	 */
	final private String name;
	
	/**
	 * 문자열을 전달받아 enum을 생성하는 private 생성자
	 * @param name
	 */
	private PrivacyInfo(String name){
		this.name = name;
	}
	
	/**
	 * 카테고리에 해당하는 문자열을 반환한다.
	 * @return 카테고리의 문자열
	 */
	public String getName(){
		return name;
	}
}
