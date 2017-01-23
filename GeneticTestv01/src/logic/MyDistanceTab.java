package logic;
import logic.MyPoint;
import java.util.Comparator;
import java.util.LinkedHashSet;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;

//import logic.MyPoint;
//import javafx.scene.shape.Line;

public class MyDistanceTab {		//klasa generuj¹ca dane oraz populacje

	private Set<Set<MyPoint>> population;
	private MyPoint [] myPointTab;
	private int [][] myDistanc;
	
	public MyDistanceTab() {	
	}

	public Set<MyPoint> generatedPopul(long cardinality){// generuje losowe dane do zbiorów zbioru
		
		population = new TreeSet<Set<MyPoint>>(new Comparator<Set<MyPoint>>(){
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
		        return sum1>sum2 ? 1 :-1;
		    }
		});
			
		for(long i=0;i<cardinality;i++){
			population.add(generatedGen());
		}		
		for(Set<MyPoint> p: population){
			return p;
		}
		return null;
	}
	
	public Set<MyPoint> generatedGen(){
	//generowanie listy genów czyli zbiór My Pointów losowy ustawionych 
		
		final int gen=8;
		Random rng = new Random(); 
		Set<MyPoint> generated = new LinkedHashSet<MyPoint>();
		while (generated.size() < gen){
		    MyPoint next = myPointTab[rng.nextInt(gen)];
		    generated.add(next);
		}	
		System.out.println(generated);
		return generated;		
	}
	
	public void init(){
	// inicjacja listy obiektów Punktów  oraz tabeli odleg³oœci (chyba niepotrzebne)
		int pointNum = 8;
		myPointTab=new MyPoint[pointNum];
		myPointTab[0]=new MyPoint(10,54);
		myPointTab[1]=new MyPoint(26,75);
		myPointTab[2]=new MyPoint(37,33);
		myPointTab[3]=new MyPoint(33,74);
		myPointTab[4]=new MyPoint(52,77);
		myPointTab[5]=new MyPoint(35,24);
		myPointTab[6]=new MyPoint(82,87);
		myPointTab[7]=new MyPoint(8,45);
		/*myPointTab[8]=new MyPoint(9,84);
		myPointTab[9]=new MyPoint(84,17);
		myPointTab[10]=new MyPoint(28,41);
		myPointTab[11]=new MyPoint(21,14);
		myPointTab[12]=new MyPoint(18,9);
		myPointTab[13]=new MyPoint(55,71);
		myPointTab[14]=new MyPoint(98,3);
		myPointTab[15]=new MyPoint(11,48);
		myPointTab[16]=new MyPoint(8,98);
		myPointTab[17]=new MyPoint(58,38);
		myPointTab[18]=new MyPoint(77,38);
		myPointTab[19]=new MyPoint(61,8);*/
		myDistanc=new int[pointNum][pointNum];
		
		for(int i=0;i<pointNum;i++){
			for(int j=0;j<pointNum;j++){
				myDistanc[i][j]=myPointTab[i].getDistans(myPointTab[j]);
				System.out.print(myDistanc[i][j]+" ");
			}
			System.out.println();
		}
	}
	public int [][] getTabDistance(){
		return myDistanc;
	}
	public MyPoint [] getTabPoint(){
		return myPointTab;
	}	
	
}
