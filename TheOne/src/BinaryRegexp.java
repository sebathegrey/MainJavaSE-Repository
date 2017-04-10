import java.math.BigInteger;
import java.util.regex.Pattern;

public class BinaryRegexp {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(findIt(new int []{20,1,1,2,2,3,3,5,5,4,20,4,5}));
		
	}
  public static Pattern multipleOf3() {
    // Regular expression that matches binary inputs that are multiple of 3
    
    String s = "";

    
    int z = Integer.parseInt(s,2);
    
    return Pattern.compile("");
  }
	public static int findIt(int[] A) {
		  
		  int liczba;
		    for(int i=0; i<A.length; i++){
		      liczba=0;
		      for(int j=0;j<A.length;j++){
		        if(A[i]==A[j]){
		          liczba++;
		          }
		      
		      }
		      if(liczba%2!=0)
		        return A[i];
		    }
		  	return 0;
		  }
}