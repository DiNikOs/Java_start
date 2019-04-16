package HW6;
/**
 * Java. Level 3 Lesson 6
 * main class TESTS
 *
 * @author DMITRIY OSTROVSKIY
 * @version 0.1 dated APR 16, 2019
 */
import java.util.Arrays;

public class HW6 {

    public static void main(String[] args) throws RuntimeException {
        HW6 hw6 = new HW6();
        // HW6.2
        System.out.println(Arrays.toString(hw6.getArrayPastFour(new int[]{1, 2, 4, 4, 2, 3, 4, 1, 7})));
        System.out.println(Arrays.toString(hw6.getArrayPastFour(new int[]{2, 2, 1, 4, 1})));
        System.out.println(Arrays.toString(hw6.getArrayPastFour(new int[]{2, 2, 1, 4, 4})));
        // HW6.3
        System.out.println(hw6.checkForNumber(new int[]{4, 4, 4, 4, 4, 1, 1}));
        System.out.println(hw6.checkForNumber(new int[]{1, 2, 3, 4}));
        System.out.println(hw6.checkForNumber(new int[]{1, 4, 4, 1}));
    }
//=================================HW6.2=================================
    public int[] getArrayPastFour(int[] arr) throws RuntimeException { //
        for (int i = arr.length - 1; i >= 0; i--)
            if (arr[i] == 4) {
                int startArr2 = i + 1;
                int[] result = new int[arr.length - startArr2];

                System.arraycopy(arr, startArr2, result, 0, result.length);
                return result;
            }
        throw new RuntimeException("NO NUMBERS 4");
    }

//=================================HW6.3=================================
    public boolean checkForNumber(int[] arr) {
        boolean one = false;
        boolean four = false;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == 1) {
                one = true;
            }
            else if (arr[i] == 4) {
                four = true;
            }
            else return false;
        }
        return one && four;
    }
}
