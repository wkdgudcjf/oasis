package com.theOasis.member.languageBuddy;

import java.util.LinkedList;
import java.util.List;

import com.theOasis.member.Userable;

/**
 * language buddy ȸ�� ��� ȸ���� �߰��ϰų� ���� �Ǵ� ������ �˻��� �� �� �ִ�. ���� ����� ��� ȸ���� �� ����Ʈ��
 * ����� �ȴ�.
 * 
 * @author yewon
 * 
 */
public class LanguageBuddyList {
	/**
	 * language buddy ȸ�� ���
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
	 * language buddy ���񽺸� �̿��ϴ� ȸ���� �߰��մϴ�.
	 * 
	 * @param lb
	 *            language buddy�� �̿��ϴ� ȸ��
	 */
	public void add(LanguageBuddy lb) {
		/*
		 * parameter�� �Ѱܹ��� language buddy�� null�� �ƴϰ� �̹� ��ϵ� ȸ���� �ƴ� ��쿡�� add
		 */
		if ((lb != null) && (!list.contains(lb)))
			list.add(lb);
	}

	/**
	 * language buddy ���񽺸� �̿��ϴ� ȸ���� �߰��մϴ�.
	 * 
	 * @param me
	 *            ȸ��
	 * @param buddy
	 *            ȸ���� language buddy
	 * @param profile
	 *            ȸ���� profile
	 */
	public void add(Userable me, Userable buddy, LanguageBuddyProfile profile) {
		if (me != null) {
			list.add(new LanguageBuddy(me, buddy, profile));
		}
	}

	/**
	 * language buddy ���񽺸� �̿��ϴ� ȸ���� �߰��մϴ�.
	 * 
	 * @param me
	 *            ȸ��
	 * @param profile
	 *            ȸ���� profile
	 */
	public void add(Userable me, LanguageBuddyProfile profile) {
		if (me != null) {
			list.add(new LanguageBuddy(me, null, profile));
		}
	}

	/**
	 * language buddy ���񽺸� �̿��ϴ� ȸ���� �߰��մϴ�.
	 * 
	 * @param me
	 *            ȸ��
	 * @param introduction
	 *            �ڱ� �Ұ�
	 * @param motherTongue
	 *            �𱹾�
	 * @param wannaLearn
	 *            ���� ���� ���
	 */
	public void add(Userable me, String introduction, String motherTongue,
			String wannaLearn) {
		add(me,
				new LanguageBuddyProfile(introduction, motherTongue, wannaLearn));
	}

	/**
	 * language buddy ���񽺸� �̿��ϴ� ȸ���� Ż���ŵ�ϴ�.
	 * 
	 * @param lb
	 *            ȸ��
	 */
	public void remove(LanguageBuddy lb) {
		if (lb != null)
			remove(lb.getMe().getId());
	}

	/**
	 * language buddy ���񽺸� �̿��ϴ� ȸ���� Ż���ŵ�ϴ�.
	 * 
	 * @param id
	 *            ȸ���� id
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
	 * language buddy ���񽺸� �̿��ϴ� ȸ���� Ż���ŵ�ϴ�.
	 * 
	 * @param user
	 *            ȸ��
	 */
	public void remove(Userable user) {
		if (user != null)
			remove(user.getId());
	}

	/**
	 * language buddy ���񽺸� �̿��ϴ� ȸ���� Ż���ŵ�ϴ�.
	 * 
	 * @param index
	 *            ȸ�� ����� index
	 */
	public void remove(int index) {
		remove(list.get(index));
	}

	/**
	 * language buddy ���񽺸� �̿��ϴ� ȸ���� �˻��մϴ�.
	 * 
	 * @param index
	 *            ȸ�� ����� index
	 * @return �˻��� ȸ��
	 */
	public LanguageBuddy get(int index) {
		return list.get(index);
	}

	/**
	 * language buddy ���񽺸� �̿��ϴ� ȸ���� �˻��մϴ�.
	 * 
	 * @param id
	 *            ȸ���� id
	 * @return �˻��� ȸ��
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
