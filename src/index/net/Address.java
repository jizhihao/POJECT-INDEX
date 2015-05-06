package index.net;

import index.var.Final;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class Address {
	static final Address SERVER = new Address(Final.SERVER_IP, Final.SERVER_PORT);
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
