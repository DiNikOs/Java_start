package hw1;
import java.util.*;
import hw1.users.*;
import hw1.obstacles.*;

class Main {
    public static void main(String[] args) {
        
        Course cource = new Course(new Obstacle[] { // did not all
            new Cross(80), 
            new Wall(2), 
            new Wall(1), 
            new Cross(120)});
       
        Team team = new Team(new Competitor[] {     // did not all
            new Human("Bob"),
            new Cat("Barsik"), 
            new Dog("Bobic")});
        cource.doIt(team);                          // done
        team.info();                                // done
    }
}