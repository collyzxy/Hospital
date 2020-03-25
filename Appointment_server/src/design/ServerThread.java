package design;

import java.net.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

/* 信息分类：ID/检查部位+ID标识
 * 收到ID查询返回患者信息
 * 收到检查部位进行时间分配，返回结果*/

public class ServerThread implements Runnable {
	private DatagramSocket sock;
	
//	String url = "jdbc:sqlserver://localhost:1433;databaseName=Hospital";
//    String user="olly";
//    String password="199811";
    Statement ps;
	ResultSet rs;
	Connection con;
	
	Vector<equipment> equips=new Vector<equipment>();
	
	void connectdb(){
		try{
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");  
            con=DriverManager.getConnection("jdbc:sqlserver://localhost:1433;integratedSecurity=true;DatabaseName=Hospital");
            ps=con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		}catch (ClassNotFoundException|SQLException e1) {
			e1.printStackTrace();
		}
	}
	
	public ServerThread(DatagramSocket sock,Vector<equipment> equips){
		this.sock=sock;
		this.equips=equips;
	}
	
	@Override
	public void run(){
		connectdb();
		byte[] data=new byte[1024];
		DatagramPacket pack=new DatagramPacket(data, data.length);
		try{
			sock.receive(pack);
			String info=new String(data,0,pack.getLength());
			InetAddress addr=pack.getAddress();
			int port=pack.getPort();
			deal handle=new deal(info,equips);
			Vector<String> result=handle.get_result();
			String reply=new String();
			patient p=handle.pa;
			ps.executeUpdate("update patientinfo set flag=0 where ID='"+p.ID+"'");
			if(!result.elementAt(0).equals("Error"))
				for(int i=0;i<result.size();i++)
				{
					String[] temp=result.elementAt(i).split(",");
					ps.executeUpdate("insert into appointinfo values('"+p.ID+"','"+temp[0]+"','"+temp[4]+"','"+temp[2]+temp[3]+"',"+temp[6]+")");
					reply=reply+","+result.elementAt(i);
				}
			else
				reply=",error";
			reply=p.ID+","+p.name+","+p.age+","+p.sex+reply;
			ps.executeUpdate("update patientinfo set flag=0 where ID='"+p.ID+"'");
			DatagramPacket repack=new DatagramPacket(reply.getBytes(),reply.getBytes().length,addr,port);
			sock.send(repack);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}