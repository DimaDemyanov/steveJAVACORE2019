package commands;

import main.Steve;

public abstract class Command {

    protected String name = this.getClass().getName();

    public void perform(Steve steve, String [] options){
        System.out.println("Command not implemented");
    }

    public String getName() {
        return name;
    }
}
