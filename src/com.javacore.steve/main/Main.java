package main;

import commands.Commands;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {

    public static final Logger LOGGER = LogManager.getLogger("Steve_logger");

    public static void main(String[] args) {
        Steve steve = new Steve();
        Commands.INSTANCE.printCommands();
        steve.run();
    }
}
