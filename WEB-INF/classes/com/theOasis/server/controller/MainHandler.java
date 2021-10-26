package com.theOasis.server.controller;

import java.io.IOException;
import java.util.GregorianCalendar;
import java.util.Iterator;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import com.theOasis.member.Member;
import com.theOasis.member.MemberInfo;
import com.theOasis.text.Readable;
import com.theOasis.text.bbs.Agree;
import com.theOasis.text.bbs.BBSItem;
import com.theOasis.controller.BBSController;
import com.theOasis.controller.MemberController;

public class MainHandler extends SimpleTagSupport{
	private String id;
	private int page;
	public void setId(String id)
	{
		this.id = id;
	}
	public void setPage(int page)
	{
		this.page = page;
	}
	@Override
	public void doTag() throws JspException, IOException {
		if(BBSController.getInstance().getRecentBBSItem(id)==null)
			return ;
		Iterator<Readable> iter = BBSController.getInstance().getRecentBBSItem(id).iterator();
		while(iter.hasNext())
		{
			BBSItem item = (BBSItem) iter.next();
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
			getJspContext().setAttribute("owner", BBSController.getInstance().getManager().findOwner(item.getNumber()));
			getJspContext().setAttribute("good", good);
			getJspContext().setAttribute("bad", bad);
			getJspBody().invoke(null);
		}
	}
}
