package com.theOasis.member;

import java.util.*;
/**
 * 
 * ȸ������ ������ ��Ƽ� ������ �� �ִ� �ڷᱸ������ ����� ���� �ִ� Ŭ����
 * ȸ���� ������ ��� ������ �� ������ List �ڷᱸ������ ���Ұ� ���� ��Ͽ� ȸ���� ���� �߰� , ȸ���� �����ִ��� üũ,
 * ����� ������, ��Ͽ� �ִ� �����͸� ��� ������ ���� ������ �Ѵ�.
 * @author jisu
 *
 */
public class MemberList {
	
	/**
	 * ȸ���� �������� �����س��� �� �ִ� List(ģ�����, �׷�)
	 */
	private List<Userable> memberList;
	
	/**
	 * ������
	 */
	public MemberList(){
		this(new LinkedList<Userable>());
	}
	
	/**
	 * ȸ������� ���޹޾� ��ü�� �����ϴ� ������
	 * @param memberList
	 */
	public MemberList(List<Userable> memberList){
		this.memberList = memberList;
	}
	/**
	 * ���̵� ���޹޾� ��Ͽ��� ã�Ƽ� �����Ѵ�.
	 * @param id ȸ�� ���̵�
	 */
	public void remove(String id){
		memberList.remove(get(MemberInfo.ID,id).get(0));
	}
	/**
	 * Userable ��ü�� �޾� ��Ͽ��� ã�Ƽ� �����Ѵ�.
	 * @param member ȸ�� ��ü
	 */
	public void remove(Userable member){
		memberList.remove(((Member)member).getName());
	}
	
	/**
	 * index�� ���޹޾� �ش��ϴ� ȸ���� ��Ͽ��� �����Ѵ�.
	 * @param index List�� index
	 */
	public void remove(int index){
		memberList.remove(index);
	}
	

	/**
	 * ID�� NAME�� ���޹޾� ID�� NAME�� ���ԵǴ� ȸ���� ���� �� ��Ͽ� ��� ��ȯ�Ѵ�.
	 * @param info ID�� Name enum ����
	 * @param data �ش��ϴ� ������
	 * @return ȸ�����
	 */
	public List<Userable> get(MemberInfo info, String data){
		List<Userable> tempList = new LinkedList<Userable>();
		if(info==MemberInfo.ID){
			for(Userable temp : memberList){
				User user = (User)temp;
				if(user.getId().equals(data)){ 
					tempList.add(temp);
				}
			}
			return tempList;
		}
		else if(info == MemberInfo.NAME){
			for(Userable temp : memberList){
				Member user = (Member)temp;
				if(user.getName().contains(data)){
					tempList.add(temp);
				}
			}
			return tempList;
		}
		return null; //���� ��
	}
	
	/**
	 * ID, �̸�, ��й�ȣ�� ���޹޾� �ش��ϴ� ȸ���� ��Ͽ� ���� �� ȸ���� ��Ͽ� �߰��Ѵ�.
	 * @param id ���̵�
	 * @param name �̸�
	 * @param password ��й�ȣ
	 * @return ����,���п���
	 */
	public boolean add(String id, String name, String password){
		if(!contains(id)){
			Userable user = new Member(id,name,password);
			memberList.add(user);
			return true;
		}
		else{
			return false;
		}
	}
	
	/**
	 * ȸ���� ���޹޾� �ش��ϴ� ȸ���� ��Ͽ� ���� �� �ְ��Ѵ�.
	 * @param member ȸ����ü
	 * @return ����,���п���
	 */
	public boolean add(Userable member){
		if(!contains(member)){
			memberList.add(member);
			return true;
		}
		else{
			return false;
		}
	}
	
	/**
	 * ���̵�� ȸ�� ��ü�� ���޹޾� ���̵� �ش��ϴ� ȸ���� ���޹��� ȸ������ set�Ѵ�.
	 * @param id ���̵�
	 * @param member ȸ����ü
	 */
	public void set(String id, Userable member){
		for(Userable temp : memberList){
			User user = (User)temp;
			if(user.getId().equals(id)){
				memberList.set(memberList.indexOf(temp),member);
			}
		}
		//���� ��
	}
	/**
	 * �����ϰ��� �ϴ� ȸ���� ã�Ƽ� �ٸ� ȸ������ set�Ѵ�.
	 * @param oldMember �����ϰ��� �ϴ� ȸ��
	 * @param newMember ���Ӱ� �����Ǿ��� ȸ��
	 */
	public void set(Userable oldMember, Userable newMember){
		for(Userable temp : memberList){
			User user = (User)temp;
			if(user.equals(oldMember)){
				memberList.set(memberList.indexOf(temp), newMember);
			}
		}
		//���� ��
	}
	
	/**
	 * ȸ�� ��ü�� ���޹޾� �ش��ϴ� List�� index�� ��ȯ�Ѵ�.
	 * @param member ȸ����ü
	 * @return List�� index
	 */
	public int indexOf(Userable member){
		return memberList.indexOf(member);
	}
	
	/**
	 * ���̵� ���޹޾� �ش��ϴ� List�� index�� ��ȯ�Ѵ�.
	 * @param id ã���� �ϴ� ȸ���� ���̵�
	 * @return List�� index
	 */
	public int indexOf(String id){
		for(Userable temp : memberList){
			if(temp.getId().equals(id)){
				return memberList.indexOf(temp);
			}
		}
		return -1;//���� �� 
	}
	/**
	 * ȸ�� ��ü�� ���޹޾� �ش��ϴ� ȸ���� �ִ��� �˻��Ѵ�.
	 * @param member ȸ�� ��ü
	 * @return ����,���п���
	 */
	public boolean contains(Userable member){
		for(Userable temp : memberList){
			if(temp.getId().equals(member.getId())){
				return true;
			}
		}
		return false;
	}
	
	/**
	 * ȸ�� ���̵� ���޹޾� �ش��ϴ� ȸ���� �ִ��� �˻��Ѵ�.
	 * @param id ȸ�� ���̵�
	 * @return ����,���п���
	 */
	public boolean contains(String id){
		for(Userable temp : memberList){
			User user = (User)temp;
			if(user.getId().equals(id)){
				return true;
			}
		}
		return false;
	}
	
	/**
	 * ȸ�� ����� ��ȯ�Ѵ�.
	 * @return ȸ�� ���
	 */
	public List<Userable> getMemberList(){
		return memberList;
	}
	
	
	/**
	 * ȸ�� ����� �����Ѵ�.
	 * @param memberList ȸ�� ���
	 */
	public void setMemberlIst(List<Userable> memberList){
		this.memberList = memberList;
	}
	
	/**
	 * List�� size�� ��ȯ�Ѵ�.
	 * @return List�� size
	 */
	public int size(){
		return memberList.size();
	}
	
	/**
	 * List�� ������ ��� �����Ѵ�.
	 */
	public void clear(){
		memberList.clear();
	}

	@Override
	public String toString() {
		return "MemberList [memberList=" + memberList + "]";
	}
	
	public static void main(String args[]){
		MemberList list = new MemberList();
		Userable temp = new Member("kjs","1234","������");
		Userable temp1 = new Member("kjs","1234","������");
		Userable temp2 = new Member("kyw","1234","�迹��");
		Userable temp3 = new Member("hyj","3333","������");
		list.add(temp);
		list.add(temp1);
		list.add(temp2);
		list.add(temp3);
		list.add("jhc","2222","����ö");
		System.out.println(list);
		System.out.println("------------------add Test-------------------"+"\n");
		list.remove("kjs");
		list.remove(temp2);
		System.out.println(list);
		System.out.println("------------------remove Test-------------------"+"\n");
		System.out.println((list.get(MemberInfo.ID,"kjs")));
		System.out.println((list.get(MemberInfo.ID,"jhc")));
		//System.out.println((list.get("kyw")));
		System.out.println("------------------get Test-------------------");
		list.set("jhc", new Member("ksa","2222","����"));
		list.set(temp3, new Member("pjs","3333","������"));
		System.out.println(list);
		System.out.println("------------------set Test-------------------");
		System.out.println(list.indexOf(temp3)+"�������� ���� �� -1 ���� ����");
		System.out.println(list.indexOf("pjs"));
		System.out.println("------------------indexOf Test-------------------");
		System.out.println(list.contains("pjs"));
		System.out.println(list.contains("kjs"));
		System.out.println("------------------contains Test-------------------");
		System.out.println(list.size());
		System.out.println("------------------size Test-------------------");
		list.clear();
		System.out.println(list);
		System.out.println("------------------clear Test-------------------");
		
	}
}
