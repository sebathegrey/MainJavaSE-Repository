package application;

import logic.*;
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
			
			
			for(int i=0;i<8;i++){
				for(int j=0;j<8;j++){
					Circle circle = new Circle(D.getTabPoint()[i].getX()*5,D.getTabPoint()[i].getY()*5,5);
					root.getChildren().add(circle);
				}
			}
			Line line=null;
			MyPoint temp=null,first=null,last=null;
			Set<MyPoint> points=D.generatedPopul(1000);
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
			File file = new File("results/"+s);
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




