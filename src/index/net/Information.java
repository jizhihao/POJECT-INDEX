package index.net;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@SuppressWarnings("serial")
public class Information implements Serializable {
	transient static Date d;
	transient static DateFormat sdf = new SimpleDateFormat("[HH:mm:ss]"); 
	public String time;
	Information(){
		d = new Date();
		this.time = sdf.format(d);
	}
}