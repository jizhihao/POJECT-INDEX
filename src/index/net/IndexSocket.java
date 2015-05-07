package index.net;

import index.backstage.Print;
import index.var.Final;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;

public class IndexSocket {
	static Socket s;
	static OutputStream out;
	public static <T extends Object> void send(T obj){
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = null;
        try{
           oos = new ObjectOutputStream(baos);
           oos.writeObject(obj);
           oos.flush();
           byte buf[] = baos.toByteArray();
           if(buf == null)return;
           out.write(buf);
           out.flush();
           if(baos != null)baos.close();
           if(oos != null)oos.close();
        }catch(Exception e){
        	e.printStackTrace();
        }
	}
	static {
		Print.standard("Now doing load ... ", "index.net.Send", Print.INFO);
		try {
			s = new Socket(Final.SERVER_IP, Final.SERVER_PORT);
			new Thread(new Rece()).start();
			out = s.getOutputStream();
			send(new VersionInformation(Final.VERSION));
		} catch (Exception e) {
			Print.standard("Failed to create the socket object.", "index.net.Send", Print.ERROR);
		}
	}
	static class Rece implements Runnable {
		public void run() {
			int len = 0;
			InputStream in = null;
			try {
				in = s.getInputStream();
			} catch (Exception e) {
				Print.standard("Failed to gets the input stream.", "index.Server.Rece.run", Print.ERROR);
			}
			while(true){
				if(s.getInetAddress().getHostName().equals(Final.SERVER_IP)){
					byte[] buf = new byte[1024];
					try {
						len = in.read(buf);
					} catch (Exception e) {
						Print.standard("Failed to read the input stream.", "index.Server.Rece.run", Print.ERROR);
					}
					ByteArrayInputStream bais = new ByteArrayInputStream(buf, 0 , len);
			        ObjectInputStream ois = null;
			        try {
						ois = new ObjectInputStream(bais);
					} catch (Exception e) {
						e.printStackTrace();
					}
			        try {
			        	Object obj = ois.readObject();
			        	if(obj instanceof VersionInformation){
			        		VersionInformation i = (VersionInformation) obj;
			        		if(i != null){
					        	System.out.println(i.version + "  此消息撰写时间为:" + i.time);
					        	continue;
					        }
			        	}
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}
	}
}