import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;


@SuppressWarnings("serial")
public class Gui extends JFrame {

   public static final int WINDOW_WIDTH = 450;
   public static final int WINDOW_HEIGHT = 600;
   
   private AmplModel amplModel;
   
   private JPanel panelShowOutput;
   private JPanel panelInsertValu;
   private JPanel panelBut;
   private JPanel panelOrg1;
   
   private JLabel money;	//Napis
   private JLabel adMoney;
   private JLabel minQual;
   private JLabel maxQual;
   private JLabel minCenaS;
   private JLabel maxCenaS;
   private JLabel risk;
   
   private JLabel cenaSO;	//Napis
   private JLabel qualO;
   private JLabel productionO;
   private JLabel predProfi;
   
   private JLabel LcenaSO;   /// dane z AMPLA 
   private JLabel LqualO;
   private JLabel LproductionO;
   private JLabel LpredProfi;
   //private JLabel risk;
   
   private JSpinner moneyI;
   private JSpinner adMoneyI;
   private JSpinner minQualI;
   private JSpinner maxQualI;
   private JSpinner minCenaSI;
   private JSpinner maxCenaSI;
   private JSpinner riskI;
   
   
   public Gui() {
       
      Container cp = this.getContentPane();
      
      panelShowOutput=new JPanel();			//organizatory
      panelInsertValu=new JPanel();	
      panelBut=new JPanel();
      panelOrg1=new JPanel();
      panelOrg1.setBorder(new TitledBorder("Dane pocz¹tkowe"));
      panelShowOutput.setBorder(new TitledBorder("Wyniki symulacji"));
      panelInsertValu.setBorder(new TitledBorder("Wprowadz wartoœci"));
      
      GridLayout grid1 = new GridLayout(4, 1);
      grid1.setHgap(3);grid1.setVgap(3);
      GridLayout grid2 = new GridLayout(5, 2);
      grid2.setHgap(3);grid2.setVgap(3);
      
      cp.setLayout(grid1);
      panelOrg1.setLayout(new GridLayout(2,1));
      panelInsertValu.setLayout(grid2);
      panelShowOutput.setLayout(grid2);
      panelBut.setLayout(new GridLayout());   
      
      money=new JLabel("Dostêpna gotówka: ");
      adMoney = new JLabel("Ile chcesz przeznaczyæ na reklame : ");
      minQual=new JLabel("Podaj jakoœæ min");
      maxQual=new JLabel("Podaj jakoœæ max");
      minCenaS=new JLabel("Podaj minimum ceny sprzedzy");
      maxCenaS=new JLabel("Podaj maximum ceny sprzedzy");
      risk=new JLabel("Wybierz poziom ryzyka");
      
      cenaSO=new JLabel("Cena sprzeda¿y ustaw");
      qualO=new JLabel("Najlepsza jakoœc");
      productionO=new JLabel("Iloœæ ustalona do produkcji");
      predProfi=new JLabel("Przewidywany zysk");
      
      Border border = BorderFactory.createLineBorder(Color.GRAY, 1);
      LcenaSO=new JLabel("WYNIK");
      LqualO=new JLabel("WYNIK");
      LproductionO=new JLabel("WYNIKI");
      LpredProfi=new JLabel("WYNIKI");
      LcenaSO.setBorder(border);
      LqualO.setBorder(border);
      LproductionO.setBorder(border);
      LpredProfi.setBorder(border);
      
          
      minQualI= new JSpinner(new SpinnerNumberModel(10.0,0 ,100.0,1));    // wejœcie danych do AMPL
      maxQualI= new JSpinner(new SpinnerNumberModel(50.0,0 ,100.0,1));
      minCenaSI= new JSpinner(new SpinnerNumberModel(10.0,0 ,100.0,1));
      maxCenaSI= new JSpinner(new SpinnerNumberModel(50.0,0 ,100.0,1));
      riskI= new JSpinner(new SpinnerNumberModel(0.5,0.1,0.99,0.01));
      moneyI=new JSpinner(new SpinnerNumberModel(50000.0,0 ,100000000.0,1000));
      adMoneyI = new JSpinner(new SpinnerNumberModel(1000.0,0 ,100000.0,100));
            
      
      panelOrg1.add(money);panelOrg1.add(moneyI);
      panelOrg1.add(adMoney);panelOrg1.add(adMoneyI);
      
      panelShowOutput.add(cenaSO);panelShowOutput.add(LcenaSO);
      panelShowOutput.add(qualO);panelShowOutput.add(LqualO);
      panelShowOutput.add(productionO);panelShowOutput.add(LproductionO);
      panelShowOutput.add(predProfi);panelShowOutput.add(LpredProfi);
      
      panelInsertValu.add(minQual);panelInsertValu.add(minQualI);
      panelInsertValu.add(maxQual);panelInsertValu.add(maxQualI);
      panelInsertValu.add(minCenaS);panelInsertValu.add(minCenaSI);
      panelInsertValu.add(maxCenaS);panelInsertValu.add(maxCenaSI);
      panelInsertValu.add(risk);panelInsertValu.add(riskI);

      cp.add(panelOrg1);
      cp.add(panelInsertValu);
      cp.add(panelShowOutput);
      //cp.add(panelOrg1);
      
      JButton but=new JButton("Wspomó¿ decyzje");
      but.addActionListener(new ActionListener() {		//wykonanie ca³oœci
		
		@Override
		public void actionPerformed(ActionEvent e) {
			setValues();
		}
	});
      panelBut.add(but);
      cp.add( panelBut);

      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  // Exit when close button clicked
      setTitle("SWD WDEC"); // "this" JFrame sets title
      setSize(WINDOW_WIDTH, WINDOW_HEIGHT);  // or pack() the components
      setVisible(true);   // show it
   }
 
   public void setValues(){  //g³owna funkcja odpalaj¹ca ampla 
	   //amplModel.setAd(couter1);
	    
	   amplModel=new AmplModel();
	   setOutputs();			//ustawia wartoœci z ampla na wynikowe
	   amplModel.solveModel();
	   getFromAmpl();
	   amplModel.closeModel();
   }
   
   public void getFromAmpl(){
	   LcenaSO.setText(amplModel.getSalePrice().getValues().toString().replaceAll(" ", "").replaceAll("|", "").replaceAll("val", "").substring(4,10));
	   LpredProfi.setText(amplModel.getProfit().getValues().toString().replaceAll(" ", "").replaceAll("|", "").replaceAll("val", "").substring(4,10));
	   LproductionO.setText(amplModel.getProduction().getValues().toString().replaceAll(" ", "").replaceAll("|", "").replaceAll("val", "").substring(4,10));
	   LqualO.setText(amplModel.getQuality().getValues().toString().replaceAll(" ", "").replaceAll("|", "").replaceAll("val", "").substring(4));
   }
   public void setOutputs(){
	    // wejœcie danych do AMPL
	 amplModel.setAd(Double.parseDouble(adMoneyI.getValue().toString()));
	 amplModel.setMaxSale(Double.parseDouble(maxCenaSI.getValue().toString()));
	 amplModel.setMinSale(Double.parseDouble(minCenaSI.getValue().toString()));
	 amplModel.setMaxQual(Double.parseDouble(maxQualI.getValue().toString()));
	 amplModel.setMinQual(Double.parseDouble(minQualI.getValue().toString()));
	 amplModel.setMoney(Double.parseDouble(moneyI.getValue().toString()));
	 amplModel.setRisk(Double.parseDouble(riskI.getValue().toString()));
	 
   }
   public static void main(String[] args) {
      SwingUtilities.invokeLater(new Runnable() {
         public void run() {
            new Gui();  // Let the constructor do the job
         }
      });
   }
}