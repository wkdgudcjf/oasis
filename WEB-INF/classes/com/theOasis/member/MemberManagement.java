package com.theOasis.member;

import java.util.*;
/**
 * ȸ������ ������ ��� ���� �մ� �ڷᱸ�� ������ memberList�� �����ϴ� Ŭ����
 * ȸ�� ��Ͽ� ȸ���� �߰��ϰ� �����ϰ� ������ �� ������, �Ǵ� ȸ���� ���ϴ��� Ȯ�� �� �� �ִ�.
 * @author jisu
 *
 */
public class MemberManagement {
	/**
	 * ȸ�� ���
	 */
	private MemberList mList;
	
	/**
	 * default������
	 */
	public MemberManagement(){
		mList = new MemberList();
	}
	/**
	 * ȸ������� ���޹޾� ��ü�� �����ϴ� ������
	 * @param memberList
	 */
	public MemberManagement(MemberList mList){
		this.mList = mList;
	}
	/**
	 * ID�� NAME�� ���޹޾� ��Ͽ��� ã�Ƽ� ��ȯ�Ѵ�.
	 * @param info enum�� ID, NAME ����
	 * @param data ã���� �ϴ� ȸ���� ������
	 * @return ã�� ȸ����
	 */
	public List<Userable> search(MemberInfo info, String data){
		return mList.get(info, data);
	}
	
	/**
	 * ���̵� ���޹޾� �ش��ϴ� ȸ���� ��Ͽ��� �����Ѵ�.
	 * @param id ���̵�
	 */
	public void remove(String id){
		mList.remove(id);
	}
	
	/**
	 * ȸ�� ��ü�� ���޹޾� �ش��ϴ� ȸ���� ��Ͽ��� �����Ѵ�.
	 * @param member ȸ��
	 */
	public void remove(Userable member){
		mList.remove(member.getId());
	}
	
	/**
	 * ���̵�� ȸ�� ��ü�� ���޹޾� ���̵� �ش��ϴ� ȸ���� ���޹��� ȸ�� ��ü�� �����Ѵ�.
	 * @param id �����ϰ��� �ϴ� ȸ���� ���̵�
	 * @param member ���� �����Ǿ����� �ϴ� ȸ�� ��ü
	 */
	public void modify(String id, Userable member){
		if(!mList.contains(member)){
			mList.set(id, member);
		}
	}
	
	/**
	 * ���̵�� ��й�ȣ�� ���޹޾� ���̵� �ش��ϴ� ȸ���� ��й�ȣ�� �����Ѵ�.
	 * @param id ��й�ȣ�� �����ϰ��� �ϴ� ȸ���� ���̵�
	 * @param password ���� �����Ǿ����� �ϴ� ��й�ȣ
	 */
	public void modify(String id, String password){
		Member temp = (Member)mList.get(MemberInfo.ID,id).get(0);
		temp.setPassword(password);
	}
	
	public void modifyProfile(String id, String infoName, String infoData, PrivacyInfo privacy){
		TheOasisMember temp = (TheOasisMember)mList.get(MemberInfo.ID, id).get(0);
		temp.getProfile().getProfileInfo(infoName).setInfoName(infoName);
		temp.getProfile().getProfileInfo(infoName).setPrivacy(privacy);
	}
	/**
	 * ȸ���� ���̵� ���޹޾� �ش��ϴ� ȸ���� �ִ��� �˻��Ѵ�.
	 * @param id �����ϴ��� �˻��ϰ��� �ϴ� ȸ���� ���̵�
	 * @return ���� ����
	 */
	public boolean check(String id){
		return mList.contains(id);
	}
	
	/**
	 * ȸ���� ���̵�� ȸ���� ��й�ȣ�� ���޹޾� �ش��ϴ� ȸ���� �ִ��� �˻��Ѵ�.
	 * @param id �����ϴ��� �˻��ϰ��� �ϴ� ȸ���� ���̵�
	 * @param password �����ϴ��� �˻��ϰ��� �ϴ� ȸ���� ��й�ȣ
	 * @return ���� ����
	 */
	public boolean check(String id, String password){
		if(mList.get(MemberInfo.ID,id).size()==0)
			return false;
		if(mList.get(MemberInfo.ID,id).get(0)!= null){
			if(mList.get(MemberInfo.ID,id).get(0).getPassword().equals(password)){
				return true;
			}
		}
		return false;
	}
	
	/**
	 * ȸ���� ���̵�� �̸� ��й�ȣ�� ���޹޾� ��Ͽ� �߰��Ѵ�(ȸ�� ����)
	 * @param id ȸ�� ���� �ϰ����ϴ� ȸ���� ���̵�
	 * @param name ȸ�� ���� �ϰ����ϴ� ȸ���� �̸�
	 * @param password ȸ�� ���� �ϰ����ϴ� ȸ���� ��й�ȣ
	 * @return �߰� ���� ���� ����
	 */
	public boolean add(String id, String name, String password){
		return mList.add(id, name, password);
	}
	
	/**
	 * ȸ�� ��ü�� ���޹޾� ��Ͽ� �߰��Ѵ�.
	 * @param member ȸ����ü
	 * @return �߰� ���� ���� ����
	 */
	public boolean add(Userable member){
		return mList.add(member);
	}
	
	/**
	 * ȸ�� ����� ��ȯ�Ѵ�.
	 * @return ȸ�� ���
	 */
	public MemberList getMList() {
		return mList;
	}
	
	/**
	 * ȸ�� ����� �����Ѵ�.
	 * @param memberList ȸ�� ���
	 */
	public void setMList(MemberList mList) {
		this.mList = mList;
	}
	
	@Override
	public String toString() {
		return "MemberManagement [mList=" + mList + "]";
	}
	public static void main(String args[]){
		MemberManagement manager = new MemberManagement();
		System.out.println(manager.add("kjs","1234","������"));
		System.out.println(manager.add("kjs","1234","������"));
		System.out.println(manager.add("kyw","1234","�迹��"));
		System.out.println(manager.add("kyw2","1234","�迹��2"));
		Userable temp = new Member("jhc","2222","����ö");
		Userable temp2 = new Member("jhc","3333","����ö");
		System.out.println(manager.add(temp));
		System.out.println(manager.add(temp2));
		System.out.println(manager);
		System.out.println("-----------------------------add Test-----------------------------");
		System.out.println(manager.check("kjs"));
		System.out.println(manager.check("kjs","2222")+"��й�ȣ�� Ʋ�����");
		System.out.println(manager.check("kkk")+"���̵� ���� ���");
		System.out.println("-----------------------------check Test-----------------------------");
		manager.modify("kjs",temp);
		manager.modify("kjs", "555555555");
		System.out.println(manager);
		System.out.println("-----------------------------modify Test-----------------------------");
		manager.remove("kjs");
		manager.remove(temp);
		System.out.println(manager);
		System.out.println("-----------------------------remove Test-----------------------------");
		System.out.println(manager.search(MemberInfo.ID, "kyw2"));
		System.out.println(manager.search(MemberInfo.NAME, "�迹��"));
		System.out.println("-----------------------------search Test-----------------------------");
	}
}
