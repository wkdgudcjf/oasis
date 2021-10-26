package com.theOasis.member.languageBuddy;

import java.util.LinkedList;
import java.util.List;

import com.theOasis.member.Userable;

/**
 * language buddy 회원 목록 회원을 추가하거나 삭제 또는 수정과 검색을 할 수 있다. 버디를 등록한 모든 회원은 이 리스트에
 * 등록이 된다.
 * 
 * @author yewon
 * 
 */
public class LanguageBuddyList {
	/**
	 * language buddy 회원 목록
	 */
	private List<LanguageBuddy> list;

	public LanguageBuddyList() {
		this(null);
	}

	public LanguageBuddyList(List<LanguageBuddy> list) {
		if (list != null)
			this.list = list;
		else
			this.list = new LinkedList<LanguageBuddy>();
	}

	public List<LanguageBuddy> getList() {
		return list;
	}

	public void setList(List<LanguageBuddy> list) {
		this.list = list;
	}

	/**
	 * language buddy 서비스를 이용하는 회원을 추가합니다.
	 * 
	 * @param lb
	 *            language buddy를 이용하는 회원
	 */
	public void add(LanguageBuddy lb) {
		/*
		 * parameter로 넘겨받은 language buddy가 null이 아니고 이미 등록된 회원이 아닌 경우에만 add
		 */
		if ((lb != null) && (!list.contains(lb)))
			list.add(lb);
	}

	/**
	 * language buddy 서비스를 이용하는 회원을 추가합니다.
	 * 
	 * @param me
	 *            회원
	 * @param buddy
	 *            회원의 language buddy
	 * @param profile
	 *            회원의 profile
	 */
	public void add(Userable me, Userable buddy, LanguageBuddyProfile profile) {
		if (me != null) {
			list.add(new LanguageBuddy(me, buddy, profile));
		}
	}

	/**
	 * language buddy 서비스를 이용하는 회원을 추가합니다.
	 * 
	 * @param me
	 *            회원
	 * @param profile
	 *            회원의 profile
	 */
	public void add(Userable me, LanguageBuddyProfile profile) {
		if (me != null) {
			list.add(new LanguageBuddy(me, null, profile));
		}
	}

	/**
	 * language buddy 서비스를 이용하는 회원을 추가합니다.
	 * 
	 * @param me
	 *            회원
	 * @param introduction
	 *            자기 소개
	 * @param motherTongue
	 *            모국어
	 * @param wannaLearn
	 *            배우고 싶은 언어
	 */
	public void add(Userable me, String introduction, String motherTongue,
			String wannaLearn) {
		add(me,
				new LanguageBuddyProfile(introduction, motherTongue, wannaLearn));
	}

	/**
	 * language buddy 서비스를 이용하는 회원을 탈퇴시킵니다.
	 * 
	 * @param lb
	 *            회원
	 */
	public void remove(LanguageBuddy lb) {
		if (lb != null)
			remove(lb.getMe().getId());
	}

	/**
	 * language buddy 서비스를 이용하는 회원을 탈퇴시킵니다.
	 * 
	 * @param id
	 *            회원의 id
	 */
	public void remove(String id) {
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getMe().getId().equalsIgnoreCase(id)) {
				list.remove(i);
				i--;
			}
			if (list.get(i).getBuddy() != null) {
				if (list.get(i).getBuddy().getId().equalsIgnoreCase(id)) {
					list.get(i).setBuddy(null);
				}
			}
		}
	}

	/**
	 * language buddy 서비스를 이용하는 회원을 탈퇴시킵니다.
	 * 
	 * @param user
	 *            회원
	 */
	public void remove(Userable user) {
		if (user != null)
			remove(user.getId());
	}

	/**
	 * language buddy 서비스를 이용하는 회원을 탈퇴시킵니다.
	 * 
	 * @param index
	 *            회원 목록의 index
	 */
	public void remove(int index) {
		remove(list.get(index));
	}

	/**
	 * language buddy 서비스를 이용하는 회원을 검색합니다.
	 * 
	 * @param index
	 *            회원 목록의 index
	 * @return 검색된 회원
	 */
	public LanguageBuddy get(int index) {
		return list.get(index);
	}

	/**
	 * language buddy 서비스를 이용하는 회원을 검색합니다.
	 * 
	 * @param id
	 *            회원의 id
	 * @return 검색된 회원
	 */
	public LanguageBuddy get(String id) {
		for (LanguageBuddy lb : list) {
			if (lb.getMe().getId().equals(id))
				return lb;
		}
		return null;
	}
	public void clear()
	{
		list.clear();
	}
	@Override
	public String toString() {
		return "LanguageBuddyList [list=" + list + "]";
	}

}
