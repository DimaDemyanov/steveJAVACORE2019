package main;

import java.util.Scanner;

public class Application {
    public static void main(String[] args) {
        ConsoleCanvas consoleCanvas = new ConsoleCanvas(50, 50);
        Scanner sc = new Scanner(System.in);
        int x = sc.nextInt(), y = sc.nextInt(), n = sc.nextInt();
        for (int i = 0; i < 100; i++) {
            consoleCanvas.setSquareAt(x + i, y + i , n);
            consoleCanvas.draw();
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            consoleCanvas.clearScreen();
            consoleCanvas.reset();
        }
    }
}
