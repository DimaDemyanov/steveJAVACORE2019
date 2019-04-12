package commands;

import commandsUtils.Command;
import main.Steve;
import org.apache.commons.cli.ParseException;

import java.util.Scanner;

public class Game extends Command {
    private static final String HELP_MESSAGE = "Let's play the game. Imagine that there is a table, where are 21 rocks. " +
            "On each turn one of us can take from one to three rocks. Player who takes last rock will be the winner." +
            "Would you like to go first?(y/n)";

    @Override
    public void perform(Steve steve, String[] options) throws ParseException {
        synchronized (steve){
            Scanner sc = new Scanner(System.in);
            System.out.println(HELP_MESSAGE);
            
        }
    }

    @Override
    public String getName() {
        return "game";
    }
}
