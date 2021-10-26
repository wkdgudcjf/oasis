package com.theOasis.file;

import java.util.*;
/**
 * ������Ŭ����.
 * ������ �����ϸ� ��Ŭ������ ��� �ӽ� ����ȴ�.
 * @author JHC
 *
 */
public class RecycleBin extends Folder
{
  /**
   * ������ �뷮
   */
  private int capacity;
  /**
   * �⺻������
   */
  public RecycleBin()
  {
	  ;
  }
  /**
   * ������ �����ε�
   * @param capacity �뷮
   */
  public RecycleBin(String id,int capacity)
  {
	  super(id,id+"����������");
	  this.capacity=capacity;
  }
  /**
   * ������ �����ε�
   * @param id �����뱸���� ���� ����� id
   */
  public RecycleBin(String id)
  {
	  super(id,id+"����������");
	  this.capacity=50;
  }
  /**
   * ������ �����ε�
   * @param deleteFileList ������ ���� ����Ʈ
   * @param capacity �뷮
   */
  public RecycleBin(String id,List<File> deleteFileList, int capacity)
  {
	  super(id,id+"����������",deleteFileList);
	  this.capacity=capacity;
  }
  /**
   * ������ ������ �� �ִ�.
   * @param file ������ ����
   * @return ���� ����
   */
 /* public boolean restore(File file)
  {
	 return this.remove(file);
  } */
  /**
   * ������ ������ �� �ִ�.
   * @param name ������ ���� �̸�
   * @param location ������ ���� ��ġ
   * @return ��������
   */
 /* public boolean restore(String name, String location)
  {
	  return this.remove(name, location);
  } */
  /**
   * ���������� ��� ���� �����Ѵ�(���������)
   * @return ��������
   */
  public boolean empty()
  {
	 for(int i=0;i<this.getSubList().size();i++)
	 {
		 this.getSubList().remove(i);
	 }
	 return true;
  }
	/**
	 * �뷮�� �����Ѵ�.
	 * @return �뷮
	 */
	public int getCapacity() {
		return capacity;
	}
	/**
	 * �뷮�� �����Ѵ�.
	 * @param capacity �뷮
	 */
	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
	 
}
