package application;
	
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;

import javax.imageio.ImageIO;

import javafx.application.Application;
import javafx.embed.swing.SwingFXUtils;
import javafx.stage.Stage;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			Group root = new Group();
			Scene scene = new Scene(root, 500, 500, Color.GRAY);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
			printPopulation(root);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	public static void saveSceneToImg(Scene sc){//zapis automatyczny do pliku z aktualn¹ dat¹ 
	
			
			DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HHmmss");
			String s= dateFormat.format(new Date())+".png";
			File file = new File("results/"+s);
			RenderedImage renderedImage = SwingFXUtils.fromFXImage(sc.snapshot(null), null);
			try {
				ImageIO.write(
				        renderedImage, 
				        "png",
				        file);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	public static void printPopulation(Group gr){
		
		MyFirstPop gen=null;
		gen = new MyFirstPop(2000,20);
		
		///*for(int i=0;i<20;i++){      // rysowanie punktów 
			//for(int j=0;j<20;j++){
				Circle circle = new Circle(28*5,41*5,5);
				gr.getChildren().add(circle);
				Circle circle2 = new Circle(98*5,3*5,5);
				gr.getChildren().add(circle2);
				Circle circle3 = new Circle(21*5,14*5,5);
				gr.getChildren().add(circle3);
			//}
		//}*/
		Line line=null;
		MyPoint temp=null,first=null,last=null;
		//Set<MyPoint> points=D.generatedPopul();
		Set<MyPoint> points=gen.getLastPop();
		for(MyPoint p1 : points){
			if(temp==null){
				temp=p1;
				first=p1;
			}else{
				line = new MyLine(temp,p1);
				gr.getChildren().add(line);
				temp=p1;
				last=p1;
			}
		}
		gr.getChildren().add(new MyLine(first,last));	
	
	}
}
class MyLine extends Line{
	
	private static final int MULTI=5;
	public MyLine(MyPoint p1,MyPoint p2){
		super(p1.getX()*MULTI,p1.getY()*MULTI,p2.getX()*MULTI,p2.getY()*MULTI);
	}	
}
