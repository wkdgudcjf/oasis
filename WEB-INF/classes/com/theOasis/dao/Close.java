package com.theOasis.dao;

import java.sql.SQLException;
/**
 * 
 * @author yewon
 *
 */
public interface Close {
	/**
	 * DBMS과의 연결을 종료합니다.
	 * @throws SQLException
	 */
	public abstract void close() throws SQLException ;
}
