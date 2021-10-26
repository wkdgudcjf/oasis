package com.theOasis.controller;

import java.util.*;

import com.theOasis.member.*;

/**
 * 
 * 클라이언트와 통신매개체 역할을 하는 클래스. 그룹을 관리하는 매니저를 멤버로 갖고 있으며, 모델 객체를 가공시켜 반환하는 역할을 한다.
 * 또한 친구요청이 왔을 시 대기 시킬 수 있는 목록을 갖고 있다.
 * 
 * 기본적으로 모든 파라미터는 문자열 데이터로 종속성을 줄여준다. 그룹을 등록,검색,삭제,수정을 할 수 있으며,또한 그룹에 친구 추가, 친구
 * 삭제를 할 수 있다. 그룹이 아닌 친구 등록의 경우 default로 기본그룹에 등록된다.
 * 
 * @author jisu
 * 
 */
public class GroupController {

	/**
	 * 그룹관리
	 */
	private GroupManagement manager;
	/**
	 * singleton
	 */
	private static GroupController groupCon = new GroupController();
	/**
	 * 친구요청 대기목록
	 */
	private HashMap<String, List<String>> standByFriend;
	/**
	 * 그룹 요청 대기 목록 그룹 List<String>의 String은 그룹 명을 의미한다. String[]의 0 번째 방은 보낸 사람의
	 * id, 1번째 방은 그룹명
	 */
	private HashMap<String, List<String[]>> standByGroup;

	/**
	 * default생성자
	 */
	public GroupController() {
		manager = new GroupManagement();
		standByFriend = new HashMap<String, List<String>>();
		standByGroup = new HashMap<String, List<String[]>>();

	}

	/**
	 * 생성자
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
	 * 회원의 아이디와 찾으려는 친구의 정보를 전달받아 찾고 결과를 반환한다.
	 * 
	 * @param id
	 *            회원의 아이디
	 * @param info
	 *            찾으려는 친구의 정보가 ID인지 NAME인지의 여부
	 * @param data
	 *            찾으려는 친구의 정보
	 * @return 찾은 친구의 정보
	 */
	/*
	 * public List<String[]> searchFriend(String id, String info, String data){
	 * return null; }
	 */

	/**
	 * 회원의 아이디를 전달받아 회원의 그룹을 찾아서 결과를 반환한다.
	 * 
	 * @param id
	 *            회원의 아이디
	 * @return 회원의 그룹전체
	 */
	/*
	 * public HashMap<String, List<String[]>> searchGroup(String id){ return
	 * null; }
	 */
	/**
	 * 회원의 아이디와 그룹명을 전달받아 해당하는 그룹을 찾아서 결과를 반환한다.
	 * 
	 * @param id
	 *            회원의 아이디
	 * @param groupName
	 *            찾으려는 그룹의 이름
	 * @return 해당하는 그룹
	 */

	/*
	 * public List<String[]> searchGroup(String id, String groupName){ return
	 * null; }
	 */

	/**
	 * 회원의 아이디와 그룹을 전달받아 회원의 그룹에 추가한다.
	 * 
	 * @param id
	 *            회원 아이디
	 * @param group
	 *            추가하려는 그룹
	 * @return 성공, 실패여부
	 */
	/*
	 * public boolean addGroup(String id, List<String> group){ return true; }
	 */

	/**
	 * 회원의 아이디와 그룹명을 전달받아 회원의 그룹에 추가한다.
	 * 
	 * @param id
	 *            회원 아이디
	 * @param groupName
	 *            추가하려는 그룹명
	 * @return 성공,실패 여부
	 */
	/*
	 * public boolean addGroup(String id, String groupName){ return true; }
	 */

	/**
	 * 회원 아이디와 그룹명, 그룹에 추가할 친구들을 전달받아 그룹을 만들어 아이디에 해당하는 회원의 그룹목록에 추가한다.
	 * 
	 * @param id
	 *            회원의 아이디
	 * @param groupName
	 *            추가할 그룹명
	 * @param friends
	 *            추가할 그룹에 초대할 친구들
	 * @return 성공, 실패 여부
	 */
	/*
	 * public boolean addGroup(String id, String groupName, List<String>
	 * friends){ return true; }
	 */

	/**
	 * 아이디와 그룹을 전달받아 해당하는 아이디의 해당하는 그룹을 삭제한다.
	 * 
	 * @param id
	 *            회원 아이디
	 * @param group
	 *            삭제할 그룹
	 * @return 성공, 실패 여부
	 */
	/*
	 * public boolean removeGroup(String id, List<String> group){ return true; }
	 */

	/**
	 * 아이디와 그룹명을 전달받아 아이디에 해당하는 회원의 그룹목록에서 해당하는 그룹명의 그룹을 삭제한다.
	 * 
	 * @param id
	 *            회원의 아이디
	 * @param groupName
	 *            삭제하려는 그룹명
	 * @return 성공, 실패 여부
	 */
	/*
	 * public boolean removeGroup(String id, String groupName){ return true; }
	 */

	/**
	 * 회원의 아이디와 그룹명, 그룹에 추가할 친구를 전달받아 해당하는 회원의 그룹에 친구를 추가한다.
	 * 
	 * @param id
	 *            회원의 아이디
	 * @param groupName
	 *            친구를 추가할 그룹명
	 * @param friend
	 *            추가할 친구
	 * @return 성공, 실패 여부
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
					return;// 이미 친구 요청한 친구
				}
				standByFriend.get(friendId).add(id);
			} else {
				standByFriend.put(friendId, new LinkedList<String>());
				standByFriend.get(friendId).add(id);
			}
		}
	}

	/**
	 * 나의 친구 요청 목록에서 수락 하거나 거절하는 메소드 수락할시 addFriend메소드를 호출 하여 친구 추가 후 요청목록에서
	 * 삭제한다.
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
	 * 해당 아이디의 친구에게 groupName에 해당하는 그룹요청을 한다. 해당 아이디의 친구에게 그룹 요청 목록이 존재하지 않을 시
	 * put메소드를 호출해 리스트를 생성해 준 뒤에 추가한다. me는 그룹에 초대 요청을 보낸 자신, friendId는 그룹에 초대하기를
	 * 원하는 친구 id, groupName은 친구를 초대하고자 하는 그룹 명
	 * 
	 * @param me
	 *            그룹에 초대 요청을 보낸 자신
	 * @param friendId
	 *            그룹에 초대하기를 원하는 친구
	 * @param groupName
	 *            친구를 초대하고자 하는 그룹 명
	 */
	public void requestGroup(String id, String friendId, String groupName) {
		// MemberManagement tempM = MemberController.getInstance().getManager();
		// List<Userable> me = tempM.search(MemberInfo.ID, id);
		// List<Userable> friend = tempM.search(MemberInfo.ID, friendId);
		//System.out.println(manager.searchGroup(friendId, groupName));
		
		if (manager.getGroupList().get(friendId).get(groupName)==null) { //친구가 초대하고자 하는
																		 //그룹의 친구가 아닐 시에만 초대 가능
			if (standByGroup.containsKey(friendId)) {
				if (standByGroup.get(friendId).contains(groupName)) {
					return;// 이미 친구 요청한 그룹
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
	 * 나의 친구 요청 목록을 반환한다.
	 * 
	 * @param id
	 *            친구 요청을 보고 싶어 하는 회원의 아이디
	 * @return 나의 친구 요청 목록
	 */
	public List<String> getMyStandByFriend(String id) {
		return standByFriend.get(id);
	}

	/**
	 * 그룹 요청 목록에 있는 목록중에 자신이 응답하고자 하는 그룹 초대에 대한 응답을 한다 id는 응답하는 자신, receiver는
	 * 초대한 회원의 아이디, groupName은 초대받은 그룹명이며, tf는 수락 거절 체크이다.
	 * 
	 * @param id
	 *            응답하는 자신의 아이디
	 * @param receiver
	 *            초대한 회원의 아이디
	 * @param groupName
	 *            초대받은 그룹명
	 * @param tf
	 *            수락 거절 여부
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
	 * 회원의 아이디와 그룹명, 그룹에서 삭제할 친구를 전달받아 해당하는 회원의 그룹에서 친구를 제외한다.
	 * 
	 * @param id
	 *            회원의 아이디
	 * @param groupName
	 *            친구를 삭제할 그룹명
	 * @param friend
	 *            삭제할 친구
	 * @return 성공, 실패 여부
	 */
	/*
	 * public boolean removeFriend(String id, String groupName, String[]
	 * friend){ return true; }
	 */

	/**
	 * 회원의 아이디와 그룹명, 친구의 아이디를 전달받아 해당하는 회원의 그룹중 그룹명에 해당하는 그룹을 찾고, 그 그룹에서 친구의
	 * 아이디를 찾아 제외한다.
	 * 
	 * @param id
	 *            회원의 아이디
	 * @param groupName
	 *            친구를 제외하려는 그룹명
	 * @param friendId
	 *            제외하려는 친구아이디
	 * @return 성공, 실패 여부
	 */
	/*
	 * public boolean removeFriend(String id, String groupName, String
	 * friendId){ return true; }
	 */

	/**
	 * 회원의 아이디와 삭제할 친구의 아이디를 전달받아 해당하는 친구를 내 친구목록에서 삭제한다.
	 * 
	 * @param id
	 *            회원의 아이디
	 * @param friendId
	 *            삭제하려는 친구의 아이디
	 * @return 성공, 실패 여부
	 */
	/*
	 * public boolean removeFriend(String id, String friendId){ return true; }
	 */
	/**
	 * 회원의 아이디를 전달받아 해당하는 회원의 친구 요청 대기목록을 반환한다.
	 * 
	 * @param id
	 *            회원의 아이디
	 * @return 해당 회원의 친구 요청 대기 목록
	 */
	/*
	 * public List<String> getStandBy(String id){ return null; }
	 */

	/**
	 * 그룹을 관리하는 매니저를 반환한다.
	 * 
	 * @return 그룹 관리 매니저
	 */
	public GroupManagement getManager() {
		return manager;
	}

	/**
	 * 그룹을 관리하는 매니저를 갱신한다.
	 * 
	 * @param manager
	 *            그룹 관리 매니저
	 */
	public void setManager(GroupManagement manager) {
		this.manager = manager;
	}

	/**
	 * 회원의 친구 요청 대기 목록을 반환한다.
	 * 
	 * @return 친구요청 대기목록
	 */
	public HashMap<String, List<String>> getStandBy() {
		return standByFriend;
	}

	/**
	 * 회원의 친구 요청 대기목록을 갱신한다.
	 * 
	 * @param standByFriend
	 *            친구 요청 대기 목록
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
		/* 그룹 매니저를 생성 */

		MemberController.getInstance().setManager(new MemberManagement());
		MemberManagement tempM = MemberController.getInstance().getManager();
		
		Userable temp1 = new Member("kjs", "1234", "김지수");
		Userable temp2 = new Member("kyw", "2222", "김예원");
		Userable temp3 = new Member("jhc", "3333", "장형철");
		Userable temp4 = new Member("hyj", "5555", "한유진");
		/* 회원 3명을 생성 */
		
		tempM.add(temp1);
		tempM.add(temp2);
		tempM.add(temp3);
		tempM.add(temp4);

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
		mlist.add(temp1);
		mlist.add(temp2);
		mlist.add(temp3);
		/* 친구들을 여러명 지정한 리스트를 생성 */

		groupCon.requestFriend("hyj", "kjs");
		groupCon.requestFriend("jhc", "kjs");
		System.out.println(groupCon.getMyStandByFriend("hyj"));
		System.out.println(groupCon.getMyStandByFriend("kjs"));
		// System.out.println(manager);
		// manager.addGroup(temp4, "렉토피아", mlist.getMemberList());
		/* 그룹 명을 지정하고 그룹에 넣을 친구들을 선택하여 처음 그룹을 생성 */

		groupCon.responseFriend("kjs", "hyj", false);
		System.out.println(manager);
		System.out.println(groupCon.getMyStandByFriend("kjs"));
		
		groupCon.requestGroup("kjs", "jhc", "오아시스");
		System.out.println(groupCon.getMyStandByFriend("jhc"));
		System.out.println(groupCon.getMyStandByGroup("jhc").get(0)[0] + groupCon.getMyStandByGroup("jhc").get(0)[1]);
		groupCon.responseGroup("jhc", "kjs", "오아시스", true);
		System.out.println(manager);
		
		groupCon.requestGroup("kjs", "jhc", "오아시스");
		//System.out.println(groupCon.getMyStandByGroup("jhc").get(0)[0] + groupCon.getMyStandByGroup("jhc").get(0)[1]);
		
		// manager.removeGroup("kjs", "오아시스");
		/* kjs아이디의 회원이 그룹을 삭제 한다. */

		// System.out.println(manager);

		// manager.removeFriend("kjs", "kyw");
		/* 친구를 삭제 */

		// System.out.println(manager);

	}
}
