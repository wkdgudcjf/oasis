package com.theOasis.file.webFile;

import java.util.*;

import com.theOasis.file.File;
import com.theOasis.file.RecycleBin;

/**
 * ���ϵ带 �������ִ� Ŭ����
 * ���ϵ忡 ���� ��� ��û�� �ް� ó���ϴ� Ŭ�����̴�.
 * �����Ǵ� ������ �����Ҽ� ������ ������ �� �� �ִ�.
 * Ư�� ��ġ�� ������ ������ ����� �� �ְ�,
 * ������ ������ �� �ִ�.
 * �����̳� �������� ���浵 �����ϴ�. ȸ������ ��Ʈ������ �ϳ��� �־�����,
 * �����뵵 �ϳ��� �־�����.
 * Ʈ�����·� ������ �����Ѵ�. 
 * 
 * @author JHC
 *
 */
public class WebHardManagement
{
	/**
	 * ����ڸ��� �ϳ��� ������ �� �� �ִ� �������� �ʵ�
	 * ���̵� �����ϸ� �� ���̵��� �������� �����Ѵ�.
	 */
	private HashMap<String,File> webFolderList;
	/**
	 * �⺻ ������
	 */
	public WebHardManagement()
	{
		this.webFolderList= new HashMap<String,File>();
	}
	/**
	 * ������ �����ε�
	 * @param webFolder ������ �ʰ�ü
	 */
	public WebHardManagement(HashMap<String,File> webFolderList)
	{
		this.webFolderList=webFolderList;
	}
	/**
	 * ���λ�� �޼ҵ�� ��û�� �� ���丮�� ��ū�Ͽ� ���丮�� �����Ѵ�.
	 * ��� �޼ҵ忡�� ���ȴ�.
	 * �������� ��ġ�� / ������ �Ľ��Ѵ�.
	 * @param location
	 *
	 */
	private List<String> parseLocation(String location)
	{
		List<String> str = new LinkedList<String>();
		StringTokenizer stk = new StringTokenizer(location,"/");
		while(stk.hasMoreTokens())
		{
			str.add(stk.nextToken());
		}
		return str;
	}
	public HashMap<String, File> getWebFolderList() {
		return webFolderList;
	}
	public void setWebFolderList(HashMap<String, File> webFolderList) {
		this.webFolderList = webFolderList;
	}
	/**
	 * ���� ���ϵ��� ��Ʈ���丮�� �����Ѵ�.
	 * ��Ʈ������ �ش��Ѵ�.
	 * @param ��û��
	 * @return ������ ���ϵ�
	 */
	public File search(String id)
	{
		//System.out.println(id+"�� ������ �˻��ҷ��±�.."+webFolderList.get(id).getName());
		return webFolderList.get(id);
	}
	/**
	 * �������ϵ忡 Ư�����丮 �ؿ��ִ� ������ �����Ѵ�.
	 * ���������� getHighLocationFile �޼ҵ带 ����Ͽ� Ư�����丮�� ���Ϲ޾�
	 * ������ ã�� �����Ѵ�.
	 * @param id ��û��
	 * @param location ��ġ
	 * @param name ���ϸ�
	 * @return ����
	 */
	public File search(String id,String location,String name)
	{
		if(location==null)
		{
			return search(id);
		}
		WebFolder file = (WebFolder)getHighLocationFile(id,location);
		for(int i=0;i<file.getSubList().size();i++)
		{
			if(file.getSubList().get(i).getName().equals(name))
			{
				return file.getSubList().get(i);
			}
		}
		return null;
	}
	/**
	 * ��û���� ����� ������ ���Ͻ����ش�.
	 * ���������� parsLocation�� ����ϸ�,
	 * 
	 *  
	 * @param id ��ûid
	 * @param location ���
	 * @return �������
	 */
	public File getHighLocationFile(String id,String location)
	{
		List<String> str = this.parseLocation(location);
		WebFolder file = (WebFolder)search(id);
		for(int i=0;i<str.size()-1;i++)
		{
		    for(int j=0;j<file.getSubList().size();j++)
		    {
		    	if(file.getSubList().get(j).getName().equals(str.get(i+1)))
		    	{
		    		file = (WebFolder)(file.getSubList().get(j));
		    		break;
		    	}
		    }
	    }
		return file;
	}
	public static boolean isFile(String name)
	{
		if(name.contains("."))
		{
			return true;
		}
		return false;
	}
		 
	/**
	 * ���� ���ϵ带 ����Ѵ�
	 * ȸ�����԰� ���ÿ� �Ͼ��.
	 * @param id ��û��
	 * @return ��Ͽ���
	 */

	public boolean enroll(String id)
	{
		if(webFolderList.get(id)!=null)
		{
			return false;
		}
		WebFolder folder = new WebFolder("root",id,id);
	//  folder.add(new RecycleBin(id));	
		webFolderList.put(id,folder);
		//System.out.println(id+"���������"+folder.getName()+"�̶�������..");
		return true;
	}
	/**
	 * ���� ���ϵ��� ��Ʈ������ ������ ����Ѵ�
	 * ���������� getHighLocationFile �޼ҵ带 ����Ͽ� Ư�����丮�� ����Ʈ�� ��ȯ�޾�
	 * ������ ����Ѵ�.
	 * @param id ��û��
	 * @param file ����� ����
	 * @return ��Ͽ���
	 */
	public boolean enroll(String id,File file)
	{
	  /*if(file==null||(file.getName().equals(id+"����������")&&file.getLocation().equals(id)))
		{
			return false;
		}*/
		WebFolder folder = (WebFolder)getHighLocationFile(id,file.getLocation());
		for(int i=0;i<folder.getSubList().size();i++)
		{
			if(folder.getSubList().get(i).getName().equals(file.getName()))
			{
				file.setName(file.getName()+"(!)");
			}
		}
		folder.getSubList().add(file);
		return true;
	}
	/**
	 * ���� ���ϵ��� Ư�� ���丮�� ������ ����Ѵ�
	 * ���������� getHighLocationFile�� parseLocation �޼ҵ带 ����Ͽ� Ư�����丮�� ����Ʈ�� ��ȯ�޾�
	 * ������ ����Ѵ�.
	 * @param id ��û��
	 * @param location ��ġ
	 * @param name ���ϸ�
	 * @return ��Ͽ���
	 */
	public boolean enroll(String id,String location,String name,int size)
	{
      /*if(location.equals(id)&&name.equals(id+"����������"))
		{
			return false;
		}*/
		WebFolder folder = (WebFolder)getHighLocationFile(id,location);
		for(int i=0;i<folder.getSubList().size();i++)
		{
			if(folder.getSubList().get(i).getName().equals(name))
			{
				name = name+"(!)";
			}
		}
		if(size>0)
		{
			folder.getSubList().add(new WebFile(location,name,size,id));
		}
		else
		{
			folder.getSubList().add(new WebFolder(location,name,id));
		}
		return true;
	}
	/**
	 * ���� ���ϵ��� ��Ʈ������ ������ �����Ѵ�
	 * ���������� getHighLocationFile �޼ҵ带 ����Ͽ� Ư�����丮�� ����Ʈ�� ��ȯ�޾�
	 * ������ ã�� �����Ѵ�.
	 * ������ ������ ��� ��û�ڿ� ���� ���ΰ� ���� ������ ������ ���� �ʴ´�.
	 * @param id ��û��
	 * @param file ������ ����
	 * @return ��Ͽ���
	 */
	public boolean remove(String id,File file)
	{
		/*if(file.getName().equals(id+"����������")&&file.getLocation().equals(id))
		{
			return false;
		}*/
		
		if(!(isFile(file.getName())))
	    {
			WebFolder folder = (WebFolder)getHighLocationFile(id,file.getLocation());
			WebFolder fd = (WebFolder)(folder.get(file.getName()));
	    	if(fd.getOwner().equals(id))
	    	{
	    		if(fd.getSharingMember().size()>0)
	    		{
	    			for(int i=0;i<fd.getSharingMember().size();i++)
	    			{
	    				fd.getSharingMember().remove(i);
	    			}
	    		}
	    		folder.remove(file.getName());
	    		//WebFolder wf = (WebFolder)search(id);
				//RecycleBin rb = (RecycleBin)wf.getSubList().get(0);
				//rb.add(fd);
	    		return true;
	    	}
	    	else
	    	{
	    		return false;
	    	}
        }
	    else
	    {
	    	WebFolder folder = (WebFolder)getHighLocationFile(id,file.getLocation());
			WebFile fd = (WebFile)(folder.get(file.getName()));
	    	if(fd.getOwner().equals(id))
	    	{
	    		if(fd.getSharingMember().size()>0)
	    		{
	    			for(int i=0;i<fd.getSharingMember().size();i++)
	    			{
	    				fd.getSharingMember().remove(i);
	    			}
	    		}
	    		folder.remove(file.getName());
	    		//WebFolder wf = (WebFolder)search(id);
				//RecycleBin rb = (RecycleBin)wf.getSubList().get(0);
				//rb.add(fd);
	    		return true;
	    	}
	    	else
	    	{
	    		return false;
	    	}
	    }
	   
	}
	/**
	 * ���� ���ϵ��� Ư�� ���丮�� ������ �����Ѵ�
	 * ���������� getHighLocationFile �޼ҵ带 ����Ͽ� Ư�����丮�� ����Ʈ�� ��ȯ�޾�
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
		if(name.equals(id+"����������")&&location.equals(id))
		{
			return false;
		}
		
		if(!(isFile(name)))
	    {
			WebFolder folder = (WebFolder)getHighLocationFile(id,location);
			WebFolder fd = (WebFolder)(folder.get(name));
	    	if(fd.getOwner().equals(id))
	    	{
	    		if(fd.getSharingMember().size()>0)
	    		{
	    			for(int i=0;i<fd.getSharingMember().size();i++)
	    			{
	    				fd.getSharingMember().remove(i);
	    			}
	    		}
	    		folder.remove(name);
	    		//WebFolder wf = (WebFolder)search(id);
				//RecycleBin rb = (RecycleBin)wf.getSubList().get(0);
				//rb.add(fd);
	    		return true;
	    	}
	    	else
	    	{
	    		return false;
	    	}
        }
	    else
	    {
	    	WebFolder folder = (WebFolder)getHighLocationFile(id,location);
			WebFile fd = (WebFile)(folder.get(name));
	    	if(fd.getOwner().equals(id))
	    	{
	    		if(fd.getSharingMember().size()>0)
	    		{
	    			for(int i=0;i<fd.getSharingMember().size();i++)
	    			{
	    				fd.getSharingMember().remove(i);
	    			}
	    		}
	    		folder.remove(name);
	    		//WebFolder wf = (WebFolder)search(id);
				//RecycleBin rb = (RecycleBin)wf.getSubList().get(0);
				//rb.add(fd);
	    		return true;
	    	}
	    	else
	    	{
	    		return false;
	    	}
	    }
    }
	/**
	 * �������ϵ忡 Ư�� ���丮�� �����̳� ������ �̸��� �ٲ۴�.
	 * ���������� getHighLocationFile �޼ҵ带 ����Ͽ� Ư�����丮�� ����Ʈ�� ��ȯ�޾�
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
		if(location==null)
		{
			return false;
		}
		/*if((oldname.equals(id+"����������")||newname.equals(id+"����������"))&&location.equals(id))
		{
		    return false;
		}*/
		WebFolder folder = (WebFolder)getHighLocationFile(id,location);
		for(int i=0;i<folder.getSubList().size();i++)
		{
			if(folder.getSubList().get(i).getName().equals(newname))
			{
				return false;
			}
		}
		if(isFile(oldname))
	    {
			WebFile fd = (WebFile)(folder.get(oldname));
			if(fd==null)
			{
				return false;
			}
			if(fd.getOwner().equals(id))
			{
				if(isFile(newname))
				{
					StringTokenizer stk=new StringTokenizer(oldname,".");
					stk.nextToken();
					StringTokenizer stk1=new StringTokenizer(newname,".");
					stk1.nextToken();
					if(stk.nextToken().equals(stk1.nextToken()))
					{
						fd.setName(newname);
						return true;
					}
					return false;
				}
				else
				{
					StringTokenizer stk=new StringTokenizer(oldname,".");
					stk.nextToken();
					fd.setName(newname+"."+stk.nextToken());
					return true;
				}
			}
			else
			{
				return false;
			}
	     }
		 else
	     {
			WebFolder fd = (WebFolder)(folder.get(oldname));
			if(fd==null)
			{
				return false;
			}
			if(fd.getOwner().equals(id))
			{
				fd.setName(newname);
				settingAllLocation(fd,location,oldname,newname);
				return true;
			}
			else
			{
				return false;
			}
	    }
	}
	private void settingAllLocation(File fd,String location,String oldname,String newname)
	{
		if(isFile(fd.getName()))
		{
			;
		}
		else
		{
			WebFolder wf = (WebFolder)fd;
			for(int i=0;i<wf.getSubList().size();i++)
			{
				wf.getSubList().get(i).setLocation(wf.getSubList().get(i).getLocation().replace(location+"/"+oldname, location+"/"+newname));
				settingAllLocation(wf.getSubList().get(i),location,oldname,newname);
			}
		}
	}
	private void settingAllLocation(File fd,String oldlocation,String newlocation)
	{
		if(isFile(fd.getName()))
		{
			;
		}
		else
		{
			WebFolder wf = (WebFolder)fd;
			for(int i=0;i<wf.getSubList().size();i++)
			{
				wf.getSubList().get(i).setLocation(wf.getSubList().get(i).getLocation().replace(oldlocation, newlocation));
				settingAllLocation(wf.getSubList().get(i),oldlocation,newlocation);
			}
		}
	}
	public boolean moveDirectory(String id,String oldlocation,String newlocation,String name)
	{
		WebFolder folder = (WebFolder)getHighLocationFile(id,oldlocation);
		if(!(isFile(name)))
	    {
			WebFolder fd = (WebFolder)(folder.get(name));
			if(fd==null)
			{
				return false;
			}
			if(fd.getOwner().equals(id))
			{
				folder.remove(name);
				WebFolder folder2 = (WebFolder)getHighLocationFile(id,newlocation);
				List<String> list = folder2.getSharingMember();
				folder2.add(fd);
				fd.setLocation(newlocation);
				settingAllLocation(fd,oldlocation,newlocation);
				for(int i=0;i<list.size();i++)
				{
					addSharingUser(id,newlocation,name,list.get(i));
				}
				return true;
			}
			else
			{
				return false;
			}
		}
		else
	     {
			WebFile fd = (WebFile)(folder.get(name));
			if(fd==null)
			{
				return false;
			}
			if(fd.getOwner().equals(id))
			{
				folder.remove(name);
				WebFolder folder2 = (WebFolder)getHighLocationFile(id,newlocation);
				List<String> list = folder2.getSharingMember();
				folder2.add(fd);
				fd.setLocation(newlocation);
				for(int i=0;i<list.size();i++)
				{
					addSharingUser(id,newlocation,name,list.get(i));
				}
				return true;
			}
			else
			{
				return false;
			}
	    }
	}
	/**
	 * ���� ���ϵ��� �����뿡�� ������ ���������Ѵ�.
	 * ������ ������ ��� ��û�ڿ� ���� ���ΰ� ���� ������ ������ ���� �ʴ´�.
	 * @param id ��û��
	 * @param file ���������� ����
	 * @return ������������
	 */
	/*public boolean permanentRemove(String id,File file)
	{
		if(file.getLocation().equals(id+"����������"))
		{
		WebFolder rec = (WebFolder)search(id);
		RecycleBin rb = (RecycleBin)(rec.getSubList().get(0));
		rb.remove(file);
		return true;
		}
		return false;
	}*/
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
		if(location.equals(id+"����������"))
		{
		WebFolder rec = (WebFolder)search(id);
		RecycleBin rb = (RecycleBin)(rec.getSubList().get(0));
		rb.remove(name);
		return true;
		}
		return false;
	}*/
	/**
	 * ���� ���ϵ��� �����뿡�� ������ �����Ѵ�.
	 * ���������� getHighLocationFile �޼ҵ带 ����Ͽ� ������ ��θ� ������ ����Ʈ�� ��ȯ�޾�
	 * ������ ��Ͻ����ش�.
	 * ������ ��Ʈ������ ��ϵȴ�.
	 * @param id ��û��
	 * @param file ������ ����
	 * @return ���� ����
	 */
/*	public boolean restore(String id,File file)
	{
		if(file.getLocation().equals(id+"����������"))
		{
		settingAllLocation(file,file.getLocation(),id);
		file.setLocation(id);
		enroll(id,file);
		permanentRemove(id,file);
		return true;
		}
		return false;
	}*/
	/**
	 * ���� ���ϵ��� �����뿡�� ������ �����Ѵ�.
	 * ���������� getHighLocationFile �޼ҵ带 ����Ͽ� ������ ��θ� ������ ����Ʈ�� ��ȯ�޾�
	 * ������ ��Ͻ����ش�.
	 * ������ ���� ���丮�� �����Ǿ������ ���������ش�.
	 * @param id ��û��
	 * @param location ��ġ
	 * @param name ���ϸ�
	 * @return ���� ����
	 */
	/*public boolean restore(String id,String location,String name)
	{
		if(location.equals(id+"����������"))
		{
			WebFolder wf = (WebFolder)search(id);
			RecycleBin rb = (RecycleBin)wf.getSubList().get(0);
			File file = rb.get(name);
			settingAllLocation(file,file.getLocation(),id);
			file.setLocation(id);
			enroll(id,file);
			permanentRemove(id,location,name);
		}
		return true;
	}*/
	/**
	 * �������θ� �����Ѵ�.
	 * ������ �����Ұ�� ������ϵ鵵 ��� �����ȴ�.
	 * ������ ������ ��� ��û�ڿ� ���� ���ΰ� ���� ������ ������ ���� �ʴ´�.
	 * @param id ��û��
	 * @param file ������ ����
	 * @param bool ������ ����
	 * @return ��������
	 */
	public boolean setIsSharing(String id,File file,boolean bool)
	{
	/*	if(file==null||(file.getName().equals(id+"����������")&&file.getLocation().equals(id)))
		{
			return false;
		}*/
        WebFolder folder = (WebFolder)getHighLocationFile(id,file.getLocation());
        if(folder.isShare())
		{
			return false;
		}
       	File fl = folder.get(file.getName());
		if(isFile(fl.getName()))
		{
			WebFile wf = (WebFile)fl;
			if(!(wf.getOwner().equals(id)))
			{
				return false;
			}
			else
			{
				wf.setShare(true);
				return true;
			}
		}
		else
		{
			WebFolder wfolder = (WebFolder)fl;
			if(!(wfolder.getOwner().equals(id)))
			{
				return false;
			}
			settingAllSharing(id,fl,bool);
		}
		return true;
	}
	private void settingAllSharing(String id,File fd,boolean bool)
	{
		if(isFile(fd.getName()))
		{
			WebFile wf = (WebFile)fd;
			if(!(wf.getOwner().equals(id)))
			{
				;
			}
			else
			{
				wf.setShare(true);
			}
		}
		else
		{
			WebFolder wf = (WebFolder)fd;
			wf.setShare(bool);
			for(int i=0;i<wf.getSubList().size();i++)
			{
				settingAllSharing(id,wf.getSubList().get(i),bool);
			}
		}
	}
	
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
		/*if(name.equals(id+"����������")&&location.equals(id))
		{
			return false;
		}*/
        WebFolder folder = (WebFolder)getHighLocationFile(id,location);
        if(folder.isShare())
		{
			return false;
		}
		File fl = folder.get(name);
		if(isFile(fl.getName()))
		{
			WebFile wf = (WebFile)fl;
			if(!(wf.getOwner().equals(id)))
			{
				return false;
			}
			else
			{
				wf.setShare(true);
				return true;
			}
		}
		else
		{
			WebFolder wfolder = (WebFolder)fl;
			if(!(wfolder.getOwner().equals(id)))
			{
				return false;
			}
			settingAllSharing(id,fl,bool);
		}
		return true;
	}
	/**
	 * ������ �����̳� ������ ������ ����� �����Ѵ�.
	 * ������ �����Ұ�� ������ϵ� ��� �����ȴ�.
	 * ������ ����� ������ �����̳� ������ �ڽ��� ��Ʈ������ �����ȴ�.
	 * ������ ������ ��� ��û�ڿ� ���� ���ΰ� ���� ������ ������ ���� �ʴ´�.
	 * @param id ��û��
	 * @param location ��ġ
	 * @param name ���ϸ�
	 * @param sharingMember ������ �����
	 * @return ���������������
	 */
	public boolean addSharingUser(String id,String location,String name,String sharingMember)
	{
		WebFolder folder = (WebFolder)getHighLocationFile(id,location);
		File file = folder.get(name);
		if(isFile(file.getName()))
		{
			WebFile wf = (WebFile)file;
			if(!(wf.getOwner().equals(id)))
			{
				return false;
			}
			if(!(wf.isShare()))
			{
				setIsSharing(id,location,name,true);
			}
			if(wf.getSharingMember().contains(sharingMember))
			{
				return false;
			}
			wf.addSharingMember(sharingMember);
		}
		else
		{
			WebFolder wfolder = (WebFolder)file;
			if(!(wfolder.getOwner().equals(id)))
			{
				return false;
			}
			if(!(wfolder.isShare()))
			{
				setIsSharing(id, location, name, true);
			}
			if(wfolder.getSharingMember().contains(sharingMember))
			{
				return false;
			}
			settingAddSharingMember(id,wfolder,sharingMember);
		}
		return true;
	}
	public boolean addSharingUser(String id,File file,String sharingMember)
	{
		WebFolder folder = (WebFolder)getHighLocationFile(id,file.getLocation());
		File fl = folder.get(file.getName());
		if(isFile(file.getName()))
		{
			WebFile wf = (WebFile)fl;
			if(!(wf.getOwner().equals(id)))
			{
				return false;
			}
			if(!(wf.isShare()))
			{
				setIsSharing(id,file,true);
			}
			if(wf.getSharingMember().contains(sharingMember))
			{
				return false;
			}
			wf.addSharingMember(sharingMember);
		}
		else
		{
			WebFolder wfolder = (WebFolder)fl;
			if(!(wfolder.getOwner().equals(id)))
			{
				return false;
			}
			if(!(wfolder.isShare()))
			{
				setIsSharing(id, file, true);
			}
			if(wfolder.getSharingMember().contains(sharingMember))
			{
				return false;
			}
			settingAddSharingMember(id,wfolder,sharingMember);
		}
		return true;
	}
	private void settingAddSharingMember(String id,File file,String member)
	{
		if(isFile(file.getName()))
		{
			WebFile wf = (WebFile)file;
			if(!(wf.getOwner().equals(id)))
			{
				;
			}
			else
			{
				if(wf.getSharingMember().contains(member))
				{
					;
				}
				else
				{
					wf.addSharingMember(member);
				}
			}
		}
		else
		{
			WebFolder wf = (WebFolder)file;
			if(wf.getSharingMember().contains(member))
			{
				;
			}
			else
			{
				wf.addSharingMember(member);
				for(int i=0;i<wf.getSubList().size();i++)
				{
					settingAddSharingMember(id,wf.getSubList().get(i),member);
				}
			}
		}
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
		WebFolder folder = (WebFolder)getHighLocationFile(id,location);
		if(folder.getSharingMember().contains(sharingMember))
		{
			return false;
		}
		File file = folder.get(name);
		if(isFile(file.getName()))
		{
			WebFile wf = (WebFile)file;
			if(!(wf.getOwner().equals(id)))
			{
				return false;
			}
			if(wf.getSharingMember().contains(sharingMember))
			{
				wf.removeSharingMember(sharingMember);
			}
			else
			{
				return false;
			}
		}
		else
		{
			WebFolder wfolder = (WebFolder)file;
			if(!(wfolder.getOwner().equals(id)))
			{
				return false;
			}
			if(wfolder.getSharingMember().contains(sharingMember))
			{
				settingRemoveSharingMember(id,wfolder,sharingMember);
			}
			else
			{
				return false;
			}
		}
		return true;
	}
	public boolean removeSharingUser(String id,File file,String sharingMember)
	{
		WebFolder folder = (WebFolder)getHighLocationFile(id,file.getLocation());
		if(folder.getSharingMember().contains(sharingMember))
		{
			return false;
		}
		File fl = folder.get(file.getName());
		if(isFile(fl.getName()))
		{
			WebFile wf = (WebFile)fl;
			if(!(wf.getOwner().equals(id)))
			{
				return false;
			}
			if(wf.getSharingMember().contains(sharingMember))
			{
				wf.removeSharingMember(sharingMember);
			}
			else
			{
				return false;
			}
		}
		else
		{
			WebFolder wfolder = (WebFolder)fl;
			if(!(wfolder.getOwner().equals(id)))
			{
				return false;
			}
			if(wfolder.getSharingMember().contains(sharingMember))
			{
				settingRemoveSharingMember(id,wfolder,sharingMember);
			}
			else
			{
				return false;
			}
		}
		return true;
	}
	private void settingRemoveSharingMember(String id,File file,String member)
	{
		if(isFile(file.getName()))
		{
			WebFile wf = (WebFile)file;
			if(!(wf.getOwner().equals(id)))
			{
				;
			}
			else
			{
				if(wf.getSharingMember().contains(member))
				{
					wf.removeSharingMember(member);
				}
				else
				{
					;
				}
			}
		}
		else
		{
			WebFolder wf = (WebFolder)file;
			if(wf.getSharingMember().contains(member))
			{
				wf.removeSharingMember(member);
				for(int i=0;i<wf.getSubList().size();i++)
				{
					settingRemoveSharingMember(id,wf.getSubList().get(i),member);
				}
			}
			else
			{
				;
			}
		}
	}
	public static void main(String args[])
	{
		String id1="jhc";
		String id2="kjs";
		String id3="kyw";
		WebHardManagement manager = new WebHardManagement();
		
		manager.enroll(id1);
		manager.enroll(id2);
		manager.enroll(id3);
		
		
		manager.enroll(id1, id1, "����ö", 0);
		manager.enroll(id1, id1+"/����ö","��ö����.jpg",23);
		manager.enroll(id1, id1+"/����ö","��ö����1.jpg",13);
		manager.enroll(id1, id1+"/����ö","��ö����",0);
		manager.enroll(id1, id1+"/����ö/��ö����","��ö����3.ppt",7);
		manager.enroll(id1, id1,"����ö2",0);
		manager.enroll(id1, id1+"/����ö2","��ö����2",0);
		
		manager.enroll(id2, id2, "������", 0);
		manager.enroll(id2, id2+"/������","��������.jpg",23);
		manager.enroll(id2, id2+"/������","��������1.jpg",13);
		manager.enroll(id2, id2+"/������","��������",0);
		manager.enroll(id2, id2+"/������/��������","��������3.ppt",7);
		manager.enroll(id2, id2,"������2",0);
		manager.enroll(id2, id2+"/������2","��������2",0);
		
		manager.enroll(id3, id3, "�迹��", 0);
		manager.enroll(id3, id3+"/�迹��","��������.jpg",23);
		manager.enroll(id3, id3+"/�迹��","��������1.jpg",13);
		manager.enroll(id3, id3+"/�迹��","��������",0);
		manager.enroll(id3, id3+"/�迹��/��������","��������3.ppt",7);
		manager.enroll(id3, id3,"�迹��2",0);
		manager.enroll(id3, id3+"/�迹��2","��������2",0);

		manager.addSharingUser(id1, id1, "����ö", id3);
		manager.enroll(id3, manager.search(id1, id1, "����ö"));
		manager.enroll(id3, id3,"�迹��3",0);
		manager.enroll(id3, id3+"/�迹��3","��������3",0);
		
	    WebFolder file1 = (WebFolder)manager.search(id1);
	    WebFolder file2 = (WebFolder)manager.search(id2);
	    WebFolder file3 = (WebFolder)manager.search(id3);
	    
	    System.out.println(file1.getSubList().size());
	    System.out.println(file2.getSubList().size());
	    System.out.println(file3.getSubList().size());
	    
	    print(id1,file1);
	  //  printShare(id1,file1);
	    System.out.println("��ö ��");
	    print(id2,file2);
	 //   printShare(id2,file2);
	    System.out.println("���� ��");
	    print(id3,file3);
	  //  printShare(id3,file3);
	    System.out.println("���� ��");
	}
/*	public static void printShare(String id,File file)
	{
		 if(isFile(file.getName()))
		 {
			 WebFile wf = (WebFile)file;
			 if(!(wf.getOwner().equals(id)))
			 {
				 System.out.println("���="+file.getLocation()+"/"+file.getName());
				 if(wf.getSharingMember().size()>0)
				 {
				    System.out.println(wf.getSharingMember().get(0));
				 }
			 }
		  }
		  else
		  {
			   WebFolder wf= (WebFolder)file;
			   if(!(wf.getOwner().equals(id)))
			   {
				   System.out.println("���="+wf.getLocation()+"/"+wf.getName());
				   if(wf.getSharingMember().size()>0)
				   {
						System.out.println(wf.getSharingMember().get(0));
				   }
			   }
			   for(int i=0;i<wf.getSubList().size();i++)
			   {
			      printShare(id,wf.getSubList().get(i));
			   }
			}
		 }*/
	public static void print(String id,File file)
	 {
    	  if(isFile(file.getName()))
		  {
			 WebFile wf = (WebFile)file;
			// if(wf.getOwner().equals(id))
			// {
				  System.out.println("���="+file.getLocation()+"/"+file.getName());
				  if(wf.getSharingMember().size()>0)
				  {
					  System.out.println(wf.getSharingMember().get(0));
				  }
			// }
		  }
		  else
		  {
			   WebFolder wf= (WebFolder)file;
			   //if(wf.getOwner().equals(id))
			  // {
				   System.out.println("���="+wf.getLocation()+"/"+wf.getName());
				   if(wf.getSharingMember().size()>0)
				   {
						System.out.println(wf.getSharingMember().get(0));
				   }
			  // }
			   for(int i=0;i<wf.getSubList().size();i++)
			   {
			   	  print(id,wf.getSubList().get(i));
			   }
		  }
     }
}
