package javafxweatherapp;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
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
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 *
 * @author Lydia, Michael, Vini
 */
public class JavaFXweatherapp extends Application {
    Stage window;
    Scene scene, scene2, scene3;
    
    //thread used for cycle / updates
    static Thread tr = new Thread();
    
    //icon images and their sources
    Image image00 = new Image("icons/00.png");
    Image image1d = new Image("icons/01d.png");
    Image image1n = new Image("icons/01n.png");
    Image image2d = new Image("icons/02d.png");
    Image image2n = new Image("icons/02n.png");
    Image image3d = new Image("icons/03d.png");
    Image image3n = new Image("icons/03n.png");
    Image image4d = new Image("icons/04d.png");
    Image image4n = new Image("icons/04n.png");
    Image image9d = new Image("icons/09d.png");
    Image image9n = new Image("icons/09n.png");
    Image image10d = new Image("icons/10d.png");
    Image image10n = new Image("icons/10n.png");
    Image image11d = new Image("icons/11d.png");
    Image image11n = new Image("icons/11n.png");
    Image image13d = new Image("icons/13d.png");
    Image image13n = new Image("icons/13n.png");
    Image image50d = new Image("icons/50d.png");
    Image image50n = new Image("icons/50n.png");
    
    //for testing
    int i = 0;
    
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
        Label text1 = new Label("Enter city name:");
            text1.setFont(Font.font("Arial", FontWeight.BOLD,15));
        Label errorMessage = new Label("");
            errorMessage.setFont(Font.font("Arial", FontWeight.BOLD,15));
        
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
        GridPane.setConstraints(errorMessage, 0, 4);

        //add items to grid and sets the scene
        grid1.getChildren().addAll(text1, ulocation, btn1, errorMessage);
        grid1.setBackground(new Background(new BackgroundFill(Color.BISQUE, CornerRadii.EMPTY, Insets.EMPTY)));
        scene = new Scene(grid1, 400, 200);
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
        grid2.setHgap(0); //horizontal padding
        
        // Labels and fonts
        Label locationLabel = new Label("City name here");
            locationLabel.setFont(Font.font("Arial", FontWeight.BOLD,30));
        Label weatherTextLabel = new Label("Weather Info:");
            weatherTextLabel.setFont(Font.font("Arial", 20));
        Label line = new Label("-----------------------------------------");
        Label line2 = new Label("----------------");
        Label mainWeatherLabel = new Label("Main weather description here");
        Label weatherDescriptionLabel = new Label("description here");
        Label temperatureLabel = new Label("temperature here");
        Label humidityLabel = new Label("humidity here");
        Label visibilityLabel = new Label("visibility here");
        Label windDegreeLabel = new Label("wind degree here");
        Label windSpeedLabel = new Label("wind speed here");
        Label text = new Label("Go to Ambient Window?");
        
        // Buttons
        Button btn2 = new Button("GO");
        Button search = new Button("Search");
        
        // Image View
        ImageView imageView = new ImageView();
        
        // GridPane constrains for layout (labelname, column, row)
        GridPane.setConstraints(locationLabel, 0, 0);
        GridPane.setConstraints(search, 1, 0);
        GridPane.setConstraints(line, 0, 1);
        GridPane.setConstraints(line2, 1, 1);
        GridPane.setConstraints(weatherTextLabel, 0, 2);
        GridPane.setConstraints(imageView, 1, 2);
        GridPane.setConstraints(mainWeatherLabel, 0, 3);
        GridPane.setConstraints(weatherDescriptionLabel, 0, 4);
        GridPane.setConstraints(temperatureLabel, 0, 5);
        GridPane.setConstraints(humidityLabel, 0, 6);
        GridPane.setConstraints(visibilityLabel, 0, 7);
        GridPane.setConstraints(windDegreeLabel, 0, 8);
        GridPane.setConstraints(windSpeedLabel, 0, 9);
        GridPane.setConstraints(text, 0, 10);
        GridPane.setConstraints(btn2, 1, 10);

                         
        
        //add items to grid2 and set the scene
        grid2.getChildren().addAll(locationLabel, btn2, search, line, line2, weatherTextLabel, mainWeatherLabel, imageView, weatherDescriptionLabel, temperatureLabel,
            humidityLabel, visibilityLabel, windDegreeLabel, windSpeedLabel, text);
        grid2.setBackground(new Background(new BackgroundFill(Color.BISQUE, CornerRadii.EMPTY, Insets.EMPTY)));
        largeWindow.getChildren().addAll(grid2);
        scene2 = new Scene(largeWindow, 400, 450);
        
        
        
        
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
            locationName.setFont(Font.font("Arial", FontWeight.BOLD,25));
        Label temperature = new Label("Temperature");
        Label weather = new Label("Weather");
        Label humid = new Label("Humidity");
        Label wind = new Label("Wind Speed");
        Label testing = new Label("Testing variable");
        
        // Buttons
        Button backbtn = new Button("Back");
        Button updatebtn = new Button("Update");
        
        // ImageView
        ImageView imageView2 = new ImageView();

        
        // Constraints
        GridPane.setConstraints(locationName, 0, 0);
        GridPane.setConstraints(updatebtn, 2, 0);
        GridPane.setConstraints(backbtn, 3, 0);
        GridPane.setConstraints(temperature, 0, 1);
            GridPane.setRowIndex(imageView2, 2);
            GridPane.setColumnIndex(imageView2, 3);
            GridPane.setRowSpan(imageView2, 4);
        GridPane.setConstraints(weather, 2, 1);
        GridPane.setConstraints(humid, 0, 2);
        GridPane.setConstraints(wind, 2, 2);
        GridPane.setConstraints(testing, 0, 4);
            //GridPane.setColumnSpan(testing, 2);
        
        
        //add items to grid3 and set the scene
        grid3.getChildren().addAll(locationName, updatebtn, backbtn, temperature, imageView2, weather, humid, wind, testing);
        grid3.setBackground(new Background(new BackgroundFill(Color.BISQUE, CornerRadii.EMPTY, Insets.EMPTY)));
        smallWindow.getChildren().addAll(grid3);
        scene3 = new Scene(smallWindow, 400, 175);
   
    
        
        // ALL THE BUTTONS AND THEIR ACTION EVENTS ///////////////////////////
        
        // Button action for screen 1
        // Button takes into account city name validation
        btn1.setOnAction((ActionEvent event) -> {
            String city;
            city = ulocation.getText();
            
            if (validateCityChar(city)){
                Location test = new Location(city); 
                HashMap<String, String> hm = test.getCityWeatherData();
                if (cityExist(hm.get("name"))){
                    window.setScene(scene2);
                    System.out.println("Switched screens");
                    displayHashLargeWindow(ulocation, locationLabel, mainWeatherLabel, imageView, weatherDescriptionLabel, temperatureLabel,
                        humidityLabel, visibilityLabel, windDegreeLabel, windSpeedLabel);
                        errorMessage.setText("");
                }
                else {
                    System.out.println("City name error");
                    errorMessage.setText("*** Error! Check spelling on city name ***");
                }
            }
            else {
                System.out.println("City name error");
                errorMessage.setText("*** Error! Check spelling on city name ***");
            }
        });
        
        // Buttons action events for screen 2
        btn2.setOnAction((ActionEvent event) -> {
            window.setScene(scene3);
            System.out.println("Switched screens");
        });
        search.setOnAction((ActionEvent event) -> {
            window.setScene(scene);
            System.out.println("Switched screens");
        });
        
        // Button action for small window
        //update btn initiates updating cycle
        updatebtn.setOnAction((ActionEvent event) -> {
            updateCounter(ulocation, locationName, temperature, imageView2, weather, humid, wind, testing);
        });
        backbtn.setOnAction((ActionEvent event) -> {
            window.setScene(scene);
            System.out.println("Switched screens");
        });

    } // end of start
    

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       launch(args);
    }
    
    // METHODS used throughout the code above // // // // // // // // // // // //

    /*
    * INFINITE LOOP THAT UPDATES
    * Calls on displayFromHash
    */
    public void updateCounter(TextField userCity, Label location, Label temperature, ImageView imageView, Label weather, Label humid, Label wind, Label testing){
        testing.setText("API Call count: " + String.valueOf(i));
        displayFromHash(userCity, location, temperature, imageView, weather, humid, wind);
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(10), e -> {
            i++;
            testing.setText("API Call count: " + String.valueOf(i));
            displayFromHash(userCity, location, temperature, imageView, weather, humid, wind);
        }));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }
    
    /*
    * DISPLAY INFO FOR SMALL WINDOW
    * Method that takes the user input for city
    * and displays the information from the API HashMap
    */
    public void displayFromHash(TextField cityInput, Label location, Label temperature, ImageView imageView, Label weather, Label humid, Label wind){
        String city;
        String iconKey;
        city = cityInput.getText();
        Location test = new Location(city);
            HashMap<String, String> hm = test.getCityWeatherData();
                location.setText(hm.get("name"));
                temperature.setText("temp: " + hm.get("temperature") + " F");
                weather.setText("weather: " + hm.get("mainDescription"));
                humid.setText("humidity: " + hm.get("humidity")+ "%");
                wind.setText("wind: " + hm.get("windSpeed") + " mph");
                iconKey = hm.get("icon");
            switch (iconKey){
            case "01d":
                imageView.setImage(image1d);
                break;
            case "01n":
                imageView.setImage(image1n);
                break;
            case "02d":
                imageView.setImage(image2d);
                break;
            case "02n":
                imageView.setImage(image2n);
                break;
            case "03d":
                imageView.setImage(image3d);
                break;
            case "03n":
                imageView.setImage(image3n);
                break;
            case "04d":
                imageView.setImage(image4d);
                break;
            case "04n":
                imageView.setImage(image4n);
                break;
            case "09d":
                imageView.setImage(image9d);
                break; 
            case "09n":
                imageView.setImage(image9n);
                break; 
            case "10d":
                imageView.setImage(image10d);
                break;
            case "10n":
                imageView.setImage(image10n);
                break;
            case "11d":
                imageView.setImage(image11d);
                break; 
            case "11n":
                imageView.setImage(image11n);
                break;
            case "13d":
                imageView.setImage(image13d);
                break;
            case "13n":
                imageView.setImage(image13n);
                break;
            case "50d":
                imageView.setImage(image50d);
                break; 
            case "50n":
                imageView.setImage(image50n);
                break; 
            default:
                imageView.setImage(image00);
        }   
    }
    
    /*
    * DSPLAY INFO FOR LARGER WINDOW
    * Takes user input for city and displays information
    * ONLY DISPLAYS ONCE
    */
    public void displayHashLargeWindow(TextField cityInput, Label location, Label mainWeather, ImageView imageView, 
            Label weatherDescription, Label temperature, Label humidity, Label visibility, Label windDegree, Label windSpeed){
            
            String city;
            String iconKey;
            city = cityInput.getText();
            Location test = new Location(city);
            
            HashMap<String, String> hm = test.getCityWeatherData();
                location.setText(hm.get("name"));
                mainWeather.setText("weather: " + hm.get("mainDescription"));
                weatherDescription.setText("description: " + hm.get("description"));
                temperature.setText("temp: " + hm.get("temperature") + " F");
                humidity.setText("humidity: " + hm.get("humidity") + "%");
                visibility.setText("visibility: " + hm.get("visibility"));
                windDegree.setText("wind degree: " + hm.get("windDegree"));
                windSpeed.setText("wind speed: " + hm.get("windSpeed") + " mph");
                iconKey = hm.get("icon");
            switch (iconKey){
            case "01d":
                imageView.setImage(image1d);
                break;
            case "01n":
                imageView.setImage(image1n);
                break;
            case "02d":
                imageView.setImage(image2d);
                break;
            case "02n":
                imageView.setImage(image2n);
                break;
            case "03d":
                imageView.setImage(image3d);
                break;
            case "03n":
                imageView.setImage(image3n);
                break;
            case "04d":
                imageView.setImage(image4d);
                break;
            case "04n":
                imageView.setImage(image4n);
                break;
            case "09d":
                imageView.setImage(image9d);
                break; 
            case "09n":
                imageView.setImage(image9n);
                break; 
            case "10d":
                imageView.setImage(image10d);
                break;
            case "10n":
                imageView.setImage(image10n);
                break;
            case "11d":
                imageView.setImage(image11d);
                break; 
            case "11n":
                imageView.setImage(image11n);
                break;
            case "13d":
                imageView.setImage(image13d);
                break;
            case "13n":
                imageView.setImage(image13n);
                break;
            case "50d":
                imageView.setImage(image50d);
                break; 
            case "50n":
                imageView.setImage(image50n);
                break; 
            default:
                imageView.setImage(image00);
            }   
    }
    

    /*
    * CITY NAME VALIDATION METHODS
    */
    public boolean validateCityChar(String city) {
        return city.matches("([a-zA-Z]+|[a-zA-Z]+\\s[a-zA-Z]+)");
    }

    public static boolean cityExist(String cityName){
        
        if(cityName == null){
            return false;
        }        
        return true; 
    }
}
