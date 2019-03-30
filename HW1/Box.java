/**
 * Java3. Core. HW1
 * class Box
 * @author Dmitriy Ostrovskiy
 * @version 0.1 date Mar 30, 2019
 */

package HW1;

import java.util.ArrayList;
import java.util.List;

public class Box <T extends Fruit> {

    private ArrayList<T> fruits;

    public Box() {
        fruits = new ArrayList<T>();
    }

    public void addFruit (T fruit) {
        fruits.add(fruit);
    }

    public void addAllFruits (ArrayList<T> fruits) {
        this.fruits.addAll(fruits);
    }

    public ArrayList<T> getFruits() {
        return fruits;
    }
    public void setFruits(ArrayList<T> fruits) {
        this.fruits = fruits;
    }

    public void info() {
        System.out.println("fruit " + fruits.size());
        System.out.println("type_fruit " + fruits.getClass());
    }

    public float getWeight () {
        float weight = 0.0f;
        for (T f: fruits) {
            weight += f.getWeigth();
        }
        return (float)Math.ceil(weight);
    }

    public boolean compare (Box<?> box) {
      return this.getWeight() == box.getWeight();
    }

    public void moveFruits (Box<T> box) {//
        box.addAllFruits(this.getFruits());
        fruits.clear();
    }
}
