package com.theOasis.member.languageBuddy;
/**
 * language buddy profile
 * 서비스를 이용하려면 반드시 등록을 해야한다.
 * 자기소개,모국어,배우고싶은언어를 등록한다.
 * @author yewon
 *
 */
public class LanguageBuddyProfile {
	/**
	 * 자기 소개
	 */
	private String introduction;
	/**
	 * 모국어
	 */
	private String motherTongue;
	/**
	 * 배우고 싶은 언어
	 */
	private String wannaLearn;
	
	public LanguageBuddyProfile()
	{
		
	}
	public LanguageBuddyProfile(String introduction, String motherTongue, String wannaLearn)
	{
		this.introduction = introduction;
		this.motherTongue = motherTongue;
		this.wannaLearn = wannaLearn;
	}
	/**
	 * 자기소개를 리턴한다.
	 * @return 자기소개
	 */
	public String getIntroduction() {
		return introduction;
	}
	/**
	 * 자기소개를 설정한다.
	 * @param introduction 설정할 자기소개
	 */
	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}
	/**
	 * 모국어를 리턴한다.
	 * @return 모국어
	 */
	public String getMotherTongue() {
		return motherTongue;
	}
	/**
	 * 모국어를 설정한다.
	 * @param motherTongue 설정할 모국어
	 */
	public void setMotherTongue(String motherTongue) {
		this.motherTongue = motherTongue;
	}
	/**
	 * 배우고 싶은 언어를 리턴한다.
	 * @return 배우고싶은 언어
	 */
	public String getWannaLearn() {
		return wannaLearn;
	}
	/**
	 * 배우고 싶은언어를 설정한다.
	 * @param wannaLearn 설정할 배우고싶은 언어.
	 */
	public void setWannaLearn(String wannaLearn) {
		this.wannaLearn = wannaLearn;
	}
	@Override
	public String toString() {
		return "LanguageBuddyProfile [introduction=" + introduction
				+ ", motherTongue=" + motherTongue + ", wannaLearn="
				+ wannaLearn + "]";
	}
	
}
