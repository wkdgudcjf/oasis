package com.theOasis.member;

import java.io.Serializable;

/**
 * 
 * ȸ���� �����ʿ� ���� ������ ���� ��� �ϱ� ������ ���� �ϱ� ���ϰ� ���뼺�� Ȯ�强�� ����Ͽ� 
 * �����ʿ� ����ϰ� ���� ī�װ��� ī�װ��� �ش��ϴ� ����, ���������� ����� ���� ������,
 * ProfileŬ������ ��ü�� ���Եȴ�.
 * �����ڰ� ī�װ��� ���� �ø��� �Ͱų�, ���̰� ���� �� ���α׷��ӿ��Ա��� �������� �ʾƵ� ���� ���� �� �� �ִ�.
 * @author jisu
 *
 */
public class ProfileInfo implements Serializable
{
	
	/**
	 * ī�װ���
	 */
	private String infoName;
	/**
	 * ����
	 */
	private String infoData;
	/**
	 * ���� ����
	 */
	private PrivacyInfo privacy;

	/**
	 * default ������
	 */
	public ProfileInfo(){
		
	}
	
	/**
	 * ������ ī�װ����� ���޹޾� �������� �����Ѵ�.
	 * �����ڸ� ���� �޼ҵ�
	 * @param infoName ������ ī�װ���
	 */
	public ProfileInfo(String infoName){
		this.infoName = infoName;
		this.infoData = infoData;
	}
	
	/**
	 * ī�װ��� ������ ���޹޾� ��ü�� �����ϴ� ������
	 * @param infoName ī�װ���
	 * @param infoData �ش��ϴ� ����
	 */
	public ProfileInfo(String infoName, String infoData){
		this.infoName = infoName;
		this.infoData = infoData;
	}
	
	/**
	 * ī�װ��� ����, ���������� ���޹޾� ��ü�� �����ϴ� ������
	 * @param infoName ī�װ���
	 * @param infoData �ش��ϴ� ����
	 * @param privacy ��������
	 */
	public ProfileInfo(String infoName, String infoData, PrivacyInfo privacy){
		this.infoName = infoName;
		this.infoData = infoData;
		this.privacy = privacy;
	}
	/**
	 * ȸ���� �����ʿ� ���� ���������� �����Ѵ�.
	 * @param privacy  �����ʿ� ���� ��������
	 */
	public void setPrivacy(PrivacyInfo privacy){
		this.privacy = privacy;
	}
	
	/**
	 * ȸ���� �����ʿ� ���� ���������� ��ȯ�Ѵ�.
	 * @return �����ʿ� ���� ��������
	 */
	public PrivacyInfo getPrivacy(){
		return privacy;
	}
	
	/**
	 * ȸ���� �����ʿ� ���� ī�װ����� �����Ѵ�.
	 * @param infoName �����ʿ� ���� ī�װ���
	 */
	public void setInfoName(String infoName){
		
	}
	
	/**
	 * ȸ���� �����ʿ� ���� ī�װ����� ��ȯ�Ѵ�.
	 * @return �����ʿ� ���� ī�װ���
	 */
	public String getInfoName(){
		return infoName;
	}
	
	/**
	 * ȸ���� ������ ī�װ��� ���� ������ �����Ѵ�.
	 * @param infoData ������ ī�װ��� ���� ����
	 */
	public void setInfoData(String infoData){
		this.infoData = infoData;
	}
	
	/**
	 * ȸ���� ������ ī�װ��� ���� ������ ��ȯ�Ѵ�.
	 * @return ������ ī�װ��� ���� ����
	 */
	public String getInfoData(){
		return infoData;
	}
	
	public String toString(){
		String str = "������ ī�װ� ��  : " + infoName + " ������ ���� : " + infoData + " ���� ���� : " + privacy;
		return str;
	}
}
