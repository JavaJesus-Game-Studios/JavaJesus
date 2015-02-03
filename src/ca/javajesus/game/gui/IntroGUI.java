package ca.javajesus.game.gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import ca.javajesus.game.Game;
import ca.javajesus.game.InputHandler;
import ca.javajesus.game.entities.Player;

public class IntroGUI extends ScreenGUI implements ActionListener{

	private static final long serialVersionUID = 1L;
	private String name;
	JTextField nameBox;
	private Game game;

	public IntroGUI(Game game) {
		
		this.game = game;
		this.setFocusable(true);
		this.setLayout(new BorderLayout(0, 0));
		this.input = new InputHandler(this);
		
		JPanel panel = new JPanel(new BorderLayout());
		JLabel label = new JLabel("Enter your name: ");
		nameBox = new JTextField(20);
		nameBox.addActionListener(this);
		
		panel.add(label, BorderLayout.WEST);
		panel.add(nameBox, BorderLayout.CENTER);
		this.add(panel, BorderLayout.CENTER);

	}

	public void tick() {

	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		name = nameBox.getText();
		game.player.setName(name);
		Game.displayGame();
	}
	
	public String getPlayerName() {
		return name;
	}

}
