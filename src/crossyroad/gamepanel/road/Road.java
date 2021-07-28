package crossyroad.gamepanel.road;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import crossyroad.gamepanel.road.block.Block;
import crossyroad.gamepanel.road.block.Bridge;
import crossyroad.gamepanel.road.block.Car;
import crossyroad.gamepanel.road.block.Tree;
import crossyroad.utils.RNG;

/**
 * 一條路，會負責生成和移動上面的東西
 */
public class Road extends JPanel {
    private final int width;
    private final int height;
    private final String type;
    private final List<Block> blocks;
    private final Direction direction;
    private int velocity;
    private Image backgroundImage;

    /**
     * 建構子
     * 
     * @param type   路的類型，有Land、Road、Water
     * @param width  路的長度
     * @param height 路的高度
     */
    public Road(String type, int width, int height) {
        this.width = width;
        this.height = height;
        this.type = type;
        this.blocks = new ArrayList<>();
        this.direction = RNG.randDirection();
        this.velocity = RNG.randint(4, 12);
        setVisible(true);
        if (type == "Road") {
            generateCar();
            setBackgroundImage("src/crossyroad/gamepanel/road/block/road.png");
        } else if (type == "Land") {
            generateTree();
            this.velocity = 0;
            setBackground(new Color(0xc3ff5f));
        } else if (type == "Water") {
            generateBridge();
            setBackgroundImage("src/crossyroad/gamepanel/road/block/river.png");
        }
        setSize(width, height);
        setLayout(null);
    }

    /**
     * 取得這條路的類型
     * 
     * @return 路的類型
     */
    public String getType() {
        return type;
    }

    /**
     * 移動路上面的東西
     */
    public void update() {
        for (Block block : blocks) {
            block.move();
            if (block.getDirection() == Direction.LEFT && block.getX() > width + RNG.randint(50, 100))
                block.setLocation(0 - RNG.randint(block.getWidth(), 1000), block.getY());
            else if (block.getDirection() == Direction.RIGHT && block.getX() < -block.getWidth())
                block.setLocation(getWidth() + RNG.randint(block.getWidth(), 1000), block.getY());
        }

    }

    /**
     * 設定路的背景圖片
     * 
     * @param path 背景圖片的路徑
     */
    public void setBackgroundImage(String path) {
        try {
            Image image = ImageIO.read(new File(path));
            backgroundImage = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);

        } catch (Exception e) {
            e.printStackTrace(System.err);
        }
        revalidate();
        repaint();
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        if (backgroundImage != null)
            graphics.drawImage(backgroundImage, 0, 0, this);
    }

    /**
     * Road專用，生成車子
     */
    private void generateCar() {
        Car car = new Car(direction, velocity);
        if (this.direction == Direction.LEFT)
            car.setLocation(0 - RNG.randint(car.getWidth(), 1000), getY());
        else
            car.setLocation(width + car.getWidth() + RNG.randint(car.getWidth(), 1000), getY());
        this.blocks.add(car);
        this.add(car);
    }

    /**
     * Water專用，生成漂流木
     */
    private void generateBridge() {
        Bridge bridge = new Bridge(direction, velocity);
        if (this.direction == Direction.LEFT)
            bridge.setLocation(0 - RNG.randint(bridge.getWidth(), 1000), getY());
        else
            bridge.setLocation(width + bridge.getWidth() + RNG.randint(bridge.getWidth(), 1000), getY());
        this.blocks.add(bridge);
        this.add(bridge);
    }

    /**
     * Land專用，生成樹
     */
    private void generateTree() {
        int n = RNG.randint(1, 5);
        for (int i = 0; i < n; i++) {
            int k = RNG.randint(0, 12);
            int xLoc = k * 100;
            Tree tree = new Tree();
            tree.setLocation(xLoc, getY());
            this.blocks.add(tree);
            this.add(tree);
        }
    }

    /**
     * 取得這條路上東西的移動速度
     * 
     * @return 移動速度
     */
    public int getVelocity() {
        return velocity;
    }

    /**
     * 取得這條路上東西的移動方向
     * 
     * @return 移動方向
     */
    public Direction getDirection() {
        return direction;
    }

    /**
     * 取得這條路上所有東西的List
     * 
     * @return 這條路上所有東西的List
     */
    public List<Block> getBlocks() {
        return blocks;
    }
}
