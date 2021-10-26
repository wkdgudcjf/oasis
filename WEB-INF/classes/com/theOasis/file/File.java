package com.theOasis.file;

import java.util.*;
/**
 * ������ �⺻���� ��Ҹ� ������ �ִ� Ŭ����
 * 
 * ���� ���������̸� ������ �⺻������ �������� ��ο� �̸� �����ð� ũ�⸦ ������ �ִ�.
 * ��� ������ ���Ͽ������� ���۵ȴ�.
 * @author JHC
 *
 */


public class File
{
	/**
	 * ������ ���(�ٷ� �Ѵܰ� ������ϱ��� ����ȴ�)
	 */
  private String location;
  /**
   * �����̸�
   */
  private String name;
  /**
   * ���ϻ����ð�
   */
  private GregorianCalendar time;
  /**
   * ����ũ��
   */
  private int size;
  /**
   * ����Ʈ������
   */
  public File()
  {
	  this.time=new GregorianCalendar();
  }
  /**
   * �����ڿ����ε�
   * @param location ������ġ
   * @param name �����̸�
   */
  public File(String location, String name)
  {
	  this.location=location;
	  this.name=name;
	  this.time=new GregorianCalendar();
  }
  /**
   * �����ڿ����ε�
   * @param location ������ġ
   * @param name �����̸�
   */
  public File(String location, String name,GregorianCalendar time)
  {
	  this.location=location;
	  this.name=name;
	  this.time=time;
	  this.size=0;
  }
  /**
   * �����ڿ����ε�
   * @param location ������ġ
   * @param name �����̸�
   * @param size ����ũ��
   */
  public File(String location, String name,int size)
  {
	  this.location=location;
	  this.name=name;
	  this.time=new GregorianCalendar();
	  this.size=size;
  }
  /**
   * �����ڿ����ε�
   * @param location ������ġ
   * @param name �����̸�
   * @param time ���ϻ����ð�
   * @param size ����ũ��
   */
  public File(String location, String name, GregorianCalendar time, int size)
  {
	  this.location=location;
	  this.name=name;
	  this.time=time;
	  this.size=size;
  }
  
/**
 * ������ġ�� �����Ѵ�.
 * @return ������ġ
 */
	public String getLocation() {
		return location;
	}
	/**
	 * ������ġ�� �����Ѵ�.
	 * @param location ������ ���� ��ġ
	 */
	public void setLocation(String location) {
		this.location = location;
	}
	
	/**
	 * �����̸��� �����Ѵ�.
	 * @return �����̸�
	 */
	public String getName() {
		return name;
	}
	/**
	 * �����̸��� �����Ѵ�.
	 * @param name ������ �����̸�
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * �����ð��� �����Ѵ�.
	 * @return �����ð�
	 */
	public GregorianCalendar getTime() {
		return time;
	}
	/**
	 * �ۼ��ð��� �����Ѵ�.
	 * @param time �ۼ��ð�
	 */
	public void setTime(GregorianCalendar time) {
		this.time = time;
	}
	/**
	 * ����ũ�⸦ �����Ѵ�.
	 * @return
	 */
	public int getSize() {
		return size;
	}
	/**
	 * ����ũ�⸦ �����Ѵ�.
	 * @param size ����ũ��
	 */
	public void setSize(int size) {
		this.size = size;
	}
	  
	 
}
