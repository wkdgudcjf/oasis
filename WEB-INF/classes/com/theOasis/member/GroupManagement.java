package com.theOasis.member;

import java.util.*;

/**
 * 
 * 회원들의 그룹목록을 전체적으로 관리 하는 클래스. 한 회원이 갖고있는 그룹 목록을 멤버로 갖고 있으며, 회원에 대한 그룹 목록을 찾거나
 * 추가하거나 삭제할 수 있으며, 회원에 그룹에 속한 친구을 삭제하거나 추가가 가능하다.
 * 
 * @author jisu
 * 
 */
public class GroupManagement {
	/**
	 * 회원의 그룹목록
	 */
	private HashMap<String, GroupList> groupList;

	/**
	 * 존재 하는 그룹을 모아둔 그룹
	 */
	private HashMap<String, Group> allGroupList;

	/**
	 * default 생성자
	 */
	public GroupManagement() {
		groupList = new HashMap<String, GroupList>();
		allGroupList = new HashMap<String, Group>();
	}

	/**
	 * 회원들의 그룹목록과 그룹 명 목록을 전달받아 생성한다.
	 * 
	 * @param groupList
	 *            회원들의 그룹 목록
	 * @param groupNameList
	 *            전체 회원의 그룹 이름 목록
	 */
	public GroupManagement(HashMap<String, GroupList> groupList,
			HashMap<String, Group> allGroupList) {
		this.groupList = groupList;
		this.allGroupList = allGroupList;
	}

	/**
	 * 
	 * 회원과 그룹을 전달받아 해당하는 회원에게 그룹을 추가한다. 또한 그 그룹에 해당 회원을 추가한다.
	 * 
	 * @param member
	 *            그룹을 추가하려는 회원
	 * @param group
	 *            추가될 그룹
	 */
	public void addGroup(Userable member, MemberList group) {
		if (member != null) {
			if (group != null) {
				if (!(group.contains(member))) { // 그룹에 내가 없을 시
					searchGroup(member.getId()).add(group);// 내그룹에 그룹을 추가
					group.add(member);// 그룹에 나도 추가
				}
			}
		}
	}

	/**
	 * 
	 * 회원과 그룹명을 전달받아 그룹명이 존재 하지 않아 사용 가능 할 시 해당하는 회원에게 그룹을 생성한다. 생성한 그룹에 생성한 자기
	 * 자신도 추가한다.
	 * 
	 * @param member
	 *            그룹을 생성하고자 하는 회원
	 * @param groupName
	 *            추가하려는 그룹명
	 */
	public void addGroup(Userable member, String groupName) {
		if (member != null) {
			if (groupName != null) {
				if (!(allGroupList.containsKey(groupName))) {
					searchGroup(member.getId()).add(groupName); // 내가 그룹 명만 갖고
																// 있는 그룹을 생성,
					Group group = ((Group)searchGroup(member, groupName)); // 그룹에 나도 추가
					group.add(member);
					allGroupList.put(groupName, group);// 그룹 이름 목록에 추가
				}
			}
		}
	}

	/**
	 * 
	 * DB에 있는 데이터를 꺼내오기 위한 translator를 위한 메소드
	 * 해당 그룹 명이 존재하더라도 디비에 있는 데이터를 갖고 온 뒤 뿌려주는 것이기
	 * 때문에 만들어서 회원 아이디에 넣어준.
	 * 
	 * @param member
	 *            그룹을 생성하고자 하는 회원
	 * @param groupName
	 *            추가하려는 그룹명
	 */
	public void translateGroup(Userable member, String groupName) {
		if (member != null) {
			if (groupName != null) {
					searchGroup(member.getId()).add(groupName); // 내가 그룹 명만 갖고
																// 있는 그룹을 생성,
					Group group = ((Group)searchGroup(member, groupName)); // 그룹에 나도 추가
					group.add(member);
					allGroupList.put(groupName, group);// 그룹 이름 목록에 추가
			}
		}
	}
	
	/**
	 * 회원과 그룹명, 그룹에 추가할 회원들을 전달받아 해당하는 아이디에 그룹을 만들어 추가한다. 생성한 그룹에 자기 자신(전달받은
	 * 회원)도 추가한다.
	 * 
	 * @param member
	 *            그룹을 추가하고자 하는 회원
	 * @param groupName
	 *            추가하려는 그룹명
	 * @param friends
	 *            그룹에 추가하고자 하는 친구들
	 */
	public void addGroup(Userable member, String groupName,
			List<Userable> friends) {
		if (member != null) {
			if (groupName != null) {
					if (!(allGroupList.containsKey(groupName))) {// 존재하는 그룹 명중에 해당
																// 그룹
																// 명이 존재하지 않을 경우
						if (friends != null) {

							if (containsFriend(member, friends)) {// 추가하려는 친구들이 모두
																// 자신이 친구들인지 검사한
																// 뒤에
																// 한 명이라도 친구가 아닐
																// 시 추가해 주지 않는다.
							addGroup(member, groupName); // 해당 회원에게 그룹이름만 만들어 먼저
															// 그룹을 생성한 뒤
							searchGroup(member.getId()).add(groupName, friends); // 그
																					// 그룹을
																					// 회원에게서
																					// 찾아서
																					// friends를
																					// 넣는다.
							Group group = (Group) searchGroup(member.getId(),
									groupName);
							allGroupList.put(groupName, group);// 그룹 명을 리스트에
																// 넣는다.

							for (Userable user : friends) { // 그룹을 만들어서 추가한
															// 친구들에게도 해당 그룹을 모두
															// 추가해준다.
								addGroup(
										user,
										searchGroup(member.getId()).get(
												groupName));
							}
							if (!(friends.contains(member))) {
								// 성공 실패 여부
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
	 * 회원의 아이디와 그룹명을 전달받아 해당하는 회원의 그룹목록에서 해당하는 그룹명의 그룹을 반환한다.
	 * 
	 * @param id
	 *            그룹을 찾고자 하는 회원의 아이디
	 * @param groupName
	 *            찾으려는 그룹명
	 * @return 해당하는 그룹
	 */
	public MemberList searchGroup(String id, String groupName) {
		if (groupList.get(id) != null) {
			if ((groupList.get(id).get(groupName).contains(id))) {// 회원이 그 그룹에
																	// 속해있으면
				return groupList.get(id).get(groupName); // 해당하는 회원의 그룹목록에서
															// groupName에 해당하는
															// 그룹을 찾아서 반환
			}
		}
		return null;// 속해 있지 않으면 null을 반환
	}

	/**
	 * 회원의 아이디를 전달받아 해당하는 회원의 그룹목록을 반환한다.
	 * 
	 * @param id
	 *            찾으려는 회원의 아이디
	 * @return 해당하는 회원의 그룹목록
	 */
	public GroupList searchGroup(String id) {
		if (groupList.get(id) != null) {
			return groupList.get(id);
		}
		return null;
	}

	/**
	 * GroupManagement 내에서만 사용 가능한 private 메소드 클래스 내에서 회원에 속한 그룹을 꺼낼때 속한 회원 확인
	 * 없이 모두 search하는 메소드 public 메소드의 경우 자신이 속하지 않은 그룹이면 반환하지 않는 search 메소드이다.
	 * 친구그룹을 찾을때 쓰인다.
	 * @param member
	 *            그룹을 찾으려는 회원
	 * @param groupName
	 *            찾으려는 그룹이름
	 * @return 그룹(super형인 MemberList)로 반환한다.
	 */
	public MemberList searchGroup(Userable member, String groupName) {
		if (member != null) {
			return searchGroup(member.getId()).get(groupName);
		}
		return null;
	}

	/**
	 * 전달받은 회원 아이디의 회원을 찾고, 전달받은 그룹을 그룹목록에서 삭제한다.
	 * 
	 * @param id
	 *            그룹을 삭제하려는 회원의 아이디
	 * @param group
	 *            삭제하고자 하는 그룹
	 */
	public void removeGroup(String id, MemberList group) {

		searchGroup(id).remove(((Group) group).getGroupName());
		group.remove(id);
	}

	/**
	 * 전달받은 회원 아이디의 회원을 찾고, 전달받은 그룹명의 그룹을 그룹목록에서 삭제한다.
	 * 
	 * @param id
	 *            그룹을 삭제하려는 회원의 아이디
	 * @param groupName
	 *            삭제하고자 하는 그룹의 그룹명
	 */
	public void removeGroup(String id, String groupName) {
		removeFriend(id, groupName, id);
		searchGroup(id).remove(groupName);

	}

	/**
	 * 회원의 아이디와 ID 혹은 이름을 전달받아 해당하는 친구들을 반환한다.
	 * 
	 * @param id
	 *            친구들을 찾고자 하는 회원의 아이디
	 * @param info
	 *            찾고자 하는 친구의 이름이나 아이디의 여부
	 * @param data
	 *            찾고자 하는 친구의 이름이나 아이디
	 * @return 친구들
	 */
	public List<Userable> search(String id, String groupName, MemberInfo info,
			String data) {
		return searchGroup(id).get(groupName).get(info, data);
	}

	/**
	 * 회원의 아이디를 전달받아 회원을 찾은뒤 전달받은 그룹명의 그룹에 친구를 추가한다.
	 * 
	 * @param id
	 *            친구를 추가하고자 하는 회원의 아이디
	 * @param groupName
	 *            친구를 추가하려는 그룹명
	 * @param friend
	 *            추가하려는 친구
	 */
	public void addFriend(String id, String groupName, Userable friend) {
		if (id != null) {
			if (groupName != null) {
				if (friend != null) {
					if (searchGroup(id).get("친구").contains(friend)) { // 그룹에
																		// 추가하려는
																		// 친구가
																		// 나의
																		// 친구라면
						// System.out.println(searchGroup(id).get(groupName));
						if (!(searchGroup(id).get(groupName).contains(friend))) {// 그룹에
																					// 추가하려는
																					// 친구가
																					// 그
																					// 그룹에
																					// 존재하지
																					// 않는다면
							searchGroup(id).get(groupName).add(friend);
							// 해당 friend에 그룹도 추가?? 그러면 addGroup(id:String,
							// group:MemberList)가 하는일은??
							addGroup(friend,
									((Group) searchGroup(id, groupName)));
						}
					}
				}
			}
		}
	}

	/**
	 * 친구를 추가하려는 아이디와 추가할 친구를 전달받아 회원의 기본 그룹인 친구에 추가한다.
	 * 
	 * @param id
	 *            친구를 추가하려는 아이디
	 * @param friend
	 *            추가할 친구
	 */
	/*
	 * public void addFriend(String id, Userable friend){ if(id!=null){
	 * if(friend!=null){ searchGroup(id).get("친구").add(friend); //add(friend)에서
	 * 존재하는지 하지 않는지 검사 이미 존재하면 추가하지 않는다.
	 * //searchGroup(friend.getId()).get("친구").add(id); //친구한테도 나를 추가 } } }
	 */// 아이디로 자신을 찾을 수가 없다. MemberManagement와 통신 해야 함

	/**
	 * 친구를 추가하려는 회원과 추가할 친구를 전달받아 회원의 기본 그룹인 친구에 추가한다.
	 * 
	 * @param me
	 *            친구를 추가 하려는 회원
	 * @param friend
	 *            추가할 친구
	 */
	public void addFriend(Userable me, Userable friend) {
		if (me != null) {
			if (friend != null) {
				if (searchGroup(me.getId()).get("친구") == null) { // 내 아이디에 친구
																	// 그룹이 없을 시
					put(me.getId());// 친구 그룹을 put해준다.
				}
				if (searchGroup(friend.getId()).get("친구") == null) {
					put(friend.getId());
				}
				searchGroup(me.getId()).get("친구").add(friend); // add(friend)에서
																// 존재하는지 하지 않는지
																// 검사 이미 존재하면
																// 추가하지 않는다.
				searchGroup(friend.getId()).get("친구").add(me); // 친구에게도 나를 추가
			}
		}
	}

	/**
	 * 회원의 아이디를 전달받아 회원을 찾은뒤 전달받은 그룹명의 그룹에서 친구를 삭제한다.
	 * 
	 * @param id
	 *            친구를 삭제하고자 하는 회원의 아이디
	 * @param groupName
	 *            친구를 삭제하려는 그룹
	 * @param friend
	 *            삭제하려는 친구
	 */
	public void removeFriend(String id, String groupName, Userable friend) {
		searchGroup(id).get(groupName).remove(friend);
	}

	/**
	 * 회원의 아이디를 전달받아 회원을 찾은뒤 전달받은 그룹명의 그룹에서 친구를 삭제한다.
	 * 
	 * @param id
	 *            친구를 삭제하고자 하는 회원의 아이디
	 * @param groupName
	 *            친구를 삭제하려는 그룹명
	 * @param friendId
	 *            삭제하고자 하는 친구의 아이디
	 */
	public void removeFriend(String id, String groupName, String friendId) {
		searchGroup(id).get(groupName).remove(friendId);
	}// 그룹에서 친구 삭제는 불가능

	/**
	 * 친구를 삭제하려는 회원의 아이디와 삭제하려는 친구를 전달 받아 회원의 친구 목록에서 친구를 삭제한다 삭제한 친구의 친구목록에서도
	 * 해당 회원이 삭제된다.
	 * 
	 * @param id
	 *            친구를 삭제하려는 회원
	 * @param friendId
	 *            삭제하려는 친구
	 */
	public void removeFriend(String id, String friendId) {
		removeFriend(id, "친구", friendId);
		removeFriend(friendId, "친구", id);
	}

	/**
	 * 그룹에 추가하기 위해서는 꼭 내 친구여야 한다. 따라서 그룹에 추가 하려는 친구들 중 한명이라도 내 친구가 아닐 시 false를
	 * 반환한다
	 * 
	 * @param id
	 *            그룹에 친구들을 초대하려는 회원
	 * @param friends
	 *            초대하려는 친구들
	 * @return
	 */
	public boolean containsFriend(Userable member, List<Userable> friends) {
		for (Userable user : friends) {
			// System.out.println(id+friends.get(0).getId());
			// System.out.println(searchGroup(id,"친구"));
			if (!(searchGroup(member, "친구").contains(user))) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 회원 가입 했을 시 HashMap에 put 해주는 메소드 친구를 추가 했을 시 추가되는 기본 친구 그룹을 합께 생성한다.
	 * 
	 * @param id
	 *            회원 가입한 회원의 아이디
	 */
	public void put(String id) {
		GroupList list = new GroupList();
		list.add("친구");
		groupList.put(id, list);
		
	}

	/**
	 * 회원 가입 했을 시 HashMap에 put 해주는 메소드 친구를 추가 했을시 추가되는 기본 친구 그룹을 함께 생성한다.
	 * 
	 * @param member
	 *            회원가입한 회원
	 */
	public void put(Userable member) {
		GroupList list = new GroupList();
		list.add("친구");
		groupList.put(member.getId(), list);
	}

	public Group getGroup(String id) {
		return allGroupList.get(id);
	}

	/**
	 * 회원의 아이디를 전달받아 회원을 찾은뒤 전달받은 아이디의 친구를 삭제한다.
	 * 
	 * @param id
	 *            그룹을 삭제하고자 하는 회원의 아이디
	 * @param groupName
	 *            삭제하고자 하는 그룹명
	 */
	/*
	 * public void removeFriend(String id, String friendId){
	 * 
	 * }
	 */

	/**
	 * 회원들의 그룹 목록을 반환한다.
	 * 
	 * @return 회원들의 그룹 목록
	 */
	public HashMap<String, GroupList> getGroupList() {
		return groupList;
	}

	/**
	 * 회원들의 그룹 목록을 갱신한다.
	 * 
	 * @param groupList
	 *            회원들의 그룹 목록
	 */
	public void setGroupList(HashMap<String, GroupList> groupList) {
		this.groupList = groupList;
	}

	/**
	 * 모든 그룹 리스트를 반환한다.
	 * 
	 * @return 모든 그룹 목록
	 */
	public HashMap<String, Group> getAllGroupList() {
		return allGroupList;
	}

	/**
	 * 모든 그룹 목록을 갱신한다.
	 * 
	 * @param allGroupList
	 *            모든 그룹 목록
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
		/* 그룹 매니저를 생성 */

		Userable temp1 = new Member("kjs", "1234", "김지수");
		Userable temp2 = new Member("kyw", "2222", "김예원");
		Userable temp3 = new Member("jhc", "3333", "장형철");
		Userable temp4 = new Member("hyj", "5555", "한유진");
		/* 회원 3명을 생성 */

		manager.put("kjs");
		manager.put("kyw");
		manager.put("jhc");
		manager.put("hyj");
		/* 회원 생성 시 그룹 목록을 할당시켜 줌 */

		manager.addFriend(temp1, temp2);
		manager.addFriend(temp1, temp3);
		manager.addFriend(temp1, temp3);
		/* 친구를 맺어!!!! */

		manager.addGroup(temp1, "오아시스");
		/* temp1이 그룹 오아시스를 생성 */

		manager.addFriend(temp1.getId(), "오아시스", temp2);
		/* temp1회원이 오아시스 그룹에 temp2 친구를 초대 */

		manager.addGroup(temp3, "오아시스");
		/* temp3회원이 이미 존재하는 이름의 오아시스 그룹을 생성할 시 생성 못하게 막음 */

		manager.addFriend(temp1.getId(), "오아시스", temp2);
		/* 이미 존재하는 회원은 중복 추가 하지 않음 */

		MemberList mlist = new MemberList();
		mlist.add(temp2);
		mlist.add(temp3);
		/* 친구들을 여러명 지정한 리스트를 생성 */

		manager.addGroup(temp4, "렉토피아", mlist.getMemberList());
		/* 그룹 명을 지정하고 그룹에 넣을 친구들을 선택하여 처음 그룹을 생성 */
		/* 친구가 아닌 사람이 있으므로 생성되지 않는다. */

		manager.addGroup(temp1, "렉토피아", mlist.getMemberList());
		manager.removeGroup("kjs", "오아시스");
		/* kjs아이디의 회원이 그룹을 삭제 한다. */

		manager.removeFriend("kjs", "kyw");
		/* 친구를 삭제 */

	}
}
