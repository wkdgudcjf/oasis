package com.theOasis.controller;

import java.util.*;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.theOasis.file.File;
import com.theOasis.file.webFile.WebFile;
import com.theOasis.file.webFile.WebFolder;
import com.theOasis.file.webFile.WebHardManagement;
/**
 * Ŭ���̾�Ʈ�� ��ŸŰ�ü ������ �ϴ� Ŭ����.
 * ���ϵ带 �����ϴ� �Ŵ����� �ʵ�� ������ ������,
 * Ŭ���̾�Ʈ�� �Ŵ������� ������ ������ �ϱ�����
 * ���� ���������ش�.
 * 
 * �⺻������ ��� �Ķ���ʹ� ���ڿ� �����ͷ� ���Ӽ��� �ٿ��ش�.
 * ���� ���,�˻�,��������,����,�̸�����,������ �����Ѵ�.
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
	 * ���� ���ϵ带 �����Ѵ�.
	 * @param ��û��
	 * @return ������ ���ϵ�
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
				
		String str1 = "<div><h1 style=\"color:navy;\">������������������������������������������������������������������������������������������������������</h1><h2 style=\"font-family:sans-serif; font-weight:bold; \">������ : "+wf.getLocation() +"/"+ wf.getName() +"</h2><h1 style=\"color: navy;\">������������������������������������������������������������������������������������������������������</h1></div><div style=\"display:inline-block; margin-right:100px;\" ><form method='post' action='upload.do' enctype='multipart/form-data'><input type='hidden' name='path' value='"+wf.getLocation()+"/"+wf.getName()+"'/><input type='hidden' name='id' value='"+id1+"'/><input type='file' name='sub' /><button type='image' style=\"background-color: #0d2d53;\"><img src='image/upload.png'/></button></form></div>" +
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
				
		String str1 = "<div><h1 style=\"color:navy;\">������������������������������������������������������������������������������������������������������</h1><h2 style=\"font-family:sans-serif; font-weight:bold; \">������ : "+wf.getLocation() +"/"+ wf.getName() +"</h2><h1 style=\"color: navy;\">������������������������������������������������������������������������������������������������������</h1></div><div style=\"display:inline-block; margin-right:100px;\" ><form method='post' action='upload.do' enctype='multipart/form-data'><input type='hidden' name='path' value='"+wf.getLocation()+"/"+wf.getName()+"'/><input type='hidden' name='id' value='"+id1+"'/><input type='file' name='sub' /><button type='image' style=\"background-color: #0d2d53;\"><img src='image/upload.png'/></button></form></div>" +
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
		
		String str1 = "<div><h1 style=\"color:navy;\">������������������������������������������������������������������������������������������������������</h1><h2 style=\"font-family:sans-serif; font-weight:bold; \">������ : "+wf.getLocation() +"/"+ wf.getName() +"</h2><h1 style=\"color: navy;\">������������������������������������������������������������������������������������������������������</h1></div><div style=\"display:inline-block; margin-right:100px;\" ><form method='post' action='upload.do' enctype='multipart/form-data'><input type='hidden' name='path' value='"+wf.getLocation()+"/"+wf.getName()+"'/><input type='hidden' name='id' value='"+id1+"'/><input type='file' name='sub' /><button type='image' style=\"background-color: #0d2d53;\"><img src='image/upload.png'/></button></form></div>" +
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
		String str1 = "<div><h1 style=\"color:navy;\">������������������������������������������������������������������������������������������������������</h1><h2 style=\"font-family:sans-serif; font-weight:bold; \">������ : "+wf.getLocation() +"/"+ wf.getName() +"</h2><h1 style=\"color: navy;\">������������������������������������������������������������������������������������������������������</h1></div><div style=\"display:inline-block; margin-right:100px;\" ><form method='post' action='upload.do' enctype='multipart/form-data'><input type='hidden' name='path' value='"+wf.getLocation()+"/"+wf.getName()+"'/><input type='hidden' name='id' value='"+id1+"'/><input type='file' name='sub' /><button type='image'  style=\"background-color: #0d2d53;\"><img src='image/upload.png'/></button></form></div>" +
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
	 * �������ϵ忡 Ư�����丮 �ؿ��ִ� ������ �����Ѵ�.
	 * �Ŵ����� ���������� parseLocation �޼ҵ带 ����Ͽ� Ư�����丮�� ����Ʈ�� ��ȯ�޾�
	 * ������ ã�� �����Ѵ�.
	 * @param id ��û��
	 * @param location ��ġ
	 * @param name ���ϸ�
	 * @return ����
	 */
	public List<String> search(String id,String location,String name)
	{
		return null;
	}
	
	/**
	 * ���� ���ϵ��� Ư�� ���丮�� ������ ����Ѵ�
	 * �Ŵ����� ���������� parseLocation �޼ҵ带 ����Ͽ� Ư�����丮�� ����Ʈ�� ��ȯ�޾�
	 * ������ ����Ѵ�.
	 * @param id ��û��
	 * @param location ��ġ
	 * @param name ���ϸ�
	 * @return ��Ͽ���
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
	 * ���� ���ϵ��� Ư�� ���丮�� ������ �����Ѵ�
	 * �Ŵ����� ���������� parseLocation �޼ҵ带 ����Ͽ� Ư�����丮�� ����Ʈ�� ��ȯ�޾�
	 * ������ ã�� �����Ѵ�.
	 * ������ ������ Ǯ�н������� ������ �����뿡 �߰��ȴ�.
	 * ������ ������ ��� ��û�ڿ� ���� ���ΰ� ���� ������ ������ ���� �ʴ´�.
	 * @param id ��û��
	 * @param location ��ġ
	 * @param name ���ϸ�
	 * @return ��������
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
	 * �������ϵ忡 Ư�� ���丮�� �����̳� ������ �̸��� �ٲ۴�.
	 * �Ŵ����� ���������� parseLocation �޼ҵ带 ����Ͽ� Ư�����丮�� ����Ʈ�� ��ȯ�޾�
	 * ������ ã�� �̸��� ���� �Ѵ�.
	 * ������ ���������� �̸��� �����Ұ�� false�� �����Ѵ�.
	 * ������ ������ ��� ��û�ڿ� ���� ���ΰ� ���� ������ ��ȯ�� ���� �ʴ´�.
	 * @param id ��û��
	 * @param location ��ġ
	 * @param oldname �� ���ϸ�
	 * @param newname �� ���ϸ�
	 * @return ��ȯ����
	 */
	public boolean modifyName(String id,String location,String oldname,String newname)
	{
		return manager.modifyName(id, location, oldname, newname);
	}
	
	/**
	 * ���� ���ϵ��� �����뿡�� ������ ���� �����Ѵ�.
	 * ������ ������ ��� ��û�ڿ� ���� ���ΰ� ���� ������ ������ ���� �ʴ´�.
	 * @param id ��û��
	 * @param location ��ġ
	 * @param name ���ϸ�
	 * @return ������������
	 */
/*	public boolean permanentRemove(String id,String location,String name)
	{
		return manager.permanentRemove(id, location, name);
	}*/
	
	/**
	 * ���� ���ϵ��� �����뿡�� ������ �����Ѵ�.
	 * �Ŵ����� ���������� parseLocation �޼ҵ带 ����Ͽ� ������ ��θ� ������ ����Ʈ�� ��ȯ�޾�
	 * ������ ��Ͻ����ش�.
     * ������ ���� ���丮�� �����Ǿ������ ���������ش�.
	 * @param id ��û��
	 * @param location ��ġ
	 * @param name ���ϸ�
	 * @return ���� ����
	 */
/*	public boolean restore(String id,String location,String name)
	{
		return manager.restore(id, location, name);
	}
	*/
	/**
	 * �������θ� �����Ѵ�.
	 * ������ �����Ұ�� ������ϵ鵵 ��� �����ȴ�.
	 * ������ ������ ��� ��û�ڿ� ���� ���ΰ� ���� ������ ������ ���� �ʴ´�.
	 * @param id ��û��
	 * @param location ��ġ
	 * @param name ���ϸ�
	 * @param bool ������ ����
	 * @return ��������
	 */
	public boolean setIsSharing(String id,String location,String name,boolean bool)
	{
		return manager.setIsSharing(id, location, name, bool);
	}

	
		
	/**
	 * ������ �����̳� ������ ������ ����� �����Ѵ�.
	 * ������ �����Ұ�� ������ϵ� ��� �����ȴ�.
	 * ������ ����� ������ �����̳� ������ �ڽ��� ��Ʈ�������� �����ȴ�.
	 * ������ ������ ��� ��û�ڿ� ���� ���ΰ� ���� ������ ������ ���� �ʴ´�.
	 * @param id ��û��
	 * @param location ��ġ
	 * @param name ���ϸ�
	 * @param sharingMember �������
	 * @return ���������������
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
