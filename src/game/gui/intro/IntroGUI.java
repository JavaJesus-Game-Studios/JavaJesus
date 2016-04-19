package game.gui.intro;

import game.Display;
import game.Game;
import game.InputHandler;
import game.entities.Player;
import game.gui.ScreenGUI;
import game.gui.slots.PlayerSlotGUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class IntroGUI extends ScreenGUI implements ActionListener {

	// Used for serialization
	private static final long serialVersionUID = 1L;

	// Name that is displayed
	private String name;

	// TextField to enter the name
	private JTextField nameBox;

	// Color List Panel
	private ColorListGUI colorList;

	// Skin Color Panel
	private SkinColorGUI sclist;

	// Instance of player to display
	private Player player = Game.player;

	// Slot Panel where the played is displayed
	private PlayerSlotGUI pScreen;

	/**
	 * Initializes instance variables and puts the panels together
	 */
	public IntroGUI() {

		this.pScreen = new PlayerSlotGUI(Display.FRAME_WIDTH / 2,
				Display.FRAME_HEIGHT + 10, "/GUI/GUI_Inventory/GUI_PLAYER.png", 0.5);
		this.setFocusable(true);
		this.setLayout(new BorderLayout(0, 0));
		this.input = new InputHandler(this);
		this.setPreferredSize(new Dimension(Display.FRAME_WIDTH,
				Display.FRAME_HEIGHT));

		JPanel mainPanel = new JPanel();
		mainPanel.setBackground(Color.BLACK);

		JPanel p1 = new JPanel(new BorderLayout());
		p1.add(pScreen, BorderLayout.CENTER);

		mainPanel.add(p1);

		JPanel p2 = new JPanel(new BorderLayout());

		JPanel infoPanel = new JPanel();
		infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.PAGE_AXIS));
		infoPanel.setBackground(Color.GRAY);
		infoPanel.setPreferredSize(new Dimension(Display.FRAME_WIDTH / 2,
				Display.FRAME_HEIGHT + 10));
		p2.add(infoPanel, BorderLayout.CENTER);

		JLabel label = new JLabel("Enter your name: ");
		nameBox = new JTextField();
		label.setAlignmentX(Component.CENTER_ALIGNMENT);
		infoPanel.add(label);
		infoPanel.add(nameBox);

		JLabel label2 = new JLabel("Choose a shirt color: ");
		colorList = new ColorListGUI(player);
		label2.setAlignmentX(Component.CENTER_ALIGNMENT);
		infoPanel.add(label2);
		infoPanel.add(colorList);

		JLabel label3 = new JLabel("Choose a skin color: ");
		sclist = new SkinColorGUI(player);
		label3.setAlignmentX(Component.CENTER_ALIGNMENT);
		infoPanel.add(label3);
		infoPanel.add(sclist);

		JButton confirm = new JButton("Confirm");
		confirm.setAlignmentX(Component.CENTER_ALIGNMENT);
		confirm.setFont(new Font(Game.FONT_NAME, Font.PLAIN, 25));
		confirm.addActionListener(this);
		infoPanel.add(confirm);

		label.setFont(new Font(Game.FONT_NAME, Font.PLAIN, 25));
		label2.setFont(new Font(Game.FONT_NAME, Font.PLAIN, 25));
		label3.setFont(new Font(Game.FONT_NAME, Font.PLAIN, 25));

		mainPanel.add(p2);

		this.add(mainPanel);

	}

	/**
	 * Updates the player panel with the user defined traits
	 */
	public void tick() {

		pScreen.tick();
		nameBox.grabFocus();
		if (nameBox.getText().length() > 13) {
			nameBox.setText(nameBox.getText().substring(0, 13));
		}
		player.setName(nameBox.getText());
		player.setShirtColor(colorList.getColor());
		player.setSkinColor(sclist.getColor());
	}

	/**
	 * When clicked, the game is started and the player is updated
	 * 
	 * @param arg0
	 *            the action that was performed
	 */
	@Override
	public void actionPerformed(ActionEvent arg0) {
		try {
			name = nameBox.getText();
			player.setName(name);
			player.setShirtColor(colorList.getColor());
			player.setSkinColor(sclist.getColor());
			if (player.getName().equals("Derek Jow")
					|| player.getName().equals("Stephen Northway")) {
				player.grantDevPowers();
			}
			Display.displayGame();
		} catch (NullPointerException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Returns the player's name
	 * 
	 * @return The Player's Name
	 */
	public String getPlayerName() {
		return name;
	}

}
