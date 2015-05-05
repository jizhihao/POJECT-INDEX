package index.net;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@SuppressWarnings("serial")
public class Information implements Serializable {
	static Date d = new Date();
	static DateFormat sdf = new SimpleDateFormat("[HH:mm:ss]"); 
	String time, str;
	Information(String str){
		this.str = str;
		this.time = sdf.format(d);
	}
}
