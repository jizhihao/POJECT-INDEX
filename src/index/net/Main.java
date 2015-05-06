package index.net;

public class Main {
	public static void main(String[] args) {
		try {
			Class.forName("index.net.Rece");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		Address a = new Address("192.168.1.4", 56656);
		Information i = new Information("i love u");
			Send.seed(i, a);
		System.out.println("");
	}
}
