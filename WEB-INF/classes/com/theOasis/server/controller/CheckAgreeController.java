package com.theOasis.server.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.theOasis.controller.BBSController;
import com.theOasis.text.bbs.Agree;
import com.theOasis.text.bbs.BBSItem;

/**
 * Servlet implementation class CheckAgreeController
 */
public class CheckAgreeController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CheckAgreeController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// System.out.println("µé¾î¿Ó´ç!!");

		// System.out.println(request.getParameter("id")+
		// Integer.parseInt(request.getParameter("bbsNo"))+
		// request.getParameter("writer")+request.getParameter("good"));
		boolean isGood = false;
		if (request.getParameter("good").equals("o")) {
			isGood = true;
		}
		if (BBSController.getInstance().getManager().registerAgree(request.getParameter("id"),
						Integer.parseInt(request.getParameter("bbsNo")),isGood, request.getParameter("writer"))) {
			int good = 0;
			int bad = 0;
			for (Agree agree : ((BBSItem) BBSController.getInstance()
					.getManager()
					.search(Integer.parseInt(request.getParameter("bbsNo"))))
					.getAgreeList()) {
				if (agree.getIsGood())
					good++;
				else
					bad++;
			}
			if(isGood)
				response.getWriter().print(good);
			else
				response.getWriter().print(bad);
		} else
			response.getWriter().print("fail");
		// response.getWriter().flush();

	}

}
