package commandsUtils;

import main.Steve;
import org.apache.commons.cli.ParseException;

public enum State {
    IDLE{
        @Override
        public void onCommand(Command command, Steve steve, String[] options) {
            synchronized (this) {
                steve.setState(steve.getState().nextState());
            }
            try {
                command.perform(steve, options);
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
        public void onCommand(Command command, Steve steve, String[] options) {
            System.out.println("Busy executing command...");
        }

        @Override
        State nextState() {
            return IDLE;
        }
    };
    public abstract void onCommand(Command command, Steve steve, String[] options);
    abstract State nextState();
}
