package application;
	
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.shape.Polygon;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			BorderPane root = new BorderPane();
			Scene scene = new Scene(root,400,400);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		
		 Group g = new Group();
		 	
	        Polygon polygon = new Polygon();
	        
	        polygon.getPoints().addAll(new Double[]{
	        	300.0, 300.0,
	        	200.0, 100.0,
	            100.0, 300.0 });

	        g.getChildren().add(polygon);

	        scene.setRoot(g);
	        primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
