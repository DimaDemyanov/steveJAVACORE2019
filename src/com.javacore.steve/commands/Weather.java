package commands;

import commandsUtils.Command;
import main.Steve;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

import com.google.gson.JsonParser;
import java.util.Properties;

public class Weather extends Command {

    public Weather() {
        name = "weather";
    }

    @Override
    public void perform(Steve steve, String [] options) {
        Properties properties = steve.getProperties();
        try {
            HttpResponse<JsonNode> response = Unirest.get(properties.getProperty("weather-Api-URL"))
                    .queryString("q", properties.getProperty("latitude") + " " + properties.getProperty("longitude"))
                    .queryString("key", properties.getProperty("weather-Api-Key"))
                    .asJson();

            JsonElement obj = new JsonParser().parse(response.getBody().toString());
            JsonObject jsonObject = null;
            if(obj.isJsonObject()) {
                jsonObject = obj.getAsJsonObject();
            }
            System.out.println("Current temperature is: " + jsonObject.getAsJsonObject("current").get("temp_c") + '\n'
                    + "Clouds: " + jsonObject.getAsJsonObject("current").get("cloud")+ '\n'
                    + "Wind direction: " + jsonObject.getAsJsonObject("current").get("wind_dir"));
        } catch (UnirestException e) {
            e.printStackTrace();
        }
    }

}
