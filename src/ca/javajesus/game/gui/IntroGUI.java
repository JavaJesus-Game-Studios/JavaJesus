package ca.javajesus.game.gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import ca.javajesus.game.Game;
import ca.javajesus.game.InputHandler;

public class IntroGUI extends ScreenGUI implements ActionListener{

	private static final long serialVersionUID = 1L;
	private String name;
	JTextField nameBox;
	private Game game;
	ColorListGUI colorList;
	
	private PlayerSlotGUI pScreen;

	public IntroGUI(Game game) {
		
		this.game = game;
		this.pScreen = new PlayerSlotGUI();
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
		JLabel l2 = new JLabel("Choose a color: ");
		p2.add(l2, BorderLayout.NORTH);
		colorList = new ColorListGUI();
		p2.add(colorList, BorderLayout.CENTER);
		
		panel.add(p1, BorderLayout.CENTER);
		panel.add(p2, BorderLayout.EAST);
		
		this.add(panel, BorderLayout.CENTER);

	}

	public void tick() {

		pScreen.tick();
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		name = nameBox.getText();
		game.player.setName(name);
		game.player.changeColor(colorList.getColor());
		Game.displayGame();
	}
	
	public String getPlayerName() {
		return name;
	}

}
