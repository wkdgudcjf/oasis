package com.theOasis.dao;
import java.sql.SQLException;
/**
 * 
 * @author yewon
 *
 */
public interface Insert {
	/**
	 * table에 data를 삽입하는 쿼리문을 전송합니다.
	 * @param query
	 * @return
	 * @throws SQLException
	 */
	public abstract int insert(String query) throws SQLException ;
}
