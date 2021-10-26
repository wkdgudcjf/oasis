package com.theOasis.member;
/**
 * 검색할 카테고리를 지정하는 enum
 * @author jisu
 *
 */
public enum MemberInfo {
	
	/**
	 * 카테고리로는 아이디와 이름을 갖고있다.
	 */
	ID("아이디"), NAME("이름");
	
	/**
	 * 변환할 문자열
	 */
	final private String name;
	
	/**
	 * 문자열을 전달받아 enum을 생성하는 private 생성자
	 * @param name 카테고리의 이름(문자열)
	 */
	private MemberInfo(String name){
		this.name = name;
	}
	
	/**
	 * 카테고리의 이름을 반환한다.
	 * @return 카테고리의 이름
	 */
	public String getName(){
		return name;
	}
}
