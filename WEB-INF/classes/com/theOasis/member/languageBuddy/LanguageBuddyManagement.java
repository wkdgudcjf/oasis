package com.theOasis.member.languageBuddy;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import com.theOasis.member.Member;
import com.theOasis.member.Userable;

/**
 * language buddy�� �����ϴ� Ŭ����
 * �� Ŭ������ ȸ������ language buddy ���񽺸� ���ϰ� �̿��� �� �ֵ���
 * �پ��� �޼ҵ带 �����ϴ� Ŭ�����̴�.
 * �ڽŰ� ������ �´� �ٸ�ȸ���� ��õ���־� ������ ���ϰ� �� ������ �� �ִ�.
 * language buddy�� ����� ȸ����� �ξ��� ȸ������ ���� �����Ͽ� 
 * �����Ī ���񽺸� �����Ѵ�.
 * 
 * @author yewon
 *
 */
public class LanguageBuddyManagement {
	/**
	 * language buddy join list
	 * ���� ���� �ִ� ������� ��� ���� �ʰ�ü
	 */
	private HashMap<String, String> joinList;
	/**
	 * language buddy �� ����� ȸ�� ���
	 */
	private LanguageBuddyList languageBuddies;
	
	public LanguageBuddyManagement()
	{
		this(null,null);
	}
	public LanguageBuddyManagement(HashMap<String, String> joinList)
	{
		this(joinList,null);
	}
	public LanguageBuddyManagement(LanguageBuddyList languageBuddies)
	{
		this(null, languageBuddies);
	}
	public LanguageBuddyManagement(HashMap<String, String> joinList,LanguageBuddyList languageBuddies )
	{
		if(joinList!=null)
			this.joinList = joinList;
		else
			this.joinList = new HashMap<String, String>();
		
		if(languageBuddies!=null)
			this.languageBuddies = languageBuddies;
		else
			this.languageBuddies = new LanguageBuddyList();
		
	}
	/**
	 * �ش� ȸ������ �´� language buddy ��õ ����� �����մϴ�. 
	 * ���� ����� ȸ������� �����Ͽ� �����մϴ�.
	 * @param profile ������
	 * @return ��õ ���
	 */
	public List<LanguageBuddy> recommendedList(LanguageBuddyProfile profile)
	{
		if(profile==null)
			return null;
		
		LinkedList<LanguageBuddy> list = new LinkedList<LanguageBuddy>();
		for(LanguageBuddy lb :languageBuddies.getList())
		{
			if(joinList.containsKey(lb.getMe().getId()))
				continue;
			if(lb.getProfile()==null)
				continue;
			if(lb.getProfile().getMotherTongue().equals(profile.getWannaLearn()))
			{
				if(lb.getProfile().getWannaLearn().equals(profile.getMotherTongue()))
				{
					list.add(lb);
				}
			}
		}
		return list;
	}
	/**
	 * �ش� ȸ������ �´� language buddy ��õ ����� �����մϴ�.
	 * ���� ����� ȸ������� �������� �����Ͽ� �����մϴ�.
	 * @param id ȸ���� id
	 * @return ��õ ���
	 */
	public List<LanguageBuddy> recommendedList(String id)
	{
		return recommendedList(languageBuddies.get(id));
	}
	/**
	 * �ش� ȸ������ �´� language buddy ��õ ����� �����մϴ�.
	 * ���� ����� ȸ������� �����Ͽ� �����մϴ�.
	 * @param me ȸ��
	 * @return ��õ ���
	 */
	public List<LanguageBuddy> recommendedList(Userable me)
	{
		if(me!=null)
			return recommendedList(languageBuddies.get(me.getId()));
		else return null;
	}
	/**
	 * �ش� ȸ������ �´� language buddy ��õ ����� �����մϴ�.
	 * ���� ����� ȸ������� �����Ͽ� �����մϴ�.
	 * @param lb language buddy ȸ��
	 * @return ��õ ���
	 */
	public List<LanguageBuddy> recommendedList(LanguageBuddy lb)
	{
		if(lb!=null)
			return recommendedList(lb.getProfile());
		return null;
	}
	/**
	 * language buddy ���踦 �����ϴ�.
	 * @param id ȸ���� ���̵�
	 * @param lbId ȸ���� language buddy�� id
	 * @return ���� ����
	 */
	public boolean breakLb(String id, String lbId)
	{
		LanguageBuddy me = languageBuddies.get(id);
		LanguageBuddy buddy = languageBuddies.get(lbId);
		if((me==null)||(lbId==null))
			return false;
		if(me.getBuddy().getId().equals(lbId)&&buddy.getBuddy().getId().equals(id))
		{
			joinList.remove(id);
			joinList.remove(lbId);
			me.setBuddy(null);
			buddy.setBuddy(null);
			return true;
		}
		return false;
	}
	/**
	 * language buddy ���踦 �����ϴ�.
	 * @param id ȸ���� ���̵�
	 * @return ���� ����
	 */	
	public boolean breakLb(String id)
	{
		if(joinList.containsKey(id))
			return breakLb(id, joinList.get(id));
		return false;
	}

	/**
	 * language buddy ���踦 �����ϴ�.
	 * @param me ȸ��
	 * @return ���� ����
	 */
	public boolean breakLb(Userable me)
	{
		if(me!=null)
			return breakLb(me.getId());
		return false;
	}
	/**
	 * language buddy ���踦 �����ϴ�.
	 * @param lb ȸ��
	 * @return ���� ����
	 */
	public boolean breakLb(LanguageBuddy lb)
	{
		if(lb!=null)
			return breakLb(lb.getMe().getId());
		return false;
	}
	/**
	 * language buddy ���踦 �ν��ϴ�.
	 * ��û�� �����Ǹ� ��Ī��Ͽ� ��Ͻ�ŵ�ϴ�.
	 * @param id ȸ���� id
	 * @param lbId ���ϴ� language buddy�� id
	 * @return ���� ����
	 */
	public boolean makeLb(String id, String lbId)
	{
		if(joinList.containsKey(id))
		{
			if(!joinList.get(id).equals(lbId))
				return false;
			else
				return true;
		}
		if(joinList.containsKey(lbId))
		{
			if(!joinList.get(lbId).equals(id))
				return false;
		}
				
		LanguageBuddy me = languageBuddies.get(id);
		LanguageBuddy you = languageBuddies.get(lbId);
		if((me!=null)&&(you!=null))
		{
			joinList.put(id, lbId);
			joinList.put(lbId, id);
			me.setBuddy(you.getMe());
			you.setBuddy(me.getMe());
			return true;
		}
		return false;
	}
	/**
	 * language buddy ���踦 �ν��ϴ�
	 * ��û�� �����Ǹ� ��Ī��Ͽ� ��Ͻ�ŵ�ϴ�.
	 * @param me ȸ��
	 * @param buddy ���ϴ� language buddy
	 * @return ���� ����
	 */
	public boolean makeLb(Userable me, Userable buddy)
	{
		return makeLb(me.getId(), buddy.getId());
	}
	/**
	 * language buddy ���踦 �ν��ϴ�. 
	 * ��û�� �����Ǹ� ��Ī��Ͽ� ��Ͻ�ŵ�ϴ�.
	 * @param me ȸ��
	 * @param buddy ���ϴ� language buddy
	 * @return ���� ����
	 */
	public boolean makeLb(LanguageBuddy me, LanguageBuddy buddy)
	{
		return makeLb(me.getMe().getId(), buddy.getMe().getId());
	}
	/**
	 * language buddy ���񽺸� ��û�մϴ�.
	 * ���� ��� ��Ͽ� ȸ���� ��Ͻ�ŵ�ϴ�.
	 * @param user ȸ��
	 * @param introduction �ڱ� �Ұ�
	 * @param motherTongue �𱹾�
	 * @param wannaLearn ���� ���� ���
	 * @return ���� ����
	 */
	public boolean enroll(Userable user, String introduction, String motherTongue, String wannaLearn)
	{
		return enroll(user, new LanguageBuddyProfile(introduction, motherTongue, wannaLearn));
	}
	/**
	 * language buddy ���񽺸� ��û�մϴ�.
	 * ���� ��� ��Ͽ� ȸ���� ��Ͻ�ŵ�ϴ�.
	 * @param user ȸ��
	 * @param profile ������
	 * @return ���� ����
	 */
	public boolean enroll(Userable user, LanguageBuddyProfile profile)
	{
		if((user==null)||(profile==null))
			return false;
		if(languageBuddies.get(user.getId())!=null)
			return false;
		languageBuddies.add(user, profile);
		return true;
	}
	/**
	 * language buddy ���񽺸� Ż���մϴ�.
	 * ���� ��� ��Ͽ� ȸ���� ������ŵ�ϴ�.
	 * @param id ȸ���� id
	 * @return ���� ����
	 */
	public boolean unenroll(String id){
		if(joinList.containsKey(id))
		{
			String temp = joinList.get(id);
			joinList.remove(temp);
			joinList.remove(id);
			breakLb(id);
		}
		languageBuddies.remove(id);
		return true;
	}
	/**
	 * language buddy ���񽺸� Ż���մϴ�.
	 *  ���� ��� ��Ͽ� ȸ���� ������ŵ�ϴ�.
	 * @param me ȸ��
	 * @return ���� ����
	 */
	public boolean unenroll(Userable me)
	{
		return unenroll(me.getId());
	}
	/**
	 * language buddy ���񽺸� Ż���մϴ�.
	 *  ���� ��� ��Ͽ� ȸ���� ������ŵ�ϴ�.
	 * @param me ȸ��
	 * @return ���� ����
	 */
	public boolean unenroll(LanguageBuddy me)
	{
		return unenroll(me.getMe().getId());
	}
	public HashMap<String, String> getJoinList() {
		return joinList;
	}
	public void setJoinList(HashMap<String, String> joinList) {
		this.joinList = joinList;
	}
	public LanguageBuddyList getLanguageBuddies() {
		return languageBuddies;
	}
	public void setLanguageBuddies(LanguageBuddyList languageBuddies) {
		this.languageBuddies = languageBuddies;
	}
	/**
	 * �ڽ��� �������� ������ �� �ֽ��ϴ�.
	 * ����� �Ǿ����� �ʴٸ� false�� �����մϴ�.
	 * @param id ȸ��
	 * @param introduction �ڱ�Ұ�
	 * @param motherTongue �𱹾�
	 * @param wannaLearn ���� ���� ���
	 * @return ���漺������
	 */
	public boolean modifyProfile(String id,String introduction,String motherTongue,String wannaLearn)
	{
		if(languageBuddies.get(id)==null)
			return false;
		
		languageBuddies.get(id).setProfile(new LanguageBuddyProfile(introduction, motherTongue, wannaLearn));
		return true;
	}
	public static void main(String[] args) {
		LanguageBuddyManagement manager = new LanguageBuddyManagement();
		manager.enroll(new Member("yewon","1234","����"),"���Ұ�","�ѱ���","����");
		manager.enroll(new Member("Jisu","1234","����"),"���Ұ�","����","�ѱ���");
		manager.enroll(new Member("hc","1234","��ö"),"���Ұ�","����","�ѱ���");
		System.out.println(manager.recommendedList("yewon"));
		manager.makeLb("hc", "Jisu");
		System.out.println(manager.recommendedList("yewon"));
		//manager.breakLb("hc");
		//System.out.println(manager.recommendedList("yewon"));
		manager.unenroll("hc");
		System.out.println(manager.recommendedList("yewon"));
	}
}
