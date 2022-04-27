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
import java.util.HashMap;
import java.util.Map;
import com.google.gson.*;
import com.google.gson.reflect.*;

//https://code.google.com/archive/p/json-simple/downloads
//https://search.maven.org/artifact/com.google.code.gson/gson/2.8.6/jar
// test comment
/*
    while(on app) {
        Get location from user
        send request to weather API 
        Read web JSON and write to StringBuilder
        Append StringBuilder.toString() to data.json
        Read data.json and assign variables (create and return hashmap with valuable weather info)
        Print to JavaFX
        Clear data.json
        program.sleep()
    }
*/


public class App {

    public static void writeToJSON(String fileName,String str){
                                   
         try {                                                                   // Try block to check for exceptions
            BufferedWriter out = new BufferedWriter(new FileWriter(fileName));   // Open given file in append mode by creating an // object of BufferedWriter class                                        

            out.write(str);                                                      // Writing on output stream
            out.close();                                                         // Closing the connection
        }
        catch (IOException e) {                                                  // Catch block to handle the exceptions
            System.out.println("exception occurred: " + e);                      // Display message when exception occurs
        }
    }

    public static String getDataFromAPI(String LOCATION){
        
        String API_KEY = "5a0eb1ec7bae1494bd256e8b296423a2";
        String URL_STRING = "https://api.openweathermap.org/data/2.5/weather?q=" + LOCATION + "&appid=" + API_KEY+ "&units=metric";

        StringBuilder APIdata = new StringBuilder();

        try{
            URL url = new URL(URL_STRING);
            URLConnection connection = url.openConnection();
            BufferedReader rd = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            
            String line;
            while((line = rd.readLine()) != null){
                APIdata.append(line);               
            }

            rd.close();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } 

        return APIdata.toString();        
    }

    public static Map<String, Object> jsonToMap(String str){
        Map<String, Object> map = 
            new Gson().fromJson(str, new TypeToken<HashMap<String, Object>>() {}.getType());

        return map;
    }

    public static HashMap<String, String> extractingBasicInfo (String fileName){

        File input = new File(fileName);
        HashMap<String, String> HM = new HashMap<String, String>();

        try{
            JsonElement fileElement = JsonParser.parseReader(new FileReader(input));
            JsonObject fileObject = fileElement.getAsJsonObject();

            //Get basic fields
            String visibility = fileObject.get("visibility").getAsString();
            String cityName = fileObject.get("name").getAsString();
            String unixTimeStamp = fileObject.get("dt").getAsString();

            HM.put("visibility", visibility);
            HM.put("cityName", cityName);
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
        catch(FileNotFoundException e){
            e.getStackTrace();
        }
        return HM;
    }
        

    public static HashMap<String, String> main(String LOCATION) throws Exception {        
        
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
}
