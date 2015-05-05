package text;

import java.math.BigInteger;

public class c {
	public static void main(String[] a){
		System.out.println("本机完成计算大约需要:" + Math.pow(2,303) / (3600 * 24 * 365) + "年");
		/*BigInteger i = BigInteger.valueOf(2);
		BigInteger t = BigInteger.valueOf(2);
		BigInteger d = BigInteger.ZERO;
		BigInteger c = new BigInteger("179769313486231590772930519078902473361797697894230657273430081157732675805500963132708477322407536021120113879871393357658789768814416622492847430639474124377767893424865485276302219601246094119453082952085005768838150682342462881473913110540827237163350510684586298239947245938479716304835356329624224137216");
		while(!d.equals(c)){
			i = i.multiply(t);
			d = d.add(BigInteger.ONE);
			System.out.println(i);
		}*/

		
		/*BigInteger i = BigInteger.valueOf(4);
		BigInteger t = BigInteger.valueOf(2);
		BigInteger d = BigInteger.valueOf(0);
		BigInteger y = BigInteger.valueOf(1);
		BigInteger c = new BigInteger("1024");
		while(!d.equals(c)){
			i = i.multiply(t);
			d = d.add(y);
			System.out.println(d);
		}
		System.out.println(i);*/
	}
}
