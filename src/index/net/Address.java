package index.net;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class Address {
	InetAddress i;
	int port;
	Address(String ip, int port){
		try {
			this.i = InetAddress.getByName(ip);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		this.port = port;
	}
}
