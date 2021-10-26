package com.theOasis.member;

import java.util.*;

/**
 * 
 * ȸ������ �׷����� ��ü������ ���� �ϴ� Ŭ����. �� ȸ���� �����ִ� �׷� ����� ����� ���� ������, ȸ���� ���� �׷� ����� ã�ų�
 * �߰��ϰų� ������ �� ������, ȸ���� �׷쿡 ���� ģ���� �����ϰų� �߰��� �����ϴ�.
 * 
 * @author jisu
 * 
 */
public class GroupManagement {
	/**
	 * ȸ���� �׷���
	 */
	private HashMap<String, GroupList> groupList;

	/**
	 * ���� �ϴ� �׷��� ��Ƶ� �׷�
	 */
	private HashMap<String, Group> allGroupList;

	/**
	 * default ������
	 */
	public GroupManagement() {
		groupList = new HashMap<String, GroupList>();
		allGroupList = new HashMap<String, Group>();
	}

	/**
	 * ȸ������ �׷��ϰ� �׷� �� ����� ���޹޾� �����Ѵ�.
	 * 
	 * @param groupList
	 *            ȸ������ �׷� ���
	 * @param groupNameList
	 *            ��ü ȸ���� �׷� �̸� ���
	 */
	public GroupManagement(HashMap<String, GroupList> groupList,
			HashMap<String, Group> allGroupList) {
		this.groupList = groupList;
		this.allGroupList = allGroupList;
	}

	/**
	 * 
	 * ȸ���� �׷��� ���޹޾� �ش��ϴ� ȸ������ �׷��� �߰��Ѵ�. ���� �� �׷쿡 �ش� ȸ���� �߰��Ѵ�.
	 * 
	 * @param member
	 *            �׷��� �߰��Ϸ��� ȸ��
	 * @param group
	 *            �߰��� �׷�
	 */
	public void addGroup(Userable member, MemberList group) {
		if (member != null) {
			if (group != null) {
				if (!(group.contains(member))) { // �׷쿡 ���� ���� ��
					searchGroup(member.getId()).add(group);// ���׷쿡 �׷��� �߰�
					group.add(member);// �׷쿡 ���� �߰�
				}
			}
		}
	}

	/**
	 * 
	 * ȸ���� �׷���� ���޹޾� �׷���� ���� ���� �ʾ� ��� ���� �� �� �ش��ϴ� ȸ������ �׷��� �����Ѵ�. ������ �׷쿡 ������ �ڱ�
	 * �ڽŵ� �߰��Ѵ�.
	 * 
	 * @param member
	 *            �׷��� �����ϰ��� �ϴ� ȸ��
	 * @param groupName
	 *            �߰��Ϸ��� �׷��
	 */
	public void addGroup(Userable member, String groupName) {
		if (member != null) {
			if (groupName != null) {
				if (!(allGroupList.containsKey(groupName))) {
					searchGroup(member.getId()).add(groupName); // ���� �׷� �� ����
																// �ִ� �׷��� ����,
					Group group = ((Group)searchGroup(member, groupName)); // �׷쿡 ���� �߰�
					group.add(member);
					allGroupList.put(groupName, group);// �׷� �̸� ��Ͽ� �߰�
				}
			}
		}
	}

	/**
	 * 
	 * DB�� �ִ� �����͸� �������� ���� translator�� ���� �޼ҵ�
	 * �ش� �׷� ���� �����ϴ��� ��� �ִ� �����͸� ���� �� �� �ѷ��ִ� ���̱�
	 * ������ ���� ȸ�� ���̵� �־���.
	 * 
	 * @param member
	 *            �׷��� �����ϰ��� �ϴ� ȸ��
	 * @param groupName
	 *            �߰��Ϸ��� �׷��
	 */
	public void translateGroup(Userable member, String groupName) {
		if (member != null) {
			if (groupName != null) {
					searchGroup(member.getId()).add(groupName); // ���� �׷� �� ����
																// �ִ� �׷��� ����,
					Group group = ((Group)searchGroup(member, groupName)); // �׷쿡 ���� �߰�
					group.add(member);
					allGroupList.put(groupName, group);// �׷� �̸� ��Ͽ� �߰�
			}
		}
	}
	
	/**
	 * ȸ���� �׷��, �׷쿡 �߰��� ȸ������ ���޹޾� �ش��ϴ� ���̵� �׷��� ����� �߰��Ѵ�. ������ �׷쿡 �ڱ� �ڽ�(���޹���
	 * ȸ��)�� �߰��Ѵ�.
	 * 
	 * @param member
	 *            �׷��� �߰��ϰ��� �ϴ� ȸ��
	 * @param groupName
	 *            �߰��Ϸ��� �׷��
	 * @param friends
	 *            �׷쿡 �߰��ϰ��� �ϴ� ģ����
	 */
	public void addGroup(Userable member, String groupName,
			List<Userable> friends) {
		if (member != null) {
			if (groupName != null) {
					if (!(allGroupList.containsKey(groupName))) {// �����ϴ� �׷� ���߿� �ش�
																// �׷�
																// ���� �������� ���� ���
						if (friends != null) {

							if (containsFriend(member, friends)) {// �߰��Ϸ��� ģ������ ���
																// �ڽ��� ģ�������� �˻���
																// �ڿ�
																// �� ���̶� ģ���� �ƴ�
																// �� �߰��� ���� �ʴ´�.
							addGroup(member, groupName); // �ش� ȸ������ �׷��̸��� ����� ����
															// �׷��� ������ ��
							searchGroup(member.getId()).add(groupName, friends); // ��
																					// �׷���
																					// ȸ�����Լ�
																					// ã�Ƽ�
																					// friends��
																					// �ִ´�.
							Group group = (Group) searchGroup(member.getId(),
									groupName);
							allGroupList.put(groupName, group);// �׷� ���� ����Ʈ��
																// �ִ´�.

							for (Userable user : friends) { // �׷��� ���� �߰���
															// ģ���鿡�Ե� �ش� �׷��� ���
															// �߰����ش�.
								addGroup(
										user,
										searchGroup(member.getId()).get(
												groupName));
							}
							if (!(friends.contains(member))) {
								// ���� ���� ����
								searchGroup(member.getId(), groupName).add(
										member);
							}
						}
					}
				}
			}
		}
	}

	/**
	 * ȸ���� ���̵�� �׷���� ���޹޾� �ش��ϴ� ȸ���� �׷��Ͽ��� �ش��ϴ� �׷���� �׷��� ��ȯ�Ѵ�.
	 * 
	 * @param id
	 *            �׷��� ã���� �ϴ� ȸ���� ���̵�
	 * @param groupName
	 *            ã������ �׷��
	 * @return �ش��ϴ� �׷�
	 */
	public MemberList searchGroup(String id, String groupName) {
		if (groupList.get(id) != null) {
			if ((groupList.get(id).get(groupName).contains(id))) {// ȸ���� �� �׷쿡
																	// ����������
				return groupList.get(id).get(groupName); // �ش��ϴ� ȸ���� �׷��Ͽ���
															// groupName�� �ش��ϴ�
															// �׷��� ã�Ƽ� ��ȯ
			}
		}
		return null;// ���� ���� ������ null�� ��ȯ
	}

	/**
	 * ȸ���� ���̵� ���޹޾� �ش��ϴ� ȸ���� �׷����� ��ȯ�Ѵ�.
	 * 
	 * @param id
	 *            ã������ ȸ���� ���̵�
	 * @return �ش��ϴ� ȸ���� �׷���
	 */
	public GroupList searchGroup(String id) {
		if (groupList.get(id) != null) {
			return groupList.get(id);
		}
		return null;
	}

	/**
	 * GroupManagement �������� ��� ������ private �޼ҵ� Ŭ���� ������ ȸ���� ���� �׷��� ������ ���� ȸ�� Ȯ��
	 * ���� ��� search�ϴ� �޼ҵ� public �޼ҵ��� ��� �ڽ��� ������ ���� �׷��̸� ��ȯ���� �ʴ� search �޼ҵ��̴�.
	 * ģ���׷��� ã���� ���δ�.
	 * @param member
	 *            �׷��� ã������ ȸ��
	 * @param groupName
	 *            ã������ �׷��̸�
	 * @return �׷�(super���� MemberList)�� ��ȯ�Ѵ�.
	 */
	public MemberList searchGroup(Userable member, String groupName) {
		if (member != null) {
			return searchGroup(member.getId()).get(groupName);
		}
		return null;
	}

	/**
	 * ���޹��� ȸ�� ���̵��� ȸ���� ã��, ���޹��� �׷��� �׷��Ͽ��� �����Ѵ�.
	 * 
	 * @param id
	 *            �׷��� �����Ϸ��� ȸ���� ���̵�
	 * @param group
	 *            �����ϰ��� �ϴ� �׷�
	 */
	public void removeGroup(String id, MemberList group) {

		searchGroup(id).remove(((Group) group).getGroupName());
		group.remove(id);
	}

	/**
	 * ���޹��� ȸ�� ���̵��� ȸ���� ã��, ���޹��� �׷���� �׷��� �׷��Ͽ��� �����Ѵ�.
	 * 
	 * @param id
	 *            �׷��� �����Ϸ��� ȸ���� ���̵�
	 * @param groupName
	 *            �����ϰ��� �ϴ� �׷��� �׷��
	 */
	public void removeGroup(String id, String groupName) {
		removeFriend(id, groupName, id);
		searchGroup(id).remove(groupName);

	}

	/**
	 * ȸ���� ���̵�� ID Ȥ�� �̸��� ���޹޾� �ش��ϴ� ģ������ ��ȯ�Ѵ�.
	 * 
	 * @param id
	 *            ģ������ ã���� �ϴ� ȸ���� ���̵�
	 * @param info
	 *            ã���� �ϴ� ģ���� �̸��̳� ���̵��� ����
	 * @param data
	 *            ã���� �ϴ� ģ���� �̸��̳� ���̵�
	 * @return ģ����
	 */
	public List<Userable> search(String id, String groupName, MemberInfo info,
			String data) {
		return searchGroup(id).get(groupName).get(info, data);
	}

	/**
	 * ȸ���� ���̵� ���޹޾� ȸ���� ã���� ���޹��� �׷���� �׷쿡 ģ���� �߰��Ѵ�.
	 * 
	 * @param id
	 *            ģ���� �߰��ϰ��� �ϴ� ȸ���� ���̵�
	 * @param groupName
	 *            ģ���� �߰��Ϸ��� �׷��
	 * @param friend
	 *            �߰��Ϸ��� ģ��
	 */
	public void addFriend(String id, String groupName, Userable friend) {
		if (id != null) {
			if (groupName != null) {
				if (friend != null) {
					if (searchGroup(id).get("ģ��").contains(friend)) { // �׷쿡
																		// �߰��Ϸ���
																		// ģ����
																		// ����
																		// ģ�����
						// System.out.println(searchGroup(id).get(groupName));
						if (!(searchGroup(id).get(groupName).contains(friend))) {// �׷쿡
																					// �߰��Ϸ���
																					// ģ����
																					// ��
																					// �׷쿡
																					// ��������
																					// �ʴ´ٸ�
							searchGroup(id).get(groupName).add(friend);
							// �ش� friend�� �׷쵵 �߰�?? �׷��� addGroup(id:String,
							// group:MemberList)�� �ϴ�����??
							addGroup(friend,
									((Group) searchGroup(id, groupName)));
						}
					}
				}
			}
		}
	}

	/**
	 * ģ���� �߰��Ϸ��� ���̵�� �߰��� ģ���� ���޹޾� ȸ���� �⺻ �׷��� ģ���� �߰��Ѵ�.
	 * 
	 * @param id
	 *            ģ���� �߰��Ϸ��� ���̵�
	 * @param friend
	 *            �߰��� ģ��
	 */
	/*
	 * public void addFriend(String id, Userable friend){ if(id!=null){
	 * if(friend!=null){ searchGroup(id).get("ģ��").add(friend); //add(friend)����
	 * �����ϴ��� ���� �ʴ��� �˻� �̹� �����ϸ� �߰����� �ʴ´�.
	 * //searchGroup(friend.getId()).get("ģ��").add(id); //ģ�����׵� ���� �߰� } } }
	 */// ���̵�� �ڽ��� ã�� ���� ����. MemberManagement�� ��� �ؾ� ��

	/**
	 * ģ���� �߰��Ϸ��� ȸ���� �߰��� ģ���� ���޹޾� ȸ���� �⺻ �׷��� ģ���� �߰��Ѵ�.
	 * 
	 * @param me
	 *            ģ���� �߰� �Ϸ��� ȸ��
	 * @param friend
	 *            �߰��� ģ��
	 */
	public void addFriend(Userable me, Userable friend) {
		if (me != null) {
			if (friend != null) {
				if (searchGroup(me.getId()).get("ģ��") == null) { // �� ���̵� ģ��
																	// �׷��� ���� ��
					put(me.getId());// ģ�� �׷��� put���ش�.
				}
				if (searchGroup(friend.getId()).get("ģ��") == null) {
					put(friend.getId());
				}
				searchGroup(me.getId()).get("ģ��").add(friend); // add(friend)����
																// �����ϴ��� ���� �ʴ���
																// �˻� �̹� �����ϸ�
																// �߰����� �ʴ´�.
				searchGroup(friend.getId()).get("ģ��").add(me); // ģ�����Ե� ���� �߰�
			}
		}
	}

	/**
	 * ȸ���� ���̵� ���޹޾� ȸ���� ã���� ���޹��� �׷���� �׷쿡�� ģ���� �����Ѵ�.
	 * 
	 * @param id
	 *            ģ���� �����ϰ��� �ϴ� ȸ���� ���̵�
	 * @param groupName
	 *            ģ���� �����Ϸ��� �׷�
	 * @param friend
	 *            �����Ϸ��� ģ��
	 */
	public void removeFriend(String id, String groupName, Userable friend) {
		searchGroup(id).get(groupName).remove(friend);
	}

	/**
	 * ȸ���� ���̵� ���޹޾� ȸ���� ã���� ���޹��� �׷���� �׷쿡�� ģ���� �����Ѵ�.
	 * 
	 * @param id
	 *            ģ���� �����ϰ��� �ϴ� ȸ���� ���̵�
	 * @param groupName
	 *            ģ���� �����Ϸ��� �׷��
	 * @param friendId
	 *            �����ϰ��� �ϴ� ģ���� ���̵�
	 */
	public void removeFriend(String id, String groupName, String friendId) {
		searchGroup(id).get(groupName).remove(friendId);
	}// �׷쿡�� ģ�� ������ �Ұ���

	/**
	 * ģ���� �����Ϸ��� ȸ���� ���̵�� �����Ϸ��� ģ���� ���� �޾� ȸ���� ģ�� ��Ͽ��� ģ���� �����Ѵ� ������ ģ���� ģ����Ͽ�����
	 * �ش� ȸ���� �����ȴ�.
	 * 
	 * @param id
	 *            ģ���� �����Ϸ��� ȸ��
	 * @param friendId
	 *            �����Ϸ��� ģ��
	 */
	public void removeFriend(String id, String friendId) {
		removeFriend(id, "ģ��", friendId);
		removeFriend(friendId, "ģ��", id);
	}

	/**
	 * �׷쿡 �߰��ϱ� ���ؼ��� �� �� ģ������ �Ѵ�. ���� �׷쿡 �߰� �Ϸ��� ģ���� �� �Ѹ��̶� �� ģ���� �ƴ� �� false��
	 * ��ȯ�Ѵ�
	 * 
	 * @param id
	 *            �׷쿡 ģ������ �ʴ��Ϸ��� ȸ��
	 * @param friends
	 *            �ʴ��Ϸ��� ģ����
	 * @return
	 */
	public boolean containsFriend(Userable member, List<Userable> friends) {
		for (Userable user : friends) {
			// System.out.println(id+friends.get(0).getId());
			// System.out.println(searchGroup(id,"ģ��"));
			if (!(searchGroup(member, "ģ��").contains(user))) {
				return false;
			}
		}
		return true;
	}

	/**
	 * ȸ�� ���� ���� �� HashMap�� put ���ִ� �޼ҵ� ģ���� �߰� ���� �� �߰��Ǵ� �⺻ ģ�� �׷��� �ղ� �����Ѵ�.
	 * 
	 * @param id
	 *            ȸ�� ������ ȸ���� ���̵�
	 */
	public void put(String id) {
		GroupList list = new GroupList();
		list.add("ģ��");
		groupList.put(id, list);
		
	}

	/**
	 * ȸ�� ���� ���� �� HashMap�� put ���ִ� �޼ҵ� ģ���� �߰� ������ �߰��Ǵ� �⺻ ģ�� �׷��� �Բ� �����Ѵ�.
	 * 
	 * @param member
	 *            ȸ�������� ȸ��
	 */
	public void put(Userable member) {
		GroupList list = new GroupList();
		list.add("ģ��");
		groupList.put(member.getId(), list);
	}

	public Group getGroup(String id) {
		return allGroupList.get(id);
	}

	/**
	 * ȸ���� ���̵� ���޹޾� ȸ���� ã���� ���޹��� ���̵��� ģ���� �����Ѵ�.
	 * 
	 * @param id
	 *            �׷��� �����ϰ��� �ϴ� ȸ���� ���̵�
	 * @param groupName
	 *            �����ϰ��� �ϴ� �׷��
	 */
	/*
	 * public void removeFriend(String id, String friendId){
	 * 
	 * }
	 */

	/**
	 * ȸ������ �׷� ����� ��ȯ�Ѵ�.
	 * 
	 * @return ȸ������ �׷� ���
	 */
	public HashMap<String, GroupList> getGroupList() {
		return groupList;
	}

	/**
	 * ȸ������ �׷� ����� �����Ѵ�.
	 * 
	 * @param groupList
	 *            ȸ������ �׷� ���
	 */
	public void setGroupList(HashMap<String, GroupList> groupList) {
		this.groupList = groupList;
	}

	/**
	 * ��� �׷� ����Ʈ�� ��ȯ�Ѵ�.
	 * 
	 * @return ��� �׷� ���
	 */
	public HashMap<String, Group> getAllGroupList() {
		return allGroupList;
	}

	/**
	 * ��� �׷� ����� �����Ѵ�.
	 * 
	 * @param allGroupList
	 *            ��� �׷� ���
	 */
	public void setAllGroupList(HashMap<String, Group> allGroupList) {
		this.allGroupList = allGroupList;
	}

	@Override
	public String toString() {
		return "GroupManagement [groupList=" + groupList + ", allGroupList="
				+ allGroupList + "]";
	}

	public static void main(String args[]) {
		GroupManagement manager = new GroupManagement();
		/* �׷� �Ŵ����� ���� */

		Userable temp1 = new Member("kjs", "1234", "������");
		Userable temp2 = new Member("kyw", "2222", "�迹��");
		Userable temp3 = new Member("jhc", "3333", "����ö");
		Userable temp4 = new Member("hyj", "5555", "������");
		/* ȸ�� 3���� ���� */

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
		mlist.add(temp2);
		mlist.add(temp3);
		/* ģ������ ������ ������ ����Ʈ�� ���� */

		manager.addGroup(temp4, "�����Ǿ�", mlist.getMemberList());
		/* �׷� ���� �����ϰ� �׷쿡 ���� ģ������ �����Ͽ� ó�� �׷��� ���� */
		/* ģ���� �ƴ� ����� �����Ƿ� �������� �ʴ´�. */

		manager.addGroup(temp1, "�����Ǿ�", mlist.getMemberList());
		manager.removeGroup("kjs", "���ƽý�");
		/* kjs���̵��� ȸ���� �׷��� ���� �Ѵ�. */

		manager.removeFriend("kjs", "kyw");
		/* ģ���� ���� */

	}
}
