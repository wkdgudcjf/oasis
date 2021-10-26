package com.theOasis.file.webFile;

import java.util.*;

import com.theOasis.file.Folder;
/**
 * ������ ����Ҽ� �ִ� �����ν� ��������� ������ �ִ�.
 * @author JHC
 *
 */

public class WebFolder extends Folder
{
	/**
	 * ��������
	 */
  private String owner;
  /**
   * �����������Ʈ
   */
  private List<String> sharingMember;
  /**
   * ��������
   */
  private boolean isShare;
  /**
   * �⺻������
   */
  public WebFolder()
  {
	  this.sharingMember = new LinkedList<String>();
	  this.isShare=false;
  }
  /**
   * ������ �����ε�
   * @param location ��ġ
   * @param name �̸�
   * @param owner ��������
   */
  public WebFolder(String location, String name, String owner)
  {
	  super(location,name);
	  this.owner=owner;
	  this.sharingMember = new LinkedList<String>();
	  this.isShare=false;
  }
  /**
   * ������ �����ε�
   * @param location ��ġ
   * @param name �̸�
   * @param owner ��������
   * @param isSharing ��������
   */
  public WebFolder(String location, String name, String owner, boolean isSharing)
  {
	  super(location,name);
	  this.owner=owner;
	  this.sharingMember = new LinkedList<String>();
	  this.isShare=isSharing;
  }
  /**
   * ������ �����ε�
   * @param location ��ġ
   * @param name �̸�
   * @param owner ��������
   * @param isSharing ��������
   * @param sharingMember �����������Ʈ
   * @param isSharing ��������
   */
  public WebFolder(String location, String name, String owner, boolean isSharing,List<String> sharingMember)
  {
	  super(location,name);
	  this.owner=owner;
	  this.sharingMember = sharingMember;
	  this.isShare=isSharing;
  }
  /**
   * ������ �����ε�
   * @param location ��ġ
   * @param name �̸�
   * @param time �����ð�
   * @param size ũ��
   * @param owner ��������
   * @param isSharing ��������
   */
  public WebFolder(String location, String name, GregorianCalendar time, String owner, boolean isSharing)
  {
	  super(location,name,time);
	  this.owner=owner;
	  this.sharingMember = new LinkedList<String>();
	  this.isShare=isSharing;
  }
  
  /**
   * ������ �����ε�
   * @param location ��ġ
   * @param name �̸�
   * @param time �����ð�
   * @param size ũ��
   * @param owner ��������
   * @param sharingMember �����������Ʈ
   * @param isSharing ��������
   */
  public WebFolder(String location, String name, GregorianCalendar time, String owner, List<String> sharingMember, boolean isSharing)
  {
	  super(location,name,time);
	  this.owner=owner;
	  this.sharingMember = sharingMember;
	  this.isShare=isSharing;
  }
  /**
   * ��������� �߰��ϴ�.
   * @param member �߰��� ����̸�
   */
public void addSharingMember(String member)
{
	  this.sharingMember.add(member);
}
/**
 * ��������� �߰��ϴ�.
 * @param member �߰��� �����
 */
public void addSharingMember(List<String> member)
{
	  this.sharingMember.addAll(member);
}
/**
 * ����� �����ϴ�.
 * @param member ������ ����̸�
 */
  public void removeSharingMember(String member)
 {
   this.sharingMember.remove(member); 
 }
  /**
   * ��������� �����ϴ�.
   * @param member ������ �����
   */
    public void removeSharingMember(List<String> member)
   {
     this.sharingMember.removeAll(member); 
   }
  
/**
 * ������ �����Ѵ�.
 * @return ����
 */
  public String getOwner() {
	return owner;
}
/**
 * ������ �����Ѵ�
 * @param owner ������ ����
 */
public void setOwner(String owner) {
	this.owner = owner;
}
/**
 * �������Ʈ�� �����Ѵ�.
 * @return �������Ʈ
 */
public List<String> getSharingMember() {
	return sharingMember;
}
/**
 * �������Ʈ�� �����Ѵ�.
 * @param sharingMember ������ �������Ʈ
 */
public void setSharingMember(List<String> sharingMember) {
	this.sharingMember = sharingMember;
}
/**
 * �������θ� �����Ѵ�.
 * @return ��������
 */
public boolean isShare() {
	return isShare;
}
/**
 * �������θ� �����Ѵ�.
 * @param isShare ������ ��������
 */
public void setShare(boolean isShare) {
	this.isShare = isShare;
}


}
