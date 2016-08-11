/**
 * update the students' information
 */
package model2;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

public class StuUpdateDialog extends JDialog implements ActionListener {	
	JPanel jp1, jp2, jp3;
	JLabel jl1, jl2, jl3, jl4, jl5, jl6;
	JButton jb1, jb2;
	JTextField jtf1, jtf2, jtf3, jtf4, jtf5, jtf6;
	// owner - the parent window
	public StuUpdateDialog(Frame owner, String title, boolean modal, StuModel sm, int rowNum) {
		super(owner, title, modal);
		jl1 = new JLabel("stuId");
		jl2 = new JLabel("stuName");
		jl3 = new JLabel("stuGender");
		jl4 = new JLabel("stuAge");
		jl5 = new JLabel("stuHometown");
		jl6 = new JLabel("stuDept");
		
		jtf1 = new JTextField();
		// initialization 
		jtf1.setText((String) sm.getValueAt(rowNum, 0));
		// stuId cannot be changed
		jtf1.setEditable(false);
		jtf2 = new JTextField();
		jtf2.setText((String) sm.getValueAt(rowNum, 1));
		jtf3 = new JTextField();
		jtf3.setText((String) sm.getValueAt(rowNum, 2));
		jtf4 = new JTextField();
		jtf4.setText(sm.getValueAt(rowNum, 3).toString());
		jtf5 = new JTextField();
		jtf5.setText((String) sm.getValueAt(rowNum, 4));
		jtf6 = new JTextField();
		jtf6.setText((String) sm.getValueAt(rowNum, 5));
		
		jb1 = new JButton("Update");
		jb1.addActionListener(this);
		jb2 = new JButton("Cancle");
		
		jp1 = new JPanel();
		jp2 = new JPanel();
		jp3 = new JPanel();
		
		jp1.setLayout(new GridLayout(6, 1));
		jp2.setLayout(new GridLayout(6, 1));
		
		jp1.add(jl1);
		jp1.add(jl2);
		jp1.add(jl3);
		jp1.add(jl4);
		jp1.add(jl5);
		jp1.add(jl6);
		
		jp2.add(jtf1);
		jp2.add(jtf2);
		jp2.add(jtf3);
		jp2.add(jtf4);
		jp2.add(jtf5);
		jp2.add(jtf6);
		
		jp3.add(jb1);
		jp3.add(jb2);
		
		this.add(jp1, BorderLayout.WEST);
		this.add(jp2, BorderLayout.CENTER);
		this.add(jp3, BorderLayout.SOUTH);
		
		this.setSize(300, 250);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == jb1) {
			
			String sql = "update stu set stuName=?, stuGender=?, stuAge=?, "
					+ "stuHometown=?, stuDept=? where stuId=?";
			String[] paras = {jtf2.getText(), jtf3.getText(), jtf4.getText(), 
					jtf5.getText(), jtf6.getText(), jtf1.getText()};
			
			// need to be optimized...
			StuModel tmp = new StuModel();
			tmp.modifyStu(sql, paras);
			this.dispose();
		}
	}
}
