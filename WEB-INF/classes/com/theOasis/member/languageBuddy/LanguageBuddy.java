package com.theOasis.member.languageBuddy;

import com.theOasis.member.Userable;

/**
 * Language buddy.
 * ȸ���� ����� �� �ִ� �����̴�.
 * ��ϸ��Ұ�� buddy�� null���� �����Ѵ�.
 * ���� �ξ������ buddy ���� �������� �����ȴ�.
 * @author yewon
 *
 */
public class LanguageBuddy {
	/**
	 * language buddy ���񽺿� ������ ȸ��
	 */
	private Userable me;
	/**
	 * ȸ���� language buddy
	 */
	private Userable buddy;
	/**
	 * ȸ���� ������
	 */
	private LanguageBuddyProfile profile;
	
	public LanguageBuddy()
	{
		
	}
	public LanguageBuddy(Userable me, Userable buddy, LanguageBuddyProfile profile)
	{
		this.me = me;
		this.buddy=buddy;
		this.profile = profile;
	}
	public LanguageBuddy(Userable me, String introduction, String motherTongue, String wannaLearn)
	{
		this(me, null, new LanguageBuddyProfile(introduction, motherTongue, wannaLearn));
	}
	public LanguageBuddy(Userable me, Userable buddy, String introduction, String motherTongue, String wannaLearn)
	{
		this(me, buddy, new LanguageBuddyProfile(introduction, motherTongue, wannaLearn));
	}
	/**
	 * �����̵� �����Ѵ�.
	 * @return �����̵�
	 */
	public Userable getMe() {
		return me;
	}
	/**
	 * �����̵� �����Ѵ�.
	 * @param me ������ ���̵�
	 */
	public void setMe(Userable me) {
		this.me = me;
	}
	/**
	 * ������ �����Ѵ�.
	 * @return ����
	 */
	public Userable getBuddy() {
		return buddy;
	}
	/**
	 * ������ �����Ѵ�.
	 * @param buddy ������ ����
	 */
	public void setBuddy(Userable buddy) {
		this.buddy = buddy;
	}
	/**
	 * �� ������������ �����Ѵ�.
	 * @return ����������
	 */
	public LanguageBuddyProfile getProfile() {
		return profile;
	}
	/**
	 * �� ������������ �����Ѵ�.
	 * @param profile ������ ����������
	 */
	public void setProfile(LanguageBuddyProfile profile) {
		this.profile = profile;
	}
	@Override
	public String toString() {
		return "LanguageBuddy [me=" + me + ", buddy=" + buddy + ", profile="
				+ profile + "]";
	}
	
}
