package com.theOasis.dao;
import java.sql.SQLException;
/**
 * 
 * @author yewon
 *
 */
public interface Delete {
	/**
	 * table에서 data를 모두 삭제합니다.
	 * @return
	 * @throws SQLException
	 */
	public abstract int delete() throws SQLException ;
	/**
	 * table에서 data를 삭제하는 쿼리를 전송합니다.
	 * @param query
	 * @return
	 * @throws SQLException
	 */
	public abstract int delete(String query) throws SQLException ;
}
