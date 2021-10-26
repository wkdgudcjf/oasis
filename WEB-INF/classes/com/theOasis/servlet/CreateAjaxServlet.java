package com.theOasis.servlet;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import com.theOasis.controller.WebHardController;

/**
 * Servlet implementation class CreateAjaxServlet
 */
public class CreateAjaxServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final String subpath = "C:\\À¥ÇÏµå\\";
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		WebHardController wc = WebHardController.getInstance();
		String newname = request.getParameter("newname");
		String location = request.getParameter("location");
		String id = request.getParameter("id");
		if(newname.contains(".")||newname.contains("/"))
		{
			response.getWriter().print("error");
		}
		else
		{
			StringTokenizer stk = new StringTokenizer(location,"/");
			if(stk.nextToken().equals("root"))
			{
				String fullPath=subpath+"\\"+id+"\\"+newname;
				File fl=new File(fullPath);
				fl.mkdirs();
				response.getWriter().print(wc.createFolder(id,stk.nextToken(), newname));
			}
			else
			{
				String fullPath=subpath+"\\"+parseLocation(location)+"\\"+newname;
				File fl=new File(fullPath);
				fl.mkdirs();
				response.getWriter().print(wc.createFolder(id,location, newname));
			}
		}
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
