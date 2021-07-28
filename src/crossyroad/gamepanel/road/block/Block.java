package crossyroad.gamepanel.road.block;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import crossyroad.gamepanel.road.Direction;

/**
 * 路上的東西
 */
public abstract class Block extends JLabel {
    private Direction direction;
    private int velocity;

    public Direction getDirection() {
        return direction;
    }

    protected void setDirection(Direction dir) {
        this.direction = dir;
    }

    public int getVelocity() {
        return velocity;
    }

    public void setVelocity(int velocity) {
        this.velocity = velocity;
    }

    protected void setImage(String path) {
        try {
            setIcon(new ImageIcon(path));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    abstract public void move();
}