package commands;

import commandsUtils.Command;
import main.Steve;
import managers.ProfileManager;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.ParseException;

public class ListProfiles extends Command {

    public ListProfiles() {
        options.addRequiredOption("n", "number", true, "Number of profiles to be listed");
    }

    @Override
    public void perform(Steve steve, String[] options) throws ParseException {
        CommandLine commandLine = parser.parse(this.options, options);
        ProfileManager.PROFILE_MANAGER.listProfiles(Integer.valueOf(commandLine.getOptionValue('n')));
    }

    @Override
    public String getName() {
        return "list";
    }
}
