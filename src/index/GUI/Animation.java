package index.GUI;

public abstract class Animation extends Thread{
	int frames;
	int currentframes;
	int allFrames;
	Animation(int frames, int allFrames){
		this.frames = frames;
		this.allFrames = allFrames;
	}
	public final int getAnimation(){
		try {
			sleep(1000 / frames);
		} catch(InterruptedException e) {
			e.printStackTrace();
		}
		currentframes++;
		return returnAnimation();	
	}
	public abstract int returnAnimation();
}