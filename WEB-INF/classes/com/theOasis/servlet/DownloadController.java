package com.theOasis.servlet;

import java.io.*;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

import net.sf.jazzlib.ZipEntry;
import net.sf.jazzlib.ZipOutputStream;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class DownloadController implements ApplicationContextAware
{       
	private static final int COMPRESSION_LEVEL = 8;
	private static final int BUFFER_SIZE = 64*1024;
	private static final char FS = File.separatorChar;
	private WebApplicationContext context = null;     
	private final String subpath = "C:\\���ϵ�\\";
	@RequestMapping(value="download.do" , method=RequestMethod.GET)     
	public ModelAndView download(@RequestParam("id") String id,
			@RequestParam("path") String path,
			@RequestParam("filename") String filename) throws IOException
	{                   
		String dpath="";
		String did="";
		String dfilename="";
		try {
			dpath = new String(path.getBytes("ISO-8859-1"), "UTF-8");
			did = new String(id.getBytes("ISO-8859-1"), "UTF-8");
			dfilename = new String(filename.getBytes("ISO-8859-1"), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String fullPath=subpath+parseLocation(dpath)+dfilename; 
		File file=null;
		
		if(!(dfilename.contains(".")))
		{
			int cnt;
	        byte[] buffer = new byte[BUFFER_SIZE];
	        FileInputStream finput = null;
	        FileOutputStream foutput;
	        
	        // ������ ������ ���� ��ü�� �����Ѵ�.
	        File rfile = new File(fullPath);
	        String filePath = rfile.getAbsolutePath();

	        // ���� �ȿ� �ִ� ���ϵ��� ���� �迭 ��ü�� �����´�.
	        File[] fileArray = rfile.listFiles();
	             
	        // Zip ������ �����.
	        file = new File("d:\\"+dfilename+".zip");
	        // Zip ���� ��ü�� ��� ��Ʈ���� �ִ´�.
	        foutput = new FileOutputStream(file);

	        // ����� ��Ʈ���� �������� �ִ´�.
	        ZipOutputStream zoutput = new ZipOutputStream((OutputStream)foutput);

	        ZipEntry zentry = null;

	        try {
	            for (int i=0; i < fileArray.length; i++) 
	            {
	                // ������ ���� �迭 �� �ϳ��� ������ �Է� ��Ʈ���� �ִ´�.
	                finput = new FileInputStream(fileArray[i]);
	               
	                zentry = new ZipEntry(fileArray[i].getName());

	                zoutput.putNextEntry(zentry);
	                
	              //   ���� ������ ���ϴ°�, 9�� ���� ���� �����.
	              //   �� ��� �ӵ��� �� ����. ����Ʈ�� 8.
	                
	                zoutput.setLevel(COMPRESSION_LEVEL);

	                cnt = 0;
	                while ((cnt = finput.read(buffer)) != -1)
	                {
	                    zoutput.write(buffer, 0, cnt);
	                }

	                finput.close();
	                zoutput.closeEntry();
	            }
	            zoutput.close();
	            foutput.close();
	        }
	        catch (Exception e)
	        {
	             e.printStackTrace();
	        }
	        finally 
	        {
	            if (finput != null) 
	            {
	            	
	                finput.close();
	            }
	            if (zoutput != null) 
	            {
	                zoutput.close();
	            }
	            if (foutput != null) 
	            {
	                foutput.close();
	            }
	        }
		}
		else
		{
			file = new File(fullPath); 
		}
		//System.out.println(fullPath);
		//System.out.println(file.length());
		
		return new ModelAndView("download", "downloadFile", file);
	}       
	private String parseLocation(String location)
	{
		List<String> str = new LinkedList<String>();
		StringTokenizer stk = new StringTokenizer(location,"/");
		while(stk.hasMoreTokens())
		{
			str.add(stk.nextToken());
		}
		String arr ="";
		for(int i=0;i<str.size();i++)
		{
			if(str.get(i).equals("root"))
			   {
			    continue;
			   }
			arr+=str.get(i)+"\\";
		}
		return arr;
	}
	@Override    
	public void setApplicationContext(ApplicationContext arg0)            
	throws BeansException 
	{         // TODO Auto-generated method stub  
		this.context = (WebApplicationContext)arg0;               
	}       
}


