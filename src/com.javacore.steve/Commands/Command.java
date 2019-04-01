package Commands;

import Main.Steve;

public abstract class Command {

    protected String name = this.getClass().getName();

    public void perform(Steve steve){
        System.out.println("Command not implemented");
    }

    public String getName() {
        return name;
    }
}
