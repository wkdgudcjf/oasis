package com.theOasis.controller;

import java.util.*;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.theOasis.file.File;
import com.theOasis.file.webFile.WebFile;
import com.theOasis.file.webFile.WebFolder;
import com.theOasis.file.webFile.WebHardManagement;
/**
 * 클라이언트와 통신매개체 역할을 하는 클래스.
 * 웹하드를 관리하는 매니져를 필드로 가지고 있으며,
 * 클라이언트와 매니져간에 소통을 원할히 하기위해
 * 값을 가공시켜준다.
 * 
 * 기본적으로 모든 파라미터는 문자열 데이터로 종속성을 줄여준다.
 * 파일 등록,검색,영구삭제,삭제,이름수정,공유를 지원한다.
 * 
 * @author JHC
 *
 */
public class WebHardController 
{
	private static WebHardController con = new WebHardController();
	private WebHardManagement manager;
	private HashMap<String,List<File>> readyShareList;
	/**
	 * 개인 웹하드를 리턴한다.
	 * @param 요청자
	 * @return 개인의 웹하드
	 */
	private WebHardController()
	{
		this.readyShareList = new HashMap<String,List<File>>();
		this.manager = new WebHardManagement();
	}
	public WebHardManagement getManager() {
		return manager;
	}
	public void setManager(WebHardManagement manager) {
		this.manager = manager;
	}
	public HashMap<String, List<File>> getReadyShareList() {
		return readyShareList;
	}
	public void setReadyShareList(HashMap<String, List<File>> readyShareList) {
		this.readyShareList = readyShareList;
	}
	public static WebHardController getInstance()
	{
		return con;
	}
	public JSONObject delete(String id1,String location,String name)
	{
		WebFolder wf = (WebFolder)this.manager.getHighLocationFile(id1, location);
		WebFolder wf1 = (WebFolder)this.manager.search(id1);

		manager.remove(id1, location, name);
		JSONObject jo1 = new JSONObject();
		
		StringBuffer str = new StringBuffer();
		str.append("<li><a href='#' onclick=\"count('null','"+id1+"')\">"+id1+"</a><ul>");
		for(int i=0;i<wf1.getSubList().size();i++)
		{
			save(wf1.getSubList().get(i),str);
		}

		str.append("</ul></li>");
		jo1.put("a", str.toString());
				
		String str1 = "<div><h1 style=\"color:navy;\">━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━</h1><h2 style=\"font-family:sans-serif; font-weight:bold; \">현재경로 : "+wf.getLocation() +"/"+ wf.getName() +"</h2><h1 style=\"color: navy;\">━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━</h1></div><div style=\"display:inline-block; margin-right:100px;\" ><form method='post' action='upload.do' enctype='multipart/form-data'><input type='hidden' name='path' value='"+wf.getLocation()+"/"+wf.getName()+"'/><input type='hidden' name='id' value='"+id1+"'/><input type='file' name='sub' /><button type='image' style=\"background-color: #0d2d53;\"><img src='image/upload.png'/></button></form></div>" +
		"<div style=\"display:inline-block; margin-left:100px;\"><a href='#' onclick=\"createf('"+wf.getLocation()+"','"+wf.getName()+"')\" ><img src='image/newfolder.png' /></a></div>";
		jo1.put("b",str1);
		
		StringBuffer str3= new StringBuffer();
		setting(wf,str3,id1);
		jo1.put("c", str3.toString());
		return jo1;
	}
	public JSONObject createFolder(String id1,String location,String name)
	{
		WebFolder wf = (WebFolder)this.manager.getHighLocationFile(id1, location);
		WebFolder wf1 = (WebFolder)this.manager.search(id1);
		
		manager.enroll(id1, location, name, 0);
		
		JSONObject jo1 = new JSONObject();
		
		StringBuffer str = new StringBuffer();
		str.append("<li><a href='#' onclick=\"count('null','"+id1+"')\">"+id1+"</a><ul>");
		for(int i=0;i<wf1.getSubList().size();i++)
		{
			save(wf1.getSubList().get(i),str);
		}

		str.append("</ul></li>");
		jo1.put("a", str.toString());
				
		String str1 = "<div><h1 style=\"color:navy;\">━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━</h1><h2 style=\"font-family:sans-serif; font-weight:bold; \">현재경로 : "+wf.getLocation() +"/"+ wf.getName() +"</h2><h1 style=\"color: navy;\">━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━</h1></div><div style=\"display:inline-block; margin-right:100px;\" ><form method='post' action='upload.do' enctype='multipart/form-data'><input type='hidden' name='path' value='"+wf.getLocation()+"/"+wf.getName()+"'/><input type='hidden' name='id' value='"+id1+"'/><input type='file' name='sub' /><button type='image' style=\"background-color: #0d2d53;\"><img src='image/upload.png'/></button></form></div>" +
		"<div style=\"display:inline-block; margin-left:100px;\"><a href='#' onclick=\"createf('"+wf.getLocation()+"','"+wf.getName()+"')\" ><img src='image/newfolder.png' /></a></div>";
		jo1.put("b",str1);
		
		StringBuffer str3= new StringBuffer();
		setting(wf,str3,id1);
		jo1.put("c", str3.toString());
		return jo1;
	}
	public JSONObject fdsearch(String id1,String location,String name)
	{
		WebFolder wf =null;
		if(location.equals("null")||location.equals("root"))
		{
			wf = (WebFolder)this.manager.search(id1);
		}
		else
		{
			wf = (WebFolder)this.manager.search(id1, location, name);
		}
		JSONObject jo1 = new JSONObject();
		
		String str1 = "<div><h1 style=\"color:navy;\">━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━</h1><h2 style=\"font-family:sans-serif; font-weight:bold; \">현재경로 : "+wf.getLocation() +"/"+ wf.getName() +"</h2><h1 style=\"color: navy;\">━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━</h1></div><div style=\"display:inline-block; margin-right:100px;\" ><form method='post' action='upload.do' enctype='multipart/form-data'><input type='hidden' name='path' value='"+wf.getLocation()+"/"+wf.getName()+"'/><input type='hidden' name='id' value='"+id1+"'/><input type='file' name='sub' /><button type='image' style=\"background-color: #0d2d53;\"><img src='image/upload.png'/></button></form></div>" +
		"<div style=\"display:inline-block; margin-left:100px;\"><a href='#' onclick=\"createf('"+wf.getLocation()+"','"+wf.getName()+"')\" ><img src='image/newfolder.png' /></a></div>";
		jo1.put("a",str1);
		
		StringBuffer str3= new StringBuffer();
		setting(wf,str3,id1);
		jo1.put("b", str3.toString());
		return jo1;
	}
	public JSONObject search(String id1)
	{
		//System.out.println(id1);
		WebFolder wf = (WebFolder)this.manager.search(id1);
		//System.out.println(wf.getName());
//		JSONArray ja = new JSONArray();
		JSONObject jo1 = new JSONObject();
//		jo1.put("a", "<li><a href='#' onclick='count(null,"+id1+")'>"+id1+"</a><ul>");
//		ja.add(jo1);
		StringBuffer str = new StringBuffer();
		str.append("<li><a href='#' onclick=\"count('null','"+id1+"')\">"+id1+"</a><ul>");
		for(int i=0;i<wf.getSubList().size();i++)
		{
//			JSONObject jo = new JSONObject();
//			String str = save(wf.getSubList().get(i));
			save(wf.getSubList().get(i),str);
//			jo.put("a",str);
//			ja.add(jo);
		}
//		JSONObject jo2 = new JSONObject();
//		jo2.put("a","</ul></li>");
//		ja.add(jo2);
		str.append("</ul></li>");
		jo1.put("a", str.toString());
		String str1 = "<div><h1 style=\"color:navy;\">━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━</h1><h2 style=\"font-family:sans-serif; font-weight:bold; \">현재경로 : "+wf.getLocation() +"/"+ wf.getName() +"</h2><h1 style=\"color: navy;\">━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━</h1></div><div style=\"display:inline-block; margin-right:100px;\" ><form method='post' action='upload.do' enctype='multipart/form-data'><input type='hidden' name='path' value='"+wf.getLocation()+"/"+wf.getName()+"'/><input type='hidden' name='id' value='"+id1+"'/><input type='file' name='sub' /><button type='image'  style=\"background-color: #0d2d53;\"><img src='image/upload.png'/></button></form></div>" +
		"<div style=\"display:inline-block; margin-left:100px;\"><a href='#' onclick=\"createf('"+wf.getLocation()+"','"+wf.getName()+"')\" ><img src='image/newfolder.png' /></a></div>";
		jo1.put("b",str1);
		
		StringBuffer str3= new StringBuffer();
		setting(wf,str3,id1);
		jo1.put("c", str3.toString());
		return jo1;
	}
	public void setting(File file,StringBuffer str,String id)
	{
		WebFolder wf = (WebFolder)file;
		for(int i=0;i<wf.getSubList().size();i++)
		{
			if(manager.isFile(wf.getSubList().get(i).getName()))
			{
				WebFile wfi = (WebFile)wf.getSubList().get(i);
				str.append("<tr><td>"+wfi.getName()+"<img src='image/file-icon.gif' /></td><td>"+wfi.getSize()/1024+"</td><td>"+wfi.getTime().get(GregorianCalendar.YEAR)+ "."+ (wfi.getTime().get(GregorianCalendar.MONTH) + 1)+ "."+ wfi.getTime().get(GregorianCalendar.DAY_OF_MONTH)+"</td><td>"+wfi.getOwner()+"</td><td><a href=\"download.do?id="+wfi.getOwner()+"&path="+wfi.getLocation()+"&filename="+wfi.getName()+"\" ><img src='image/Download-icon.png' /></a></td><td><a href='#' onclick=\"deletef('"+wfi.getLocation()+"','"+wfi.getName()+"')\" ><img src='image/delete-icon2.png' /></a></td></tr>");
			}
			else
			{
				WebFolder wfi = (WebFolder)wf.getSubList().get(i);
				str.append("<tr><td><a href='#' onclick=\"moved('"+wfi.getLocation()+"','"+wfi.getName()+"')\" >"+wfi.getName()+"<img src='image/fd-icon.PNG' /></a></td><td>"+wfi.getSize()/1024+"</td><td>"+wfi.getTime().get(GregorianCalendar.YEAR)+ "."+ (wfi.getTime().get(GregorianCalendar.MONTH) + 1)+ "."+ wfi.getTime().get(GregorianCalendar.DAY_OF_MONTH)+"</td><td>"+wfi.getOwner()+"</td><td><a href=\"download.do?id="+wfi.getOwner()+"&path="+wfi.getLocation()+"&filename="+wfi.getName()+"\" ><img src='image/Download-icon.png' /></a></td><td><a href='#' onclick=\"deletef('"+wfi.getLocation()+"','"+wfi.getName()+"')\" ><img src='image/delete-icon2.png' /></a></td></tr>");
			}
		}
	}
	public void save(File file,StringBuffer str)
	{
		saver(file,str);
	}
	public void saver(File file,StringBuffer str)
	{
		if(manager.isFile(file.getName()))
		{
			str.append("<li>"+file.getName()+"</a></li>");
		}
		else
		{
			WebFolder wf = (WebFolder)file;
			str.append("<li><a href='#' onclick=\"count('"+file.getLocation()+"','"+file.getName()+"')\">"+file.getName()+"</a><ul>");
			for(int i=0;i<wf.getSubList().size();i++)
			{
				saver(wf.getSubList().get(i),str);
			}
			str.append("</ul></li>");
		}
	}
	/**
	 * 개인웹하드에 특정디렉토리 밑에있는 파일을 리턴한다.
	 * 매니저가 내부적으로 parseLocation 메소드를 사용하여 특정디렉토리의 리스트를 반환받아
	 * 파일을 찾아 리턴한다.
	 * @param id 요청자
	 * @param location 위치
	 * @param name 파일명
	 * @return 파일
	 */
	public List<String> search(String id,String location,String name)
	{
		return null;
	}
	
	/**
	 * 개인 웹하드의 특정 디렉토리에 파일을 등록한다
	 * 매니져가 내부적으로 parseLocation 메소드를 사용하여 특정디렉토리의 리스트를 반환받아
	 * 파일을 등록한다.
	 * @param id 요청자
	 * @param location 위치
	 * @param name 파일명
	 * @return 등록여부
	 */
	public boolean enroll(String id,String location,String name,int size)
	{
		return manager.enroll(id, location, name, size);
	}
	public boolean enroll(String id)
	{
		return manager.enroll(id);
	}
	
	/**
	 * 개인 웹하드의 특정 디렉토리에 파일을 삭제한다
	 * 매니저가 내부적으로 parseLocation 메소드를 사용하여 특정디렉토리의 리스트를 반환받아
	 * 파일을 찾아 삭제한다.
	 * 삭제한 파일은 풀패스네임을 가지고 휴지통에 추가된다.
	 * 공유된 파일일 경우 요청자와 파일 주인과 같지 않을시 삭제가 되지 않는다.
	 * @param id 요청자
	 * @param location 위치
	 * @param name 파일명
	 * @return 삭제여부
	 */
	public boolean remove(String id,String location,String name)
	{
		File file = manager.search(id, location, name);
		if(manager.isFile(file.getName()))
		{
			WebFile wf = (WebFile)file;
			for(int i=0;i<wf.getSharingMember().size();i++)
			{
				WebFolder wfd = (WebFolder)manager.search(wf.getSharingMember().get(i));
				wfd.remove(file);
			}
			manager.remove(id, location, name);
			return true;
		}
		else
		{
			WebFolder wf = (WebFolder)file;
			for(int i=0;i<wf.getSharingMember().size();i++)
			{
				WebFolder wfd = (WebFolder)manager.search(wf.getSharingMember().get(i));
				wfd.remove(file);
			}
			manager.remove(id, location, name);
			return true;
		}
	}
	/**
	 * 개인웹하드에 특정 디렉토리의 파일이나 폴더의 이름을 바꾼다.
	 * 매니저가 내부적으로 parseLocation 메소드를 사용하여 특정디렉토리의 리스트를 반환받아
	 * 파일을 찾아 이름을 변경 한다.
	 * 폴더에 같은파일의 이름이 존재할경우 false를 리턴한다.
	 * 공유된 파일일 경우 요청자와 파일 주인과 같지 않을시 변환이 되지 않는다.
	 * @param id 요청자
	 * @param location 위치
	 * @param oldname 전 파일명
	 * @param newname 후 파일명
	 * @return 변환여부
	 */
	public boolean modifyName(String id,String location,String oldname,String newname)
	{
		return manager.modifyName(id, location, oldname, newname);
	}
	
	/**
	 * 개인 웹하드의 휴지통에서 파일을 영구 삭제한다.
	 * 공유된 파일일 경우 요청자와 파일 주인과 같지 않을시 삭제가 되지 않는다.
	 * @param id 요청자
	 * @param location 위치
	 * @param name 파일명
	 * @return 영구삭제여부
	 */
/*	public boolean permanentRemove(String id,String location,String name)
	{
		return manager.permanentRemove(id, location, name);
	}*/
	
	/**
	 * 개인 웹하드의 휴지통에서 파일을 복원한다.
	 * 매니저가 내부적으로 parseLocation 메소드를 사용하여 파일의 경로를 가지고 리스트를 반환받아
	 * 파일을 등록시켜준다.
     * 복원시 상위 디렉토리가 삭제되었을경우 생성시켜준다.
	 * @param id 요청자
	 * @param location 위치
	 * @param name 파일명
	 * @return 복원 여부
	 */
/*	public boolean restore(String id,String location,String name)
	{
		return manager.restore(id, location, name);
	}
	*/
	/**
	 * 공유여부를 설정한다.
	 * 폴더를 설정할경우 하위목록들도 모두 설정된다.
	 * 공유된 파일일 경우 요청자와 파일 주인과 같지 않을시 설정이 되지 않는다.
	 * @param id 요청자
	 * @param location 위치
	 * @param name 파일명
	 * @param bool 설정할 여부
	 * @return 설정여부
	 */
	public boolean setIsSharing(String id,String location,String name,boolean bool)
	{
		return manager.setIsSharing(id, location, name, bool);
	}

	
		
	/**
	 * 공유된 파일이나 폴더에 공유할 멤버를 삭제한다.
	 * 폴더를 설정할경우 하위목록도 모두 삭제된다.
	 * 공유된 멤버는 공유된 파일이나 폴더를 자신의 루트폴더에서 삭제된다.
	 * 공유된 파일일 경우 요청자와 파일 주인과 같지 않을시 설정이 되지 않는다.
	 * @param id 요청자
	 * @param location 위치
	 * @param name 파일명
	 * @param sharingMember 공유멤버
	 * @return 공유멤버설정여부
	 */
	public boolean removeSharingUser(String id,String location,String name,String sharingMember)
	{
		boolean bool = manager.removeSharingUser(id, location, name, sharingMember);
		if(bool==true)
		{
			File file = manager.search(id, location, name);
			WebFolder wf = (WebFolder)manager.search(sharingMember);
			wf.remove(file);
			return true;
		}
		else
		{
			return false;
		}
	}
	public boolean requestShare(String id,String location,String name,List<String> member)
	{
		File file = manager.search(id, location, name);
		int check;
		for(int i=0;i<member.size();i++)
		{
			List<File> list = this.readyShareList.get(member.get(i));
			check=0;
			for(int j=0;j<list.size();j++)
			{
				if(list.get(j).equals(file))
				{
					check++;
				}
			}
			if(check==0)
			{
				list.add(file);
			}
		}
		return true;
	}
	public boolean submitShare(String gid,String hid,String name)
	{
		List<File> list = this.readyShareList.get(gid);
		WebFolder wfl = (WebFolder)manager.search(gid);
		for(int i=0;i<list.size();i++)
		{
			if(manager.isFile(list.get(i).getName()))
			{
				WebFile wf = (WebFile)list.get(i);
				if(wf.getOwner().equals(hid))
				{
					if(!(wfl.getSubList().contains(wf)))
					{
						manager.enroll(gid, wf);
						manager.addSharingUser(hid, wf, hid);
						list.remove(wf);
						return true;
					}
				}
			}
			else
			{
				WebFolder wf = (WebFolder)list.get(i);
				if(wf.getOwner().equals(hid))
				{
					if(!(wfl.getSubList().contains(wf)))
					{
						manager.enroll(gid, wf);
						list.remove(wf);
						manager.addSharingUser(hid, wf, hid);
						return true;
					}
				}
			}
		}
		return false;
	}
	public boolean notSubmitShare(String gid,String hid,String name)
	{
		List<File> list = this.readyShareList.get(gid);
		for(int i=0;i<list.size();i++)
		{
			if(manager.isFile(list.get(i).getName()))
			{
				WebFile wf = (WebFile)list.get(i);
				if(wf.getOwner().equals(hid))
				{
					list.remove(wf);
				}
			}
			else
			{
				WebFolder wf = (WebFolder)list.get(i);
				if(wf.getOwner().equals(hid))
				{
					list.remove(wf);
				}
			}
		}
		return false;
	}
}
