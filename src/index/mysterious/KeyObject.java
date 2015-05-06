package index.mysterious;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

public final class KeyObject {
	int[] a = new int[8192];
	int[] b = new int[8192];
	int[] c = new int[8192];
	int[] d = new int[8192];
	int[] e = new int[8192];
	int[] f = new int[8192];
	public final static void main(String[] args){
		KeyObject key = new KeyObject();
		for(int i = 0; i < 8192; i++){
			key.a[i] = (int) (Math.random() * 0x1f) + 1;
			key.b[i] = (int) (Math.random() * 0x1f) + 1;
			key.c[i] = (int) (Math.random() * 0x1f) + 1;
			key.d[i] = (int) (Math.random() * 0x1f) + 1;
			key.e[i] = (int) (Math.random() * 0x1f) + 1;
			key.f[i] = (int) (Math.random() * 0x1f) + 1;
		}
		FileOutputStream fos = null;
		ObjectOutputStream oos = null;
	    try {
	    	fos = new FileOutputStream("Prefix");
            oos = new ObjectOutputStream(fos);                   
            oos.writeObject("Mickey");
            oos.close();    
            fos = new FileOutputStream("a.key");
            oos = new ObjectOutputStream(fos);                   
            oos.writeObject(key.a);
            oos.close(); 
            fos = new FileOutputStream("b.key");
            oos = new ObjectOutputStream(fos);                   
            oos.writeObject(key.b);
            oos.close();    
            fos = new FileOutputStream("c.key");
            oos = new ObjectOutputStream(fos);                   
            oos.writeObject(key.c);
            oos.close();    
            fos = new FileOutputStream("d.key");
            oos = new ObjectOutputStream(fos);                   
            oos.writeObject(key.d);
            oos.close();    
            fos = new FileOutputStream("e.key");
            oos = new ObjectOutputStream(fos);                   
            oos.writeObject(key.e);
            oos.close();    
            fos = new FileOutputStream("f.key");
            oos = new ObjectOutputStream(fos);                   
            oos.writeObject(key.f);
            oos.close();    
	    } catch (Exception ex) {  ex.printStackTrace();   }
	}
}
