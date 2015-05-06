package index.backstage;

import index.GUI.FramePrintStream;
import index.GUI.GUIframe;

import java.util.Map;

public class ThreadNumber extends Thread {
	public static boolean running;
	public static Map<Thread, StackTraceElement[]> map = Thread.getAllStackTraces();
	public void run(){
		while(running){
			FramePrintStream.getFps().setTitle("The execute permissions: Chief Executive Officer - Mickey   FPS: " + GUIframe.getFPS() + "   Thread: " + map.size());
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}