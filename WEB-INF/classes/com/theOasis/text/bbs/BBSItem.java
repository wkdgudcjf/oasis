package com.theOasis.text.bbs;

import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.List;

import com.theOasis.text.Readable;
import com.theOasis.text.Text;

/**
 * �Խù�. text�� ��ӹ��� ��ǥ���� ��. ������ϰ� ��۸���� �������ִ�. �۹�ȣ�� static �̹Ƿ� �ٿ���� �ʴ��� �۹�ȣ�� ��ø����
 * �ʴ´�.
 * 
 * @author yewon
 * 
 */
public class BBSItem extends Text {
	/**
	 * �Խ� ���
	 */
	private String postTarget;
	/**
	 * ÷�ε� �̹��� ���
	 */
	private String image;
	/**
	 * ���� ����Ʈ
	 */
	private List<Agree> agreeList;
	/**
	 * ��� ����Ʈ
	 */
	private List<Readable> comments;
	/**
	 * �Խù� ��ȣ
	 */
	private static int bbsNo;
	static {
		bbsNo = 1;
	}

	public BBSItem() {
		this("", "", "");
	}

	public BBSItem(String content, String writer, String postTarget) {
		this(content, writer, postTarget, "");
	}

	public BBSItem(String content, String writer, String postTarget,
			String image) {
		this(content, writer, postTarget, image, new LinkedList<Agree>(),
				new LinkedList<Readable>());
	}

	public BBSItem(String content, String writer, String postTarget,
			List<Agree> agreeList, List<Readable> comments) {
		this(content, writer, postTarget, "", agreeList, comments);
	}

	public BBSItem(String content, String writer, String postTarget,
			String image, List<Agree> agreeList, List<Readable> comments) {
		super(content, writer, bbsNo++);
		this.postTarget=postTarget;
		this.image = image;
		this.agreeList = agreeList;
		this.comments = comments;
	}

	/**
	 * �Խù��� ������ ����մϴ�
	 * 
	 * @param agree
	 */
	public boolean addAgree(Agree agree) {
		if (agree != null) {
			for(Agree ag : agreeList)
			{
				if(ag.getWriter().equals(agree.getWriter()))
					return false;
			}
			agreeList.add(agree);
			agree.setBbsNumber(super.getNumber());
		}
		return true;
	}

	/**
	 * �Խù��� ������ ����մϴ�
	 * 
	 * @param isGood
	 *            good or bad, good �̸� true, bad�̸� false
	 * @param writer
	 *            �ۼ���
	 */
	public boolean addAgree(boolean isGood, String writer) {
		return addAgree(new Agree(isGood, super.getNumber(), writer));
	}

	/**
	 * �Խù��� ������ �����ݴϴ�
	 * 
	 * @param agreeNo
	 *            ���� ��ȣ
	 * @return ����
	 */
	public Agree getAgree(int agreeNo) {
		if ((agreeNo >= 0) && (agreeNo < agreeList.size()))
			return agreeList.get(agreeNo);
		return null;
	}

	/**
	 * �Խù��� ����� �����ݴϴ�.
	 * 
	 * @param commentNo
	 *            ��� ��ȣ
	 * @return ���
	 */
	public Readable getComment(int commentNo) {
		sortComment();
		return comments.get(commentNo);
	}

	/**
	 * �Խù��� ������ �����մϴ�
	 * 
	 * @param writer
	 *            �ۼ���
	 */
	public void removeAgree(String writer) {
		for (int i = agreeList.size() - 1; i >= 0; i--) {
			if (agreeList.get(i).getWriter().equals(writer))
				agreeList.remove(i);
		}
	}

	/**
	 * �Խù��� ������ �����մϴ�.
	 * 
	 * @param index
	 *            ���� ��ȣ
	 */
	public void removeAgree(int index) {
		sortComment();
		if ((index >= 0) && (index < agreeList.size()))
			agreeList.remove(index);
		sortComment();
	}

	/**
	 * �Խù��� ����� ����մϴ�
	 * 
	 * @param comment
	 *            ���
	 */
	public void addComment(Readable comment) {

		if (comment != null) {
			this.comments.add(comment);
			((Text) comment).setNumber(comments.size() - 1);
			((Comment) comment).setBbsNumber(super.getNumber());
		}
		sortComment();

	}

	/**
	 * �Խù��� ����� ����մϴ�
	 * 
	 * @param content
	 *            ����
	 * @param writer
	 *            �ۼ���
	 */
	public void addComment(String content, String writer) {
		addComment(new Comment(content, writer, super.getNumber()));
	}

	/**
	 * �Խù��� ����� �����մϴ�.
	 * 
	 * @param commentNo
	 *            ��� ��ȣ
	 * @param comment
	 *            ���ο� ���
	 */
	public void modifyComment(int commentNo, Readable comment) {
		sortComment();
		comments.set(commentNo, comment);
	}

	/**
	 * �Խù��� ����� �����մϴ�.
	 * 
	 * @param commentNo
	 *            ��� ��ȣ
	 * @param content
	 *            ���ο� ����
	 */
	public void modifyComment(int commentNo, String content) {
		sortComment();
		comments.get(commentNo).setContent(content);
	}

	/**
	 * �Խù��� ����� �����մϴ�.
	 * 
	 * @param commentNo
	 *            ��� ��ȣ
	 * @param content
	 *            ���ο� ����
	 * @param writer
	 *            ���ο� �ۼ���
	 */
	public void modifyComment(int commentNo, String content, String writer) {
		sortComment();
		comments.get(commentNo).setContent(content);
		comments.get(commentNo).setWriter(writer);
	}

	/**
	 * �Խù��� �̹��� ��ο� ������ �����մϴ�.
	 * 
	 * @param image
	 *            �̹��� ���
	 * @param content
	 *            ���ο� ����
	 */
	public void modify(String image, String content) {
		setImage(image);
		setContent(content);
	}

	/**
	 * �Խù��� ����� �����մϴ�.
	 * 
	 * @param commentNo
	 *            ��� ��ȣ
	 * @param writer
	 *            �ۼ���
	 */
	public void removeComment(int commentNo, String writer) {
		sortComment();
		if(comments.get(commentNo).getWriter().equalsIgnoreCase(writer))
			comments.remove(commentNo);
		sortComment();
	}

	/**
	 * �Խù��� ����� �����մϴ�.
	 * 
	 * @param commentNo
	 *            ��� ��ȣ
	 */
	public void removeComment(int commentNo) {
		sortComment();
		comments.remove(commentNo);
		sortComment();
	}

	/**
	 * �Խù��� ����� �����մϴ�.
	 * 
	 * @param writer
	 *            �ۼ���
	 */
	public void removeComment(String writer) {
		for(int i=0; i<comments.size(); i++)
		{
			if(comments.get(i).getWriter().equalsIgnoreCase(writer))
			{
				comments.remove(i);
				i--;
			}
		}
		sortComment();
	}

	/**
	 * Comment�� Number�� comments�� index�� ���ν��Ѽ� �����մϴ�
	 */
	private void sortComment() {
		for (int i = 0; i < comments.size() - 1; i++) {
			for (int j = i + 1; j < comments.size(); j++) {
				if (((Text) comments.get(i)).getTime().after(((Text) comments.get(j)).getTime())) 
				{
					Readable temp = comments.get(i);
					comments.set(i, comments.get(j));
					comments.set(j, temp);
				}
			}
		}
		int i = 0;
		for (Readable r : comments) {
			((Text) r).setNumber(i++);
		}
	}

	/**
	 * �Խô���� �����Ѵ�.
	 * 
	 * @return �Խô��
	 */
	public String getPostTarget() {
		return postTarget;
	}

	/**
	 * �Խô���� �����Ѵ�
	 * 
	 * @param postTarget
	 *            ������ �Խô��
	 */
	public void setPostTarget(String postTarget) {
		this.postTarget = postTarget;
	}

	/**
	 * �̹�����θ� �����Ѵ�.
	 * 
	 * @return �̹������
	 */
	public String getImage() {
		return image;
	}

	/**
	 * �̹�����θ� �����Ѵ�
	 * 
	 * @param image
	 *            ������ �̹������
	 */
	public void setImage(String image) {
		this.image = image;
	}

	/**
	 * ��������� �����Ѵ�.
	 * 
	 * @return �������
	 */
	public List<Agree> getAgreeList() {
		return agreeList;
	}

	/**
	 * ��������� �����Ѵ�.
	 * 
	 * @param agreeList
	 *            ������ �������
	 */
	public void setAgreeList(List<Agree> agreeList) {
		this.agreeList = agreeList;
	}

	/**
	 * ��۸���� �����Ѵ�.
	 * 
	 * @return ��۸��
	 */
	public List<Readable> getComments() {
		return comments;
	}

	/**
	 * ��۸���� �����Ѵ�.
	 * 
	 * @param comments
	 *            ������ ��۸��
	 */
	public void setComments(List<Readable> comments) {
		this.comments = comments;
	}

	/**
	 * �� ��ȣ�� �����Ѵ�.
	 * 
	 * @return �۹�ȣ
	 */
	public static int getBbsNo() {
		return bbsNo;
	}

	/**
	 * �۹�ȣ�� �����Ѵ�.
	 * 
	 * @param bbsNo
	 *            ������ �۹�ȣ
	 */
	public static void setBbsNo(int bbsNo) {
		BBSItem.bbsNo = bbsNo;
	}

	@Override
	public String toString() {
		return "BBSItem [postTarget=" + postTarget + ", image=" + image
				+ ", agreeList=" + agreeList + ", comments=" + comments
				+ ", " + "time="+super.getTime().get(GregorianCalendar.YEAR)
				+ "."
				+ (super.getTime().get(
						GregorianCalendar.MONTH) + 1)
				+ "."
				+ super.getTime().get(
						GregorianCalendar.DAY_OF_MONTH)
						+ "."
				+ super.getTime().get(
						GregorianCalendar.HOUR_OF_DAY)
						+ "."
				+ super.getTime().get(
						GregorianCalendar.MINUTE)
						+ "."
				+ super.getTime().get(
						GregorianCalendar.SECOND)
						
						+ ", getNumber()=" + getNumber()
				+ ", toString()=" + super.toString() + ", getContent()="
				+ getContent() + ", getWriter()=" + getWriter()+ "]\n";
	}
	public static void main(String[] args) {
		BBSItem item = new BBSItem("����", "�ۼ���", "�ۼ����");
		item.addComment("���1", "��ö");
		item.addComment("���2", "����");
		item.addComment("���3", "����");
		item.addComment("���4", "����");
		item.addAgree(true, "����");
		item.addAgree(false, "��ö");
		item.addAgree(true, "����");

		System.out.println(item.getAgreeList());
		System.out.println(item.getComments());
		//item.removeComment("����");
		System.out.println(item.getComments());
	}
}
