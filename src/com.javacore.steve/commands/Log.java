package commands;

import main.Steve;
import org.apache.commons.cli.ParseException;

public class Log extends Command{
    public Log() {
        name = "log";
    }

    @Override
    public void perform(Steve steve, String[] options) throws ParseException {
        super.perform(steve, options);
    }
}
