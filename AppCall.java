import java.util.HashMap;

public class ProjectCall{
    public static void main(String[] args) throws Exception {
        String location = "Fresno";
        HashMap<String, String> hm = App.main(location);           //This is call to the App class with "location" parameter

        hm.forEach((k, v) -> System.out.println(k + " : "  + v));  //For each loop to chec values 

    }
}
