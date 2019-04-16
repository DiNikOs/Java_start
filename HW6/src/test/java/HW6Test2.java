
/**
 * Java. Level 3 Lesson 6
 * Tests2
 *
 * @author DMITRIY OSTROVSKIY
 * @version 0.1 dated APR 16, 2019
 */
import HW6.HW6;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class HW6Test2 {
    private HW6 hw6;
    private int[] arr;
    private int[] result;

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {new int[] {1, 2, 4, 4, 3, 4, 1, 7}, new int[]{1, 7}},
                {new int[] {1, 2, 3, 4, 4, 3}, new int[]{3}},
                {new int[] {1, 2, 4, 3, 4}, new int[]{}},
                {new int[] {1, 2, 3}, new int[]{4}}, // checking for errors
        });
    }

    public HW6Test2(int[] arr, int[] result) {
        this.arr = arr;
        this.result = result;
    }

    @Before
    public void init() {
        hw6 = new HW6();
    }

    @Test
    public void test() {
        Assert.assertArrayEquals(result, hw6.getArrayPastFour(arr));
    }
}


