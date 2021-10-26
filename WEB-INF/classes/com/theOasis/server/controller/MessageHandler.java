package com.theOasis.server.controller;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import com.theOasis.text.Readable;
import com.theOasis.text.message.Message;
import com.theOasis.controller.MemberController;
import com.theOasis.controller.MessageController;

public class MessageHandler extends SimpleTagSupport{
	private String id;

	public void setId(String id) {
		this.id = id;
	}

	@Override
	public void doTag() throws JspException, IOException {
		List<List<Readable>> all = MessageController.getInstance().getRecentMessage(id);
		if(all==null)
			return ;
		Iterator<List<Readable>> iter = all.iterator();
		while(iter.hasNext())
		{
			String name;
			List<Readable> list = iter.next();
			Message m = (Message)list.get(0);
			if(!m.getReceiver().equals(id))
			{
				name = MemberController.getInstance().search(m.getReceiver()).getName();
				getJspContext().setAttribute("friendId", m.getReceiver());
			}
			else
			{
				name= MemberController.getInstance().search(m.getWriter()).getName();
				getJspContext().setAttribute("friendId", m.getWriter());
			}
				
			
			getJspContext().setAttribute("name", name);
			getJspContext().setAttribute("list", list);
			getJspBody().invoke(null);
		}
	}
}
