package index.net;

import index.backstage.Print;
import index.var.Final;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.net.Socket;

public class IndexSocket {
	static Thread t;
	static Socket s;
	static OutputStream out;
	public static final <T extends Information & Serializable> void send(T obj){
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
        	Print.standard("Failed to send the object.", "index.net.IndexSocket.send", Print.ERROR);
        }
	}
	static {
		Print.standard("Now doing load ... ", "index.net.IndexSocket", Print.INFO);
		try {
			(t = new Thread(new Rece())).start();
			s = new Socket(Final.SERVER_IP, Final.SERVER_PORT);
			Print.standard("Successfully established a connection with the server !", "index.net.IndexSocket", Print.INFO);
			out = s.getOutputStream();
			Print.standard("Successfully obtain output stream !", "index.net.IndexSocket", Print.INFO);
			//send(new LoginInformation("Mickey", GenerateKey.generateKey("!!z961216@@")));
			send(new VersionInformation(Final.VERSION));
		} catch(Exception e){
			Print.standard("Failed to create the socket object.", "index.net.IndexSocket", Print.ERROR);
		}
	}
	static class Rece implements Runnable {
		public void run(){
			int len = 0;
			InputStream in = null;
			try {
				in = s.getInputStream();
				Print.standard("Successfully obtain input stream !", "index.net.IndexSocket", Print.INFO);
			} catch(Exception e){
				Print.standard("Failed to gets the input stream.", "index.net.IndexSocket$Rece.run", Print.ERROR);
			}
			while(true){
				if(s.getInetAddress().getHostName().equals(Final.SERVER_IP)){
					byte[] buf = new byte[1024];
					try {
						len = in.read(buf);
					} catch(Exception e){
						Print.standard("Failed to read the input stream.", "index.net.IndexSocket$Rece.run", Print.ERROR);
					}
					ByteArrayInputStream bais = new ByteArrayInputStream(buf, 0, len);
			        ObjectInputStream ois = null;
			        try {
						ois = new ObjectInputStream(bais);
					} catch(Exception e){
						Print.standard("Failed to converts a byte stream object.", "index.net.IndexSocket$Rece.run", Print.ERROR);
					}
			        try {
			        	Object obj = ois.readObject();
			        	if(obj instanceof VersionInformation){
			        		VersionInformation i = (VersionInformation) obj;
			        		if(i != null){
			        			VersionInformation.checkVersion(i);
					        	continue;
					        }
			        	}
					} catch(Exception e){
						Print.standard("Failed to convert object.", "index.net.IndexSocket$Rece.run", Print.ERROR);
					}
				} else{
					Print.standard("Receive an unknown data from " + s.getInetAddress().getHostName() + " packets.", "index.net.IndexSocket$Rece.run", Print.ERROR);
				}
			}
		}
	}
}