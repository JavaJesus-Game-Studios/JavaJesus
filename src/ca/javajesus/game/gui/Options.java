package ca.javajesus.game.gui;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;

public class Options extends JFrame {

	private static final long serialVersionUID = 1L;

	protected JPanel window = new JPanel();
	private JButton vid, sound, back, quit;
	private Rectangle rvid, rsound, rback, rquit;
	// Configuration config = new Configuration();

	private int width = 800;
	private int height = 400;
	protected int button_width = 80;
	protected int button_height = 40;

	public Options() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}

		// setUndecorated(true);
		setTitle("JavaJesus Options:");
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
		vid = new JButton("Video");
		rvid = new Rectangle((width / 2) - (button_width / 2), 90,
				button_width, button_height);
		vid.setBounds(rvid);
		window.add(vid);

		sound = new JButton("Sound");
		rsound = new Rectangle((width / 2) - (button_width / 2), 140,
				button_width, button_height);
		sound.setBounds(rsound);
		window.add(sound);

		back = new JButton("Back");
		rback = new Rectangle((width / 2) - (button_width / 2), 190,
				button_width, button_height);
		back.setBounds(rback);
		window.add(back);

		quit = new JButton("Quit!");
		rquit = new Rectangle((width / 2) - (button_width / 2), 240,
				button_width, button_height);
		quit.setBounds(rquit);
		window.add(quit);

		vid.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// config.loadConfiguration("settings.xml");
				// dispose();
				System.out
						.print("Well your video is probably working just fine!");
			}
		});
		sound.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// dispose();
				System.out.println("Currently this game is silent!");
				// new Options();
			}
		});
		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new Launcher(0).startMenu();
			}
		});
		quit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
	}

}
