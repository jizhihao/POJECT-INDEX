package index.backstage;

import index.GUI.FramePrintStream;
import index.GUI.GUIframe;

public class ThreadNumber extends Thread {
	public static boolean running;
	public void run(){
		while(running){
			FramePrintStream.getFps().setTitle("The execute permissions: Chief Executive Officer - Mickey   FPS: " + GUIframe.getFPS() + "   Thread: " + Thread.getAllStackTraces().size());
			try {
				Thread.sleep(1000);
			} catch(InterruptedException e){
				e.printStackTrace();
			}
		}
	}
}