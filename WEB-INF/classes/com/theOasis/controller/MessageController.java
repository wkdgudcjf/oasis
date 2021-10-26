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
 * 클라이언트와 매개체역할을 하는 클래스.
 * 필드로 쪽지를 관리하는 매니져를 가지고 있다.
 * 
 * 쪽지함의 쪽지를 확인 하거나,
 * 쪽지를 삭제, 혹은 수신의 역할을 할 수 있다.
 * 
 * 클라이언트에게 리턴값을 변환시켜 리턴시킨다.
 * 
 * @author JHC
 *
 */
public class MessageController {
	/**
	 * message 관리자
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
	 * 회원의 id로 발신된 쪽지와 수신된 쪽지를 검색합니다.
	 * @param id 회원의 id
	 * @return 검색된 쪽지들
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
			 * 회원의 수신함과 발신함을 모두 list에 삽입
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
		manager.send("형철","예원",  "0");
		try {
			Thread.sleep(1000);
		} catch (Exception e) {
		}
		manager.send("예원", "지수", "1");
		try {
			Thread.sleep(1000);
		} catch (Exception e) {
		}
		manager.send("지수", "예원", "2");
		try {
			Thread.sleep(1000);
		} catch (Exception e) {
		}
		manager.send("형철","예원",  "3");
		try {
			Thread.sleep(1000);
		} catch (Exception e) {
		}
		manager.send("지수", "예원", "4");
		try {
			Thread.sleep(1000);
		} catch (Exception e) {
		}
		manager.send("형철","예원",  "5");
		try {
			Thread.sleep(1000);
		} catch (Exception e) {
		}
		manager.send("예원", "지수", "6");
		try {
			Thread.sleep(1000);
		} catch (Exception e) {
		}
		manager.send("형철","예원",  "7");
		try {
			Thread.sleep(1000);
		} catch (Exception e) {
		}
		manager.send("예원","형철",  "8");
		TextList[] temp = manager.search("예원", "형철");
		MessageController.getInstance().setManager(manager);
		System.out.println(MessageController.getInstance().getRecentMessage("예원"));		
	}
}
