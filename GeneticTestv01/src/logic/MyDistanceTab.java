package logic;
import java.util.LinkedHashSet;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;

//import logic.MyPoint;
//import javafx.scene.shape.Line;

public class MyDistanceTab {		//klasa generuj¹ca dane oraz populacje

	private TreeSet<Set<MyPoint>> population;
	private MyPoint [] myPointTab;
	private int [][] myDistanc;
	private int pointCardin;
	private int cardinality;
	
	public MyDistanceTab(int pointCardin, int cardinality) {	//liczba punktów, licznosc populacji
		
		setPointCardin(pointCardin);
		setCardinality(cardinality);
		initP();
		population = new TreeSet<Set<MyPoint>>(new MyComparator());
		generatedPopul();
	}

	public void generatedPopul(){// generuje losowe dane do zbiorów zbioru, param licznoœæ zbiorów losowych, licznosc populacji 
		
		for(long i=0;i<cardinality;i++){
			population.add(generatedGen());
		}
	
	}
	public TreeSet<Set<MyPoint>> getPopulation(){
	
		return population;	
	}
	
	public Set<MyPoint> generatedGen(){
	//generowanie listy genów czyli zbiór My Pointów losowy ustawionych 
		
		final int gen=getPointCardin();
		Random rng = new Random(); 
		
		Set<MyPoint> generated = new LinkedHashSet<MyPoint>(); //zbiór losowych genów 
		while (generated.size() < gen){
		    MyPoint next = myPointTab[rng.nextInt(gen)];
		    generated.add(next);
		}	
		System.out.println(generated);
		return generated;		
	}
	
	public void initP(){
	// inicjacja listy obiektów Punktów  oraz tabeli odleg³oœci (chyba niepotrzebne)
		int pointNum = getPointCardin();
		myPointTab=new MyPoint[pointNum];
		myPointTab[0]=new MyPoint(10,54);//musi byc zadeklarowane tyle punktów co populacji
		myPointTab[1]=new MyPoint(26,75);
		myPointTab[2]=new MyPoint(37,33);
		myPointTab[3]=new MyPoint(33,74);
		myPointTab[4]=new MyPoint(52,77);
		myPointTab[5]=new MyPoint(35,24);
		myPointTab[6]=new MyPoint(82,87);
		myPointTab[7]=new MyPoint(8,45);
		myPointTab[8]=new MyPoint(9,84);
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
		myPointTab[19]=new MyPoint(61,8);
		myDistanc=new int[pointNum][pointNum];
		
		for(int i=0;i<pointNum;i++){			// inicjalizacja myDistanc wartosci obliczone do porównywania ale mam przeciez nasz Comparator dlat. raczej NOTUSE
			for(int j=0;j<pointNum;j++){
				myDistanc[i][j]=myPointTab[i].getDistans(myPointTab[j]);
				//System.out.print(myDistanc[i][j]+" ");
			}
			//System.out.println();
		}
	}
	public int [][] getTabDistance(){
		return myDistanc;
	}
	public MyPoint [] getTabPoint(){
		return myPointTab;
	}

	public int getPointCardin() {
		return pointCardin;
	}
	public void setPointCardin(int pointCardin) {
		this.pointCardin = pointCardin;
	}

	public int getCardinality() {
		return cardinality;
	}

	public void setCardinality(int cardinality) {
		this.cardinality = cardinality;
	}	
	
}
