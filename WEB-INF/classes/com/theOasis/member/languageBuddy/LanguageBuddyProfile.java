package com.theOasis.member.languageBuddy;
/**
 * language buddy profile
 * ���񽺸� �̿��Ϸ��� �ݵ�� ����� �ؾ��Ѵ�.
 * �ڱ�Ұ�,�𱹾�,��������� ����Ѵ�.
 * @author yewon
 *
 */
public class LanguageBuddyProfile {
	/**
	 * �ڱ� �Ұ�
	 */
	private String introduction;
	/**
	 * �𱹾�
	 */
	private String motherTongue;
	/**
	 * ���� ���� ���
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
	 * �ڱ�Ұ��� �����Ѵ�.
	 * @return �ڱ�Ұ�
	 */
	public String getIntroduction() {
		return introduction;
	}
	/**
	 * �ڱ�Ұ��� �����Ѵ�.
	 * @param introduction ������ �ڱ�Ұ�
	 */
	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}
	/**
	 * �𱹾 �����Ѵ�.
	 * @return �𱹾�
	 */
	public String getMotherTongue() {
		return motherTongue;
	}
	/**
	 * �𱹾 �����Ѵ�.
	 * @param motherTongue ������ �𱹾�
	 */
	public void setMotherTongue(String motherTongue) {
		this.motherTongue = motherTongue;
	}
	/**
	 * ���� ���� �� �����Ѵ�.
	 * @return ������� ���
	 */
	public String getWannaLearn() {
		return wannaLearn;
	}
	/**
	 * ���� ������ �����Ѵ�.
	 * @param wannaLearn ������ ������� ���.
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
