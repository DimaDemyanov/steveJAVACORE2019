package commands;

import commandsUtils.Command;
import main.Steve;

public class Creator extends Command {

    public Creator() {
        name = "author";
    }

    public void perform(Steve steve, String [] options) {
        System.out.println("My author is " + steve.getProperties().getProperty("creator"));
    }
}
