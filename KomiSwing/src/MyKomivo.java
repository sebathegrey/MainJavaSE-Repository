
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class MyKomivo extends JPanel {
    static BufferedImage offi;
    Graphics offg;
    static MyKomivo dpl;
    static MyGenetics gen;
    static JPanel res;
    static JLabel aktBestPop;
    static JLabel aktPop;
    MyKomivo() {

    }
    
    @Override
    protected synchronized void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(offi,0,0,this);
    }

    private void draw(){
        if(offi == null){
            offi = (BufferedImage)createImage(getWidth(),getHeight());
            offg = offi.getGraphics();
            
            offg.setColor(Color.black);
            offg.fillRect(0,0,getWidth(),getHeight());
        }  
        offg.setColor(Color.RED);
        //offg.fillRect((int)x1,(int)y1,1,1);
    	if(gen.isSemafor()){

			
			try{
                Thread.sleep(25); //sleep if you want it to be animated
            }catch(InterruptedException e){
                e.printStackTrace();
            }
			gen.setSemafor(false);
            offg.setColor(Color.BLACK);
	        offg.fillRect(0, 0, 500, 500);
	        offg.setColor(Color.RED);
			MyPoint temp=null,first=null,last=null;
			Set<MyPoint> points=gen.getAktualPop();
			aktBestPop.setText("Najlepszy wynik : " + gen.getoAktBestSolut());
			aktPop.setText("Populacja : " + gen.getoAktPopul());

			for(MyPoint p1 : points){
				if(temp==null){
					temp=p1;
					first=p1;
				}else{
					offg.drawLine(5*temp.getX(), 5*temp.getY(), 5*p1.getX(), 5*p1.getY());
					temp=p1;
					last=p1;
				}
			}
			offg.drawLine(5*first.getX(), 5*first.getY(), 5*last.getX(), 5*last.getY());
			//f.paintComponents(s);
		}
	}   

    public void drawLine() {
            
        while (true) {          
            draw(); 
            repaint();
        }  
    }
    
    public static void saveToFile(){
    	DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HHmmss");
		String s= dateFormat.format(new Date())+".png";
		File file = new File("results/"+s);
    	try {
			ImageIO.write(offi, "PNG", file);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    }
    
    public static void startApp(){
    	
        new Thread(gen).start();

        new Thread(() -> dpl.drawLine()).start();;
    }
    
    public static void main(String[] args){
    	
        	SwingUtilities.invokeLater(() -> {
            JFrame jFrame = new JFrame(""); 
            JButton but = new JButton("Save");
            JButton but1 = new JButton("Start");
            JPanel view = new JPanel();
            JPanel mainView = new JPanel(new BorderLayout());
                      
            dpl = new MyKomivo();
            res=new JPanel();
            aktBestPop=new JLabel("Najlepszy wynik : 0");
            aktPop=new JLabel("Populacja : 0");
            
            gen = new MyGenetics(200,20);
            
            mainView.setSize(550,550);
            dpl.setSize(550, 500);

            but.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					saveToFile();
				}
			});
            but1.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					startApp();
				}
			});
            
            res.setBackground(Color.BLACK);
            res.add(aktBestPop);
            res.add(aktPop);
            
            mainView.add(dpl, BorderLayout.CENTER);
            mainView.add(res, BorderLayout.PAGE_END);
           
            view.add(but1);
            view.add(but);
            
            jFrame.add(mainView,BorderLayout.CENTER);
            jFrame.add(view,BorderLayout.PAGE_END);
            
            jFrame.setSize(550,600);
            jFrame.setResizable(true);
            jFrame.setVisible(true);
            jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            

        });
    }

}