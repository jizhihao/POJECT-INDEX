package index.GUI;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;

import javax.swing.JComponent;
import javax.swing.JFrame;

import index.backstage.Print;
import index.backstage.ThreadNumber;

public final class GUIframe implements Runnable {
	private interface Draw{
		public abstract void render(Graphics2D g, JFrame frame);
		public abstract void update(int deltaTime);
	}
	private static final <Darw> Draw getLoginDraw(){
		return new Draw(){
			double x = 0;
			public final void render(Graphics2D g, JFrame frame){
				g.drawImage(Resources.cloud, (int) x, 0, Resources.cloudC.w, Resources.cloudC.h, frame);
		    	g.drawImage(Resources.landingBox, Resources.landingBoxC.x, Resources.landingBoxC.y, Resources.landingBoxC.w, Resources.landingBoxC.h, frame);
		    	g.drawImage(Resources.i, Resources.strokeC.x, Resources.strokeC.y, Resources.strokeC.w, Resources.strokeC.h, frame);
		    	if(Resources.headDoing)g.drawImage(Resources.head, Resources.strokeC.x, Resources.strokeC.y, Resources.strokeC.w, Resources.strokeC.h, frame);
		    	if(Box.visibility)g.drawImage(Resources.point, Resources.pointC.x, Resources.pointC.y, Resources.pointC.w, Resources.pointC.h, frame);
			}
			public final void update(int deltaTime){
				x -= deltaTime * 0.12;
		        while(x < -Resources.w)x += Resources.w;
			}
		};
	}
	private static Graphics2D g;
	private static Draw draw;
	private static GUIframe gf;
	private static Thread t;
	private static JFrame frame;
    private static Canvas canvas; 
    private static JComponent jc;
    private static BufferStrategy bufferStrategy;
    static boolean framePrintStreamRunning;
	private GUIframe(){
		Print.standard("Now doing launchFrame ... ", "index.GUI.GUI.launchFrame", Print.INFO);
        frame = new JFrame("POJECT - INDEX");
        frame.setIconImage(Resources.icon);
        frame.setUndecorated(true);
        frame.getContentPane().setBackground(new Color(71, 219, 250));
        jc = (JComponent) frame.getLayeredPane();
        jc.add(DimorphismButton.quit);
        jc.add(DimorphismButton.next);
        jc.add(Box.userID);
        jc.add(Box.password);
        canvas = new Canvas();  
        canvas.setBounds(0, 0, Resources.w, Resources.h);  
        canvas.setIgnoreRepaint(true); 
        frame.add(canvas);
        frame.pack();
        frame.setResizable(false);
        canvas.createBufferStrategy(2);
        bufferStrategy = canvas.getBufferStrategy();
        Box.userID.requestFocus();
        Print.standard("Complete launchFrame !", "index.GUI.GUI.launchFrame", Print.INFO);
	}
	private static long desiredFPS = 0;  
	private static long desiredDeltaLoop = 0;
	private static boolean running = true;
    public final void run(){
        long beginLoopTime = 0;
        long endLoopTime = 0;
        long currentUpdateTime = System.nanoTime();
        long lastUpdateTime = 0;
        long deltaLoop = 0;
        while(running){
            beginLoopTime = System.nanoTime();
            render(); 
            lastUpdateTime = currentUpdateTime;
            currentUpdateTime = System.nanoTime();
            draw.update((int) ((currentUpdateTime - lastUpdateTime)/(1000*1000)));
            endLoopTime = System.nanoTime();
            deltaLoop = endLoopTime - beginLoopTime;
        	if(desiredDeltaLoop > deltaLoop)
                try{
                    Thread.sleep((desiredDeltaLoop - deltaLoop)/(1000*1000));
                }catch(InterruptedException e){}
    		}
    }
    private final void render(){ 
    	g = (Graphics2D) bufferStrategy.getDrawGraphics();
        g.clearRect(0, 0, Resources.w, Resources.h);
        draw.render(g, frame);
        g.dispose();
        bufferStrategy.show();
    }
    public static final void setFPS(long l){
    	desiredFPS = l;
    	desiredDeltaLoop = (1000*1000*1000) / desiredFPS;
    	if(ThreadNumber.running)FramePrintStream.getFps().setTitle("The execute permissions: Chief Executive Officer - Mickey   FPS: " + desiredFPS + "   Thread: " + ThreadNumber.map.size());
    	Print.standard("Now doing setFPS " + (int) l + " ... ", "index.GUI.GUI.setFPS", Print.INFO);
    }
    public static final long getFPS(){
		return desiredFPS;
    }
    static {
    	Print.standard("Now doing load ... ", "index.GUI.GUI", Print.INFO);
    	draw = getLoginDraw();
    	gf = new GUIframe();
		t = new Thread(gf);
		t.start();
		setFPS(60);
		frame.setVisible(true);
    }
}