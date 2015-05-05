package text;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class d {
	public static void main(String[] a) throws UnknownHostException{
		InetAddress[] i = InetAddress.getAllByName("www.baidu.com");
		System.out.println(i[0] + "/" + i[1]);
	}
}
