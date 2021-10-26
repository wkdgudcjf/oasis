package com.theOasis.member;

import java.io.Serializable;
import java.util.*;

/**
 * 회원이 자신을 나타내기 위한 이미지 혹은 프로필을 등록 할 수 있도록 하는 클래스
 * 프로필의 경우 프로필 정보클래스의 객체들을 리스트로 갖고 있다.
 * @author jisu
 *
 */
public class Profile implements Serializable
{
	
	/**
	 * 회원이 등록하는 자신을 나타내기 위한 이미지
	 */
	private String mainImage;
	
	/**
	 * 회원의 프로필 정보들
	 */
	private List<ProfileInfo> profileInfoList;
	
	private final String[] defaulfProfile = new String[]{"관심사","연애상태","출신학교","기분","성별","혈액형","국적","직장","직업"};
	/*
	 * default 생성자
	 */
	public Profile(){
		profileInfoList = new LinkedList<ProfileInfo>();
		for(String profile : defaulfProfile){
			profileInfoList.add(new ProfileInfo(profile,"없음"));
		}
	}
	
	/**
	 * 회원의 이미지와 프로필들을 전달받아 프로필을 생성하는 생성자
	 * @param mainImage 회원의 이미지
	 * @param profileInfoList 프로필 정보 리스트
	 */
	public Profile(String mainImage, List<ProfileInfo> profileInfoList){
		this.mainImage = mainImage;
		this.profileInfoList = profileInfoList;
		
	}
	
	/**
	 * 프로필정보들을 전달받아 프로필을 생성하는 생성자
	 * @param profileInfoList 프로필 정보 리스트
	 */
	public Profile(List<ProfileInfo> profileInfoList){
		this.profileInfoList = profileInfoList;
	}
	
	/**
	 * 프로필 정보를 받아서 회원의 프로필 List에 추가한다.
	 * @param info 프로필정보
	 */
	public void addProfileInfo(ProfileInfo info){
		profileInfoList.add(info);
		
	}
	
	/**
	 * 프로필에 대한 카테고리, 정보, 공개범위를 전달받아 회원의 프로필 List에 추가한다.
	 * @param infoName 카테고리
	 * @param infoData 카테고리에 해당하는 정보
	 * @param privacy 공개범위
	 */
	public void addProfileInfo(String infoName, String infoData, PrivacyInfo privacy){
		profileInfoList.add(new ProfileInfo(infoName, infoData, privacy));
	}
	/**
	 * 프로필에 대한 카테고리와 정보를 전달받아 회원의 프로필 List에 추가한다
	 * @param infoName 카테고리
	 * @param infoData 카테고리에 해당하는 정보
	 */
	public void addProfileInfo(String infoName, String infoData){
		profileInfoList.add(new ProfileInfo(infoName, infoData));
	}
	
	/**
	 * 회원의 프로필 카테고리와 정보 공개범위를 수정한다.
	 * @param oldInfo 수정할 프로필 정보
	 * @param newInfo 수정되어질 프로필 정보
	 */
	public void setProfileInfo(ProfileInfo oldInfo, ProfileInfo newInfo){
		profileInfoList.set(profileInfoList.indexOf(oldInfo), newInfo);
	}
	
	/**
	 * 수정하고자 하는 카테고리에 대한 정보를 전달받아 프로필 정보를 수정한다.
	 * @param infoName 수정할 프로필의 카테고리
	 * @param infoData 수정되어질 프로필 정보
	 */
	public void setProfileInfo(String infoName, String infoData){
		for(ProfileInfo info : profileInfoList){
			if(info.getInfoName().equals(infoName)){
				info.setInfoData(infoData);
			}
		}
		//해당하는 infoName이 없을 시 실패여부
	}
	
	/**
	 * 수정하고자 하는 카테고리에 대한 정보를 전달받아 해당 프로필 정보에 대한 카테고리, 정보, 공개범위를 수정한다.
	 * @param infoName 수정할 프로필의 카테고리
	 * @param newInfo 수정되어질 프로필 정보
	 */
	public void setProfileInfo(String infoName, ProfileInfo newInfo){
		for(ProfileInfo info : profileInfoList){
			if(info.getInfoName().equals(infoName)){
				profileInfoList.set(profileInfoList.indexOf(info), newInfo);
			}
		}
	}
	
	/**
	 * index를 전달받아 해당하는 프로필을 반환한다.
	 * @param index List의 index
	 * @return 프로필 정보
	 */
	public ProfileInfo getProfileInfo(int index){
		return profileInfoList.get(index);
	}
	
	/**
	 * 카테고리명를 전달받아 해당하는 프로필을 반환한다. 
	 * @param infoName 카테고리명
	 * @return 프로필 정보
	 */
	public ProfileInfo getProfileInfo(String infoName){
		for(ProfileInfo info : profileInfoList){
			if(info.getInfoName().equals(infoName)){
				return info;
			}
		}
		//실패시 실패 여부
		return null;
	}
	
	/**
	 * 카테고리명을 전달받아 해당하는 프로필을 삭제한다.
	 * @param infoName 카테고리명
	 */
	public void removeProfileInfo(String infoName){
		for(ProfileInfo info : profileInfoList){
			if(info.getInfoName().equals(infoName)){
				profileInfoList.remove(info);
				//성공 시
			}
		}
		//실패 시
	}
	
	/**
	 * 프로필 정보를 전달받아 해당하는 프로필을 삭제한다.
	 * @param info 프로필 정보
	 */
	public void removeProfileInfo(ProfileInfo info){
		profileInfoList.remove(info);
		//성공 시
		//실패 시
	}
	
	/**
	 * 회원이 올린 이미지를 반환한다.
	 * @return 회원이 올린 이미지
	 */
	public String getMainImage(){
		return mainImage;
	}
	
	/**
	 * 회원이 올린 이미지를 갱신한다.
	 * @param mainImage 회원이 올린 이미지
	 */
	public void setMainImage(String mainImage){
		this.mainImage = mainImage;
	}
	
	/**
	 * 회원의 프로필 정보 리스트를 반환한다.
	 * @return 프로필 정보 리스트
	 */
	public List<ProfileInfo> getProfileInfoList(){
		return profileInfoList;
	}
	
	/**
	 * 회원의 프로필 정보 리스트를 갱신한다.
	 * @param profileInfoList 프로필 정보 리스트
	 */
	public void setProfileInfoList(List<ProfileInfo> profileInfoList){
		this.profileInfoList = profileInfoList;
	}
	
	public String toString(){
		String str = "이미지 : " + mainImage + "/n" + "프로필 : " + profileInfoList;
		return str;
	}
	
}
