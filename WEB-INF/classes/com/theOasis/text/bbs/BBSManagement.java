package com.theOasis.text.bbs;

import java.util.HashMap;
import java.util.Iterator;
import com.theOasis.text.TextInfo;
import com.theOasis.text.TextList;
import com.theOasis.text.Readable;

/**
 * 게시물을 관리하는 클래스. 각회원마다 게시물리스트가 하나씩 할당된다. 게시물을 등록,수정,삭제,검색 할수 있다. 게시물에는
 * 게시글,공감,댓글 이 있다.
 * 
 * 
 * @author yewon
 * 
 */
public class BBSManagement {
	/**
	 * 게시물 목록
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
	 * 해당 id를 가진 회원의 게시물을 제공합니다. 이때 id는 작성자가 아닐 수도 있습니다.
	 * 
	 * @param id
	 *            소유자의 id
	 * @return 검색된 게시물들
	 */
	public TextList search(String id) {
		if (bbsList.containsKey(id))
			return bbsList.get(id);
		return null;
	}

	/**
	 * 회원이 소유하고 있는 게시물을 작성자나 내용으로 검색합니다. 파라미터로 들어온 카테고리로 검색하여 소유자의 게시물을 리턴합니다.
	 * 
	 * @param id
	 *            소유자의 id
	 * @param info
	 *            작성자 or 내용
	 * @param data
	 *            검색어
	 * @return 검색된 게시물들
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
	 * 해당 게시물 번호에 맞는 게시물을 제공합니다.
	 * 
	 * @param bbsNo
	 *            게시물 번호
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
	 * 회원의 게시물목록에 새로운 게시물을 등록합니다 파라미터로 들어온 id의 게시물 목록에 게시물을 등록합니다. 이때 게시대상이란
	 * 게시물이 공유된 게시물인지 여부를 확인할 수 있습니다. 만약 게시물이 공유된 게시물이라면 게시대상은 공유한 회원의 id가 됩니다.
	 * 
	 * @param id
	 *            소유자의 id
	 * @param bbsItem
	 *            새로운 게시물
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
	 * 회원의 게시물목록에 새로운 게시물을 등록합니다 파라미터로 들어온 id의 게시물 목록에 게시물을 등록합니다. 이때 게시대상이란
	 * 게시물이 공유된 게시물인지 여부를 확인할 수 있습니다. 만약 게시물이 공유된 게시물이라면 게시대상은 공유한 회원의 id가 됩니다.
	 * 
	 * @param id
	 *            소유자의 id
	 * @param content
	 *            글 내용
	 * @param writer
	 *            작성자
	 * @param postTarget
	 *            작성 대상
	 */
	public void register(String id, String content, String writer,
			String postTarget) {
		register(id, new BBSItem(content, writer, postTarget));
	}

	/**
	 * 회원의 게시물목록에 새로운 게시물을 등록합니다 파라미터로 들어온 id의 게시물 목록에 게시물을 등록합니다. 이때 게시대상이란
	 * 게시물이 공유된 게시물인지 여부를 확인할 수 있습니다. 만약 게시물이 공유된 게시물이라면 게시대상은 공유한 회원의 id가 됩니다.
	 * 
	 * @param id
	 *            소유자의 id
	 * @param content
	 *            글 내용
	 * @param writer
	 *            작성자
	 * @param postTarget
	 *            작성 대상
	 * @param image
	 *            게시물에 첨부된 이미지 경로
	 */
	public void register(String id, String content, String writer,
			String postTarget, String image) {
		register(id, new BBSItem(content, writer, postTarget, image));
	}

	/**
	 * 게시물에 댓글을 등록합니다. 게시물 소유자의 id의 게시물 목록중 게시물 번호로 게시물을 찾아 그 게시물에 댓글을 추가합니다.
	 * 
	 * @param id
	 *            소유자 id
	 * @param bbsNo
	 *            게시물 번호
	 * @param readable
	 *            댓글
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
	 * 게시물에 댓글을 등록합니다. 게시물 소유자의 id의 게시물 목록중 게시물 번호로 게시물을 찾아 그 게시물에 댓글을 추가합니다.
	 * 
	 * @param id
	 *            소유자 id
	 * @param bbsNo
	 *            게시물 번호
	 * @param content
	 *            댓글 내용
	 * @param writer
	 *            댓글 작성자
	 */
	public void registerComment(String id, int bbsNo, String content,
			String writer) {
		registerComment(id, bbsNo, new Comment(content, writer, bbsNo));
	}

	/**
	 * 게시물에 공감을 등록합니다. 게시물 소유자의 id의 게시물 목록중 게시물 번호로 게시물을 찾아 그 게시물에 공감을 추가합니다.
	 * 
	 * @param id
	 *            소유자 id
	 * @param bbsNo
	 *            게시물 번호
	 * @param isGood
	 *            good or bad, good이면 true, bad이면 false
	 * @param writer
	 *            공감 등록자
	 */
	public boolean registerAgree(String id, int bbsNo, boolean isGood,
			String writer) {
		return registerAgree(id, bbsNo, new Agree(isGood, bbsNo, writer));
	}

	/**
	 * 게시물에 공감을 등록합니다. 게시물 소유자의 id의 게시물 목록중 게시물 번호로 게시물을 찾아 그 게시물에 공감을 추가합니다.
	 * 
	 * @param id
	 *            소유자 id
	 * @param bbsNo
	 *            게시물 번호
	 * @param agree
	 *            공감
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
	 * 게시물에 작성된 댓글이나 공감을 삭제합니다. 게시물 소유자의 id의 게시물 목록중 게시물 번호로 게시물을 찾아 그 게시물에 공감
	 * 또는 댓글을 삭제합니다. 작성자와 요청자가 다를경우 삭제되지 않습니다.
	 * 
	 * @param id
	 *            소유자 id
	 * @param bbsNo
	 *            게시물 번호
	 * @param info
	 *            댓글/공감
	 * @param writer
	 *            댓글/공감 작성자
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
	 * 게시물의 댓글을 삭제합니다. 게시물 소유자의 id의 게시물 목록중 게시물 번호로 게시물을 찾아 그 게시물에 댓글을 삭제합니다.
	 * 작성자와 요청자가 다를경우 삭제되지 않습니다.
	 * 
	 * @param id
	 *            소유자 id
	 * @param bbsNo
	 *            게시물 번호
	 * @param commentNo
	 *            댓글 번호
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
	 * 게시물을 삭제합니다 게시물 소유자의 id의 게시물 목록중 게시물 번호로 게시물을 찾아 그 게시물을삭제합니다. 작성자와 요청자가
	 * 다를경우 삭제되지 않습니다.
	 * 
	 * @param id
	 *            소유자 id
	 * @param bbsNo
	 *            게시물 번호
	 */
	public void remove(String id, int bbsNo) {
		if (bbsList.containsKey(id)) {
			TextList list = bbsList.get(id);
			list.remove(list.get(bbsNo));
		}
	}

	/**
	 * 게시물의 내용을 변경합니다. 게시물 소유자의 id의 게시물 목록중 게시물 번호로 게시물을 찾아 그 게시물의 내용을 파라미터로 들어온
	 * 내용으로 수정합니다. 작성자와 요청자가 다를경우 변경되지 않습니다
	 * 
	 * @param id
	 *            소유자 id
	 * @param bbsNo
	 *            게시물 번호
	 * @param content
	 *            게시물 내용
	 */
	public void modify(String id, int bbsNo, String content) {
		if (bbsList.containsKey(id)) {
			TextList list = bbsList.get(id);
			if (list.get(bbsNo) != null)
				list.get(bbsNo).setContent(content);
		}
	}

	/**
	 * 게시물에 작성된 댓글을 수정합니다. 게시물 소유자의 id의 게시물 목록중 게시물 번호로 게시물을 찾아 그 게시물의 댓글번호로 댓글을
	 * 찾아 파라미터로 들어온 내용으로 수정합니다. 작성자와 요청자가 다를경우 변경되지 않습니다
	 * 
	 * @param id
	 *            소유자 id
	 * @param bbsNo
	 *            게시물 번호
	 * @param no
	 *            댓글 번호
	 * @param content
	 *            댓글의 새로운 내용
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
	 * 게시물의 내용을 변경합니다. 게시물 소유자의 id의 게시물 목록중 게시물 번호로 게시물을 찾아 그 게시물의 내용을 파라미터로 들어온
	 * 내용으로 수정합니다. 작성자와 요청자가 다를경우 변경되지 않습니다.
	 * 
	 * @param id
	 *            소유자 id
	 * @param number
	 *            게시물 번호
	 * @param item
	 *            새로운 게시물
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
	 * 게시물을 공유합니다. 게시물 소유자의 id의 게시물 목록중 게시물 번호로 게시물을 찾아 그 게시물을 파라미터로 들어온 타회원에게
	 * 공유합니다.
	 * 
	 * @param myId
	 *            소유자 id
	 * @param yourId
	 *            공유물의 주인 id
	 * @param bbsNo
	 *            게시물 번호
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
	 * 게게시물을 공유합니다. 게시물 소유자의 id의 게시물 목록중 게시물 번호로 게시물을 찾아 그 게시물을 파라미터로 들어온 타회원에게
	 * 공유합니다.
	 * 
	 * @param myId
	 *            소유자 id
	 * @param yourId
	 *            공유물의 주인 id
	 * @param item
	 *            게시물
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
	 * 게시물을 공유합니다. 게시물 소유자의 id의 게시물 목록중 게시물 번호로 게시물을 찾아 그 게시물을 파라미터로 들어온 타회원에게
	 * 공유합니다.
	 * 
	 * @param myId
	 *            소유자 id
	 * @param item
	 *            공유하고자 하는 게시물
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
		manager.register("yewon", "내용입니다", "yewon", "jisu");
		manager.register("yewon", "내용입니다2", "yewon", "jisu");
		manager.register("yewon", "내용입니다3", "yewon", "jisu");
		manager.register("yewon", "내용입니다4", "yewon", "jisu");
		manager.share("jisu", "yewon", 4);
		manager.remove("yewon", 4);
		manager.modify("jisu", 5, "내용일까요?");
		System.out.println(manager.search("yewon"));

	}
}