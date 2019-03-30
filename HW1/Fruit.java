/**
 * Java3. Core. HW1
 * class's Fruit, Apple extends Fruit, Orange extends Fruit
 * @author Dmitriy Ostrovskiy
 * @version 0.1 date Mar 30, 2019
 */

package HW1;

public class Fruit {
    public float weight;

    public Fruit(float weight) {
        this.weight = weight;
    }

    public float getWeigth() {
        return weight;
    }

    @Override
    public String toString() {
        return this.getClass().getName() + " = " + weight;
    }
}

class Apple extends Fruit {
    public Apple() {
        super(1.0f);
    }
}

class Orange extends Fruit {
    public Orange() {
        super(1.5f);
    }
}
