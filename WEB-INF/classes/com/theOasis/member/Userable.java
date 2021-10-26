package com.theOasis.member;

/**
 * 회원에 공통적이 행위를 지정해 놓은 인터페이스
 * 인터페이스를 구현하고 있는 클래스는 해당 메소드를 모두 overriding해야 한다.
 * @author jisu
 *
 */
public interface Userable {
	
	/**
	 * 회원이 아이디를 반환한다
	 * @return 회원의 아이디
	 */
	public String getId();
	
	/**
	 * 회원의 비밀번호를 반환한다.
	 * @return 회원의 비밀번호
	 */
	public String getPassword();
}
