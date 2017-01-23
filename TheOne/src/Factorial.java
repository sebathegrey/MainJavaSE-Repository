
public class Factorial {

	public static void main(String[] args) {

		System.out.println(fact(18));
	}
	public static int fact(int num){
		if(num==0){
			return 1;
		}else return fact2(num,1);
	}
	public static int fact2(int fnum , int sum){
		int temp=0;
		temp=sum*fnum;
		if(fnum>1)
			return fact2(fnum-1,temp);
		return temp;
	}
}