package Main;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.*;
import Commands.*;

public class Steve {
    private static final String ANS_FOR_NO_COMMAND = "Sorry, I don't understand you.";
    private Properties properties;
    private Scanner scanner;


    public Properties getProperties() {
        return properties;
    }

    Steve(){
        this(System.in);
    }

    Steve(InputStream in){
        this.scanner = new Scanner(in);
        properties = new Properties();
        try {
            properties.load(new FileInputStream("config.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    void run(){
        while(true){
            String [] commands = getCommands();
            if(commands.length > 1){
                askSpecifiedQuestion(commands);
                continue;
            }
            if(commands.length == 0){
                System.out.println(ANS_FOR_NO_COMMAND);
                continue;
            }
            if(processCommand(commands[0])) break;
        }
    }

    private String[] getCommands(){
        String inputString = scanner.nextLine();
        Set<String> wordsInStr = new HashSet<String>(Arrays.asList(inputString.toLowerCase().replaceAll("[?!,.]","").split(" ")));
        Set<String> commandsSet = new HashSet<String>(Arrays.asList(Commands.getCommands()));
        wordsInStr.retainAll(commandsSet);
        return  wordsInStr.toArray(new String[0]);
    }

    private void askSpecifiedQuestion(String[] commands) {
        String question = "Which of this commands do you want to be processed?\n";
        for (int i = 0; i < commands.length; i++) {
            question += commands[i] + '\n';
        }
        System.out.print(question);
    }

    private boolean processCommand(String command) {
        if(command.equals("exit"))
            return true;
        String commandClassName = Commands.findClass(command);
        Class<?> clazz = null;
        try {
            clazz = Class.forName("Commands." + commandClassName);
            Constructor<?> ctor = clazz.getConstructor(Steve.class);
            Command commandObj = (Command)ctor.newInstance(this);
            commandObj.perform();
        } catch (ClassNotFoundException | NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return false;
    }
}
