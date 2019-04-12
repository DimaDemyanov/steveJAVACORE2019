package commands;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import main.Steve;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.ParseException;

import java.io.*;
import java.util.Scanner;

public class Log extends Command{
    public Log() {
        name = "log";
        options.addOption(new Option("c", "count", true, "Count of previos commands"));
    }

    @Override
    public void perform(Steve steve, String[] options) throws ParseException {
        Gson gson = new Gson();
        CommandLine cmd = parser.parse(this.options, options);
        int count = Integer.valueOf(cmd.getOptionValue('c'));
        try (FileReader reader = new FileReader(steve.getProperties().getProperty("logs-path")))
        {
            Scanner sc = new Scanner(reader);
            //Read JSON file
            JsonArray arr = new JsonArray();
            while(reader.ready()) {
                String logStr = "";
                String curr = "";
                while (!curr.equals("}")) {
                    curr = sc.nextLine();
                    logStr += curr;
                }
                JsonObject obj = gson.fromJson(new StringReader(logStr), JsonObject.class);
                arr.add(obj);
            }
            int size = arr.size();
            if(size < count) {
                System.out.println("There are not so many logs");
                return;
            }
            for (int i = 0; i < count; i++) {
                System.out.println(arr.get(size - count + i).getAsJsonObject().get("message"));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
