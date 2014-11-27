package ca.javajesus.game.gui;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;

public class Help extends JFrame {

	private static final long serialVersionUID = 1L;

	protected JPanel window = new JPanel();
	private JButton controls, back, quit;
	private Rectangle rcontrols, rback, rquit;
	// Configuration config = new Configuration();

	private int width = 800;
	private int height = 400;
	protected int button_width = 80;
	protected int button_height = 40;

	public Help() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}

		// setUndecorated(true);
		setTitle("JavaJesus Help:");
		setSize(new Dimension(width, height));
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		getContentPane().add(window);
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);
		window.setLayout(null);
		drawButtons();

		repaint();
	}

	private void drawButtons() {
		controls = new JButton("Controls");
		rcontrols = new Rectangle((width / 2) - (button_width / 2), 90,
				button_width, button_height);
		controls.setBounds(rcontrols);
		window.add(controls);

		back = new JButton("Back");
		rback = new Rectangle((width / 2) - (button_width / 2), 190,
				button_width, button_height);
		back.setBounds(rback);
		window.add(back);

		quit = new JButton("Quit");
		rquit = new Rectangle((width / 2) - (button_width / 2), 240,
				button_width, button_height);
		quit.setBounds(rquit);
		window.add(quit);

		controls.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// config.loadConfiguration("settings.xml");
				// dispose();

				// Stephen- haha who wrote this and what triggers it?
				System.out
						.print("This game is made by a bunch of highschool students. If you are above the age of 30, you are probably reconsidering your life accomplishments right now.");
			}
		});

	}

}
