/**
* Java.Java_Start.HW3_2
*
*@ Dmitriy Ostrovskiy
*@ version date Jan 20,2019
*/
import java.util.Arrays;
import java.util.Scanner;
import java.util.Random;

public class HW3_2Lesson {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] words = {"apple", "orange", "lemon", "banana", "apricot", "avocado", "broccoli", "carrot",
        "cherry", "garlic", "grape", "melon", "leak", "kiwi", "mango", "mushroom", "nut", "olive", "pea", "peanut", "pear", "pepper", "pineapple", "pumpkin", "potato"};

            int maxWord = 25;           
            int random = getRandom(maxWord);
            System.out.println(Arrays.toString(words));
            String word = words[random] ;
            for (int j = 0 ;j < 15; j++) {
                word = word + "#";
            }
            String answer = "";
            do {
                System.out.println("Введите загаданное слово ");
                String inputWord = scanner.nextLine();
                for (int k = 0 ;k < 15; k++) {
                    inputWord = inputWord + "#";
                }
                if (inputWord.equals(word)) {
                    System.out.println("Поздравляю! Вы угадали");
                    break;
                }
                else {
                    answer = "";
                    for (int i = 0; i < 15; i++) {
                        char sumbolInputWord = inputWord.charAt(i);
                        char sumbolWord = word.charAt(i);
                        if (sumbolInputWord == sumbolWord) {
                            answer = answer + sumbolWord;
                        }   
                        else {
                            answer = answer + '#';
                        }
                    }
                    System.out.println("Загаданное слово " + answer);
                }
            }
            while (true);
        scanner.close();
    }
    public static int getRandom(int maxWord) {
        Random randomWord = new Random();
        int random = randomWord.nextInt(maxWord+1);
        return random;	
    }    
}  