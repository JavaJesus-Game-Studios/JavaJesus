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

	private int width = 800;
	private int height = 750;
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
			// drawButtons();
		}
		repaint();
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
					ImageIO.read(Launcher.class.getResource("/Main_Menu.png")),
					0, 0, 800, 800, null);

			/** Story Button */
			if (InputHandler.MouseX > 365 && InputHandler.MouseX < 365 + 80
					&& InputHandler.MouseY > 450
					&& InputHandler.MouseY < 450 + 30) {
				g.drawImage(ImageIO.read(Launcher.class
						.getResource("/Buttons/story_on.png")), 365, 450, 100,
						30, null);
				g.drawImage(ImageIO.read(Launcher.class
						.getResource("/Buttons/sword_selector.png")), 365 - 110,
						450, 100, 30, null);
				if (InputHandler.MouseButton == 1) {
					running = false;
					dispose();
					new Game().start();
					this.stopMenu();
					return;
				}

			} else {
				g.drawImage(ImageIO.read(Launcher.class
						.getResource("/Buttons/story_off.png")), 365, 450, 100,
						30, null);
			}

			/** Sandbox Button */
			if (InputHandler.MouseX > 365 && InputHandler.MouseX < 365 + 80
					&& InputHandler.MouseY > 510
					&& InputHandler.MouseY < 510 + 30) {
				g.drawImage(ImageIO.read(Launcher.class
						.getResource("/Buttons/sandbox_on.png")), 365, 510, 100,
						30, null);
				g.drawImage(ImageIO.read(Launcher.class
						.getResource("/Buttons/sword_selector.png")), 365 - 110,
						510, 100, 30, null);
				if (InputHandler.MouseButton == 1) {
					dispose();
					new Sandbox();
					this.stopMenu();
					return;
				}

			} else {
				g.drawImage(ImageIO.read(Launcher.class
						.getResource("/Buttons/sandbox_off.png")), 365, 510,
						100, 30, null);
			}

			/** Options Button */
			if (InputHandler.MouseX > 365 && InputHandler.MouseX < 365 + 80
					&& InputHandler.MouseY > 570
					&& InputHandler.MouseY < 570 + 30) {
				g.drawImage(ImageIO.read(Launcher.class
						.getResource("/Buttons/options_on.png")), 365, 570, 100,
						30, null);
				g.drawImage(ImageIO.read(Launcher.class
						.getResource("/Buttons/sword_selector.png")), 365 - 110,
						570, 100, 30, null);
				if (InputHandler.MouseButton == 1) {
					dispose();
					new Options();
					this.stopMenu();
					return;
				}

			} else {
				g.drawImage(ImageIO.read(Launcher.class
						.getResource("/Buttons/options_off.png")), 365, 570,
						100, 30, null);
			}

			/** Help */
			if (InputHandler.MouseX > 365 && InputHandler.MouseX < 365 + 80
					&& InputHandler.MouseY > 630
					&& InputHandler.MouseY < 630 + 30) {
				g.drawImage(ImageIO.read(Launcher.class
						.getResource("/Buttons/help_on.png")), 365, 630, 100,
						30, null);
				g.drawImage(ImageIO.read(Launcher.class
						.getResource("/Buttons/sword_selector.png")), 365 - 110,
						630, 100, 30, null);
				if (InputHandler.MouseButton == 1) {
					dispose();
					new Help();
					this.stopMenu();
					return;
				}

			} else {
				g.drawImage(ImageIO.read(Launcher.class
						.getResource("/Buttons/help_off.png")), 365, 630, 100,
						30, null);
			}

			/** Quit */
			if (InputHandler.MouseX > 365 && InputHandler.MouseX < 365 + 80
					&& InputHandler.MouseY > 690
					&& InputHandler.MouseY < 690 + 30) {
				g.drawImage(ImageIO.read(Launcher.class
						.getResource("/Buttons/quit_on.png")), 365, 690, 100,
						30, null);
				g.drawImage(ImageIO.read(Launcher.class
						.getResource("/Buttons/sword_selector.png")), 365 - 110,
						690, 100, 30, null);
				if (InputHandler.MouseButton == 1) {
					System.exit(0);
					this.stopMenu();
					return;
				}

			} else {
				g.drawImage(ImageIO.read(Launcher.class
						.getResource("/Buttons/quit_off.png")), 365, 690, 100,
						30, null);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		g.dispose();
		bs.show();
	}
}
