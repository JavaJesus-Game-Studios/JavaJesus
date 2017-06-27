package javajesus.gui.intro;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import javajesus.JavaJesus;
import javajesus.InputHandler;
import javajesus.JavaJesus;
import javajesus.gui.ScreenGUI;
import javajesus.gui.slots.PlayerSlotGUI;

public class IntroGUI extends ScreenGUI implements ActionListener {

	// Used for serialization
	private static final long serialVersionUID = 1L;

	// TextField to enter the name
	private JTextField nameBox;

	// Color List Panel
	private ColorListGUI colorList;

	// Skin Color Panel
	private SkinColorGUI sclist;

	// Slot Panel where the played is JavaJesused
	private PlayerSlotGUI pScreen;
	
	// instance of what created this screen
	private JavaJesus main;

	/**
	 * Initializes instance variables and puts the panels together
	 */
	public IntroGUI(JavaJesus main) {
		
		this.main = main;

		this.pScreen = new PlayerSlotGUI(JavaJesus.WINDOW_WIDTH / 2,
				JavaJesus.WINDOW_HEIGHT, "/GUI/GUI_Inventory/GUI_PLAYER.png", 0.5);
		this.setFocusable(true);
		this.setLayout(new BorderLayout(0, 0));
		this.input = new InputHandler(this);
		this.setPreferredSize(new Dimension(JavaJesus.WINDOW_WIDTH,
				JavaJesus.WINDOW_HEIGHT));

		this.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
		this.setBackground(Color.BLACK);

		JPanel infoPanel = new JPanel();
		infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.PAGE_AXIS));
		infoPanel.setBackground(Color.GRAY);
		infoPanel.setPreferredSize(new Dimension(JavaJesus.WINDOW_WIDTH / 2,
				JavaJesus.WINDOW_HEIGHT));

		JLabel label = new JLabel("Enter your name: ");
		nameBox = new JTextField();
		label.setAlignmentX(Component.CENTER_ALIGNMENT);
		infoPanel.add(label);
		infoPanel.add(nameBox);

		JLabel label2 = new JLabel("Choose a shirt color: ");
		colorList = new ColorListGUI();
		label2.setAlignmentX(Component.CENTER_ALIGNMENT);
		infoPanel.add(label2);
		infoPanel.add(colorList);

		JLabel label3 = new JLabel("Choose a skin color: ");
		sclist = new SkinColorGUI();
		label3.setAlignmentX(Component.CENTER_ALIGNMENT);
		infoPanel.add(label3);
		infoPanel.add(sclist);
		
		JButton confirm = new JButton("Confirm");
		confirm.setAlignmentX(Component.CENTER_ALIGNMENT);
		confirm.setFont(new Font(JavaJesus.FONT_NAME, Font.PLAIN, 25));
		confirm.addActionListener(this);
		infoPanel.add(confirm);

		label.setFont(new Font(JavaJesus.FONT_NAME, Font.PLAIN, 25));
		label2.setFont(new Font(JavaJesus.FONT_NAME, Font.PLAIN, 25));
		label3.setFont(new Font(JavaJesus.FONT_NAME, Font.PLAIN, 25));
		
		this.add(pScreen);
		this.add(infoPanel);

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
		
		pScreen.setPlayerName(nameBox.getText());
		pScreen.setShirtColor(colorList.getColor());
		pScreen.setSkinColor(sclist.getColor());
		
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
			// starts the game
			main.createPlayer(pScreen.getPlayerName(), pScreen.getShirtColor(), pScreen.getSkinColor());
			
		} catch (NullPointerException e) {
			e.printStackTrace();
		}
	}

}
