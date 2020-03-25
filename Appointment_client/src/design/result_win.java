package design;

import java.awt.event.*;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class result_win {

	result_win(String result){
		String[] datas=result.split(",");
		String ID=datas[0];
		String NAME=datas[1];
		String AGE=datas[2];
		String SEX=datas[3];
		
		if(!datas[4].equals("error")){
			Object[][] data=new Object[datas.length/7][7];
			for(int i=0;i<datas.length/7;i++){
				for(int j=0;j<7;j++) {
					if(j==2){
						java.util.Date utildate=new java.util.Date();
						Calendar cal=new GregorianCalendar();
						cal.setTime(utildate);
						cal.add(cal.DATE, Integer.parseInt(datas[7*i+j+4].trim()));
						utildate=cal.getTime();
						java.sql.Date date=new java.sql.Date(utildate.getTime());
						data[i][j]=date;
					}
					else if(j==3){
						if(Integer.parseInt(datas[7*i+j+4].trim())==0)
							data[i][j]="����";
						else
							data[i][j]="����";
					}
					else
						data[i][j]=datas[7*i+j+4];
				}
			}
			show_win(ID,NAME,AGE,SEX,data);
		}
		else{
			JPanel jp_warning=new JPanel();
			JOptionPane.showMessageDialog(jp_warning, "���ύ��ԤԼ", "����",JOptionPane.WARNING_MESSAGE);
		}
	}
	void show_win(String ID,String NAME,String AGE,String SEX,Object[][] data) {
		JFrame jf_result=new JFrame("ԤԼ���");
		JLabel id=new JLabel("ID:"+ID);
		JLabel name=new JLabel("����:"+NAME);
		JLabel age=new JLabel("����:"+AGE);
		JLabel sex=new JLabel("�Ա�:"+SEX);
		
		String[] field={"��λ","�豸���","����","ʱ��","�ص�","Ҫ��","�Ŷ�����"};
		DefaultTableModel mod=new DefaultTableModel(data,field);
			
		JTable jt=new JTable(mod);
		JScrollPane jsp=new JScrollPane();
		JButton jb=new JButton("ȷ��");
		JPanel jp_info=new JPanel();
		JPanel jp_result=new JPanel();
		
		jf_result.add("North",jp_info);
		jf_result.add("Center", jsp);
		jf_result.add("South", jp_result);
		
		jsp.getViewport().add(jt);
		
		jp_info.add(id);
		jp_info.add(name);
		jp_info.add(age);
		jp_info.add(sex);
		
		jp_result.add(jb);
		
		jb.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				jf_result.dispatchEvent(new WindowEvent(jf_result,WindowEvent.WINDOW_CLOSING) );
			}
		});
		
		jf_result.setVisible(true);
		jf_result.setSize(800, 600);
		jf_result.setLocationRelativeTo(null);

	}

}
