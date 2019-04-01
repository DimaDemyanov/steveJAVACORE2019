package Commands;

import Main.Steve;

public class Creator extends Command {

    public void perform(Steve steve) {
        System.out.println("My author is " + steve.getProperties().getProperty("creator"));
    }
}
