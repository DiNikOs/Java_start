/**
* Java.Java_Start.HW4_3
*
*@ Dmitriy Ostrovskiy
*@ version date Jan 24,2019
*/

import java.util.Random;
import java.util.Scanner;
import java.util.Arrays;

class HW4_3Lesson { 

    final int SIZE = 5;
    final char DOT_X = 'x';
    final char DOT_O = 'o';
    final char DOT_EMPTY = '.';
    char[][] map;
	int X;
	int Y;
    Random random;
    Scanner scanner;

    public static void main(String[] args) {
        new HW4_3Lesson().game();
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
            System.out.println("Enter X and Y (1.." + SIZE + "):");
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
        String []line = {"","","","","","","","","","","",""};
        String  lineTrue = "";
        for (int i = 1; i < SIZE; i++) {
            lineTrue += dt;
        }
        for (int i = 0; i < SIZE; i++) {            
            line[0] = line[1] = "";
            for (int j = 0; j < SIZE; j++) {
                line[0] += map[i][j];
                line[1] += map[j][i];
                line[2] = line[0].substring(1);
                line[3] = line[1].substring(1);
                if (i == j) line[4]+= map[i][j];
                line[5] = line[4].substring(1);
                if (i == j+1) {
                    line[6]+= map[i][j];
                    line[7]+= map[j][i];
                }
                if (i == SIZE-1-j) {
                    line[8] += map[i][j];
                    line[9] += map[j][i];
                }
                if (i == SIZE-2-j) {
                line[10] += map[i][j];
                line[11] += map[i+1][j+1];
                }                
                for (String l : line) {
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