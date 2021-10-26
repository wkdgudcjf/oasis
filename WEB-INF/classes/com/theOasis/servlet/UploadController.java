package com.theOasis.servlet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.theOasis.controller.WebHardController;

@Controller
public class UploadController 
{
	private final String subpath = "C:\\À¥ÇÏµå\\";
	@RequestMapping(value="upload.do" , method=RequestMethod.POST)
	public ModelAndView submitReport(MultipartHttpServletRequest request)
	{
		FileOutputStream fos=null;
		String path = request.getParameter("path");
		String id = request.getParameter("id");
		MultipartFile file = request.getFile("sub");
		if(file.isEmpty())
		{
			ModelAndView m = new ModelAndView();
			m.setViewName("webhard");
			return m;
		}
		try {
			String fullPath="";
			if(path.equals("root"+"/"+id))
			{
				File file1 = new File(subpath+id);
				file1.mkdirs();
				fullPath+=subpath+id+"\\"+file.getOriginalFilename();
			}
			else{
				fullPath=subpath+"\\"+parseLocation(path)+"\\"+file.getOriginalFilename();
			}
			//System.out.println(fullPath);
			File fl=new File(fullPath);
			fl.createNewFile();
		   	byte fileData[] = file.getBytes();
			fos = new FileOutputStream(fl);
			fos.write(fileData); 
			WebHardController.getInstance().enroll(id, path, file.getOriginalFilename(), (int)file.getSize());
			ModelAndView m = new ModelAndView();
			m.setViewName("webhard");
			return m;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	private String parseLocation(String location)
	{
		List<String> str = new LinkedList<String>();
		StringTokenizer stk = new StringTokenizer(location,"/");
		while(stk.hasMoreTokens())
		{
			str.add(stk.nextToken());
		}
		String arr="";
		for(int i=0;i<str.size();i++)
		{
			if(i==str.size()-1)
			{
				arr+=str.get(i);
			}
			else
			{
				arr+=str.get(i)+"\\";
			}
		}
		return arr;
	}
	
}
