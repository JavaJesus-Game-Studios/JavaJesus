package ca.javajesus.game.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.sound.sampled.Clip;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import ca.javajesus.game.Game;
import ca.javajesus.game.InputHandler;
import ca.javajesus.game.SoundHandler;
import ca.javajesus.game.ZombieSurvival;

public class Launcher extends JFrame implements Runnable {

	private static final long serialVersionUID = 1L;

	/** Temporary Solution to limit frames */
	private final int FRAMES_PER_SECOND = 60;
	private final int DELAY = 1000 / FRAMES_PER_SECOND;
	public boolean running = false;

	private final String VERSION = "Alpha 0.1.3";
	private final String LAST_UPDATED = "Last Updated 2/1/2014";

	private int swordOffset;
	private boolean nextScreen = false;
	private int buttonId = 0;
	private int id;

	protected JPanel window = new JPanel();

	private int width = 800;
	private int height = 750;
	protected int button_width = 80;
	protected int button_height = 40;

	public SoundHandler sound = SoundHandler.sound;
	Thread thread;

	public Launcher(int id) {
		this.id = id;
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}

		new InputHandler(this);
		sound.playLoop(sound.background1);
		setUndecorated(true);
		setTitle("JavaJesus Launcher:");
		setSize(new Dimension(width, height));
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);
		window.setLayout(null);
		swordOffset = 0;
		repaint();
	}

	public void run() {
		while (running) {
			renderMenu();
			//updateFrameLocation();
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

	protected void updateFrameLocation() {
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
		try {
			g.drawImage(
					ImageIO.read(Launcher.class.getResource("/GUI/GUI_Menus/Main_Menu.png")),
					0, 0, 800, 800, null);

			switch (id) {

			case 0: {
				/** Story Button */
				if (InputHandler.MouseX > 365 && InputHandler.MouseX < 365 + 80
						&& InputHandler.MouseY > 450
						&& InputHandler.MouseY < 450 + 30) {
					g.drawImage(ImageIO.read(Launcher.class
							.getResource("/GUI/Buttons/story_on.png")), 365, 450,
							100, 30, null);
					g.drawImage(ImageIO.read(Launcher.class
							.getResource("/GUI/Buttons/sword_selector.png")),
							365 - 110 + swordOffset, 450, 100, 30, null);
					if (InputHandler.MouseButton == 1) {
						InputHandler.MouseButton = 0;
						sound.play(sound.sheathe);
						nextScreen = true;
						buttonId = 0;
					}

				} else {
					g.drawImage(ImageIO.read(Launcher.class
							.getResource("/GUI/Buttons/story_off.png")), 365, 450,
							100, 30, null);
				}

				/** Sandbox Button */
				if (InputHandler.MouseX > 365 && InputHandler.MouseX < 365 + 80
						&& InputHandler.MouseY > 510
						&& InputHandler.MouseY < 510 + 30) {
					g.drawImage(ImageIO.read(Launcher.class
							.getResource("/GUI/Buttons/sandbox_on.png")), 365, 510,
							100, 30, null);
					g.drawImage(ImageIO.read(Launcher.class
							.getResource("/GUI/Buttons/sword_selector.png")),
							365 - 110 + swordOffset, 510, 100, 30, null);
					if (InputHandler.MouseButton == 1) {
						InputHandler.MouseButton = 0;
						sound.play(sound.sheathe);
						nextScreen = true;
						buttonId = 1;
					}

				} else {
					g.drawImage(ImageIO.read(Launcher.class
							.getResource("/GUI/Buttons/sandbox_off.png")), 365,
							510, 100, 30, null);
				}

				/** Options Button */
				if (InputHandler.MouseX > 365 && InputHandler.MouseX < 365 + 80
						&& InputHandler.MouseY > 570
						&& InputHandler.MouseY < 570 + 30) {
					g.drawImage(ImageIO.read(Launcher.class
							.getResource("/GUI/Buttons/options_on.png")), 365, 570,
							100, 30, null);
					g.drawImage(ImageIO.read(Launcher.class
							.getResource("/GUI/Buttons/sword_selector.png")),
							365 - 110 + swordOffset, 570, 100, 30, null);
					if (InputHandler.MouseButton == 1) {
						InputHandler.MouseButton = 0;
						sound.play(sound.sheathe);
						nextScreen = true;
						buttonId = 2;
					}

				} else {
					g.drawImage(ImageIO.read(Launcher.class
							.getResource("/GUI/Buttons/options_off.png")), 365,
							570, 100, 30, null);
				}

				/** Help */
				if (InputHandler.MouseX > 365 && InputHandler.MouseX < 365 + 80
						&& InputHandler.MouseY > 630
						&& InputHandler.MouseY < 630 + 30) {
					g.drawImage(ImageIO.read(Launcher.class
							.getResource("/GUI/Buttons/help_on.png")), 365, 630,
							100, 30, null);
					g.drawImage(ImageIO.read(Launcher.class
							.getResource("/GUI/Buttons/sword_selector.png")),
							365 - 110 + swordOffset, 630, 100, 30, null);
					if (InputHandler.MouseButton == 1) {
						InputHandler.MouseButton = 0;
						sound.play(sound.sheathe);
						nextScreen = true;
						buttonId = 3;
					}

				} else {
					g.drawImage(ImageIO.read(Launcher.class
							.getResource("/GUI/Buttons/help_off.png")), 365, 630,
							100, 30, null);
				}
				break;

			}
			case 1: {

				/** Survival Mode */
				if (InputHandler.MouseX > 365 && InputHandler.MouseX < 365 + 80
						&& InputHandler.MouseY > 450
						&& InputHandler.MouseY < 450 + 30) {
					g.drawImage(ImageIO.read(Launcher.class
							.getResource("/GUI/Buttons/survival_on.png")), 365,
							450, 100, 30, null);
					g.drawImage(ImageIO.read(Launcher.class
							.getResource("/GUI/Buttons/sword_selector.png")),
							365 - 110 + swordOffset, 450, 100, 30, null);
					if (InputHandler.MouseButton == 1) {
						InputHandler.MouseButton = 0;
						sound.play(sound.sheathe);
						nextScreen = true;
						buttonId = 5;
					}

				} else {
					g.drawImage(ImageIO.read(Launcher.class
							.getResource("/GUI/Buttons/survival_off.png")), 365,
							450, 100, 30, null);
				}

				/** Zombies Button */
				if (InputHandler.MouseX > 365 && InputHandler.MouseX < 365 + 80
						&& InputHandler.MouseY > 510
						&& InputHandler.MouseY < 510 + 30) {
					g.drawImage(ImageIO.read(Launcher.class
							.getResource("/GUI/Buttons/zombies_on.png")), 365, 510,
							100, 30, null);
					g.drawImage(ImageIO.read(Launcher.class
							.getResource("/GUI/Buttons/sword_selector.png")),
							365 - 110 + swordOffset, 510, 100, 30, null);
					if (InputHandler.MouseButton == 1) {
						InputHandler.MouseButton = 0;
						sound.play(sound.sheathe);
						nextScreen = true;
						buttonId = 6;
					}

				} else {
					g.drawImage(ImageIO.read(Launcher.class
							.getResource("/GUI/Buttons/zombies_off.png")), 365,
							510, 100, 30, null);
				}
				break;

			}
			case 2: {
				/** Audio Button */
				if (InputHandler.MouseX > 365 && InputHandler.MouseX < 365 + 80
						&& InputHandler.MouseY > 450
						&& InputHandler.MouseY < 450 + 30) {
					g.drawImage(ImageIO.read(Launcher.class
							.getResource("/GUI/Buttons/audio_on.png")), 365, 450,
							100, 30, null);
					g.drawImage(ImageIO.read(Launcher.class
							.getResource("/GUI/Buttons/sword_selector.png")),
							365 - 110 + swordOffset, 450, 100, 30, null);
					if (InputHandler.MouseButton == 1) {
						InputHandler.MouseButton = 0;
						sound.play(sound.sheathe);
						nextScreen = true;
						buttonId = 8;
					}

				} else {
					g.drawImage(ImageIO.read(Launcher.class
							.getResource("/GUI/Buttons/audio_off.png")), 365, 450,
							100, 30, null);
				}

				/** Video Button */
				if (InputHandler.MouseX > 365 && InputHandler.MouseX < 365 + 80
						&& InputHandler.MouseY > 510
						&& InputHandler.MouseY < 510 + 30) {
					g.drawImage(ImageIO.read(Launcher.class
							.getResource("/GUI/Buttons/video_on.png")), 365, 510,
							100, 30, null);
					g.drawImage(ImageIO.read(Launcher.class
							.getResource("/GUI/Buttons/sword_selector.png")),
							365 - 110 + swordOffset, 510, 100, 30, null);
					if (InputHandler.MouseButton == 1) {
						InputHandler.MouseButton = 0;
						sound.play(sound.sheathe);
						nextScreen = true;
						buttonId = 9;
					}

				} else {
					g.drawImage(ImageIO.read(Launcher.class
							.getResource("/GUI/Buttons/video_off.png")), 365, 510,
							100, 30, null);
				}

				/** Controls Button */
				if (InputHandler.MouseX > 365 && InputHandler.MouseX < 365 + 80
						&& InputHandler.MouseY > 570
						&& InputHandler.MouseY < 570 + 30) {
					g.drawImage(ImageIO.read(Launcher.class
							.getResource("/GUI/Buttons/controls_on.png")), 365,
							570, 100, 30, null);
					g.drawImage(ImageIO.read(Launcher.class
							.getResource("/GUI/Buttons/sword_selector.png")),
							365 - 110 + swordOffset, 570, 100, 30, null);
					if (InputHandler.MouseButton == 1) {
						InputHandler.MouseButton = 0;
						sound.play(sound.sheathe);
						nextScreen = true;
						buttonId = 10;
					}
				} else {
					g.drawImage(ImageIO.read(Launcher.class
							.getResource("/GUI/Buttons/controls_off.png")), 365,
							570, 100, 30, null);
				}
				break;
			}
			case 3: {
				/** New Story Button */
				if (InputHandler.MouseX > 365 && InputHandler.MouseX < 365 + 80
						&& InputHandler.MouseY > 450
						&& InputHandler.MouseY < 450 + 30) {
					g.drawImage(ImageIO.read(Launcher.class
							.getResource("/GUI/Buttons/new_on.png")), 365, 450,
							100, 30, null);
					g.drawImage(ImageIO.read(Launcher.class
							.getResource("/GUI/Buttons/sword_selector.png")),
							365 - 110 + swordOffset, 450, 100, 30, null);
					if (InputHandler.MouseButton == 1) {
						InputHandler.MouseButton = 0;
						sound.play(sound.sheathe);
						nextScreen = true;
						buttonId = 11;
					}

				} else {
					g.drawImage(ImageIO.read(Launcher.class
							.getResource("/GUI/Buttons/new_off.png")), 365, 450,
							100, 30, null);
				}

				/** Continue Button */
				if (InputHandler.MouseX > 365 && InputHandler.MouseX < 365 + 80
						&& InputHandler.MouseY > 510
						&& InputHandler.MouseY < 510 + 30) {
					g.drawImage(ImageIO.read(Launcher.class
							.getResource("/GUI/Buttons/continue_on.png")), 365,
							510, 100, 30, null);
					g.drawImage(ImageIO.read(Launcher.class
							.getResource("/GUI/Buttons/sword_selector.png")),
							365 - 110 + swordOffset, 510, 100, 30, null);
					if (InputHandler.MouseButton == 1) {
						InputHandler.MouseButton = 0;
						sound.play(sound.sheathe);
						nextScreen = true;
						buttonId = 12;
					}

				} else {
					g.drawImage(ImageIO.read(Launcher.class
							.getResource("/GUI/Buttons/continue_off.png")), 365,
							510, 100, 30, null);
				}
				break;

			}

			case 4: {
				/** Mute Button */
				if (InputHandler.MouseX > 365 && InputHandler.MouseX < 365 + 80
						&& InputHandler.MouseY > 450
						&& InputHandler.MouseY < 450 + 30) {
					g.drawImage(ImageIO.read(Launcher.class
							.getResource("/GUI/Buttons/mute_on.png")), 365, 450,
							100, 30, null);
					g.drawImage(ImageIO.read(Launcher.class
							.getResource("/GUI/Buttons/sword_selector.png")),
							365 - 110 + swordOffset, 450, 100, 30, null);
					if (InputHandler.MouseButton == 1) {
						InputHandler.MouseButton = 0;
						sound.play(sound.sheathe);
						nextScreen = true;
						buttonId = 13;
					}

				} else {
					g.drawImage(ImageIO.read(Launcher.class
							.getResource("/GUI/Buttons/mute_off.png")), 365, 450,
							100, 30, null);
				}
				break;
			}

			}

			if (id != 0) {
				/** Back */
				if (InputHandler.MouseX > 365 && InputHandler.MouseX < 365 + 80
						&& InputHandler.MouseY > 630
						&& InputHandler.MouseY < 630 + 30) {
					g.drawImage(ImageIO.read(Launcher.class
							.getResource("/GUI/Buttons/back_on.png")), 365, 630,
							100, 30, null);
					g.drawImage(ImageIO.read(Launcher.class
							.getResource("/GUI/Buttons/sword_selector.png")),
							365 - 110 + swordOffset, 630, 100, 30, null);
					if (InputHandler.MouseButton == 1) {
						InputHandler.MouseButton = 0;
						sound.play(sound.sheathe);
						nextScreen = true;
						buttonId = 7;
					}

				} else {
					g.drawImage(ImageIO.read(Launcher.class
							.getResource("/GUI/Buttons/back_off.png")), 365, 630,
							100, 30, null);
				}
			}

			/** Quit */
			if (InputHandler.MouseX > 365 && InputHandler.MouseX < 365 + 80
					&& InputHandler.MouseY > 690
					&& InputHandler.MouseY < 690 + 30) {
				g.drawImage(ImageIO.read(Launcher.class
						.getResource("/GUI/Buttons/quit_on.png")), 365, 690, 100,
						30, null);
				g.drawImage(ImageIO.read(Launcher.class
						.getResource("/GUI/Buttons/sword_selector.png")),
						365 - 110 + swordOffset, 690, 100, 30, null);
				if (InputHandler.MouseButton == 1) {
					InputHandler.MouseButton = 0;
					sound.play(sound.sheathe);
					nextScreen = true;
					buttonId = 4;
				}

			} else {
				g.drawImage(ImageIO.read(Launcher.class
						.getResource("/GUI/Buttons/quit_off.png")), 365, 690, 100,
						30, null);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		g.setColor(Color.RED);
		g.setFont(new Font("Verdana", 0, 20));
		g.drawString(VERSION, 5, 20);
		g.drawString(LAST_UPDATED, 5, 725);
		g.dispose();
		bs.show();

		if (nextScreen) {
			swordOffset += 10;
			if (swordOffset > 100) {
				swordOffset = 0;
				nextScreen = false;
				transferScreen(buttonId);
			}
		}

	}

	private void transferScreen(int id) {

		switch (id) {
		case 0: {
			this.id = 3;
			return;
		}
		case 1: {
			this.id = 1;
			return;
		}
		case 2: {
			this.id = 2;
			return;
		}
		case 3: {
			System.out.println("Help Coming Soon");
			return;
		}
		case 4: {
			System.exit(0);
			this.stopMenu();
			return;
		}
		case 5: {
			running = false;
			dispose();
			sound.background1.stop();
			new ZombieSurvival().start();
			this.stopMenu();
			return;
		}
		case 6: {
			running = false;
			dispose();
			sound.background1.stop();
			new ZombieSurvival().start();
			this.stopMenu();
			return;
		}
		case 7: {
			this.id = 0;
			return;
		}
		case 8: {
			// System.out.println("Audio Coming Soon");
			this.id = 4;
			return;
		}
		case 9: {
			System.out.println("Video Coming Soon");
			return;
		}
		case 10: {
			System.out.println("Controls Coming Soon");
			return;
		}
		case 11: {
			running = false;
			dispose();
			sound.background1.stop();
			new Game().start();
			this.stopMenu();
			return;
		}
		case 12: {
			System.out.println("Cannot load game, coming soon");
			return;
		}
		case 13: {
			if (!sound.muted) {
				sound.muted = true;
				sound.background1.stop();
			} else {
				sound.muted = false;
				sound.play(sound.background1);
			}
			return;
		}
		default: {
			return;
		}
		}

	}
}
