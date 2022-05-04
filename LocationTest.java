import org.junit.Test;
import org.junit.jupiter.api.Assertions;

public class LocationTest {
    
    @Test
    //Tests object instance and lowerCase
    public void cityNameToLowerCase(){
        Location location = new Location("Fresno");
        Assertions.assertEquals("fresno", location.getCityName());
        Assertions.assertNotEquals("Fresno", location.getCityName());
    }

    @Test
    //tests setNewCityName function
    public void newCityName(){
        Location location = new Location("Fresno");
        Assertions.assertEquals("santa barbara", location.setNewCityName("Santa Barbara"));
    }

    @Test
    //Tests main function. Make sure when city exists, it does not return null values
    public void mainDoesNotReturnNull(){  
        String city = "Fresno";
        Location location = new Location("Fresno");
        try {
            Assertions.assertNotNull(location.main(city));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    //Testing main functions for city that do not exist or cities with typos
    //This is supposed to return null values
    //This will be used to validate user input
    public void mainReturnsNull(){
        String city = "ahahahaha";
        Location location = new Location(city);
        try {
            Assertions.assertNull(location.main(city));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    //Tests if getCityWeatherData does not return null for valid city 
    public void getCityWeatherDataDoesNotReturnNull(){
        Location location = new Location("Fresno");
        try {
            Assertions.assertNotNull(location.getCityWeatherData());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @Test
    //tests if we are able to extract the basic info
    public void extractingBasicInfoDoesNotReturnNull(){
        String fileName = "data.json";
        Location location = new Location("Fresno");
        try {
            Assertions.assertNotNull(location.extractingBasicInfo(fileName));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    //Tests the function that will map nested objects returned by API
    public void jsonToMapDoesNotReturnNull(){
        String city = "Fresno";
        Location location = new Location("Fresno");
        String strToMap = location.getDataFromAPI(city);
        try {
            Assertions.assertNotNull(location.jsonToMap(strToMap));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    //Tests the function that sends the request to the API and returns a string
    public void getDataFromAPIDoesNotReturnNull(){
        String city = "Fresno";
        Location location = new Location("Fresno");
        try {
            Assertions.assertNotNull(location.getDataFromAPI(city));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
