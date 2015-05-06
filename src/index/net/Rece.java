package index.net;

import index.backstage.Print;
import index.var.Final;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class Rece extends Thread {
	private static int port = 56655;
	public static int getPort(){
		return port;
	}
	static DatagramSocket ds;
	private static DatagramSocket getDatagramSocket(int port){
		try {
			return new DatagramSocket(port);
		} catch (SocketException e) {
			return null;
		}
	}
	static {
		Print.standard("Now doing load ... ", "index.net.Rece", Print.INFO);
		while(ds == null){
			ds = getDatagramSocket(++port);
		}
		new Rece().start();
		Print.standard("Rece binding to: " + port, "index.net.Rece", Print.INFO);
	}
	public void run() {
		while(true){
			byte[] buf = new byte[65536];
			DatagramPacket dp = new DatagramPacket(buf, buf.length);
			try {
				ds.receive(dp);
			} catch (IOException e) {
				e.printStackTrace();
			}
			if(dp.getAddress().getHostName().equals(Final.SERVER_IP)){
				ByteArrayInputStream bais = new ByteArrayInputStream(dp.getData());
		        ObjectInputStream ois = null;
		        try {
					ois = new ObjectInputStream(bais);
				} catch (Exception e) {
					e.printStackTrace();
				}
		        Information i = null;
		        try {
		        	Object obj = ois.readObject();
		        	if(obj instanceof Information)i = (Information) obj;
				} catch (Exception e) {
					e.printStackTrace();
				}
		        if(i != null){
		        	System.out.println(i.str + "  此消息撰写时间为:" + i.time);
		        }
			}
		}
	}
}