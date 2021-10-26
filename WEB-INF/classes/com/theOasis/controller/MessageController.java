package com.theOasis.controller;

import com.theOasis.text.Readable;
import com.theOasis.text.Text;
import com.theOasis.text.TextList;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import com.theOasis.text.message.Message;
import com.theOasis.text.message.MessageInfo;
import com.theOasis.text.message.MessageManagement;
/**
 * Ŭ���̾�Ʈ�� �Ű�ü������ �ϴ� Ŭ����.
 * �ʵ�� ������ �����ϴ� �Ŵ����� ������ �ִ�.
 * 
 * �������� ������ Ȯ�� �ϰų�,
 * ������ ����, Ȥ�� ������ ������ �� �� �ִ�.
 * 
 * Ŭ���̾�Ʈ���� ���ϰ��� ��ȯ���� ���Ͻ�Ų��.
 * 
 * @author JHC
 *
 */
public class MessageController {
	/**
	 * message ������
	 */
	private MessageManagement manager;
	private static MessageController instance;
	static{
		instance = new MessageController();
	}
	
	private MessageController()
	{
		
	}
	/**
	 * ȸ���� id�� �߽ŵ� ������ ���ŵ� ������ �˻��մϴ�.
	 * @param id ȸ���� id
	 * @return �˻��� ������
	 */
	public List<List<Readable>> getRecentMessage(String id)
	{
		if(MemberController.getInstance().getManager().check(id))
		{
			List<Readable> list = new LinkedList<Readable>();
			if(manager.search(id)==null)
				return null;
			if((manager.search(id)[0].size()==0)&&(manager.search(id)[1].size()==0))
				return null;
			list.addAll(manager.search(id)[0].getList());
			list.addAll(manager.search(id)[1].getList());
			sortByTime(list);
			/*
			 * ȸ���� �����԰� �߽����� ��� list�� ����
			 */
			List<List<Readable>> ret = new LinkedList<List<Readable>>();
			List<String> name = new LinkedList<String>();
			//System.out.println(list);
			for(Readable r : list)
			{
				if(r.getWriter().equals(id)&&(!name.contains(((Message)r).getReceiver())))
				{
					name.add(((Message)r).getReceiver());
					LinkedList<Readable> temp = new LinkedList<Readable>();
					temp.addAll(manager.search(id, ((Message)r).getReceiver())[0].getList());
					temp.addAll(manager.search(id, ((Message)r).getReceiver())[1].getList());
					sortByTime(temp);
					ret.add((List<Readable>)temp.clone());
				}
				else if((!r.getWriter().equals(id))&&(!name.contains(r.getWriter())))
				{
					name.add(r.getWriter());
					LinkedList<Readable> temp = new LinkedList<Readable>();
					temp.addAll(manager.search(id, r.getWriter())[0].getList());
					temp.addAll(manager.search(id, r.getWriter())[1].getList());
					sortByTime(temp);
					ret.add((List<Readable>)temp.clone());
				}
			}
			return ret;
		}
		return null;
	}
	private void sortByTime(List<Readable> re)
	{
		for (int i = 0; i < re.size(); i++) {
			for (int j = i + 1; j < re.size(); j++) {
				if (((Text) re.get(i)).getTime().after(((Text) re.get(j)).getTime())) 
				{
					Readable temp = re.get(i);
					re.set(i, re.get(j));
					re.set(j, temp);
				}
			}
		}
	}
	public MessageManagement getManager() {
		return manager;
	}
	public void setManager(MessageManagement manager) {
		this.manager = manager;
	}
	public static MessageController getInstance() {
		return instance;
	}
	public static void main(String[] args) {
		MessageManagement manager = new MessageManagement();
		manager.send("��ö","����",  "0");
		try {
			Thread.sleep(1000);
		} catch (Exception e) {
		}
		manager.send("����", "����", "1");
		try {
			Thread.sleep(1000);
		} catch (Exception e) {
		}
		manager.send("����", "����", "2");
		try {
			Thread.sleep(1000);
		} catch (Exception e) {
		}
		manager.send("��ö","����",  "3");
		try {
			Thread.sleep(1000);
		} catch (Exception e) {
		}
		manager.send("����", "����", "4");
		try {
			Thread.sleep(1000);
		} catch (Exception e) {
		}
		manager.send("��ö","����",  "5");
		try {
			Thread.sleep(1000);
		} catch (Exception e) {
		}
		manager.send("����", "����", "6");
		try {
			Thread.sleep(1000);
		} catch (Exception e) {
		}
		manager.send("��ö","����",  "7");
		try {
			Thread.sleep(1000);
		} catch (Exception e) {
		}
		manager.send("����","��ö",  "8");
		TextList[] temp = manager.search("����", "��ö");
		MessageController.getInstance().setManager(manager);
		System.out.println(MessageController.getInstance().getRecentMessage("����"));		
	}
}
