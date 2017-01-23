import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
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
   
   
   public Gui() throws IOException {
       
      Container cp = this.getContentPane();
      
      panelShowOutput=new JPanel();			//organizatory
      panelInsertValu=new JPanel();	
      panelBut=new JPanel();
      panelOrg1=new JPanel();
      panelOrg1.setBorder(new TitledBorder("Dane pocz¹tkowe"));
      panelShowOutput.setBorder(new TitledBorder("Wyniki przeprowadzonej symulacji"));
      panelInsertValu.setBorder(new TitledBorder("Wprowadz swoje oczekiwania"));
      
      GridLayout grid1 = new GridLayout(4, 1);
      grid1.setHgap(10);grid1.setVgap(10);
      GridLayout grid2 = new GridLayout(5, 2);
      grid2.setHgap(3);grid2.setVgap(3);
      
      cp.setLayout(grid1);
      panelOrg1.setLayout(new GridLayout(2,1));
      panelInsertValu.setLayout(grid2);
      panelShowOutput.setLayout(grid2);
      panelBut.setLayout(new GridLayout());   
      
      money=new JLabel("Dostêpna gotówka: ");
      adMoney = new JLabel("Wydatki na reklamê");
      minQual=new JLabel("Okreœl jakoœæ min");
      maxQual=new JLabel("Okreœl jakoœæ max");
      minCenaS=new JLabel("Minimalna cena sprzeda¿y");
      maxCenaS=new JLabel("Maksymalna cena sprzeda¿y");
      risk=new JLabel("Prawd. sprzedarzy(ryzyko) [0.0-0.99]");
      
      cenaSO=new JLabel("Cena sprzeda¿y");
      qualO=new JLabel("Wybrana jakoœæ");
      productionO=new JLabel("Wytypowana iloœæ produkcji");
      predProfi=new JLabel("Przewidywany zysk");
      
      Border border = BorderFactory.createLineBorder(Color.GRAY, 1);
      LcenaSO=new JLabel("0.000");
      LqualO=new JLabel("0.000");
      LproductionO=new JLabel("0.000");
      LpredProfi=new JLabel("0.000");
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
      addTrayIcon();
      JButton but=new JButton("Oblicz parametry dla wprowadzonych danych");
      but.setBackground(new Color(191, 192, 201));
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
   		public void addTrayIcon() throws IOException{
   			
   		 if (!SystemTray.isSupported()) {
             System.out.println("SystemTray is not supported");
             return;
         }
         final PopupMenu popup = new PopupMenu();
         final TrayIcon trayIcon =
                 new TrayIcon(ImageIO.read(new File("seba.jpg")));
         trayIcon.setToolTip("hhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhh");
         final SystemTray tray = SystemTray.getSystemTray();
        
         // Create a pop-up menu components
         MenuItem aboutItem = new MenuItem("About");
         CheckboxMenuItem cb1 = new CheckboxMenuItem("Set auto size");
         CheckboxMenuItem cb2 = new CheckboxMenuItem("Set tooltip");
         Menu displayMenu = new Menu("Display");
         MenuItem errorItem = new MenuItem("Error");
         MenuItem warningItem = new MenuItem("Warning");
         MenuItem infoItem = new MenuItem("Info");
         MenuItem noneItem = new MenuItem("None");
         MenuItem exitItem = new MenuItem("Exit");
        JButton ddd = new JButton();
       
         //Add components to pop-up menu
         popup.add(aboutItem);
         popup.addSeparator();
         popup.add(cb1);
         popup.add(cb2);
         popup.addSeparator();
         popup.add(displayMenu);
         displayMenu.add(errorItem);
         displayMenu.add(warningItem);
         displayMenu.add(infoItem);
         displayMenu.add(noneItem);
         popup.add(exitItem);
         trayIcon.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				setValues();
				}})
		;
         trayIcon.setPopupMenu(popup);
         exitItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
				
			}
		});
         try {
             tray.add(trayIcon);
         } catch (AWTException e) {
             System.out.println("TrayIcon could not be added.");
         }
        
   			
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
            try {
				new Gui();
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}  // Let the constructor do the job
         }
      });
   }
}