package com.theOasis.member;

import java.io.Serializable;
import java.util.*;

/**
 * ȸ���� �ڽ��� ��Ÿ���� ���� �̹��� Ȥ�� �������� ��� �� �� �ֵ��� �ϴ� Ŭ����
 * �������� ��� ������ ����Ŭ������ ��ü���� ����Ʈ�� ���� �ִ�.
 * @author jisu
 *
 */
public class Profile implements Serializable
{
	
	/**
	 * ȸ���� ����ϴ� �ڽ��� ��Ÿ���� ���� �̹���
	 */
	private String mainImage;
	
	/**
	 * ȸ���� ������ ������
	 */
	private List<ProfileInfo> profileInfoList;
	
	private final String[] defaulfProfile = new String[]{"���ɻ�","���ֻ���","����б�","���","����","������","����","����","����"};
	/*
	 * default ������
	 */
	public Profile(){
		profileInfoList = new LinkedList<ProfileInfo>();
		for(String profile : defaulfProfile){
			profileInfoList.add(new ProfileInfo(profile,"����"));
		}
	}
	
	/**
	 * ȸ���� �̹����� �����ʵ��� ���޹޾� �������� �����ϴ� ������
	 * @param mainImage ȸ���� �̹���
	 * @param profileInfoList ������ ���� ����Ʈ
	 */
	public Profile(String mainImage, List<ProfileInfo> profileInfoList){
		this.mainImage = mainImage;
		this.profileInfoList = profileInfoList;
		
	}
	
	/**
	 * �������������� ���޹޾� �������� �����ϴ� ������
	 * @param profileInfoList ������ ���� ����Ʈ
	 */
	public Profile(List<ProfileInfo> profileInfoList){
		this.profileInfoList = profileInfoList;
	}
	
	/**
	 * ������ ������ �޾Ƽ� ȸ���� ������ List�� �߰��Ѵ�.
	 * @param info ����������
	 */
	public void addProfileInfo(ProfileInfo info){
		profileInfoList.add(info);
		
	}
	
	/**
	 * �����ʿ� ���� ī�װ�, ����, ���������� ���޹޾� ȸ���� ������ List�� �߰��Ѵ�.
	 * @param infoName ī�װ�
	 * @param infoData ī�װ��� �ش��ϴ� ����
	 * @param privacy ��������
	 */
	public void addProfileInfo(String infoName, String infoData, PrivacyInfo privacy){
		profileInfoList.add(new ProfileInfo(infoName, infoData, privacy));
	}
	/**
	 * �����ʿ� ���� ī�װ��� ������ ���޹޾� ȸ���� ������ List�� �߰��Ѵ�
	 * @param infoName ī�װ�
	 * @param infoData ī�װ��� �ش��ϴ� ����
	 */
	public void addProfileInfo(String infoName, String infoData){
		profileInfoList.add(new ProfileInfo(infoName, infoData));
	}
	
	/**
	 * ȸ���� ������ ī�װ��� ���� ���������� �����Ѵ�.
	 * @param oldInfo ������ ������ ����
	 * @param newInfo �����Ǿ��� ������ ����
	 */
	public void setProfileInfo(ProfileInfo oldInfo, ProfileInfo newInfo){
		profileInfoList.set(profileInfoList.indexOf(oldInfo), newInfo);
	}
	
	/**
	 * �����ϰ��� �ϴ� ī�װ��� ���� ������ ���޹޾� ������ ������ �����Ѵ�.
	 * @param infoName ������ �������� ī�װ�
	 * @param infoData �����Ǿ��� ������ ����
	 */
	public void setProfileInfo(String infoName, String infoData){
		for(ProfileInfo info : profileInfoList){
			if(info.getInfoName().equals(infoName)){
				info.setInfoData(infoData);
			}
		}
		//�ش��ϴ� infoName�� ���� �� ���п���
	}
	
	/**
	 * �����ϰ��� �ϴ� ī�װ��� ���� ������ ���޹޾� �ش� ������ ������ ���� ī�װ�, ����, ���������� �����Ѵ�.
	 * @param infoName ������ �������� ī�װ�
	 * @param newInfo �����Ǿ��� ������ ����
	 */
	public void setProfileInfo(String infoName, ProfileInfo newInfo){
		for(ProfileInfo info : profileInfoList){
			if(info.getInfoName().equals(infoName)){
				profileInfoList.set(profileInfoList.indexOf(info), newInfo);
			}
		}
	}
	
	/**
	 * index�� ���޹޾� �ش��ϴ� �������� ��ȯ�Ѵ�.
	 * @param index List�� index
	 * @return ������ ����
	 */
	public ProfileInfo getProfileInfo(int index){
		return profileInfoList.get(index);
	}
	
	/**
	 * ī�װ��� ���޹޾� �ش��ϴ� �������� ��ȯ�Ѵ�. 
	 * @param infoName ī�װ���
	 * @return ������ ����
	 */
	public ProfileInfo getProfileInfo(String infoName){
		for(ProfileInfo info : profileInfoList){
			if(info.getInfoName().equals(infoName)){
				return info;
			}
		}
		//���н� ���� ����
		return null;
	}
	
	/**
	 * ī�װ����� ���޹޾� �ش��ϴ� �������� �����Ѵ�.
	 * @param infoName ī�װ���
	 */
	public void removeProfileInfo(String infoName){
		for(ProfileInfo info : profileInfoList){
			if(info.getInfoName().equals(infoName)){
				profileInfoList.remove(info);
				//���� ��
			}
		}
		//���� ��
	}
	
	/**
	 * ������ ������ ���޹޾� �ش��ϴ� �������� �����Ѵ�.
	 * @param info ������ ����
	 */
	public void removeProfileInfo(ProfileInfo info){
		profileInfoList.remove(info);
		//���� ��
		//���� ��
	}
	
	/**
	 * ȸ���� �ø� �̹����� ��ȯ�Ѵ�.
	 * @return ȸ���� �ø� �̹���
	 */
	public String getMainImage(){
		return mainImage;
	}
	
	/**
	 * ȸ���� �ø� �̹����� �����Ѵ�.
	 * @param mainImage ȸ���� �ø� �̹���
	 */
	public void setMainImage(String mainImage){
		this.mainImage = mainImage;
	}
	
	/**
	 * ȸ���� ������ ���� ����Ʈ�� ��ȯ�Ѵ�.
	 * @return ������ ���� ����Ʈ
	 */
	public List<ProfileInfo> getProfileInfoList(){
		return profileInfoList;
	}
	
	/**
	 * ȸ���� ������ ���� ����Ʈ�� �����Ѵ�.
	 * @param profileInfoList ������ ���� ����Ʈ
	 */
	public void setProfileInfoList(List<ProfileInfo> profileInfoList){
		this.profileInfoList = profileInfoList;
	}
	
	public String toString(){
		String str = "�̹��� : " + mainImage + "/n" + "������ : " + profileInfoList;
		return str;
	}
	
}
