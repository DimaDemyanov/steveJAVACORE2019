package main;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.ReentrantLock;

import commandsUtils.Command;
import commandsUtils.Commands;
import commandsUtils.State;
import managers.ProfileManager;
import org.hibernate.HibernateException;

import static commandsUtils.State.IDLE;

public class Steve {
    private static final String ANS_FOR_NO_COMMAND = "Sorry, I don't understand you.";
    private Properties properties;
    private Reader reader;
    private State state = IDLE;
    private Semaphore semaphore = new Semaphore(1);

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }


    public Properties getProperties() {
        return properties;
    }

    Steve() throws IOException, HibernateException {
        this(System.in);
    }

    Steve(InputStream in) throws IOException, HibernateException {
        this.reader = new Reader(in);
        properties = new Properties();
        properties.load(new FileInputStream("config.properties"));
        ProfileManager.init();
    }


    void run(){
        System.out.println("You can ask me something");
        while(true){
            Reader.Input input = reader.getCommands();
            String []commands = input.getCommands();
            if(commands.length > 1){
                StringBuilder commandsStr = new StringBuilder();
                for (int i = 0; i < commands.length; i++) {
                    commandsStr.append(commands[i] + " ");
                }
                Main.LOGGER.info("Getting too much commands. Variants: " + commandsStr);
                askSpecifiedQuestion(commands);
                continue;
            }
            if(commands.length == 0){
                Main.LOGGER.info("No commands found in request");
                System.out.println(ANS_FOR_NO_COMMAND);
                continue;
            }
            Main.LOGGER.info("Getting command: " + commands[0]);
            if(processCommand(commands[0], input.getOptions())) break;
        }
    }


    private void askSpecifiedQuestion(String[] commands) {
        String question = "Which of this commands do you want to be processed?\n";
        for (int i = 0; i < commands.length; i++) {
            question += commands[i] + '\n';
        }
        System.out.print(question);
    }

    private boolean processCommand(String command, String [] options) {
        if(command.equals("exit"))
            return true;
        Command cmd = Commands.INSTANCE.findClass(command);
        final Steve steveCpy = this;
        try {
            Semaphore stateWaiter = new Semaphore(0);
            state.before(cmd, semaphore);
            Thread thread = new Thread(() -> state.onCommand(cmd, steveCpy, options, semaphore, stateWaiter));
            thread.start();
            stateWaiter.acquire();
            state.after(cmd, semaphore);
            try {
                semaphore.acquire();
                semaphore.release();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return false;
    }
}
