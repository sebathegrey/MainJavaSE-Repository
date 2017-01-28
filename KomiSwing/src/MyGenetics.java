

import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Map.Entry;
import java.util.Random;
import java.util.TreeMap;

public class MyGenetics implements Runnable{

	private MyPoint [] myPointTab;
	private TreeMap<Double,LinkedHashSet<MyPoint>> mapPopulacji;
	private TreeMap<Double,LinkedHashSet<MyPoint>> mapLastPop;	
	
	private LinkedHashSet<MyPoint> aktualPop;
	private volatile boolean semafor;
	private double oAktBestSolut;
	private double oAktPopul;
	
	private double aktBestSolut;
	private int licz;
	
	private int pointCardin;
	private int cardinality;
	private int stopGen;
	private double prawdopKrz;
	private int mutProc;
	private double bestSolut;
	
	@Override
	public void run() {

		initP();						//initializacja listy punktow 
		initP(new MyPoint(52,2));
		initP(new MyPoint(2,72));
		initP(new MyPoint(1,99));
		initP(new MyPoint(99,99));
		initP(new MyPoint(5,49));
		initP(new MyPoint(19,69));
		
		setPopulacja();					//populacja losowa pierwsza
		countNextPopulation();
		
	}
	
	public MyGenetics(int licznoscPop,int liczbaPunk){
		
		licz=0;
		
		setSemafor(false);
		setBestSolut(480); //minimalne rozwi¹zanie
		setMutProc(15); 		// losowa mmutacja w ra¿ona w procentach
		setPrawdopKrz (0.65);	//
		setStopGen(100);		//
		setCardinality(licznoscPop);	//liczba osobników w populacji
		setPointCardin(liczbaPunk);		//liczba punktów w przedziale 
		
		
		mapPopulacji=new TreeMap<>();
		mapLastPop=new TreeMap<>();
		
	/*	initP();						//initializacja listy punktow 
		initP(new MyPoint(52,52));
		initP(new MyPoint(2,2));
		initP(new MyPoint(1,99));
		initP(new MyPoint(99,99));
		setPopulacja();					//populacja losowa pierwsza
		
		countNextPopulation();	*/		//generacja kolejnych populacji 
	}
	
	public double wayValue(LinkedHashSet<MyPoint> myPoints){ // droga dla danego chromosomu
		
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
	
	public void setPopulacja(){ // generuje losowa populacje
		
		while(mapPopulacji.size()<cardinality){
		
			LinkedHashSet<MyPoint> s=generatedGen();
			mapPopulacji.put( wayValue(s),s);	
		}
			//System.out.println(mapPopulacji);
			System.out.println(mapPopulacji.firstKey());
	}
	
	public LinkedHashSet<MyPoint> generatedGen(){ //generuje losowy chromosom
		//generowanie listy genów czyli zbiór My Pointów losowy ustawionych 	
			final int gen=MyPoint.getID_POINT();
			Random rng = new Random(); 
			LinkedHashSet<MyPoint> generated = new LinkedHashSet<MyPoint>(); //zbiór losowych genów 
			
			while (generated.size() < gen){
			    MyPoint next = myPointTab[rng.nextInt(gen)];
			    generated.add(next);
			}	
		return generated;		
		}
	
	public void initP(){  //initialiacja punktów 
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
		
	}
	
	public void initP(MyPoint my){
		int pointNum = MyPoint.getID_POINT();
		
		MyPoint myPointTab2[]=new MyPoint[pointNum];
		
		for(int i=0; i < pointNum-1;i++){
			myPointTab2[i]=myPointTab[i];
		}
		myPointTab2[pointNum-1]=my;
		
		myPointTab=myPointTab2;
	}
	
	public void countNextPopulation(){
		
		aktBestSolut=mapPopulacji.firstKey();
		while(aktBestSolut > bestSolut){
		//while(licz<stopGen){
			try{
	            Thread.sleep(50); //sleep if you want it to be animated
	           }catch(InterruptedException e){
	                e.printStackTrace();
	           }
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
					temp2=doCrossing(temp,my);
					mapLastPopj.put(wayValue(temp2), temp2);
					temp3=doCrossing(my,temp);
					mapLastPopj.put(wayValue(temp3), temp3);
					last=my;
					temp=my;
				}
				if(times>size){
					mapLastPopj.put(wayValue(first), last);
					mapLastPopj.put(wayValue(last), first);
					break;
				}
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
			mapPopulacji=doMutation(mapLastPop);
			aktBestSolut=mapPopulacji.firstKey();
			
			if(isSemafor()==false){
				setSemafor(false);
				setAktualPop(mapPopulacji);
				setoAktBestSolut(aktBestSolut);
				setoAktPopul(licz);
			}
			setSemafor(true);
			
			if(licz > stopGen) break;
			
		}
		System.out.println("Liczba populacji : "+licz);
		System.out.println("Najlepsza populacja : "+mapPopulacji.firstKey());
		return;		

	}
	
	public LinkedHashSet<MyPoint> doCrossing(LinkedHashSet<MyPoint> zbior1,LinkedHashSet<MyPoint> zbior2){
		
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
				//int index=0;
				int value=0;
							
				for(int i =0;i<(ogr1-ogr2);i++)	{
					boolean bool2=false;
					for(int j=ogr2;j<ogr1;j++){
						if(points1[j]==temp[i]){
							bool2=true;
						}
					}
					if(bool2==false){
						//index=i;
						value=temp[i];

				//znalezc w pierwszym zapisan¹ wartoœæ i zamienic z obecnym [z]
						for(int i2 =0;i2<ogr3;i2++){
							if(points1[i2]==value){
								int t=points1[z];
								points1[z]=value;
								points1[i2]=t;
								break;
							}
						}
					}
				}
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
		return r;
	}	
	
	public TreeMap<Double, LinkedHashSet<MyPoint>> doMutation(TreeMap<Double,LinkedHashSet<MyPoint>> my){
		
		TreeMap<Double, LinkedHashSet<MyPoint>> temp1=new TreeMap<>();
		double tempD=0;
		LinkedHashSet<MyPoint> tempL=null;
		Entry<Double, LinkedHashSet<MyPoint>> tempS=null;
		Iterator<Entry<Double, LinkedHashSet<MyPoint>>> it = my.entrySet().iterator();
			
		while(it.hasNext()){
				
			tempS=it.next();
			tempD=tempS.getKey();
			tempL=tempS.getValue();
				
			if((new Random().nextInt(100)+1)<mutProc ){
				//mutacja na LinkedHashSet<MyPoint>
				LinkedHashSet<MyPoint> tempL2=new LinkedHashSet<>();
				MyPoint pointTab[] = new MyPoint[MyPoint.getID_POINT()];
				int i=0;
					
				for(MyPoint tmpPoint:tempL){
					pointTab[i]=tmpPoint;
					i++;
				}
				int j=0,j2=0;
				
				while(j==j2){
					j = new Random().nextInt(MyPoint.getID_POINT());
					j2 = new Random().nextInt(MyPoint.getID_POINT());
				}
				MyPoint tmp=pointTab[j];
				pointTab[j]=pointTab[j2];
				pointTab[j2]=tmp;
					
				for(int z=0;z<MyPoint.getID_POINT();z++){				
					tempL2.add(pointTab[z]);
				}
				temp1.put(wayValue(tempL2), tempL2);
			}else {
				temp1.put(tempD, tempL);
			}
		}
		return temp1;
	}
		
	//Gettery i settery
	public LinkedHashSet<MyPoint> getAktualPop(){
		
		return aktualPop;
		
	}
	public  void setAktualPop(TreeMap<Double, LinkedHashSet<MyPoint>> tree){
		
		aktualPop=tree.firstEntry().getValue();
		
	}
	public LinkedHashSet<MyPoint> getLastPop(){
		
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
	public MyPoint [] getMyPointTab() {
		return myPointTab;
	}
	public void setMyPointTab(MyPoint [] myPointTab) {
		this.myPointTab = myPointTab;
	}
	public int getStopGen() {
		return stopGen;
	}
	public void setStopGen(int stopGen) {
		this.stopGen = stopGen;
	}
	public int getMutProc() {
		return mutProc;
	}
	public void setMutProc(int mutProc) {
		this.mutProc = mutProc;
	}
	public double getBestSolut() {
		return bestSolut;
	}
	public void setBestSolut(double bestSolut) {
		this.bestSolut = bestSolut;
	}
	public double getPrawdopKrz() {
		return prawdopKrz;
	}
	public void setPrawdopKrz(double prawdopKrz) {
		this.prawdopKrz = prawdopKrz;
	}

	public boolean isSemafor() {
		return semafor;
	}

	public void setSemafor(boolean semafor) {
		this.semafor = semafor;
	}

	public double getoAktBestSolut() {
		return oAktBestSolut;
	}

	public void setoAktBestSolut(double oAktBestSolut) {
		this.oAktBestSolut = oAktBestSolut;
	}

	public double getoAktPopul() {
		return oAktPopul;
	}

	public void setoAktPopul(double oAktPopul) {
		this.oAktPopul = oAktPopul;
	}


}
