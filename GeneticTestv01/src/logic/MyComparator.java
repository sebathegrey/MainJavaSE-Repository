package logic;
import java.util.Comparator;
import java.util.Set;
import logic.MyPoint;

public class MyComparator implements Comparator<Set<MyPoint>>{

	@Override
    public int compare(Set<MyPoint> row1,Set<MyPoint> row2) {
		Integer sum1=0,sum2=0;
		MyPoint temp=null;
		for(MyPoint r :row1){
			if(temp==null)
				temp=r;
			else{
				sum1+=temp.getDistans(r);
				temp=r;
			}
		}
		temp=null;
		for(MyPoint r :row2){
			if(temp==null)
				temp=r;
			else{
				sum2+=temp.getDistans(r);
				temp=r;
			}
		}
		if(sum1<sum2)
			return -1;
		else if(sum1>sum2) return 1;
		else return 0;
    }
}
