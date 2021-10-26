package com.theOasis.server.controller;

import java.io.IOException;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import com.theOasis.controller.BBSController;
import com.theOasis.controller.MemberController;
import com.theOasis.member.Member;
import com.theOasis.member.MemberInfo;
import com.theOasis.text.*;
import com.theOasis.text.Readable;
import com.theOasis.text.bbs.Agree;
import com.theOasis.text.bbs.BBSItem;
public class PageHandler extends SimpleTagSupport{
	private int page;
	private String id;
	
	
	public void setPage(int page) {
		this.page = page;
	}


	public void setId(String id) {
		this.id = id;
	}


	@Override
	public void doTag() throws JspException, IOException {
		List<Readable> list = BBSController.getInstance().getMyRecentBBSItem(id);
		if(list==null)
			return ;
		Iterator<Readable> i = list.iterator();
		while(i.hasNext())
		{
			BBSItem item = (BBSItem) i.next();
			getJspContext().setAttribute("writer", ((Member)MemberController.getInstance().getManager().search(MemberInfo.ID, item.getWriter()).get(0)).getName());
			getJspContext().setAttribute("item", item);
			getJspContext().setAttribute("content", item.getContent().replaceAll("\n", "<br>"));
			int good=0;
			int bad=0;
			for(Agree agree: item.getAgreeList())
			{
				if(agree.getIsGood())
					good++;
				else
					bad++;
			}
			GregorianCalendar time = item.getTime();
			getJspContext().setAttribute("date", time.get(GregorianCalendar.YEAR)+"³â "+(time.get(GregorianCalendar.MONTH)+1)+"¿ù "+time.get(GregorianCalendar.DAY_OF_MONTH)+"ÀÏ");
					
			getJspContext().setAttribute("good", good);
			getJspContext().setAttribute("bad", bad);
			getJspBody().invoke(null);
		}
	}
}
