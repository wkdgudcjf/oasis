package com.theOasis.controller;

import java.util.*;

import com.theOasis.member.*;

/**
 * 
 * Ŭ���̾�Ʈ�� ��ŸŰ�ü ������ �ϴ� Ŭ����. �׷��� �����ϴ� �Ŵ����� ����� ���� ������, �� ��ü�� �������� ��ȯ�ϴ� ������ �Ѵ�.
 * ���� ģ����û�� ���� �� ��� ��ų �� �ִ� ����� ���� �ִ�.
 * 
 * �⺻������ ��� �Ķ���ʹ� ���ڿ� �����ͷ� ���Ӽ��� �ٿ��ش�. �׷��� ���,�˻�,����,������ �� �� ������,���� �׷쿡 ģ�� �߰�, ģ��
 * ������ �� �� �ִ�. �׷��� �ƴ� ģ�� ����� ��� default�� �⺻�׷쿡 ��ϵȴ�.
 * 
 * @author jisu
 * 
 */
public class GroupController {

	/**
	 * �׷����
	 */
	private GroupManagement manager;
	/**
	 * singleton
	 */
	private static GroupController groupCon = new GroupController();
	/**
	 * ģ����û �����
	 */
	private HashMap<String, List<String>> standByFriend;
	/**
	 * �׷� ��û ��� ��� �׷� List<String>�� String�� �׷� ���� �ǹ��Ѵ�. String[]�� 0 ��° ���� ���� �����
	 * id, 1��° ���� �׷��
	 */
	private HashMap<String, List<String[]>> standByGroup;

	/**
	 * default������
	 */
	public GroupController() {
		manager = new GroupManagement();
		standByFriend = new HashMap<String, List<String>>();
		standByGroup = new HashMap<String, List<String[]>>();

	}

	/**
	 * ������
	 * 
	 * @param manager
	 * @param standByFriend
	 */
	public GroupController(GroupManagement manager,
			HashMap<String, List<String>> standByFriend,
			HashMap<String, List<String[]>> standByGroup) {
		this.manager = manager;
		this.standByFriend = standByFriend;
		this.standByGroup = standByGroup;
	}

	public static GroupController getInstance() {
		return groupCon;
	}

	/**
	 * ȸ���� ���̵�� ã������ ģ���� ������ ���޹޾� ã�� ����� ��ȯ�Ѵ�.
	 * 
	 * @param id
	 *            ȸ���� ���̵�
	 * @param info
	 *            ã������ ģ���� ������ ID���� NAME������ ����
	 * @param data
	 *            ã������ ģ���� ����
	 * @return ã�� ģ���� ����
	 */
	/*
	 * public List<String[]> searchFriend(String id, String info, String data){
	 * return null; }
	 */

	/**
	 * ȸ���� ���̵� ���޹޾� ȸ���� �׷��� ã�Ƽ� ����� ��ȯ�Ѵ�.
	 * 
	 * @param id
	 *            ȸ���� ���̵�
	 * @return ȸ���� �׷���ü
	 */
	/*
	 * public HashMap<String, List<String[]>> searchGroup(String id){ return
	 * null; }
	 */
	/**
	 * ȸ���� ���̵�� �׷���� ���޹޾� �ش��ϴ� �׷��� ã�Ƽ� ����� ��ȯ�Ѵ�.
	 * 
	 * @param id
	 *            ȸ���� ���̵�
	 * @param groupName
	 *            ã������ �׷��� �̸�
	 * @return �ش��ϴ� �׷�
	 */

	/*
	 * public List<String[]> searchGroup(String id, String groupName){ return
	 * null; }
	 */

	/**
	 * ȸ���� ���̵�� �׷��� ���޹޾� ȸ���� �׷쿡 �߰��Ѵ�.
	 * 
	 * @param id
	 *            ȸ�� ���̵�
	 * @param group
	 *            �߰��Ϸ��� �׷�
	 * @return ����, ���п���
	 */
	/*
	 * public boolean addGroup(String id, List<String> group){ return true; }
	 */

	/**
	 * ȸ���� ���̵�� �׷���� ���޹޾� ȸ���� �׷쿡 �߰��Ѵ�.
	 * 
	 * @param id
	 *            ȸ�� ���̵�
	 * @param groupName
	 *            �߰��Ϸ��� �׷��
	 * @return ����,���� ����
	 */
	/*
	 * public boolean addGroup(String id, String groupName){ return true; }
	 */

	/**
	 * ȸ�� ���̵�� �׷��, �׷쿡 �߰��� ģ������ ���޹޾� �׷��� ����� ���̵� �ش��ϴ� ȸ���� �׷��Ͽ� �߰��Ѵ�.
	 * 
	 * @param id
	 *            ȸ���� ���̵�
	 * @param groupName
	 *            �߰��� �׷��
	 * @param friends
	 *            �߰��� �׷쿡 �ʴ��� ģ����
	 * @return ����, ���� ����
	 */
	/*
	 * public boolean addGroup(String id, String groupName, List<String>
	 * friends){ return true; }
	 */

	/**
	 * ���̵�� �׷��� ���޹޾� �ش��ϴ� ���̵��� �ش��ϴ� �׷��� �����Ѵ�.
	 * 
	 * @param id
	 *            ȸ�� ���̵�
	 * @param group
	 *            ������ �׷�
	 * @return ����, ���� ����
	 */
	/*
	 * public boolean removeGroup(String id, List<String> group){ return true; }
	 */

	/**
	 * ���̵�� �׷���� ���޹޾� ���̵� �ش��ϴ� ȸ���� �׷��Ͽ��� �ش��ϴ� �׷���� �׷��� �����Ѵ�.
	 * 
	 * @param id
	 *            ȸ���� ���̵�
	 * @param groupName
	 *            �����Ϸ��� �׷��
	 * @return ����, ���� ����
	 */
	/*
	 * public boolean removeGroup(String id, String groupName){ return true; }
	 */

	/**
	 * ȸ���� ���̵�� �׷��, �׷쿡 �߰��� ģ���� ���޹޾� �ش��ϴ� ȸ���� �׷쿡 ģ���� �߰��Ѵ�.
	 * 
	 * @param id
	 *            ȸ���� ���̵�
	 * @param groupName
	 *            ģ���� �߰��� �׷��
	 * @param friend
	 *            �߰��� ģ��
	 * @return ����, ���� ����
	 */
	/*
	 * public boolean addFriend(String id, String groupName, String[] friend){
	 * return false; }
	 */

	public void requestFriend(String id, String friendId) {
		//MemberController.getInstance().setManager(new MemberManagement());
		MemberManagement tempM = MemberController.getInstance().getManager();
		List<Userable> me = tempM.search(MemberInfo.ID, id);
		List<Userable> friend = tempM.search(MemberInfo.ID, friendId);
		if (!(manager.containsFriend(me.get(0), friend))) {
			if (standByFriend.containsKey(friendId)) {
				if (standByFriend.get(friendId).contains(id)) {
					return;// �̹� ģ�� ��û�� ģ��
				}
				standByFriend.get(friendId).add(id);
			} else {
				standByFriend.put(friendId, new LinkedList<String>());
				standByFriend.get(friendId).add(id);
			}
		}
	}

	/**
	 * ���� ģ�� ��û ��Ͽ��� ���� �ϰų� �����ϴ� �޼ҵ� �����ҽ� addFriend�޼ҵ带 ȣ�� �Ͽ� ģ�� �߰� �� ��û��Ͽ���
	 * �����Ѵ�.
	 * 
	 * @param id
	 * @param friendId
	 * @param tf
	 */
	public void responseFriend(String id, String friendId, boolean tf) {
		MemberManagement tempM = MemberController.getInstance().getManager();
		List<Userable> me = tempM.search(MemberInfo.ID, id);
		List<Userable> friend = tempM.search(MemberInfo.ID, friendId);
		if (tf == true) {
			manager.addFriend(me.get(0), friend.get(0));
			standByFriend.get(id).remove(friendId);
		} else if (tf == false) {
			standByFriend.get(id).remove(friendId);
		}
	}

	/**
	 * �ش� ���̵��� ģ������ groupName�� �ش��ϴ� �׷��û�� �Ѵ�. �ش� ���̵��� ģ������ �׷� ��û ����� �������� ���� ��
	 * put�޼ҵ带 ȣ���� ����Ʈ�� ������ �� �ڿ� �߰��Ѵ�. me�� �׷쿡 �ʴ� ��û�� ���� �ڽ�, friendId�� �׷쿡 �ʴ��ϱ⸦
	 * ���ϴ� ģ�� id, groupName�� ģ���� �ʴ��ϰ��� �ϴ� �׷� ��
	 * 
	 * @param me
	 *            �׷쿡 �ʴ� ��û�� ���� �ڽ�
	 * @param friendId
	 *            �׷쿡 �ʴ��ϱ⸦ ���ϴ� ģ��
	 * @param groupName
	 *            ģ���� �ʴ��ϰ��� �ϴ� �׷� ��
	 */
	public void requestGroup(String id, String friendId, String groupName) {
		// MemberManagement tempM = MemberController.getInstance().getManager();
		// List<Userable> me = tempM.search(MemberInfo.ID, id);
		// List<Userable> friend = tempM.search(MemberInfo.ID, friendId);
		//System.out.println(manager.searchGroup(friendId, groupName));
		
		if (manager.getGroupList().get(friendId).get(groupName)==null) { //ģ���� �ʴ��ϰ��� �ϴ�
																		 //�׷��� ģ���� �ƴ� �ÿ��� �ʴ� ����
			if (standByGroup.containsKey(friendId)) {
				if (standByGroup.get(friendId).contains(groupName)) {
					return;// �̹� ģ�� ��û�� �׷�
				}
				standByGroup.get(friendId).add(new String[] { id, groupName });
			} else {
				standByGroup.put(friendId, new LinkedList<String[]>());
				manager.addGroup(MemberController.getInstance().getManager().search(MemberInfo.ID, id).get(0), groupName);
				standByGroup.get(friendId).add(new String[] {id, groupName});
				
			}
		}
	}
	
	/**
	 * ���� ģ�� ��û ����� ��ȯ�Ѵ�.
	 * 
	 * @param id
	 *            ģ�� ��û�� ���� �;� �ϴ� ȸ���� ���̵�
	 * @return ���� ģ�� ��û ���
	 */
	public List<String> getMyStandByFriend(String id) {
		return standByFriend.get(id);
	}

	/**
	 * �׷� ��û ��Ͽ� �ִ� ����߿� �ڽ��� �����ϰ��� �ϴ� �׷� �ʴ뿡 ���� ������ �Ѵ� id�� �����ϴ� �ڽ�, receiver��
	 * �ʴ��� ȸ���� ���̵�, groupName�� �ʴ���� �׷���̸�, tf�� ���� ���� üũ�̴�.
	 * 
	 * @param id
	 *            �����ϴ� �ڽ��� ���̵�
	 * @param receiver
	 *            �ʴ��� ȸ���� ���̵�
	 * @param groupName
	 *            �ʴ���� �׷��
	 * @param tf
	 *            ���� ���� ����
	 */
	public void responseGroup(String id, String receiver, String groupName,
			boolean tf) {
		MemberManagement tempM = MemberController.getInstance().getManager();
		List<Userable> me = tempM.search(MemberInfo.ID, id);
		if (tf == true) {
			Group temp = (Group) manager.searchGroup(receiver, groupName);
			manager.addGroup(me.get(0), temp);
			for (int i = 0; i < standByGroup.get(id).size(); i++) {
				if (standByGroup.get(id).get(i)[1].equals(groupName)
						&& standByGroup.get(id).get(i)[0].equals(receiver)) {
					standByGroup.get(id).remove(i);
				}
			}
		} else {
			for (int i = 0; i < standByGroup.get(id).size(); i++) {
				if (standByGroup.get(id).get(i)[1].equals(groupName)
						&& standByGroup.get(id).get(i)[0].equals(receiver)) {
					standByGroup.get(id).remove(i);
				}
			}
		}
	}

	public List<String[]> getMyStandByGroup(String id) {
		return standByGroup.get(id);
	}
	
	public void putStandByFriend(String id){
		standByFriend.put(id, new LinkedList<String>());
	}
	public void putStandByGroup(String id){
		standByFriend.put(id, new LinkedList<String>());
	}

	/**
	 * ȸ���� ���̵�� �׷��, �׷쿡�� ������ ģ���� ���޹޾� �ش��ϴ� ȸ���� �׷쿡�� ģ���� �����Ѵ�.
	 * 
	 * @param id
	 *            ȸ���� ���̵�
	 * @param groupName
	 *            ģ���� ������ �׷��
	 * @param friend
	 *            ������ ģ��
	 * @return ����, ���� ����
	 */
	/*
	 * public boolean removeFriend(String id, String groupName, String[]
	 * friend){ return true; }
	 */

	/**
	 * ȸ���� ���̵�� �׷��, ģ���� ���̵� ���޹޾� �ش��ϴ� ȸ���� �׷��� �׷�� �ش��ϴ� �׷��� ã��, �� �׷쿡�� ģ����
	 * ���̵� ã�� �����Ѵ�.
	 * 
	 * @param id
	 *            ȸ���� ���̵�
	 * @param groupName
	 *            ģ���� �����Ϸ��� �׷��
	 * @param friendId
	 *            �����Ϸ��� ģ�����̵�
	 * @return ����, ���� ����
	 */
	/*
	 * public boolean removeFriend(String id, String groupName, String
	 * friendId){ return true; }
	 */

	/**
	 * ȸ���� ���̵�� ������ ģ���� ���̵� ���޹޾� �ش��ϴ� ģ���� �� ģ����Ͽ��� �����Ѵ�.
	 * 
	 * @param id
	 *            ȸ���� ���̵�
	 * @param friendId
	 *            �����Ϸ��� ģ���� ���̵�
	 * @return ����, ���� ����
	 */
	/*
	 * public boolean removeFriend(String id, String friendId){ return true; }
	 */
	/**
	 * ȸ���� ���̵� ���޹޾� �ش��ϴ� ȸ���� ģ�� ��û ������� ��ȯ�Ѵ�.
	 * 
	 * @param id
	 *            ȸ���� ���̵�
	 * @return �ش� ȸ���� ģ�� ��û ��� ���
	 */
	/*
	 * public List<String> getStandBy(String id){ return null; }
	 */

	/**
	 * �׷��� �����ϴ� �Ŵ����� ��ȯ�Ѵ�.
	 * 
	 * @return �׷� ���� �Ŵ���
	 */
	public GroupManagement getManager() {
		return manager;
	}

	/**
	 * �׷��� �����ϴ� �Ŵ����� �����Ѵ�.
	 * 
	 * @param manager
	 *            �׷� ���� �Ŵ���
	 */
	public void setManager(GroupManagement manager) {
		this.manager = manager;
	}

	/**
	 * ȸ���� ģ�� ��û ��� ����� ��ȯ�Ѵ�.
	 * 
	 * @return ģ����û �����
	 */
	public HashMap<String, List<String>> getStandBy() {
		return standByFriend;
	}

	/**
	 * ȸ���� ģ�� ��û ������� �����Ѵ�.
	 * 
	 * @param standByFriend
	 *            ģ�� ��û ��� ���
	 */
	public void setStandBy(HashMap<String, List<String>> standByFriend) {
		this.standByFriend = standByFriend;
	}

	public HashMap<String, List<String>> getStandByFriend() {
		return standByFriend;
	}

	public void setStandByFriend(HashMap<String, List<String>> standByFriend) {
		this.standByFriend = standByFriend;
	}

	public HashMap<String, List<String[]>> getStandByGroup() {
		return standByGroup;
	}

	public void setStandByGroup(HashMap<String, List<String[]>> standByGroup) {
		this.standByGroup = standByGroup;
	}

	@Override
	public String toString() {
		return "GroupController [manager=" + manager + ", standByFriend="
				+ standByFriend + ", standByGroup=" + standByGroup + "]";
	}

	public static void main(String args[]) {

		GroupController groupCon = GroupController.getInstance();
		GroupManagement manager = GroupController.getInstance().getManager();
		/* �׷� �Ŵ����� ���� */

		MemberController.getInstance().setManager(new MemberManagement());
		MemberManagement tempM = MemberController.getInstance().getManager();
		
		Userable temp1 = new Member("kjs", "1234", "������");
		Userable temp2 = new Member("kyw", "2222", "�迹��");
		Userable temp3 = new Member("jhc", "3333", "����ö");
		Userable temp4 = new Member("hyj", "5555", "������");
		/* ȸ�� 3���� ���� */
		
		tempM.add(temp1);
		tempM.add(temp2);
		tempM.add(temp3);
		tempM.add(temp4);

		manager.put("kjs");
		manager.put("kyw");
		manager.put("jhc");
		manager.put("hyj");
		/* ȸ�� ���� �� �׷� ����� �Ҵ���� �� */

		manager.addFriend(temp1, temp2);
		manager.addFriend(temp1, temp3);
		manager.addFriend(temp1, temp3);
		/* ģ���� �ξ�!!!! */

		manager.addGroup(temp1, "���ƽý�");
		/* temp1�� �׷� ���ƽý��� ���� */

		manager.addFriend(temp1.getId(), "���ƽý�", temp2);
		/* temp1ȸ���� ���ƽý� �׷쿡 temp2 ģ���� �ʴ� */

		manager.addGroup(temp3, "���ƽý�");
		/* temp3ȸ���� �̹� �����ϴ� �̸��� ���ƽý� �׷��� ������ �� ���� ���ϰ� ���� */

		manager.addFriend(temp1.getId(), "���ƽý�", temp2);
		/* �̹� �����ϴ� ȸ���� �ߺ� �߰� ���� ���� */

		MemberList mlist = new MemberList();
		mlist.add(temp1);
		mlist.add(temp2);
		mlist.add(temp3);
		/* ģ������ ������ ������ ����Ʈ�� ���� */

		groupCon.requestFriend("hyj", "kjs");
		groupCon.requestFriend("jhc", "kjs");
		System.out.println(groupCon.getMyStandByFriend("hyj"));
		System.out.println(groupCon.getMyStandByFriend("kjs"));
		// System.out.println(manager);
		// manager.addGroup(temp4, "�����Ǿ�", mlist.getMemberList());
		/* �׷� ���� �����ϰ� �׷쿡 ���� ģ������ �����Ͽ� ó�� �׷��� ���� */

		groupCon.responseFriend("kjs", "hyj", false);
		System.out.println(manager);
		System.out.println(groupCon.getMyStandByFriend("kjs"));
		
		groupCon.requestGroup("kjs", "jhc", "���ƽý�");
		System.out.println(groupCon.getMyStandByFriend("jhc"));
		System.out.println(groupCon.getMyStandByGroup("jhc").get(0)[0] + groupCon.getMyStandByGroup("jhc").get(0)[1]);
		groupCon.responseGroup("jhc", "kjs", "���ƽý�", true);
		System.out.println(manager);
		
		groupCon.requestGroup("kjs", "jhc", "���ƽý�");
		//System.out.println(groupCon.getMyStandByGroup("jhc").get(0)[0] + groupCon.getMyStandByGroup("jhc").get(0)[1]);
		
		// manager.removeGroup("kjs", "���ƽý�");
		/* kjs���̵��� ȸ���� �׷��� ���� �Ѵ�. */

		// System.out.println(manager);

		// manager.removeFriend("kjs", "kyw");
		/* ģ���� ���� */

		// System.out.println(manager);

	}
}
