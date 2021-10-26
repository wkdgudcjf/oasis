package com.theOasis.file.webFile;

import java.util.*;

import com.theOasis.file.File;
/**
 * 파일의 자식으로써 웹에서 사용할 수 있는 클래스.
 * @author JHC
 *
 */

public class WebFile extends File
{
	/**
	 * 파일 주인
	 */
  private String owner;
  /**
   * 공유멤버 리스트
   */
  private List<String> sharingMember;
  /**
   * 공유여부
   */
  private boolean isShare;
  /**
   * 기본생성자
   */
  public WebFile()
  {
	  this.sharingMember = new LinkedList<String>();
	  this.isShare=false;
  }
  /**
   * 생성자 오버로딩
   * @param location 위치
   * @param name 이름
   * @param owner 주인
   */
  public WebFile(String location, String name, String owner)
  {
	  super(location,name);
	  this.owner=owner;
	  this.sharingMember = new LinkedList<String>();
	  this.isShare=false;
  }
  /**
   * 생성자 오버로딩
   * @param location 위치
   * @param name 이름
   * @param size 크기
   * @param owner 주인
   */
  public WebFile(String location, String name,int size,String owner)
  {
	  super(location,name,size);
	  this.owner=owner;
	  this.sharingMember = new LinkedList<String>();
	  this.isShare=false;
  }
  /**
   * 생성자 오버로딩
   * @param location 위치
   * @param name 이름
   * @param owner 주인
   * @param isSharing 공유여부
   */
  public WebFile(String location, String name,int size,String owner, boolean isSharing)
  {
	  super(location,name,size);
	  this.owner=owner;
	  this.isShare=isSharing;
	  this.sharingMember = new LinkedList<String>();
  }
  /**
   * 생성자 오버로딩
   * @param location 위치
   * @param name 이름
   * @param owner 주인
   * @param isSharing 공유여부
   * @param sharingMember 공유멤버리스트
   */
  public WebFile(String location, String name,int size,String owner, boolean isSharing,List<String> sharingMember)
  {
	  super(location,name,size);
	  this.owner=owner;
	  this.isShare=isSharing;
	  this.sharingMember = new LinkedList<String>();
  }
  /**
   * 생성자 오버로딩
   * @param location 위치
   * @param name 이름
   * @param time 생성시간
   * @param size 크기
   * @param owner 파일주인
   * @param isSharing 공유여부
   */
  public WebFile(String location, String name,int size ,GregorianCalendar time, String owner, boolean isSharing)
  {
	  super(location,name,time,size);
	  this.owner=owner;
	  this.isShare=isSharing;
	  this.sharingMember = new LinkedList<String>();
  }
  /**
   * 생성자 오버로딩
   * @param location 위치
   * @param name 이름
   * @param time 생성시간
   * @param size 크기
   * @param owner 파일주인
   * @param sharingMember 공유멤버리스트
   * @param isSharing 공유여부
   */
  public WebFile(String location, String name, GregorianCalendar time, int size, String owner, List<String> sharingMember, boolean isSharing)
  {
	  super(location,name,time,size);
	  this.owner=owner;
	  this.isShare=isSharing;
	  this.sharingMember = sharingMember;
  }
    /**
     * 공유멤버를 추가하다.
     * @param member 추가할 멤버이름
     */
  public void addSharingMember(String member)
  {
	  this.sharingMember.add(member);
  }
  /**
   * 공유멤버를 추가하다.
   * @param member 추가할 멤버들
   */
public void addSharingMember(List<String> member)
{
	  this.sharingMember.addAll(member);
}
  /**
   * 멤버를 삭제하다.
   * @param member 삭제할 멤버이름
   */
    public void removeSharingMember(String member)
   {
     this.sharingMember.remove(member); 
   }
    /**
     * 공유멤버를 삭제하다.
     * @param member 삭제할 멤버들
     */
      public void removeSharingMember(List<String> member)
     {
       this.sharingMember.removeAll(member); 
     }
    /**
     * 주인을 리턴한다.
     * @return 주인
     */
  public String getOwner() {
		return owner;
	}
  /**
   * 주인을 설정한다.
   * @param owner 설정할 주인
   */
	public void setOwner(String owner) {
		this.owner = owner;
	}
	/**
	 * 공유 멤버 목록를 리턴한다
	 * @return 공유멤버목록
	 */
	public List<String> getSharingMember() {
		return sharingMember;
	}
	/**
	 * 공유멤버목록를 설정한다.
	 * @param sharingMember 설정할 공유멤버목록
	 */
	public void setSharingMember(List<String> sharingMember) {
		this.sharingMember = sharingMember;
	}
	/**
	 * 공유여부를 리턴한다.
	 * @return 공유여부
	 */
	public boolean isShare() {
		return isShare;
	}
	/**
	 * 공유여부를 설정한다.
	 * @param isShare 설정할 공유여부
	 */
	public void setShare(boolean isShare) {
		this.isShare = isShare;
	}

}
