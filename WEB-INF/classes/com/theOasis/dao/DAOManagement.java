package com.theOasis.dao;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 
 * @author yewon
 *
 */
public interface DAOManagement extends Close{
	/**
	 * dbms와 연결합니다
	 * @throws SQLException
	 */
	public abstract void connect() throws SQLException;
	/**
	 * table의 data를 삭제합니다.
	 * @return
	 * @throws SQLException
	 */
	public abstract int delete() throws SQLException;
	/**
	 * table에 data를 삽입합니다.
	 * @return
	 * @throws SQLException
	 * @throws FileNotFoundException
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	public abstract int insert() throws SQLException, FileNotFoundException, ClassNotFoundException, IOException;
	/**
	 * table에서 data를 얻어옵니다.
	 * @return 얻어온 data
	 * @throws FileNotFoundException
	 * @throws SQLException
	 * @throws IOException
	 */
	public abstract ResultSet select() throws FileNotFoundException, SQLException, IOException;
}
