package com.theOasis.dao;
import java.sql.ResultSet;
/**
 * 
 * @author yewon
 *
 */
public interface Select {
	/**
	 * ���̺��� data�� ���ɴϴ�.
	 * @return ���� data
	 * @throws Exception
	 */
	public abstract ResultSet select()  throws Exception ;
	/**
	 * ���̺��� data�� ������ �������� �����մϴ�
	 * @param query ���� data
	 * @return
	 * @throws Exception
	 */
	public abstract ResultSet select(String query) throws Exception ;
}
