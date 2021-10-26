package com.theOasis.member;

import java.util.*;

/**
 * 회원이 갖고 있는 그룹을 모두 보여주기 위한 그룹 목록을 다루는 클래스
 * @author jisu
 *
 */
public class GroupList {
	
	/**
	 * 그룹목록
	 */
	private List<MemberList> groupList;
	
	/**
	 * 생성자
	 */
	public GroupList(){
		groupList = new LinkedList<MemberList>();
	}
	
	/**
	 * 그룹 목록을 받아서 그룹목록을 생성
	 * @param groupList
	 */
	public GroupList(List<MemberList> groupList){
		this.groupList = groupList;
	}
	
	/**
	 * 그룹을 전달받아 그룹 목록에 추가한다.
	 * @param group 그룹
	 */
	public void add(MemberList group){
		if(group!=null){
		if(!contains(group)){
		groupList.add(group);
		}
		//성공 실패 여부
		}
	}
	
	/**
	 * 그룹 이름을 전달받아 그룹 목록에 추가한다.
	 * @param groupName 그룹명
	 */
	public void add(String groupName){
		if(groupName!=null){
		if(!contains(groupName)){
		groupList.add(new Group(groupName));
		//성공 실패 여부
		}
		}
	}
	
	/**
	 * 그룹 이름과 친구들을 전달받아 그룹을 만들어 그룹목록에 추가한다.
	 * @param groupName 그룹명
	 * @param friends 그룹에 추가할 친구들
	 */
	public void add(String groupName, List<Userable> friends){
		if(groupName!=null){
		if(!contains(groupName)){
		groupList.add(new Group(friends, groupName));
		//성공 실패 여부
		    }
		else{
			for(Userable user : friends){
				get(groupName).add(user);
				}
			}
		}
	}
	
	/**
	 * 그룹이름으로 그룹을 찾아 전달받은 친구를 초대한다.
	 * @param groupName 친구를 초대할 그룹명
	 * @param friend 그룹에 초대할 친구
	 */
	public void add(String groupName, Userable friend){
		if(friend!=null){
			if(get(groupName)!=null){
				if(!contains(groupName)){
		get(groupName).add(friend);
		//성공 실패 여부
		}
			}
		}
	}
	/**
	 * 그룹을 전달받아 그룹목록에서 찾은 뒤 삭제한다.
	 * @param group 그룹 객체
	 */
	public void remove(MemberList group){
		groupList.remove(((Group)group).getGroupName());
		//성공 실패 여부
	}
	
	/**
	 * 삭제하고 싶은 index를 전달받아 그룹목록에서 찾은 뒤 삭제한다.
	 * @param index List의 index
	 */
	public void remove(int index){
		groupList.remove(index);
		//성공 실패 여부
	}
	
	/**
	 * 삭제하고 싶은 그룹명을 전달받아 그룹목록에서 찾은 뒤 삭제한다.
	 * @param groupName 그룹명
	 */
	public void remove(String groupName){
		groupList.remove(get(groupName));
		//성공 실패 여부
	}
	
	/**
	 * 그룹을 전달받아 해당 그룹의 이름이 그룹 목록에 존재하는지 여부를 알린다.
	 * @param group 이름의 존재 여부를 확인 할 그룹
	 * @return 존재 여부
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
	 * 그룹 이름을 전달받아 해당 그룹의 이름이 그룹 목록에 존재하는지 여부를 알린다.
	 * @param groupName 이름의 존재 여부를 확인 할 그룹 명
	 * @return 존재 여부
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
	 * 그룹명을 전달받아 그룹목록에서 해당하는 그룹을 반환한다.
	 * @param groupName 그룹명
	 * @return 그룹객체
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
	 * index를 전달받아 그룹목록에서 해당하는 그룹을 반환한다.
	 * @param index List의 index
	 * @return 그룹 객체
	 */
	public MemberList get(int index){
		return groupList.get(index);
	}
	
	/**
	 * 그룹목록을 모두 삭제한다.
	 */
	public void clear(){
		groupList.clear();
		
	}
	
	/**
	 * 그룹목록의 크기를 반환한다.
	 * @return 정수형 숫자
	 */
	public int size(){
		return groupList.size();
	}
	
	/**
	 * 그룹 목록을 반환하는 메소드
	 * @return 그룹 목록을 반환
	 */
	public List<MemberList> getGroupList(){
		return groupList;
	}
	
	/**
	 * 그룹 목록을 갱신하는 메소드
	 * @param groupList 그룹 목록
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
		groupList.add("lec",new Member("kjs","1234","김지수"));
		MemberList group3 = new Group("lec3");
		groupList.add(group3);
		MemberList group = new Group();
		Userable temp = new Member("kjs","1234","김지수");
		Userable temp2 = new Member("kyw","1234","김예원");
		Userable temp3 = new Member("hyj","3333","한유진");
		group.add(temp);
		group.add("jhc","2222","장형철");
		groupList.add("lec", group.getMemberList());
		//System.out.println(groupList);
		
		MemberList group2 = new Group();
		group2.add(temp2);
		group2.add(temp);
		groupList.add("lec",group2.getMemberList());
		System.out.println(groupList);
		groupList.add("lec2",group2.getMemberList());
		System.out.println(groupList);
		System.out.println("----------------------------그룹 추가 Test ---------------------------");
		groupList.remove("lec2");
		groupList.remove(group3);
		System.out.println(groupList);
		System.out.println("----------------------------그룹 삭제 Test ---------------------------");
		
		System.out.println(groupList.get("lec"));
		System.out.println("----------------------------그룹 get Test ---------------------------");
	}
}
