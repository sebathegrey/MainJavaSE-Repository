
public class Binary {

	public static void main(String[] args) {
		// TODO Auto-generated method stub


	 String maxAmpStr = Integer.toBinaryString(15);
	    char[] arr = maxAmpStr.toCharArray();
	    boolean[] binaryarray = new boolean[10];
	    for (int i=0; i<maxAmpStr.length(); i++){
	        if (arr[i] == '1'){             
	            binaryarray[i+(binaryarray.length-arr.length)] = true;  
	        }
	        else if (arr[i] == '0'){
	            binaryarray[i+(binaryarray.length-arr.length)] = false; 
	        }
	    }

	    System.out.println(maxAmpStr);

	    System.out.println(binaryarray[0]);
	    System.out.println(binaryarray[1]);
	    System.out.println(binaryarray[2]);
	    System.out.println(binaryarray[3]);
	    System.out.println(binaryarray[4]);
	    System.out.println(binaryarray[5]);
	    System.out.println(binaryarray[6]);
	    System.out.println(binaryarray[7]);
	    System.out.println(binaryarray[8]);
	    System.out.println(binaryarray[9]);


	}
}