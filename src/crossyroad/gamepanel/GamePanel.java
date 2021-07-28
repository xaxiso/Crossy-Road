package crossyroad.gamepanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.Timer;

import crossyroad.Window;
import crossyroad.gamepanel.road.Direction;
import crossyroad.gamepanel.road.Road;
import crossyroad.gamepanel.road.block.Block;
import crossyroad.utils.RNG;

/**
 * 遊戲進行的畫面
 */
public class GamePanel extends JPanel implements ActionListener {

	private Hero hero;

	private final LinkedList<Road> roads;
	private final Timer timer;
	private final ScoreBoard score;
	private final Window window;

	private static final int roadHeight = 90;
	private static final int maxRoad = 12;

	/**
	 * 建構子
	 * 
	 * @param width  畫面寬度
	 * @param height 畫面高度
	 * @param window 所在的畫面
	 */
	public GamePanel(int width, int height, Window window) {
		super();
		this.window = window;
		this.roads = new LinkedList<>();
		this.score = new ScoreBoard(window);
		this.score.setLocation(0, 0);
		this.add(score);
		this.setComponentZOrder(score, 0);
		setLayout(null);
		setSize(width, height);
		// 加入道路和遊戲角色
		for (int i = -3; i <= maxRoad; ++i) {
			Road road;
			if (i == 7) {
				road = new Road("Land", width, roadHeight);
				for (int j = 0; j < road.getBlocks().size(); ++j)
					if (road.getBlocks().get(j).getX() == 600) {
						road.remove(road.getBlocks().get(j));
						road.getBlocks().remove(j);
						break;
					}
				this.hero = new Hero(road);
			} else
				road = RNG.randRoad(width);
			road.setLocation(0, i * roadHeight);
			roads.add(road);
			this.add(road);
		}
		this.add(hero);
		this.setComponentZOrder(hero, 0);
		// 增加wasd、上下左右鍵的handler
		getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("released W"), "W");
		getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("released A"), "A");
		getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("released S"), "S");
		getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("released D"), "D");
		getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("released Up"), "W");
		getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("released Left"), "A");
		getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("released Down"), "S");
		getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("released Right"), "D");
		getActionMap().put("W", new MoveAction(this, Direction.UP));
		getActionMap().put("A", new MoveAction(this, Direction.LEFT));
		getActionMap().put("S", new MoveAction(this, Direction.DOWN));
		getActionMap().put("D", new MoveAction(this, Direction.RIGHT));
		timer = new Timer(12, this);
		revalidate();
		repaint();
		timer.start();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		for (Road r : roads) {
			r.update();
		}
		if (hero.getOnRoad().getType() == "Road")
			for (Block b : hero.getOnRoad().getBlocks())
				if (hero.checkCollision(b)) {
					timer.stop();
					window.endGame();
				}
		manageObjects();
		repaint();
	}

	/**
	 * 移動畫面上的所有東西，每幀移動一次
	 */
	private void manageObjects() {
		for (Road r : roads)
			r.setLocation(r.getX(), r.getY() + 1);
		if (roads.getLast().getY() > roadHeight * maxRoad) {
			remove(roads.getLast());
			roads.removeLast();
			Road newRoad = RNG.randRoad(this.getWidth());
			newRoad.setLocation(0, roads.getFirst().getY() - roadHeight);
			roads.addFirst(newRoad);
			add(newRoad);
		}
		hero.setLocation(hero.getX(), hero.getY() + 1);
		if (hero.getOnRoad().getType() == "Water") {
			final int velocity = hero.getOnRoad().getVelocity();
			final Direction direction = hero.getOnRoad().getDirection();
			hero.setLocation(hero.getX() + (direction == Direction.LEFT ? velocity : -velocity), hero.getY());
		}
		if (hero.getX() < -5) {
			timer.stop();
			window.endGame();
		}
		if (hero.getX() > 1300) {
			timer.stop();
			window.endGame();
		}
		if (hero.getY() > 836) {
			timer.stop();
			window.endGame();
		}
	}

	/**
	 * 取得畫面上所有路
	 * 
	 * @return 所有路的List
	 */
	public List<Road> getRoads() {
		return roads;
	}

	/**
	 * 取得記分板
	 * 
	 * @return 記分板
	 */
	public ScoreBoard getScoreBoard() {
		return score;
	}

	/**
	 * 取得遊戲角色
	 * 
	 * @return 遊戲角色
	 */
	public Hero getHero() {
		return hero;
	}

	/**
	 * 取得畫面更新器
	 * 
	 * @return 畫面更新器
	 */
	public Timer getTimer() {
		return timer;
	}

	/**
	 * 取得所在畫面
	 * 
	 * @return 所在畫面
	 */
	public Window getWindow() {
		return window;
	}

}