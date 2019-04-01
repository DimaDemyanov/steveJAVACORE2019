package Commands;

import Main.Steve;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

import com.google.gson.JsonParser;
import java.util.Properties;

public class Weather extends Command {

    @Override
    public void perform(Steve steve) {
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

    //   @Override
//    public void perform() {
//        Properties props = steve.getProperties();
//        try {
//            Map<String, String> parameters = new HashMap<>();
//            parameters.put("lat", props.getProperty("latitude"));
//            parameters.put("lon", props.getProperty("longitude"));
//            parameters.put("APPID", props.getProperty("weather-Api-Key"));
//            URL url = new URL("http://api.openweathermap.org/data/2.5/forecast?" + ParameterStringBuilder.getParamsString(parameters));
//            HttpURLConnection con = (HttpURLConnection) url.openConnection();
//            con.setRequestMethod("GET");
//
////            Uri uri = new Uri.Builder()
////                    .scheme("http")
////                    .authority("api.openweathermap.org")
////                    .path("data/2.5/forecast")
////                    .appendQueryParameter("param1", foo)
////                    .appendQueryParameter("param2", bar)
////                    .build();
////            con.setDoOutput(true);
////            DataOutputStream out = new DataOutputStream(con.getOutputStream());
////            out.writeBytes(ParameterStringBuilder.getParamsString(parameters));
////            out.flush();
////            out.close();
//
//            con.connect();
//
//            BufferedReader in = new BufferedReader(
//                    new InputStreamReader(con.getInputStream()));
//            String inputLine;
//            StringBuffer content = new StringBuffer();
//            while ((inputLine = in.readLine()) != null) {
//                content.append(inputLine);
//            }
//            System.out.println(content);
//            in.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
}
