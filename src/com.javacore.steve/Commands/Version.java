package Commands;

import Main.Steve;

public class Version extends Command {

    public void perform(Steve steve) {
        System.out.println("Current version " + steve.getProperties().getProperty("version"));
    }
}
