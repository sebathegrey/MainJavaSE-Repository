
import java.util.ArrayList;
import java.util.List;

public class Fibonacci {
	
	public static void main(String[] a){
		
		System.out.println(mean("London",     "London:Jan 48.0,Feb 38.9,Mar 39.9,Apr 42.2,May 47.3,Jun 52.1,Jul 59.5,Aug 57.2,Sep 55.4,Oct 62.0,Nov 59.0,Dec 52.9" ));
		System.out.println(variance("London",     "London:Jan 48.0,Feb 38.9,Mar 39.9,Apr 42.2,May 47.3,Jun 52.1,Jul 59.5,Aug 57.2,Sep 55.4,Oct 62.0,Nov 59.0,Dec 52.9" ));

/*		int n = 6;
        for (int i = 0; i <= n; i++)
            System.out.println(i + ": " + fibonacci(i));
		
		*/
	}
	
	public static List<Integer> fibonacci(int n) {
        if (n <= 1) return n;
        else return fibonacci(n-1) + fibonacci(n-2);
    }
	public int Ackermann(int m,int n){
	    
	    if(m==0){
	    return  n++;
	    }
	    if(m>0 && n==0){
	    return Ackermann(m-1,1);
	    }
	    else if(m>0 && n>0){
	    return Ackermann(m-1,Ackermann(m,n-1));
	    }
	    else return 0;
	  }
    //"Rome:Jan 90.2,Feb 73.2,Mar 80.3,Apr 55.7,May 53.0,Jun 36.4,Jul 17.5,Aug 27.5,Sep 60.9,Oct 147.7,Nov 121.0,Dec 97.9" +
    //"London:Jan 58.0,Feb 38.9,Mar 49.9,Apr 42.2,May 67.3,Jun 52.1,Jul 59.5,Aug 77.2,Sep 55.4,Oct 62.0,Nov 69.0,Dec 52.9" +

	public static double mean(String town, String strng) {
        
		double suma=0,ilosc=0;
		
		for(String pierwszy : strng.split("/n")){
			if(town.equals(pierwszy.substring(0, pierwszy.indexOf(':')))){
				
				for(String drugi : pierwszy.substring(pierwszy.indexOf(':')).split(",")){
					ilosc++;
					suma+=Double.parseDouble(drugi.split(" ")[1]);
					
				}
			}else
			return -1;
		}
		return suma/ilosc;		

    }
    public static double variance(String town, String strng) {
		double suma=0,ilosc=0;
		ArrayList<Double> arr = new ArrayList<>();
		arr.is
		for(String pierwszy : strng.split("/n")){
			if(town.equals(pierwszy.substring(0, pierwszy.indexOf(':')))){
				
				for(String drugi : pierwszy.substring(pierwszy.indexOf(':')).split(",")){
					ilosc++;
					double d=Double.parseDouble(drugi.split(" ")[1]);
					arr.add(d);
					suma+=d;
					
				}
			}else return -1;
		}
		double war=0;
		for(Double db : arr){
			
			war += Math.pow(db-(suma/ilosc), 2.0);
			
		}
		List<Integer> s = new ArrayList<>();
		war = war/(ilosc);
		System.out.println(ilosc);
		return war;
    }
    static List<Integer> list = new ArrayList<>();

	public static List<Integer> sqInRect(int lng, int wdth) {
		
	     List<Integer> list = new ArrayList<>();

	    
	    if(lng == wdth)
			  {if(list.isEmpty()) return null;
	          else return list;}
	          
	    int i=0,mod=1;
	    
	    if( lng > wdth ){
	        while(mod!=0){
	        i = lng / wdth;
	        mod = lng % wdth;
	        for(int j=0;j<i;j++)
	          list.add(wdth);
	        lng=wdth;
	        wdth=mod;
	          }
	         return list;
	        
	      }
	     if( lng < wdth ){
	        i =  wdth/lng ;
	        mod = wdth % lng;
	        for(int j=0;j<i;j++)
	          list.add(lng);
	        wdth=lng;
	        lng=mod;
	          }
	         return list;
	      

	      return null;
		}

}
