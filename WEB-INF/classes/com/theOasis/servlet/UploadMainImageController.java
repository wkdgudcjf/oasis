package com.theOasis.servlet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.theOasis.controller.MemberController;

import com.theOasis.member.*;

@Controller
public class UploadMainImageController {
	private final String subpath="image\\";
	@RequestMapping(value="imageupload.do" , method=RequestMethod.POST)
	public ModelAndView submitReport(MultipartHttpServletRequest request)
	{
		FileOutputStream fos=null;
		String id = request.getParameter("id");
		MultipartFile file = request.getFile("mainimage");
		if(file.isEmpty())
		{
			ModelAndView m = new ModelAndView();
			m.setViewName("mypage");
			return m;
		}
		try {
			//StringTokenizer stk = new StringTokenizer(file.getOriginalFilename(),".");
			//String name = stk.nextToken();
			//String h = stk.nextToken();

			File lo=new File(subpath+id);
			lo.mkdirs();
			
			//System.out.println(file.getSize());
			//System.out.println(subpath+file.getOriginalFilename());
			File fl=new File(subpath+id+"\\"+file.getOriginalFilename());
		
			//System.out.println(subpath+id+"/"+file.getOriginalFilename());
			fl.createNewFile();
		   	byte fileData[] = file.getBytes();
			fos = new FileOutputStream(fl);
			fos.write(fileData); 
			
			TheOasisMember tm = MemberController.getInstance().search(id);
			tm.getProfile().setMainImage(file.getOriginalFilename());
			
			ModelAndView m = new ModelAndView();
			m.setViewName("redirect:login.html");
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
