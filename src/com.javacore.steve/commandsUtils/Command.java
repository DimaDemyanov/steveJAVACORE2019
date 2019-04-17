package commandsUtils;

import main.Steve;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


public abstract class Command {

    protected CommandLineParser parser = new DefaultParser();
    protected Options options = new Options();
    protected boolean isLockingRequared = false;

    protected String name = this.getClass().getName();

    public void perform(Steve steve, String [] options) throws ParseException {
        System.out.println("Command not implemented");
    }

    public String getName() {
        return name;
    }
}
