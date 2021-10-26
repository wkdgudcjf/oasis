package com.theOasis.profileIO;

import java.io.*;
import java.util.*;

import com.theOasis.controller.MemberController;
import com.theOasis.member.*;

public class ProfileIO {
	private final static String subpath = "C:\\개인프로필\\";
	public static void importProfile() {
			ObjectInputStream in = null;
			List<Userable> ml = MemberController.getInstance().getManager().getMList().getMemberList();
			if(ml.size()==0)
			{
				return ;
			}
			try 
			{
				for(int i=0;i<ml.size();i++)
				{
					in = new ObjectInputStream(new FileInputStream(subpath+ml.get(i).getId()+"\\profile.dat"));
					((TheOasisMember)ml.get(i)).setProfile((Profile)in.readObject());
				}
				in.close();
			} 
			catch (Exception e) 
			{
				e.printStackTrace();
			}
		}
		public static void exportProfile() {
		
		List<Userable> ml = MemberController.getInstance().getManager().getMList().getMemberList();
		
		ObjectOutputStream out = null;
		try 
		{
			for(int i=0;i<ml.size();i++)
			{
				File file = new File("subpath"+ml.get(i).getId());
				file.mkdirs();
				out = new ObjectOutputStream(new FileOutputStream(subpath+ml.get(i).getId()+"\\profile.dat"));
				out.writeObject(((TheOasisMember)ml.get(i)).getProfile());
			}
			out.close();
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		}

	
		public static void exportProfile(TheOasisMember member) {
			
			ObjectOutputStream out = null;
			try 
			{
				File file = new File(subpath+member.getId());
				file.mkdirs();
				out = new ObjectOutputStream(new FileOutputStream(subpath+member.getId()+"\\profile.dat"));
				out.writeObject(member.getProfile());
				out.close();
			} 
			catch (Exception e) 
			{
				e.printStackTrace();
			}

		}
}
