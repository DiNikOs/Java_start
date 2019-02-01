/**
* Java.Java_Start.HW6
*
*@ Dmitriy Ostrovskiy
*@ version date Feb 02,2019
*/
import java.util.Random;

class HW6Lesson {

    public static void main(String[] args) {
        Random random = new Random();

        System.out.println("Limit Cat : run =" + 200 + ";\t swim = No swim;jump =" + 2);
        System.out.println("Limit Dog1: run =" + 400 + ";\t swim =" + 10 + ";\tjump =" + 0.5);
        System.out.println("Limit Dog2: run =" + 600 + ";\t swim =" + 10 + ";\tjump =" + 0.5);
        System.out.println();

        for (int i = 0; i < 5; i++) {
            Cat cat = new Cat(random.nextInt(700), random.nextInt(20), Math.floor (random.nextDouble()*30)/10);
            cat.setMaxRun(cat.RUN_MAX);
            cat.setMaxJump(cat.JUMP_MAX);

            Dog1 dog1 = new Dog1(random.nextInt(700), random.nextInt(20), Math.floor (random.nextDouble()*30)/10);      
            dog1.setMaxRun(dog1.RUN_MAX);
            dog1.setMaxJump(dog1.JUMP_MAX);
            dog1.setMaxSwim(dog1.SWIM_MAX);

            Dog2 dog2 = new Dog2(random.nextInt(700), random.nextInt(20), Math.floor (random.nextDouble()*30)/10);      
            dog2.setMaxRun(dog2.RUN_MAX);
            dog2.setMaxJump(dog2.JUMP_MAX);
            dog2.setMaxSwim(dog2.SWIM_MAX);

            Animal[] animals = {cat, dog1, dog2};	
            for (Animal animal : animals) {
                System.out.println( "run(" + animal.getRun() + ")=" + animal.run() + ";\tswim(" + animal.getSwim() + ")=" + animal.swim() + ";\tjump(" + animal.getJump() +  ")=" + animal.jump());        
                }
            System.out.println();
        }     
    }
}

class Cat extends Animal {
    final int RUN_MAX = 200;
    final double JUMP_MAX = 2;
    final String SWIM_MAX = "No swim";
        
    Cat(int run, int swim, double jump) {
        super(run, swim, jump);
    }
    @Override
    public boolean swim() {
      return  false; 
    }
}

class Dog1 extends Animal {
    final int RUN_MAX = 400; 
    final int SWIM_MAX = 10;
    final double JUMP_MAX = 0.5;
      
    Dog1(int run, int swim, double jump) {
        super(run, swim, jump);
    }   
}

class Dog2 extends Dog1 {
    final int RUN_MAX = 600;
    final int SWIM_MAX = 10;
    final double JUMP_MAX = 0.5;
        
    Dog2 (int run, int swim, double jump) {
        super(run, swim, jump);
    }
}

abstract class Animal  { 
    
    protected int RUN_MAX; 
    protected int SWIM_MAX;
    protected double JUMP_MAX;
    
    protected int run;
    protected int swim;
    protected double jump;  

    Animal(int run, int swim, double jump) {
        this.run = run;
        this.swim = swim;
        this.jump = jump;
    }

    public int getRun() {
        return run;
    }  
    public int getSwim() {
        return swim;
    }   
    public double getJump() {
        return jump;
    }

    public void setMaxRun(int RUN_MAX) {
        this.RUN_MAX = RUN_MAX;
    }
    public void setMaxSwim(int SWIM_MAX) {
        this.SWIM_MAX = SWIM_MAX;
    }
    public void setMaxJump(double JUMP_MAX) {
        this.JUMP_MAX = JUMP_MAX;
    }

    public boolean run() {
        if (run <= RUN_MAX) return true;
        else return false;
    }
    public boolean swim() {
        if (swim <= SWIM_MAX) return true;
        else return false;
    }
    public boolean jump() {
        if (jump <= JUMP_MAX) return true;
        else return false;
    }
 }