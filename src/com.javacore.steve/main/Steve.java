package main;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import commands.*;
import org.apache.commons.cli.ParseException;

public class Steve {
    private static final String ANS_FOR_NO_COMMAND = "Sorry, I don't understand you.";
    private Properties properties;
    private Reader reader;


    public Properties getProperties() {
        return properties;
    }

    Steve(){
        this(System.in);
    }

    Steve(InputStream in){
        this.reader = new Reader(in);
        properties = new Properties();
        try {
            properties.load(new FileInputStream("config.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    void run(){
        while(true){
            Reader.Input input = reader.getCommands();
            String []commands = input.getCommands();
            if(commands.length > 1){
                StringBuilder commandsStr = new StringBuilder();
                for (int i = 0; i < commands.length; i++) {
                    commandsStr.append(commands[i] + " ");
                }
                Main.LOGGER.info("Getting too much commands. Variants: " + commandsStr);
                askSpecifiedQuestion(commands);
                continue;
            }
            if(commands.length == 0){
                Main.LOGGER.info("No commands found in request");
                System.out.println(ANS_FOR_NO_COMMAND);
                continue;
            }
            Main.LOGGER.info("Getting command: " + commands[0]);
            if(processCommand(commands[0], input.getOptions())) break;
        }
    }


    private void askSpecifiedQuestion(String[] commands) {
        String question = "Which of this commands do you want to be processed?\n";
        for (int i = 0; i < commands.length; i++) {
            question += commands[i] + '\n';
        }
        System.out.print(question);
    }

    private boolean processCommand(String command, String [] options) {
        if(command.equals("exit"))
            return true;
        Command cmd = Commands.INSTANCE.findClass(command);
        try {
            cmd.perform(this, options);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return false;
    }
}
