package commands;

import commandsUtils.Command;
import main.Steve;
import managers.ProfileManager;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.ParseException;

import java.util.concurrent.Semaphore;

public class Delete extends Command {

    public Delete() {
        options.addRequiredOption("n", "number", true, "Nnumber of profile to delete");
    }

    @Override
    public void perform(Steve steve, String[] options) throws ParseException {
        CommandLine cmd = parser.parse(this.options, options);
        try {
            ProfileManager.PROFILE_MANAGER.deleteProfile(new Integer(cmd.getOptionValue('n')));
        } catch (NumberFormatException ex){
            System.out.println("Cannot read id");
        }
    }

    @Override
    public String getName() {
        return super.getName();
    }
}
