package com.theOasis.controller;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import com.theOasis.member.GroupManagement;
import com.theOasis.member.Member;
import com.theOasis.member.MemberManagement;
import com.theOasis.member.TheOasisMember;
import com.theOasis.member.Userable;
import com.theOasis.member.languageBuddy.LanguageBuddy;
import com.theOasis.member.languageBuddy.LanguageBuddyList;
import com.theOasis.member.languageBuddy.LanguageBuddyManagement;
import com.theOasis.member.languageBuddy.LanguageBuddyProfile;
/**
 * Ŭ���̾�Ʈ�� �Ű�ü������ �ϴ� Ŭ����
 * language buddy�� �����ϴ� �Ŵ����� �ʵ�� ������ ������ �Ŵ����� �޼ҵ带 ����
 * ���Ϲ��� �������� ���� ���� �������� �����Ѵ�.
 * 
 * �⺻������ ��� �Ķ���ʹ� ���ڿ� �����ͷ� ���Ӽ��� �ٿ��ش�.
 * �� Ŭ������ ȸ������ language buddy ���񽺸� ���ϰ� �̿��� �� �ֵ���
 * �پ��� �޼ҵ带 �����ϴ� Ŭ�����̴�.
 * �ڽŰ� ������ �´� �ٸ�ȸ���� ��õ���־� ������ ���ϰ� �� ������ �� �ִ�.
 * language buddy�� ����� ȸ����� �ξ��� ȸ������ ���� �����Ͽ� 
 * �����Ī ���񽺸� �����Ѵ�.
 * 
 * @author JHC
 *
 */
public class LanguageBuddyController
{
	private LanguageBuddyManagement manager;
	private HashMap<String, List<String>> requestList;
	
	
	private static LanguageBuddyController instance;
	static{
		instance = new LanguageBuddyController();
	}
	
	private LanguageBuddyController()
	{
		this.requestList = new HashMap<String, List<String>>();
	}
	
	private boolean check(String id)
	{
		return MemberController.getInstance().getManager().check(id);
	}
	public List<String> getRequest(String id)
	{
		if(!check(id))
			return null;
		
		if(requestList.containsKey(id))
			return requestList.get(id);
		else
			return null;
	}
	public void addRequest(String requestReceiver, String requestSender)
	{
		if(!(check(requestReceiver)&&check(requestSender)))
			return ;
		
		if(requestList.containsKey(requestReceiver))
			requestList.get(requestReceiver).add(requestSender);
		else
		{
			requestList.put(requestReceiver, new LinkedList<String>());
			requestList.get(requestReceiver).add(requestSender);
		}
	}
	
	public void put(String id){
		requestList.put(id, new LinkedList<String>());
	}
	
	public void response(boolean isAccept, String requestReceiver, String requestSender)
	{
		if(!(check(requestReceiver)&&check(requestSender)))
			return ;
		
		requestList.get(requestReceiver).remove(requestSender);
		if(isAccept)
		{
			manager.makeLb(requestReceiver, requestSender);
		}
	}
	
	public LanguageBuddyManagement getManager() {
		return manager;
	}
	public void setManager(LanguageBuddyManagement manager) {
		this.manager = manager;
	}
	public static LanguageBuddyController getInstance() {
		if(instance==null)
			instance = new LanguageBuddyController();
		return instance;
	}
	public static void main(String[] args) {
		MemberController.getInstance().setManager(new MemberManagement());
		MemberController.getInstance().getManager().add("yewon","yewon", "1234");
		MemberController.getInstance().getManager().add("jisu","jisu", "1234");
		MemberController.getInstance().getManager().add("hc","hc", "1234");
		
		LanguageBuddyManagement manager = new LanguageBuddyManagement();
		manager.enroll(new Member("yewon","1234","����"),"���Ұ�","�ѱ���","����");
		manager.enroll(new Member("jisu","1234","����"),"���Ұ�","����","�ѱ���");
		manager.enroll(new Member("hc","1234","��ö"),"���Ұ�","����","�ѱ���");
		
		LanguageBuddyController.getInstance().setManager(manager);
		LanguageBuddyController.getInstance().addRequest("jisu", "yewon");
		LanguageBuddyController.getInstance().addRequest("jisu", "hc");
		System.out.println(LanguageBuddyController.getInstance().getRequest("jisu"));
		LanguageBuddyController.getInstance().response(false, "jisu", "hc");
		System.out.println(LanguageBuddyController.getInstance().getRequest("jisu"));
		LanguageBuddyController.getInstance().response(true, "jisu", "yewon");
		System.out.println(LanguageBuddyController.getInstance().getRequest("jisu"));
		System.out.println(LanguageBuddyController.getInstance().getManager().getJoinList());
		/*
		System.out.println(manager.recommendedList("yewon"));
		manager.makeLb("hc", "Jisu");
		System.out.println(manager.recommendedList("yewon"));
		//manager.breakLb("hc");
		//System.out.println(manager.recommendedList("yewon"));
		manager.unenroll("hc");
		System.out.println(manager.recommendedList("yewon"));
		*/
	}
	/*
	/**
	 * �ش� ȸ������ �´� language buddy ��õ ����� �����մϴ�. 
	 * ���� ����� ȸ������� �������� �����Ͽ� �����մϴ�.
	 * @param id ȸ���� id
	 * @return ��õ ���
	 */
	/*
	public List<String[]> recommendedList(String id)
	{
		/*
		 * return ���� null�̸� �ش� ȸ���� �������� ����.
		 * return�� list�� ����ִٸ� ������ ������ �ȵǾ��ְų� ��õ ����� ����
		 *//*
		List<String[]> re = new LinkedList<String[]>();
		if(MemberController.getInstance().check(id))
		{
			List<LanguageBuddy> list = manager.recommendedList(id);
			if(list!=null)
			{
				for(LanguageBuddy temp : list)
				{
					/*
					 * ����, id, �̸�, �ڱ�Ұ�
					 *//*
					re.add(new String[]{((TheOasisMember)temp.getMe()).getProfile().getMainImage(),temp.getMe().getId(), ((Member)temp.getMe()).getName(), temp.getProfile().getIntroduction()});
				}
			}
			return re;
		}
		else
			return null;
	}*/
	/*
	/**
	 * language buddy ���踦 �����ϴ�.
	 * ���谡 �������� ��ϸ���Ʈ�� ȸ������ �ٽ� ��Ͻ����ݴϴ�.
	 * @param id ȸ���� ���̵�
	 * @return ���� ����
	 *//*	
	public boolean breakLb(String id)
	{
		if(MemberController.getInstance().check(id))
		{
			return manager.breakLb(id);
		}
		return false;
	}
	*//*
	/**
	 * language buddy ���踦 �ν��ϴ�.
	 * ��û�� �����Ǹ� ��Ī��Ͽ� ��Ͻ�ŵ�ϴ�.
	 * @param id ȸ���� id
	 * @param lbId ���ϴ� language buddy�� id
	 * @return ���� ����
	 *//*
	public boolean makeLb(String id, String lbId)
	{
		if(MemberController.getInstance().check(id))
		{
			return manager.makeLb(id, lbId);
		}
		return false;
	}
	*//*
	/**
	 * language buddy ���񽺸� ��û�մϴ�.
	 * ���� ��� ��Ͽ� ȸ���� ��Ͻ�ŵ�ϴ�.
	 * @param user ȸ��
	 * @param introduction �ڱ� �Ұ�
	 * @param motherTongue �𱹾�
	 * @param wannaLearn ���� ���� ���
	 * @return ���� ����
	 *//*
	public boolean enroll(Userable user, String introduction, String motherTongue, String wannaLearn)
	{
		if(MemberController.getInstance().check(user.getId(), user.getPassword()))
		{
			return manager.enroll(user, introduction, motherTongue, wannaLearn);
		}
		return false;
	}
	*//*
	/**
	 * language buddy ���񽺸� Ż���մϴ�.
	 * ���� ��� ��Ͽ� ȸ���� ������ŵ�ϴ�.
	 * @param id ȸ���� id
	 * @return ���� ����
	 *//*
	public boolean unenroll(String id){
		if(MemberController.getInstance().check(id))
		{
			return manager.unenroll(id);
		}
		return false;
	}*//*
	/**
	 * �� ����ģ�� ���̵� �����մϴ�.
	 * @param id
	 * @return ����ģ�����̵�
	 *//*
	public String getBuddy(String id)
	{
		if(MemberController.getInstance().check(id))
		{
			return manager.getLanguageBuddies().get(id).getBuddy().getId();
		}
		return null;
	}
	public HashMap<String, String> getJoinList() {
		return manager.getJoinList();
	}*/
	/*
	/**
	 * ��ϵ� ��� ����ȸ���� �����Ѵ�
	 * @return
	 *//*
	public List<String[]> getAllbuddy()
	{
		/*
		 * ����, ���̵�, �̸�, �ڱ�Ұ�, �𱹾�, ���� ���� ���
		 *//*
		LanguageBuddyList list = manager.getLanguageBuddies();
		List<String[]> re = new LinkedList<String[]>();
		for(LanguageBuddy temp : list.getList())
			re.add(new String[]{((TheOasisMember)temp.getMe()).getProfile().getMainImage(),temp.getMe().getId(), ((Member)temp.getMe()).getName(), 
					temp.getProfile().getIntroduction(), temp.getProfile().getMotherTongue(), temp.getProfile().getWannaLearn()
					});
			
		return null;
	}*//*
	/**
	 * �ڽ��� �������� ������ �� �ֽ��ϴ�.
	 * ����� �Ǿ����� �ʴٸ� false�� �����մϴ�.
	 * @param id ȸ��
	 * @param introduction �ڱ�Ұ�
	 * @param motherTongue �𱹾�
	 * @param wannaLearn ���� ���� ���
	 * @return ���漺������
	 *//*
	public boolean modifyProfile(String id,String introduction,String motherTongue,String wannaLearn)
	{
		if(MemberController.getInstance().check(id))
		{
			return manager.modifyProfile(id, introduction, motherTongue, wannaLearn);
		}
		return false;
	}*/

}
