package application;


import java.awt.image.RenderedImage;
import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;
import javax.imageio.ImageIO;
import javafx.application.Application;
import javafx.embed.swing.SwingFXUtils;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;



public class Main extends Application  {
	@Override
	public void start(Stage primaryStage) throws Exception{

			
			Group root = new Group();
			Scene scene = new Scene(root, 500, 500, Color.GRAY);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());

			MyDistanceTab D = new MyDistanceTab();
			D.init();
			//D.generatedPopul(10);
			
			
			for(int i=0;i<15;i++){
				for(int j=0;j<15;j++){
					Circle circle = new Circle(D.getTabPoint()[i].getX()*5,D.getTabPoint()[i].getY()*5,5);
					root.getChildren().add(circle);
				}
			}
			Line line=null;
			MyPoint temp=null,first=null,last=null;
			Set<MyPoint> points=D.generatedPopul(10000);
			for(MyPoint p1 : points){
				if(temp==null){
					temp=p1;
					first=p1;
				}else{
					line = new MyLine(temp,p1);
					root.getChildren().add(line);
					temp=p1;
					last=p1;
				}
			}
		
			root.getChildren().add(new MyLine(first,last));		
			//primaryStage.initStyle(StageStyle.UNDECORATED);
			primaryStage.setScene(scene);
			primaryStage.show();
			
			DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HHmmss");
			String s= dateFormat.format(new Date())+".png";
			File file = new File(s);
			RenderedImage renderedImage = SwingFXUtils.fromFXImage(scene.snapshot(null), null);
			ImageIO.write(
			        renderedImage, 
			        "png",
			        file);

	}
	
	public static void main(String[] args) {
		launch(args);
	}
}

class MyLine extends Line{
	
	private static int MULTI=5;
	MyLine(MyPoint p1,MyPoint p2){
		super(p1.getX()*MULTI,p1.getY()*MULTI,p2.getX()*MULTI,p2.getY()*MULTI);
	}	
}

class MyDistanceTab {		//klasa generuj¹ca dane oraz populacje

	private Set<Set<MyPoint>> population;
	private MyPoint [] myPointTab;
	private int [][] myDistanc;
	
	public MyDistanceTab() {	
	}

	public Set<MyPoint> generatedPopul(int cardinality){// generuje losowe dane do zbiorów zbioru
		
		population = new TreeSet<Set<MyPoint>>(new Comparator<Set<MyPoint>>(){
			@Override
		    public int compare(Set<MyPoint> row1,Set<MyPoint> row2) {
				Integer sum1=0,sum2=0;
				MyPoint temp=null;
				for(MyPoint r :row1){
					if(temp==null)
						temp=r;
					else{
						sum1+=temp.getDistans(r);
						temp=r;
					}
				}
				temp=null;
				for(MyPoint r :row2){
					if(temp==null)
						temp=r;
					else{
						sum2+=temp.getDistans(r);
						temp=r;
					}
				}
		        return sum1>sum2 ? 1 :-1;
		    }
		});
			
		for(int i=0;i<cardinality;i++){
			population.add(generatedGen());
		}		
		for(Set<MyPoint> p: population){
			return p;
		}
		return null;
	}
	
	public Set<MyPoint> generatedGen(){
	//generowanie listy genów czyli zbiór My Pointów losowy ustawionych 
		
		final int gen=15;
		Random rng = new Random(); 
		Set<MyPoint> generated = new LinkedHashSet<MyPoint>();
		while (generated.size() < gen){
		    MyPoint next = myPointTab[rng.nextInt(gen)];
		    generated.add(next);
		}	
		System.out.println(generated);
		return generated;		
	}
	
	public void init(){
	// inicjacja listy obiektów Punktów  oraz tabeli odleg³oœci (chyba niepotrzebne)
		int pointNum = 15;
		myPointTab=new MyPoint[pointNum];
		myPointTab[0]=new MyPoint(10,54);
		myPointTab[1]=new MyPoint(26,75);
		myPointTab[2]=new MyPoint(37,33);
		myPointTab[3]=new MyPoint(33,74);
		myPointTab[4]=new MyPoint(52,77);
		myPointTab[5]=new MyPoint(35,24);
		myPointTab[6]=new MyPoint(82,87);
		myPointTab[7]=new MyPoint(8,45);
		myPointTab[8]=new MyPoint(9,84);
		myPointTab[9]=new MyPoint(4,17);
		myPointTab[10]=new MyPoint(28,41);
		myPointTab[11]=new MyPoint(21,14);
		myPointTab[12]=new MyPoint(18,9);
		myPointTab[13]=new MyPoint(55,71);
		myPointTab[14]=new MyPoint(98,38);
		myDistanc=new int[pointNum][pointNum];
		
		for(int i=0;i<pointNum;i++){
			for(int j=0;j<pointNum;j++){
				myDistanc[i][j]=myPointTab[i].getDistans(myPointTab[j]);
				System.out.print(myDistanc[i][j]+" ");
			}
			System.out.println();
		}
	}
	public int [][] getTabDistance(){
		return myDistanc;
	}
	public MyPoint [] getTabPoint(){
		return myPointTab;
	}	
	
}

class MyPoint{
	private int x;
	private int y;
	private static int ID_POINT;
	
	MyPoint(int x,int y){
		
		ID_POINT++;
		setX(x);
		setY(y);
	}

	public int getDistans(MyPoint p){
	
	return (int) Math.sqrt(Math.pow(this.getX()-p.getX(),2)+Math.pow(this.getY()-p.getY(),2));		
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public String toString(){
		return " "+getX()+ " " +getY();
	}
}





