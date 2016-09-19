package ssa;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SqlStatement {

	private String statement;
	private Connection myConn;
	
	//columns should be in the form "column1, column2, ..." 
	public PreparedStatement getSelectStatement(String table, String columns) throws SQLException {
		return myConn.prepareStatement("select " + columns + " from " + table);
	}
	
	//conditions should be in the form "cond1 = ?, cond2 = ?, ..." with ? included
	public PreparedStatement getSelectStatement(String table, String columns, String conditions) throws SQLException {
		return myConn.prepareStatement("select " + columns + " from " + table + " where " + conditions);
	}
	
	public SqlStatement(Connection myConn) {
		this.myConn = myConn;
	}
}
