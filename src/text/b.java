package text;

import java.io.IOException;
import java.util.Enumeration;
import java.util.Properties;

public class b {
    public static void main(String[] args) {  
        // TODO Auto-generated method stub  
        Properties sp = System.getProperties();  
        Enumeration<?>  e = sp.propertyNames();//���������б������м���ö��  
        while(e.hasMoreElements()){  
            String key = (String)e.nextElement();  
            System.out.println(key+"="+sp.getProperty(key));  
        }  
          
        Process p = null;  
        try {  
            p = Runtime.getRuntime().exec("GifCam-4.5.CHS.exe");//TestProperties.java�ļ�Ҫ����TestProperties�ļ��и�Ŀ¼��  
        } catch (IOException e1) {  
            // TODO Auto-generated catch block  
            e1.printStackTrace();  
        }  
        try {  
            Thread.sleep(5000);  
        } catch (InterruptedException e1) {  
            // TODO Auto-generated catch block  
            e1.printStackTrace();  
        }  
        p.destroy();  
    }  
}
