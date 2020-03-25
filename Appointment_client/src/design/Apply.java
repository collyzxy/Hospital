package design;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

class Part extends JPanel{
	JCheckBox jCheck1,jCheck2,jCheck3,jCheck4,jCheck5,jCheck6;
	Part(){
		jCheck1=new JCheckBox("脑部");
		jCheck2=new JCheckBox("心脏");
		jCheck3=new JCheckBox("骨骼");
		jCheck4=new JCheckBox("肝脏");
		jCheck5=new JCheckBox("胰脏");
		jCheck6=new JCheckBox("腹腔");
		add(new JLabel("检查部位"));
		add(jCheck1);
		add(jCheck2);
		add(jCheck3);
		add(jCheck4);
		add(jCheck5);
		add(jCheck6);
	}
}

class IDBox extends JPanel{
	JTextField jText;
	IDBox(){
		jText=new JTextField(10);
		JLabel l1=new JLabel("ID");
		add(l1);
		add(jText);
		setVisible(true);
	}
}
class ThreeButton extends JPanel{
	JButton jButton1,jButton2,jButton3;
	ThreeButton(){
		jButton1=new JButton("List");
		jButton2=new JButton("Up");
		jButton3=new JButton("Exit");
		add(jButton1);
		add(jButton2);
		add(jButton3);
	}
}

class Apply extends JFrame implements ActionListener{

	Part parts;
	IDBox text;
	JTextArea JText;
	ThreeButton tb;
	Apply(){
		super("申请表");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(new FlowLayout());
		testInit();
		add(text);
		add(parts);
		add(new JScrollPane(JText));
		add(tb);
		this.setBounds(300,200,480,300);
		this.setVisible(true);
	}
	
	void testInit() {
		// TODO Auto-generated method stub
		parts=new Part();
		text=new IDBox();
		JText=new JTextArea(7,22);
		tb=new ThreeButton();
		tb.jButton1.addActionListener(this);
		tb.jButton2.addActionListener(this);
		tb.jButton3.addActionListener(this);
	}

	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object o=e.getSource();
		if(o==tb.jButton1) {
			StringBuffer ss=new StringBuffer(" ID："+text.jText.getText());
			ss.append("\n 检查的部位：");
			if(parts.jCheck1.isSelected()==true)
				ss.append("脑部");
			if(parts.jCheck2.isSelected()==true)
				ss.append(" 心脏");
			if(parts.jCheck3.isSelected()==true)
				ss.append(" 骨骼");
			if(parts.jCheck4.isSelected()==true)
				ss.append(" 肝脏");
			if(parts.jCheck5.isSelected()==true)
				ss.append(" 胰脏");
			if(parts.jCheck6.isSelected()==true)
				ss.append(" 腹腔");
			JText.setText(ss.toString());
		}
		else if(o==tb.jButton2) {
			try {
				//传给服务器
				String s=text.jText.getText();
				if(parts.jCheck1.isSelected()==true)
					s+=" 脑部";
				if(parts.jCheck2.isSelected()==true)
					s+=" 心脏";
				if(parts.jCheck3.isSelected()==true)
					s+=" 骨骼";
				if(parts.jCheck4.isSelected()==true)
					s+=" 肝脏";
				if(parts.jCheck5.isSelected()==true)
					s+=" 胰脏";
				if(parts.jCheck6.isSelected()==true)
					s+=" 腹腔";
				JOptionPane.showConfirmDialog(this, "预约已上传", "成功",JOptionPane.YES_NO_OPTION);
				new client(s);
			}catch(Exception ex) {}
	}
	else 
		System.exit(0);
	}
	public static void main(String[] args) {
		new Apply();
	}
}