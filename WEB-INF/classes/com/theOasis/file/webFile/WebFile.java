package com.theOasis.file.webFile;

import java.util.*;

import com.theOasis.file.File;
/**
 * ������ �ڽ����ν� ������ ����� �� �ִ� Ŭ����.
 * @author JHC
 *
 */

public class WebFile extends File
{
	/**
	 * ���� ����
	 */
  private String owner;
  /**
   * ������� ����Ʈ
   */
  private List<String> sharingMember;
  /**
   * ��������
   */
  private boolean isShare;
  /**
   * �⺻������
   */
  public WebFile()
  {
	  this.sharingMember = new LinkedList<String>();
	  this.isShare=false;
  }
  /**
   * ������ �����ε�
   * @param location ��ġ
   * @param name �̸�
   * @param owner ����
   */
  public WebFile(String location, String name, String owner)
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
   * @param size ũ��
   * @param owner ����
   */
  public WebFile(String location, String name,int size,String owner)
  {
	  super(location,name,size);
	  this.owner=owner;
	  this.sharingMember = new LinkedList<String>();
	  this.isShare=false;
  }
  /**
   * ������ �����ε�
   * @param location ��ġ
   * @param name �̸�
   * @param owner ����
   * @param isSharing ��������
   */
  public WebFile(String location, String name,int size,String owner, boolean isSharing)
  {
	  super(location,name,size);
	  this.owner=owner;
	  this.isShare=isSharing;
	  this.sharingMember = new LinkedList<String>();
  }
  /**
   * ������ �����ε�
   * @param location ��ġ
   * @param name �̸�
   * @param owner ����
   * @param isSharing ��������
   * @param sharingMember �����������Ʈ
   */
  public WebFile(String location, String name,int size,String owner, boolean isSharing,List<String> sharingMember)
  {
	  super(location,name,size);
	  this.owner=owner;
	  this.isShare=isSharing;
	  this.sharingMember = new LinkedList<String>();
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
  public WebFile(String location, String name,int size ,GregorianCalendar time, String owner, boolean isSharing)
  {
	  super(location,name,time,size);
	  this.owner=owner;
	  this.isShare=isSharing;
	  this.sharingMember = new LinkedList<String>();
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
  public WebFile(String location, String name, GregorianCalendar time, int size, String owner, List<String> sharingMember, boolean isSharing)
  {
	  super(location,name,time,size);
	  this.owner=owner;
	  this.isShare=isSharing;
	  this.sharingMember = sharingMember;
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
   * ������ �����Ѵ�.
   * @param owner ������ ����
   */
	public void setOwner(String owner) {
		this.owner = owner;
	}
	/**
	 * ���� ��� ��ϸ� �����Ѵ�
	 * @return ����������
	 */
	public List<String> getSharingMember() {
		return sharingMember;
	}
	/**
	 * ���������ϸ� �����Ѵ�.
	 * @param sharingMember ������ ����������
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
