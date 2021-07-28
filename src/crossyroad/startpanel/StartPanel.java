package crossyroad.startpanel;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import crossyroad.Window;

/**
 * 遊戲開始標題畫面
 */
public class StartPanel extends JPanel implements KeyListener {
	private final JLabel titleImage;
	private final StartButton startButton;
	private final Window window;

	/**
	 * 建構子
	 * 
	 * @param width
	 * @param height
	 * @param titleImagePath
	 * @param window
	 */
	public StartPanel(int width, int height, String titleImagePath, Window window) {
		super();
		this.window = window;
		addKeyListener(this);
		this.setFocusable(true);
		this.requestFocusInWindow();
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		// 加背景
		this.setBackground(new Color(0x66c8e5));
		// 加圖片
		BufferedImage img = null;
		try {
			img = ImageIO.read(new File(titleImagePath));
		} catch (IOException e) {
			e.printStackTrace();
		}
		final int titleImageHeight = height / 4;
		Image scaledImg = img.getScaledInstance((int) (((double) img.getWidth() / img.getHeight()) * titleImageHeight),
				titleImageHeight, BufferedImage.SCALE_SMOOTH);
		titleImage = new JLabel(new ImageIcon(scaledImg));
		titleImage.setBorder(new EmptyBorder(height / 10, 0, height / 10, 0));
		titleImage.setAlignmentX(Component.CENTER_ALIGNMENT);
		this.add(titleImage, "Center");
		// 加按鈕
		startButton = new StartButton("Press Enter to Start");
		startButton.setFont(new Font("Arial", Font.BOLD, 28));
		startButton.setPreferredSize(new Dimension(width * 2 / 3, height / 10));
		startButton.setForeground(Color.WHITE);
		startButton.setBackground(Color.BLACK);
		startButton.setPressedBackground(new Color(0x333333));
		startButton.setFocusable(false);
		startButton.addActionListener(e -> {
			window.startGame();
		});
		startButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		this.add(startButton);
	}

	/**
	 * 偵測按下鍵盤按鍵的method，按下的按鈕放開才算
	 * 
	 * @param e 按下哪個按鈕的event
	 */
	@Override
	public void keyReleased(KeyEvent e) {
		if (KeyEvent.getKeyText(e.getKeyCode()) == "Enter")
			window.startGame();
	}

	/**
	 * 用不到
	 * 
	 * @param e 按下哪個按鈕的event
	 */
	@Override
	public void keyTyped(KeyEvent e) {
	}

	/**
	 * 用不到
	 * 
	 * @param e 按下哪個按鈕的event
	 */
	@Override
	public void keyPressed(KeyEvent e) {
	}

}