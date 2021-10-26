package com.theOasis.file.webFile;

import java.util.*;

import com.theOasis.file.Folder;
/**
 * 웹에서 사용할수 있는 폴더로써 하위목록을 가지고 있다.
 * @author JHC
 *
 */

public class WebFolder extends Folder
{
	/**
	 * 폴더주인
	 */
  private String owner;
  /**
   * 공유멤버리스트
   */
  private List<String> sharingMember;
  /**
   * 공유여부
   */
  private boolean isShare;
  /**
   * 기본생성자
   */
  public WebFolder()
  {
	  this.sharingMember = new LinkedList<String>();
	  this.isShare=false;
  }
  /**
   * 생성자 오버로딩
   * @param location 위치
   * @param name 이름
   * @param owner 폴더주인
   */
  public WebFolder(String location, String name, String owner)
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
   * @param owner 폴더주인
   * @param isSharing 공유여부
   */
  public WebFolder(String location, String name, String owner, boolean isSharing)
  {
	  super(location,name);
	  this.owner=owner;
	  this.sharingMember = new LinkedList<String>();
	  this.isShare=isSharing;
  }
  /**
   * 생성자 오버로딩
   * @param location 위치
   * @param name 이름
   * @param owner 폴더주인
   * @param isSharing 공유여부
   * @param sharingMember 공유멤버리스트
   * @param isSharing 공유여부
   */
  public WebFolder(String location, String name, String owner, boolean isSharing,List<String> sharingMember)
  {
	  super(location,name);
	  this.owner=owner;
	  this.sharingMember = sharingMember;
	  this.isShare=isSharing;
  }
  /**
   * 생성자 오버로딩
   * @param location 위치
   * @param name 이름
   * @param time 생성시간
   * @param size 크기
   * @param owner 폴더주인
   * @param isSharing 공유여부
   */
  public WebFolder(String location, String name, GregorianCalendar time, String owner, boolean isSharing)
  {
	  super(location,name,time);
	  this.owner=owner;
	  this.sharingMember = new LinkedList<String>();
	  this.isShare=isSharing;
  }
  
  /**
   * 생성자 오버로딩
   * @param location 위치
   * @param name 이름
   * @param time 생성시간
   * @param size 크기
   * @param owner 폴더주인
   * @param sharingMember 공유멤버리스트
   * @param isSharing 공유여부
   */
  public WebFolder(String location, String name, GregorianCalendar time, String owner, List<String> sharingMember, boolean isSharing)
  {
	  super(location,name,time);
	  this.owner=owner;
	  this.sharingMember = sharingMember;
	  this.isShare=isSharing;
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
 * 주인을 설정한다
 * @param owner 설정할 주인
 */
public void setOwner(String owner) {
	this.owner = owner;
}
/**
 * 멤버리스트를 리턴한다.
 * @return 멤버리스트
 */
public List<String> getSharingMember() {
	return sharingMember;
}
/**
 * 멤버리스트를 설정한다.
 * @param sharingMember 설정할 멤버리스트
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
