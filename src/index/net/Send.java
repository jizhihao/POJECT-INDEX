package index.net;

import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class Send {
	static DatagramSocket ds;
	public static void seed(Object obj, Address a){
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = null;
        try{
           oos = new ObjectOutputStream(baos);
           oos.writeObject(obj);
           oos.flush();
           byte buf[] = baos.toByteArray();
           if(buf == null)return;
           ds.send(new DatagramPacket(buf, buf.length, a.i, a.port));
           if(baos != null)baos.close();
           if(oos != null)oos.close();
        }catch(Exception e){
        	e.printStackTrace();
        }
	}
	static {
		try {
			ds = new DatagramSocket();
		} catch (SocketException e) {
			e.printStackTrace();
		}
	}
}