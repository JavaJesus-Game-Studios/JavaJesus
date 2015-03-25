package ca.javajesus.game.gui.intro;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

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

		JPanel panel = new JPanel(new BorderLayout());

		JPanel p1 = new JPanel(new BorderLayout());
		JLabel label = new JLabel("Enter your name: ");
		nameBox = new JTextField(20);
		nameBox.addActionListener(this);

		JPanel p3 = new JPanel(new BorderLayout());
		p3.add(label, BorderLayout.NORTH);
		p3.add(nameBox, BorderLayout.SOUTH);

		p1.add(p3, BorderLayout.NORTH);
		p1.add(pScreen, BorderLayout.CENTER);

		JPanel p2 = new JPanel(new BorderLayout());
		JLabel l2 = new JLabel("Choose a shirt color: ");
		p2.add(l2, BorderLayout.NORTH);
		colorList = new ColorListGUI(player);
		p2.add(colorList, BorderLayout.CENTER);

		JPanel p4 = new JPanel(new BorderLayout());
		JLabel l3 = new JLabel("Choose a skin color: ");
		p4.add(l3, BorderLayout.NORTH);
		sclist = new SkinColorGUI(player);
		p4.add(sclist, BorderLayout.CENTER);

		JPanel p5 = new JPanel(new GridLayout(4, 0));
		p5.add(p3);
		p5.add(p2);
		p5.add(p4);

		panel.add(p1, BorderLayout.CENTER);
		panel.add(p5, BorderLayout.EAST);

		this.add(panel, BorderLayout.CENTER);

	}

	public void tick() {

		pScreen.tick();
		nameBox.grabFocus();
		player.setName(nameBox.getText());
		player.setShirtColor(colorList.getColor());
		player.setSkinColor(sclist.getColor());
		player.updateColor();
		if (nameBox.getText().equals("Derek Jow")
				|| nameBox.getText().equals("Stephen Pacwa")
				|| nameBox.getText().equals("Stephen Northway")) {
			player.grantDevPowers();
		}
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
			Game.displayGame();
		} catch (NullPointerException e) {
			e.printStackTrace();
		}
	}

	public String getPlayerName() {
		return name;
	}

}
