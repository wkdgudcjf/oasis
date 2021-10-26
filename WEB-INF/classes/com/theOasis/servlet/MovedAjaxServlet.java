package com.theOasis.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.theOasis.controller.WebHardController;

/**
 * Servlet implementation class MovedAjaxServlet
 */
public class MovedAjaxServlet extends HttpServlet {
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 WebHardController wc = WebHardController.getInstance();
		 String name = request.getParameter("name");
		 String location = request.getParameter("location");
		 String id = request.getParameter("id");
		 response.getWriter().print(wc.fdsearch(id,location, name));
	}

}
