package com.theOasis.text.message;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import com.theOasis.text.Readable;
import com.theOasis.text.TextInfo;
import com.theOasis.text.TextList;

/**
 * 회원들의 쪽지를 관리하는 매니저 역할를 하는 클래스 회원들의 쪽지를 담고 있는 쪽지 함을 멤버로 갖고 있으며, 쪽지함의 쪽지를 확인
 * 하거나, 쪽지를 삭제, 혹은 수신의 역할을 할 수 있다.
 * 
 * @author yewon
 * 
 */
public class MessageManagement {
	/**
	 * 메세지함
	 */
	/*
	 * 0번째는 수신함 1번째는 발신함
	 */
	private HashMap<String, TextList[]> messageBox;

	/**
	 * default 생성자
	 */
	public MessageManagement() {
		this(null);
	}

	/**
	 * 회원의 쪽지함을 전달받아 객체를 생성하는 생성자
	 * 
	 * @param messageBox
	 *            회원의 쪽지함
	 */
	public MessageManagement(HashMap<String, TextList[]> messageBox) {
		if (messageBox != null)
			this.messageBox = messageBox;
		else
			this.messageBox = new HashMap<String, TextList[]>();
	}

	/**
	 * 회원의 id로 맵객체에서 수신함과 발신함을 찾습니다. 수신함은 회원이 수신받은 리스트를, 발신함에는 회원이 발신한 리스트를 리턴받아
	 * 리스트형태로 리턴합니다.
	 * 
	 * @param myId
	 *            회원의 id
	 * @return 수신함과 발신함
	 */
	public TextList[] search(String myId) {
		if (messageBox.containsKey(myId)) {
			return messageBox.get(myId);
		} else
			return null;
	}

	/**
	 * 두 회원이 주고받은 쪽지를 검색합니다. 각각의 회원들이 수신자와 발신자가 일치하는 목록을 리턴합니다.
	 * 
	 * @param myId
	 *            회원1
	 * @param yourId
	 *            회원2
	 * @return 두 회원이 주고 받은 쪽지 목록
	 */
	public TextList[] search(String myId, String yourId) {
		return new TextList[] { search(myId, MessageInfo.SENDER, yourId),
				search(myId, MessageInfo.RECEIVER, yourId) };
	}

	/**
	 * 회원의 메세지를 검색하여 제공합니다. 발신함에서 메세지를 검색하여 리턴합니다.
	 * 
	 * @param id
	 *            회원의 id
	 * @param info
	 *            검색 카테고리 : 내용, 발신자, 수신자
	 * @param data
	 *            검색어
	 * @return
	 */
	public TextList search(String id, MessageInfo info, String data) {
		TextList receiveBox = search(id)[0];
		TextList sendBox = search(id)[1];
		LinkedList<Readable> re = new LinkedList<Readable>();
		if (MessageInfo.CONTENT == info) {
			re.addAll(receiveBox.get(TextInfo.CONTENT, data));
			re.addAll(sendBox.get(TextInfo.CONTENT, data));

		} else if (info ==MessageInfo.RECEIVER) {
			for(Readable t : sendBox.getList())
			{
				if(((Message)t).getReceiver().equalsIgnoreCase(data))
					re.add(t);
			}
		} else if (info==MessageInfo.SENDER) {
			re.addAll(receiveBox.get(TextInfo.WRITER, data));
		}
		return new TextList(re);
	}

	/**
	 * 쪽지를 전송합니다. 발신자는 발신함에 수신자는 수신함에 각각 등록합니다.
	 * 
	 * @param receiver
	 *            수신자
	 * @param sender
	 *            발신자
	 * @param content
	 *            쪽지 내용
	 */
	public void send(String receiver, String sender, String content) {
		Message message = new Message(content, sender, receiver);
		send(message);
	}

	/**
	 * 쪽지를 전송합니다. 발신자는 발신함에 수신자는 수신함에 각각 등록합니다.
	 * 
	 * @param message
	 *            전송할 쪽지
	 */
	public void send(Readable message) {
		/*
		 * message 안에 receiver와 sender가 있으니 꺼내서 작업해주세요~
		 */
		if (message != null) {
			checkMember(((Message) message).getReceiver());
			checkMember(((Message) message).getWriter());
			messageBox.get(((Message) message).getReceiver())[0].add(message);
			messageBox.get(((Message) message).getWriter())[1].add(message);
		}
	}

	/**
	 * 쪽지를 검색하여 삭제합니다.
	 * 
	 * @param id
	 *            회원의 id
	 * @param info
	 *            검색 카테고리 : 내용/발신자/수신자
	 * @param data
	 *            검색어
	 */
	public void remove(String id, MessageInfo info, String data) {
		TextList receiveBox = search(id)[0];
		TextList sendBox = search(id)[1];
		if (MessageInfo.CONTENT == info) {
			List<Readable> list = receiveBox.get(TextInfo.CONTENT, data);

			int i=list.size()-1;
			while(i>=0)
			{
				receiveBox.remove(list.get(i));
				i--;
			}
			list = sendBox.get(TextInfo.CONTENT, data);
			i=list.size()-1;
			while(i>=0)
			{
				sendBox.remove(list.get(i));
				i--;
			}
		} else if (MessageInfo.RECEIVER == info) {
			List<Readable> list = sendBox.getList();
			int i=list.size()-1;
			while(i>=0)
			{
				if(((Message)list.get(i)).getReceiver().equalsIgnoreCase(data))
					sendBox.remove(list.get(i));
				i--;
			}
		} else if (MessageInfo.SENDER == info) {
			List<Readable> list = receiveBox.get(TextInfo.WRITER, data);
			int i=list.size()-1;
			while(i>=0)
			{
				receiveBox.remove(list.get(i));
				i--;
			}
	/*		
			for (Readable temp : receiveBox.get(TextInfo.WRITER, data))
				receiveBox.remove(temp);
				*/
		}
	}

	/**
	 * 회원의 쪽지를 삭제합니다.
	 * 
	 * @param id
	 *            회원의 id
	 * @param message
	 *            쪽지
	 */
	public void remove(String id, Readable message) {
		if (message != null) {
			search(id)[0].remove(message);
			search(id)[1].remove(message);
		}
	}

	/**
	 * 회원들의 쪽지함을 반환한다.
	 * 
	 * @return 회원들의 쪽지함
	 */
	public HashMap<String, TextList[]> getMessageBox() {
		return messageBox;
	}

	/**
	 * 회원들의 쪽지함을 갱신한다.
	 * 
	 * @param messageBox
	 *            회원들의 쪽지함
	 */
	public void setMessageBox(HashMap<String, TextList[]> messageBox) {
		this.messageBox = messageBox;
	}

	private void checkMember(String id) {

		if (!messageBox.containsKey(id)) {
			messageBox.put(id,
					new TextList[] { new TextList(), new TextList() });
		}
	}

	@Override
	public String toString() {
		return "MessageManagement [messageBox=" + messageBox + "]";
	}

	public static void main(String[] args) {
		MessageManagement manager = new MessageManagement();
		manager.send("예원", "지수", "내용음슴");
		manager.send("지수", "예원", "내용음슴1");
		manager.send("형철","예원",  "내용음슴1");
		manager.send("지수", "예원", "내용음슴1");
		manager.send("형철","예원",  "내용음슴1");
		manager.send("예원", "지수", "내용음슴");
		manager.send("형철","예원",  "내용음슴1");
		TextList[] temp = manager.search("예원", "형철");
		System.out.println(temp[0]);
		System.out.println(temp[1]);
		System.out.println(manager.search("예원")[0]);
		manager.remove("예원", MessageInfo.SENDER, "지수");
		System.out.println(manager.search("예원")[0]);
	}
}
