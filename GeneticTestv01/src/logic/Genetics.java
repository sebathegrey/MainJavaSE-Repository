package logic;

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

	public Genetics() {
		population = 50;
		crossProb = 0.8;
		replays = 0;
		popul = new MyDistanceTab(20, population);
		firstPopulation = popul.getPopulation();
		countNextPopulation(firstPopulation);
	}

	public void countNextPopulation(TreeSet<Set<MyPoint>> present) {

		if (crossProb > 1.0)
			return;

		nextPopulation = new TreeSet<Set<MyPoint>>(new MyComparator());

		int times = (int) (present.size() * crossProb);

		Set<MyPoint> tmp = null;
		int i = 0;
		for (Set<MyPoint> myPoint : present) { // ile elementów pobieramy do
												// krzy¿owania
			nextPopulation.add(myPoint);
			if (i == 0) {
				tmp = myPoint;
			} else {
				nextPopulation.add(crossing(tmp, myPoint));
				nextPopulation.add(crossing(myPoint,tmp));
				tmp = myPoint;
				}
			i++;

			if (i >= times)
				break;
		}

		while (replays < 8000) {
			replays++;
			countNextPopulation(selectin(nextPopulation));
		}
	}

	public Set<MyPoint> crossing(Set<MyPoint> zbior1,Set<MyPoint> zbior2){
		
		int []points1=new int[MyPoint.getID_POINT()];
		int []points2=new int[MyPoint.getID_POINT()];
		int i=0;
		
		Set<MyPoint> r=new LinkedHashSet<>();
		
		for(MyPoint p : zbior1){
			points1[i]=p.getId_p();
			i++;
	
		}
		int j=0;
		
		for(MyPoint p1 : zbior2){
			points2[j]=p1.getId_p();
			j++;
		}
		int ogr1=(int)(0.7*MyPoint.getID_POINT());
		int ogr2=(int)(0.2*MyPoint.getID_POINT());
		int ogr3=MyPoint.getID_POINT();
		
		
			
			for(int z=ogr2;z<ogr1;z++){
				boolean bool1=false;
				for(int z2=ogr2;z2<ogr1;z2++){
				
					if(points1[z]==points2[z2]){
						bool1=true;
					}					
				}
			
				if (bool1==false){
					int temp=0;
					for(int z2=ogr1;z2<ogr3;z2++){
						for(int zp=ogr1;zp<ogr3;zp++){
							if(points2[z2]==points1[zp]){
								temp=zp;
								break;
							}
						}
						for(int zp=0;zp<ogr2;zp++){
							if(points2[z2]==points1[zp]){
								temp=zp;
								break;
							}
						}	
					}
					int temp2 = points1[z];
					points1[z]=points1[temp];
					points1[temp]=temp2;	
				}
			
			}
	
	
		int []temp=new int[ogr1-ogr2];
		
		for(int z=ogr2;z<ogr1;z++){
			
			//points1[z]=points2[z];	
		}
		
		for(MyPoint s: zbior1){
		for(int z=0;z<MyPoint.getID_POINT();z++){
			
			
				
				if(s.getId_p()==points1[z]){
					//System.out.println("----"+points1[z]+"  ");
					r.add(s);
				}	
			}
		}
		
		return r;
	}

	
	
	
	
	
	public void mutating(double probM) {

	}

	public TreeSet<Set<MyPoint>> selectin(Set<Set<MyPoint>> before) { // wybór	// tyle

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
		System.out.println("Suma : " + sum1);
		return ret;
	}

	public MyPoint[] getPointTab() {
		return popul.getTabPoint();
	}
}
