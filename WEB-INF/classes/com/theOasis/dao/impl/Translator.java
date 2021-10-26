package com.theOasis.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.*;

import com.theOasis.controller.GroupController;
import com.theOasis.controller.LanguageBuddyController;
import com.theOasis.controller.MemberController;
import com.theOasis.file.File;
import com.theOasis.file.webFile.WebFile;
import com.theOasis.file.webFile.WebFolder;
import com.theOasis.file.webFile.WebHardManagement;
import com.theOasis.member.*;
import com.theOasis.member.languageBuddy.*;
import com.theOasis.text.TextList;
import com.theOasis.text.bbs.BBSManagement;
import com.theOasis.text.message.MessageManagement;
import com.theOasis.text.*;
import com.theOasis.text.bbs.*;
import com.theOasis.text.message.*;
import com.theOasis.text.Readable;

public class Translator {

	private class FriendTranslator implements Translatable {
		private GroupManagement groupM;
		private MemberManagement memberM;
		private LanguageBuddyManagement languageBuddyM;

		/*
		 * 여기서 처리해야되는 table은 Member_tb, Friend_tb, languageBuddy_tb,
		 * languageBuddyProfile_tb
		 */
		public FriendTranslator() {
			this(null, null, null);
		}

		public FriendTranslator(GroupManagement groupM,
				MemberManagement memberM, LanguageBuddyManagement languageBuddyM) {
			if (groupM != null)
				this.groupM = groupM;
			else {
				this.groupM = new GroupManagement();
			}

			if (memberM != null)
				this.memberM = memberM;
			else {
				this.memberM = new MemberManagement();
			}

			if (languageBuddyM != null)
				this.languageBuddyM = languageBuddyM;
			else {
				this.languageBuddyM = new LanguageBuddyManagement();
			}
		}

		public List<String[]> parseMember() {
			List<String[]> temp = new LinkedList<String[]>();
			MemberList mlist = memberM.getMList();
			List<Userable> list = mlist.getMemberList();
			Iterator<Userable> iterator = list.iterator();
			while (iterator.hasNext()) {
				TheOasisMember user = (TheOasisMember) iterator.next();
				String arr[] = {
						user.getId(),
						user.getName(),
						user.getPassword(),
						user.getBirthday().get(GregorianCalendar.YEAR)
								+ "."
								+ (user.getBirthday().get(
										GregorianCalendar.MONTH) + 1)
								+ "."
								+ user.getBirthday().get(
										GregorianCalendar.DAY_OF_MONTH),
						user.getPhoneNumber(), user.getQuestion(),
						user.getAnswer() };
				temp.add(arr.clone());

			}
			return temp;
		}

		public List<String[]> parseFriend() {
			HashMap<String, GroupList> groupList = groupM.getGroupList();
			List<String[]> temp = new LinkedList<String[]>();
			Iterator<String> iterator = groupList.keySet().iterator();
			while (iterator.hasNext()) {
				String key = iterator.next();
				GroupList my = groupList.get(key);
				Iterator<MemberList> myIterator = my.getGroupList().iterator(); // 내
																				// 그룹리스트
				while (myIterator.hasNext()) {
					MemberList friends = myIterator.next();
					for (Userable friend : friends.getMemberList()) {
						String arr[] = { key, friend.getId(),
								((Member) friend).getName(),
								((Group) friends).getGroupName() };
						temp.add(arr.clone());
					}
				}
			}
			return temp;
		}

		public List<String[]> parseLanguageBuddy() {
			HashMap<String, String> joinList = languageBuddyM.getJoinList();
			List<String[]> temp = new LinkedList<String[]>();
			Iterator<String> iterator = joinList.keySet().iterator();
			while (iterator.hasNext()) {
				String key = iterator.next();
				String langbu = joinList.get(key);
				String arr[] = { key, langbu };
				temp.add(arr.clone());
			}
			return temp;
		}

		public List<String[]> parseLanguageBuddyProfile() {
			List<String[]> temp = new LinkedList<String[]>();
			List<LanguageBuddy> langbuList = languageBuddyM
					.getLanguageBuddies().getList();
			Iterator<LanguageBuddy> iterator = langbuList.iterator();
			while (iterator.hasNext()) {
				LanguageBuddy lb = iterator.next();
				String id = lb.getMe().getId();
				String introduction = lb.getProfile().getIntroduction();
				String motherTongue = lb.getProfile().getMotherTongue();
				String wannaLearn = lb.getProfile().getWannaLearn();
				String arr[] = { id, introduction, motherTongue, wannaLearn };
				temp.add(arr.clone());
			}
			return temp;
		}

		public LanguageBuddyManagement parseLanguageBuddy(ResultSet lb,
				ResultSet lbprofile) throws SQLException {
			languageBuddyM.getJoinList().clear();
			languageBuddyM.getLanguageBuddies().clear();
			List<String[]> profileList = new LinkedList<String[]>();
			while (lbprofile.next()) {
				String id = lbprofile.getString(1);
				String introduction = lbprofile.getString(2);
				String motherTongue = lbprofile.getString(3);
				String wannalearn = lbprofile.getString(4);
				String arr[] = { id, introduction, motherTongue, wannalearn };
				profileList.add(arr);
				Userable user = memberM.getMList().get(MemberInfo.ID, id).get(0);
				LanguageBuddy languageBuddy = new LanguageBuddy(user, arr[1], arr[2], arr[3]);
				languageBuddyM.getLanguageBuddies().add(languageBuddy);
				LanguageBuddyController.getInstance().put(id);
				
			}

			while (lb.next()) {
				String id = lb.getString(1);
				String lbId = lb.getString(2);
				languageBuddyM.makeLb(memberM.search(MemberInfo.ID, id)
								.get(0), memberM.search(MemberInfo.ID, lbId)
								.get(0));
					
			}
			return languageBuddyM;
		}

		public GroupManagement parseGroup(ResultSet rs) throws SQLException {
			

			while (rs.next()) {
				String id = rs.getString(1);
				String friendId = rs.getString(2);
				String friendName = rs.getString(3);
				String groupName = rs.getString(4);
				if (groupM.getGroupList().containsKey(id)) {
					if (groupName != "친구") {
						if ((groupM.getGroupList().get(id).contains(groupName))) {
							groupM.searchGroup(id)
									.get(groupName)
									.add(memberM
											.search(MemberInfo.ID, friendId)
											.get(0));
							// 친구 아이디로 친구를 memberM에서 찾은 뒤 groupM의 회원 아이디의 그룹 안에
							// add한다.
						} else {
							groupM.translateGroup(memberM.search(MemberInfo.ID, id)
									.get(0), groupName);
							groupM.searchGroup(id, groupName).add(
									memberM.search(MemberInfo.ID, friendId)
											.get(0));
						}
					} else {
						groupM.addFriend(
								memberM.search(MemberInfo.ID, id).get(0),
								memberM.search(MemberInfo.ID, friendId).get(0)); // 그룹
																					// 이름이
																					// 친구라면
																					// 회원을
																					// 친구로
																					// 추가한다.(addFriend메소드
																					// 호출)
					}
				}
				
				else {
					groupM.put(id);
					if (groupName != "친구") {
						if ((groupM.getGroupList().get(id).contains(groupName))) {
							groupM.searchGroup(id)
									.get(groupName)
									.add(memberM
											.search(MemberInfo.ID, friendId)
											.get(0));
							// 친구 아이디로 친구를 memberM에서 찾은 뒤 groupM의 회원 아이디의 그룹 안에
							// add한다.
						} else {
							groupM.addGroup(memberM.search(MemberInfo.ID, id)
									.get(0), groupName);
							groupM.searchGroup(id, groupName).add(
									memberM.search(MemberInfo.ID, friendId)
											.get(0));
						}
					} else {
						groupM.addFriend(
								memberM.search(MemberInfo.ID, id).get(0),
								memberM.search(MemberInfo.ID, friendId).get(0)); // 그룹
																					// 이름이
																					// 친구라면
																					// 회원을
																					// 친구로
																					// 추가한다.(addFriend메소드
																					// 호출)
					}
					
				}
			}
			return groupM;
		}

		public MemberManagement parseMember(ResultSet rs) throws SQLException {
			memberM.getMList().clear();
			groupM.getGroupList().clear();
			groupM.getAllGroupList().clear();
			while (rs.next()) {
				String id = rs.getString("id");
				String name = rs.getString("name");
				String password = rs.getString("password");
				Date date = rs.getDate("birthday");

				StringTokenizer token = new StringTokenizer(date.toString(),
						"-");
				GregorianCalendar birthday = new GregorianCalendar(
						Integer.parseInt(token.nextToken()),
						Integer.parseInt(token.nextToken()) - 1,
						Integer.parseInt(token.nextToken()));

				String phoneNumber = rs.getString("phonenumber");
				String question = rs.getString("question");
				String answer = rs.getString("answer");

				Userable user = new TheOasisMember(id, password, name,
						birthday, phoneNumber, question, answer);
				memberM.getMList().add(user);
				
				groupM.put(id);
				GroupController.getInstance().putStandByFriend(id);
				GroupController.getInstance().putStandByGroup(id);
			}
			return memberM;
		}

		/*
		 * Getter/Setter
		 */
		public GroupManagement getGroupM() {
			return groupM;
		}

		public void setGroupM(GroupManagement groupM) {
			this.groupM = groupM;
		}

		public MemberManagement getMemberM() {
			return memberM;
		}

		public void setMemberM(MemberManagement memberM) {
			this.memberM = memberM;
		}

		public LanguageBuddyManagement getLanguageBuddyM() {
			return languageBuddyM;
		}

		public void setLanguageBuddyM(LanguageBuddyManagement languageBuddyM) {
			this.languageBuddyM = languageBuddyM;
		}

		@Override
		public String toString() {
			return "FriendTranslator [groupM=" + groupM + ", memberM="
					+ memberM + ", languageBuddyM=" + languageBuddyM + "]";
		}

	}
	private class TextTranslator implements Translatable {
		private BBSManagement bbsM;
		private MessageManagement messageM;

		/*
		 * 여기서 처리해야할 table : bbs_Tb, comment_tb, agree_tb message_tb
		 */
		public TextTranslator() {
			this(null, null);
		}

		public TextTranslator(BBSManagement bbsM, MessageManagement messageM) {
			if (bbsM != null)
				this.bbsM = bbsM;
			else
				this.bbsM = new BBSManagement();
			if (messageM != null)
				this.messageM = messageM;
			else
				this.messageM = new MessageManagement();
		}

		public List<String[]> parseBbs() {
			HashMap<String, TextList> map = bbsM.getBbsList();
			Iterator<String> iter = map.keySet().iterator();
			List<String[]> list = new LinkedList<String[]>();

			while (iter.hasNext()) {

				String id = iter.next();
				TextList t = map.get(id);
				for (Readable r : t.getList()) {
					BBSItem temp = (BBSItem) r;
					if (temp.getContent().equals(""))
						temp.setContent("  ");
					if (temp.getImage().equals(""))
						temp.setImage("no image");
					list.add(new String[] {
							temp.getNumber() + "",
							id,
							temp.getWriter(),
							temp.getPostTarget(),
							temp.getContent(),
							temp.getTime().get(GregorianCalendar.YEAR)
									+ "."
									+ (temp.getTime().get(
											GregorianCalendar.MONTH) + 1)
									+ "."
									+ temp.getTime().get(
											GregorianCalendar.DAY_OF_MONTH),
							temp.getImage() });
				}
			}
			return list;
		}

		public List<String[]> parseComment() {
			HashMap<String, TextList> map = bbsM.getBbsList();
			Iterator<String> iter = map.keySet().iterator();
			List<String[]> list = new LinkedList<String[]>();

			while (iter.hasNext()) {
				String id = iter.next();
				TextList t = map.get(id);
				for (Readable r : t.getList()) {
					BBSItem temp = (BBSItem) r;
					for (int i = 0; i < temp.getComments().size(); i++) {
						Comment com = (Comment) temp.getComment(i);
						list.add(new String[] {
								i + "",
								com.getWriter(),
								temp.getNumber() + "",
								com.getContent(),
								com.getTime().get(GregorianCalendar.YEAR)
										+ "."
										+ (com.getTime().get(
												GregorianCalendar.MONTH) + 1)
										+ "."
										+ com.getTime().get(
												GregorianCalendar.DAY_OF_MONTH) });
					}
				}
			}
			return list;
		}

		public List<String[]> parseAgree() {
			HashMap<String, TextList> map = bbsM.getBbsList();
			Iterator<String> iter = map.keySet().iterator();
			List<String[]> list = new LinkedList<String[]>();

			while (iter.hasNext()) {
				String id = iter.next();
				TextList t = map.get(id);
				for (Readable r : t.getList()) {
					BBSItem temp = (BBSItem) r;
					for (Agree agree : temp.getAgreeList()) {
						if (agree.getIsGood())
							list.add(new String[] {
									agree.getBbsNumber() + "_"
											+ agree.getWriter(), "o" });
						else
							list.add(new String[] {
									agree.getBbsNumber() + "_"
											+ agree.getWriter(), "x" });
					}
				}
			}
			return list;
		}

		public List<String[]> parseMessage() {
			List<String[]> list = new LinkedList<String[]>();
			HashMap<String, TextList[]> map = messageM.getMessageBox();
			Iterator<String> iter = map.keySet().iterator();
			while (iter.hasNext()) {
				TextList tList = map.get(iter.next())[0];
				for (Readable r : tList.getList()) {
					Message message = (Message) r;
					if (message.getIsCheck())
						list.add(new String[] {
								message.getWriter(),
								message.getReceiver(),
								message.getContent(),
								"to_timestamp('"
										+ new Timestamp(message.getTime()
												.getTimeInMillis()).toString()
										+ "','yyyy-mm-dd hh24:mi:ssxff')", "o" });
					else
						list.add(new String[] {
								message.getWriter(),
								message.getReceiver(),
								message.getContent(),
								"to_timestamp('"
										+ new Timestamp(message.getTime()
												.getTimeInMillis()).toString()
										+ "','yyyy-mm-dd hh24:mi:ssxff')", "x" });
				}

			}
			return list;
		}

		public BBSManagement parseBbs(ResultSet bbs, ResultSet comment,
				ResultSet agree) throws SQLException {

			bbsM.getBbsList().clear();
			while (bbs.next()) {
				int bbsNo = bbs.getInt("bbs_no");
				String id = bbs.getString("id");
				String writer = bbs.getString("writer");
				String postTarget = bbs.getString("POSTTARGET");
				String content = bbs.getClob("content").getSubString(1,
						(int) bbs.getClob("content").length());
				Date date = bbs.getDate("writtendate");
				StringTokenizer token = new StringTokenizer(date.toString(),
						"-");
				GregorianCalendar writtendate = new GregorianCalendar(
						Integer.parseInt(token.nextToken()),
						Integer.parseInt(token.nextToken()) - 1,
						Integer.parseInt(token.nextToken()));
				String image = bbs.getString("location");

				BBSItem item = new BBSItem(content, writer, postTarget, image);
				item.setTime(writtendate);
				item.setNumber(bbsNo);

				bbsM.register(id, item);
			}

			while (comment.next()) {
				int comment_no = comment.getInt(1);
				String writer = comment.getString(2);
				int bbsNo = comment.getInt(3);
				String content = comment.getString(4);

				Date date = comment.getDate(5);
				StringTokenizer token = new StringTokenizer(date.toString(),
						"-");
				GregorianCalendar writtendate = new GregorianCalendar(
						Integer.parseInt(token.nextToken()),
						Integer.parseInt(token.nextToken()) - 1,
						Integer.parseInt(token.nextToken()));

				Comment com = new Comment(content, writer, comment_no, bbsNo);
				com.setTime(writtendate);

				bbsM.registerComment(bbsM.search(bbsNo).getWriter(), bbsNo, com);
			}
			while (agree.next()) {
				String bbsNoWriter = agree.getString(1);
				StringTokenizer token = new StringTokenizer(bbsNoWriter, "_");
				int bbsNo = Integer.parseInt(token.nextToken());
				String writer = token.nextToken();
				boolean isgood = false;
				if (agree.getString(2).equals("o"))
					isgood = true;

				bbsM.registerAgree(bbsM.search(bbsNo).getWriter(), bbsNo,
						isgood, writer);
			}

			return bbsM;
		}

		public MessageManagement parseMessage(ResultSet message)
				throws SQLException {
			messageM.getMessageBox().clear();

			while (message.next()) {
				String sender = message.getString(1);
				String receiver = message.getString(2);
				String content = message.getClob("content").getSubString(1,
						(int) message.getClob("content").length());

				Timestamp stamp = message.getTimestamp(4);
				// GregorianCalendar writtendate = new GregorianCalendar();
				GregorianCalendar writtendate = new GregorianCalendar(
						stamp.getYear() + 1900, stamp.getMonth(),
						stamp.getDate(), stamp.getHours(), stamp.getMinutes(),
						stamp.getSeconds());
				// writtendate.setTimeInMillis(stamp.getTime());
				/*
				 * Date date = message.getDate(4); StringTokenizer token = new
				 * StringTokenizer(date.toString(), "-"); GregorianCalendar
				 * writtendate = new GregorianCalendar(
				 * Integer.parseInt(token.nextToken()),
				 * Integer.parseInt(token.nextToken()) - 1,
				 * Integer.parseInt(token.nextToken()));
				 */
				String check = message.getString(5);
				Message temp = null;
				if (check.equals("o"))
					temp = new Message(content, sender, true, receiver);
				else
					temp = new Message(content, sender, false, receiver);

				temp.setTime(writtendate);
				messageM.send(temp);

			}
			return messageM;
		}

		/*
		 * getter/setter
		 */
		public BBSManagement getBbsM() {
			return bbsM;
		}

		public void setBbsM(BBSManagement bbsM) {
			this.bbsM = bbsM;
		}

		public MessageManagement getMessageM() {
			return messageM;
		}

		public void setMessageM(MessageManagement messageM) {
			this.messageM = messageM;
		}
	}

	private class WebhardTranslator implements Translatable {
		/*
		 * 여기서 처리할 table은 recyclebin_tb, webfile_tb, webhardsharing_tb
		 */
		private WebHardManagement whM;

		public WebhardTranslator() {
			this(null);
		}

		public WebhardTranslator(WebHardManagement whM) {
			if (whM != null)
				this.whM = whM;
			else {
				this.whM = new WebHardManagement();
			}
		}

		/*
		 * public List<String[]> parseRecycleBin() { HashMap<String, File> map =
		 * whM.getWebFolderList(); Iterator<String> iter =
		 * map.keySet().iterator(); List<String[]> list = new
		 * LinkedList<String[]>();
		 * 
		 * while (iter.hasNext()) { String id = iter.next(); WebFolder t =
		 * (WebFolder)map.get(id); RecycleBin rb =
		 * (RecycleBin)t.getSubList().get(0); for(File f : rb.getSubList()) {
		 * list.add(new
		 * String[]{id,f.getLocation()+"_"+f.getName(),f.getTime().get
		 * (GregorianCalendar.YEAR) + "." + (f.getTime().get(
		 * GregorianCalendar.MONTH) + 1) + "." + f.getTime().get(
		 * GregorianCalendar.DAY_OF_MONTH) }); } } return list; }
		 */

		public List<String[]> parseWebfile() {
			HashMap<String, File> map = whM.getWebFolderList();
			Iterator<String> iter = map.keySet().iterator();
			List<String[]> list = new LinkedList<String[]>();

			while (iter.hasNext()) {
				String id = iter.next();
				File t = (WebFolder) map.get(id);
				save(id, list, t);
			}
			return list;
		}

		private void save(String id, List<String[]> list, File t) {
			if (whM.isFile(t.getName())) {
				WebFile wf = (WebFile) t;
				// if(wf.getOwner().equals(id))
				// {
				list.add(new String[] {
						"file_no.nextval",
						wf.getLocation() + "_" + wf.getName(),
						wf.getOwner(),
						wf.getTime().get(GregorianCalendar.YEAR)
								+ "."
								+ (wf.getTime().get(GregorianCalendar.MONTH) + 1)
								+ "."
								+ wf.getTime().get(
										GregorianCalendar.DAY_OF_MONTH),
						wf.isShare() ? "o" : "x", "x", wf.getSize() + "" });
				// }
			} else {
				WebFolder wf = (WebFolder) t;
				list.add(new String[] {
						"file_no.nextval",
						wf.getLocation() + "_" + wf.getName(),
						wf.getOwner(),
						wf.getTime().get(GregorianCalendar.YEAR)
								+ "."
								+ (wf.getTime().get(GregorianCalendar.MONTH) + 1)
								+ "."
								+ wf.getTime().get(
										GregorianCalendar.DAY_OF_MONTH),
						wf.isShare() ? "o" : "x", "o", wf.getSize() + "" });
				for (int i = 0; i < wf.getSubList().size(); i++) {
					save(id, list, wf.getSubList().get(i));
				}
			}

		}

		/*
		 * public List<String[]> parseWebhardSharing() { HashMap<String, File>
		 * map = whM.getWebFolderList(); Iterator<String> iter =
		 * map.keySet().iterator(); List<String[]> list = new
		 * LinkedList<String[]>();
		 * 
		 * while (iter.hasNext()) { String id = iter.next(); File t =
		 * (WebFolder)map.get(id); saveSharing(id,list,t); } return list; }
		 * private void saveSharing(String id,List<String[]> list,File t) {
		 * if(whM.isFile(t.getName())) { WebFile wf = (WebFile)t;
		 * if(wf.isShare()) { if(wf.getOwner().equals(id)) { for(int
		 * i=0;i<wf.getSharingMember().size();i++) { String[] str = new
		 * String[]{
		 * wf.getLocation()+"_"+wf.getName(),id,wf.getSharingMember().get(i)};
		 * if(list.contains(str)) { ; } else { list.add(str); } } } } } else {
		 * WebFolder wf= (WebFolder)t; if(wf.isShare()) {
		 * if(wf.getOwner().equals(id)) { for(int
		 * i=0;i<wf.getSharingMember().size();i++) { String[] str = new
		 * String[]{
		 * wf.getLocation()+"_"+wf.getName(),id,wf.getSharingMember().get(i)};
		 * if(list.contains(str)) { ; } else { list.add(str); } } } } for(int
		 * i=0;i<wf.getSubList().size();i++) {
		 * if(wf.getSubList().get(i).getLocation().equals(id)) {
		 * if(i+1<wf.getSubList().size()) {
		 * saveSharing(id,list,wf.getSubList().get(i+1)); } } else {
		 * saveSharing(id,list,wf.getSubList().get(i)); } } } }
		 */
		/*
		 * public WebHardManagement parseWebhard(ResultSet recycle, ResultSet
		 * webfile, ResultSet webhardShare) {
		 * 
		 * bbsM.getBbsList().clear(); while (bbs.next()) { int bbsNo =
		 * bbs.getInt("bbs_no"); String id = bbs.getString("id"); String writer
		 * = bbs.getString("writer"); String postTarget =
		 * bbs.getString("POSTTARGET"); String content =
		 * bbs.getClob("content").getSubString(1, (int)
		 * bbs.getClob("content").length()); Date date =
		 * bbs.getDate("writtendate"); StringTokenizer token = new
		 * StringTokenizer(date.toString(), "-"); GregorianCalendar writtendate
		 * = new GregorianCalendar( Integer.parseInt(token.nextToken()),
		 * Integer.parseInt(token.nextToken()) - 1,
		 * Integer.parseInt(token.nextToken())); String image =
		 * bbs.getString("location");
		 * 
		 * BBSItem item = new BBSItem(content, writer, postTarget, image);
		 * item.setTime(writtendate); item.setNumber(bbsNo);
		 * 
		 * bbsM.register(id, item); }
		 */
		public WebHardManagement parseWebhard(ResultSet webfile)
				throws SQLException {
			whM.getWebFolderList().clear();
			while (webfile.next()) {
				String pathname = webfile.getString("location_filename");
				String id = webfile.getString("ownerid");
				Date date = webfile.getDate("writtendate");
				String isShare = webfile.getString("isshare");
				String isDirectory = webfile.getString("isdirectory");
				int filesize = webfile.getInt("filesize");

				StringTokenizer datetoken = new StringTokenizer(
						date.toString(), "-");
				GregorianCalendar writtendate = new GregorianCalendar(
						Integer.parseInt(datetoken.nextToken()),
						Integer.parseInt(datetoken.nextToken()) - 1,
						Integer.parseInt(datetoken.nextToken()));

				StringTokenizer filetoken = new StringTokenizer(pathname, "_");

				//System.out.println(id + "," + pathname + "," + writtendate
				//		+ "," + filesize + "," + isShare + "," + isDirectory);

				String location = filetoken.nextToken();
				String name = filetoken.nextToken();
				if (location.equals("root")) {
					whM.enroll(id);
				} else {
					File file = null;
					if (isDirectory.equals("o")) {
						if (isShare.equals("o")) {
							file = new WebFolder(location, name, writtendate,
									id, true);
						} else {
							file = new WebFolder(location, name, writtendate,
									id, false);
						}
					} else {
						if (isShare.equals("o")) {
							file = new WebFile(location, name, filesize,
									writtendate, id, true);
						} else {
							file = new WebFile(location, name, filesize,
									writtendate, id, false);
						}
					}
					whM.enroll(id, file);
				}
			}
			return whM;
		}

		/*
		 * getter/setter
		 */
		public WebHardManagement getWebhardM() {
			return whM;
		}

		public void setWebhardM(WebHardManagement whM) {
			this.whM = whM;
		}
	}

	private Translatable friend;
	private Translatable text;
	private Translatable webhard;

	public Translator() {
		this(null, null, null, null, null, null);
	}

	public Translator(GroupManagement groupM, MemberManagement memberM,
			BBSManagement bbsM, MessageManagement messageM,
			WebHardManagement webhardM, LanguageBuddyManagement languagebuddyM) {
		this.friend = new FriendTranslator(groupM, memberM, languagebuddyM);
		this.text = new TextTranslator(bbsM, messageM);
		this.webhard = new WebhardTranslator(webhardM);
	}

	public Translatable getFriend() {
		return friend;
	}

	public void setFriend(Translatable friend) {
		this.friend = friend;
	}

	public Translatable getText() {
		return text;
	}

	public void setText(Translatable text) {
		this.text = text;
	}

	public Translatable getWebhard() {
		return webhard;
	}

	public void setWebhard(Translatable webhard) {
		this.webhard = webhard;
	}

	public List<String[]> parseBbs() {
		return ((TextTranslator) text).parseBbs();
	}

	public List<String[]> parseComment() {
		return ((TextTranslator) text).parseComment();
	}

	public List<String[]> parseAgree() {
		return ((TextTranslator) text).parseAgree();
	}

	public List<String[]> parseMessage() {
		return ((TextTranslator) text).parseMessage();
	}

	public BBSManagement parseBbs(ResultSet bbs, ResultSet comment,
			ResultSet agree) throws SQLException {
		return ((TextTranslator) text).parseBbs(bbs, comment, agree);

	}

	public MessageManagement parseMessage(ResultSet message)
			throws SQLException {
		return ((TextTranslator) text).parseMessage(message);
	}

	public List<String[]> parseMember() {
		return ((FriendTranslator) friend).parseMember();
	}

	public List<String[]> parseFriend() {
		return ((FriendTranslator) friend).parseFriend();
	}

	public List<String[]> parseLanguageBuddy() {
		return ((FriendTranslator) friend).parseLanguageBuddy();
	}

	public List<String[]> parseLanguageBuddyProfile() {
		return ((FriendTranslator) friend).parseLanguageBuddyProfile();
	}

	public LanguageBuddyManagement parseLanguageBuddy(ResultSet lb,
			ResultSet lbprofile) throws SQLException {
		return ((FriendTranslator) friend).parseLanguageBuddy(lb, lbprofile);
	}

	public GroupManagement parseGroup(ResultSet rs) throws SQLException {
		return ((FriendTranslator) friend).parseGroup(rs);
	}

	public MemberManagement parseMember(ResultSet rs) throws SQLException {
		return ((FriendTranslator) friend).parseMember(rs);
	}

	/*
	 * getter/setter
	 */
	public GroupManagement getGroupM() {
		return ((FriendTranslator) friend).getGroupM();
	}

	public void setGroupM(GroupManagement groupM) {
		((FriendTranslator) friend).setGroupM(groupM);
	}

	public MemberManagement getMemberM() {
		return ((FriendTranslator) friend).getMemberM();
	}

	public void setMemberM(MemberManagement memberM) {
		((FriendTranslator) friend).setMemberM(memberM);
	}

	public LanguageBuddyManagement getLanguageBuddyM() {
		return ((FriendTranslator) friend).getLanguageBuddyM();
	}

	public void setLanguageBuddyM(LanguageBuddyManagement languageBuddyM) {
		((FriendTranslator) friend).setLanguageBuddyM(languageBuddyM);
	}

	public BBSManagement getBbsM() {
		return ((TextTranslator) text).getBbsM();
	}

	public void setBbsM(BBSManagement bbsM) {
		((TextTranslator) text).setBbsM(bbsM);
	}

	public MessageManagement getMessageM() {
		return ((TextTranslator) text).getMessageM();
	}

	public void setMessageM(MessageManagement messageM) {
		((TextTranslator) text).setMessageM(messageM);
	}
	public WebHardManagement getWebhardM() {
		return ((WebhardTranslator) webhard).getWebhardM();
	}

	public void setWebhardM(WebHardManagement webhardM) {
		((WebhardTranslator) webhard).setWebhardM(webhardM);
	}
	
	public List<String[]> parseWebhard() {
		return ((WebhardTranslator) webhard).parseWebfile();
	}
	public WebHardManagement parseWebHard(ResultSet rs) throws SQLException {
		return ((WebhardTranslator) webhard).parseWebhard(rs);
	}

}
