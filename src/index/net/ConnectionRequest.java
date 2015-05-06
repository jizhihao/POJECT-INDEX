package index.net;

import index.var.Final;

@SuppressWarnings("serial")
public class ConnectionRequest extends Information{
	int port;
	ConnectionRequest(int port) {
		super(Final.CONNECTION);
		this.port = port;
	}
}
