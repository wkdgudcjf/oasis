package com.theOasis.text;

import java.util.LinkedList;
import java.util.List;

/**
 * �۵��� ��Ƶδ� Ŭ����. �߰�,����,�˻�,���� ����� �����Ѵ�. ��� �۵��� �� ����Ʈ�� ����� �ȴ�.
 * 
 * @author yewon
 * 
 */
public class TextList {
	/**
	 * �� ���
	 */
	private List<Readable> list;

	public TextList() {
		this(null);
	}

	public TextList(List<Readable> list) {
		if (list != null)
			this.list = list;
		else
			this.list = new LinkedList<Readable>();
	}

	/**
	 * �� ��Ͽ� ���� �߰��մϴ�.
	 * 
	 * @param readable
	 *            ��
	 */
	public void add(Readable readable) {
		if (readable != null) {
			this.list.add(readable);
		}
	}

	/**
	 * �� ��ȣ�� number�� ���� ���� �����մϴ�
	 * 
	 * @param number
	 *            �� ��ȣ
	 * @return ��
	 */
	public Readable get(int number) {
		/*
		 * �ε����� �����ϴ°� �ƴԴϴ�~ text type���� �ٿ�ĳ�����ؼ� ã���ּ��� �ƴϸ� sortȣ���ϰ� �ε����� �������ּ���
		 */
		for (Readable temp : list) {
			if (((Text) temp).getNumber() == number)
				return temp;
		}
		return null;
	}

	/**
	 * �ۼ��ڳ� �������� �Խù��� �˻��մϴ�.
	 * 
	 * @param info
	 *            �ۼ��ڷ� �˻�, Ȥ�� �������� �˻�
	 * @param data
	 *            �˻���
	 * @return �˻� ���
	 */
	public List<Readable> get(TextInfo info, String data) {
		LinkedList<Readable> re = new LinkedList<Readable>();

		if (info == TextInfo.WRITER) {
			for (Readable temp : list) {
				if (((Text) temp).getWriter().equalsIgnoreCase(data))
					re.add(temp);
			}
		} else if (info == TextInfo.CONTENT) {
			for (Readable temp : list) {
				if (((Text) temp).getContent().contains(data))
				{
					re.add(temp);
				}
			}
		}
		return re;
	}

	/**
	 * �� ��Ͽ��� �ش� ���� �����մϴ�.
	 * 
	 * @param readable
	 *            ��
	 */
	public void remove(Readable readable) {
		list.remove(readable);
	}

	/**
	 * �� ��Ͽ��� �ش� �� ��ȣ�� ���� ���� �����մϴ�.
	 * 
	 * @param number
	 *            �� ��ȣ
	 */
	public void remove(int number) {
		/*
		 * index�� �����ϴ°� �ƴԴϴ�. Text�� �ٿ�ĳ�����ؼ� �������ּ���~ Ȥ�� sort �޼ҵ� ȣ�� �Ŀ� index��
		 * �����ϼŵ� �˴ϴ�.
		 */
		for (Readable temp : list) {
			if (((Text) temp).getNumber() == number)
			{
				list.remove(temp);
				return ;
			}
		}
	}

	/**
	 * �� ��Ͽ� �ش� ���� �����ϴ��� Ȯ���մϴ�.
	 * 
	 * @param readable
	 *            ��
	 * @return ���� ����
	 */
	public boolean contains(Readable readable) {
		return list.contains(readable);
	}

	/**
	 * �� ��� ������ �۵��� ������������ (�� ��ȣ ����) �����մϴ�.
	 */
	public void sort() {
		for (int i = 0; i < list.size() - 1; i++) {
			for (int j = i + 1; j < list.size(); j++) {
				if (((Text) list.get(i)).getNumber() > ((Text) list.get(j))
						.getNumber()) {
					Readable temp = list.get(i);
					list.set(i, list.get(j));
					list.set(j, temp);
				}
			}
		}
	}

	/**
	 * �� ��Ͽ� ���Ե� ���� ������ �˷��ݴϴ�.
	 * 
	 * @return ���� ����
	 */
	public int size() {
		return list.size();
	}

	/**
	 * �� ��Ͽ��� ���� ��� �����մϴ�.
	 */
	public void clear() {
		list.clear();
	}

	public List<Readable> getList() {
		return list;
	}

	public void setList(List<Readable> list) {
		this.list = list;
		
	}
	public int indexOf(Readable item)
	{
		return list.indexOf(item);
	}
	@Override
	public String toString() {
		return "TextList [list=" + list + "]";
	}
	public static void main(String[] args) {
		TextList list = new TextList();
		list.add(new Text("ù��°�� �־����ϴ�", "�ۼ���1", 4));
		list.add(new Text("�ι�°�� �־����ϴ�", "�ۼ���2", 1));
		list.add(new Text("����°�� �־����ϴ�", "�ۼ���1", 3));
		System.out.println(list);
		list.sort();
		/*
		 * sort ���� �׽�Ʈ �Ϸ� 
		 */
		System.out.println(list);
		System.out.println(list.get(TextInfo.WRITER, "�ۼ���1"));
		/*
		 * search ���� �׽�Ʈ �Ϸ�
		 */
	}
}
