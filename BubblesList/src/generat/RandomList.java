package generat;


public class RandomList {

	private int tab[];
	
	public void printList() //drukuje listê klasy RandomList
	{
	for(int i=0;i<tab.length;i++)
		System.out.print(tab[i]+" ");
	System.out.println();
	}
	
	public void printList(int tabL[]) //drukuje listê dowolna
	{
	for(int i=0;i<tabL.length;i++)
		System.out.print(tabL[i]+" ");
	System.out.println();
	}
	
	private boolean checkNumInList(int num)//sprawdza w liscie czy dana liczba by³a juz wylosowana na podstawie tej liczby,tabeli
	{													//zwraca true jeœli jest uzyta
		for(int i=0;i<tab.length;i++)
			if(tab[i]==num)
			{
				return true;
			}
		return false;
	}
	
	public int[] generatRandomList(int n, int z)   ///generuje tablice d³ugoœci n znaków z przedzia³u z, z>=n musi byc
	{
		 tab=new int[n];
		
		for(int i = 0;i<n;i++)
		{
			int num;
			do
			{
				num=(int) (Math.random()*z)+1;
			}	while(this.checkNumInList(num));
			tab[i]=num;
		}
		return tab;
	}
	
	public int[] generatRandomList(int z)
	{
		return this.generatRandomList(z,z);
	}
	
	public void printListAsTab(int []list)   // liste zamienia na tabele znaków, odpowiada to osiom i>y j>x
	{
		char tab1[][]=new char[list.length][list.length];
			for(int i=0;i<list.length;i++)
			{
				for(int j=0;j<list.length;j++)
				{
					if(i==list[j])
						tab1[i][j]='#';
					else tab1[i][j]='-';
				}
			}
		this.printTab(tab1);
		//return tab1;
	}
	
	private void printTab(char[][]tab1)   // drukuje tabele znaków 
	{
		for(int i=0;i<tab1.length;i++)
			{
			for(int j=0;j<tab1.length;j++)
				System.out.print(" "+tab1[i][j]+" ");
			System.out.println();
			}
		System.out.println();
		for(int i=0;i<tab1.length;i++)
			System.out.print("---");
		System.out.println();

	}
}