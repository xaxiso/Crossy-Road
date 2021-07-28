package crossyroad;

import javax.swing.JFrame;
import javax.swing.JPanel;

import crossyroad.gamepanel.GamePanel;
import crossyroad.endpanel.EndPanel;
import crossyroad.startpanel.StartPanel;

/**
 * 遊戲視窗
 */
public class Window extends JFrame {

    private JPanel nowPanel;
    private int width;
    private int height;
    private int score;

    /**
     * 建構子
     * 
     * @param width          視窗寬度
     * @param height         視窗高度
     * @param title          視窗標題
     * @param titleImagePath 標題畫面上面的圖片
     */
    public Window(int width, int height, String title, String titleImagePath) {
        super();
        this.score = 0;
        this.width = width;
        this.height = height;
        this.setTitle(title);
        this.setSize(width, height);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        nowPanel = new StartPanel(width, height, titleImagePath, this);
        this.add(nowPanel);
        this.setVisible(true);
    }

    public int getScore() {
        return score;
    }

    public void addScore() {
        ++score;
    }

    public void startGame() {
        score = 0;
        nowPanel = new GamePanel(width, height, this);
        refresh();
    }

    public void endGame() {
        nowPanel = new EndPanel(score, this);
        refresh();
    }

    private void refresh() {
        this.setContentPane(nowPanel);
        revalidate();
        repaint();
    }
}