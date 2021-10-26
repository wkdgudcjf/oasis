package com.theOasis.controller;

import java.util.*;

import com.theOasis.member.*;
import com.theOasis.profileIO.ProfileIO;

/**
 * 클라이언트와 통신매개체 역할을 하는 클래스.
 * 회원을 관리하는 매니저를 멤버로 갖고 있으며, 모델 객체를 가공시켜 반환하는 역할을 한다.
 * 
 * 기본적으로 모든 파라미터는 문자열 데이터로 종속성을 줄여준다.
 * 회원을 회원 가입, 로그인 시 확인, 탈퇴, 수정의 역할을 한다.
 * @author jisu
 *
 */
public class MemberController {
	
	/**
	 * 회원 관리
	 */
	private MemberManagement manager;
	private static MemberController memberCon = new MemberController();
	/**
	 * default 생성자
	 */
	private MemberController(){
		
	}
	
	public static MemberController getInstance(){
		return memberCon;
	}
	/**
	 * 회원을 관리하는 매니저를 전달받아 객체를 생성하는 생성자
	 * @param manager 회원 관리역할을 하는 매니저
	 */
	public MemberController(MemberManagement manager){
		this.manager = manager;
	}
	
	/**
	 * 찾으려는 회원의 아이디 혹은 이름을 전달받아 해당하는 회원을 반환한다.
	 * @param info 회원을 검색할 카테고리가 아이디인지 이름인지
	 * @param data 해당하는 아이디 혹은 이름의 정보
	 * @return 아이디나 이름에 해당하는 회원목록(이름의 경우 중복의 가능성이 있기 때문에) String[] 의 순서는 아이디, 이름, 생일, 사진
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
	 * 회원의 아이디를 전달받아 해당하는 회원을 탈퇴시킨다.
	 * @param id 탈퇴하려는 회원의 아이디
	 */
	public void remove(String id){
		manager.remove(id);
	}
	
	/**
	 * 회원에 대한 정보를 전달받아 해당하는 회원을 탈퇴시킨다.
	 * 전달인자로는 member에 대한 아이디, 비밀번호, 이름을 받는다.
	 * @param member 회원의 정보
	 */
	public void remove(String[] member){
		Userable user = new Member(member[0],member[1],member[2]);
		manager.remove(user);
	}
	
	/**
	 * 회원의 아이디와 비밀번호를 받아 비밀번호를 수정한다.
	 * @param id 비밀번호를 수정할 회원의 아이디
	 * @param password 수정할 비밀번호
	 */
	public void modify(String id, String password){
		manager.modify(id, password);
	}
	
	/**
	 * 회원의 아이디와 변경하려는 회원의 정보를 전달받아 해당 회원을 변경한다.
	 * 아이디와  프로필 정보명, 프로필 정보, 프로필 공개범위를 String[]로 받는다.
	 * @param id 회원의 아이디
	 * @param member 변경하려고 하는 정보들
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
	 * 회원의 아이디를 전달받아 해당하는 회원이 존재하는지 검사한다.
	 * @param id 회원의 아이디
	 * @return 존재 여부
	 */
	public boolean check(String id){

		return manager.check(id);
	}
	
	/**
	 * 회원의 아이디와 비밀번호를 전달받아 해당하는 회원이 존재하는지 검사한다.
	 * @param id 회원의 아이디
	 * @param password 회원의 비밀번호
	 * @return 존재 여부
	 */
	public boolean check(String id, String password){
		return manager.check(id, password);
	}
	
	/**
	 * 회원의 아이디와 비밀번호를 전달받아 회원가입 한다.
	 * @param id 회원의 아이디
	 * @param password 회원의 비밀번호
	 */
	public void add(String id, String name, String password){
		manager.add(id, name, password);
		GroupController.getInstance().getManager().put(id);
		LanguageBuddyController.getInstance().put(id);
		GroupController.getInstance().putStandByFriend(id);
		GroupController.getInstance().putStandByGroup(id);
	}
	
	/**
	 * 회원의 정보들을 전달받아 회원가입 한다.
	 * id, password, name, birthday, phoneNumber, question, answer 
	 * @param member 회원의 정보들
	 * @return 성공, 실패 여부
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
     * 회원을 관리하는 매니저를 반환한다.
     * @return 회원 관리 역할을 하는 객체
     */
	public MemberManagement getManager() {
		return manager;
	}

	/**
	 * 회원을 관리하는 매니저 객체를 갱신한다.
	 * @param manager 회원 관리 역할을 하는 객체
	 */
	public void setManager(MemberManagement manager) {
		this.manager = manager;
	}
	public TheOasisMember search(String id)
	{
		return (TheOasisMember)manager.getMList().get(MemberInfo.ID, id).get(0);
	}
}
