package design;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

class Part extends JPanel{
	JCheckBox jCheck1,jCheck2,jCheck3,jCheck4,jCheck5,jCheck6;
	Part(){
		jCheck1=new JCheckBox("�Բ�");
		jCheck2=new JCheckBox("����");
		jCheck3=new JCheckBox("����");
		jCheck4=new JCheckBox("����");
		jCheck5=new JCheckBox("����");
		jCheck6=new JCheckBox("��ǻ");
		add(new JLabel("��鲿λ"));
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
		super("�����");
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
			StringBuffer ss=new StringBuffer(" ID��"+text.jText.getText());
			ss.append("\n ���Ĳ�λ��");
			if(parts.jCheck1.isSelected()==true)
				ss.append("�Բ�");
			if(parts.jCheck2.isSelected()==true)
				ss.append(" ����");
			if(parts.jCheck3.isSelected()==true)
				ss.append(" ����");
			if(parts.jCheck4.isSelected()==true)
				ss.append(" ����");
			if(parts.jCheck5.isSelected()==true)
				ss.append(" ����");
			if(parts.jCheck6.isSelected()==true)
				ss.append(" ��ǻ");
			JText.setText(ss.toString());
		}
		else if(o==tb.jButton2) {
			try {
				//����������
				String s=text.jText.getText();
				if(parts.jCheck1.isSelected()==true)
					s+=" �Բ�";
				if(parts.jCheck2.isSelected()==true)
					s+=" ����";
				if(parts.jCheck3.isSelected()==true)
					s+=" ����";
				if(parts.jCheck4.isSelected()==true)
					s+=" ����";
				if(parts.jCheck5.isSelected()==true)
					s+=" ����";
				if(parts.jCheck6.isSelected()==true)
					s+=" ��ǻ";
				JOptionPane.showConfirmDialog(this, "ԤԼ���ϴ�", "�ɹ�",JOptionPane.YES_NO_OPTION);
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