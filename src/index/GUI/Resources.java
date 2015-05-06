package index.GUI;

import index.backstage.Print;

import java.awt.AlphaComposite;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

public final class Resources {
	static final class ImageContent {
		int x, y, w, h;
		public ImageContent(Image i, int x, int y){
			try{
				this.x = (int) (x * scaleW);
				this.y = (int) (y * scaleH);
				this.w = (int) (i.getWidth(null) * scaleW);
				this.h = (int) (i.getHeight(null) * scaleH);
			}catch(NullPointerException e){
				Print.standard("NullPointerException !", "index.GUI.Resources.ImageContent", Print.ERROR);
			}
		}
	}
	private Resources(){}
	static final double SRC_W = 1440.0, SRC_H = 900.0;
	static int w, h;
	static boolean scale, headDoing, identical;
	static String name;
	static Font wawaFont;
	static double scaleW, scaleH;
	static Cursor pointer;
	static Image i, iBuffer, head, icon, cloud, landingBox, stroke, point;
	static ImageContent cloudC, landingBoxC, strokeC, pointC;
	static ImageContent quitC, nextC;
	private static final void getImage(){
		Print.standard("Now doing getImage ... ", "index.GUI.Resources.getImage", Print.INFO);
		icon = getImage("icon.png");
		point = getImage("point.png");
		pointC = new ImageContent(point, 900, 470);
		cloud = getImage("cloud.png");
		cloudC = new ImageContent(cloud, 0, 0);
		landingBox = getImage("landingBox.png");
		landingBoxC = new ImageContent(landingBox, 406, 217);
		stroke = getImage("stroke.png");
		strokeC = new ImageContent(stroke, 659, 299);
		Print.standard("Complete getImage !", "index.GUI.Resources.getImage", Print.INFO);
	}
	private static final void getImageScale(){
		getImage();
		Print.standard("Now doing ScaleImage(ScreenSize != 1440 * 900) ... ", "index.GUI.Resources.getImageScale", Print.INFO);
		point = point.getScaledInstance(pointC.w, pointC.h, Image.SCALE_SMOOTH);
		cloud = cloud.getScaledInstance(cloudC.w, cloudC.h, Image.SCALE_SMOOTH);
		landingBox = landingBox.getScaledInstance(landingBoxC.w, landingBoxC.h, Image.SCALE_SMOOTH);
		stroke = stroke.getScaledInstance(strokeC.w, strokeC.h, Image.SCALE_SMOOTH);
		Print.standard("Complete ScaleImage !", "index.GUI.Resources.getImageScale", Print.INFO);
	}
	private static final Image getImage(String fileName){
		try {
			return ImageIO.read(Resources.class.getResource("/resources/" + fileName));
		} catch(IOException | IllegalArgumentException e) {
			Print.standard("Resource files are missing: "+fileName, "index.GUI.Resources.getImage", Print.ERROR);
			return null;
		}
	}
	private static final void getButtonScale(){
		Print.standard("Now doing getButtonScale ... ", "index.GUI.Resources.getButtonScale", Print.INFO);
		quitC = new ImageContent(getImage("quit0.png"), 568, 560);
		DimorphismButton.quit = new DimorphismButton(getImage("quit0.png").getScaledInstance(quitC.w, quitC.h, Image.SCALE_SMOOTH), getImage("quit1.png").getScaledInstance(quitC.w, quitC.h, Image.SCALE_SMOOTH), quitC.x, quitC.y, quitC.w, quitC.h);
		nextC = new ImageContent(getImage("next0.png"), 760, 560);
		DimorphismButton.next = new DimorphismButton(getImage("next0.png").getScaledInstance(nextC.w, nextC.h, Image.SCALE_SMOOTH), getImage("next1.png").getScaledInstance(nextC.w, nextC.h, Image.SCALE_SMOOTH), nextC.x, nextC.y, nextC.w, nextC.h);
		Print.standard("Complete getButtonScale !", "index.GUI.Resources.getButtonScale", Print.INFO);
	}
	private static final void getButton(){
		Print.standard("Now doing getButton ... ", "index.GUI.Resources.getButton", Print.INFO);
		quitC = new ImageContent(getImage("quit0.png"), 568, 560);
		DimorphismButton.quit = new DimorphismButton(getImage("quit0.png"), getImage("quit1.png"), quitC.x, quitC.y, quitC.w, quitC.h);
		nextC = new ImageContent(getImage("next0.png"), 760, 560);
		DimorphismButton.next = new DimorphismButton(getImage("next0.png"), getImage("next1.png"), nextC.x, nextC.y, nextC.w, nextC.h);
		Print.standard("Complete getButton !", "index.GUI.Resources.getButton", Print.INFO);
	}
	private static final void getFont(){  
    	Print.standard("Now doing getFont ... ", "index.GUI.Resources.getFont", Print.INFO);
        InputStream is = null;
        BufferedInputStream bis = null;  
        try {
            is = Resources.class.getResourceAsStream("/font/wawa.ttf");
            bis = new BufferedInputStream(is);
            wawaFont = Font.createFont(Font.TRUETYPE_FONT, bis).deriveFont(24.0f);
            is.close();
            bis.close();
        } catch (FontFormatException | IOException e) {
        	Print.standard("Resource files are missing: wawa.ttf", "index.GUI.Resources.getFont", Print.ERROR);
        }
        Print.standard("Complete getFont !", "index.GUI.Resources.getFont", Print.INFO);
    }
	private static final Image getHead(String fileName){
		try {
			return ImageIO.read(Resources.class.getResource("/resources/" + fileName));
		} catch(IOException | IllegalArgumentException e) {
			Print.standard("Resource files are missing: "+fileName, "index.GUI.Resources.getImage", Print.ERROR);
			return null;
		}
	}
	static final void getHead(String userID, boolean b){
		if(name != userID){
			head = i;
			boolean l = false;
			BufferedImage temp;
			Graphics2D g;
			if(i == iBuffer)identical = true;
			else identical = false;
			if((i = getHead(userID)) == null){
				i = iBuffer;
			}
			if(i == iBuffer){
				l = true;
				if(identical)identical = true;
			} else identical = false;
			if(!identical){
				headDoing = true;
				if(!b && !l){
					temp = new BufferedImage(strokeC.w, strokeC.h, BufferedImage.TYPE_INT_ARGB);
			        g = temp.createGraphics();
			        g.drawImage(i, 1, 1, strokeC.w - 2, strokeC.h - 2, null);
			        g.drawImage(stroke, 0, 0, strokeC.w, strokeC.h, null);
			        i = temp;
			        g.dispose();
				}
				temp = new BufferedImage(head.getWidth(null), head.getHeight(null), BufferedImage.TYPE_INT_ARGB);
		        g = temp.createGraphics();
		        for(float f = 1.0f; f > 0.0f; f -= 0.02f){
		        	g.drawImage(head, 0, 0, null);
		        	g.setComposite(AlphaComposite.getInstance(AlphaComposite.DST_ATOP, f));
				    head = temp;
				    try {
				    	Thread.sleep(1000 / GUI.getFPS());
				    } catch (InterruptedException e) {
				    	e.printStackTrace();
				    }
			    }
		        g.dispose();
		        headDoing = false;
			}
			name = userID;
		}
    }
	static {
		Print.standard("Now doing load ... ", "index.GUI.Resources", Print.INFO);
		Print.standard("Now doing getResources ... ", "index.GUI.Resources.getResources", Print.INFO);
		name = "null.png";
		w = Tool.getSW();
		h = Tool.getSH();
		scaleW = w / SRC_W;
		scaleH = h / SRC_H;
		scale = scaleW * scaleH != 1;
		if(scale){
			getImageScale();
			getButtonScale();
		}
		else {
			getImage();
			getButton();
		}
		i = getImage("null.png");
		iBuffer = i;
		getFont();
		DimorphismButton.setEvent();
		Print.standard("Complete getResources !", "index.GUI.Resources.getResources", Print.INFO);
		Print.standardGC("index.GUI.Resources.getResources");
	}
}