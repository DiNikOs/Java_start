//package var2;
/**
 * Java. Level 3 Lesson 5
 * Thread. part2
 *
 * @author DMITRIY OSTROVSKIY
 * @version 0.2 dated APR 14, 2019
 */
 
import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.CountDownLatch; 
import java.util.concurrent.Semaphore;      

public class HW5_CD {
    public static final int CARS_COUNT = 4;
    static CountDownLatch start = new CountDownLatch(CARS_COUNT + 1);
    static final CountDownLatch finish = new CountDownLatch(CARS_COUNT);
    static final Semaphore tunnel = new Semaphore(2); 
    
    public static void main(String[] args) throws InterruptedException {
        System.out.println("Attention >>> Get ready!!!");
        Race race = new Race(
                new Road(60),
                new Tunnel(),
                new Road(40));
        Car[] cars = new Car[CARS_COUNT];
        
        for (int i = 0; i < cars.length; i++) {
            cars[i] = new Car(race, 20 + (int) (Math.random() * 10));      
            new Thread(cars[i]).start();                
        }

        while (start.getCount() > 1) {
            Thread.sleep(100);                          
        }        
        System.out.println("Attention >>> Start!!!");
        start.countDown();
        
        while (finish.getCount() > 0) {        
        }
        System.out.println("Attention >>> Finish!!!");
    }
}

class Car implements Runnable {
    
    private Race race;
    private int speed;
    private String name;    
    private static int CARS_COUNT; 
    
    static {
        CARS_COUNT = 0;
    } 
    public String getName() {
        return name;
    }
    public int getSpeed() {
        return speed;
    }
    public Car(Race race, int speed) {
        this.race = race;
        this.speed = speed;
        CARS_COUNT++;
        this.name = "Участник #" + CARS_COUNT;
    }
    @Override
    public void run() {
        try {
            System.out.println(this.name + " готовится");
            Thread.sleep(500 + (int)(Math.random() * 80));
            System.out.println(this.name + " готов");
            HW5_CD.start.countDown();
            HW5_CD.start.await();
        } catch (Exception e) {
            e.printStackTrace();
        }

        for (int i = 0; i < race.getStages().size(); i++) {
            race.getStages().get(i).go(this);
        }
        
        if (HW5_CD.finish.getCount() > 0) {       
            System.out.println(name + " - Finish");
        }
        if (HW5_CD.finish.getCount() == HW5_CD.CARS_COUNT) {       
            System.out.println(name + " - WIN");
        }
        
        try {
            HW5_CD.finish.countDown();   
            HW5_CD.finish.await();       
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

abstract class Stage {
    protected int length;
    protected String description;
    
    public String getDescription() {
        return description;
    }
    public abstract void go(Car c);
}

class Road extends Stage {
    public Road(int length) {
        this.length = length;
        this.description = "Дорога " + length + " метров";
    }
    @Override
    public void go(Car c) {
        try {
            System.out.println(c.getName() + " начал этап: " + description);
            Thread.sleep(length / c.getSpeed() * 100);
            System.out.println(c.getName() + " закончил этап: " + description);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class Tunnel extends Stage {
    public Tunnel() {
        this.length = 80;
        this.description = "Тоннель " + length + " метров";
    }
    @Override
    public void go(Car c) {
        try {
            try {                
                System.out.println(c.getName() + " готовится к этапу(ждет): " + description);
                HW5_CD.tunnel.acquire(); 
                System.out.println(c.getName() + " начал этап: " + description);
                Thread.sleep(length / c.getSpeed() * 100);
                HW5_CD.tunnel.release();                 
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                System.out.println(c.getName() + " закончил этап: " + description);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

class Race {
    private ArrayList<Stage> stages;
    
    public Race(Stage... stages) {
        this.stages = new ArrayList<>(Arrays.asList(stages));
    }
    public ArrayList<Stage> getStages() {
        return stages; 
    }       
}