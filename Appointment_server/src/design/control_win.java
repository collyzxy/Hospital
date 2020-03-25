package design;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

@SuppressWarnings("serial")
class ThreeButton extends JPanel{
	JButton jButton1,jButton2,jButton3;
	ThreeButton(){
		jButton1=new JButton("启动");
		jButton2=new JButton("重置");
		jButton3=new JButton("退出");
		add(jButton1);
		add(jButton2);
		add(jButton3);
	}
}

@SuppressWarnings("serial")
public class control_win extends JFrame implements ActionListener{

	server s;
	ThreeButton tb;
	control_win(){//设置界面大小 待写
		super("服务器控制");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(new FlowLayout());
		testInit();
		add(tb);
		this.setSize(300, 100);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}

	void testInit() {
		// TODO Auto-generated method stub
		tb=new ThreeButton();
		tb.jButton1.addActionListener(this);
		tb.jButton2.addActionListener(this);
		tb.jButton3.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object o=e.getSource();
		if(o==tb.jButton1)
			try {
				s=new server();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		if(o==tb.jButton2)
		{
			java.util.Date utildate=new java.util.Date();
			java.sql.Date today = new java.sql.Date(utildate.getTime());
			s.restart(today);
		}
		else
		{
			java.util.Date utildate=new java.util.Date();
			java.sql.Date today = new java.sql.Date(utildate.getTime());
			s.restart(today);
			System.exit(0);
		}
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		new control_win();
	}
}
