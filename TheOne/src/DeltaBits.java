
public class DeltaBits {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(convertBits(31, 125));
		System.out.println(convertBits2(31, 125));
		System.out.println(convertBits3(31, 125));
	}

	public static int convertBits(int a, int b) {

		String stA = String.format("%16s", Integer.toBinaryString(a)).replace(' ', '0');
		String stB = String.format("%16s", Integer.toBinaryString(b)).replace(' ', '0');
		int diff = 0;

		for (int i = 0; (i < stA.length()) || (i < stB.length()); i++) {

			if (stA.charAt(i) != stB.charAt(i)) {
				diff++;

			}
		}
		return diff;
	}
	public static int convertBits2(int a, int b) {
		
		int dif = a^b;
		System.out.println(dif);
		return Integer.bitCount(a ^ b);
		

	}
	
public static int convertBits3(int a, int b) {
		
		int dif = b-a;
		System.out.println(dif);
		return Integer.bitCount(dif);
		

	}
}
