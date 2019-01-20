/**
* Java.Java_Start.HW3_1
*
*@ Dmitriy Ostrovskiy
*@ version date Jan 20,2019
*/
import java.util.Arrays;
import java.util.Scanner;
import java.util.Random;

public class HW3_1Lesson {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
		
        do {
            int maxNumber = 9;           
            int random = getRandom(maxNumber);
            int maxTry = 3;
			
            for (int i = 0; i < maxTry; i++) {                
                System.out.println("Введите число от 0 до " + maxNumber);
                int inputNumber = scanner.nextInt();
                if (inputNumber == random) {
                    System.out.println("Поздравляю! Вы угадали");
                    break;
                    }
                else if (inputNumber < random) {
                    System.out.println("Загаданное число больше");
                    }
                else if (inputNumber > random) {
                    System.out.println("Загаданное число менше");
                    }
                if (i == maxTry-1) { 
                    System.out.println("Вы проиграли");
                    }                
            }
            int  answer = 0;
            while (answer != 1||answer != 0) {  
            System.out.println("Повторить игру ещё раз? 1 - да/0 - нет.");
                answer = scanner.nextInt();
                if (answer == 0 || answer == 1) {
                    break;
                }
            }
            if (answer == 0 ) {
                break;
            }    
        }
        while (true);
        scanner.close();
        System.out.println("GAME OVER");
    }
    public static int getRandom(int maxNumber) {
        Random randomNumber = new Random();
        int random = randomNumber.nextInt(maxNumber+1);
        return random;	
  }    
}  