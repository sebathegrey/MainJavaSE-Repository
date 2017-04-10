
public class ProdFib {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(productFib(4895));
		
		

	}
	public static long[] productFib(long prod) {
	    
		long[] tab = new long[3];
		int i=0;
		long now=0,bef=0,times=0;
		while(prod != times || prod < times){
			now=fibo(i);
			times=now*bef;
			bef=now;
		}
		
		if(times==prod) return new long[]{bef,now,1};
		else return new long[]{bef,now,0};

	   }
	
	public static long fibo(int n){
		if(n<2)
		return n;
		
		return fibo(n-1)+fibo(n-2);
	}
}
