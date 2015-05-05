package index.net;

import index.Backstage.Print;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class Rece extends Thread {
	static String hostName = "192.168.1.4";
	static int port = 56655;
	static DatagramSocket ds;
	private static DatagramSocket getDatagramSocket(int port){
		try {
			return new DatagramSocket(port);
		} catch (SocketException e) {
			return null;
		}
	}
	static {
		Print.standard("Now doing load ... ", "net.Rece", Print.INFO);
		while(ds == null){
			ds = getDatagramSocket(++port);
		}
		new Rece().start();
		Print.standard("Rece binding to: " + port, "net.Rece", Print.INFO);
	}
	public void run() {
		while(true){
			byte[] buf = new byte[1024];
			DatagramPacket dp = new DatagramPacket(buf, buf.length);
			try {
				ds.receive(dp);
			} catch (IOException e) {
				e.printStackTrace();
			}
			if(dp.getAddress().getHostName().equals(hostName)){
				ByteArrayInputStream bais = new ByteArrayInputStream(dp.getData());
		        ObjectInputStream ois = null;
		        try {
					ois = new ObjectInputStream(bais);
				} catch (IOException e) {
					e.printStackTrace();
				}
		        Information i = null;
		        try {
					i = (Information) ois.readObject();
				} catch (ClassNotFoundException | IOException e) {
					e.printStackTrace();
				}
		        if(i != null){
		        	System.out.println(i.str + "  此消息撰写时间为:" + i.time);
		        }
			}
		}
	}
}
