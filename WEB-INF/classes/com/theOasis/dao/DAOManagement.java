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
	 * dbms�� �����մϴ�
	 * @throws SQLException
	 */
	public abstract void connect() throws SQLException;
	/**
	 * table�� data�� �����մϴ�.
	 * @return
	 * @throws SQLException
	 */
	public abstract int delete() throws SQLException;
	/**
	 * table�� data�� �����մϴ�.
	 * @return
	 * @throws SQLException
	 * @throws FileNotFoundException
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	public abstract int insert() throws SQLException, FileNotFoundException, ClassNotFoundException, IOException;
	/**
	 * table���� data�� ���ɴϴ�.
	 * @return ���� data
	 * @throws FileNotFoundException
	 * @throws SQLException
	 * @throws IOException
	 */
	public abstract ResultSet select() throws FileNotFoundException, SQLException, IOException;
}
