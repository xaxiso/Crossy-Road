package crossyroad.gamepanel.road.block;

import crossyroad.gamepanel.road.Direction;
import crossyroad.utils.RNG;

/**
 * 車
 */
public class Car extends Block {
    private String filePath = "src/crossyroad/gamepanel/road/block/car";

    public Car(Direction direction, int velocity) {
        super.setDirection(direction);
        setRandomCarImage();
        setImage(filePath);
        setVelocity(velocity);
        setSize(getIcon().getIconWidth(), getIcon().getIconHeight());
    }

    private void setRandomCarImage() {
        Integer n = RNG.randint(1, 4); // decide which color it is
        filePath += Integer.toString(n);
        if (getDirection() == Direction.LEFT)
            filePath += "right";
        else
            filePath += "left"; // choose the correct direction
        filePath += ".png";
    }

    @Override
    public void move() {
        if (getDirection() == Direction.LEFT)
            setLocation(getX() + getVelocity(), getY());
        else
            setLocation(getX() - getVelocity(), getY());
    }

}