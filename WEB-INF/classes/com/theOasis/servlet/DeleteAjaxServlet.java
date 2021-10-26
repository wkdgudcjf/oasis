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

import com.theOasis.controller.WebHardController;

/**
 * Servlet implementation class DeleteAjaxServlet
 */
public class DeleteAjaxServlet extends HttpServlet {
	private final String subpath = "C:\\À¥ÇÏµå\\";
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		WebHardController wc = WebHardController.getInstance();
		String name = request.getParameter("name");
		String location = request.getParameter("location");
		String id = request.getParameter("id");
		
		//System.out.println(location);
		//System.out.println(name);
		String fullPath=subpath+"\\"+parseLocation(location)+"\\"+name;
		File fl=new File(fullPath);
		fl.delete();
		response.getWriter().print(wc.delete(id,location, name));
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
