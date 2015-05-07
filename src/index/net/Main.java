package index.net;

import index.main.Start;
import index.mysterious.GenerateKey;

public class Main {
	public static void main(String[] args) {
		Start.initClass("index.Server.Rece");
		IndexSocket.send(new LoginInformation("Mickey", GenerateKey.generateKey("!!z961216@@")));
	}
}
