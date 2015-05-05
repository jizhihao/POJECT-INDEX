package index.GUI;

import java.awt.Color;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JPasswordField;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public final class Box extends JTextField implements FocusListener {
	private static final class PasswordBox extends JPasswordField implements FocusListener {
		private PasswordBox(){
			super();
			setBackground(new Color(117, 228, 251));
			setBorder(null);
			setForeground(Color.WHITE);
			setCaretColor(Color.WHITE);
			addFocusListener(this);
		}
		public final void focusGained(FocusEvent e){
			visibility = true;
			if(!state){
				while(ani.currentframes <= ani.allFrames){
					ani.getAnimation();
					Resources.pointC.y += 4;
				}
				ani.currentframes = 0;
				state = true;
			}
		}
		public final void focusLost(FocusEvent e){
			visibility = false;
		}
	}
	static Box userID;
	static PasswordBox password;
	static boolean state, doing, visibility;
	static String userIDBuffer = "";
	static Animation ani = new Animation(60, 12){
		public int returnAnimation(){
			return 0;
		}
	};
	private Box(){
		super();
		setBackground(new Color(117, 228, 251));
		setBorder(null);
		setForeground(Color.WHITE);
		setCaretColor(Color.WHITE);
		addFocusListener(this);
	}
	public final void focusGained(FocusEvent e){
		visibility = true;
		if(state){
			while(ani.currentframes <= ani.allFrames){
				ani.getAnimation();
				Resources.pointC.y -= 4;
			}
			ani.currentframes = 0;
			state = false;
		}
	}
	public final void focusLost(FocusEvent e){
		visibility = false;
		new Thread(){
			public void run(){
				if(userID.getText() != userIDBuffer){
					if(userID.getText().equals(""))Resources.getHead("null.png", true);
					else Resources.getHead(userID.getText() + ".png", false);
					userIDBuffer = userID.getText();
				}
			}
		}.start();
	}
	static {
		userID = new Box();
		userID.setBounds((int) (618 * Resources.scaleW), (int) (466 * Resources.scaleH), (int) (270 * Resources.scaleW), (int) (24 * Resources.scaleH));
		userID.setFont(Resources.wawaFont);
		password = new PasswordBox();
		password.setBounds((int) (618 * Resources.scaleW), (int) (515 * Resources.scaleH), (int) (270 * Resources.scaleW), (int) (24 * Resources.scaleH));
		password.setFont(Resources.wawaFont);
	}
}