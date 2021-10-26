package com.theOasis.text;

import java.util.LinkedList;
import java.util.List;

/**
 * 글들을 모아두는 클래스. 추가,삭제,검색,수정 기능을 수행한다. 모든 글들은 이 리스트에 등록이 된다.
 * 
 * @author yewon
 * 
 */
public class TextList {
	/**
	 * 글 목록
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
	 * 글 목록에 글을 추가합니다.
	 * 
	 * @param readable
	 *            글
	 */
	public void add(Readable readable) {
		if (readable != null) {
			this.list.add(readable);
		}
	}

	/**
	 * 글 번호가 number와 같은 글을 제공합니다
	 * 
	 * @param number
	 *            글 번호
	 * @return 글
	 */
	public Readable get(int number) {
		/*
		 * 인덱스로 리턴하는게 아님니당~ text type으로 다운캐스팅해서 찾아주세용 아니면 sort호출하고 인덱스로 리턴해주세용
		 */
		for (Readable temp : list) {
			if (((Text) temp).getNumber() == number)
				return temp;
		}
		return null;
	}

	/**
	 * 작성자나 내용으로 게시물을 검색합니다.
	 * 
	 * @param info
	 *            작성자로 검색, 혹은 내용으로 검색
	 * @param data
	 *            검색어
	 * @return 검색 결과
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
	 * 글 목록에서 해당 글을 삭제합니다.
	 * 
	 * @param readable
	 *            글
	 */
	public void remove(Readable readable) {
		list.remove(readable);
	}

	/**
	 * 글 목록에서 해당 글 번호를 가진 글을 삭제합니다.
	 * 
	 * @param number
	 *            글 번호
	 */
	public void remove(int number) {
		/*
		 * index로 삭제하는게 아님니당. Text로 다운캐스팅해서 진행해주세요~ 혹은 sort 메소드 호출 후에 index로
		 * 삭제하셔도 됩니당.
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
	 * 글 목록에 해당 글이 존재하는지 확인합니다.
	 * 
	 * @param readable
	 *            글
	 * @return 존재 여부
	 */
	public boolean contains(Readable readable) {
		return list.contains(readable);
	}

	/**
	 * 글 목록 내부의 글들을 오름차순으로 (글 번호 기준) 정렬합니다.
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
	 * 글 목록에 포함된 글의 개수를 알려줍니다.
	 * 
	 * @return 글의 개수
	 */
	public int size() {
		return list.size();
	}

	/**
	 * 글 목록에서 글을 모두 삭제합니다.
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
		list.add(new Text("첫번째로 넣었습니다", "작성자1", 4));
		list.add(new Text("두번째로 넣었습니다", "작성자2", 1));
		list.add(new Text("세번째로 넣었습니다", "작성자1", 3));
		System.out.println(list);
		list.sort();
		/*
		 * sort 단위 테스트 완료 
		 */
		System.out.println(list);
		System.out.println(list.get(TextInfo.WRITER, "작성자1"));
		/*
		 * search 단위 테스트 완료
		 */
	}
}
