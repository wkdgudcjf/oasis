package com.theOasis.member.languageBuddy;

import com.theOasis.member.Userable;

/**
 * Language buddy.
 * 회원이 등록할 수 있는 서비스이다.
 * 등록만할경우 buddy는 null값을 유지한다.
 * 버디가 맺어진경우 buddy 값이 상대방으로 설정된다.
 * @author yewon
 *
 */
public class LanguageBuddy {
	/**
	 * language buddy 서비스에 가입한 회원
	 */
	private Userable me;
	/**
	 * 회원의 language buddy
	 */
	private Userable buddy;
	/**
	 * 회원의 프로필
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
	 * 내아이디를 리턴한다.
	 * @return 내아이디
	 */
	public Userable getMe() {
		return me;
	}
	/**
	 * 내아이디를 설정한다.
	 * @param me 설정할 아이디
	 */
	public void setMe(Userable me) {
		this.me = me;
	}
	/**
	 * 내버디를 리턴한다.
	 * @return 버디
	 */
	public Userable getBuddy() {
		return buddy;
	}
	/**
	 * 내버디를 설정한다.
	 * @param buddy 설정할 버디
	 */
	public void setBuddy(Userable buddy) {
		this.buddy = buddy;
	}
	/**
	 * 내 버디프로필을 리턴한다.
	 * @return 버디프로필
	 */
	public LanguageBuddyProfile getProfile() {
		return profile;
	}
	/**
	 * 내 버디프로필을 설정한다.
	 * @param profile 설정할 버디프로필
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
