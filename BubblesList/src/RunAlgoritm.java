//import java.util.Scanner;
import generat.RandomList;
import check.Order;
import implementAlgorithm.ApplyAlgorithm;

public class RunAlgoritm {

	public static void main(String[] args) throws InterruptedException {
		/*RandomList al=new RandomList ();
		Scanner s=new Scanner (System.in);
		int scan;
		do
		{
			System.out.println("Wprowadz liczbê nie wieksza niz 50");
			scan=s.nextInt();
			System.out.println("Wynik: "+ Order.checkOrderList(al.generatTab(scan,scan)));
			al.printList();
		}	while(scan!=0);
		s.close();*/
		RandomList list=new RandomList();
		ApplyAlgorithm al=new ApplyAlgorithm(list.generatRandomList(10));
		int []listOrder;

		do
		{
			listOrder=al.sortByBubbles();
			//list.printList(listOrder);
			list.printListAsTab(listOrder);
			Thread.sleep(0);		
}
		while(!Order.checkOrderList(listOrder));
		System.out.println("Liczba iteracji :"+al.licz);
	}

}
