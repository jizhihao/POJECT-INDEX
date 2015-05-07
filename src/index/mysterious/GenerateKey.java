package index.mysterious;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public final class GenerateKey {
	public final static String generateKey(String strkey){
		int[] a = null;
		int[] b = null;
		int[] c = null;
		int[] d = null;
		int[] e = null;
		int[] f = null;
		String str = null;
		StringBuffer sb = new StringBuffer();
		FileInputStream fis = null;
		ObjectInputStream ois = null;
        try {
        	fis = new FileInputStream("Prefix");
        	ois = new ObjectInputStream(fis);
        	str = (String) ois.readObject() + strkey;
        	fis.close();
        	ois.close();
        	fis = new FileInputStream("a.key");
        	ois = new ObjectInputStream(fis);
        	a = (int[]) ois.readObject();
        	fis.close();
        	ois.close();
        	fis = new FileInputStream("b.key");
        	ois = new ObjectInputStream(fis);
        	b = (int[]) ois.readObject();
        	fis.close();
        	ois.close();
        	fis = new FileInputStream("c.key");
        	ois = new ObjectInputStream(fis);
        	c = (int[]) ois.readObject();
        	fis.close();
        	ois.close();
        	fis = new FileInputStream("d.key");
        	ois = new ObjectInputStream(fis);
        	d = (int[]) ois.readObject();
        	fis.close();
        	ois.close();
        	fis = new FileInputStream("e.key");
        	ois = new ObjectInputStream(fis);
        	e = (int[]) ois.readObject();
        	fis.close();
        	ois.close();
        	fis = new FileInputStream("f.key");
        	ois = new ObjectInputStream(fis);
        	f = (int[]) ois.readObject();
        	fis.close();
        	ois.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        str = toUnicode(str);
        long temp;
        int length = str.length();
        int tempIndex1, tempIndex2, tempIndex3, tempIndex4, tempIndex5, tempIndex6;
		for(int i = 0; i < length - 6; i++){
			temp = Long.valueOf(str.charAt(i + 6));
			tempIndex1 = Integer.valueOf(str.charAt(length - (i + 6)));
			tempIndex2 = Integer.valueOf(str.charAt(length - (i + 5)));
			tempIndex3 = Integer.valueOf(str.charAt(length - (i + 4)));
			tempIndex4 = Integer.valueOf(str.charAt(length - (i + 3)));
			tempIndex5 = Integer.valueOf(str.charAt(length - (i + 2)));
			tempIndex6 = Integer.valueOf(str.charAt(length - (i + 1)));
			for(int t = 0; t < 8192; t++){
				if(temp > e[t]){
					temp += Math.abs(a[t] - tempIndex1) + (tempIndex5 + tempIndex4) / 2;
				} else{
					temp -= Math.abs(b[t] - tempIndex2) + (tempIndex6 + tempIndex3) / 2;
				}
				if(temp < Math.pow(f[t], t)){
					temp *= Math.abs(c[t] - tempIndex3) + (tempIndex5 + tempIndex2) / 2;
				} else{
					temp /= Math.abs(d[t] - tempIndex4) + (tempIndex6 + tempIndex1) / 2;
				}
			}
			sb.append(Long.toHexString(temp));
		}
		System.gc();
		return MD5(sb.toString());
	}
	private final static String toUnicode(String str) {
		str = (str == null ? "" : str);	
		StringBuffer sb = new StringBuffer();
		char c;
		for (int i = 0; i < str.length(); i++) {
			String t;
			c = str.charAt(i);
			if ((t = Integer.toHexString(c >>> 8)).length() == 1)
				sb.append("0");
			sb.append(t);
			if ((t = Integer.toHexString(c & 0xFF)).length() == 1)
				sb.append("0");
			sb.append(t);
		}
		return sb.toString();
	}
    private static String MD5(String sourceStr) {
        String result = "";
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(sourceStr.getBytes());
            byte b[] = md.digest();
            int i;
            StringBuffer buf = new StringBuffer("");
            for (int offset = 0; offset < b.length; offset++) {
                i = b[offset];
                if (i < 0)
                    i += 256;
                if (i < 16)
                    buf.append("0");
                buf.append(Integer.toHexString(i));
            }
            result = buf.toString();
            //16 - buf.toString().substring(8, 24)
        } catch (NoSuchAlgorithmException e) {
            System.out.println(e);
        }
        return result;
    }
}