package crossyroad.gamepanel.road.block;

import crossyroad.gamepanel.road.Direction;
import crossyroad.utils.RNG;

/**
 * 漂流木
 */
public class Bridge extends Block {
    private static final String file = "src/crossyroad/gamepanel/road/block/wood.png";

    public Bridge(Direction direction, int velocity) {
        setDirection(direction);
        setVelocity(velocity);
        setImage(file);
        setSize(getIcon().getIconWidth() / 2 + RNG.randint(20, 180), getIcon().getIconHeight());
    }

    @Override
    public void move() {
        if (getDirection() == Direction.LEFT)
            setLocation(getX() + getVelocity(), getY());
        else
            setLocation(getX() - getVelocity(), getY());
    }

}