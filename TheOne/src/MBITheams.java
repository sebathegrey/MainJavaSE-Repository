import java.util.LinkedHashSet;
import java.util.Random;
import java.util.Set;

public class MBITheams {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(done());
	}

	public static String done(){
		
		Set<Integer>result = new LinkedHashSet<>();
		Random r= new Random();
		
		while(result.size()<5){
			int i = r.nextInt(6)+1;
			if(i == 5);
			else result.add(i);
			
			
		}
		
		return result.toString();
		
	}
}
