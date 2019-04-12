package commands;

import commandsUtils.Command;
import main.Steve;

public class Version extends Command {

    public Version() {
        name = "version";
    }

    public void perform(Steve steve, String [] options) {
        System.out.println("Current version " + steve.getProperties().getProperty("version"));
    }
}
