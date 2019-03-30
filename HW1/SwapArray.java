/**
 * Java3. Core. HW1
 * class SwapArray
 * @author Dmitriy Ostrovskiy
 * @version 0.1 date Mar 30, 2019
 */

package HW1;

import java.util.ArrayList;
import java.util.Arrays;

public class SwapArray<T> {
    private T[] nums;

    public SwapArray(T[] nums) {
        this.nums = nums;
    }


    public int[] getSwapArray () {
       int [] arr = new int[nums.length];
        for (int i = 0; i < nums.length; i+=2) {
            if ((i+1) < nums.length) {
                arr[i+1] = (Integer) nums[i];
                arr[i] = (Integer) nums[i+1];
            }
        }
        return arr;
    }

    public String getSwapArrayIndex (int a, int b) {
        int [] arr = new int[nums.length];
        //String val = "";
        try {
            for (int i = 0; i < nums.length; i++) {
                arr[i] = (Integer) nums[i];
            }
            arr[b] = (Integer) nums[a];
            arr[a] = (Integer) nums[b];
            System.out.println("SwapArrayIndex(" + a + " ," + b + " ) = " + Arrays.toString(arr));
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Exception array Index a || b");
        }
        return "END HW1.1"; // я не знаю как обойти необходимость возврата какой либо переменное
    }
}
