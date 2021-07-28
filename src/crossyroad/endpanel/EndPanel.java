package crossyroad.endpanel;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import crossyroad.Window;

/**
 * 遊戲結束畫面
 */
public class EndPanel extends JPanel {

	public EndPanel(int score, Window window) {
		super();
		this.setBackground(new Color(0xe85579));
		this.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 0;
		JLabel lbl1 = new JLabel("Game Over!");
		lbl1.setFont(new Font("Ariel", Font.BOLD, 72));
		lbl1.setForeground(Color.white);
		this.add(lbl1, gbc);
		this.add(Box.createVerticalStrut(200));
		gbc.gridx = 0;
		gbc.gridy = 1;
		JLabel lbl = new JLabel("Your point is");
		lbl.setFont(new Font("Ariel", Font.BOLD, 48));
		lbl.setForeground(Color.white);
		this.add(lbl, gbc);
		this.add(Box.createVerticalStrut(50));
		gbc.gridx = 0;
		gbc.gridy = 2;
		JLabel point = new JLabel(Integer.toString(score));
		point.setFont(new Font("Ariel", Font.BOLD, 48));
		point.setForeground(Color.white);
		this.add(point, gbc);
		this.add(Box.createVerticalStrut(50));
		gbc.weighty = 1;
		gbc.weightx = 0;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		gbc.anchor = GridBagConstraints.SOUTH;
		gbc.gridx = 0;
		gbc.gridy = 3;
		JButton restart = new JButton(new ImageIcon("src/crossyroad/endpanel/start.gif"));
		restart.setBorderPainted(false);
		restart.setFocusPainted(false);
		restart.setContentAreaFilled(false);
		restart.addActionListener(e -> {
			window.startGame();
		});
		this.add(restart, gbc);
	}
}
