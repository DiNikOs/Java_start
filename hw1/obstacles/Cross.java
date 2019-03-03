package hw1.obstacles;
import java.util.*;
import hw1.users.*;
import hw1.obstacles.*;

public class Cross extends Obstacle {
    int length;

    public Cross(int length) {
        this.length = length;
    }

    @Override
    public void doIt(Competitor competitor) {
        competitor.run(length);
    }
}