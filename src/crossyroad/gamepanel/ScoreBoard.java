package crossyroad.gamepanel;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JLabel;

import crossyroad.Window;

/**
 * 記分板
 */
public class ScoreBoard extends JLabel {
	private final Window window;
	private int countBack;

	/**
	 * 建構子
	 * 
	 * @param window 所在的視窗
	 */
	public ScoreBoard(Window window) {
		this.window = window;
		countBack = 0;
		setText(Integer.toString(window.getScore()));
		setFont(new Font("Arial", Font.BOLD, 54));
		setForeground(new Color(0xe85579));
		setSize(100, 100);
	}

	/**
	 * 如果沒有往後的紀錄就加一分，不然減少一次往後的紀錄
	 */
	public void addScore() {
		if (countBack == 0) {
			window.addScore();
			setText(Integer.toString(window.getScore()));
			repaint();
		} else
			--countBack;
	}

	/**
	 * 紀錄往後幾格
	 */
	public void addCountBack() {
		++countBack;
	}

	/**
	 * 取得目前分數
	 * 
	 * @return 目前分數
	 */
	public int getScore() {
		return window.getScore();
	}
}
