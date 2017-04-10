import java.math.BigInteger;
import java.util.Iterator;
import java.util.LinkedList;

public class PolishNotationCalk {
	
	public static String PLUS="+";
	public static String MINUS="-";
	public static String MULTI="*";
	public static String DIVIDE="/";

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		count("5 1 2 + 4 * + 3 - 4 /");
		int z;
		BigInteger big=new BigInteger(String.valueOf(1));
		big=big.multiply(new BigInteger(String.valueOf(1)));
		
	}
	
	public static int count(String s){
		
		LinkedList<String> set = new LinkedList<>();
		StringBuilder odp = new StringBuilder();
		int liczbaOtwaNaw = 0;
		
		for(String z : s.split("(?<=[+\\-\\*\\/])"))
			{set.add(z);System.out.println(z);}
		
		Iterator<String> itr = set.descendingIterator();
		
		while(itr.hasNext()){
			String temp = itr.next();
			
			
			
			if(temp.substring(temp.length()-1,temp.length()).equals(PLUS)||temp.substring(temp.length()-1,temp.length()).equals(MINUS)
					||temp.substring(temp.length()-1,temp.length()).equals(MULTI)||temp.substring(temp.length()-1,temp.length()).equals(DIVIDE)){
				
				odp.append(")"+temp);
				
				//System.out.println(temp.substring(temp.length()-1,temp.length()));

			}

		}
		System.out.println(odp.reverse());
		return 0;
	}
}
