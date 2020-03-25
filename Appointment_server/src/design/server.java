package design;

import java.net.*;
import java.sql.*;
import java.util.Vector;

/* 获取客户端发送信息，生成线程*/

public class server {
	
//	String url = "jdbc:sqlserver://localhost:1433;databaseName=Hospital";
//    String user="olly";
//    String password="199811";
    Statement ps;
	ResultSet rs;
	Connection con;
	
	DatagramSocket sock;
	
	Vector<equipment> equips=new Vector<equipment>();//读数据库存入设备信息
	
	void connectdb(){
		try{
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");  
            con=DriverManager.getConnection("jdbc:sqlserver://localhost:1433;integratedSecurity=true;DatabaseName=Hospital");
            ps=con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		}catch (ClassNotFoundException|SQLException e1) {
			e1.printStackTrace();
		}
	}
	void getequipinfo(){
		//读数据库设备信息
		String no="";
		String location=new String();
		Vector<Vector<Integer>> num=new Vector<Vector<Integer>>();
		int maxnum=0;
		int firstdate=0;
		int firsttime=0;
		try {
			rs=ps.executeQuery("select * from equips");
			while(rs.next()){
				no=rs.getString(1).trim();
				location=rs.getString(4);
				maxnum=rs.getInt(5);
				if(!no.isEmpty()){
					Statement s=con.createStatement();
					ResultSet trs;
					trs=s.executeQuery("select * from ["+no+"]");
					while(trs.next()){
						Vector<Integer> dnum=new Vector<Integer>(2);
						Integer pnum=trs.getInt("num_am");
						dnum.add(pnum);
						trs.next();
						pnum=trs.getInt("num_pm");
						dnum.add(pnum);
						num.add(dnum);
					}
				}
				flag:for(int i=0;i<num.size();i++){
					for(int j=0;j<num.elementAt(i).size();j++){
						if(num.elementAt(i).elementAt(j)<maxnum){
							firstdate=i;
							firsttime=j;
							break flag;
						}
					}
				}
				equipment e=new equipment(no,location,firstdate,firsttime,maxnum,num);
				equips.add(e);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
	}

	void restart(Date today){
		sock.close();
		try{
			for(int i=0;i<equips.size();i++){
				equipment e=equips.elementAt(i);
				int r=ps.executeUpdate("delete from "+e.no+" where checkdate<"+today);
				for(int j=r/2;j<e.num.size();j++){
					ps.executeUpdate("update "+e.no+" set num_am="+e.num.elementAt(0)+",num_pm="+e.num.elementAt(1)+" where checkdate=dateadd(day,"+j+","+today+")");
				}
			}
			sock=new DatagramSocket(8888);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	server() throws Exception{
		connectdb();
		getequipinfo();
		/*String info="101 胰脏 腹腔 心脏 骨骼 肝脏 脑部";
		deal handle=new deal(info,equips);
		Vector<String> result=handle.get_result();
		for(int i=0;i<result.size();i++)
			System.out.println(result.elementAt(i));*/		
		sock=new DatagramSocket(8888);
		while(true)
		{
			System.out.println("waiting for connection");
			ServerThread ct=new ServerThread(sock,equips);
			ct.run();
		}
	}
	
	/*public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			new server();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}*/
	
}
