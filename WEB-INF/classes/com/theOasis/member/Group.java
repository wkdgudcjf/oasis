package com.theOasis.member;


import java.util.*;
/**
 * 
 * ���� ���� �����ϰ� �̾߱� �� �� �ֵ��� ���ϴ� ȸ������ ������ ���� �׷��� ����� �ִ� Ŭ����
 * @author jisu
 *
 */
public class Group extends MemberList {
	
	/**
	 * �׷� �̸�
	 */
	private String groupName;
	
	/**
	 * ������
	 */
	public Group(){
		
	}
	
	/**
	 * ������
	 * @param memberList
	 */
	public Group(List<Userable> memberList){
		super(memberList);
	}
	/**
	 * 
	 * �׷��� ����⸦ ���ϴ� ȸ������ ����Ʈ�� �׷���� ���޹޾� �׷��� �����Ѵ�.
	 * @param memberList
	 * @param groupName
	 */
	public Group(List<Userable> memberList, String groupName){
		super(memberList);
		this.groupName = groupName;
	}
	
	/**
	 * �׷� ���� ���޹޾� �׷� ��ü�� �����Ѵ�.
	 * @param groupName �׷��� ���� �� ������ �׷� ��
	 */
	public Group(String groupName){
		super();
		this.groupName = groupName;
	}
	/**
	 * �׷� ���� ��ȯ�ϴ� �޼ҵ�
	 * @return groupName �׷��� �̸��� ��ȯ
	 */
	public String getGroupName(){
		return groupName;
	}
	
	/**
	 * �׷� ���� �����ϴ� �޼ҵ�
	 * @param groupName �׷��� �̸��� ���޹޾� �����Ѵ�.
	 */
	public void setGroupName(String groupName){
		this.groupName = groupName;
	}

	@Override
	public String toString() {
		return super.toString() + "Group [groupName=" + groupName + "]";
	}
	
	
}
