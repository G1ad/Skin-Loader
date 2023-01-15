package skins.skins;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public final class ApiManager {

    public static String makeApiCall(String url){
        try{

            URL httpUrl = new URL(url);

            HttpURLConnection connection = (HttpURLConnection) httpUrl.openConnection();
            connection.setRequestMethod("GET");
            connection.setDoInput(true);
            connection.setDoOutput(true);
            connection.connect();

            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuffer content = new StringBuffer();
            while((inputLine = reader.readLine()) !=null){
                    content.append(inputLine);
            }
            String responseString = content.toString();
            reader.close();
            connection.disconnect();
                return responseString;
        }catch(Exception e){
            System.out.println("Failed to make a GET request");
        }
        return null;
    }
}
