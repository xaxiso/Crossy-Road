package crossyroad.startpanel;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * 可以自訂按下時背景顏色的按鈕
 */
public class StartButton extends JButton {
	private Color pressedBackground;
	private Color backgroundColor;

	/**
	 * 建構子
	 * 
	 * @param text 按鈕要顯示的文字
	 */
	public StartButton(String text) {
		super(text);
		setBorderPainted(false);
		setFocusPainted(false);
		setContentAreaFilled(false);
		setOpaque(true);
		pressedBackground = Color.BLACK;
		addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent evt) {
				if (getModel().isPressed()) {
					setBackground(pressedBackground);
				} else if (getModel().isRollover()) {
					setBackground(backgroundColor);
				} else {
					setBackground(backgroundColor);
				}
			}
		});
	}

	@Override
	public void setBackground(Color color) {
		super.setBackground(color);
		backgroundColor = color;
	}

	/**
	 * 設定按下時的顏色
	 * 
	 * @param pressedBackground 按下時的顏色
	 */
	public void setPressedBackground(Color pressedBackground) {
		this.pressedBackground = pressedBackground;
	}
}
