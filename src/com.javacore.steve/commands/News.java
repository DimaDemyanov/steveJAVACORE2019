package commands;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.mashape.unirest.request.HttpRequest;
import commandsUtils.Command;
import main.Steve;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.ParseException;
import java.util.Properties;
import java.util.Random;

public class News extends Command {

    public static final int MAX_ARTICLES = 3;
    public static final String [] TITLES = new String[]{
            "Apple",
            "Bitcoin",
            "Sports",
            "Buisness",
            "Animals"
    };

    public News() {
        options.addOption(new Option("t", "type", true, "Topic type"));
        name = "news";
    }

    @Override
    public void perform(Steve steve, String[] options) throws ParseException {
        CommandLine cmd = parser.parse(this.options, options);
        cmd.getArgList();
        Properties properties = steve.getProperties();
        try {
            HttpRequest request = Unirest.get(properties.getProperty("news-Api-URL"));

            if(cmd.hasOption('t')){
                request.queryString("q", cmd.getOptionValue('t'));
            } else {
                int topicId = Math.abs(new Random().nextInt()) % TITLES.length;
                request.queryString("q", TITLES[topicId]);
            }

            HttpResponse<JsonNode> response = request.queryString("apiKey", properties.getProperty("news-Api-Key"))
                    .asJson();

            JsonElement obj = new JsonParser().parse(response.getBody().toString());
            JsonObject jsonObject = null;
            if(obj.isJsonObject()) {
                jsonObject = obj.getAsJsonObject();
            }
            JsonArray articles = jsonObject.getAsJsonArray("articles");
            for (int i = 0; i < articles.size() && i < MAX_ARTICLES; i++) {
                JsonObject article = articles.get(i).getAsJsonObject();
                System.out.println("Title: " + article.get("title"));
                System.out.println("URL: " +article.get("url"));
            }
        } catch (UnirestException e) {
            e.printStackTrace();
        }
    }
}
