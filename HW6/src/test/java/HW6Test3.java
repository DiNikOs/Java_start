
/**
 * Java. Level 3 Lesson 6
 * Tests3
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
public class HW6Test3 {

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {new int[]{1, 4, 4, 1}, true},
                {new int[]{4, 4, 4}, false},
                {new int[]{1, 1, 4, 1, 4}, true},
                {new int[]{4, 1, 4}, false}, // checking for errors
                {new int[]{1, 4, 5}, false}
        });
    }
    private HW6 hw6;
    private boolean result;
    private int[] arr;

    public HW6Test3(int[] arr, boolean result){
        this.arr = arr;
        this.result = result;
    }

    @Before
    public void init() {
        hw6 = new HW6();
    }

    @Test
    public void test(){
        Assert.assertEquals(result, hw6.checkForNumber(arr));
    }
}

