package com.theOasis.server.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.theOasis.controller.MemberController;
import com.theOasis.controller.WebHardController;
import com.theOasis.profileIO.ProfileIO;

public class JoinServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public JoinServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("idf");
		String checkid = request.getParameter("command");
		//System.out.println("JoinServlet : 38"+checkid);
		String password = request.getParameter("passwordf");
		String name = request.getParameter("namef");
		String birthday = request.getParameter("birthday1") + "-" + request.getParameter("birthday2") + "-" + request.getParameter("birthday3");
		String phoneNumber = request.getParameter("phone1") + "-" + request.getParameter("phone2") + "-" + request.getParameter("phone3");
		String question = request.getParameter("question");
		String answer = request.getParameter("answer");
		if(checkid!=null)
		{
			//System.out.println("JoinServlet :47 " + MemberController.getInstance().check(id));
			if(MemberController.getInstance().check(checkid)){
				response.getWriter().print("fail");
			}
			else
			{
				//System.out.println("∫Ò¡∏¿Á");
				response.getWriter().print("true");
			}
		}
		else
		{
			if(!MemberController.getInstance().check(id)){
				MemberController.getInstance().add(new String[]{id,password,name,birthday,phoneNumber,question,answer});
				WebHardController.getInstance().enroll(id);
			}
			
			response.sendRedirect("login.html");
		}
		//RequestDispatcher view = request.getRequestDispatcher("login");
		//view.forward(request, response);
		
		// TODO Auto-generated method stub
	}

}
