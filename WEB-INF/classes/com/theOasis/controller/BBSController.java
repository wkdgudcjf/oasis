package com.theOasis.controller;

import java.util.LinkedList;
import java.util.List;

import com.theOasis.member.GroupManagement;
import com.theOasis.member.MemberInfo;
import com.theOasis.member.MemberList;
import com.theOasis.member.MemberManagement;
import com.theOasis.member.Userable;
import com.theOasis.text.Readable;
import com.theOasis.text.Text;
import com.theOasis.text.bbs.BBSItem;
import com.theOasis.text.bbs.BBSManagement;

/**
 * Ŭ���̾�Ʈ�� ��ŸŰ�ü ������ �ϴ� Ŭ����. �Խù��� �����ϴ� �Ŵ����� �ʵ�� ������ ������ �Ŵ����� �޼ҵ带 ���� ���Ϲ��� �������� ����
 * ���� �������� �����Ѵ�.
 * 
 * �⺻������ ��� �Ķ���ʹ� ���ڿ� �����ͷ� ���Ӽ��� �ٿ��ش�. �Խù� ���,�˻�,����,����,������ �����Ѵ�. �Խù����� ������ �Խñ�
 * ����� ���ԵǾ��ִ�.
 * 
 * @author JHC
 * 
 */
public class BBSController {
	/**
	 * �Խù� �����ڷν� ��� �Խù��� �����ϴ� �ʵ�.
	 */
	private BBSManagement manager;
	private static BBSController instance;
	static {
		instance = new BBSController();
	}

	private BBSController() {

	}

	private boolean check(String id) {
		return MemberController.getInstance().getManager().check(id);
	}
	public List<Readable> getMyRecentBBSItem(String id){
		if(manager.search(id)==null)
			return null;
		List<Readable> list = manager.search(id).getList();
		if(list!=null)
			sortByTime(list);
		return list;
	}
	public List<Readable> getMyRecentBBSItem(String id, int num){
		return getMyRecentBBSItem(id).subList(0, num);
	}
	public List<Readable> getMyRecentBBSItem(String id, int startNo, int num){
		return getMyRecentBBSItem(id).subList(startNo, num);
	}
	public List<Readable> getRecentBBSItem(String id) {
		
		if (!check(id))
			return null;
		List<Readable> re = new LinkedList<Readable>();
		MemberList list = GroupController.getInstance().getManager().searchGroup(id).get("ģ��");
		List<Userable> friends = list.getMemberList();
		for (Userable friend : friends) {
			if(manager.search(friend.getId())==null)
				continue;
			re.addAll(manager.search(friend.getId()).getList());
		}
		sortByTime(re);
		return re;
	}
	private void sortByTime(List<Readable> re)
	{
		for (int i = 0; i < re.size(); i++) {
			for (int j = i + 1; j < re.size(); j++) {
				if (((Text) re.get(i)).getTime().before(((Text) re.get(j)).getTime())) 
				{
					Readable temp = re.get(i);
					re.set(i, re.get(j));
					re.set(j, temp);
				}
			}
		}
	}
	public List<Readable> getRecentBBSItem(String id, int num)
	{
		return getRecentBBSItem(id).subList(0, num);
	}
	public List<Readable> getRecentBBSItem(String id, int startNo, int num)
	{
		return getRecentBBSItem(id).subList(startNo, startNo+num);
	}
	public BBSManagement getManager() {
		return manager;
	}

	public void setManager(BBSManagement manager) {
		this.manager = manager;
	}

	public static BBSController getInstance() {
		return instance;
	}
	public static void main(String[] args) {
		MemberController.getInstance().setManager(new MemberManagement());
		MemberController.getInstance().getManager().add("yewon","yewon", "1234");
		GroupController.getInstance().setManager(new GroupManagement());
		GroupController.getInstance().getManager().put("yewon");
		MemberController.getInstance().getManager().add("jisu","jisu", "1234");
		GroupController.getInstance().getManager().put("jisu");
		MemberController.getInstance().getManager().add("hc","hc", "1234");
		GroupController.getInstance().getManager().put("hc");
		GroupController.getInstance().getManager().addFriend(MemberController.getInstance().getManager().search(MemberInfo.ID, "yewon").get(0), MemberController.getInstance().getManager().search(MemberInfo.ID, "jisu").get(0));
		GroupController.getInstance().getManager().addFriend(MemberController.getInstance().getManager().search(MemberInfo.ID, "yewon").get(0), MemberController.getInstance().getManager().search(MemberInfo.ID, "hc").get(0));
		BBSManagement manager = new BBSManagement();
		manager.register("hc", "0", "hc", "jisu");
		try {
			Thread.sleep(1000);
		} catch (Exception e) {
		}
		manager.register("jisu", "1", "jisu", "jisu");
		try {
			Thread.sleep(1000);
		} catch (Exception e) {
		}
		manager.register("jisu", "2", "jisu", "jisu");
		try {
			Thread.sleep(1000);
		} catch (Exception e) {
		}
		manager.register("hc", "3", "hc", "jisu");
		//manager.share("jisu", "yewon", 4);
		//manager.remove("yewon", 4);
		manager.modify("jisu", 2, "�����ϱ��?");
		//System.out.println(manager.search("yewon"));
		BBSController.getInstance().setManager(manager);
		
	}
}
