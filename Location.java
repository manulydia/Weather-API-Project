import java.util.HashMap;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Map;
import com.google.gson.*;
import com.google.gson.reflect.*;


public class Location {
    String cityName;

    public Location(String cityName){                                          //Location class constructor
        this.cityName = cityName.toLowerCase();                                //Just uses cityName parameter
    }

    public String setNewCityName(String newCityName){                          //Seter for cityName
        cityName = newCityName.toLowerCase(); 
        return cityName;                                                       //In case we want to change objects location
    }

    public String getCityName(){                                               //cityName getter
        return cityName;
    }

    private static void writeToJSON(String fileName, String str){
                                   
        try {                                                                   // Try block to check for exceptions
           BufferedWriter out = new BufferedWriter(new FileWriter(fileName));   // Open given file in append mode by creating an // object of BufferedWriter class                                        

           out.write(str);                                                      // Writing on output stream
           out.close();                                                         // Closing the connection
       }
       catch (IOException e) {                                                  // Catch block to handle the exceptions
           System.out.println("exception occurred: " + e);                      // Display message when exception occurs
           System.out.println(e.toString());
       }
   }

    public String getDataFromAPI(String LOCATION){
        
        //creating Constant API_KEY and URL_STRING
        String API_KEY = "61801fa219f237e8d8118308535cc94e";
        String URL_STRING = "https://api.openweathermap.org/data/2.5/weather?q=" + LOCATION + "&appid=" + API_KEY+ "&units=imperial";

        //String builder object to append the lines of data provided my API in the web
        StringBuilder APIdata = new StringBuilder();

        /*  Try block
            Create an URL object
            Open a connection with the url provided above
            reads it if next line isn't null
            While loop to read every line and append to String builder
        */
        try{
            URL url = new URL(URL_STRING);
            URLConnection connection = url.openConnection();
            BufferedReader rd = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            
            String line;
            while((line = rd.readLine()) != null){
                APIdata.append(line);               
            }
            
            //close BufferedReader connection
            rd.close();

        /*  Catch blocks
            catch any malformed URL provided 
            and any problems with input and output
        */
        } catch (MalformedURLException e) {
            e.printStackTrace();
            System.out.println("The URL is not valid. Check city name and getDataFromAPI method");
            System.out.println(e.toString());
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Exception with I/O inside getDataFromAPI method. Check city name");
            System.out.println(e.toString());
        } 
        
        //returns APIdata in String format
        return APIdata.toString();        
    }

    /*  jsonToMap method maps nested object in json format
        Takes a String for the key which value is/are object/s
        It uses gson library to map and returns a new simpler map for key       
    */
    public Map<String, Object> jsonToMap(String str){

        Map<String, Object> map = new Gson().fromJson(
            str, new TypeToken<HashMap<String, Object>>() {}.getType());
        
            return map;
    }

    /*  extractBasicInfo method extract all simple objects Key: value
        and also a key: array of objects
        This uses the local json file instead of a String
        returns a hashMap <String, String> with data reorganized
    */
    public HashMap<String, String> extractingBasicInfo (String fileName){

        //Creating file to be read and hashMap
        File input = new File(fileName);
        HashMap<String, String> HM = new HashMap<String, String>();

        /**
         * Try block:
         * Get basic fields key: value and put to hashMap
         * Get elements from array of objects in JSON from "weather" section (which is the only array)
         */
        try{
            JsonElement fileElement = JsonParser.parseReader(new FileReader(input));
            JsonObject fileObject = fileElement.getAsJsonObject();

            //Geting basic fields
            String visibility = fileObject.get("visibility").getAsString();
            String cityName = fileObject.get("name").getAsString();
            String unixTimeStamp = fileObject.get("dt").getAsString();

            HM.put("visibility", visibility);
            HM.put("name", cityName);
            HM.put("unixTimeStamp", unixTimeStamp);

            //Get elements from array of objects in JSON from "weather" section
            //ID, Main, Description, Icon
            JsonArray weatherMainInfo = fileObject.get("weather").getAsJsonArray();            
            for (JsonElement weatherElement : weatherMainInfo){
                JsonObject weatherJsonObject = weatherElement.getAsJsonObject();

                String cityId = weatherJsonObject.get("id").getAsString();
                String main = weatherJsonObject.get("main").getAsString();
                String description = weatherJsonObject.get("description").getAsString();
                String icon = weatherJsonObject.get("icon").getAsString();

                HM.put("id", cityId);
                HM.put("mainDescription", main);
                HM.put("description", description);
                HM.put("icon", icon);
            }            
        }
        // handles File not found exception
        catch(FileNotFoundException e){
            e.getStackTrace();
            System.out.println("Error extractingBasicInfo method ");
            System.out.println(e.toString());
        }
        return HM;
    }

    /**
     * 
     * @param LOCATION
     * @return 
     * @throws Exception
     * 
     * This is the Function of the functions. The one to rule them all
     * it is initiated by LocationObject created in in the main class when getCityWeatherData is called
     * It coordinates the functions above to get data online, organize it, and handle it back so it can be displayed
     * Returns a hashMap <String, String>
     */
    public HashMap<String, String> main(String LOCATION) throws Exception {        
        
        //Local json file location
        String FILE_NAME = "data.json";

        
        String data = getDataFromAPI(LOCATION);
            
        writeToJSON(FILE_NAME, data);

        HashMap<String, String> mainWeatherInfoHashMap = extractingBasicInfo(FILE_NAME);


        Map<String, Object> restMap = jsonToMap(data);
        Map<String, Object> mainMap = jsonToMap(restMap.get("main").toString());
        Map<String, Object> windMap = jsonToMap(restMap.get("wind").toString());
        Map<String, Object> sysMap = jsonToMap(restMap.get("sys").toString());

        mainWeatherInfoHashMap.put("temperature", mainMap.get("temp").toString());
        mainWeatherInfoHashMap.put("FeelsLike", mainMap.get("feels_like").toString());
        mainWeatherInfoHashMap.put("minTemp", mainMap.get("temp_min").toString());
        mainWeatherInfoHashMap.put("maxTemp", mainMap.get("temp_max").toString());
        mainWeatherInfoHashMap.put("pressure", mainMap.get("pressure").toString());
        mainWeatherInfoHashMap.put("humidity", mainMap.get("humidity").toString());
           
        mainWeatherInfoHashMap.put("windSpeed", windMap.get("speed").toString());
        mainWeatherInfoHashMap.put("windDegree", windMap.get("deg").toString());
            
        mainWeatherInfoHashMap.put("country", sysMap.get("country").toString());      

        return mainWeatherInfoHashMap;                   
    }


    /**
     * This is the function used to call the functions above from the main class
     * Returns all data needed in a HashMap     * 
     */
    public HashMap<String, String> getCityWeatherData(){
        
        HashMap<String, String> hm = new HashMap<>();
        try {
            hm = main(cityName);
            return hm;
        } catch (Exception e) {            
            e.printStackTrace();
        }  
        return hm;
    } 
    /** LIST OF KEYS IN HASHMAP:
      
        country
        FeelsLike      
        visibility     
        mainDescription
        unixTimeStamp  
        icon
        maxTemp        
        description    
        pressure       
        minTemp        
        name
        temperature    
        humidity       
        windDegree     
        id
        windSpeed   

     */  
}
