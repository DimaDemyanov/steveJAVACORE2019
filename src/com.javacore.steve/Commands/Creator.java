package Commands;

import Main.Steve;

public class Creator extends Command {

    public Creator(Steve steve) {
        super(steve);
    }

    public void perform() {
        System.out.println("My author is " + steve.getProperties().getProperty("creator"));
    }
}
