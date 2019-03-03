/**
 * Java2. Core. HW1
 * 
 * @author Dmitriy Ostrovskiy
 * @version 0.1 date Mar 03, 2019
 */
package hw1.users;
import hw1.users.*;
import hw1.obstacles.*;
import java.util.*;

public class Team {
    private Competitor[] competitors;
    private String result;    
    
    public Team(Competitor[] competitors) {        
        this.competitors = competitors;
        result = "";
    }
    
    public void doIt(Obstacle cource) {
        for (Competitor c : competitors) {
            cource.doIt(c);
            if (!c.isOnDistance()) break;
        }         
    }       
    
    public void info() {
        for (Competitor c : competitors) {
            c.info(); 
        }            
    }   
}