package com.koala.sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Vector;

public class JSONDao {
	Connection conn;
	Statement stm;
	PreparedStatement pstm;
	String SQL_SELECT_ALL;
	String SQL_SELECT;
	String SQL_INSERT;
	String SQL_UPDATE;
	String SQL_DELETE;
	
	public JSONDao() {
		SQL_SELECT_ALL = "SELECT * FROM MEMBERS";
		SQL_SELECT = "SELECT * FROM MEMBERS WHERE NO=?";
		SQL_INSERT = "INSERT INTO MEMBERS(NAME,EMAIL,PHONE) VALUES (?,?,?)";
		SQL_UPDATE = "UPDATE MEMBERS SET NAME=?M EMAIL=?, PHONE=?";
		SQL_DELETE = "DELETE FROM MEMBERS WHERE NO=?";
	}
	
	public void setConnection(Connection conn) {
		this.conn = conn;
	}
	
	public Vector<Vector> selectAll() {
		return null;
		
	}
}
