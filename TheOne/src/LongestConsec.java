import java.math.BigInteger;
import java.util.LinkedList;
import java.util.List;

public class LongestConsec {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//System.out.println(longestConsec(new String[] {"zone", "abigail", "theta", "form", "libe", "zas", "theta", "abigail"}, 1));
		System.out.println(jS(11,19));
	}
	public static String longestConsec(String[] strarr, int k) {
	     
		
		String wynik = "";
		for(int i =0;i<=strarr.length-k;i++){
			StringBuilder str = new StringBuilder();
			for(int j=0;j<k;j++){
				str.append(strarr[i+j]);
				
			}
			if(str.length()>wynik.length()){
				wynik = str.toString();
			}
			
		}
		return wynik;
	}
	  static String toCamelCase(String s){
		  
		  StringBuilder str = new StringBuilder();
		  for(String temp : s.split("[-\\_]")){
			  
			  str.append(temp.substring(0, 1).toUpperCase()+temp.substring(1));
		  }
		    return str.toString().substring(0, 1).toLowerCase()+str.toString().substring(1);
		  }
	  

		  public static BigInteger score (BigInteger n) {
			
			  return BigInteger.valueOf(2).pow(n.bitLength()).subtract(BigInteger.valueOf(1));
		    
		  }

		  public static int jS(final int n, final int k) {

			  List<Integer> bui = new LinkedList<>();
			  for(int i=1 ; i<=n ; i++){
				  bui.add(i);
			  }
			  
			  int j;
			  int aktualna=(k-1);
			  int licz= (k);
			  
			  while((j = (bui.size()))!=1){
				  
				
				if(aktualna < j){
				 
					if(licz%k == 0){
						bui.remove(aktualna);
						licz = 0;
						aktualna --;}
					}else {
						aktualna = 0;
						if(licz%k == 0){
							bui.remove(aktualna);
							licz = 0;
							aktualna --;}
							
						}
				aktualna++;
				licz++;
				 System.out.println(bui.toString());
			  }
			  
			  return bui.get(0);
		  }  

		  
		  
			  public int loopSize(Node node) {
			  
			    LinkedList<Node> li = new LinkedList<>();
			    boolean b=false;
			    int i =0;
			    while(b == true){
			      Node n =node.getNext();
			      
			      for(Node n2 : li){ 
			    	  i++;
			    	  if(n2.equals(n))
			          b = true;
			        else {b = false;
			        i=0;
			        }
			      }
			      li.add(node.getNext());
			    }
			  
			    return li.size()-i+1;
			  }

			
}

class Node{
	
public Node getNext(){
	
	return null;
}

}
