package com.theOasis.member;

import java.io.Serializable;

/**
 * 
 * 회원의 프로필에 대한 정보의 양이 방대 하기 때문에 관리 하기 편리하고 재사용성과 확장성을 고려하여 
 * 프로필에 등록하고 싶은 카테고리와 카테고리에 해당하는 정보, 공개범위를 멤버로 갖고 있으며,
 * Profile클래스의 객체에 포함된다.
 * 관리자가 카테고리의 수를 늘리고 싶거나, 줄이고 싶을 때 프로그래머에게까지 도달하지 않아도 쉽게 수정 할 수 있다.
 * @author jisu
 *
 */
public class ProfileInfo implements Serializable
{
	
	/**
	 * 카테고리명
	 */
	private String infoName;
	/**
	 * 정보
	 */
	private String infoData;
	/**
	 * 공개 범위
	 */
	private PrivacyInfo privacy;

	/**
	 * default 생성자
	 */
	public ProfileInfo(){
		
	}
	
	/**
	 * 프로필 카테고리명만을 전달받아 프로필을 생성한다.
	 * 관리자를 위한 메소드
	 * @param infoName 프로필 카테고리명
	 */
	public ProfileInfo(String infoName){
		this.infoName = infoName;
		this.infoData = infoData;
	}
	
	/**
	 * 카테고리와 정보를 전달받아 객체를 생성하는 생성자
	 * @param infoName 카테고리명
	 * @param infoData 해당하는 정보
	 */
	public ProfileInfo(String infoName, String infoData){
		this.infoName = infoName;
		this.infoData = infoData;
	}
	
	/**
	 * 카테고리와 정보, 공개범위를 전달받아 객체를 생성하는 생성자
	 * @param infoName 카테고리명
	 * @param infoData 해당하는 정보
	 * @param privacy 공개범위
	 */
	public ProfileInfo(String infoName, String infoData, PrivacyInfo privacy){
		this.infoName = infoName;
		this.infoData = infoData;
		this.privacy = privacy;
	}
	/**
	 * 회원의 프로필에 대한 공개범위를 갱신한다.
	 * @param privacy  프로필에 대한 공개범위
	 */
	public void setPrivacy(PrivacyInfo privacy){
		this.privacy = privacy;
	}
	
	/**
	 * 회원의 프로필에 대한 공개범위를 반환한다.
	 * @return 프로필에 대한 공개범위
	 */
	public PrivacyInfo getPrivacy(){
		return privacy;
	}
	
	/**
	 * 회원의 프로필에 대한 카테고리명을 갱신한다.
	 * @param infoName 프로필에 대한 카테고리명
	 */
	public void setInfoName(String infoName){
		
	}
	
	/**
	 * 회원의 프로필에 대한 카테고리명을 반환한다.
	 * @return 프로필에 대한 카테고리명
	 */
	public String getInfoName(){
		return infoName;
	}
	
	/**
	 * 회원의 프로필 카테고리에 대한 정보를 갱신한다.
	 * @param infoData 프로필 카테고리에 대한 정보
	 */
	public void setInfoData(String infoData){
		this.infoData = infoData;
	}
	
	/**
	 * 회원의 프로필 카테고리에 대한 정보를 반환한다.
	 * @return 프로필 카테고리에 대한 정보
	 */
	public String getInfoData(){
		return infoData;
	}
	
	public String toString(){
		String str = "프로필 카테고리 명  : " + infoName + " 프로필 정보 : " + infoData + " 공개 범위 : " + privacy;
		return str;
	}
}
