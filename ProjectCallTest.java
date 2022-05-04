import java.util.HashMap;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;

public class ProjectCallTest {

    @Test
    //tests function that will check if user's input contain any non-valid characters
    //Return false if there is any, and true if input is partialy valid
    public void validateCityCharTest(){
        Assertions.assertEquals(true, validateCityChar("Fresno"));
        Assertions.assertEquals(true, validateCityChar("Santa Barbara"));
        Assertions.assertEquals(false, validateCityChar("c1tyC@nnot!ncludeNumbers/symbols"));
        Assertions.assertEquals(false, validateCityChar("12%@^#12&*"));
    }
    
    /*
        Testing the second function to validate user's input
        this function will return false if city inputed does not exist
        and will return true if city is valid
    */

    @Test
    //This will test if cityExist function will return true for valid city
    public void cityExistTest(){

        String cityNames[] = {"Fresno", "Santa Barbara", "San Francisco", "Toronto", "London" , "Tokio"};

        for(int i = 0; i < 6; i++){
            Location location = new Location(cityNames[i]);
            HashMap<String, String> locationData = location.getCityWeatherData();
            Assertions.assertEquals(true, cityExist(locationData.get("name"))); 
        }        
    }

    @Test
    //This will test if cityExist function will return false for non existent city input
    public void cityExistTestfalse(){
        String cityNames[] = {"Los Angelis", "Santi Barbiri", "Torunto", "NotAName", "Londons" , "Frisno"};

        for(int i = 0; i < 6; i++){
            Location location = new Location(cityNames[i]);
            HashMap<String, String> locationData = location.getCityWeatherData();
            Assertions.assertEquals(false, cityExist(locationData.get("name"))); 
        }  

    }

    @Test
    //This will test if setImage function's switch statement is working properly and returning correct path to image
    public void setImagetest(){
        String IdArray[] = {
        "01d", "01n", "02d", "02n", "03d", "03n", "04d", "04n", 
        "09d", "09n", "10d", "10n", "11d", "11n", "13d", "13n",
        "50d", "50n"    
        };
        String expectedString = "image";

        for(int i = 0; i < IdArray.length; i++){
            expectedString += IdArray[i];

            Assertions.assertEquals(expectedString, setImage(IdArray[i]));

            expectedString = "image";
        }
    }

    //Validation input functions
    public boolean validateCityChar(String city) {
        return city.matches("([a-zA-Z]+|[a-zA-Z]+\\s[a-zA-Z]+)");
    }

    public static boolean cityExist(String cityName){
        
        if(cityName == null){
            return false;
        } else{
            return true;
        }       
    }

    //Selection icon function
    public String setImage(String str){
        String iconKey = str;
        switch (iconKey){
            case "01d":
                return "image01d";
                
            case "01n":
                return "image01n";
            
            case "02d":
                return "image02d";
            
            case "02n":
                return "image02n";
            
            case "03d":
                return "image03d";
            
            case "03n":
                return "image03n";
            
            case "04d":
                return "image04d";
            
            case "04n":
                return "image04n";
            
            case "09d":
                return "image09d";
            
            case "09n":
                return "image09n";
            
            case "10d":
                return "image10d";
            
            case "10n":
                return "image10n";
            
            case "11d":
                return "image11d";
            
            case "11n":
                return "image11n";
            
            case "13d":
                return "image13d";
            
            case "13n":
                return "image13n";
            
            case "50d":
                return "image50d";
            
            case "50n":
                return "image50n";

            default:
                return "image1d";            
        }  
    }
}
