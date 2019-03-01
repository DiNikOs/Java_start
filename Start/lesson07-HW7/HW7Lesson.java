

/**
* Java.Java_Start.HW7
*
*@ Dmitriy Ostrovskiy
*@ version date Feb 04,2019
*/
import java.util.Random;


class HW7Lesson {
        
    public static void main(String[] args) {
        
        Random random = new Random();
        int add = random.nextInt(30)+1;

        Cat cat1 = new Cat("Barsik", random.nextInt(25));   
        Cat cat2 = new Cat("Murzik", random.nextInt(25));  
        Cat cat3 = new Cat("Redgik", random.nextInt(25));

        Plate plate = new Plate(add, add + random.nextInt(20)); // (food ,<= full)
                
        Cat[] cats = {cat1, cat2, cat3}; 
                
        System.out.println("\nStart " + plate);
        for (Cat cat : cats) {
            cat.eat(plate);
            System.out.println(cat);
            }                    
        System.out.println("End   " + plate);

        System.out.println("\nadd + " + add);
        plate.addition(add);

        System.out.println("Start " + plate);        
        for (Cat cat : cats) {
            cat.setNotHungry(false);                            // set false in loop - isNotHungry
            cat.eat(plate);
            System.out.println(cat);
            }
        System.out.println("End   " + plate);
    }
}    


class Cat  {     
        
    protected String name;
    protected int appetite;
    protected boolean notHungry;
    
    Cat(String name, int appetite) {
        this.name = name;
        this.appetite = appetite;            
    }
        
    void setNotHungry(boolean isNotHungry) {
        this.notHungry = isNotHungry;
    }    
    
    void eat(Plate plate) {
        if (!notHungry) {
            notHungry = plate.dicreaseFood(appetite);
        }
    } 
            
    @Override
    public String toString() {
        return "Cat(" + name + ") appetite=" + appetite + ", notHungry=" + notHungry;
    }             
}           
    
class Plate {
    
    protected int food;
    protected int full;

    Plate(int food, int full) {
        this.food = food;
        this.full = full;
    }

    boolean dicreaseFood(int appetiteCat) {
        if (food > appetiteCat) {
            food -= appetiteCat;
            return true;        
        }
        return false;
    }
    
    void addition (int add) {
        if (full >= this.food + add  ) {       // 1 step End food + add
            this.food += add;                  // 2 step Start food
        }
    }
        
    @Override
    public String toString() {
        return "food: " + food + ", Full=" + full;
        }
}
