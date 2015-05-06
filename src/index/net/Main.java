package index.net;

import index.main.Start;

public class Main {
	public static void main(String[] args) {
		Start.initClass("index.Server.Rece");
		IndexSocket.send(new Information("i love you"));
	}
}
