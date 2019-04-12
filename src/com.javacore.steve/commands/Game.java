package commands;

import commandsUtils.Command;
import main.Steve;
import org.apache.commons.cli.ParseException;

import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Game extends Command {
    private static final String HELP_MESSAGE = "Let's play the game. Imagine that there is a table, where are 21 rocks. " +
            "On each turn one of us can take from one to three rocks. Player who takes last rock will be the winner.";
    private static final String WHO_FIRST = "Would you like to go first?(y/n)";
    private static final int ROCKS = 21;

    enum Turn{
        COMPUTER,
        PLAYER;
    }

    @Override
    public void before(Semaphore semaphore) {
        try {
            semaphore.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void perform(Steve steve, String[] options, Semaphore semaphore) throws ParseException {
        perform(steve, options);
        semaphore.release();
    }

    @Override
    public void perform(Steve steve, String[] options) {
        int rocks = ROCKS;
        Turn turn = null;
        Scanner sc = new Scanner(System.in);
        System.out.println(HELP_MESSAGE);
        String answer;
        while (true){
            System.out.println(WHO_FIRST);
            answer = sc.nextLine();
            if(answer.equals("y")){
                turn = Turn.PLAYER;
                break;
            }
            if(answer.equals("n")){
                turn = Turn.COMPUTER;
                break;
            }
        }
        while (rocks != 0){
            System.out.println("Current count: " + rocks);
            if (turn == Turn.PLAYER){
                String cnt = null;
                while(true) {
                    System.out.println("Your turn (type number of rocks that you would like to take 1, 2, 3):");
                    cnt = sc.nextLine();
                    try{
                        rocks -= Integer.parseInt(cnt);
                        break;
                    } catch (NumberFormatException ignored){
                    }
                }
                turn = Turn.COMPUTER;
            } else {
                int cnt = rocks % 4;
                if(cnt == 0){
                    cnt = Math.min(rocks, Math.abs(new Random().nextInt() % 3) + 1);
                }
                rocks -= cnt;
                System.out.println("Hmm...I take " + cnt);
                turn = Turn.PLAYER;
            }
        }
        if (turn == Turn.COMPUTER){
            System.out.println("You win!!!");
        } else {
            System.out.println("You lose!!!");
        }
    }

    @Override
    public String getName() {
        return "game";
    }
}
