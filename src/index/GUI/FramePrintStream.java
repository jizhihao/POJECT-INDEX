package index.GUI;

import index.Backstage.Print;
import index.Backstage.ThreadNumber;

import java.awt.Font;
import java.awt.FontFormatException;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;
import javax.swing.text.JTextComponent;

@SuppressWarnings("serial")
public final class FramePrintStream extends JFrame {
	final class GUIPrintStream extends PrintStream {  
	    private JTextComponent component;  
	    private StringBuffer sb = new StringBuffer();  
	    private GUIPrintStream(OutputStream out, JTextComponent component){  
	        super(out);  
	        this.component = component;  
	    }   
	    public void write(byte[] buf, int off, int len) {  
	        final String message = new String(buf, off, len);   
	        SwingUtilities.invokeLater(new Runnable(){  
	            public void run(){  
	                sb.append(message);  
	                component.setText(sb.toString());  
	            }  
	        });  
	    }  
	}
	private static Font Inconsolata = getInconsolata();
	private static FramePrintStream fps = new FramePrintStream();
    private static JTextArea textArea;
    private static JScrollPane scrollPane;
    private static GUIPrintStream guiPrintStream;
    private FramePrintStream(){  
        initComponents();
        guiPrintStream = new GUIPrintStream(System.out, textArea);
        System.setOut(guiPrintStream);
        System.setErr(guiPrintStream);
    }  
    private final void initComponents(){
        scrollPane = new JScrollPane();
        textArea = new JTextArea();
        textArea.setEditable(false);
        textArea.setFont(Inconsolata);
        scrollPane.setViewportView(textArea);
        setAlwaysOnTop(true);
        setBounds(20,20,600,400);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);  
        setTitle("The execute permissions: Chief Executive Officer - Mickey");
        getContentPane().add(scrollPane, java.awt.BorderLayout.CENTER);  
    }
    private static final Font getInconsolata(){
        try {
        	InputStream is = null;
            BufferedInputStream bis = null;
            is = FramePrintStream.class.getResourceAsStream("/font/Inconsolata.otf");  
            bis = new BufferedInputStream(is);  
            Inconsolata = Font.createFont(Font.TRUETYPE_FONT, bis).deriveFont((float) 14.0);
            is.close();
            bis.close();
        } catch (FontFormatException | IOException e) {
        	Print.standard("Resource files are missing: Inconsolata.otf", "GUI.FramePrintStream.getInconsolata", Print.ERROR);
        }
        return Inconsolata;
    }
    public static final FramePrintStream getFps(){
		return fps;
    }
    static {
    	fps.setVisible(true);
    	Print.standard("Success link background !", "GUI.FramePrintStream", Print.INFO);
    	try {
			Class.forName("index.Backstage.ThreadNumber");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
    	ThreadNumber.running = true;
		new ThreadNumber().start();
    }
}