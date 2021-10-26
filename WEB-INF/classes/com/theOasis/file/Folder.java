package com.theOasis.file;

import java.util.*;

import com.theOasis.file.webFile.*;
/**
 * 파일을 확장한 폴더.
 * 하위파일목록을 가지고 있다.
 * @author JHC
 *
 */
public class Folder extends File
{
	/**
	 * 하위 파일목록
	 */
  private List<File> subList;
  /**
   * 기본 생성자
   */
  public Folder()
  {
	  subList=new LinkedList<File>();
  }
  /**
   * 생성자 오버로딩
   * @param location 폴더위치
   * @param name 폴더이름
   */
  public Folder(String location, String name)
  {
	  super(location,name);
	  subList=new LinkedList<File>();
  }
  /**
   * 생성자 오버로딩
   * @param location 폴더위치
   * @param name 폴더이름
   */
  public Folder(String location, String name,GregorianCalendar time)
  {
	  super(location,name,time);
	  subList=new LinkedList<File>();
  }
  /**
   * 생성자 오버로딩
   * @param location 폴더위치
   * @param name 폴더이름
   * @param subList 하위목록
   */
  public Folder(String location, String name, List<File> subList)
  {
	  super(location,name);
	  this.subList=subList;
  }
  /**
   * 생성자 오버로딩
   * @param location 폴더위치
   * @param name 폴더이름
   * @param time 폴더 생성시간
   * @param size 폴더 크기
   * @param subList 하위목록
   */
  public Folder(String location, String name, GregorianCalendar time, int size, List<File> subList)
  {
	  super(location,name,time,size);
	  this.subList=subList;
  }
  /**
   * 하위목록을 리턴한다.
   * @return 하위목록
   */
  public List<File> getSubList()
  {
	return subList;
  }
  /**
   * 하위목록을 설정한다.
   * @param subList 하위목록
   */
  public void setSubList(List<File> subList)
  {
	  this.subList=subList;
  }
  /**
   * 목록중 파일또는 폴더를 얻어온다
   * @param location 위치
   * @param name 이름
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
   * 목록중 파일또는 폴더를 얻어온다
   * @param name 파일명
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
    * 해당 파일 또는 목록을 파일 목록에서 삭제한다
    * @param file 삭제할파일
    * @return 삭제여부
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
   * 하위목록 해당 위치에 파일 또는 폴더를 삭제한다
   * @param name 이름
   * @param location 위치
   * @return 삭제여부
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
   * 해당 파일 이름 또는 폴더를 삭제한다
   * @param name
   * @return 삭제여부
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
   * 파일을 등록한다
   * @param location 위치
   * @param name 이름
   * @return 등록여부
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
   * 파일을 등록한다
   * @param file 파일
   * @return 등록여부
   */
  public boolean add(File file)
  {
	  subList.add(file);
	  return true;
  }
  /**
   * 파일을 등록한다
   * @param location 위치.
   * @param name 이름
   * @param bool 공유여부
   * @param sharingMember 공유멤버
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
   * 오버라이딩 메소드.
	 * 폴더의 크기를 리턴한다.
	 * @return 폴더의크기
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
