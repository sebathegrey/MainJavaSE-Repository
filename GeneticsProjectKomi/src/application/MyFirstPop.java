package application;

import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Map.Entry;
import java.util.Random;
import java.util.Set;
import java.util.TreeMap;

public class MyFirstPop {

	private MyPoint [] myPointTab;
	private int pointCardin;
	private int cardinality;
	private TreeMap<Double,LinkedHashSet<MyPoint>> mapPopulacji;
	private TreeMap<Double,LinkedHashSet<MyPoint>> mapLastPop;	
	private int stopGen;
	private int licz;
	private double prawdopKrz;
	
	public MyFirstPop(int licznoscPop,int liczbaPunk){
		
		licz=0;
		
		prawdopKrz = 0.555;
		stopGen=50;
		mapPopulacji=new TreeMap<>();
		mapLastPop=new TreeMap<>();
		setCardinality(licznoscPop);
		setPointCardin(liczbaPunk);
		initP();
		setPopulacja();
		nextPopulation();
	}
	
	public double wayValue(LinkedHashSet<MyPoint> myPoints){
		double sum=0;
		MyPoint temp=null,first=null,last=null,my=null;
		Iterator<MyPoint > it=myPoints.iterator();
		while(it.hasNext())
		{
			my=it.next();
			if(temp==null){
				first=my;
				temp=my;
			}else{ 
				sum+=temp.getDistans(my);
				temp=my;
				last=my;
			}
		}
		sum+=last.getDistans(first);
		return  sum;
	}
	
	public void setPopulacja(){
		
		while(mapPopulacji.size()<cardinality){
		
			LinkedHashSet<MyPoint> s=generatedGen();
			mapPopulacji.put( wayValue(s),s);	
		}
			//System.out.println(mapPopulacji);
			System.out.println(mapPopulacji.firstKey());
	}
	
	public LinkedHashSet<MyPoint> generatedGen(){
		//generowanie listy genów czyli zbiór My Pointów losowy ustawionych 	
			final int gen=getPointCardin();
			Random rng = new Random(); 
			LinkedHashSet<MyPoint> generated = new LinkedHashSet<MyPoint>(); //zbiór losowych genów 
			
			while (generated.size() < gen){
			    MyPoint next = myPointTab[rng.nextInt(gen)];
			    generated.add(next);
			}	
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
		myPointTab[18]=new MyPoint(77,8);
		myPointTab[19]=new MyPoint(61,88);
		//myDistanc=new int[pointNum][pointNum];
		
/*		for(int i=0;i<pointNum;i++){			// inicjalizacja myDistanc wartosci obliczone do porównywania ale mam przeciez nasz Comparator dlat. raczej NOTUSE
			for(int j=0;j<pointNum;j++){
//				myDistanc[i][j]=myPointTab[i].getDistans(myPointTab[j]);
			}
		}*/
	}

	
	public void nextPopulation(){
		
		double naj =1220;
		
		while(licz<stopGen){
			licz++;
			int size = (int) (mapPopulacji.size()*prawdopKrz);
			int times = 0;
			TreeMap<Double,LinkedHashSet<MyPoint>> mapLastPopj=new TreeMap<>();
			LinkedHashSet<MyPoint> temp=null,first = null,last=null,my=null;
			Iterator<LinkedHashSet<MyPoint>> it = mapPopulacji.values().iterator();
			
			while(it.hasNext()){
				LinkedHashSet<MyPoint> temp2=null,temp3=null;
				my=it.next();
				if(temp==null){
					temp=my;
					first=my;
				
				}else{
					temp2=crossing(temp,my);
					mapLastPopj.put(wayValue(temp2), temp2);
					temp3=crossing(my,temp);
					mapLastPopj.put(wayValue(temp3), temp3);
					last=my;
					temp=my;
				}
				if(times>size)break;
				times++;
			}
			for( LinkedHashSet<MyPoint> s : mapPopulacji.values()){
			mapLastPopj.put(wayValue(s),s);
			}
			int i=0;
			for(Entry<Double, LinkedHashSet<MyPoint>> s : mapLastPopj.entrySet()){
				if(i>cardinality)break;
				
				mapLastPop.put(s.getKey(),s.getValue());
				i++;
			}
			mapPopulacji=mapLastPop;
			double t=mapPopulacji.firstKey();
			if(t<naj)
			naj=t;
		}
		System.out.println(mapLastPop.firstKey()+"\n"+naj);
		return;		
		//mapPopulacji=mapLastPop;
		//nextPopulation();
	}
	
	
	
	public LinkedHashSet<MyPoint> crossing(LinkedHashSet<MyPoint> zbior1,LinkedHashSet<MyPoint> zbior2){
		
		int []points1=new int[MyPoint.getID_POINT()];
		int []points2=new int[MyPoint.getID_POINT()];
		int ii=0;
		
		Random g1 = new Random(); 
		double alfa1 = ((double)(g1.nextInt(4) + 6)/10.0);
		Random g2 = new Random(); 
		double alfa2 = ((double)(g2.nextInt(4) + 1))/10.0;
		
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
		int ogr1=(int)(alfa1*MyPoint.getID_POINT());
		int ogr2=(int)(alfa2*MyPoint.getID_POINT());
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
		r.clear();
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
	public LinkedHashSet<MyPoint> getLastPop(){
		
		return mapLastPop.firstEntry().getValue();
		
	}
	public LinkedHashSet<MyPoint> getFirstPop(){
		
		return mapPopulacji.firstEntry().getValue();
		
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
