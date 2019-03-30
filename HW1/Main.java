/**
 * Java3. Core. HW1
 * Main Class
 * @author Dmitriy Ostrovskiy
 * @version 0.1 date Mar 30, 2019
 */

package HW1;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Main {


    public static void main(String[] args) {
// ------------------HW1.1-------------------
        Integer[] arr = {1,2,3,4,5,6,7,8};
        SwapArray<Integer> nums = new SwapArray<Integer>(arr);
        int[] res1 = nums.getSwapArray();
        System.out.println("SwapArray = " + Arrays.toString(res1));
        String res2 = nums.getSwapArrayIndex(2, 4);
        System.out.println(res2);
// ------------------HW1.2-------------------
        Integer[] arr2 = {1,2,3,4,5,6,7,8};
        ToArrayList<Integer> arrList = new ToArrayList<Integer>(arr2);
        List<Integer> res3 = arrList.getArrayList();
        System.out.println("ArrayList = " + res3.getClass());
        System.out.println("END HW1.2");
// ------------------HW1.3-------------------
        Box<Apple> box1 = new Box<Apple>();
        Box<Orange> box2 = new Box<Orange>();
        int countApple = 6;
        int countOrange = 4;

        for (int i = 0; i < countApple ; i++) {
            box1.addFruit(new Apple());
        }
        for (int i = 0; i < countOrange ; i++) {
            box2.addFruit(new Orange());
        }

        System.out.println("fruit_box = " + box1.getFruits());
        System.out.println("fruit_box = " + box2.getFruits());
        System.out.println("box1 = box2 - " + box1.compare(box2));
        Box<Orange> box3 = new Box<Orange>();
        box3.addFruit(new Orange());
        System.out.println("fruit_box3 = " + box3.getFruits());
        box3.moveFruits(box2);
        System.out.println("fruit_box2 = " + box2.getFruits());
        System.out.println("fruit_box3 = " + box3.getFruits());

    }
}



