package com.theOasis.file;

import java.util.*;

import com.theOasis.file.webFile.*;
/**
 * ������ Ȯ���� ����.
 * �������ϸ���� ������ �ִ�.
 * @author JHC
 *
 */
public class Folder extends File
{
	/**
	 * ���� ���ϸ��
	 */
  private List<File> subList;
  /**
   * �⺻ ������
   */
  public Folder()
  {
	  subList=new LinkedList<File>();
  }
  /**
   * ������ �����ε�
   * @param location ������ġ
   * @param name �����̸�
   */
  public Folder(String location, String name)
  {
	  super(location,name);
	  subList=new LinkedList<File>();
  }
  /**
   * ������ �����ε�
   * @param location ������ġ
   * @param name �����̸�
   */
  public Folder(String location, String name,GregorianCalendar time)
  {
	  super(location,name,time);
	  subList=new LinkedList<File>();
  }
  /**
   * ������ �����ε�
   * @param location ������ġ
   * @param name �����̸�
   * @param subList �������
   */
  public Folder(String location, String name, List<File> subList)
  {
	  super(location,name);
	  this.subList=subList;
  }
  /**
   * ������ �����ε�
   * @param location ������ġ
   * @param name �����̸�
   * @param time ���� �����ð�
   * @param size ���� ũ��
   * @param subList �������
   */
  public Folder(String location, String name, GregorianCalendar time, int size, List<File> subList)
  {
	  super(location,name,time,size);
	  this.subList=subList;
  }
  /**
   * ��������� �����Ѵ�.
   * @return �������
   */
  public List<File> getSubList()
  {
	return subList;
  }
  /**
   * ��������� �����Ѵ�.
   * @param subList �������
   */
  public void setSubList(List<File> subList)
  {
	  this.subList=subList;
  }
  /**
   * ����� ���϶Ǵ� ������ ���´�
   * @param location ��ġ
   * @param name �̸�
   * @return
   */
  public File get(String location, String name)
  {
	  for(int i=0;i<subList.size();i++)
	  {
		  if(subList.get(i).getLocation().equals(location)&&subList.get(i).getName().equals(name))
		  {
			  return subList.get(i);
		  }
	  }
	  return null;
  }
  /**
   * ����� ���϶Ǵ� ������ ���´�
   * @param name ���ϸ�
   * @return
   */
  public File get(String name)
  {
	  for(int i=0;i<subList.size();i++)
	  {
		  if(subList.get(i).getName().equals(name))
		  {
			  return subList.get(i);
		  }
	  }
	  return null;
  }
    
   /**
    * �ش� ���� �Ǵ� ����� ���� ��Ͽ��� �����Ѵ�
    * @param file ����������
    * @return ��������
    */
  public boolean remove(File file)
  {
	  for(int i=0;i<subList.size();i++)
	  {
		  if(subList.get(i).getLocation().equals(file.getLocation())&&subList.get(i).getName().equals(file.getName()))
		  {
			  subList.remove(file);
			  return true;
		  }
	  }
	  return false;
   }
  /**
   * ������� �ش� ��ġ�� ���� �Ǵ� ������ �����Ѵ�
   * @param name �̸�
   * @param location ��ġ
   * @return ��������
   */
  public boolean remove(String name, String location)
  {
	  for(int i=0;i<subList.size();i++)
	  {
		 if(subList.get(i).getLocation().equals(location)&&subList.get(i).getName().equals(name))
		  {
			  subList.remove(subList.get(i));
			  return true;
		  } 
	  }
	  return false;
  }
  
  /**
   * �ش� ���� �̸� �Ǵ� ������ �����Ѵ�
   * @param name
   * @return ��������
   */
  public boolean remove(String name)
  {
	  for(int i=0;i<subList.size();i++)
	  {
		  if(subList.get(i).getName().equals(name))
		  {
			  subList.remove(subList.get(i));
			  return true;
		  }
	  }
	  return false;
  
  }
  /**
   * ������ ����Ѵ�
   * @param location ��ġ
   * @param name �̸�
   * @return ��Ͽ���
   */
  public boolean add(String location, String name,int size,String owner)
  {
	  if(size!=0)
	  {
		  subList.add(new WebFile(location,name,size,owner));
		  return true;
	  }
	  else
	  {
		  subList.add(new WebFolder(location,name,owner));
		  return true;
	  }
  }
  /**
   * ������ ����Ѵ�
   * @param file ����
   * @return ��Ͽ���
   */
  public boolean add(File file)
  {
	  subList.add(file);
	  return true;
  }
  /**
   * ������ ����Ѵ�
   * @param location ��ġ.
   * @param name �̸�
   * @param bool ��������
   * @param sharingMember �������
   * @return
   */
  public boolean add(String location, String name,int size,boolean bool, List<String> sharingMember,String owner)
  {
	  if(size!=0)
	  {
		  subList.add(new WebFile(location,name,size,owner,bool,sharingMember));
		  return true;
	  }
	  else
	  {
		  subList.add(new WebFolder(location,name,owner,bool,sharingMember));
		  return true;
	  }
  }
  /**
   * �������̵� �޼ҵ�.
	 * ������ ũ�⸦ �����Ѵ�.
	 * @return ������ũ��
	 */
	public int getSize() 
	{
		int size = 0;
		for(int i=0;i<subList.size();i++)
		{
			size+=subList.get(i).getSize();
		}
		return size;
	}
}
