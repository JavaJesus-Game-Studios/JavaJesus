package ca.javajesus.game.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.UIManager;

import ca.javajesus.game.Game;
import ca.javajesus.game.InputHandler;
import ca.javajesus.game.SoundHandler;

public class Launcher extends JFrame implements Runnable {

	private static final long serialVersionUID = 1L;

	private boolean running;

	private final String VERSION = "Alpha 0.6.0";
	private final String LAST_UPDATED = "Last Updated 7/26/2015";

	private int swordOffset;
	private boolean nextScreen;
	private int buttonId;

	private int id;
	private int lastId;

	private int width = 800;
	private int height = 750;
	protected int button_width = 80;
	protected int button_height = 40;

	private Game game;

	private final int MAINMENU = 0, SURVIVALMENU = 1, OPTIONSMENU = 2,
			STORYMENU = 3, AUDIOMENU = 4;

	private final int STORY = 0, SANDBOX = 1, OPTIONS = 2, HELP = 3, QUIT = 4,
			SURVIVAL = 5, ZOMBIES = 6, BACK = 7, AUDIO = 8, VIDEO = 9,
			CONTROLS = 10, NEWSTORY = 11, CONTINUESTORY = 12, MUTE = 13;

	private BufferedImage background, sword_selector, story_on, story_off,
			sandbox_on, sandbox_off, options_on, options_off, help_on,
			help_off, survival_on, survival_off, zombies_on, zombies_off,
			audio_on, audio_off, video_on, video_off, controls_on,
			controls_off, new_on, new_off, continue_on, continue_off, mute_on,
			mute_off, back_on, back_off, quit_on, quit_off;

	private SoundHandler sound = SoundHandler.sound;

	public Launcher(Game game) {
		this.game = game;
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}

		new InputHandler(this);
		sound.playLoop(sound.background1);
		setUndecorated(true);
		setTitle("JavaJesus Launcher");
		setSize(new Dimension(width, height));
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);
		swordOffset = 0;
		init();
		toFront();
	}

	private void init() {
		try {
			background = ImageIO.read(Launcher.class
					.getResource("/GUI/GUI_Menus/Main_Menu.png"));

			sword_selector = ImageIO.read(Launcher.class
					.getResource("/GUI/Buttons/sword_selector.png"));

			story_on = ImageIO.read(Launcher.class
					.getResource("/GUI/Buttons/story_on.png"));

			story_off = ImageIO.read(Launcher.class
					.getResource("/GUI/Buttons/story_off.png"));

			sandbox_on = ImageIO.read(Launcher.class
					.getResource("/GUI/Buttons/sandbox_on.png"));

			sandbox_off = ImageIO.read(Launcher.class
					.getResource("/GUI/Buttons/sandbox_off.png"));

			options_on = ImageIO.read(Launcher.class
					.getResource("/GUI/Buttons/options_on.png"));

			options_off = ImageIO.read(Launcher.class
					.getResource("/GUI/Buttons/options_off.png"));

			help_on = ImageIO.read(Launcher.class
					.getResource("/GUI/Buttons/help_on.png"));

			help_off = ImageIO.read(Launcher.class
					.getResource("/GUI/Buttons/help_off.png"));

			survival_on = ImageIO.read(Launcher.class
					.getResource("/GUI/Buttons/survival_on.png"));

			survival_off = ImageIO.read(Launcher.class
					.getResource("/GUI/Buttons/survival_off.png"));

			zombies_on = ImageIO.read(Launcher.class
					.getResource("/GUI/Buttons/zombies_on.png"));

			zombies_off = ImageIO.read(Launcher.class
					.getResource("/GUI/Buttons/zombies_off.png"));

			audio_on = ImageIO.read(Launcher.class
					.getResource("/GUI/Buttons/audio_on.png"));

			audio_off = ImageIO.read(Launcher.class
					.getResource("/GUI/Buttons/audio_off.png"));

			video_on = ImageIO.read(Launcher.class
					.getResource("/GUI/Buttons/video_on.png"));

			video_off = ImageIO.read(Launcher.class
					.getResource("/GUI/Buttons/video_off.png"));

			controls_on = ImageIO.read(Launcher.class
					.getResource("/GUI/Buttons/controls_on.png"));

			controls_off = ImageIO.read(Launcher.class
					.getResource("/GUI/Buttons/controls_off.png"));

			new_on = ImageIO.read(Launcher.class
					.getResource("/GUI/Buttons/new_on.png"));

			new_off = ImageIO.read(Launcher.class
					.getResource("/GUI/Buttons/new_off.png"));

			continue_on = ImageIO.read(Launcher.class
					.getResource("/GUI/Buttons/continue_on.png"));

			continue_off = ImageIO.read(Launcher.class
					.getResource("/GUI/Buttons/continue_off.png"));

			mute_on = ImageIO.read(Launcher.class
					.getResource("/GUI/Buttons/mute_on.png"));

			mute_off = ImageIO.read(Launcher.class
					.getResource("/GUI/Buttons/mute_off.png"));

			back_on = ImageIO.read(Launcher.class
					.getResource("/GUI/Buttons/back_on.png"));

			back_off = ImageIO.read(Launcher.class
					.getResource("/GUI/Buttons/back_off.png"));

			quit_on = ImageIO.read(Launcher.class
					.getResource("/GUI/Buttons/quit_on.png"));

			quit_off = ImageIO.read(Launcher.class
					.getResource("/GUI/Buttons/quit_off.png"));

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void run() {
		long lastMinute = System.currentTimeMillis();
		long lastTime = System.nanoTime();
		double nsPerTick = 1000000000 / 60.0;
		int frames = 0;
		long lastTimer = System.currentTimeMillis();
		double delta = 0;
		long previousTime = System.currentTimeMillis();

		while (running) {
			try {
				if (System.currentTimeMillis() > lastMinute + 1000) {
					lastMinute = System.currentTimeMillis();
				}
				long now = System.nanoTime();
				delta += (now - lastTime) / nsPerTick;
				lastTime = now;

				while (delta >= 1) {
					delta--;
				}
				frames++;
				render();
				
				if (System.currentTimeMillis() > previousTime + 20) {
					previousTime = System.currentTimeMillis();
					if (nextScreen) {
						swordOffset += 10;
						if (swordOffset > 100) {
							swordOffset = 0;
							nextScreen = false;
							transferScreen(buttonId);
						}
					}
				}
				
				if (System.currentTimeMillis() - lastTimer >= 1000) {
					lastTimer += 1000;
					System.out.println(frames + " fps");
					frames = 0;
				}

			} catch (Exception e) {
				e.printStackTrace();
				stop();
			}
		}
		this.dispose();
	}

	public void start() {
		running = true;
		new Thread(this, "Menu").start();
	}

	public void stop() {
		running = false;
	}

	protected void updateFrameLocation() {
		if (InputHandler.dragged) {
			int x = getX();
			int y = getY();
			setLocation(x + InputHandler.MouseDX - InputHandler.MousePX, y
					+ InputHandler.MouseDY - InputHandler.MousePY);
		}
	}

	private void render() {
		BufferStrategy bs = getBufferStrategy();
		if (bs == null) {
			createBufferStrategy(3);
			return;
		}

		Graphics g = bs.getDrawGraphics();
		g.drawImage(background, 0, 0, 800, 800, null);

		switch (id) {

		case MAINMENU: {
			/** Story Button */
			if (InputHandler.MouseX > 365 && InputHandler.MouseX < 365 + 100
					&& InputHandler.MouseY > 450
					&& InputHandler.MouseY < 450 + 30) {
				g.drawImage(story_on, 365, 450, 100, 30, null);
				g.drawImage(sword_selector, 365 - 110 + swordOffset, 450, 100,
						30, null);
				if (InputHandler.MouseButton == 1) {
					InputHandler.MouseButton = 0;
					sound.play(sound.sheathe);
					nextScreen = true;
					buttonId = STORY;
					lastId = MAINMENU;
				}

			} else {
				g.drawImage(story_off, 365, 450, 100, 30, null);
			}

			/** Sandbox Button */
			if (InputHandler.MouseX > 365 && InputHandler.MouseX < 365 + 100
					&& InputHandler.MouseY > 510
					&& InputHandler.MouseY < 510 + 30) {
				g.drawImage(sandbox_on, 365, 510, 100, 30, null);
				g.drawImage(sword_selector, 365 - 110 + swordOffset, 510, 100,
						30, null);
				if (InputHandler.MouseButton == 1) {
					InputHandler.MouseButton = 0;
					sound.play(sound.sheathe);
					nextScreen = true;
					buttonId = SANDBOX;
					lastId = MAINMENU;
				}

			} else {
				g.drawImage(sandbox_off, 365, 510, 100, 30, null);
			}

			/** Options Button */
			if (InputHandler.MouseX > 365 && InputHandler.MouseX < 365 + 100
					&& InputHandler.MouseY > 570
					&& InputHandler.MouseY < 570 + 30) {
				g.drawImage(options_on, 365, 570, 100, 30, null);
				g.drawImage(sword_selector, 365 - 110 + swordOffset, 570, 100,
						30, null);
				if (InputHandler.MouseButton == 1) {
					InputHandler.MouseButton = 0;
					sound.play(sound.sheathe);
					nextScreen = true;
					buttonId = OPTIONS;
					lastId = MAINMENU;
				}

			} else {
				g.drawImage(options_off, 365, 570, 100, 30, null);
			}

			/** Help */
			if (InputHandler.MouseX > 365 && InputHandler.MouseX < 365 + 100
					&& InputHandler.MouseY > 630
					&& InputHandler.MouseY < 630 + 30) {
				g.drawImage(help_on, 365, 630, 100, 30, null);
				g.drawImage(sword_selector, 365 - 110 + swordOffset, 630, 100,
						30, null);
				if (InputHandler.MouseButton == 1) {
					InputHandler.MouseButton = 0;
					sound.play(sound.sheathe);
					nextScreen = true;
					buttonId = HELP;
					lastId = MAINMENU;
				}

			} else {
				g.drawImage(help_off, 365, 630, 100, 30, null);
			}
			break;

		}
		case SURVIVALMENU: {

			/** Survival Mode */
			if (InputHandler.MouseX > 365 && InputHandler.MouseX < 365 + 100
					&& InputHandler.MouseY > 450
					&& InputHandler.MouseY < 450 + 30) {
				g.drawImage(survival_on, 365, 450, 100, 30, null);
				g.drawImage(sword_selector, 365 - 110 + swordOffset, 450, 100,
						30, null);
				if (InputHandler.MouseButton == 1) {
					InputHandler.MouseButton = 0;
					sound.play(sound.sheathe);
					nextScreen = true;
					buttonId = SURVIVAL;
					lastId = SURVIVALMENU;
				}

			} else {
				g.drawImage(survival_off, 365, 450, 100, 30, null);
			}

			/** Zombies Button */
			if (InputHandler.MouseX > 365 && InputHandler.MouseX < 365 + 100
					&& InputHandler.MouseY > 510
					&& InputHandler.MouseY < 510 + 30) {
				g.drawImage(zombies_on, 365, 510, 100, 30, null);
				g.drawImage(sword_selector, 365 - 110 + swordOffset, 510, 100,
						30, null);
				if (InputHandler.MouseButton == 1) {
					InputHandler.MouseButton = 0;
					sound.play(sound.sheathe);
					nextScreen = true;
					buttonId = ZOMBIES;
					lastId = SURVIVALMENU;
				}

			} else {
				g.drawImage(zombies_off, 365, 510, 100, 30, null);
			}
			break;

		}
		case OPTIONSMENU: {
			/** Audio Button */
			if (InputHandler.MouseX > 365 && InputHandler.MouseX < 365 + 100
					&& InputHandler.MouseY > 450
					&& InputHandler.MouseY < 450 + 30) {
				g.drawImage(audio_on, 365, 450, 100, 30, null);
				g.drawImage(sword_selector, 365 - 110 + swordOffset, 450, 100,
						30, null);
				if (InputHandler.MouseButton == 1) {
					InputHandler.MouseButton = 0;
					sound.play(sound.sheathe);
					nextScreen = true;
					buttonId = AUDIO;
					lastId = MAINMENU;
				}

			} else {
				g.drawImage(audio_off, 365, 450, 100, 30, null);
			}

			/** Video Button */
			if (InputHandler.MouseX > 365 && InputHandler.MouseX < 365 + 100
					&& InputHandler.MouseY > 510
					&& InputHandler.MouseY < 510 + 30) {
				g.drawImage(video_on, 365, 510, 100, 30, null);
				g.drawImage(sword_selector, 365 - 110 + swordOffset, 510, 100,
						30, null);
				if (InputHandler.MouseButton == 1) {
					InputHandler.MouseButton = 0;
					sound.play(sound.sheathe);
					nextScreen = true;
					buttonId = VIDEO;
					lastId = MAINMENU;
				}

			} else {
				g.drawImage(video_off, 365, 510, 100, 30, null);
			}

			/** Controls Button */
			if (InputHandler.MouseX > 365 && InputHandler.MouseX < 365 + 100
					&& InputHandler.MouseY > 570
					&& InputHandler.MouseY < 570 + 30) {
				g.drawImage(controls_on, 365, 570, 100, 30, null);
				g.drawImage(sword_selector, 365 - 110 + swordOffset, 570, 100,
						30, null);
				if (InputHandler.MouseButton == 1) {
					InputHandler.MouseButton = 0;
					sound.play(sound.sheathe);
					nextScreen = true;
					buttonId = CONTROLS;
					lastId = MAINMENU;
				}
			} else {
				g.drawImage(controls_off, 365, 570, 100, 30, null);
			}
			break;
		}
		case 3: {
			/** New Story Button */
			if (InputHandler.MouseX > 365 && InputHandler.MouseX < 365 + 100
					&& InputHandler.MouseY > 450
					&& InputHandler.MouseY < 450 + 30) {
				g.drawImage(new_on, 365, 450, 100, 30, null);
				g.drawImage(sword_selector, 365 - 110 + swordOffset, 450, 100,
						30, null);
				if (InputHandler.MouseButton == 1) {
					InputHandler.MouseButton = 0;
					sound.play(sound.sheathe);
					nextScreen = true;
					buttonId = NEWSTORY;
					lastId = MAINMENU;
				}

			} else {
				g.drawImage(new_off, 365, 450, 100, 30, null);
			}

			/** Continue Button */
			if (InputHandler.MouseX > 365 && InputHandler.MouseX < 365 + 100
					&& InputHandler.MouseY > 510
					&& InputHandler.MouseY < 510 + 30) {
				g.drawImage(continue_on, 365, 510, 100, 30, null);
				g.drawImage(sword_selector, 365 - 110 + swordOffset, 510, 100,
						30, null);
				if (InputHandler.MouseButton == 1) {
					InputHandler.MouseButton = 0;
					sound.play(sound.sheathe);
					nextScreen = true;
					buttonId = CONTINUESTORY;
					lastId = MAINMENU;
				}

			} else {
				g.drawImage(continue_off, 365, 510, 100, 30, null);
			}
			break;

		}

		case AUDIOMENU: {
			/** Mute Button */
			if (InputHandler.MouseX > 365 && InputHandler.MouseX < 365 + 100
					&& InputHandler.MouseY > 450
					&& InputHandler.MouseY < 450 + 30) {
				g.drawImage(mute_on, 365, 450, 100, 30, null);
				g.drawImage(sword_selector, 365 - 110 + swordOffset, 450, 100,
						30, null);
				if (InputHandler.MouseButton == 1) {
					InputHandler.MouseButton = 0;
					sound.play(sound.sheathe);
					nextScreen = true;
					buttonId = MUTE;
					lastId = OPTIONSMENU;
				}

			} else {
				g.drawImage(mute_off, 365, 450, 100, 30, null);
			}
			break;
		}

		}

		if (id != MAINMENU) {
			/** Back */
			if (InputHandler.MouseX > 365 && InputHandler.MouseX < 365 + 100
					&& InputHandler.MouseY > 630
					&& InputHandler.MouseY < 630 + 30) {
				g.drawImage(back_on, 365, 630, 100, 30, null);
				g.drawImage(sword_selector, 365 - 110 + swordOffset, 630, 100,
						30, null);
				if (InputHandler.MouseButton == 1) {
					InputHandler.MouseButton = 0;
					sound.play(sound.sheathe);
					nextScreen = true;
					buttonId = BACK;
				}

			} else {
				g.drawImage(back_off, 365, 630, 100, 30, null);
			}
		}

		/** Quit */
		if (InputHandler.MouseX > 365 && InputHandler.MouseX < 365 + 90
				&& InputHandler.MouseY > 690 && InputHandler.MouseY < 690 + 30) {
			g.drawImage(quit_on, 365, 690, 100, 30, null);
			g.drawImage(sword_selector, 365 - 110 + swordOffset, 690, 100, 30,
					null);
			if (InputHandler.MouseButton == 1) {
				InputHandler.MouseButton = 0;
				sound.play(sound.sheathe);
				nextScreen = true;
				buttonId = QUIT;
			}

		} else {
			g.drawImage(quit_off, 365, 690, 100, 30, null);
		}

		g.setColor(Color.RED);
		g.setFont(new Font("Verdana", 0, 20));
		g.drawString(VERSION, 5, 20);
		g.drawString(LAST_UPDATED, 5, 725);
		g.dispose();
		bs.show();

	}

	private void transferScreen(int id) {

		switch (id) {
		case STORY: {
			this.id = STORYMENU;
			return;
		}
		case SANDBOX: {
			this.id = SURVIVALMENU;
			return;
		}
		case OPTIONS: {
			this.id = OPTIONSMENU;
			return;
		}
		case HELP: {
			System.out.println("Help Coming Soon");
			return;
		}
		case QUIT: {
			this.stop();
			return;
		}
		case SURVIVAL: {
			sound.background1.stop();
			Game.mode = Game.GameMode.SURVIVAL;
			game.start();
			this.stop();
			return;
		}
		case ZOMBIES: {
			sound.background1.stop();
			Game.mode = Game.GameMode.MINI;
			game.start();
			this.stop();
			return;
		}
		case BACK: {
			this.id = lastId;
			return;
		}
		case AUDIO: {
			this.id = AUDIOMENU;
			return;
		}
		case VIDEO: {
			System.out.println("Video Coming Soon");
			return;
		}
		case CONTROLS: {
			System.out.println("Controls Coming Soon");
			return;
		}
		case NEWSTORY: {
			sound.background1.stop();
			game.start();
			this.stop();
			return;
		}
		case CONTINUESTORY: {
			sound.background1.stop();
			game.startWithLoad();
			this.stop();
			return;
		}
		case MUTE: {
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
			System.out.println("ID IS MISSING");
			return;
		}
		}

	}

}
