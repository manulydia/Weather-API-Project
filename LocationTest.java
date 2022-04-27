import org.junit.Test;
import org.junit.jupiter.api.Assertions;

public class LocationTest {
    
    @Test
    //Testing object instance and lowerCase
    public void cityNameToLowerCase(){
        Location location = new Location("Fresno");
        Assertions.assertEquals("fresno", location.getCityName());
        Assertions.assertNotEquals("Fresno", location.getCityName());

    }

    @Test
    //testing setNewCityName function
    public void newCityName(){
        Location location = new Location("Fresno");
        Assertions.assertEquals("santa barbara", location.setNewCityName("Santa Barbara"));
    }

    @Test
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
    public void getCityWeatherDataDoesNotReturnNull(){
        Location location = new Location("Fresno");
        try {
            Assertions.assertNotNull(location.getCityWeatherData());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @Test
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
