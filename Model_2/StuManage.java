/**
 * Management Information System (mis)
 * a mini version student management system
 * this is main panel
 * model 2 (model + viewer)
 */
package model2;

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
		// not good to use 1 = 1
		String[] paras = {"1"};
		sm.queryStu("select * from stu where 1=?", paras);
								
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
			String sql = "select * from stu where stuName=?";
			String[] paras = {name};
			sm = new StuModel();
			sm.queryStu(sql, paras);
			// update message in JTable
			jt.setModel(sm);
			
		} else if (arg0.getSource() == jb2) {
			StuAddDialog sa = new StuAddDialog(this, "Add new students", true);
			sm = new StuModel();
			String[] paras2 = {"1"};
			sm.queryStu("select * from stu where 1=?", paras2);
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
			String[] paras2 = {"1"};
			sm.queryStu("select * from stu where 1=?", paras2);
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
			String sql = "delete from stu where stuId=?";
			String[] paras = {stuId};
			StuModel tmp = new StuModel();
			tmp.modifyStu(sql, paras);
			
			sm = new StuModel();
			String[] paras2 = {"1"};
			sm.queryStu("select * from stu where 1=?", paras2);
			// update message in JTable
			jt.setModel(sm);
		}
	}
}
