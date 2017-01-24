package logic;

import java.util.Iterator;
import java.util.Set;

public class Genetics {  // utworzyc populacje robic cosokreslona ilosc razy z okreslonym prawdopodobienstwem itp 

	private Set<Set<MyPoint>> firstPopulation;
	private Set<Set<MyPoint>> nextPopulation;
	private Set<Set<MyPoint>> presentPopulation;
	private int replays;
	
	
	Genetics(){
		MyDistanceTab popul = new MyDistanceTab(10,1000);
		firstPopulation=popul.getPopulation();
		
	}

	public void countNextPopulation(double crossProb){
		
		if (crossProb>1.0) return;
		
		Set<Set<MyPoint>> presentPopulation=firstPopulation;
		
		Iterator<Set<MyPoint>> it = presentPopulation.iterator();
		
		double times = ((double)presentPopulation.size()*crossProb);
		
		for(int i=0;i<(int)times;i++){ // ile eleentów pobieramy do krzy¿owania
			
			Set<MyPoint> temp=it.next();
			
			Iterator<MyPoint> itIn = temp.iterator(); // iterowanie po zbiorze podzbioru
			 tmp=0;
			
			while(itIn.hasNext()){
				tmp=itIn.next().getId_p();
			
			}
		}
		
		
	}
	
	public void crossing(double probC){
		
		
		
	}
	
	public void mutating(double probM){
		
	}
	
	public int getReplays() {
		return replays;
	}
	public void setReplays(int replays) {
		this.replays = replays;
	}

	public Set<Set<MyPoint>> getNextPopulation() {
		return nextPopulation;
	}

	public void setNextPopulation(Set<Set<MyPoint>> nextPopulation) {
		this.nextPopulation = nextPopulation;
	}

	public Set<Set<MyPoint>> getPresentPopulation() {
		return presentPopulation;
	}

	public void setPresentPopulation(Set<Set<MyPoint>> presentPopulation) {
		this.presentPopulation = presentPopulation;
	}
	
	
	
	
}
