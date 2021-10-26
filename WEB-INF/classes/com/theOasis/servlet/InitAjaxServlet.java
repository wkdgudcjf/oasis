package com.theOasis.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.theOasis.controller.WebHardController;

/**
 * Servlet implementation class InitAjaxServlet
 */
public class InitAjaxServlet extends HttpServlet {
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		WebHardController wc = WebHardController.getInstance();
		//System.out.println("에이젝스!");
		String id = request.getParameter("id");
		response.getWriter().print(wc.search(id));

	}

}
