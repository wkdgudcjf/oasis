package com.theOasis.dao;
import java.sql.SQLException;
/**
 * 
 * @author yewon
 *
 */
public interface Insert {
	/**
	 * table�� data�� �����ϴ� �������� �����մϴ�.
	 * @param query
	 * @return
	 * @throws SQLException
	 */
	public abstract int insert(String query) throws SQLException ;
}
