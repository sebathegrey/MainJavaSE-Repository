public class LongestSubsequences {
	
	public static void main (String [] ar){
		
		System.out.println(lcs("fffinalsss","ffinallss"));
		
	}
	
    public static String lcs(String x, String y) {
    	if(x.equals("") ||y.equals("") ||x==null ||y==null)
            return "";
    	  if(x.equals(y))
              return x;
    	  
         StringBuilder last = new StringBuilder();
         StringBuilder answer = new StringBuilder();
        
         String ys=y;
        for(int j = 0; j < x.length();){
        	char temp = x.charAt(j);
        	j++;
        		for(int z = 0 ; z < ys.length(); z++){
        			char temp2 = ys.charAt(z);
        			if(temp == temp2){
        				answer.append(temp);
        				ys=ys.substring(z+1);
        				//j--;
        				break;
        			}
        		}
        }
        
        return answer.append(last.toString()).toString();
  }
}