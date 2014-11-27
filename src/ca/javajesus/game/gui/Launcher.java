package ca.javajesus.game.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferStrategy;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;

import ca.javajesus.game.Game;
import ca.javajesus.game.InputHandler;

public class Launcher extends JFrame implements Runnable {

	private static final long serialVersionUID = 1L;

	/** Temporary Solution to limit frames */
	private final int FRAMES_PER_SECOND = 60;
	private final int DELAY = 1000 / FRAMES_PER_SECOND;
	public boolean running = false;

	protected JPanel window = new JPanel();
	private JButton play, options, help, quit, sandbox;
	private Rectangle rplay, roptions, rhelp, rquit, rsandbox;
	// Configuration config = new Configuration();

	private int width = 800;
	private int height = 400;
	protected int button_width = 80;
	protected int button_height = 40;

	Thread thread;

	public Launcher(int id) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}

		new InputHandler(this);

		setUndecorated(true);
		setTitle("JavaJesus Launcher:");
		setSize(new Dimension(width, height));
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		// getContentPane().add(window);
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);
		window.setLayout(null);
		if (id == 0) {
			//drawButtons();
		}
		repaint();
	}

	private void drawButtons() {
		play = new JButton("Play!");
		rplay = new Rectangle((width / 2) - (button_width / 2), 90,
				button_width, button_height);
		play.setBounds(rplay);
		window.add(play);

		sandbox = new JButton("Sandbox!");
		rsandbox = new Rectangle((width / 2) - (button_width / 2), 140,
				button_width, button_height);
		sandbox.setBounds(rsandbox);
		window.add(sandbox);

		options = new JButton("Options!");
		roptions = new Rectangle((width / 2) - (button_width / 2), 190,
				button_width, button_height);
		options.setBounds(roptions);
		window.add(options);

		help = new JButton("Help!");
		rhelp = new Rectangle((width / 2) - (button_width / 2), 240,
				button_width, button_height);
		help.setBounds(rhelp);
		window.add(help);

		quit = new JButton("Quit!");
		rquit = new Rectangle((width / 2) - (button_width / 2), 290,
				button_width, button_height);
		quit.setBounds(rquit);
		window.add(quit);

		play.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// config.loadConfiguration("settings.xml");
				running = false;
				dispose();
				new Game().start();
			}
		});
		sandbox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new Sandbox();
			}
		});
		options.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new Options();
			}
		});
		help.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new Help();
			}
		});
		quit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
	}

	public void run() {
		while (running) {
			renderMenu();
			updateFrameLocation();
			// Temporary Frame Limiter
			try {
				Thread.sleep(DELAY / 2);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public void startMenu() {
		running = true;
		thread = new Thread(this, "Menu");
		thread.start();
	}

	public void stopMenu() {
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private void updateFrameLocation() {
		if (InputHandler.dragged) {
			int x = getX();
			int y = getY();
			setLocation(x + InputHandler.MouseDX - InputHandler.MousePX, y
					+ InputHandler.MouseDY - InputHandler.MousePY);
		}
		System.out.println(InputHandler.MouseDX);
	}

	private void renderMenu() {
		BufferStrategy bs = getBufferStrategy();
		if (bs == null) {
			createBufferStrategy(3);
			return;
		}

		Graphics g = bs.getDrawGraphics();
		g.setColor(Color.RED);
		try {
			g.drawImage(
					ImageIO.read(Launcher.class.getResource("/templogo.png")),
					0, 0, 800, 400, null);

			/** Play Button */
			if (InputHandler.MouseX > width / 2
					&& InputHandler.MouseX < width / 2 + 80
					&& InputHandler.MouseY > 90
					&& InputHandler.MouseY < 90 + 30) {
				g.drawImage(ImageIO.read(Launcher.class
						.getResource("/Buttons/play_on.png")), width / 2, 90,
						80, 30, null);
				g.drawImage(ImageIO.read(Launcher.class
						.getResource("/Buttons/sword.png")), width / 2 - 80,
						90, 80, 30, null);
				if (InputHandler.MouseButton == 1) {
					running = false;
					dispose();
					new Game().start();
					this.stopMenu();
					return;
				}

			} else {
				g.drawImage(ImageIO.read(Launcher.class
						.getResource("/Buttons/play.png")), width / 2, 90, 80,
						30, null);
			}

			/** Sandbox Button */
			if (InputHandler.MouseX > width / 2
					&& InputHandler.MouseX < width / 2 + 80
					&& InputHandler.MouseY > 140
					&& InputHandler.MouseY < 140 + 30) {
				g.drawImage(ImageIO.read(Launcher.class
						.getResource("/Buttons/sandbox_on.png")), width / 2,
						140, 80, 30, null);
				g.drawImage(ImageIO.read(Launcher.class
						.getResource("/Buttons/sword.png")), width / 2 - 80,
						140, 80, 30, null);
				if (InputHandler.MouseButton == 1) {
					dispose();
					new Sandbox();
					this.stopMenu();
					return;
				}

			} else {
				g.drawImage(ImageIO.read(Launcher.class
						.getResource("/Buttons/sandbox.png")), width / 2, 140,
						80, 30, null);
			}

			/** Options Button */
			if (InputHandler.MouseX > width / 2
					&& InputHandler.MouseX < width / 2 + 80
					&& InputHandler.MouseY > 190
					&& InputHandler.MouseY < 190 + 30) {
				g.drawImage(ImageIO.read(Launcher.class
						.getResource("/Buttons/options_on.png")), width / 2,
						190, 80, 30, null);
				g.drawImage(ImageIO.read(Launcher.class
						.getResource("/Buttons/sword.png")), width / 2 - 80,
						190, 80, 30, null);
				if (InputHandler.MouseButton == 1) {
					dispose();
					new Options();
					this.stopMenu();
					return;
				}

			} else {
				g.drawImage(ImageIO.read(Launcher.class
						.getResource("/Buttons/options.png")), width / 2, 190,
						80, 30, null);
			}

			/** Help */
			if (InputHandler.MouseX > width / 2
					&& InputHandler.MouseX < width / 2 + 80
					&& InputHandler.MouseY > 240
					&& InputHandler.MouseY < 240 + 30) {
				g.drawImage(ImageIO.read(Launcher.class
						.getResource("/Buttons/help_on.png")), width / 2, 240,
						80, 30, null);
				g.drawImage(ImageIO.read(Launcher.class
						.getResource("/Buttons/sword.png")), width / 2 - 80,
						240, 80, 30, null);
				if (InputHandler.MouseButton == 1) {
					dispose();
					new Help();
					this.stopMenu();
					return;
				}

			} else {
				g.drawImage(ImageIO.read(Launcher.class
						.getResource("/Buttons/help.png")), width / 2, 240, 80,
						30, null);
			}

			/** Quit */
			if (InputHandler.MouseX > width / 2
					&& InputHandler.MouseX < width / 2 + 80
					&& InputHandler.MouseY > 290
					&& InputHandler.MouseY < 290 + 30) {
				g.drawImage(ImageIO.read(Launcher.class
						.getResource("/Buttons/quit_on.png")), width / 2, 290,
						80, 30, null);
				g.drawImage(ImageIO.read(Launcher.class
						.getResource("/Buttons/sword.png")), width / 2 - 80,
						290, 80, 30, null);
				if (InputHandler.MouseButton == 1) {
					System.exit(0);
					this.stopMenu();
					return;
				}

			} else {
				g.drawImage(ImageIO.read(Launcher.class
						.getResource("/Buttons/quit.png")), width / 2, 290, 80,
						30, null);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		g.dispose();
		bs.show();
	}
}
