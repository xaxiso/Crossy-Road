package crossyroad.gamepanel;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import crossyroad.gamepanel.road.Direction;
import crossyroad.gamepanel.road.Road;
import crossyroad.gamepanel.road.block.Block;

/**
 * 遊戲操控的handler
 */
public class MoveAction extends AbstractAction {

	private final GamePanel gamePanel;
	private final Direction direction;

	/**
	 * 建構子
	 * 
	 * @param gamePanel 遊戲進行畫面
	 * @param direction 要移動的方向
	 */
	public MoveAction(GamePanel gamePanel, Direction direction) {
		super();
		this.gamePanel = gamePanel;
		this.direction = direction;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Hero hero = gamePanel.getHero();
		switch (direction) {
			case UP:
				final Road frontRoad = gamePanel.getRoads().get(gamePanel.getRoads().indexOf(hero.getOnRoad()) - 1);
				final String frontType = frontRoad.getType();
				if (frontType == "Land") {
					for (Block b : frontRoad.getBlocks())
						if (Math.abs(b.getX() - hero.getX()) < 50)
							return;
				}
				hero.setLocation(hero.getX(), hero.getY() - 90);
				hero.setOnRoad(frontRoad);
				if (hero.getY() < 200) {
					for (Road r : gamePanel.getRoads())
						r.setLocation(r.getX(), r.getY() + 90);
					hero.setLocation(hero.getX(), hero.getY() + 90);
				}
				final boolean frontCollision = hero.checkCollision(frontRoad.getBlocks().get(0));
				if (frontType == "Road" && frontCollision) {
					gamePanel.getTimer().stop();
					gamePanel.getWindow().endGame();
				}
				if (frontType == "Water" && !frontCollision) {
					gamePanel.getTimer().stop();
					gamePanel.getWindow().endGame();
				}
				gamePanel.getScoreBoard().addScore();
				break;
			case LEFT:
				if (hero.getX() == 0)
					return;
				if (hero.getOnRoad().getType() == "Land")
					for (Block b : hero.getOnRoad().getBlocks())
						if (b.getX() == hero.getX() - 100)
							return;
				hero.setLocation(hero.getX() - 100, hero.getY());
				final boolean leftCollision = hero.checkCollision(hero.getOnRoad().getBlocks().get(0));
				if (hero.getOnRoad().getType() == "Water" && !leftCollision) {
					gamePanel.getTimer().stop();
					gamePanel.getWindow().endGame();
				}
				break;
			case DOWN:
				final Road backRoad = gamePanel.getRoads().get(gamePanel.getRoads().indexOf(hero.getOnRoad()) + 1);
				final String backType = backRoad.getType();
				if (backType == "Land") {
					for (Block b : backRoad.getBlocks())
						if (Math.abs(b.getX() - hero.getX()) < 50)
							return;
				}
				hero.setOnRoad(backRoad);
				hero.setLocation(hero.getX(), hero.getY() + 90);
				final boolean backCollision = hero.checkCollision(backRoad.getBlocks().get(0));
				if (backType == "Road" && backCollision) {
					gamePanel.getTimer().stop();
					gamePanel.getWindow().endGame();
				}
				if (backType == "Water" && !backCollision) {
					gamePanel.getTimer().stop();
					gamePanel.getWindow().endGame();
				}
				if (hero.getY() > 846) {
					gamePanel.getTimer().stop();
					gamePanel.getWindow().endGame();
				}
				gamePanel.getScoreBoard().addCountBack();
				break;
			case RIGHT:
				if (hero.getX() == 1200)
					return;
				if (hero.getOnRoad().getType() == "Land")
					for (Block b : hero.getOnRoad().getBlocks())
						if (b.getX() == hero.getX() + 100)
							return;
				hero.setLocation(hero.getX() + 100, hero.getY());
				final boolean rightCollision = hero.checkCollision(hero.getOnRoad().getBlocks().get(0));
				if (hero.getOnRoad().getType() == "Water" && !rightCollision) {
					gamePanel.getTimer().stop();
					gamePanel.getWindow().endGame();
				}
				break;
			default:
				return;
		}
		if (hero.getOnRoad().getType() != "Water")
			hero.setLocation((hero.getX() / 100) * 100, hero.getY());
	}

}
