/**
 * Model of the table of Students
 * put all operations to stu table into this class
 */
package model1;

import java.sql.*;
import java.util.Vector;
import javax.swing.table.*;


public class StuModel extends AbstractTableModel {

	Vector rowData, colNames;
	
	// things needed to connect to database
	PreparedStatement ps = null;
	Connection ct = null;
	ResultSet rs = null;
	
	public void init(String sql) {
		if (sql.equals("")) {
			sql = "select * from stu";
		}
		// middle panel
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
			ps = ct.prepareStatement(sql);
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
	}
	
	public void addStu(String sql) {
		
	}
	
	// pass sql to get model
	public StuModel(String sql) {
		this.init(sql);
	}	
	
	// construct function, to initiate our model
	public StuModel() {
		this.init("");
	}
	
	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return this.colNames.size();
	}

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return this.rowData.size();
	}

	@Override
	public Object getValueAt(int row, int col) {
		// TODO Auto-generated method stub
		return ((Vector)this.rowData.get(row)).get(col);
	}

	@Override
	public String getColumnName(int column) {
		// TODO Auto-generated method stub
		return (String)this.colNames.get(column);
	}
	
}
