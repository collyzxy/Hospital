package design;

import java.net.*;

/* ����ID+��鲿λ����ȡ�ظ���Ϣ
 * �ظ���Ϣ��ԤԼ���
 * ��������ʾ����*/

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
		//ת������ʾ����
		if(!redata.isEmpty())
			new result_win(redata);
	}
//	public static void main(String[] args) throws Exception {
//		new client("");
//	}
	

}

