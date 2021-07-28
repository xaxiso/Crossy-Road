package crossyroad.utils;

import java.util.Random;

import crossyroad.gamepanel.road.Direction;
import crossyroad.gamepanel.road.Road;

public class RNG {
    static final Random r;

    static {
        r = new Random();
    }

    public static int randint(int start, int end) {
        // generate a random integer from [start, end]
        return r.nextInt(end - start) + start;
    }

    public static Direction randDirection() {
        int i = randint(0, 2);
        if (i % 2 == 0)
            return Direction.LEFT;
        return Direction.RIGHT;
    }

    public static Road randRoad(int width) {
        int type = RNG.randint(0, 3);
        if (type == 0)
            return new Road("Road", width, 90);
        else if (type == 1)
            return new Road("Land", width, 90);
        else
            return new Road("Water", width, 90);

    }
}
