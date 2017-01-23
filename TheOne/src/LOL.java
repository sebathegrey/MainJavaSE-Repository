
public class LOL {

	public static void main(String[] args) {
		System.out.println(findNb(2022));
	
	}
	public static long findNb(long m) {
		double sum=0;
    for(int i=0;  i<=2022;i++){
        long z=0;
        sum= sum + Math.pow(i, 3);
        z++;
    }
   
    return (long) sum;
	}	
}
