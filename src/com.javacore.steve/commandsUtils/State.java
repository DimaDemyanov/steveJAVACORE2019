package commandsUtils;

import main.Steve;
import org.apache.commons.cli.ParseException;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public enum State {
    IDLE{
        @Override
        public void before(Command command, Semaphore semaphore) throws InterruptedException {
            if(command.isLockingRequared) semaphore.acquire();
        }

        @Override
        public void onCommand(Command command, Steve steve, String[] options, Semaphore semaphore, Semaphore stateWaiter) {
            steve.setState(EXECUTING);
            stateWaiter.release();
            try {
                command.perform(steve, options);
            } catch (ParseException e) {
                System.out.println("Cannot parse options");
            } finally {
                steve.setState(IDLE);
                System.out.println();
                System.out.println("you can ask me something");
            }
        }

        @Override
        public void after(Command command, Semaphore semaphore) {
            if(!command.isLockingRequared) semaphore.release();
        }

        @Override
        State nextState() {
            return EXECUTING;
        }


    },
    EXECUTING{
        @Override
        public void onCommand(Command command, Steve steve, String[] options, Semaphore semaphore, Semaphore stateWaiter) {
            System.out.println("Busy executing command...");
        }

        @Override
        State nextState() {
            return IDLE;
        }
    };
    public void before(Command command, Semaphore semaphore) throws InterruptedException {}
    public void after(Command command, Semaphore semaphore){}
    public abstract void onCommand(Command command, Steve steve, String[] options, Semaphore semaphore, Semaphore stateWaiter);
    abstract State nextState();
}
