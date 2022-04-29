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
        * LARGER WINDOW WITH MORE WEATHER INFORMATION
        * This window displays lots of information from API
        * This is not a cycle but a one time display of weather
        * User can go back and search for a different city -- repeating process
        * Or user can switch to smaller ambient screen
        */
        StackPane largeWindow = new StackPane();
        GridPane grid2 = new GridPane();
        grid2.setPadding(new Insets(10, 10, 10, 10)); //add padding for each cell
        grid2.setVgap(8); //vertical padding
        grid2.setHgap(8); //horizontal padding
        
        // Labels
        Label locationLabel = new Label("City name here");
        Label weatherTextLabel = new Label("Weather Info:");
        Label mainWeatherLabel = new Label("Main weather description here");
        Label weatherDescriptionLabel = new Label("description here");
        Label temperatureLabel = new Label("temperature here");
        Label humidityLabel = new Label("humidity here");
        Label visibilityLabel = new Label("visibility here");
        Label windDegreeLabel = new Label("wind degree here");
        Label windSpeedLabel = new Label("wind speed here");
        
        // Buttons
        Button btn2 = new Button("+");
        Button search = new Button("Search");
        
        // Buttons action events
        btn2.setOnAction((ActionEvent event) -> {
            window.setScene(scene3);
            System.out.println("Switched screens");
        });
        search.setOnAction((ActionEvent event) -> {
            window.setScene(scene);
            System.out.println("Switched screens");
        });
        
        // GridPane constrains for layout (labelname, column, row)
        GridPane.setConstraints(locationLabel, 0, 0);
        GridPane.setConstraints(btn2, 1, 0);
        GridPane.setConstraints(search, 2, 0);
        GridPane.setConstraints(weatherTextLabel, 0, 1);
        GridPane.setConstraints(mainWeatherLabel, 0, 2);
        GridPane.setConstraints(weatherDescriptionLabel, 0, 3);
        GridPane.setConstraints(temperatureLabel, 0, 4);
        GridPane.setConstraints(humidityLabel, 0, 5);
        GridPane.setConstraints(visibilityLabel, 0, 6);
        GridPane.setConstraints(windDegreeLabel, 0, 7);
        GridPane.setConstraints(windSpeedLabel, 0, 8);
                          
        
        //add items to grid2 and set the scene
        grid2.getChildren().addAll(locationLabel, btn2, search, weatherTextLabel, mainWeatherLabel, weatherDescriptionLabel, temperatureLabel,
            humidityLabel, visibilityLabel, windDegreeLabel, windSpeedLabel );
        largeWindow.getChildren().addAll(grid2);
        scene2 = new Scene(largeWindow,  400, 400);
        
        
        
        
        /*
        * SMALL AMBIENT UPDATING WINDOW
        * Small weather window layout
        * Will display information from API thorugh constant updates
        */
        StackPane smallWindow = new StackPane();
        GridPane grid3 = new GridPane();
        grid3.setPadding(new Insets(10, 10, 10, 10)); //add padding for each cell
        grid3.setVgap(8); //vertical padding
        grid3.setHgap(8); //horizontal padding
    
        // Labels
        Label locationName = new Label("City");
        Label temperature = new Label("Temperature");
        Label weather = new Label("Weather");
        Label humid = new Label("Humidity");
        Label wind = new Label("Wind Speed");
        
        // Icon
        Image icon = new Image("File:icons/01d.png");
        
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
        
        // Constraints
        GridPane.setConstraints(locationName, 0, 0);
        GridPane.setConstraints(updatebtn, 1, 0);
        GridPane.setConstraints(expandbtn, 2, 0);
        GridPane.setConstraints(temperature, 0, 1);
        GridPane.setConstraints(icon, 1, 1);
        GridPane.setConstraints(time, 2, 1);
        GridPane.setConstraints(airQ, 0, 2);
        GridPane.setConstraints(date, 2, 2);
        
        //add items to grid3 and set the scene
        grid3.getChildren().addAll(locationName, updatebtn, expandbtn, temperature, icon, time, airQ, date);
        smallWindow.getChildren().addAll(grid3);
        scene3 = new Scene(smallWindow,  400, 150);
        
        
        
        // Button action for screen 1
        btn1.setOnAction((ActionEvent event) -> {
            window.setScene(scene2);
            System.out.println("Switched screens");
            displayHashLargeWindow(ulocation, locationLabel, mainWeatherLabel, weatherDescriptionLabel, temperatureLabel,
                    humidityLabel, visibilityLabel, windDegreeLabel, windSpeedLabel);
        });
        // Button action for small window (currently only displays once)
         updatebtn.setOnAction((ActionEvent event) -> {
            displayFromHash(ulocation, locationName, temperature, weather, humid, wind);
        });
    }
    

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
    /*
    * DISPLAY INFO FOR SMALL WINDOW
    * Method that takes the user input for city
    * and displays the information from the API HashMap
    */
    public static void displayFromHash(TextField cityInput, Label location, Label temperature, Label weather, Label humid, Label wind){
        String city;
        city = cityInput.getText();
        Location test = new Location(city);
            HashMap<String, String> hm = test.getCityWeatherData();
                location.setText(hm.get("name"));
                temperature.setText("temp: " + hm.get("temperature") + " F");
                weather.setText("weather: " + hm.get("mainDescription"));
                humid.setText("humidity: " + hm.get("humidity"));
                wind.setText("wind: " + hm.get("windSpeed") + "mph");
    }
    
    /*
    * DSPLAY INFO FOR LARGER WINDOW
    * Takes user input for city and displays information
    * ONLY DISPLAYS ONCE
    */
    public static void displayHashLargeWindow(TextField cityInput, Label location, Label mainWeather, Label weatherDescription,
        Label temperature, Label humidity, Label visibility, Label windDegree, Label windSpeed){
            String city;
            city = cityInput.getText();
            Location test = new Location(city);
            
            HashMap<String, String> hm = test.getCityWeatherData();
                location.setText(hm.get("name"));
                mainWeather.setText("weather: " + hm.get("mainDescription"));
                weatherDescription.setText("description: " + hm.get("description"));
                temperature.setText("temp: " + hm.get("temperature") + " F");
                humidity.setText("humidity: " + hm.get("humidity"));
                visibility.setText("visibility: " + hm.get("visibility"));
                windDegree.setText("wind degree: " + hm.get("windDegree"));
                windSpeed.setText("wind speed: " + hm.get("windSpeed") + "mph");
    }
}
