package index.backstage;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public final class Print {
	static Date d;
	static DateFormat sdf = new SimpleDateFormat("[HH:mm:ss]"); 
	public static final int INFO = 0, WARNING = 1, ERROR = 2, GROSS_ERROR = 3;
	public static final void standard(String str,String name, int i){
		d = new Date();
		System.out.println(sdf.format(d) + "<" + (i == 1 ? "WARNING" : i == 2 ? "ERROR" : i == 3 ? "GROSS_ERROR" : "INFO") + ">" + name + "\n--->   " + str);
	}
	public static final void standardGC(String name){
		d = new Date();
		System.gc();
		System.out.println(sdf.format(d) + "<INFO>" + name + "\n--->   System.gc !");
	}
}