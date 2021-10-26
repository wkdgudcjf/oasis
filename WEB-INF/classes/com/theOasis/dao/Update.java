package com.theOasis.dao;
import java.sql.SQLException;
/**
 * 
 * @author yewon
 *
 */
public interface Update {
	public abstract int update(String query) throws SQLException ;
}
