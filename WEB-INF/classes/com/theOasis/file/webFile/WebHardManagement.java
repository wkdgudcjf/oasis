package com.theOasis.file.webFile;

import java.util.*;

import com.theOasis.file.File;
import com.theOasis.file.RecycleBin;

/**
 * 웹하드를 관리해주는 클래스
 * 웹하드에 대한 모든 요청을 받고 처리하는 클래스이다.
 * 폴더또는 파일을 삭제할수 있으며 복원도 할 수 있다.
 * 특정 위치의 폴더에 파일을 등록할 수 있고,
 * 폴더를 생성할 수 있다.
 * 파일이나 폴더명을 변경도 가능하다. 회원마다 루트폴더가 하나씩 주어지며,
 * 휴지통도 하나씩 주어진다.
 * 트리형태로 파일을 관리한다. 
 * 
 * @author JHC
 *
 */
public class WebHardManagement
{
	/**
	 * 사용자마다 하나씩 제공해 줄 수 있는 맵형태의 필드
	 * 아이디를 제공하면 그 아이디의 웹폴더를 리턴한다.
	 */
	private HashMap<String,File> webFolderList;
	/**
	 * 기본 생성자
	 */
	public WebHardManagement()
	{
		this.webFolderList= new HashMap<String,File>();
	}
	/**
	 * 생성자 오버로딩
	 * @param webFolder 설정할 맵객체
	 */
	public WebHardManagement(HashMap<String,File> webFolderList)
	{
		this.webFolderList=webFolderList;
	}
	/**
	 * 내부사용 메소드로 요청이 온 디렉토리를 토큰하여 디렉토리를 구분한다.
	 * 모든 메소드에서 사용된다.
	 * 제공받은 위치를 / 단위로 파싱한다.
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
	 * 개인 웹하드의 루트디렉토리를 리턴한다.
	 * 루트폴더에 해당한다.
	 * @param 요청자
	 * @return 개인의 웹하드
	 */
	public File search(String id)
	{
		//System.out.println(id+"로 파일을 검색할려는군.."+webFolderList.get(id).getName());
		return webFolderList.get(id);
	}
	/**
	 * 개인웹하드에 특정디렉토리 밑에있는 파일을 리턴한다.
	 * 내부적으로 getHighLocationFile 메소드를 사용하여 특정디렉토리를 리턴받아
	 * 파일을 찾아 리턴한다.
	 * @param id 요청자
	 * @param location 위치
	 * @param name 파일명
	 * @return 파일
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
	 * 요청받은 경로의 파일을 리턴시켜준다.
	 * 내부적으로 parsLocation을 사용하며,
	 * 
	 *  
	 * @param id 요청id
	 * @param location 경로
	 * @return 경로파일
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
	 * 개인 웹하드를 등록한다
	 * 회원가입과 동시에 일어난다.
	 * @param id 요청자
	 * @return 등록여부
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
		//System.out.println(id+"를등록햇지"+folder.getName()+"이란폴더로..");
		return true;
	}
	/**
	 * 개인 웹하드의 루트폴더에 파일을 등록한다
	 * 내부적으로 getHighLocationFile 메소드를 사용하여 특정디렉토리의 리스트를 반환받아
	 * 파일을 등록한다.
	 * @param id 요청자
	 * @param file 등록할 파일
	 * @return 등록여부
	 */
	public boolean enroll(String id,File file)
	{
	  /*if(file==null||(file.getName().equals(id+"님의휴지통")&&file.getLocation().equals(id)))
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
	 * 개인 웹하드의 특정 디렉토리에 파일을 등록한다
	 * 내부적으로 getHighLocationFile와 parseLocation 메소드를 사용하여 특정디렉토리의 리스트를 반환받아
	 * 파일을 등록한다.
	 * @param id 요청자
	 * @param location 위치
	 * @param name 파일명
	 * @return 등록여부
	 */
	public boolean enroll(String id,String location,String name,int size)
	{
      /*if(location.equals(id)&&name.equals(id+"님의휴지통"))
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
	 * 개인 웹하드의 루트폴더에 파일을 삭제한다
	 * 내부적으로 getHighLocationFile 메소드를 사용하여 특정디렉토리의 리스트를 반환받아
	 * 파일을 찾아 삭제한다.
	 * 공유된 파일일 경우 요청자와 파일 주인과 같지 않을시 삭제가 되지 않는다.
	 * @param id 요청자
	 * @param file 삭제할 파일
	 * @return 등록여부
	 */
	public boolean remove(String id,File file)
	{
		/*if(file.getName().equals(id+"님의휴지통")&&file.getLocation().equals(id))
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
	 * 개인 웹하드의 특정 디렉토리에 파일을 삭제한다
	 * 내부적으로 getHighLocationFile 메소드를 사용하여 특정디렉토리의 리스트를 반환받아
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
		if(name.equals(id+"님의휴지통")&&location.equals(id))
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
	 * 개인웹하드에 특정 디렉토리의 파일이나 폴더의 이름을 바꾼다.
	 * 내부적으로 getHighLocationFile 메소드를 사용하여 특정디렉토리의 리스트를 반환받아
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
		if(location==null)
		{
			return false;
		}
		/*if((oldname.equals(id+"님의휴지통")||newname.equals(id+"님의휴지통"))&&location.equals(id))
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
	 * 개인 웹하드의 휴지통에서 파일을 영구삭제한다.
	 * 공유된 파일일 경우 요청자와 파일 주인과 같지 않을시 삭제가 되지 않는다.
	 * @param id 요청자
	 * @param file 영구삭제할 파일
	 * @return 영구삭제여부
	 */
	/*public boolean permanentRemove(String id,File file)
	{
		if(file.getLocation().equals(id+"님의휴지통"))
		{
		WebFolder rec = (WebFolder)search(id);
		RecycleBin rb = (RecycleBin)(rec.getSubList().get(0));
		rb.remove(file);
		return true;
		}
		return false;
	}*/
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
		if(location.equals(id+"님의휴지통"))
		{
		WebFolder rec = (WebFolder)search(id);
		RecycleBin rb = (RecycleBin)(rec.getSubList().get(0));
		rb.remove(name);
		return true;
		}
		return false;
	}*/
	/**
	 * 개인 웹하드의 휴지통에서 파일을 복원한다.
	 * 내부적으로 getHighLocationFile 메소드를 사용하여 파일의 경로를 가지고 리스트를 반환받아
	 * 파일을 등록시켜준다.
	 * 복원시 루트폴더에 등록된다.
	 * @param id 요청자
	 * @param file 복원할 파일
	 * @return 복원 여부
	 */
/*	public boolean restore(String id,File file)
	{
		if(file.getLocation().equals(id+"님의휴지통"))
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
	 * 개인 웹하드의 휴지통에서 파일을 복원한다.
	 * 내부적으로 getHighLocationFile 메소드를 사용하여 파일의 경로를 가지고 리스트를 반환받아
	 * 파일을 등록시켜준다.
	 * 복원시 상위 디렉토리가 삭제되었을경우 생성시켜준다.
	 * @param id 요청자
	 * @param location 위치
	 * @param name 파일명
	 * @return 복원 여부
	 */
	/*public boolean restore(String id,String location,String name)
	{
		if(location.equals(id+"님의휴지통"))
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
	 * 공유여부를 설정한다.
	 * 폴더를 설정할경우 하위목록들도 모두 설정된다.
	 * 공유된 파일일 경우 요청자와 파일 주인과 같지 않을시 설정이 되지 않는다.
	 * @param id 요청자
	 * @param file 설정할 파일
	 * @param bool 설정할 여부
	 * @return 설정여부
	 */
	public boolean setIsSharing(String id,File file,boolean bool)
	{
	/*	if(file==null||(file.getName().equals(id+"님의휴지통")&&file.getLocation().equals(id)))
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
		/*if(name.equals(id+"님의휴지통")&&location.equals(id))
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
	 * 공유된 파일이나 폴더에 공유할 멤버를 설정한다.
	 * 폴더를 설정할경우 하위목록도 모두 설정된다.
	 * 공유된 멤버는 공유된 파일이나 폴더를 자신의 루트폴더에 생성된다.
	 * 공유된 파일일 경우 요청자와 파일 주인과 같지 않을시 설정이 되지 않는다.
	 * @param id 요청자
	 * @param location 위치
	 * @param name 파일명
	 * @param sharingMember 공유할 멤버들
	 * @return 공유멤버설정여부
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
		
		
		manager.enroll(id1, id1, "장형철", 0);
		manager.enroll(id1, id1+"/장형철","형철파일.jpg",23);
		manager.enroll(id1, id1+"/장형철","형철파일1.jpg",13);
		manager.enroll(id1, id1+"/장형철","형철폴더",0);
		manager.enroll(id1, id1+"/장형철/형철폴더","형철파일3.ppt",7);
		manager.enroll(id1, id1,"장형철2",0);
		manager.enroll(id1, id1+"/장형철2","형철폴더2",0);
		
		manager.enroll(id2, id2, "김지수", 0);
		manager.enroll(id2, id2+"/김지수","지수파일.jpg",23);
		manager.enroll(id2, id2+"/김지수","지수파일1.jpg",13);
		manager.enroll(id2, id2+"/김지수","지수폴더",0);
		manager.enroll(id2, id2+"/김지수/지수폴더","지수파일3.ppt",7);
		manager.enroll(id2, id2,"김지수2",0);
		manager.enroll(id2, id2+"/김지수2","지수폴더2",0);
		
		manager.enroll(id3, id3, "김예원", 0);
		manager.enroll(id3, id3+"/김예원","예원파일.jpg",23);
		manager.enroll(id3, id3+"/김예원","예원파일1.jpg",13);
		manager.enroll(id3, id3+"/김예원","예원폴더",0);
		manager.enroll(id3, id3+"/김예원/예원폴더","예원파일3.ppt",7);
		manager.enroll(id3, id3,"김예원2",0);
		manager.enroll(id3, id3+"/김예원2","예원폴더2",0);

		manager.addSharingUser(id1, id1, "장형철", id3);
		manager.enroll(id3, manager.search(id1, id1, "장형철"));
		manager.enroll(id3, id3,"김예원3",0);
		manager.enroll(id3, id3+"/김예원3","예원폴더3",0);
		
	    WebFolder file1 = (WebFolder)manager.search(id1);
	    WebFolder file2 = (WebFolder)manager.search(id2);
	    WebFolder file3 = (WebFolder)manager.search(id3);
	    
	    System.out.println(file1.getSubList().size());
	    System.out.println(file2.getSubList().size());
	    System.out.println(file3.getSubList().size());
	    
	    print(id1,file1);
	  //  printShare(id1,file1);
	    System.out.println("형철 끝");
	    print(id2,file2);
	 //   printShare(id2,file2);
	    System.out.println("지수 끝");
	    print(id3,file3);
	  //  printShare(id3,file3);
	    System.out.println("예원 끝");
	}
/*	public static void printShare(String id,File file)
	{
		 if(isFile(file.getName()))
		 {
			 WebFile wf = (WebFile)file;
			 if(!(wf.getOwner().equals(id)))
			 {
				 System.out.println("경로="+file.getLocation()+"/"+file.getName());
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
				   System.out.println("경로="+wf.getLocation()+"/"+wf.getName());
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
				  System.out.println("경로="+file.getLocation()+"/"+file.getName());
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
				   System.out.println("경로="+wf.getLocation()+"/"+wf.getName());
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
