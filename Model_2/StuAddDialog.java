package model2;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

public class StuAddDialog extends JDialog implements ActionListener {	
	JPanel jp1, jp2, jp3;
	JLabel jl1, jl2, jl3, jl4, jl5, jl6;
	JButton jb1, jb2;
	JTextField jtf1, jtf2, jtf3, jtf4, jtf5, jtf6;
	// owner - the parent window
	public StuAddDialog(Frame owner, String title, boolean modal) {
		super(owner, title, modal);
		jl1 = new JLabel("stuId");
		jl2 = new JLabel("stuName");
		jl3 = new JLabel("stuGender");
		jl4 = new JLabel("stuAge");
		jl5 = new JLabel("stuHometown");
		jl6 = new JLabel("stuDept");
		
		jtf1 = new JTextField();
		jtf2 = new JTextField();
		jtf3 = new JTextField();
		jtf4 = new JTextField();
		jtf5 = new JTextField();
		jtf6 = new JTextField();
		
		jb1 = new JButton("Add");
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
			StuModel tmp = new StuModel();
			String sql = "insert into stu values(?,?,?,?,?,?)";
			String[] paras = {jtf1.getText(), jtf2.getText(), jtf3.getText(), jtf4.getText(), jtf5.getText(), jtf6.getText()};
			if (!tmp.modifyStu(sql, paras)) {
				JOptionPane.showMessageDialog(this, "addition operation is failed");
				
			}
			this.dispose();
		}
	}
}
