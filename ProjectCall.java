import java.util.HashMap;

public class ProjectCall{
    
    public boolean validateCityChar(String city) {
        return city.matches("([a-zA-Z]+|[a-zA-Z]+\\s[a-zA-Z]+)");
    }

    public static boolean cityExist(String cityName){
        
        if(cityName == null){
            return false;
        }        
        return true; 
    }
    
    public static void main(String[] args) throws Exception {
        String locationInput = "San Francisco";
    

        Location city1 = new Location(locationInput);
        

        HashMap<String, String> cityData1 = city1.getCityWeatherData();
       

        cityData1.forEach((key, value) ->{
            System.out.println(key + ": " + value);
        }); 
    }
}
