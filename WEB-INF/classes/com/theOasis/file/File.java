package com.theOasis.file;

import java.util.*;
/**
 * 파일의 기본적인 요소를 가지고 있는 클래스
 * 
 * 가장 하위구조이며 파일이 기본적으로 가져야할 경로와 이름 생성시간 크기를 가지고 있다.
 * 모든 구조는 파일에서부터 시작된다.
 * @author JHC
 *
 */


public class File
{
	/**
	 * 파일의 경로(바로 한단계 상위목록까지 저장된다)
	 */
  private String location;
  /**
   * 파일이름
   */
  private String name;
  /**
   * 파일생성시간
   */
  private GregorianCalendar time;
  /**
   * 파일크기
   */
  private int size;
  /**
   * 디폴트생성자
   */
  public File()
  {
	  this.time=new GregorianCalendar();
  }
  /**
   * 생성자오버로딩
   * @param location 파일위치
   * @param name 파일이름
   */
  public File(String location, String name)
  {
	  this.location=location;
	  this.name=name;
	  this.time=new GregorianCalendar();
  }
  /**
   * 생성자오버로딩
   * @param location 파일위치
   * @param name 파일이름
   */
  public File(String location, String name,GregorianCalendar time)
  {
	  this.location=location;
	  this.name=name;
	  this.time=time;
	  this.size=0;
  }
  /**
   * 생성자오버로딩
   * @param location 파일위치
   * @param name 파일이름
   * @param size 파일크기
   */
  public File(String location, String name,int size)
  {
	  this.location=location;
	  this.name=name;
	  this.time=new GregorianCalendar();
	  this.size=size;
  }
  /**
   * 생성자오버로딩
   * @param location 파일위치
   * @param name 파일이름
   * @param time 파일생성시간
   * @param size 파일크기
   */
  public File(String location, String name, GregorianCalendar time, int size)
  {
	  this.location=location;
	  this.name=name;
	  this.time=time;
	  this.size=size;
  }
  
/**
 * 파일위치를 리턴한다.
 * @return 파일위치
 */
	public String getLocation() {
		return location;
	}
	/**
	 * 파일위치를 설정한다.
	 * @param location 설정할 파일 위치
	 */
	public void setLocation(String location) {
		this.location = location;
	}
	
	/**
	 * 파일이름을 리턴한다.
	 * @return 파일이름
	 */
	public String getName() {
		return name;
	}
	/**
	 * 파일이름을 설정한다.
	 * @param name 설정할 파일이름
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 생성시간을 리턴한다.
	 * @return 생성시간
	 */
	public GregorianCalendar getTime() {
		return time;
	}
	/**
	 * 작성시간을 설정한다.
	 * @param time 작성시간
	 */
	public void setTime(GregorianCalendar time) {
		this.time = time;
	}
	/**
	 * 파일크기를 리턴한다.
	 * @return
	 */
	public int getSize() {
		return size;
	}
	/**
	 * 파일크기를 설정한다.
	 * @param size 파일크기
	 */
	public void setSize(int size) {
		this.size = size;
	}
	  
	 
}
