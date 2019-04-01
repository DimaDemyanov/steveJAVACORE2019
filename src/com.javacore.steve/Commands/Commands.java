package Commands;

import java.util.*;

public class Commands {
    public static Map<String[], String> commandsToClass;
    static {
        commandsToClass = new HashMap<>();
        commandsToClass.put(new String[]{"version"}, "Version");
        commandsToClass.put(new String[]{"author", "creator", "father"}, "Creator");
        commandsToClass.put(new String[]{"weather"}, "Weather");
        commandsToClass.put(new String[]{"time"}, "Time");
    }

    public static String[] getCommands(){
        List<String> commands = new LinkedList<>();
        for (String [] cmds: commandsToClass.keySet()) {
            commands.addAll(Arrays.asList(cmds));
        }
        return commands.toArray(new String[0]);
    }

    public static String findClass(String command){
        for (String [] cmds: commandsToClass.keySet()) {
            if(Arrays.asList(cmds).contains(command)){
                return commandsToClass.get(cmds);
            }
        }
        return null;
    }
}
