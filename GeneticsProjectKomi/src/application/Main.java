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
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;


public class Main extends Application {
	
	@Override
	public void start(Stage primaryStage) {
		try {
			Group root = new Group();
			Button saveBut=new Button("save");
			Scene scene = new Scene(root, 500, 500, Color.GRAY);
			
			saveBut.setOnAction(new EventHandler<ActionEvent>() {
		            @Override
		            public void handle(ActionEvent event) {
		                saveSceneToImg(scene);
		            }
		        });
			root.getChildren().add(saveBut);
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
		
		MyGenetics gen=null;
		gen = new MyGenetics(200,20);
		
		for(int i=0;i<MyPoint.getID_POINT();i++){      // rysowanie punktów 
			for(int j=0;j<MyPoint.getID_POINT();j++){
				Circle circle = new Circle(gen.getMyPointTab()[i].getX()*5,gen.getMyPointTab()[i].getY()*5,5);
				gr.getChildren().add(circle);
			}
		}
		Line line=null;
		MyPoint temp=null,first=null,last=null;
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
