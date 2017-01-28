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

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Task;
import javafx.concurrent.Worker;
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


public class Main extends Application  {
	
	
	private static Scene scene;
	private static Group root;
	private boolean semafor2;
	static MyGenetics  gen;
	static Thread t1;
	@Override
	public void start(Stage primaryStage) {
		try {
			setSemafor2(true);
			root = new Group();
			Button saveBut=new Button("save");
			scene = new Scene(root, 500, 500, Color.GRAY);
			
			saveBut.setOnAction(e -> saveSceneToImg(scene));
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
		
		
		gen = new MyGenetics(200,20);
		t1 = new Thread(gen);
		t1.start();
/*		Thread t2 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				
				while(t1.isAlive()){
					if(gen.isSemafor()){

						gen.setSemafor(false);

					    Line line=null;
						MyPoint temp=null,first=null,last=null;
						Set<MyPoint> points=gen.getAktualPop();
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
						root.getChildren().clear();
						root.getChildren().add(new MyLine(first,last));

					}
				}
				
			}
		});
		Platform.runLater(new Runnable() {
			   @Override
			   public void run(){
		t2.start();
		
			   }
			   
			   });*/
		
/*		while(t1.isAlive());
		for(int i=0;i<MyPoint.getID_POINT();i++){      // rysowanie punktów 
			for(int j=0;j<MyPoint.getID_POINT();j++){
				
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
				root.getChildren().add(line);
				temp=p1;
				last=p1;
			}
		}
		root.getChildren().add(new MyLine(first,last));	
	*/
	
	}

	public boolean isSemafor2() {
		return semafor2;
	}

	public void setSemafor2(boolean semafor2) {
		this.semafor2 = semafor2;
	}


	
	
	
}













class MyLine extends Line{
	private static final int MULTI=5;
	public MyLine(MyPoint p1,MyPoint p2){
		super(p1.getX()*MULTI,p1.getY()*MULTI,p2.getX()*MULTI,p2.getY()*MULTI);
	}	
}
