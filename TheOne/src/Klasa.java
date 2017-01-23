import java.math.BigInteger;

public class Klasa {

	public static void main (String []ar){
		int []a=new int[]{1,1,-1,11,1,-2,100,-1,11,-3,1,1,11,1,-3,100,1,1,-1,11,1,-2,100,-1,11,-3,1,1,11,1,-3,100,1,1,-1,11,1,-2,100,-1,11,-3,1,1,11,1,-3,100,1,1,-1,11,1,-2,100,-1,11,-3,1,1,11,1,-3,100,1,1,-1,11,1,-2,100,-1,11,-3,1,1,11,1,-3,100};
		System.out.println(solution(a));
	}
	 public static int solution(int[] S) {
	     
		 int max_sum = 0;
	      int current_sum = 0;
	      boolean positive = false;
	      int n = S.length;
	      for (int i = 0; i < n; ++i) {
	          int item = S[i];
	          if (item <0) {
	                if (max_sum <= current_sum) {
	                    max_sum = current_sum;}
	                    current_sum = 0;
	                
	          } else {
	                positive = true;
	                current_sum += item;
	          }
	      }
	      if (max_sum < current_sum) {
	          max_sum = current_sum;
	      }
	      if (positive) {
	          return max_sum;
	      }
	      return -1;
	    }
	 
}
