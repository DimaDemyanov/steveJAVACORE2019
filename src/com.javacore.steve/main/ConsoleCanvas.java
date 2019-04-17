package main;

public class ConsoleCanvas extends Canvas{
    private char [][] pixes;
    private int width;
    private int height;

    public ConsoleCanvas(int width, int height) {
        this.width = width;
        this.height = height;
        init();
    }

    public void init() {
        pixes = new char[height][width];
        reset();
    }

    public void reset(){
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                pixes[i][j] = '.';
            }
        }
    }

    public void draw() {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                System.out.print(pixes[i][j]);
            }
            System.out.println();
        }
    }

    public void setSymbolAt(int x, int y, char c){
        pixes[x % height][y % width] = c;
    }

    public void setSquareAt(int x, int y, int n) {
        if (n == 1){
            setSymbolAt(x, y, '#');
            return;
        }
        for (int i = 0; i < n; i++) {
            setSymbolAt(x - n / 2, y - n / 2 + i, '#');
            setSymbolAt(x - n / 2 + i, y - n / 2, '#');
            setSymbolAt(x + n / 2 - i, y + n / 2, '#');
            setSymbolAt(x + n / 2, y + n / 2 - i, '#');
        }
    }

    public void setCircleAt(int x, int y, int n) {
        if (n == 1){
            setSymbolAt(x, y, '#');
            return;
        }
        for (int i = x - n; i < x + n + 1; i++) {
            for (int j = y - n; j < y + n + 1; j++) {
                if((int)Math.sqrt((i - x) * (i - x) + (j - y) * (j - y)) == n){
                    setSymbolAt(i, j, '#');
                }
            }
        }
    }

    public void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    @Override
    public void drawText(String text) {
        System.out.println(text);
    }

    @Override
    public void drawSquare(int size) {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if(i % (size - 1) == 0 ||j% (size - 1) == 0  ){
                    System.out.print('#');
                }else
                    System.out.print('.');
            }
            System.out.println();
        }
    }
}
