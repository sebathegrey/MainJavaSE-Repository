package implementAlgorithm;

public class ApplyAlgorithm //klasa pobiera listê niepokolejn¹, porz¹dkuje i zwraca
{
	public static int licz;
	private int []listMess;

	public ApplyAlgorithm(int []listMess) //pobranie listy przez konstruktor
	{
		setListMess(listMess);
		licz=0;
	}
	
	public int[] sortByBubbles()  //algorytm sortowania b¹belkowego dla listy, zwraca posortowan¹ tabele
	{
		for(int i=0;i<listMess.length-1;i++)
		{
			if(listMess[i]>listMess[i+1])
			{
				int tmp=listMess[i+1];
				listMess[i+1]=listMess[i];
				listMess[i]=tmp;
				licz++;
			}
		}
		return this.getListMess();
	}
	
	
	public int [] getListMess() {
		return listMess;
	}

	public void setListMess(int [] listMess) {
		this.listMess = listMess;
	}

}
