package design;

import java.net.*;

/* 发送ID+检查部位，获取回复信息
 * 回复信息：预约结果
 * 进入结果显示界面*/

public class client {

	@SuppressWarnings("resource")
	client(String data) throws Exception{
		
		DatagramSocket sock=new DatagramSocket();
		DatagramPacket pack=new DatagramPacket(data.getBytes(), data.getBytes().length, InetAddress.getByName("127.255.255.255"), 8888);
		try{
			sock.send(pack);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		byte[] reply=new byte[2048];
		DatagramPacket repack=new DatagramPacket(reply, reply.length,InetAddress.getByName("127.255.255.255"), 8888);
		sock.receive(repack);
		String redata=new String(reply,0,repack.getLength());	
		//转向结果显示界面
		if(!redata.isEmpty())
			new result_win(redata);
	}
//	public static void main(String[] args) throws Exception {
//		new client("");
//	}
	

}

