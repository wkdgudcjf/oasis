package com.theOasis.dao;

import java.sql.SQLException;
/**
 * 
 * @author yewon
 *
 */
public interface Close {
	/**
	 * DBMS���� ������ �����մϴ�.
	 * @throws SQLException
	 */
	public abstract void close() throws SQLException ;
}
