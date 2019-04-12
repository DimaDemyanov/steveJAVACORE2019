package commandsUtils;

import main.Steve;
import org.apache.commons.cli.ParseException;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public enum State {
    IDLE{
        @Override
        public void before(Command command, Semaphore semaphore) {
            command.before(semaphore);
        }

        @Override
        public void onCommand(Command command, Steve steve, String[] options, Semaphore semaphore) {
            synchronized (this) {
                steve.setState(steve.getState().nextState());
            }
            try {
                command.perform(steve, options, semaphore);
            } catch (ParseException e) {
                System.out.println("Cannot parse options");
            } finally {
                synchronized (this) {
                    steve.setState(steve.getState().nextState());
                    System.out.println();
                    System.out.println("you can ask me something");
                }
            }
        }

        @Override
        State nextState() {
            return EXECUTING;
        }


    },
    EXECUTING{
        @Override
        public void onCommand(Command command, Steve steve, String[] options, Semaphore semaphore) {
            System.out.println("Busy executing command...");
        }

        @Override
        State nextState() {
            return IDLE;
        }
    };
    public void before(Command command, Semaphore semaphore){
    }
    public abstract void onCommand(Command command, Steve steve, String[] options, Semaphore semaphore);
    abstract State nextState();
}
