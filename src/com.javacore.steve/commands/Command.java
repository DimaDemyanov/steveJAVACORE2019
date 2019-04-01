package commands;

import main.Steve;
//import org.apache.commons.cli.*

public abstract class Command {

    protected CommandLineParser

    protected String name = this.getClass().getName();

    public void perform(Steve steve, String [] options){
        System.out.println("Command not implemented");
    }

    public String getName() {
        return name;
    }
}
