
public class Ball {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(maxBall(25));
	}
	
	public static int maxBall(int z){
		
		double v = z;
		v=v*(1000.0/3600.0);
		
		double h=0.0,temp=0.0;
		int i=0;
		
		do{
			if(h>temp)
				temp = h;
			
			h=(double)(v*i)*0.1-0.005*9.81*(double)(i*i);
			i++;
			
			
			
		}while(h>=temp);
		
		return i-2;
		
	}
}
