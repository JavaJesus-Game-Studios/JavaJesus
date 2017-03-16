package game.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

import game.Display;
import game.Game;
import game.Game.GameMode;
import game.InputHandler;
import game.SoundHandler;
import game.graphics.Screen;
import level.Level;
import level.RandomLevel;
import utility.Direction;

public class Launcher extends JFrame implements Runnable {

	// Used for serialization
	private static final long serialVersionUID = 1L;

	// determines if the game is running
	private boolean running;

	// Version of the game
	private final String VERSION = "Alpha 0.7.9";

	// Last known update
	private final String LAST_UPDATED = "Last Updated 3/16/2017";

	// offset of the sword to render
	private int swordOffset;

	// starting position of sword
	private int swordStart = 110;

	// determines if one of the buttons has been clicked
	private boolean isClicked;

	// The selected button that was clicked
	private LauncherButton selectedButton;

	// Id of the page
	private int pageId;

	// Width of the game
	private int width = Display.FRAME_WIDTH;

	// height of the screen
	private int height = Display.FRAME_HEIGHT + 100;

	// delay between sword frames
	private static final int DELAY = 20;

	// the color of the text
	private Color color;

	// Random generator
	private static final Random random = new Random();

	// the randomly generated background level
	public static final Level level = new RandomLevel(500, 500, new Point(10, 10), true);;

	// the image of the level
	private static final BufferedImage image =
			new BufferedImage(Display.IMAGE_WIDTH, Display.IMAGE_HEIGHT, BufferedImage.TYPE_INT_RGB);

	// Pixel data to be used in the buffered image
	private int[] pixels;

	// Processes the level pixels
	private Screen screen = new Screen(Display.IMAGE_WIDTH, Display.IMAGE_HEIGHT);
	
	// level width and height
	public static final int LEVEL_WIDTH = 200, LEVEL_HEIGHT = 200;
	
	// offset of the level
	private int xOffset, yOffset;

	/**
	 * IDs of the page screens
	 */
	private static final int MAINMENU = 0, SANDBOXMENU = 1, OPTIONSMENU = 2, STORYMENU = 3, AUDIOMENU = 4;

	/**
	 * Ids of the buttons
	 */
	private static final int STORY = 0, SANDBOX = 1, OPTIONS = 2, HELP = 3, QUIT = 4, SURVIVAL = 5, ZOMBIES = 6,
			BACK = 7, AUDIO = 8, VIDEO = 9, CONTROLS = 10, NEWSTORY = 11, CONTINUESTORY = 12, MUTE = 13;

	// Buttons on the launcher
	private LauncherButton story, sandbox, options, help, survival, zombies, audio, video, controls, newStory,
			continueStory, mute, back, quit;

	// buffered images that are displayed on the screen
	private BufferedImage background, sword_selector;
	
	// direction the level moves
	private Direction dir = Direction.SOUTH_EAST;

	/**
	 * Constructor that creates the JFrame
	 */
	public Launcher() {
		
		// sets text color on gui
		color = new Color(random.nextInt(256), random.nextInt(256), random.nextInt(256));

		pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();

		new InputHandler(this);
		SoundHandler.playLoop(SoundHandler.background1);
		//setUndecorated(true);
		setSize(new Dimension(width, height));
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);
		setAlwaysOnTop(true);
		init();
		toFront();
		setResizable(false);

	}

	/**
	 * Initializes instance variables and loads button images
	 */
	private void init() {
		BufferedImage story_on, story_off, sandbox_on, sandbox_off, options_on, options_off, help_on, help_off,
				survival_on, survival_off, zombies_on, zombies_off, audio_on, audio_off, video_on, video_off,
				controls_on, controls_off, new_on, new_off, continue_on, continue_off, mute_on, mute_off, back_on,
				back_off, quit_on, quit_off;
		try {
			background = ImageIO.read(Launcher.class.getResource("/GUI/GUI_Menus/Main_Menu.png"));

			sword_selector = ImageIO.read(Launcher.class.getResource("/GUI/Buttons/sword_selector.png"));

			story_on = ImageIO.read(Launcher.class.getResource("/GUI/Buttons/story_on.png"));

			story_off = ImageIO.read(Launcher.class.getResource("/GUI/Buttons/story_off.png"));

			sandbox_on = ImageIO.read(Launcher.class.getResource("/GUI/Buttons/sandbox_on.png"));

			sandbox_off = ImageIO.read(Launcher.class.getResource("/GUI/Buttons/sandbox_off.png"));

			options_on = ImageIO.read(Launcher.class.getResource("/GUI/Buttons/options_on.png"));

			options_off = ImageIO.read(Launcher.class.getResource("/GUI/Buttons/options_off.png"));

			help_on = ImageIO.read(Launcher.class.getResource("/GUI/Buttons/help_on.png"));

			help_off = ImageIO.read(Launcher.class.getResource("/GUI/Buttons/help_off.png"));

			survival_on = ImageIO.read(Launcher.class.getResource("/GUI/Buttons/survival_on.png"));

			survival_off = ImageIO.read(Launcher.class.getResource("/GUI/Buttons/survival_off.png"));

			zombies_on = ImageIO.read(Launcher.class.getResource("/GUI/Buttons/zombies_on.png"));

			zombies_off = ImageIO.read(Launcher.class.getResource("/GUI/Buttons/zombies_off.png"));

			audio_on = ImageIO.read(Launcher.class.getResource("/GUI/Buttons/audio_on.png"));

			audio_off = ImageIO.read(Launcher.class.getResource("/GUI/Buttons/audio_off.png"));

			video_on = ImageIO.read(Launcher.class.getResource("/GUI/Buttons/video_on.png"));

			video_off = ImageIO.read(Launcher.class.getResource("/GUI/Buttons/video_off.png"));

			controls_on = ImageIO.read(Launcher.class.getResource("/GUI/Buttons/controls_on.png"));

			controls_off = ImageIO.read(Launcher.class.getResource("/GUI/Buttons/controls_off.png"));

			new_on = ImageIO.read(Launcher.class.getResource("/GUI/Buttons/new_on.png"));

			new_off = ImageIO.read(Launcher.class.getResource("/GUI/Buttons/new_off.png"));

			continue_on = ImageIO.read(Launcher.class.getResource("/GUI/Buttons/continue_on.png"));

			continue_off = ImageIO.read(Launcher.class.getResource("/GUI/Buttons/continue_off.png"));

			mute_on = ImageIO.read(Launcher.class.getResource("/GUI/Buttons/mute_on.png"));

			mute_off = ImageIO.read(Launcher.class.getResource("/GUI/Buttons/mute_off.png"));

			back_on = ImageIO.read(Launcher.class.getResource("/GUI/Buttons/back_on.png"));

			back_off = ImageIO.read(Launcher.class.getResource("/GUI/Buttons/back_off.png"));

			quit_on = ImageIO.read(Launcher.class.getResource("/GUI/Buttons/quit_on.png"));

			quit_off = ImageIO.read(Launcher.class.getResource("/GUI/Buttons/quit_off.png"));

			story = new LauncherButton(450, STORY, story_off, story_on);
			sandbox = new LauncherButton(500, SANDBOX, sandbox_off, sandbox_on);
			options = new LauncherButton(550, OPTIONS, options_off, options_on);
			help = new LauncherButton(600, HELP, help_off, help_on);
			survival = new LauncherButton(450, SURVIVAL, survival_off, survival_on);
			zombies = new LauncherButton(500, ZOMBIES, zombies_off, zombies_on);
			audio = new LauncherButton(450, AUDIO, audio_off, audio_on);
			video = new LauncherButton(500, VIDEO, video_off, video_on);
			controls = new LauncherButton(550, CONTROLS, controls_off, controls_on);
			newStory = new LauncherButton(450, NEWSTORY, new_off, new_on);
			continueStory = new LauncherButton(500, CONTINUESTORY, continue_off, continue_on);
			mute = new LauncherButton(450, MUTE, mute_off, mute_on);
			back = new LauncherButton(650, BACK, back_off, back_on);
			quit = new LauncherButton(700, QUIT, quit_off, quit_on);

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Called on a separate thread that processes logic
	 */
	public void run() {
		long lastMinute = System.currentTimeMillis();
		long lastTime = System.nanoTime();
		double nsPerTick = 1000000000 / 60.0;
		int frames = 0;
		long lastTimer = System.currentTimeMillis();
		double delta = 0;
		long previousTime = System.currentTimeMillis();

		while (running) {
			if (System.currentTimeMillis() > lastMinute + 1000) {
				lastMinute = System.currentTimeMillis();
			}
			long now = System.nanoTime();
			delta += (now - lastTime) / nsPerTick;
			lastTime = now;

			while (delta >= 1) {
				delta--;
				// changes the direction of the level movement
				int w = LEVEL_WIDTH * 8;
				int h = LEVEL_HEIGHT * 8;
				yOffset = (yOffset + 1) % h;
				if (dir == Direction.SOUTH_EAST) {
					xOffset++;
				} else {
					xOffset--;
				}
				if (xOffset <= 0) {
					dir = Direction.SOUTH_EAST;
				} else if (xOffset >= w) {
					dir = Direction.SOUTH_WEST;
				}
			}
			frames++;
			render();

			if (System.currentTimeMillis() > previousTime + DELAY) {
				previousTime = System.currentTimeMillis();
				
				if (isClicked) {
					swordOffset += 10;
					if (swordOffset > 100) {
						swordOffset = 0;
						isClicked = false;
						doAction();
					}
				}
			}

			if (System.currentTimeMillis() - lastTimer >= 1000) {
				lastTimer += 1000;
				System.out.println("Launcher FPS: " + frames);
				frames = 0;
			}

		}
	}

	/**
	 * Called before the Run() method
	 */
	public void start() {
		running = true;
		new Thread(this, "Menu").start();
	}

	/**
	 * Stops the Run() method
	 */
	public void stop() {
		running = false;
		this.dispose();
	}

	/**
	 * Displays the pixels onto the screen
	 */
	private void render() {
		BufferStrategy bs = getBufferStrategy();
		if (bs == null) {
			createBufferStrategy(3);
			return;
		}

		level.renderTile(screen, xOffset, yOffset);

		for (int y = 0; y < screen.getHeight(); y++) {
			for (int x = 0; x < screen.getWidth(); x++) {
				pixels[x + y * Display.IMAGE_WIDTH] = screen.getPixels()[x + y * screen.getWidth()];
			}

		}
		screen.clear();

		Graphics g = bs.getDrawGraphics();
		g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
		g.drawImage(background, 0, 0, getWidth(), getHeight(), null);

		switch (pageId) {

		case MAINMENU: {

			story.draw(g);
			sandbox.draw(g);
			options.draw(g);
			help.draw(g);
			break;

		}
		case SANDBOXMENU: {

			survival.draw(g);
			zombies.draw(g);
			break;

		}
		case OPTIONSMENU: {

			audio.draw(g);
			video.draw(g);
			controls.draw(g);
			break;

		}
		case STORYMENU: {
			newStory.draw(g);
			continueStory.draw(g);
			break;

		}

		case AUDIOMENU: {
			mute.draw(g);
			break;
		}

		}

		if (pageId != MAINMENU) {
			back.draw(g);
		}

		quit.draw(g);

		g.setColor(color);
		g.setFont(new Font(Game.FONT_NAME, 0, 20));
		g.drawString(VERSION, 5, 20);
		g.drawString(LAST_UPDATED, 5, height - 10);
		g.dispose();
		bs.show();

	}

	/**
	 * Processes button actions
	 */
	private void doAction() {

		int id = selectedButton.getActionId();
		selectedButton = null;

		switch (id) {

		case STORY: {
			this.pageId = STORYMENU;
			return;
		}
		case SANDBOX: {
			this.pageId = SANDBOXMENU;
			return;
		}
		case OPTIONS: {
			this.pageId = OPTIONSMENU;
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
			SoundHandler.background1.stop();
			new Game(GameMode.SURVIVAL, false);
			this.stop();
			return;
		}
		case ZOMBIES: {
			SoundHandler.background1.stop();
			new Game(GameMode.MINI, false);
			this.stop();
			return;
		}
		case BACK: {
			switch (pageId) {
			case SANDBOXMENU:
			case OPTIONSMENU:
			case STORYMENU:
				this.pageId = MAINMENU;
				break;
			case AUDIOMENU:
				this.pageId = OPTIONSMENU;
				break;
			}
			return;
		}
		case AUDIO: {
			this.pageId = AUDIOMENU;
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
			SoundHandler.background1.stop();
			new Game(GameMode.ADVENTURE, false);
			this.stop();
			return;
		}
		case CONTINUESTORY: {
			SoundHandler.background1.stop();
			new Game(GameMode.ADVENTURE, true);
			this.stop();
			return;
		}
		case MUTE: {
			SoundHandler.toggleMute();
			if (!SoundHandler.isMuted()) {
				SoundHandler.background1.stop();
			} else {
				SoundHandler.play(SoundHandler.background1);
			}
			return;
		}
		default: {
			System.out.println("ID IS MISSING");
			return;
		}
		}

	}

	/**
	 * Launcher Button that is used on the launcher screen
	 */
	private class LauncherButton {

		private int x, y;
		private int actionId;
		private BufferedImage imageOff, imageOn;

		public LauncherButton(int yPos, int actionId, BufferedImage imageOff, BufferedImage imageOn) {
			x = Launcher.this.width / 2 - imageOff.getWidth() / 2;
			y = yPos;
			this.imageOff = imageOff;
			this.imageOn = imageOn;
			this.actionId = actionId;
		}

		public int getActionId() {
			return actionId;
		}

		public void draw(Graphics g) {
			if (InputHandler.MouseX > x && InputHandler.MouseX < x + imageOff.getWidth() && InputHandler.MouseY > y
					&& InputHandler.MouseY < y + imageOff.getHeight()) {
				g.drawImage(imageOn, x, y, null);
				if (!isClicked || selectedButton == this) {
					g.drawImage(sword_selector, x - swordStart + swordOffset, y, null);
				}
				if (InputHandler.MouseButton == 1) {
					InputHandler.MouseButton = 0;
					SoundHandler.play(SoundHandler.sheathe);
					isClicked = true;
					selectedButton = this;
				}

			} else {
				g.drawImage(imageOff, x, y, null);
			}
		}
	}

}
