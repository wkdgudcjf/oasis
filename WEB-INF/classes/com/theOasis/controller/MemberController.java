package com.theOasis.controller;

import java.util.*;

import com.theOasis.member.*;
import com.theOasis.profileIO.ProfileIO;

/**
 * Ŭ���̾�Ʈ�� ��ŸŰ�ü ������ �ϴ� Ŭ����.
 * ȸ���� �����ϴ� �Ŵ����� ����� ���� ������, �� ��ü�� �������� ��ȯ�ϴ� ������ �Ѵ�.
 * 
 * �⺻������ ��� �Ķ���ʹ� ���ڿ� �����ͷ� ���Ӽ��� �ٿ��ش�.
 * ȸ���� ȸ�� ����, �α��� �� Ȯ��, Ż��, ������ ������ �Ѵ�.
 * @author jisu
 *
 */
public class MemberController {
	
	/**
	 * ȸ�� ����
	 */
	private MemberManagement manager;
	private static MemberController memberCon = new MemberController();
	/**
	 * default ������
	 */
	private MemberController(){
		
	}
	
	public static MemberController getInstance(){
		return memberCon;
	}
	/**
	 * ȸ���� �����ϴ� �Ŵ����� ���޹޾� ��ü�� �����ϴ� ������
	 * @param manager ȸ�� ���������� �ϴ� �Ŵ���
	 */
	public MemberController(MemberManagement manager){
		this.manager = manager;
	}
	
	/**
	 * ã������ ȸ���� ���̵� Ȥ�� �̸��� ���޹޾� �ش��ϴ� ȸ���� ��ȯ�Ѵ�.
	 * @param info ȸ���� �˻��� ī�װ��� ���̵����� �̸�����
	 * @param data �ش��ϴ� ���̵� Ȥ�� �̸��� ����
	 * @return ���̵� �̸��� �ش��ϴ� ȸ�����(�̸��� ��� �ߺ��� ���ɼ��� �ֱ� ������) String[] �� ������ ���̵�, �̸�, ����, ����
	 *
	 */
	public List<String[]> search(String info, String data){
		List<Userable> userList= new LinkedList<Userable>();
		List<String[]> returnList = new LinkedList<String[]>();
		if(info.equals(MemberInfo.ID)){
			userList = manager.search(MemberInfo.ID, data);
			TheOasisMember temp = (TheOasisMember)userList.get(0);
			String arr[] = {temp.getId(), temp.getName(), temp.getBirthday().toString(), temp.getProfile().getMainImage()};
			returnList.add(arr.clone());
			return returnList;
		}
		else if(info.equals(MemberInfo.NAME)){
			userList = manager.search(MemberInfo.NAME, data);
			for(Userable member : userList){
				TheOasisMember temp = (TheOasisMember)member;
				String arr[] = {temp.getId(), temp.getName(), temp.getBirthday().toString(), temp.getProfile().getMainImage()};
				returnList.add(arr.clone());
			}
			return returnList;
		}
		return null;
	}
	
	/**
	 * 
	 * ȸ���� ���̵� ���޹޾� �ش��ϴ� ȸ���� Ż���Ų��.
	 * @param id Ż���Ϸ��� ȸ���� ���̵�
	 */
	public void remove(String id){
		manager.remove(id);
	}
	
	/**
	 * ȸ���� ���� ������ ���޹޾� �ش��ϴ� ȸ���� Ż���Ų��.
	 * �������ڷδ� member�� ���� ���̵�, ��й�ȣ, �̸��� �޴´�.
	 * @param member ȸ���� ����
	 */
	public void remove(String[] member){
		Userable user = new Member(member[0],member[1],member[2]);
		manager.remove(user);
	}
	
	/**
	 * ȸ���� ���̵�� ��й�ȣ�� �޾� ��й�ȣ�� �����Ѵ�.
	 * @param id ��й�ȣ�� ������ ȸ���� ���̵�
	 * @param password ������ ��й�ȣ
	 */
	public void modify(String id, String password){
		manager.modify(id, password);
	}
	
	/**
	 * ȸ���� ���̵�� �����Ϸ��� ȸ���� ������ ���޹޾� �ش� ȸ���� �����Ѵ�.
	 * ���̵��  ������ ������, ������ ����, ������ ���������� String[]�� �޴´�.
	 * @param id ȸ���� ���̵�
	 * @param member �����Ϸ��� �ϴ� ������
	 */
	public void modify(String id, String[] member){
		if(member[2].equals(PrivacyInfo.PUBLIC)){
		manager.modifyProfile(id, member[0], member[1], PrivacyInfo.PUBLIC);
		}
		else if(member[2].equals(PrivacyInfo.PRIVATE)){
			manager.modifyProfile(id, member[0], member[1], PrivacyInfo.PRIVATE);
		}
		else{
			manager.modifyProfile(id, member[0], member[1], PrivacyInfo.FRIEND);
		}
	}

	/**
	 * ȸ���� ���̵� ���޹޾� �ش��ϴ� ȸ���� �����ϴ��� �˻��Ѵ�.
	 * @param id ȸ���� ���̵�
	 * @return ���� ����
	 */
	public boolean check(String id){

		return manager.check(id);
	}
	
	/**
	 * ȸ���� ���̵�� ��й�ȣ�� ���޹޾� �ش��ϴ� ȸ���� �����ϴ��� �˻��Ѵ�.
	 * @param id ȸ���� ���̵�
	 * @param password ȸ���� ��й�ȣ
	 * @return ���� ����
	 */
	public boolean check(String id, String password){
		return manager.check(id, password);
	}
	
	/**
	 * ȸ���� ���̵�� ��й�ȣ�� ���޹޾� ȸ������ �Ѵ�.
	 * @param id ȸ���� ���̵�
	 * @param password ȸ���� ��й�ȣ
	 */
	public void add(String id, String name, String password){
		manager.add(id, name, password);
		GroupController.getInstance().getManager().put(id);
		LanguageBuddyController.getInstance().put(id);
		GroupController.getInstance().putStandByFriend(id);
		GroupController.getInstance().putStandByGroup(id);
	}
	
	/**
	 * ȸ���� �������� ���޹޾� ȸ������ �Ѵ�.
	 * id, password, name, birthday, phoneNumber, question, answer 
	 * @param member ȸ���� ������
	 * @return ����, ���� ����
	 */
	public void add(String[] member) {

		GroupController.getInstance().getManager().put(member[0]);
		StringTokenizer token = new StringTokenizer(member[4], "-");
		GregorianCalendar birthday = new GregorianCalendar(
				Integer.parseInt(token.nextToken()), Integer.parseInt(token
						.nextToken()) - 1, Integer.parseInt(token.nextToken()));
		TheOasisMember mem = new TheOasisMember(member[0], member[1], member[2],
				birthday, member[4], member[5], member[6]);
		ProfileIO.exportProfile(mem);
		manager.add(mem);
		GroupController.getInstance().getManager().put(member[0]);
		LanguageBuddyController.getInstance().put(member[0]);
		GroupController.getInstance().putStandByFriend(member[0]);
		GroupController.getInstance().putStandByGroup(member[0]);
	}

    /**
     * ȸ���� �����ϴ� �Ŵ����� ��ȯ�Ѵ�.
     * @return ȸ�� ���� ������ �ϴ� ��ü
     */
	public MemberManagement getManager() {
		return manager;
	}

	/**
	 * ȸ���� �����ϴ� �Ŵ��� ��ü�� �����Ѵ�.
	 * @param manager ȸ�� ���� ������ �ϴ� ��ü
	 */
	public void setManager(MemberManagement manager) {
		this.manager = manager;
	}
	public TheOasisMember search(String id)
	{
		return (TheOasisMember)manager.getMList().get(MemberInfo.ID, id).get(0);
	}
}
