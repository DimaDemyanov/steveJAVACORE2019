package main;

import commandsUtils.Commands;

import java.io.InputStream;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Reader {

    public class Input{
        private String [] commands;
        private String [] options;

        Input(String [] commands, String [] options) {
            this.commands = commands;
            this.options = options;
        }

        public String[] getCommands(){
            return commands;
        }

        public String[] getOptions(){
            return options;
        }
    }

    private Scanner scanner;

    public Reader(InputStream in) {
        this.scanner = new Scanner(in);
    }

    public Input getCommands(){
        String inputString = scanner.nextLine();
        String [] wordsWithOptions = inputString
                .toLowerCase()
                .replaceAll("[?!,.]","")
                .split(" ");

        // Split words on options and other words. Options begin at word, which starts with '-'

        String [] wordsInStr = null;
        String [] optionsInStr = null;

        wordsInStr = wordsWithOptions;
        for (int i = 0; i < wordsWithOptions.length; i++) {
            if(wordsWithOptions[i].startsWith("-")){
                wordsInStr = Arrays.copyOfRange(wordsWithOptions, 0, i);
                optionsInStr = Arrays.copyOfRange(wordsWithOptions, i, wordsWithOptions.length);
                break;
            }
        }
        Set<String> wordsInStrSet = new HashSet<>(Arrays.asList(wordsInStr));

        Set<String> commandsSet = new HashSet<>(Arrays.asList(Commands.INSTANCE.getCommands()));
        wordsInStrSet.retainAll(commandsSet);
        return  new Input(wordsInStrSet.toArray(new String[0]), optionsInStr);
    }
}
