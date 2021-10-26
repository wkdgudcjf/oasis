package com.theOasis.text.bbs;

import java.util.HashMap;
import java.util.Iterator;
import com.theOasis.text.TextInfo;
import com.theOasis.text.TextList;
import com.theOasis.text.Readable;

/**
 * �Խù��� �����ϴ� Ŭ����. ��ȸ������ �Խù�����Ʈ�� �ϳ��� �Ҵ�ȴ�. �Խù��� ���,����,����,�˻� �Ҽ� �ִ�. �Խù�����
 * �Խñ�,����,��� �� �ִ�.
 * 
 * 
 * @author yewon
 * 
 */
public class BBSManagement {
	/**
	 * �Խù� ���
	 */
	private HashMap<String, TextList> bbsList;

	public BBSManagement() {
		this(null);
	}

	public BBSManagement(HashMap<String, TextList> bbsList) {
		if (bbsList != null)
			this.bbsList = bbsList;
		else
			this.bbsList = new HashMap<String, TextList>();
	}

	/**
	 * �ش� id�� ���� ȸ���� �Խù��� �����մϴ�. �̶� id�� �ۼ��ڰ� �ƴ� ���� �ֽ��ϴ�.
	 * 
	 * @param id
	 *            �������� id
	 * @return �˻��� �Խù���
	 */
	public TextList search(String id) {
		if (bbsList.containsKey(id))
			return bbsList.get(id);
		return null;
	}

	/**
	 * ȸ���� �����ϰ� �ִ� �Խù��� �ۼ��ڳ� �������� �˻��մϴ�. �Ķ���ͷ� ���� ī�װ��� �˻��Ͽ� �������� �Խù��� �����մϴ�.
	 * 
	 * @param id
	 *            �������� id
	 * @param info
	 *            �ۼ��� or ����
	 * @param data
	 *            �˻���
	 * @return �˻��� �Խù���
	 */
	public TextList search(String id, TextInfo info, String data) {
		TextList list;
		TextList re = new TextList();
		if (bbsList.containsKey(id))
			list = bbsList.get(id);
		else
			return null;

		if (info == TextInfo.CONTENT) {
			for (Readable r : list.getList()) {
				if (r.getContent().contains(data))
					re.add(r);
			}
		} else if (info == TextInfo.WRITER) {
			for (Readable r : list.getList()) {
				if (r.getWriter().equalsIgnoreCase(data))
					re.add(r);
			}
		}
		return re;
	}

	/**
	 * �ش� �Խù� ��ȣ�� �´� �Խù��� �����մϴ�.
	 * 
	 * @param bbsNo
	 *            �Խù� ��ȣ
	 * @return
	 */
	public Readable search(int bbsNo) {
		Iterator<String> iter = bbsList.keySet().iterator();
		while (iter.hasNext()) {
			TextList list = bbsList.get(iter.next());
			if (list.get(bbsNo) != null)
				return list.get(bbsNo);
		}
		return null;
	}
	public String findOwner(int bbsNo) {
		Iterator<String> iter = bbsList.keySet().iterator();
		while (iter.hasNext()) {
			String owner = iter.next();
			TextList list = bbsList.get(owner);
			if (list.get(bbsNo) != null)
				return owner;
		}
		return null;
	}

	/**
	 * ȸ���� �Խù���Ͽ� ���ο� �Խù��� ����մϴ� �Ķ���ͷ� ���� id�� �Խù� ��Ͽ� �Խù��� ����մϴ�. �̶� �Խô���̶�
	 * �Խù��� ������ �Խù����� ���θ� Ȯ���� �� �ֽ��ϴ�. ���� �Խù��� ������ �Խù��̶�� �Խô���� ������ ȸ���� id�� �˴ϴ�.
	 * 
	 * @param id
	 *            �������� id
	 * @param bbsItem
	 *            ���ο� �Խù�
	 */
	public void register(String id, Readable bbsItem) {
		if(bbsItem ==null)
			return ;
		
		if (bbsList.containsKey(id))
			bbsList.get(id).add(bbsItem);
		else {
			bbsList.put(id, new TextList());
			bbsList.get(id).add(bbsItem);
		}
	}

	/**
	 * ȸ���� �Խù���Ͽ� ���ο� �Խù��� ����մϴ� �Ķ���ͷ� ���� id�� �Խù� ��Ͽ� �Խù��� ����մϴ�. �̶� �Խô���̶�
	 * �Խù��� ������ �Խù����� ���θ� Ȯ���� �� �ֽ��ϴ�. ���� �Խù��� ������ �Խù��̶�� �Խô���� ������ ȸ���� id�� �˴ϴ�.
	 * 
	 * @param id
	 *            �������� id
	 * @param content
	 *            �� ����
	 * @param writer
	 *            �ۼ���
	 * @param postTarget
	 *            �ۼ� ���
	 */
	public void register(String id, String content, String writer,
			String postTarget) {
		register(id, new BBSItem(content, writer, postTarget));
	}

	/**
	 * ȸ���� �Խù���Ͽ� ���ο� �Խù��� ����մϴ� �Ķ���ͷ� ���� id�� �Խù� ��Ͽ� �Խù��� ����մϴ�. �̶� �Խô���̶�
	 * �Խù��� ������ �Խù����� ���θ� Ȯ���� �� �ֽ��ϴ�. ���� �Խù��� ������ �Խù��̶�� �Խô���� ������ ȸ���� id�� �˴ϴ�.
	 * 
	 * @param id
	 *            �������� id
	 * @param content
	 *            �� ����
	 * @param writer
	 *            �ۼ���
	 * @param postTarget
	 *            �ۼ� ���
	 * @param image
	 *            �Խù��� ÷�ε� �̹��� ���
	 */
	public void register(String id, String content, String writer,
			String postTarget, String image) {
		register(id, new BBSItem(content, writer, postTarget, image));
	}

	/**
	 * �Խù��� ����� ����մϴ�. �Խù� �������� id�� �Խù� ����� �Խù� ��ȣ�� �Խù��� ã�� �� �Խù��� ����� �߰��մϴ�.
	 * 
	 * @param id
	 *            ������ id
	 * @param bbsNo
	 *            �Խù� ��ȣ
	 * @param readable
	 *            ���
	 */
	public void registerComment(String id, int bbsNo, Readable readable) {
		if(readable==null)
			return ;
		if (bbsList.containsKey(id)) {
			TextList list = bbsList.get(id);
			if (list.get(bbsNo) != null) {
				((BBSItem) list.get(bbsNo)).addComment(readable);
			}
		}
	}

	/**
	 * �Խù��� ����� ����մϴ�. �Խù� �������� id�� �Խù� ����� �Խù� ��ȣ�� �Խù��� ã�� �� �Խù��� ����� �߰��մϴ�.
	 * 
	 * @param id
	 *            ������ id
	 * @param bbsNo
	 *            �Խù� ��ȣ
	 * @param content
	 *            ��� ����
	 * @param writer
	 *            ��� �ۼ���
	 */
	public void registerComment(String id, int bbsNo, String content,
			String writer) {
		registerComment(id, bbsNo, new Comment(content, writer, bbsNo));
	}

	/**
	 * �Խù��� ������ ����մϴ�. �Խù� �������� id�� �Խù� ����� �Խù� ��ȣ�� �Խù��� ã�� �� �Խù��� ������ �߰��մϴ�.
	 * 
	 * @param id
	 *            ������ id
	 * @param bbsNo
	 *            �Խù� ��ȣ
	 * @param isGood
	 *            good or bad, good�̸� true, bad�̸� false
	 * @param writer
	 *            ���� �����
	 */
	public boolean registerAgree(String id, int bbsNo, boolean isGood,
			String writer) {
		return registerAgree(id, bbsNo, new Agree(isGood, bbsNo, writer));
	}

	/**
	 * �Խù��� ������ ����մϴ�. �Խù� �������� id�� �Խù� ����� �Խù� ��ȣ�� �Խù��� ã�� �� �Խù��� ������ �߰��մϴ�.
	 * 
	 * @param id
	 *            ������ id
	 * @param bbsNo
	 *            �Խù� ��ȣ
	 * @param agree
	 *            ����
	 */
	public boolean registerAgree(String id, int bbsNo, Agree agree) {
		if(agree==null)
			return false;
		if (bbsList.containsKey(id)) {
			TextList list = bbsList.get(id);
			if (list.get(bbsNo) != null)
				return ((BBSItem) list.get(bbsNo)).addAgree(agree);
		}
		return false;
	}
	/*
	public boolean canRegisterAgree(String id, int bbsNo, String writer){
		if(writer==null)
			return false;
		if (bbsList.containsKey(id)) {
			TextList list = bbsList.get(id);
			if (list.get(bbsNo) != null)
			{
				for(Agree agree : ((BBSItem)list.get(bbsNo)).getAgreeList())
				{
					if(agree.getWriter().equals(writer))
						return false;
				}
				return true;
			}
		}
		return false;
	}*/
	/**
	 * �Խù��� �ۼ��� ����̳� ������ �����մϴ�. �Խù� �������� id�� �Խù� ����� �Խù� ��ȣ�� �Խù��� ã�� �� �Խù��� ����
	 * �Ǵ� ����� �����մϴ�. �ۼ��ڿ� ��û�ڰ� �ٸ���� �������� �ʽ��ϴ�.
	 * 
	 * @param id
	 *            ������ id
	 * @param bbsNo
	 *            �Խù� ��ȣ
	 * @param info
	 *            ���/����
	 * @param writer
	 *            ���/���� �ۼ���
	 */
	public void remove(String id, int bbsNo, BBSInfo info, String writer) {
		if (bbsList.containsKey(id)) {
			TextList list = bbsList.get(id);
			if (list.get(bbsNo) != null) {
				if (info == BBSInfo.AGREE) {
					((BBSItem) list.get(bbsNo)).removeAgree(writer);
				} else if (info == BBSInfo.COMMENT) {
					((BBSItem) list.get(bbsNo)).removeComment(writer);
				}
			}
		}
	}

	/**
	 * �Խù��� ����� �����մϴ�. �Խù� �������� id�� �Խù� ����� �Խù� ��ȣ�� �Խù��� ã�� �� �Խù��� ����� �����մϴ�.
	 * �ۼ��ڿ� ��û�ڰ� �ٸ���� �������� �ʽ��ϴ�.
	 * 
	 * @param id
	 *            ������ id
	 * @param bbsNo
	 *            �Խù� ��ȣ
	 * @param commentNo
	 *            ��� ��ȣ
	 */
	public void removeComment(String id, int bbsNo, int commentNo) {
		if (bbsList.containsKey(id)) {
			TextList list = bbsList.get(id);
			if (list.get(bbsNo) != null) {
				((BBSItem) list.get(bbsNo)).removeAgree(commentNo);
			}
		}
	}

	/**
	 * �Խù��� �����մϴ� �Խù� �������� id�� �Խù� ����� �Խù� ��ȣ�� �Խù��� ã�� �� �Խù��������մϴ�. �ۼ��ڿ� ��û�ڰ�
	 * �ٸ���� �������� �ʽ��ϴ�.
	 * 
	 * @param id
	 *            ������ id
	 * @param bbsNo
	 *            �Խù� ��ȣ
	 */
	public void remove(String id, int bbsNo) {
		if (bbsList.containsKey(id)) {
			TextList list = bbsList.get(id);
			list.remove(list.get(bbsNo));
		}
	}

	/**
	 * �Խù��� ������ �����մϴ�. �Խù� �������� id�� �Խù� ����� �Խù� ��ȣ�� �Խù��� ã�� �� �Խù��� ������ �Ķ���ͷ� ����
	 * �������� �����մϴ�. �ۼ��ڿ� ��û�ڰ� �ٸ���� ������� �ʽ��ϴ�
	 * 
	 * @param id
	 *            ������ id
	 * @param bbsNo
	 *            �Խù� ��ȣ
	 * @param content
	 *            �Խù� ����
	 */
	public void modify(String id, int bbsNo, String content) {
		if (bbsList.containsKey(id)) {
			TextList list = bbsList.get(id);
			if (list.get(bbsNo) != null)
				list.get(bbsNo).setContent(content);
		}
	}

	/**
	 * �Խù��� �ۼ��� ����� �����մϴ�. �Խù� �������� id�� �Խù� ����� �Խù� ��ȣ�� �Խù��� ã�� �� �Խù��� ��۹�ȣ�� �����
	 * ã�� �Ķ���ͷ� ���� �������� �����մϴ�. �ۼ��ڿ� ��û�ڰ� �ٸ���� ������� �ʽ��ϴ�
	 * 
	 * @param id
	 *            ������ id
	 * @param bbsNo
	 *            �Խù� ��ȣ
	 * @param no
	 *            ��� ��ȣ
	 * @param content
	 *            ����� ���ο� ����
	 */
	public void modify(String id, int bbsNo, int no, String content) {
		if (bbsList.containsKey(id)) {
			TextList list = bbsList.get(id);
			if (list.get(bbsNo) != null) {
				((BBSItem) list.get(bbsNo)).modifyComment(no, content);
			}
		}
	}

	/**
	 * �Խù��� ������ �����մϴ�. �Խù� �������� id�� �Խù� ����� �Խù� ��ȣ�� �Խù��� ã�� �� �Խù��� ������ �Ķ���ͷ� ����
	 * �������� �����մϴ�. �ۼ��ڿ� ��û�ڰ� �ٸ���� ������� �ʽ��ϴ�.
	 * 
	 * @param id
	 *            ������ id
	 * @param number
	 *            �Խù� ��ȣ
	 * @param item
	 *            ���ο� �Խù�
	 */
	public void modify(String id, int number, Readable item) {
		if(item==null)
			return ;
		if (bbsList.containsKey(id)) {
			TextList list = bbsList.get(id);
			if (list.get(number) != null) {
				list.getList().set(list.indexOf(list.get(number)), item);
			}
		}
	}

	/**
	 * �Խù��� �����մϴ�. �Խù� �������� id�� �Խù� ����� �Խù� ��ȣ�� �Խù��� ã�� �� �Խù��� �Ķ���ͷ� ���� Ÿȸ������
	 * �����մϴ�.
	 * 
	 * @param myId
	 *            ������ id
	 * @param yourId
	 *            �������� ���� id
	 * @param bbsNo
	 *            �Խù� ��ȣ
	 */
	public void share(String myId, String yourId, int bbsNo) {
		if (!bbsList.containsKey(yourId))
			return;

		if (!bbsList.containsKey(myId))
			bbsList.put(myId, new TextList());

		if (bbsList.get(yourId).get(bbsNo) != null) {
			BBSItem original = (BBSItem) bbsList.get(yourId).get(bbsNo);
			BBSItem newItem = new BBSItem(original.getContent(),
					original.getWriter(), original.getPostTarget(),
					original.getImage());
			bbsList.get(myId).add(newItem);
		}

	}

	/**
	 * �԰Խù��� �����մϴ�. �Խù� �������� id�� �Խù� ����� �Խù� ��ȣ�� �Խù��� ã�� �� �Խù��� �Ķ���ͷ� ���� Ÿȸ������
	 * �����մϴ�.
	 * 
	 * @param myId
	 *            ������ id
	 * @param yourId
	 *            �������� ���� id
	 * @param item
	 *            �Խù�
	 */
	public void share(String myId, String yourId, Readable item) {
		if(item==null)
			return ;
		if (!bbsList.containsKey(yourId))
			return;

		if (!bbsList.containsKey(myId))
			bbsList.put(myId, new TextList());

		BBSItem newItem = new BBSItem(item.getContent(), item.getWriter(),
				((BBSItem) item).getPostTarget(), ((BBSItem) item).getImage());
		bbsList.get(myId).add(newItem);
	}

	/**
	 * �Խù��� �����մϴ�. �Խù� �������� id�� �Խù� ����� �Խù� ��ȣ�� �Խù��� ã�� �� �Խù��� �Ķ���ͷ� ���� Ÿȸ������
	 * �����մϴ�.
	 * 
	 * @param myId
	 *            ������ id
	 * @param item
	 *            �����ϰ��� �ϴ� �Խù�
	 */
	public void share(String myId, Readable item) {

		if(item==null)
			return;
		
		if (!bbsList.containsKey(myId))
			bbsList.put(myId, new TextList());

		if (bbsList.containsKey(myId)) {
			BBSItem newItem = new BBSItem(item.getContent(), item.getWriter(),
					((BBSItem) item).getPostTarget(),
					((BBSItem) item).getImage());
			bbsList.get(myId).add(newItem);
		}
	}

	@Override
	public String toString() {
		return "BBSManagement [bbsList=" + bbsList + "]";
	}


	public HashMap<String, TextList> getBbsList() {
		return bbsList;
	}

	public void setBbsList(HashMap<String, TextList> bbsList) {
		this.bbsList = bbsList;
	}

	public static void main(String[] args) {
		BBSManagement manager = new BBSManagement();
		manager.register("yewon", "�����Դϴ�", "yewon", "jisu");
		manager.register("yewon", "�����Դϴ�2", "yewon", "jisu");
		manager.register("yewon", "�����Դϴ�3", "yewon", "jisu");
		manager.register("yewon", "�����Դϴ�4", "yewon", "jisu");
		manager.share("jisu", "yewon", 4);
		manager.remove("yewon", 4);
		manager.modify("jisu", 5, "�����ϱ��?");
		System.out.println(manager.search("yewon"));

	}
}