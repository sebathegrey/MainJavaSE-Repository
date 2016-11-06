package check;

public class Order {

	public static boolean checkOrderList(int list[])
	{
		for(int i =0;i<list.length;i++)
		{
			if(list[i]!=i+1)
				return false;
		}
		return true;
	}
	
}
