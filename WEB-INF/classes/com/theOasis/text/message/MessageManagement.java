package com.theOasis.text.message;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import com.theOasis.text.Readable;
import com.theOasis.text.TextInfo;
import com.theOasis.text.TextList;

/**
 * ȸ������ ������ �����ϴ� �Ŵ��� ���Ҹ� �ϴ� Ŭ���� ȸ������ ������ ��� �ִ� ���� ���� ����� ���� ������, �������� ������ Ȯ��
 * �ϰų�, ������ ����, Ȥ�� ������ ������ �� �� �ִ�.
 * 
 * @author yewon
 * 
 */
public class MessageManagement {
	/**
	 * �޼�����
	 */
	/*
	 * 0��°�� ������ 1��°�� �߽���
	 */
	private HashMap<String, TextList[]> messageBox;

	/**
	 * default ������
	 */
	public MessageManagement() {
		this(null);
	}

	/**
	 * ȸ���� �������� ���޹޾� ��ü�� �����ϴ� ������
	 * 
	 * @param messageBox
	 *            ȸ���� ������
	 */
	public MessageManagement(HashMap<String, TextList[]> messageBox) {
		if (messageBox != null)
			this.messageBox = messageBox;
		else
			this.messageBox = new HashMap<String, TextList[]>();
	}

	/**
	 * ȸ���� id�� �ʰ�ü���� �����԰� �߽����� ã���ϴ�. �������� ȸ���� ���Ź��� ����Ʈ��, �߽��Կ��� ȸ���� �߽��� ����Ʈ�� ���Ϲ޾�
	 * ����Ʈ���·� �����մϴ�.
	 * 
	 * @param myId
	 *            ȸ���� id
	 * @return �����԰� �߽���
	 */
	public TextList[] search(String myId) {
		if (messageBox.containsKey(myId)) {
			return messageBox.get(myId);
		} else
			return null;
	}

	/**
	 * �� ȸ���� �ְ���� ������ �˻��մϴ�. ������ ȸ������ �����ڿ� �߽��ڰ� ��ġ�ϴ� ����� �����մϴ�.
	 * 
	 * @param myId
	 *            ȸ��1
	 * @param yourId
	 *            ȸ��2
	 * @return �� ȸ���� �ְ� ���� ���� ���
	 */
	public TextList[] search(String myId, String yourId) {
		return new TextList[] { search(myId, MessageInfo.SENDER, yourId),
				search(myId, MessageInfo.RECEIVER, yourId) };
	}

	/**
	 * ȸ���� �޼����� �˻��Ͽ� �����մϴ�. �߽��Կ��� �޼����� �˻��Ͽ� �����մϴ�.
	 * 
	 * @param id
	 *            ȸ���� id
	 * @param info
	 *            �˻� ī�װ� : ����, �߽���, ������
	 * @param data
	 *            �˻���
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
	 * ������ �����մϴ�. �߽��ڴ� �߽��Կ� �����ڴ� �����Կ� ���� ����մϴ�.
	 * 
	 * @param receiver
	 *            ������
	 * @param sender
	 *            �߽���
	 * @param content
	 *            ���� ����
	 */
	public void send(String receiver, String sender, String content) {
		Message message = new Message(content, sender, receiver);
		send(message);
	}

	/**
	 * ������ �����մϴ�. �߽��ڴ� �߽��Կ� �����ڴ� �����Կ� ���� ����մϴ�.
	 * 
	 * @param message
	 *            ������ ����
	 */
	public void send(Readable message) {
		/*
		 * message �ȿ� receiver�� sender�� ������ ������ �۾����ּ���~
		 */
		if (message != null) {
			checkMember(((Message) message).getReceiver());
			checkMember(((Message) message).getWriter());
			messageBox.get(((Message) message).getReceiver())[0].add(message);
			messageBox.get(((Message) message).getWriter())[1].add(message);
		}
	}

	/**
	 * ������ �˻��Ͽ� �����մϴ�.
	 * 
	 * @param id
	 *            ȸ���� id
	 * @param info
	 *            �˻� ī�װ� : ����/�߽���/������
	 * @param data
	 *            �˻���
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
	 * ȸ���� ������ �����մϴ�.
	 * 
	 * @param id
	 *            ȸ���� id
	 * @param message
	 *            ����
	 */
	public void remove(String id, Readable message) {
		if (message != null) {
			search(id)[0].remove(message);
			search(id)[1].remove(message);
		}
	}

	/**
	 * ȸ������ �������� ��ȯ�Ѵ�.
	 * 
	 * @return ȸ������ ������
	 */
	public HashMap<String, TextList[]> getMessageBox() {
		return messageBox;
	}

	/**
	 * ȸ������ �������� �����Ѵ�.
	 * 
	 * @param messageBox
	 *            ȸ������ ������
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
		manager.send("����", "����", "��������");
		manager.send("����", "����", "��������1");
		manager.send("��ö","����",  "��������1");
		manager.send("����", "����", "��������1");
		manager.send("��ö","����",  "��������1");
		manager.send("����", "����", "��������");
		manager.send("��ö","����",  "��������1");
		TextList[] temp = manager.search("����", "��ö");
		System.out.println(temp[0]);
		System.out.println(temp[1]);
		System.out.println(manager.search("����")[0]);
		manager.remove("����", MessageInfo.SENDER, "����");
		System.out.println(manager.search("����")[0]);
	}
}
