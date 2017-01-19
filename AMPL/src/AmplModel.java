import java.io.IOException;
import com.ampl.*;

public class AmplModel {
	
		private AMPL ampl;
		
		private double minQual;
		private double maxQual;
		private double minSale;
		private double maxSale;
		private double risk;
		private double ad;
		private double money;
		
		private Parameter minQualA;  // z koñcówka A zmienne AMPL !!?
		private Parameter maxQualA;
		private Parameter minSaleA;
		private Parameter maxSaleA;
		private Parameter riskA;
		private Parameter adA;
		private Parameter moneyA;
		
		private Variable salePrice;
		private Variable quality;
		private Variable production;
		private Variable profit;
		
		AmplModel(){
        // Create an AMPL instance
        
		}
		
		public void solveModel(){  //rozwi¹ze model i przypisze do qualiti priceP i innychy wyjsciowych, dane za pomoc¹ setów ustawi sie do zmiennych prywatnych w gui
        try {
        	ampl = new AMPL();
        	ampl.read("model.mod");
        	
        	minQualA = ampl.getParameter("minJakosc");
    		maxQualA= ampl.getParameter("maxJakosc");
    		minSaleA= ampl.getParameter("minSprzedaz");
    		maxSaleA= ampl.getParameter("maxSprzedaz");
    		riskA= ampl.getParameter("ryzyko");
    		adA= ampl.getParameter("reklama");
    		moneyA= ampl.getParameter("gotowka");
        	
    		minQualA.set(minQual);
    		maxQualA.set(maxQual);
    		minSaleA.set(minSale);
    		maxSaleA.set(maxSale);
    		riskA.set(risk);
    		adA.set(ad);
    		moneyA.set(money);

           	//reklama.setValue(5555);
           	ampl.solve();
           	salePrice=ampl.getVariable("cenaS");
           	quality=ampl.getVariable("jakosc");
           	production=ampl.getVariable("produkcja");
           	profit=ampl.getVariable("zysk");
           	//gotowkaS=gotowka.get().toString();
           	//System.out.println("Objective is: "+gotowka.get().toString()+" "+ reklama.get().toString());

        	}catch (RuntimeException e ){
        	System.out.println(e.getMessage());
        	}catch ( IOException e ){
        	System.out.println(e.getMessage());
        	}finally {
            //ampl.close();
        	}

		}
		public void closeModel(){
			ampl.close();
		}
		public void setMinSale(double minSale){
			this.minSale=minSale;
		}
		public void setMaxSale(double maxSale){
			this.maxSale=maxSale;
		}
		public void setMinQual(double minQual){
			this.minQual=minQual;
		}
		public void setMaxQual(double maxQual){
			this.maxQual=maxQual;
		}
		public void setMoney(double money){
			this.money=money;
		}
		public void setAd(double adMoney){
			this.ad=adMoney;
		}
		public void setRisk(double risk){
			this.risk=risk;
		}

		public Variable getSalePrice() {
			return salePrice;
		}

		public Variable getQuality() {
			return quality;
		}

		public Variable getProduction() {
			return production;
		}

		public Variable getProfit() {
			return profit;
		}


}





