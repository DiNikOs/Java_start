/**
* Java.Java_Start.HW4_2
*
*@ Dmitriy Ostrovskiy
*@ version date Jan 24,2019
*/

import java.util.Random;
import java.util.Scanner;

class HW4_2Lesson { 

    final int SIZE = 3;
    final char DOT_X = 'x';
    final char DOT_O = 'o';
    final char DOT_EMPTY = '.';
    char[][] map;
    Random random;
    Scanner scanner;

    public static void main(String[] args) {
        new HW4_2Lesson().game();
    }

    void game() {
        initMap();
        random = new Random();
        scanner = new Scanner(System.in);
        while (true) {
            humanTurn();
            if (checkWin(DOT_X)) {
                System.out.println("YOU WON!");
                break;
            }
            if (isMapFull()) {
                System.out.println("Sorry, DRAW!");
                break;
            }
            aiTurn();
            printMap();
            if (checkWin(DOT_O)) {
                System.out.println("AI WON!");
                break;
            }
            if (isMapFull()) {
                System.out.println("Sorry, DRAW!");
                break;
            }
        }
        printMap();
        System.out.println("GAME OVER");
    }

    void initMap() {
        map = new char[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++)
            for (int j = 0; j < SIZE; j++)
                map[i][j] = DOT_EMPTY;
    }

    void printMap() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++)
                System.out.print(map[i][j] + " ");
            System.out.println();
        }
    }
    
    void humanTurn() {
        int x, y;
        do {
            System.out.println("Enter X and Y (1..3):");
            x = scanner.nextInt() - 1;
            y = scanner.nextInt() - 1;
        } while (!isCellValid(x, y));
        map[y][x] = DOT_X;
    }

    void aiTurn() {
        int x, y;
        do {
            x = random.nextInt(SIZE);
            y = random.nextInt(SIZE);
        } while (!isCellValid(x, y));
        map[y][x] = DOT_O;
    }
    
    boolean checkWin(char dt) {
        String []line = {"","","",""};
        String lineTrue = "";
        for (int i = 0; i < SIZE; i++) {
            lineTrue += dt;
        }
        for (int i = 0; i < SIZE; i++) {
            line [0] = line [1] = "";
            for (int j = 0; j < SIZE; j++) {
                line [0] += map[i][j];
                line [1] += map[j][i];
                if (i == j ) line [2] += map[i][j];
                if (i == (SIZE-1-j)) line [3] += map[i][j];
                for (String l: line) {
                    if (l.equals(lineTrue)) { 
                        return true;
                    }
                }
            }
        }
    return false;  
    }
    
    boolean isMapFull() {
        for (int i = 0; i < SIZE; i++) 
            for (int j = 0; j < SIZE; j++) 
                if (map[i][j] == DOT_EMPTY)
                    return false;
        return true;
 
    }

    boolean isCellValid(int x, int y) {
        if (x < 0 || y < 0 || x >= SIZE || y >= SIZE)
            return false;
        return map[y][x] == DOT_EMPTY; // by DSerov
    }
}