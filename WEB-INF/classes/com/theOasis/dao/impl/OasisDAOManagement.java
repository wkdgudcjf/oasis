package com.theOasis.dao.impl;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.GregorianCalendar;
import java.util.List;

import com.theOasis.dao.DAOManagement;

/**
 * dbms와 하는 통신을 관리함.
 * 
 * @author yewon
 * 
 */
public class OasisDAOManagement implements DAOManagement {

	private Connection conn;
	private String jdbc_url;
	private String db_id;
	private String db_pw;
	private Translator translator;
/*
	private String[] temp = new String[] {
			"insert into member_tb values ('haaaaaai','김예원','1234','1991/11/7','010-9905-5624','질문?','답변')",
			"insert into bbs_tb values (1, 'haaaaaai', 'haaaaaai', 'haaaaaai','내용입니다','2013/1/22', '이미지없음')",
			"insert into comment_tb values (1, 'haaaaaai', 1, '댓글입니다', '2013/1/27')",
			"insert into agree_tb values ('1_haaaaaai', 'o')",
			"insert into message_tb values ('haaaaaai', 'haaaaaai', '쪽지입니다','2013/01/13','o')",
			"insert into recyclebin_tb values ('haaaaaai','c:\\program files\\yewon.jpg','2013/1/8')",
			"insert into webfile_tb values ('c:\\program files\\yewon.jpg', 'haaaaaai', '2013/1/8', 'o','x')",
			"insert into webhardsharing_tb values ('c:\\program files\\yewon.jpg', 'haaaaaai', 'haaaaaai')",
			"insert into friend_tb values ('haaaaaai', 'haaaaaai', '김예원', '그룹1')",
			"insert into languagebuddy_tb values ('haaaaaai','haaaaaai')",
			"insert into languagebuddyprofile_tb values ('haaaaaai', '자기소개입니다', '한국어','영어')" };
*/
	private String[] tempName = new String[] { "languagebuddyprofile_tb",
			"languagebuddy_tb", "friend_tb", "webhardsharing_tb", "webFile_tb",
			"recyclebin_tb", "message_tb", "agree_tb", "comment_tb", "bbs_tb",
			"Member_tb" };

	public OasisDAOManagement() throws ClassNotFoundException {
		this("jdbc:oracle:thin:@127.0.0.1:1521:orcl", "scott", "fprxhvldk");
	}

	public OasisDAOManagement(Translator translator)
			throws ClassNotFoundException {
		this(translator, "jdbc:oracle:thin:@127.0.0.1:1521:orcl", "scott",
				"fprxhvldk");
	}

	public OasisDAOManagement(String jdbc_url, String db_id, String db_pw)
			throws ClassNotFoundException {
		this(null, jdbc_url, db_id, db_pw);
	}

	public OasisDAOManagement(Translator translator, String jdbc_url, String db_id, String db_pw)
			throws ClassNotFoundException {
		if(translator!=null)	
			this.translator = translator;
		else
			this.translator= new Translator();
		this.jdbc_url = jdbc_url;
		this.db_id = db_id;
		this.db_pw = db_pw;
		Class.forName("oracle.jdbc.driver.OracleDriver");
	}

	@Override
	public void close() throws SQLException {
		conn.close();
	}

	@Override
	public void connect() throws SQLException {
		conn = DriverManager.getConnection(jdbc_url, db_id, db_pw);
		conn.setAutoCommit(true);
	}

	@Override
	public int delete() throws SQLException {
		DAOImpl dao = new DAOImpl(conn);
		for (String t : tempName) {
			dao.setTableName(t);
			dao.delete();
		}
		return 0;
	}
	private void innerExecute(DAOImpl dao, int tableNum, List<String[]> input)
	{
		StringBuilder query =new StringBuilder();
		for(String[] list :input)
		{
			query.delete(0, query.length());
			query.append("insert into "+tempName[tableNum]+" values (");
			for(int i=0; i<list.length; i++)
			{
				if((tableNum==6)&&(i==3))
				{
					query.append(","+list[i]+"");
				}
				else if((tableNum==4)&&(i==0))
				{
					query.append(list[i]);
				}
				else if(i!=0)
					query.append(",'"+list[i]+"'");
				else
					query.append("'"+list[i]+"'");
			}
			query.append(")");
			try {
				dao.insert(query.toString());
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
	}
	@Override
	public int insert(){
		DAOImpl dao = new DAOImpl(conn);
		innerExecute(dao, 10, translator.parseMember());	
		innerExecute(dao, 9, translator.parseBbs());
		innerExecute(dao, 8, translator.parseComment());
		innerExecute(dao, 7, translator.parseAgree());
		innerExecute(dao, 6, translator.parseMessage());//이게 메세지
		innerExecute(dao, 4, translator.parseWebhard());
		innerExecute(dao, 2, translator.parseFriend());
		innerExecute(dao, 1, translator.parseLanguageBuddy());
		innerExecute(dao, 0, translator.parseLanguageBuddyProfile());
	
		
		return 0;
		
	}

	@Override
	public ResultSet select() throws FileNotFoundException, SQLException,
			IOException {

		DAOImpl dao = new DAOImpl(conn);
		ResultSet rs = null;

		for (int i = 0; i < 11; i++) {

			dao.setTableName(tempName[10 - i]);
			if(i==6)
				rs = dao.select("select * from " + tempName[4] +" order by file_no");
			else
				rs = dao.select();
			
			switch (i) {
			case 0:
				translator.parseMember(rs);
				break;
			case 1:
				dao.setTableName(tempName[8]);
				ResultSet comment = dao.select();
				
				dao.setTableName(tempName[7]);
				ResultSet agree = dao.select();
				translator.parseBbs(rs, comment, agree);
				i+=2;
				break;
			case 4:
				translator.parseMessage(rs);
				break;
			case 5:
				break;
			case 6:
				translator.parseWebHard(rs);
				break;
			case 7:
				break;
			case 8:
				translator.parseGroup(rs);
				break;
			case 9:
				dao.setTableName(tempName[0]);
				ResultSet lbp = dao.select();
				translator.parseLanguageBuddy(rs,lbp);
				i++;
				break;
			default:
				break;
			}
		}

		return null;
	}

	public Connection getConn() {
		return conn;
	}

	public void setConn(Connection conn) {
		this.conn = conn;
	}

	public String getJdbc_url() {
		return jdbc_url;
	}

	public void setJdbc_url(String jdbc_url) {
		this.jdbc_url = jdbc_url;
	}

	public String getDb_id() {
		return db_id;
	}

	public void setDb_id(String db_id) {
		this.db_id = db_id;
	}

	public String getDb_pw() {
		return db_pw;
	}

	public void setDb_pw(String db_pw) {
		this.db_pw = db_pw;
	}

	public Translator getTranslator() {
		return translator;
	}

	public void setTranslator(Translator translator) {
		this.translator = translator;
	}

	public static void main(String[] args) {
		try {
			OasisDAOManagement manage = new OasisDAOManagement();
			manage.connect();
			manage.select();
			manage.delete();

			manage.insert();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
