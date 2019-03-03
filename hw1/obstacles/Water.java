package hw1.obstacles;
import java.util.*;
import hw1.users.*;
import hw1.obstacles.*;

public class Water extends Obstacle {
    int length;

    public Water(int length) {
        this.length = length;
    }

    @Override
    public void doIt(Competitor competitor) {
        competitor.swim(length);
    }
}