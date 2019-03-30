package Commands;

import Main.Steve;

public class Version extends Command {

    public Version(Steve steve) {
        super(steve);
    }

    public void perform() {
        System.out.println("Current version " + steve.getProperties().getProperty("version"));
    }
}
