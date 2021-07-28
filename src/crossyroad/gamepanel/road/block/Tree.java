package crossyroad.gamepanel.road.block;

/**
 * 樹
 */
public class Tree extends Block {
    private final String file = "src/crossyroad/gamepanel/road/block/tree.png";

    public Tree() {
        setImage(file);
        setSize(getIcon().getIconWidth(), getIcon().getIconHeight());
    }

    @Override
    public void move() {
        // 樹不會動...
    }
}