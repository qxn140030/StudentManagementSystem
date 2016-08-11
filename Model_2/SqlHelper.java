/**
 * this class operates on database
 * data access object
 */
package model2;

import java.sql.*; 

public class SqlHelper {
	// things needed to connect to database
	PreparedStatement ps = null;
	Connection ct = null;
	ResultSet rs = null;
	String driver = "com.Microsoft.jdbc.sqlserver.SQLServerDriver";
	String url = "jdbc:microsoft:sqlserver://127.0.0.1:1443;databaseName=spdb1";
	String user = "";
	String password = "";
	
	// close database resource
	public void close() {
		try {
			if (rs != null) {
				rs.close();
			}
			if (ps != null) {
				ps.close();
			}
			if (ct != null) {
				ct.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}			
	}
	// 
	public ResultSet queryExecute(String sql) {
		
		try {
			// Load JDBC Driver
			Class.forName(driver);
			// Connection
			ct = DriverManager.getConnection(url, user, password);
			ps = ct.prepareStatement(sql);
			
			rs = ps.executeQuery();
			
		} catch (Exception e) {
			
			e.printStackTrace();
		} finally {
			// cannot close here
			// this.close();
		}
		return rs;
	}
	
	// query
	public ResultSet queryExecute(String sql, String[] paras) {
		
		try {
			// Load JDBC Driver
			Class.forName(driver);
			// Connection
			ct = DriverManager.getConnection(url, user, password);
			ps = ct.prepareStatement(sql);
			for (int i = 0; i < paras.length; i++) {
				ps.setString(i + 1, paras[i]);
			}
			rs = ps.executeQuery();
			
		} catch (Exception e) {
			
			e.printStackTrace();
		} finally {
			// cannot close here
			// this.close();
		}
		return rs;
	}
	
	// add, delete, update
	public boolean modifyExecute(String sql, String[] paras) {
		
		boolean flag = true;		
		try {
			// Load JDBC Driver
			Class.forName(driver);
			// Connection
			ct = DriverManager.getConnection(url, user, password);
			ps = ct.prepareStatement(sql);
			for (int i = 0; i < paras.length; i++) {
				ps.setString(i + 1, paras[i]);
			}
			if (ps.executeUpdate() != 1) {
				flag = false;
			}			
			
		} catch (Exception e) {
			flag = false;
			e.printStackTrace();
		} finally {
			this.close();
		}
		return flag;		
	}
}
