package com.theOasis.member;

import java.util.*;

/**
 * ȸ���� ���� �ִ� �׷��� ��� �����ֱ� ���� �׷� ����� �ٷ�� Ŭ����
 * @author jisu
 *
 */
public class GroupList {
	
	/**
	 * �׷���
	 */
	private List<MemberList> groupList;
	
	/**
	 * ������
	 */
	public GroupList(){
		groupList = new LinkedList<MemberList>();
	}
	
	/**
	 * �׷� ����� �޾Ƽ� �׷����� ����
	 * @param groupList
	 */
	public GroupList(List<MemberList> groupList){
		this.groupList = groupList;
	}
	
	/**
	 * �׷��� ���޹޾� �׷� ��Ͽ� �߰��Ѵ�.
	 * @param group �׷�
	 */
	public void add(MemberList group){
		if(group!=null){
		if(!contains(group)){
		groupList.add(group);
		}
		//���� ���� ����
		}
	}
	
	/**
	 * �׷� �̸��� ���޹޾� �׷� ��Ͽ� �߰��Ѵ�.
	 * @param groupName �׷��
	 */
	public void add(String groupName){
		if(groupName!=null){
		if(!contains(groupName)){
		groupList.add(new Group(groupName));
		//���� ���� ����
		}
		}
	}
	
	/**
	 * �׷� �̸��� ģ������ ���޹޾� �׷��� ����� �׷��Ͽ� �߰��Ѵ�.
	 * @param groupName �׷��
	 * @param friends �׷쿡 �߰��� ģ����
	 */
	public void add(String groupName, List<Userable> friends){
		if(groupName!=null){
		if(!contains(groupName)){
		groupList.add(new Group(friends, groupName));
		//���� ���� ����
		    }
		else{
			for(Userable user : friends){
				get(groupName).add(user);
				}
			}
		}
	}
	
	/**
	 * �׷��̸����� �׷��� ã�� ���޹��� ģ���� �ʴ��Ѵ�.
	 * @param groupName ģ���� �ʴ��� �׷��
	 * @param friend �׷쿡 �ʴ��� ģ��
	 */
	public void add(String groupName, Userable friend){
		if(friend!=null){
			if(get(groupName)!=null){
				if(!contains(groupName)){
		get(groupName).add(friend);
		//���� ���� ����
		}
			}
		}
	}
	/**
	 * �׷��� ���޹޾� �׷��Ͽ��� ã�� �� �����Ѵ�.
	 * @param group �׷� ��ü
	 */
	public void remove(MemberList group){
		groupList.remove(((Group)group).getGroupName());
		//���� ���� ����
	}
	
	/**
	 * �����ϰ� ���� index�� ���޹޾� �׷��Ͽ��� ã�� �� �����Ѵ�.
	 * @param index List�� index
	 */
	public void remove(int index){
		groupList.remove(index);
		//���� ���� ����
	}
	
	/**
	 * �����ϰ� ���� �׷���� ���޹޾� �׷��Ͽ��� ã�� �� �����Ѵ�.
	 * @param groupName �׷��
	 */
	public void remove(String groupName){
		groupList.remove(get(groupName));
		//���� ���� ����
	}
	
	/**
	 * �׷��� ���޹޾� �ش� �׷��� �̸��� �׷� ��Ͽ� �����ϴ��� ���θ� �˸���.
	 * @param group �̸��� ���� ���θ� Ȯ�� �� �׷�
	 * @return ���� ����
	 */
	public boolean contains(MemberList group){
		for(MemberList list : groupList){
			if(((Group)list).getGroupName().equals(((Group)group).getGroupName())){
				return true;
			}
		}
		return false;
	}
	
	/**
	 * �׷� �̸��� ���޹޾� �ش� �׷��� �̸��� �׷� ��Ͽ� �����ϴ��� ���θ� �˸���.
	 * @param groupName �̸��� ���� ���θ� Ȯ�� �� �׷� ��
	 * @return ���� ����
	 */
	public boolean contains(String groupName){
		for(MemberList list : groupList){
			if(((Group)list).getGroupName().equals(groupName)){
				return true;
			}
		}
		return false;
	}
	/**
	 * �׷���� ���޹޾� �׷��Ͽ��� �ش��ϴ� �׷��� ��ȯ�Ѵ�.
	 * @param groupName �׷��
	 * @return �׷찴ü
	 */
	public MemberList get(String groupName){
		for(MemberList temp : groupList){
			Group group = (Group)temp;
			if(group.getGroupName().equals(groupName)){
				return temp;
			}
		}
		return null;
	}
	
	/**
	 * index�� ���޹޾� �׷��Ͽ��� �ش��ϴ� �׷��� ��ȯ�Ѵ�.
	 * @param index List�� index
	 * @return �׷� ��ü
	 */
	public MemberList get(int index){
		return groupList.get(index);
	}
	
	/**
	 * �׷����� ��� �����Ѵ�.
	 */
	public void clear(){
		groupList.clear();
		
	}
	
	/**
	 * �׷����� ũ�⸦ ��ȯ�Ѵ�.
	 * @return ������ ����
	 */
	public int size(){
		return groupList.size();
	}
	
	/**
	 * �׷� ����� ��ȯ�ϴ� �޼ҵ�
	 * @return �׷� ����� ��ȯ
	 */
	public List<MemberList> getGroupList(){
		return groupList;
	}
	
	/**
	 * �׷� ����� �����ϴ� �޼ҵ�
	 * @param groupList �׷� ���
	 */
	public void setGroupList(List<MemberList> groupList){
		this.groupList = groupList;
	}

	@Override
	public String toString() {
		return "GroupList [groupList=" + groupList + "]";
	}
	
	public static void main(String args[]){
		GroupList groupList = new GroupList();
		groupList.add("lec",new Member("kjs","1234","������"));
		MemberList group3 = new Group("lec3");
		groupList.add(group3);
		MemberList group = new Group();
		Userable temp = new Member("kjs","1234","������");
		Userable temp2 = new Member("kyw","1234","�迹��");
		Userable temp3 = new Member("hyj","3333","������");
		group.add(temp);
		group.add("jhc","2222","����ö");
		groupList.add("lec", group.getMemberList());
		//System.out.println(groupList);
		
		MemberList group2 = new Group();
		group2.add(temp2);
		group2.add(temp);
		groupList.add("lec",group2.getMemberList());
		System.out.println(groupList);
		groupList.add("lec2",group2.getMemberList());
		System.out.println(groupList);
		System.out.println("----------------------------�׷� �߰� Test ---------------------------");
		groupList.remove("lec2");
		groupList.remove(group3);
		System.out.println(groupList);
		System.out.println("----------------------------�׷� ���� Test ---------------------------");
		
		System.out.println(groupList.get("lec"));
		System.out.println("----------------------------�׷� get Test ---------------------------");
	}
}
