package commands;

import main.Steve;
import org.apache.commons.cli.ParseException;

public class Wait extends Command {

    @Override
    public void perform(Steve steve, String[] options) throws ParseException {
        try {
            Thread.sleep(100000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getName() {
        return "wait";
    }
}
