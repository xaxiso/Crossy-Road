package crossyroad.gamepanel;

import java.awt.Rectangle;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

import crossyroad.gamepanel.road.Road;
import crossyroad.gamepanel.road.block.Block;

/**
 * 遊戲角色
 */
public class Hero extends JLabel {
	private Road onRoad;

	/**
	 * 建構子
	 * 
	 * @param onRoad 在哪條路上
	 */
	public Hero(Road onRoad) {
		setIcon(new ImageIcon("src/crossyroad/gamepanel/htlin.png"));
		setSize(getIcon().getIconWidth(), getIcon().getIconHeight());
		this.onRoad = onRoad;
		setLocation(600, 630);
	}

	/**
	 * 取得目前遊戲角色所在的道路
	 * 
	 * @return 所在的道路
	 */
	public Road getOnRoad() {
		return onRoad;
	}

	/**
	 * 設定遊戲角色所在的道路
	 * 
	 * @param onRoad 新的所在道路
	 */
	public void setOnRoad(Road onRoad) {
		this.onRoad = onRoad;
	}

	/**
	 * 檢查是否有碰到{@code block}
	 * 
	 * @param block 判斷是否碰到的東西
	 * @return 是否有碰到
	 */
	public boolean checkCollision(Block block) {
		final Rectangle first = new Rectangle(this.getX(), 0, this.getIcon().getIconWidth(),
				this.getIcon().getIconHeight());
		final Rectangle second = new Rectangle(block.getX(), 0, block.getWidth(), block.getHeight());
		return first.intersects(second);
	}

}
