package Commands;

import Main.Steve;

public abstract class Command {
    Steve steve;

    public Command(Steve steve) {
        this.steve = steve;
    }

    public abstract void perform();
}
