package ca.javajesus.game.gui;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.security.auth.login.Configuration;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;

import ca.javajesus.game.Game;

public class Launcher extends JFrame {

	private static final long serialVersionUID = 1L;

	protected JPanel window = new JPanel();
	private JButton play, options, help, quit;
	private Rectangle rplay, roptions, rhelp, rquit;
	//Configuration config = new Configuration();

	private int width = 800;
	private int height = 400;
	protected int button_width = 80;
	protected int button_height = 40;

	public Launcher(int id) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}

		// setUndecorated(true);
		setTitle("JavaJesus Launcher:");
		setSize(new Dimension(width, height));
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		getContentPane().add(window);
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);
		window.setLayout(null);
		if (id == 0) {
			drawButtons();
		}
		repaint();
	}

	private void drawButtons() {
		play = new JButton("Play!");
		rplay = new Rectangle((width / 2) - (button_width / 2), 90,
				button_width, button_height);
		play.setBounds(rplay);
		window.add(play);

		options = new JButton("Options!");
		roptions = new Rectangle((width / 2) - (button_width / 2), 140,
				button_width, button_height);
		options.setBounds(roptions);
		window.add(options);

		help = new JButton("Help!");
		rhelp = new Rectangle((width / 2) - (button_width / 2), 190,
				button_width, button_height);
		help.setBounds(rhelp);
		window.add(help);

		quit = new JButton("quit!");
		rquit = new Rectangle((width / 2) - (button_width / 2), 240,
				button_width, button_height);
		quit.setBounds(rquit);
		window.add(quit);

		play.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// config.loadConfiguration("settings.xml");
				dispose();
				new Game().start();
			}
		});
		options.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//dispose();
				System.out.println("COMING SOON!");
				//new Options();
			}
		});
		help.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out
						.println("Call being transfered to New Delhi. Please wait.");
			}
		});
		quit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
	}

}
