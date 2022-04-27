import java.util.HashMap;

public class ProjectCall{
    public static void main(String[] args) throws Exception {
        String locationInput = "San Francisco";
    

        Location city1 = new Location(locationInput);
        

        HashMap<String, String> cityData1 = city1.getCityWeatherData();
       

        cityData1.forEach((key, value) ->{
            System.out.println(key + ": " + value);
        }); 
    }
}
