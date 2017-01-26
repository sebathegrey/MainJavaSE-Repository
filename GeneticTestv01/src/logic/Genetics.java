package logic;

import java.lang.reflect.InvocationTargetException;
import java.util.Comparator;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.TreeSet;

public class Genetics { // utworzyc populacje robic cosokreslona ilosc razy z
						// okreslonym prawdopodobienstwem itp

	private TreeSet<Set<MyPoint>> firstPopulation;
	private TreeSet<Set<MyPoint>> nextPopulation;
	private TreeSet<Set<MyPoint>> presentPopulation;
	private int replays;
	private int population;
	MyDistanceTab popul;
	private double crossProb;

	public Genetics() throws InvocationTargetException {
		population = 300;
		crossProb = 0.40;
		replays = 0;
		popul = new MyDistanceTab(20, population);
		firstPopulation = popul.getPopulation();
		countNextPopulation(firstPopulation);
	}

	public void countNextPopulation(TreeSet<Set<MyPoint>> present) throws InvocationTargetException {

		if (crossProb > 1.0)
			return;

		nextPopulation = new TreeSet<Set<MyPoint>>(new MyComparator());
		nextPopulation.clear();
		TreeSet<Set<MyPoint>> nextPopulation2 = new TreeSet<Set<MyPoint>>(new MyComparator());
		TreeSet<Set<MyPoint>> nextPopulation3 = new TreeSet<Set<MyPoint>>(new MyComparator());
		nextPopulation=present;
		
		int times = (int) (nextPopulation.size() * crossProb);
		Set<MyPoint> tmp = null;
		
		int i = 0;
		for (Set<MyPoint> myPoint : nextPopulation) { // ile elementów pobieramy do

			if (i == 0) {
				tmp = myPoint;
			}else{
				nextPopulation2.add(crossing(tmp, myPoint));
				nextPopulation2.add(crossing(myPoint,tmp));
				tmp = myPoint;
				//System.out.println(nextPopulation);
				}
			i++;

			if (i >= times)
				break;
		}
		
		int ia = 0;
		for (Set<MyPoint> s : nextPopulation) {
			nextPopulation2.add(s);
		}
		for (Set<MyPoint> s : nextPopulation2) {
			ia++;
			nextPopulation3.add(s);
			if (ia >= population)
				break;
		}
		if(replays<6000){
			replays++;
			countNextPopulation(nextPopulation3);
		}
	}

	public Set<MyPoint> crossing(Set<MyPoint> zbior1,Set<MyPoint> zbior2){
		
		int []points1=new int[MyPoint.getID_POINT()];
		int []points2=new int[MyPoint.getID_POINT()];
		int ii=0;
		
		LinkedHashSet<MyPoint> r=new LinkedHashSet<>();
		
		for(MyPoint p : zbior1){
			points1[ii]=p.getId_p();

			ii++;

		}
		int jj=0;
		
		for(MyPoint p1 : zbior2){
			points2[jj]=p1.getId_p();
			jj++;
		}
		int ogr1=(int)(0.7*MyPoint.getID_POINT());
		int ogr2=(int)(0.3*MyPoint.getID_POINT());
		int ogr3=MyPoint.getID_POINT();

		int []temp=new int[ogr1-ogr2];	
		int ij=0;
		
		for(int z=ogr2;z<ogr1;z++){
			temp[ij]=points2[z];ij++;
		}
		
		for(int z=ogr2;z<ogr1;z++){
			boolean bool1=false;
			for(int w =0;w<(ogr1-ogr2);w++){
				
				if(points1[z]==temp[w]){
					bool1=true;
					break;
				}
			}
			//co jesli wartosci z piewszego nie ma w drugim na z miejscu stoi element ktorego nie  ma w b
			if(bool1==false){
				//przeszukamy 2 ciag znajdujac wyraz niebed¹cy w pierwszym jego wartosc zapisaac i potem miejsce z zamienic z miejscem na którym stoi ten element
				int index=0;
				int value=0;
							
				for(int i =0;i<(ogr1-ogr2);i++)	{
					boolean bool2=false;
					for(int j=ogr2;j<ogr1;j++){
						if(points1[j]==temp[i]){
							bool2=true;
						}
					}
					if(bool2==false){
						index=i;
						value=temp[i];
					//	break;
					//}
				//}
				//znalezc w pierwszym zapisan¹ wartoœæ i zamienic z obecnym [z]
				for(int i2 =0;i2<ogr3;i2++){
					if(points1[i2]==value){
						int t=points1[z];
						points1[z]=value;
						points1[i2]=t;
						break;
					}
				}}}
			}
		}
		int ji=0;
		for(int z=ogr2;z<ogr1;z++){
			points1[z]=temp[ji];
			ji++;
		}
		
			for(int z=0;z<MyPoint.getID_POINT();z++){
				for(MyPoint s: zbior1){
				if(s.getId_p()==points1[z]){
					r.add(s);
				}	
			}
		}
			//System.out.println(r);
		return r;
	}

	public void mutating(double probM) {

	}

	public TreeSet<Set<MyPoint>> selectin(TreeSet<Set<MyPoint>> before) { // wybór	// tyle

		TreeSet<Set<MyPoint>> tmp = new TreeSet<Set<MyPoint>>(new MyComparator());
		int i = 0;
		for (Set<MyPoint> s : before) {
			i++;

			tmp.add(s);
			if (i >= population)
				break;
		}
		
		return tmp;
	}

	public int getReplays() {
		return replays;
	}

	public void TreeSetReplays(int replays) {
		this.replays = replays;
	}

	/*
	 * public TreeSet<TreeSet<MyPoint>> getNextPopulation() { return
	 * nextPopulation; }
	 * 
	 * public void TreeSetNextPopulation(TreeSet<TreeSet<MyPoint>>
	 * nextPopulation) { this.nextPopulation = nextPopulation; }
	 */
	public TreeSet<Set<MyPoint>> getPresentPopulation() {
		return presentPopulation;
	}

	public void TreeSetPresentPopulation(TreeSet<Set<MyPoint>> presentPopulation) {
		this.presentPopulation = presentPopulation;
	}

	public Set<MyPoint> getBestPopulation() {

		Set<MyPoint> ret = nextPopulation.first();
		int sum1 = 0;

		MyPoint temp = null;
		for (MyPoint r : ret) {
			if (temp == null)
				temp = r;
			else {
				sum1 += temp.getDistans(r);
				temp = r;
			}
		}
		System.out.println("Suma : " + sum1+" "+ret);
		return ret;
	}

	public MyPoint[] getPointTab() {
		return popul.getTabPoint();
	}
}
