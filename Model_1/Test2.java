/**
 * get student message from database
 * need to add three jar files: msbase.jar, mssqlserver.jar, msutil.jar
 */
package model1;

import javax.swing.*;
import java.util.*;
import java.sql.*;
import java.awt.*;
import java.awt.event.*;

public class Test2 extends JFrame {
	
	Vector rowData, colNames;
	JTable jt = null;
	JScrollPane jsp = null;
	// things needed to connect to database
	PreparedStatement ps = null;
	Connection ct = null;
	ResultSet rs = null;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Test2 test2 = new Test2();
	}
	// construct function
	public Test2() {
		colNames = new Vector();
		colNames.add("stuId");
		colNames.add("stuName");
		colNames.add("stuGender");
		colNames.add("stuAge");
		colNames.add("stuHometown");
		colNames.add("stuDept");
		
		rowData = new Vector();  // can store multiple line's data

		try {
			// 1, Load the JDBC Driver
			Class.forName("com.Microsoft.jdbc.sqlserver.SQLServerDriver");
			// 127.0.0.1 means local, the empty parameters are the user name and password
			ct = DriverManager.getConnection("jdbc:microsoft:sqlserver://127.0.0.1:1433;databaseName=spdb1", "", "");
			ps = ct.prepareStatement("select * from stu");
			rs = ps.executeQuery();
			
			while (rs.next()) {
				rowData = new Vector();  // can store multiple line's data
				Vector line = new Vector();
				line.add(rs.getString(1));
				line.add(rs.getString(2));
				line.add(rs.getString(3));
				line.add(rs.getString(4));
				line.add(rs.getString(5));
				line.add(rs.getString(6));
				
				rowData.add(line);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
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
		
		
		jt = new JTable(rowData, colNames);
		jsp = new JScrollPane(jt);
		// put jsp to jframe
		this.add(jsp);
		this.setSize(400, 300);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
}
