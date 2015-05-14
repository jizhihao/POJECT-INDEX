package index.net;

import java.io.IOException;

import index.mysterious.GenerateKey;

public class Main {
	@SuppressWarnings("deprecation")
	public static void main(String[] args) throws IOException {
		//Start.initClass("index.Server.Rece");
		System.out.println(new LoginInformation("Mickey", GenerateKey.generateKey("!!z961216@@")).password);
		IndexSocket.send(new LoginInformation("Mickey", GenerateKey.generateKey("!!z961216@@")));
		IndexSocket.s.close();
		IndexSocket.t.stop();
	}
}
