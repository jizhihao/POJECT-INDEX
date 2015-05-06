package index.net;

import index.Var.Final;

@SuppressWarnings("serial")
public class ConnectionRequest extends Information{
	int port;
	ConnectionRequest(int port) {
		super(Final.CONNECTION);
		this.port = port;
	}
}
