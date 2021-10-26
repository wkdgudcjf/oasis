package com.theOasis.file;

import java.util.*;
/**
 * 휴지통클래스.
 * 파일을 삭제하면 이클래스에 모두 임시 저장된다.
 * @author JHC
 *
 */
public class RecycleBin extends Folder
{
  /**
   * 휴지통 용량
   */
  private int capacity;
  /**
   * 기본생성자
   */
  public RecycleBin()
  {
	  ;
  }
  /**
   * 생성자 오버로딩
   * @param capacity 용량
   */
  public RecycleBin(String id,int capacity)
  {
	  super(id,id+"님의휴지통");
	  this.capacity=capacity;
  }
  /**
   * 생성자 오버로딩
   * @param id 휴지통구분을 위한 사용자 id
   */
  public RecycleBin(String id)
  {
	  super(id,id+"님의휴지통");
	  this.capacity=50;
  }
  /**
   * 생성자 오버로딩
   * @param deleteFileList 삭제한 파일 리스트
   * @param capacity 용량
   */
  public RecycleBin(String id,List<File> deleteFileList, int capacity)
  {
	  super(id,id+"님의휴지통",deleteFileList);
	  this.capacity=capacity;
  }
  /**
   * 파일을 복원할 수 있다.
   * @param file 복원할 파일
   * @return 복원 여부
   */
 /* public boolean restore(File file)
  {
	 return this.remove(file);
  } */
  /**
   * 파일을 복원할 수 있다.
   * @param name 복원할 파일 이름
   * @param location 복원할 파일 위치
   * @return 복원여부
   */
 /* public boolean restore(String name, String location)
  {
	  return this.remove(name, location);
  } */
  /**
   * 삭제된파일 모두 영구 삭제한다(휴지통비우기)
   * @return 삭제여부
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
	 * 용량을 리턴한다.
	 * @return 용량
	 */
	public int getCapacity() {
		return capacity;
	}
	/**
	 * 용량을 설정한다.
	 * @param capacity 용량
	 */
	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
	 
}
