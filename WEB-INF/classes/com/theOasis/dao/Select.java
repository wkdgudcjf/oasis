package com.theOasis.dao;
import java.sql.ResultSet;
/**
 * 
 * @author yewon
 *
 */
public interface Select {
	/**
	 * 테이블에서 data를 얻어옵니다.
	 * @return 얻어온 data
	 * @throws Exception
	 */
	public abstract ResultSet select()  throws Exception ;
	/**
	 * 테이블에서 data를 얻어오는 쿼리문을 전송합니다
	 * @param query 얻어온 data
	 * @return
	 * @throws Exception
	 */
	public abstract ResultSet select(String query) throws Exception ;
}
