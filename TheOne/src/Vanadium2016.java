import java.util.Collections;
import java.util.Set;
import java.util.TreeSet;

public class Vanadium2016 {

	public static void main(String[] args) {
		int[] data1= {1,1,1,1,1,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,1,1,1,1,1,0,1,0,1,0,1,0};
	    int[] data2= {0,0,1,0,1,0,0,1,0,0,1,1,0,1,1,0};
		//check("02002020020200202002020020200202002020020200202002020020200202002020020200202002020020200202002020020200202002020020200202002020020200202002020020200202002020020200202002020020200202002020020200202002020020200202002020020200202002020020200202002020020200202002020020200202002020020200202002020020200202002020020200202002020020200202002020020200202002020020200202002020020200202002020020200202002020020200202002020020200202002020020200202002020020200202002020020200202002020020200202002020020200202002020020200202002020020200202002020020200202002020020200202002020020200202002020020200202002020020200202002020020200202002020020200202002020020200202002");
		DataReverse(data1);
	}

	
	public static void check(String s) throws NumberFormatException
{ //spawdza w pojedynczym ³añcuchu 
		int licz=0;
		for(int i=0;i<s.length();i++){
			for(int j=i+1;j<=s.length();j++){
				int []tab=new int[10];
				for(String str : s.substring(i, j).split("")){
					
					for(int z=0;z<10;z++){
					if(z==Integer.parseInt(str));
						tab[z]++;
					}
				}
				int temp1=0;
				for(int z=0;z<10;z++){
					
					if(tab[z]%2!=0)
						temp1++;
				}
				if(temp1<=1)
					licz++;
				//System.out.println(s.substring(i, j)+", ");
			}
		}
		System.out.println("Liczba przeskowów "+ (licz+s.length()) +" \nd³ugoœæ " +s.length());
		
	}

	  public static int[] DataReverse(int[] data) {
	    int []ret=new int[data.length];
	    int licz=0;
	    for(int j =data.length/8-1;j>0;j--){
	    
	    	for(int i=0;i<8;i++){
	    		ret[licz]=data[j*8+i];
	    		licz++;
	    	}
	    }
	    
	    return ret;
	  }

}
 class JadenCase {

	public String toJadenCase(String phrase) {
	
		StringBuilder strB=new StringBuilder();
		for(String str : phrase.split(" ")){
			strB.append(str.substring(0, 1).toUpperCase()+str.substring(1));
    }
		strB.length();
		return strB.toString();

	}

} class DescendingOrder {
	  public static int sortDesc(final int num) {
	    
	    Set<String> set=new TreeSet<String>(Collections.reverseOrder()){
	    	@Override
	    	public String toString(){
	    		String nowy=null;
	    		for(String str : this){
	    			nowy=nowy+str;
	    		};
	    		return nowy;
	    	}
	    	@Override 
	    	public boolean contains(Object o){
	    		return false;
	    	}
	    	@Override 
	    	public boolean equals(Object o){
	    		return false;
	    	}
	    	 
	    };
	
	    for(String in : Integer.toString(num).split("")){
	      set.add(in);
	    }
	   
	    return Integer.parseInt(set.toString());
	    
	    
	    
	  }
	}