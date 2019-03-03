/**
 * Java2. Core. HW1
 * 
 * @author Dmitriy Ostrovskiy
 * @version 0.1 date Mar 03, 2019
 */
package hw1.obstacles;
import hw1.users.*;
import hw1.obstacles.*;
import java.util.*;

public class Course {
    Obstacle[] courses;

    public Course(Obstacle[] courses) {
        this.courses = courses;
    } 

    public void doIt(Team team) {
        for (Obstacle course : courses)
            team.doIt(course);
    }

   @Override
   public String toString() {
       return Arrays.toString(courses);
   }
}