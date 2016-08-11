/**
 * the use of JTable
 */
package model1;

import javax.swing.*;
import java.util.*;
import java.sql.*;
import java.awt.*;
import java.awt.event.*;

public class Test1 extends JFrame {
	//
	Vector rowData, colNames;
	JTable jt = null;
	JScrollPane jsp = null;
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Test1 test1 = new Test1();
	}
	// construct function
	public Test1() {
		colNames = new Vector();
		colNames.add("stuId");
		colNames.add("stuName");
		colNames.add("stuGender");
		colNames.add("stuAge");
		colNames.add("stuHometown");
		colNames.add("stuDept");
		
		rowData = new Vector();  // can store multiple line's data
		Vector line = new Vector();
		line.add("sp001");
		line.add("Wukong");
		line.add("male");
		line.add("500");
		line.add("montain");
		line.add("god");
		
		rowData.add(line);
		jt = new JTable(rowData, colNames);
		jsp = new JScrollPane(jt);
		// put jsp to jframe
		this.add(jsp);
		this.setSize(400, 300);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
}
