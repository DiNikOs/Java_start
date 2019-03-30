/**
 * Java3. Core. HW1
 * class ToArrayList
 * @author Dmitriy Ostrovskiy
 * @version 0.1 date Mar 30, 2019
 */

package HW1;

import java.util.ArrayList;
import java.util.List;

public class ToArrayList <T> {
    List<T> arrList = new ArrayList<T>();
    private T[] nums;

    public ToArrayList(T[] nums) {
        this.nums = nums;

    }

    public List<T> getArrayList() {
        for (int i = 0; i < nums.length; i++) {

            arrList.add(nums[i]);
        }
        return arrList;
    }
}










