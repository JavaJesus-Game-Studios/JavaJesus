package ca.javajesus.game.gui.intro;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import ca.javajesus.game.Display;
import ca.javajesus.game.Game;
import ca.javajesus.game.InputHandler;
import ca.javajesus.game.entities.Player;
import ca.javajesus.game.gui.ScreenGUI;
import ca.javajesus.game.gui.slots.PlayerSlotGUI;

public class IntroGUI extends ScreenGUI implements ActionListener {

	private static final long serialVersionUID = 1L;
	private String name;
	JTextField nameBox;
	ColorListGUI colorList;
	SkinColorGUI sclist;
	private Player player = Game.player;

	private PlayerSlotGUI pScreen;

	public IntroGUI() {

		this.pScreen = new PlayerSlotGUI(player);
		this.setFocusable(true);
		this.setLayout(new BorderLayout(0, 0));
		this.input = new InputHandler(this);
		this.setPreferredSize(new Dimension(Display.FRAME_WIDTH, Display.FRAME_HEIGHT));

		JPanel mainPanel = new JPanel(new FlowLayout());

		mainPanel.add(pScreen);

		JPanel infoPanel = new JPanel();
		infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.PAGE_AXIS));

		JLabel label = new JLabel("Enter your name: ");
		nameBox = new JTextField(20);
		nameBox.addActionListener(this);
		infoPanel.add(label);
		infoPanel.add(nameBox);

		JLabel label2 = new JLabel("Choose a shirt color: ");
		colorList = new ColorListGUI(player);
		infoPanel.add(label2);
		infoPanel.add(colorList);

		JLabel label3 = new JLabel("Choose a skin color: ");
		sclist = new SkinColorGUI(player);
		infoPanel.add(label3);
		infoPanel.add(sclist);

		Dimension size = new Dimension((int) infoPanel.getPreferredSize()
				.getWidth(), (int) infoPanel.getPreferredSize().getHeight() / 2);

		label.setPreferredSize(size);
		nameBox.setPreferredSize(size);
		label2.setPreferredSize(size);
		colorList.setPreferredSize(size);
		label3.setPreferredSize(size);
		sclist.setPreferredSize(size);
		
		label.setFont(new Font(label.getFont().getName(), Font.PLAIN, 25));
		label2.setFont(new Font(label2.getFont().getName(), Font.PLAIN, 25));
		label3.setFont(new Font(label3.getFont().getName(), Font.PLAIN, 25));

		mainPanel.add(infoPanel);

		this.add(mainPanel);

	}

	public void tick() {

		pScreen.tick();
		nameBox.grabFocus();
		if (nameBox.getText().length() > 13) {
			nameBox.setText(nameBox.getText().substring(0, 13));
		}
		player.setName(nameBox.getText());
		player.setShirtColor(colorList.getColor());
		player.setSkinColor(sclist.getColor());
		player.updateColor();
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		try {
			name = nameBox.getText();
			player.setName(name);
			player.setShirtColor(colorList.getColor());
			player.updateColor();
			player.setSkinColor(sclist.getColor());
			player.updateColor();
			if (player.getName().equals("Derek Jow")
					|| player.getName().equals("Stephen Northway")) {
				player.grantDevPowers();
			}
			Display.displayGame();
		} catch (NullPointerException e) {
			e.printStackTrace();
		}
	}

	public String getPlayerName() {
		return name;
	}

}
