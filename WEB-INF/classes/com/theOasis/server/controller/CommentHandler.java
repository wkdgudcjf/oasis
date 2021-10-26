package com.theOasis.server.controller;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import com.theOasis.controller.MemberController;
import com.theOasis.text.bbs.Comment;

public class CommentHandler extends SimpleTagSupport{
	private List<Readable> comments;
	
	
	public void setComments(List<Readable> comments) {
		this.comments = comments;
	}


	@Override
	public void doTag() throws JspException, IOException {
		Iterator<Readable> iter = comments.iterator();
		while(iter.hasNext())
		{
			Comment comment = (Comment)iter.next();
			String writer = MemberController.getInstance().search(comment.getWriter()).getName();
			getJspContext().setAttribute("comment", comment);
			getJspContext().setAttribute("name", writer);
			getJspBody().invoke(null);
		}
	}
}
