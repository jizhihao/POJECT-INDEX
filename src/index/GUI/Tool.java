package index.GUI;

import java.awt.Toolkit;

public final class Tool {
	public static int getSW(){
		return (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
	}
	public static int getSH(){
		return (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();
	}
}