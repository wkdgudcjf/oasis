package com.theOasis.text.bbs;

import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.List;

import com.theOasis.text.Readable;
import com.theOasis.text.Text;

/**
 * 게시물. text를 상속받은 대표적인 글. 공감목록과 댓글목록을 가지고있다. 글번호는 static 이므로 다운되지 않는한 글번호는 중첩되지
 * 않는다.
 * 
 * @author yewon
 * 
 */
public class BBSItem extends Text {
	/**
	 * 게시 대상
	 */
	private String postTarget;
	/**
	 * 첨부된 이미지 경로
	 */
	private String image;
	/**
	 * 공감 리스트
	 */
	private List<Agree> agreeList;
	/**
	 * 댓글 리스트
	 */
	private List<Readable> comments;
	/**
	 * 게시물 번호
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
	 * 게시물에 공감을 등록합니다
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
	 * 게시물에 공감을 등록합니다
	 * 
	 * @param isGood
	 *            good or bad, good 이면 true, bad이면 false
	 * @param writer
	 *            작성자
	 */
	public boolean addAgree(boolean isGood, String writer) {
		return addAgree(new Agree(isGood, super.getNumber(), writer));
	}

	/**
	 * 게시물의 공감을 돌려줍니다
	 * 
	 * @param agreeNo
	 *            공감 번호
	 * @return 공감
	 */
	public Agree getAgree(int agreeNo) {
		if ((agreeNo >= 0) && (agreeNo < agreeList.size()))
			return agreeList.get(agreeNo);
		return null;
	}

	/**
	 * 게시물의 댓글을 돌려줍니다.
	 * 
	 * @param commentNo
	 *            댓글 번호
	 * @return 댓글
	 */
	public Readable getComment(int commentNo) {
		sortComment();
		return comments.get(commentNo);
	}

	/**
	 * 게시물의 공감을 삭제합니다
	 * 
	 * @param writer
	 *            작성자
	 */
	public void removeAgree(String writer) {
		for (int i = agreeList.size() - 1; i >= 0; i--) {
			if (agreeList.get(i).getWriter().equals(writer))
				agreeList.remove(i);
		}
	}

	/**
	 * 게시물의 공감을 삭제합니다.
	 * 
	 * @param index
	 *            공감 번호
	 */
	public void removeAgree(int index) {
		sortComment();
		if ((index >= 0) && (index < agreeList.size()))
			agreeList.remove(index);
		sortComment();
	}

	/**
	 * 게시물에 댓글을 등록합니다
	 * 
	 * @param comment
	 *            댓글
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
	 * 게시물에 댓글을 등록합니다
	 * 
	 * @param content
	 *            내용
	 * @param writer
	 *            작성자
	 */
	public void addComment(String content, String writer) {
		addComment(new Comment(content, writer, super.getNumber()));
	}

	/**
	 * 게시물의 댓글을 수정합니다.
	 * 
	 * @param commentNo
	 *            댓글 번호
	 * @param comment
	 *            새로운 댓글
	 */
	public void modifyComment(int commentNo, Readable comment) {
		sortComment();
		comments.set(commentNo, comment);
	}

	/**
	 * 게시물의 댓글을 수정합니다.
	 * 
	 * @param commentNo
	 *            댓글 번호
	 * @param content
	 *            새로운 내용
	 */
	public void modifyComment(int commentNo, String content) {
		sortComment();
		comments.get(commentNo).setContent(content);
	}

	/**
	 * 게시물의 댓글을 수정합니다.
	 * 
	 * @param commentNo
	 *            댓글 번호
	 * @param content
	 *            새로운 내용
	 * @param writer
	 *            새로운 작성자
	 */
	public void modifyComment(int commentNo, String content, String writer) {
		sortComment();
		comments.get(commentNo).setContent(content);
		comments.get(commentNo).setWriter(writer);
	}

	/**
	 * 게시물의 이미지 경로와 내용을 변경합니다.
	 * 
	 * @param image
	 *            이미지 경로
	 * @param content
	 *            새로운 내용
	 */
	public void modify(String image, String content) {
		setImage(image);
		setContent(content);
	}

	/**
	 * 게시물의 댓글을 삭제합니다.
	 * 
	 * @param commentNo
	 *            댓글 번호
	 * @param writer
	 *            작성자
	 */
	public void removeComment(int commentNo, String writer) {
		sortComment();
		if(comments.get(commentNo).getWriter().equalsIgnoreCase(writer))
			comments.remove(commentNo);
		sortComment();
	}

	/**
	 * 게시물의 댓글을 삭제합니다.
	 * 
	 * @param commentNo
	 *            댓글 번호
	 */
	public void removeComment(int commentNo) {
		sortComment();
		comments.remove(commentNo);
		sortComment();
	}

	/**
	 * 게시물의 댓글을 삭제합니다.
	 * 
	 * @param writer
	 *            작성자
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
	 * Comment의 Number와 comments의 index를 맵핑시켜서 정렬합니다
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
	 * 게시대상을 리턴한다.
	 * 
	 * @return 게시대상
	 */
	public String getPostTarget() {
		return postTarget;
	}

	/**
	 * 게시대상을 설정한다
	 * 
	 * @param postTarget
	 *            설정한 게시대상
	 */
	public void setPostTarget(String postTarget) {
		this.postTarget = postTarget;
	}

	/**
	 * 이미지경로를 리턴한다.
	 * 
	 * @return 이미지경로
	 */
	public String getImage() {
		return image;
	}

	/**
	 * 이미지경로를 설정한다
	 * 
	 * @param image
	 *            설정할 이미지경로
	 */
	public void setImage(String image) {
		this.image = image;
	}

	/**
	 * 공감목록을 리턴한다.
	 * 
	 * @return 공감목록
	 */
	public List<Agree> getAgreeList() {
		return agreeList;
	}

	/**
	 * 공감목록을 설정한다.
	 * 
	 * @param agreeList
	 *            설정할 공감목록
	 */
	public void setAgreeList(List<Agree> agreeList) {
		this.agreeList = agreeList;
	}

	/**
	 * 댓글목록을 리턴한다.
	 * 
	 * @return 댓글목록
	 */
	public List<Readable> getComments() {
		return comments;
	}

	/**
	 * 댓글목록을 설정한다.
	 * 
	 * @param comments
	 *            설정할 댓글목록
	 */
	public void setComments(List<Readable> comments) {
		this.comments = comments;
	}

	/**
	 * 글 번호를 리턴한다.
	 * 
	 * @return 글번호
	 */
	public static int getBbsNo() {
		return bbsNo;
	}

	/**
	 * 글번호를 설정한다.
	 * 
	 * @param bbsNo
	 *            설정할 글번호
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
		BBSItem item = new BBSItem("내용", "작성자", "작성대상");
		item.addComment("댓글1", "형철");
		item.addComment("댓글2", "지수");
		item.addComment("댓글3", "예원");
		item.addComment("댓글4", "지수");
		item.addAgree(true, "예원");
		item.addAgree(false, "형철");
		item.addAgree(true, "지수");

		System.out.println(item.getAgreeList());
		System.out.println(item.getComments());
		//item.removeComment("지수");
		System.out.println(item.getComments());
	}
}
