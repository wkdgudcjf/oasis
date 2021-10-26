package com.theOasis.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.theOasis.dao.DAO;

/**
 * dbms에 쿼리문을 전송하는 class
 * @author yewon
 *
 */
public class DAOImpl implements DAO {

	
	private Connection conn;
	/**
	 * table 명
	 */
	private String tableName;

	public DAOImpl() {

	}

	public DAOImpl(String tableName) {
		this.tableName = tableName;
	}

	public DAOImpl(Connection conn) {
		this.conn = conn;
	}

	public DAOImpl(Connection conn, String tableName) {
		this.conn = conn;
		this.tableName = tableName;
	}
	
	private ResultSet executeQuery(String query) throws SQLException{
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(query);

		} catch (Exception e) {
		}
		return pstmt.executeQuery();
	}
	private int executeUpdate(String query) throws SQLException{
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(query);

		} catch (Exception e) {
		}
		return pstmt.executeUpdate();
	}
	public ResultSet select() throws SQLException {
		return this.executeQuery("select * from " + tableName);
	}

	public ResultSet select(String query) throws SQLException {
		return this.executeQuery(query);
	}

	public int delete() throws SQLException {
		return executeUpdate("delete from " + tableName);
	}

	public int delete(String query) throws SQLException {
		return executeUpdate(query);
	}

	public int insert(String query) throws SQLException {
		return executeUpdate(query);
	}

	public int update(String query) throws SQLException {
		return executeUpdate(query);
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public Connection getConn() {
		return conn;
	}

	public void setConn(Connection conn) {
		this.conn = conn;
	}
	
}
