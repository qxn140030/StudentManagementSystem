/**
 * Model of the table of Students
 * put all operations to stu table into this class
 */
package model2;

import java.sql.*;
import java.util.Vector;
import javax.swing.table.*;


public class StuModel extends AbstractTableModel {

	Vector rowData, colNames;
	

	// add, delete, update
	public boolean modifyStu(String sql, String[] paras) {
		// SqlHelper could be static, if do not consider concurrency
		SqlHelper helper = new SqlHelper();
		return helper.modifyExecute(sql, paras);
	}
	
	public void queryStu(String sql, String[] paras) {
		SqlHelper helper = null;
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
			helper = new SqlHelper();
			ResultSet rs = helper.queryExecute(sql, paras);
			
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
			helper.close();
		}		
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
