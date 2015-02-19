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
import ca.javajesus.game.gui.ScreenGUI;
import ca.javajesus.game.gui.slots.PlayerSlotGUI;

public class IntroGUI extends ScreenGUI implements ActionListener {

	private static final long serialVersionUID = 1L;
	private String name;
	JTextField nameBox;
	private Game game;
	ColorListGUI colorList;
	SkinColorGUI sclist;
	HairColorGUI hairlist;

	private PlayerSlotGUI pScreen;

	public IntroGUI(Game game) {

		this.game = game;
		this.pScreen = new PlayerSlotGUI(game.player, 2, 1, 1);
		pScreen.setScale(1);
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
		colorList = new ColorListGUI(game.player);
		p2.add(colorList, BorderLayout.CENTER);

		JPanel p4 = new JPanel(new BorderLayout());
		JLabel l3 = new JLabel("Choose a skin color: ");
		p4.add(l3, BorderLayout.NORTH);
		sclist = new SkinColorGUI(game.player);
		p4.add(sclist, BorderLayout.CENTER);
		
		JPanel p6 = new JPanel(new BorderLayout());
		JLabel l1 = new JLabel("Choose a hair color: ");
		p6.add(l1, BorderLayout.NORTH);
		hairlist = new HairColorGUI(game.player);
		p6.add(hairlist, BorderLayout.CENTER);

		JPanel p5 = new JPanel(new GridLayout(4, 0));
		p5.add(p3);
		p5.add(p2);
		p5.add(p4);
		p5.add(p6);

		panel.add(p1, BorderLayout.CENTER);
		panel.add(p5, BorderLayout.EAST);

		this.add(panel, BorderLayout.CENTER);
		
	}

	public void tick() {

		pScreen.tick();
		nameBox.grabFocus();

	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		try {
			name = nameBox.getText();
			game.player.setName(name);
			game.player.setShirtColor(colorList.getColor());
			game.player.setSkinColor(sclist.getColor());
			game.player.setHairColor(hairlist.getColor());
			game.player.updateColor();
			Game.displayGame();
		} catch (NullPointerException e) {
			System.out.println("Something went wrong. It's probably your fault.");
			System.out.println("JK It is probably still loading, just wait. Relax. Look at the clouds.");
			System.out.println("Probably by the time you are finished reading this message the game has loaded.");
			e.printStackTrace();
		}
	}

	public String getPlayerName() {
		return name;
	}

}
