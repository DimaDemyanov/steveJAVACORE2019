package main;

import commands.Commands;

public class Main {
    public static void main(String[] args) {
        Steve steve = new Steve();
        Commands.INSTANCE.printCommands();
        steve.run();
    }
}