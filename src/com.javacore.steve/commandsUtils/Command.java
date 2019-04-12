package commandsUtils;

import main.Steve;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;


public abstract class Command {

    protected CommandLineParser parser = new DefaultParser();
    protected Options options = new Options();

    protected String name = this.getClass().getName();

    public void perform(Steve steve, String [] options) throws ParseException {
        System.out.println("Command not implemented");
    }

    public String getName() {
        return name;
    }
}
