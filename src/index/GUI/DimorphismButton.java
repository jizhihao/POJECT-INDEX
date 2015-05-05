package index.GUI;

import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;

@SuppressWarnings("serial")
public final class DimorphismButton extends JButton {
	static DimorphismButton next, quit;
	ImageIcon s0, s1;
	DimorphismButton(Image s0, Image s1, int x, int y, int w, int h){
		super();
		this.s0 = new ImageIcon(s0);
		this.s1 = new ImageIcon(s1);
		setIcon(this.s0);
		setBounds(x, y, w, h);
		setContentAreaFilled(false);
		setBorder(null);
		setVisible(true);
	}
	public static final void setEvent(){
		next.addMouseListener(new MouseAdapter(){
			public final void mousePressed(MouseEvent e) {
				next.setIcon(next.s1);
			}
			public final void mouseReleased(MouseEvent e) {
				next.setIcon(next.s0);
			}
		});
		quit.addMouseListener(new MouseAdapter(){
			public final void mousePressed(MouseEvent e) {
				quit.setIcon(quit.s1);
			}
			public final void mouseReleased(MouseEvent e) {
				quit.setIcon(quit.s0);
			}
			public final void mouseClicked(MouseEvent e) {
				System.exit(0);
			}
		});
	}
}