package javafxweatherapp;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/*
* @author Lydia, Michael, Vini
*/
public class JavaFXweatherapp extends Application {
    Stage window;
    Scene scene, scene2;
    static Thread tr = new Thread();
    
    @Override
    public void start(Stage primaryStage) {
        window = primaryStage;
        window.setTitle("Weather App");
        
        /*
        * SEARCH CITY WINDOW FOR USER INPUT
        * This is the first opening window
        * Asks for city from user
        * Takes input and sends to API
        */
        GridPane grid1 = new GridPane();
        grid1.setPadding(new Insets(10,10,10,10));
        grid1.setVgap(8); //vertical padding
        grid1.setHgap(8); //horizontal padding
        
        // Labels
        Label text1 = new Label("Enter Location:");
        
        // Textfields
        TextField ulocation = new TextField();
            ulocation.setPromptText("ex: Fresno");
        
        // Buttons
        Button btn1 = new Button();
            btn1.setText("Search");
        
        // Button actions
        
        // GridPane constrains for layout
        GridPane.setConstraints(text1, 0, 0);
        GridPane.setConstraints(ulocation, 0, 1);
        GridPane.setConstraints(btn1, 1, 1);

        //add items to grid and sets the scene
        grid1.getChildren().addAll(text1, ulocation, btn1);
        scene = new Scene(grid1, 300, 250);
        window.setScene(scene);
        window.show();
    
        
        
        /*
        * SMALL AMBIENT UPDATING WINDOW
        * Small weather window layout
        * Will display information from API thorugh constant updates
        */
        StackPane smallWindow = new StackPane();
        GridPane grid2 = new GridPane();
        grid2.setPadding(new Insets(10, 10, 10, 10)); //add padding for each cell
        grid2.setVgap(8); //vertical padding
        grid2.setHgap(8); //horizontal padding
    
        // Labels
        Label locationName = new Label("Fresno, CA");
        Label temperature = new Label("72 F");
        Label time = new Label("12:30");
        Label airQ = new Label("AQ: Great");
        Label date = new Label("4/10/2022");
        
        // Buttons
        Button updatebtn = new Button();
            updatebtn.setText("update");
        Button expandbtn = new Button();
            expandbtn.setText("Go Back");
        
        // Button actions
        expandbtn.setOnAction((ActionEvent event) -> {
            window.setScene(scene);
            System.out.println("Switched screens");
        });
        btn1.setOnAction((ActionEvent event) -> {
            window.setScene(scene2);
            System.out.println("Switched screens");
        });
        
        // Constraints
        GridPane.setConstraints(locationName, 0, 0);
        GridPane.setConstraints(updatebtn, 1, 0);
        GridPane.setConstraints(expandbtn, 2, 0);
        GridPane.setConstraints(temperature, 0, 1);
        GridPane.setConstraints(time, 2, 1);
        GridPane.setConstraints(airQ, 0, 2);
        GridPane.setConstraints(date, 2, 2);
        
        //add items to grid2 and set the scene
        grid2.getChildren().addAll(locationName, updatebtn, expandbtn, temperature, time, airQ, date);
        smallWindow.getChildren().addAll(grid2);
        scene2 = new Scene(smallWindow,  250, 100);
    }
    

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}
