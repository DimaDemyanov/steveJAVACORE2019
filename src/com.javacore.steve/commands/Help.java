package commands;

import commandsUtils.Command;
import commandsUtils.Commands;
import main.Steve;
import org.apache.commons.cli.ParseException;

public class Help extends Command {
    @Override
    public void perform(Steve steve, String[] options) throws ParseException {
        System.out.println("Available commands: ");
        Commands.INSTANCE.printCommands();
    }

    @Override
    public String getName() {
        return "help";
    }
}
