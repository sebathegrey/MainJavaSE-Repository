package application;

import java.awt.Image;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.awt.event.ActionListener;


import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Main extends Application{
    public static void main(String[] args) { 
        launch(args);       
    }

    @Override
    public void start(final Stage primaryStage) {
        primaryStage.setTitle("Hello World!");
        Button btn = new Button();
        btn.setText("Say 'Hello World'");
        btn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                System.out.println("Hello World!");            }
        });

        StackPane root = new StackPane();
        root.getChildren().add(btn);
        primaryStage.setScene(new Scene(root, 300, 250));
        primaryStage.show();

        if (SystemTray.isSupported()) {         
            SystemTray tray = SystemTray.getSystemTray();
            Image image = Toolkit.getDefaultToolkit().getImage("seba.jpg");
            PopupMenu popup = new PopupMenu();
            MenuItem item = new MenuItem("Exit");

            popup.add(item);

            TrayIcon trayIcon = new TrayIcon(image, "Amr_Trial", popup);

            ActionListener listener = new ActionListener() {                
                @Override
                public void actionPerformed(java.awt.event.ActionEvent arg0) {
                	Platform.runLater(new Runnable() {
                	    public void run() {
                    System.exit(0);                 
                	    }
                	}); }             
            };                       

            ActionListener listenerTray = new ActionListener() {                
                @Override
                public void actionPerformed(java.awt.event.ActionEvent arg0) {
                	Platform.runLater(new Runnable() {
                	    public void run() {
                	        primaryStage.hide();
                	    }
                	});
                }                   
            };            

            trayIcon.addActionListener(listenerTray);
            item.addActionListener(listener);

            try{
              tray.add(trayIcon);
            }catch (Exception e) {
              System.err.println("Can't add to tray");
            }
          } else {
            System.err.println("Tray unavailable");
          } 
        //
    }
}