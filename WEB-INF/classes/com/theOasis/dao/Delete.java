package com.theOasis.dao;
import java.sql.SQLException;
/**
 * 
 * @author yewon
 *
 */
public interface Delete {
	/**
	 * table���� data�� ��� �����մϴ�.
	 * @return
	 * @throws SQLException
	 */
	public abstract int delete() throws SQLException ;
	/**
	 * table���� data�� �����ϴ� ������ �����մϴ�.
	 * @param query
	 * @return
	 * @throws SQLException
	 */
	public abstract int delete(String query) throws SQLException ;
}
