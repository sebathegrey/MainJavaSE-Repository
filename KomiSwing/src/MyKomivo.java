
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Set;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class MyKomivo extends JPanel {
    BufferedImage offi;
    Graphics offg;

    static MyGenetics gen;
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

			gen.setSemafor(false);
			try{
                Thread.sleep(25); //sleep if you want it to be animated
            }catch(InterruptedException e){
                e.printStackTrace();
            }
            offg.setColor(Color.BLACK);
	        offg.fillRect(0, 0, 500, 500);
	        offg.setColor(Color.RED);
			MyPoint temp=null,first=null,last=null;
			Set<MyPoint> points=gen.getAktualPop();
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

    public static void main(String[] args){
        	SwingUtilities.invokeLater(() -> {
            JFrame jFrame = new JFrame("Graphics");
            MyKomivo dpl = new MyKomivo();
            gen = new MyGenetics(200,20);
            jFrame.add(dpl);
            jFrame.setSize(550,550);
            jFrame.setResizable(false);
            jFrame.setVisible(true);
            jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            new Thread(gen).start();

            new Thread(() -> dpl.drawLine()).start();;
        });
    }

}