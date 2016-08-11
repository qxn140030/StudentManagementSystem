/**
 * Management Information System (mis)
 * a mini version student management system
 * this is main panel
 * model 1 for beginners
 */
package model1;

import javax.swing.*;
import java.util.*;
import java.sql.*;
import java.awt.*;
import java.awt.event.*;

public class StuManage extends JFrame implements ActionListener {
	
	JPanel jp1, jp2;
	JLabel jl1;
	JButton jb1, jb2, jb3, jb4;
	JTable jt;
	JScrollPane jsp;
	JTextField jtf;
	StuModel sm;
	
	// things needed to connect to database
	PreparedStatement ps = null;
	Connection ct = null;
	// ResultSet rs = null;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		StuManage test3 = new StuManage();
	}
	public StuManage() {
		jp1 = new JPanel();
		jtf = new JTextField();
		jb1 = new JButton("Query");
		jb1.addActionListener(this);
		jl1 = new JLabel("Please input a name");
		
		// add them to JPanel jp1
		jp1.add(jl1);
		jp1.add(jtf);
		jp1.add(jb1);
		
		jp2 = new JPanel();
		jb2 = new JButton("Add");
		jb2.addActionListener(this);
		jb3 = new JButton("Update");
		jb3.addActionListener(this);
		jb4 = new JButton("Delete");
		jb4.addActionListener(this);
		
		// add them to JPanel jp2
		jp2.add(jb2);
		jp2.add(jb3);
		jp2.add(jb4);
		
		sm = new StuModel();
								
		jt = new JTable(sm);
		jsp = new JScrollPane(jt);
		// put jsp to jframe
		this.add(jsp);
		this.add(jp1, "North");
		this.add(jp1, "South");
		this.setSize(400, 300);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		// which button is been clicked
		if (arg0.getSource() == jb1) {  // query button
			String name = this.jtf.getText().trim();
			String sql = "select * from stu where stuName='" + name + "'";
			sm = new StuModel(sql);
			// update message in JTable
			jt.setModel(sm);
			
		} else if (arg0.getSource() == jb2) {
			StuAddDialog sa = new StuAddDialog(this, "Add new students", true);
			sm = new StuModel();
			// update message in JTable
			jt.setModel(sm);
			
		} else if (arg0.getSource() == jb3) {
			int rowNum = this.jt.getSelectedRow();
			if (rowNum == -1) {
				JOptionPane.showMessageDialog(this, "choose one row please");
				return;
			} 
			new StuUpdateDialog(this, "Update students' message", true, sm, rowNum);
			sm = new StuModel();
			// update message in JTable
			jt.setModel(sm);
			
		} else if (arg0.getSource() == jb4) {
			// want to delete, need to get stuId first
			// can return the row which has been chosen, -1 if nothing been chosen
			int rowNum = this.jt.getSelectedRow();
			if (rowNum == -1) {
				JOptionPane.showMessageDialog(this, "choose one row please");
				return;
			} 
			// starting column number is 0, not 1
			String stuId = (String) sm.getValueAt(rowNum, 0);

			try {
				// 1, Load the JDBC Driver
				Class.forName("com.Microsoft.jdbc.sqlserver.SQLServerDriver");
				// 127.0.0.1 means local, the empty parameters are the user name and password
				ct = DriverManager.getConnection("jdbc:microsoft:sqlserver://127.0.0.1:1433;databaseName=spdb1", "", "");
				ps = ct.prepareStatement("delete from stu where stuId=?");
				ps.setString(1, stuId);
				ps.executeUpdate();
				
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					
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
			
			sm = new StuModel();
			// update message in JTable
			jt.setModel(sm);
		}
	}
}
