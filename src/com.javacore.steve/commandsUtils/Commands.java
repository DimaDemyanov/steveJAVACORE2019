package commandsUtils;

import commands.*;

import java.util.*;

public enum Commands {
    INSTANCE;
    public static Map<String[], Command> commandsToClass;
    static {
        commandsToClass = new HashMap<>();
        commandsToClass.put(new String[]{"version"}, new Version());
        commandsToClass.put(new String[]{"author", "creator", "father"}, new Creator());
        commandsToClass.put(new String[]{"weather"}, new Weather());
        commandsToClass.put(new String[]{"time"}, new Time());
        commandsToClass.put(new String[]{"exit"}, new Exit());
        commandsToClass.put(new String[]{"new", "news"}, new News());
        commandsToClass.put(new String[]{"log", "logs"}, new Log());
        commandsToClass.put(new String[]{"wait"}, new Wait());
        commandsToClass.put(new String[]{"load"}, new Load());
        commandsToClass.put(new String[]{"list"}, new ListProfiles());
        commandsToClass.put(new String[]{"help"}, new Help());
        commandsToClass.put(new String[]{"game"}, new Game());
        commandsToClass.put(new String[]{"nra"}, new Nra());
        commandsToClass.put(new String[]{"delete"}, new Delete());
    }

    public String[] getCommands(){
        List<String> commands = new LinkedList<>();
        for (String [] cmds: commandsToClass.keySet()) {
            commands.addAll(Arrays.asList(cmds));
        }
        return commands.toArray(new String[0]);
    }

    public Command findClass(String command){
        for (String [] cmds: commandsToClass.keySet()) {
            if(Arrays.asList(cmds).contains(command)){
                return commandsToClass.get(cmds);
            }
        }
        return null;
    }

    public void printCommands(){
        commandsToClass.forEach((strs, cmd) -> System.out.println(cmd.getName()));
    }
}
