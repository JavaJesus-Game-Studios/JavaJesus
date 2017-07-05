package javajesus;

import java.awt.Canvas;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import javax.imageio.ImageIO;
import javax.sound.sampled.Clip;
import javax.swing.JPanel;

import engine.IGameLogic;
import engine.Input;
import engine.Window;
import javajesus.entities.Player;
import javajesus.graphics.Screen;
import javajesus.gui.PlayerCreationGUI;
import javajesus.items.Item;
import javajesus.level.Level;
import javajesus.level.RandomLevel;
import javajesus.level.sandbox.SandboxSurvivalMap1;
import javajesus.level.story.LordHillsboroughsDomain;
import javajesus.save.GameData;
import javajesus.save.SaveFile;
import javajesus.utility.Direction;
import javajesus.utility.GameMode;

/**
 * This is the JavaJesus Launcher window
 * 
 * It uses the JavaJesus external engine to
 * instantiate a game of a specified type
 */
public class Launcher extends Canvas implements IGameLogic {

	// Used for serialization
	private static final long serialVersionUID = 1L;

	// Version of the game
	private final String VERSION = "Alpha 0.8.1";

	// Last known update
	private final String LAST_UPDATED = "Last Updated 7/5/2017";
	
	// launcher font
	private static final Font LAUNCHER_FONT = new Font(JavaJesus.FONT_NAME, 0, 15);

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

	// the randomly generated background level
	public static final Level level = new RandomLevel(500, 500, new Point(10, 10), true);;

	// the image of the level
	private static final BufferedImage image =
			new BufferedImage(JavaJesus.IMAGE_WIDTH, JavaJesus.IMAGE_HEIGHT, BufferedImage.TYPE_INT_RGB);

	// Pixel data to be used in the buffered image
	private int[] pixels;

	// Processes the level pixels
	private Screen screen = new Screen(JavaJesus.IMAGE_WIDTH, JavaJesus.IMAGE_HEIGHT);
	
	// level width and height
	public static final int LEVEL_WIDTH = 200, LEVEL_HEIGHT = 200;
	
	// offset of the level
	private int xOffset, yOffset;
	
	// mouse coordinates
	private int mouseX, mouseY, mouseButton;
	
	// IDs of the page screens
	private static final int MAINMENU = 0, SANDBOXMENU = 1, OPTIONSMENU = 2, STORYMENU = 3, AUDIOMENU = 4;

	// Ids of the buttons
	private static final int STORY = 0, SANDBOX = 1, OPTIONS = 2, HELP = 3, QUIT = 4, FIXED = 5, RANDOM = 6,
			BACK = 7, AUDIO = 8, VIDEO = 9, CONTROLS = 10, MUTE = 13, 
			SLOT_1 = 14, SLOT_2 = 15, SLOT_3 = 16;

	// Buttons on the launcher
	private LauncherButton story, sandbox, options, credits, fixed, random, audio, video, controls, mute,
	back, quit, slot_1, slot_2, slot_3;

	// buffered images that are displayed on the screen
	private BufferedImage background, sword_selector;
	
	// direction the level moves
	private Direction dir = Direction.SOUTH_EAST;
	
	// whether or not the loop should continue running
	private boolean running = true;
	
	// layout for the launcher
	private CardLayout cardLayout;
	
	// JPanel of the player creation GUI
	private PlayerCreationGUI playerCreationGUI;
	
	// the display of this launcher
	private JPanel display;

	/**
	 * Constructor that creates the JFrame
	 */
	public Launcher() {
		
		// map pixels
		pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();
		SoundHandler.playLoop(SoundHandler.background1);
		
		// initialize JSwing stuff
		display = new JPanel(cardLayout = new CardLayout(0, 0));
		display.add(this, "Main");
		display.add(playerCreationGUI = new PlayerCreationGUI(), "Creation");
		
		// show the main display
		cardLayout.show(display, "Main");
		
	}

	/**
	 * Initializes instance variables and loads button images
	 */
	public void init() throws Exception {
		
		BufferedImage story_on, story_off, sandbox_on, sandbox_off, options_on, options_off, credits_on, credits_off,
		        fixed_on, fixed_off, random_on, random_off, audio_on, audio_off, video_on, video_off,
		        controls_on, controls_off, mute_on, mute_off, back_on,
		        back_off, quit_on, quit_off, slot_off;
		
		background = ImageIO.read(Launcher.class.getResource("/GUI/GUI_Menus/Main_Menu.png"));

		sword_selector = ImageIO.read(Launcher.class.getResource("/GUI/Buttons/sword_selector.png"));

		story_on = ImageIO.read(Launcher.class.getResource("/GUI/Buttons/story_on.png"));

		story_off = ImageIO.read(Launcher.class.getResource("/GUI/Buttons/story_off.png"));

		sandbox_on = ImageIO.read(Launcher.class.getResource("/GUI/Buttons/sandbox_on.png"));

		sandbox_off = ImageIO.read(Launcher.class.getResource("/GUI/Buttons/sandbox_off.png"));

		options_on = ImageIO.read(Launcher.class.getResource("/GUI/Buttons/options_on.png"));

		options_off = ImageIO.read(Launcher.class.getResource("/GUI/Buttons/options_off.png"));

		credits_on = ImageIO.read(Launcher.class.getResource("/GUI/Buttons/credits_on.png"));

		credits_off = ImageIO.read(Launcher.class.getResource("/GUI/Buttons/credits_off.png"));

		fixed_on = ImageIO.read(Launcher.class.getResource("/GUI/Buttons/fixed_on.png"));

		fixed_off = ImageIO.read(Launcher.class.getResource("/GUI/Buttons/fixed_off.png"));

		random_on = ImageIO.read(Launcher.class.getResource("/GUI/Buttons/random_on.png"));

		random_off = ImageIO.read(Launcher.class.getResource("/GUI/Buttons/random_off.png"));

		audio_on = ImageIO.read(Launcher.class.getResource("/GUI/Buttons/audio_on.png"));

		audio_off = ImageIO.read(Launcher.class.getResource("/GUI/Buttons/audio_off.png"));

		video_on = ImageIO.read(Launcher.class.getResource("/GUI/Buttons/video_on.png"));

		video_off = ImageIO.read(Launcher.class.getResource("/GUI/Buttons/video_off.png"));

		controls_on = ImageIO.read(Launcher.class.getResource("/GUI/Buttons/controls_on.png"));

		controls_off = ImageIO.read(Launcher.class.getResource("/GUI/Buttons/controls_off.png"));

		mute_on = ImageIO.read(Launcher.class.getResource("/GUI/Buttons/mute_on.png"));

		mute_off = ImageIO.read(Launcher.class.getResource("/GUI/Buttons/mute_off.png"));

		back_on = ImageIO.read(Launcher.class.getResource("/GUI/Buttons/back_on.png"));

		back_off = ImageIO.read(Launcher.class.getResource("/GUI/Buttons/back_off.png"));

		quit_on = ImageIO.read(Launcher.class.getResource("/GUI/Buttons/quit_on.png"));

		quit_off = ImageIO.read(Launcher.class.getResource("/GUI/Buttons/quit_off.png"));
		
		slot_off = ImageIO.read(Launcher.class.getResource("/GUI/slot.png"));

		story = new LauncherButton(450, STORY, story_off, story_on);
		sandbox = new LauncherButton(500, SANDBOX, sandbox_off, sandbox_on);
		options = new LauncherButton(550, OPTIONS, options_off, options_on);
		credits = new LauncherButton(600, HELP, credits_off, credits_on);
		fixed = new LauncherButton(450, FIXED, fixed_off, fixed_on);
		random = new LauncherButton(500, RANDOM, random_off, random_on);
		audio = new LauncherButton(450, AUDIO, audio_off, audio_on);
		video = new LauncherButton(500, VIDEO, video_off, video_on);
		controls = new LauncherButton(550, CONTROLS, controls_off, controls_on);
		slot_1 = new LauncherButton(450, SLOT_1, slot_off, slot_off);
		slot_2 = new LauncherButton(500, SLOT_2, slot_off, slot_off);
		slot_3 = new LauncherButton(550, SLOT_3, slot_off, slot_off);
		mute = new LauncherButton(450, MUTE, mute_off, mute_on);
		back = new LauncherButton(650, BACK, back_off, back_on);
		quit = new LauncherButton(650, QUIT, quit_off, quit_on);

	}
	
	/**
	 * Additional modifications to the window
	 */
	public void modifyWindow(Window window) {
		
		window.getContentPane().add(display);
		window.pack();
		window.setLocationRelativeTo(null);
		
		// add listeners
		window.addListeners(this, Input.MOUSE, Input.MOUSE_MOTION);
		
	}

	/**
	 * Displays the pixels onto the screen
	 */
	public void render(Window window) {
		BufferStrategy bs = getBufferStrategy();
		if (bs == null) {
			createBufferStrategy(3);
			return;
		}

		level.renderTile(screen, xOffset, yOffset);

		for (int y = 0; y < screen.getHeight(); y++) {
			for (int x = 0; x < screen.getWidth(); x++) {
				pixels[x + y * JavaJesus.IMAGE_WIDTH] = screen.getPixels()[x + y * screen.getWidth()];
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
			credits.draw(g);
			quit.draw(g);
			break;

		}
		case SANDBOXMENU: {

			fixed.draw(g);
			random.draw(g);
			break;

		}
		case OPTIONSMENU: {

			audio.draw(g);
			video.draw(g);
			controls.draw(g);
			break;

		}
		case STORYMENU: {
			slot_1.draw(g);
			slot_2.draw(g);
			slot_3.draw(g);
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

		g.setColor(Color.WHITE);
		g.setFont(LAUNCHER_FONT);
		g.drawString(VERSION, 5, 20);
		g.drawString(LAST_UPDATED, 5, JavaJesus.WINDOW_HEIGHT - 30);
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
			running = false;
			return;
		}
		case FIXED: {
			
			// load if it exists
			if (SaveFile.exists(1)) {
				
				// stop the launcher
				running = false;
				
				// create the player
				Player player = createPlayer(1, GameMode.FIXED);
				
				// start the game
				new JavaJesus(GameMode.FIXED, false, player);
				
				// create a player creation file
			} else {
				
				// show the player creation display
				playerCreationGUI.setSlot(1);
				cardLayout.show(display, "Creation");
				
			}
			return;
		}
		case RANDOM: {
			
			// load if it exists
			if (SaveFile.exists(1)) {
				
				// stop the launcher
				running = false;
				
				// create the player
				Player player = createPlayer(1, GameMode.RANDOM);
				
				// start the game
				new JavaJesus(GameMode.RANDOM, false, player);
				
				// create a player creation file
			} else {
				
				// show the player creation display
				playerCreationGUI.setSlot(1);
				cardLayout.show(display, "Creation");
				
			}
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
		case SLOT_1:
		case SLOT_2:
		case SLOT_3: {
			
			// slot file selected
			int numSlot = 0;
			if (id == SLOT_1) {
				numSlot = 1;
			} else if (id == SLOT_2) {
				numSlot = 2;
			} else {
				numSlot = 3;
			}
			
			// load if it exists
			if (SaveFile.exists(numSlot)) {
				
				// stop the launcher
				running = false;
				
				// create the player
				Player player = createPlayer(numSlot, GameMode.ADVENTURE);
				
				// start the game
				new JavaJesus(GameMode.ADVENTURE, false, player);
				
				// create a player creation file
			} else {
				
				// show the player creation display
				playerCreationGUI.setSlot(numSlot);
				cardLayout.show(display, "Creation");
				
			}
			
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
	 * Creates the player based on the gamemode
	 * 
	 * @return the player
	 */
	private Player createPlayer(int slot, GameMode mode) {
		
		// load player creation data
		Object[] data = SaveFile.load(slot);
		int skin = (int) data[0];
		int shirt = (int) data[1];
		Item weapon = (Item) data[2];
		String name = (String) data[3];
		
		// level to set the player
		Level level = getLevel(mode);
		
		// Player to create
		Player player = new Player(name, level, level.getSpawnPoint().x, level.getSpawnPoint().y);

		GameData.setPlayer(player);
		
		// TODO
		JavaJesus.PLAYER_NAME = player.getName();
		
		level.reset();
		level.add(player);
		level.getBackgroundMusic().loop(Clip.LOOP_CONTINUOUSLY);

		player.setShirtColor(shirt);
		player.setSkinColor(skin);
		player.getInventory().add(weapon);
		
		return player;
	}

	/**
	 * Gets the level to initialize
	 * 
	 * @param mode - gamemode
	 * @return - the level
	 */
	private Level getLevel(GameMode mode) {
		
		switch (mode) {
		case RANDOM:
			return Launcher.level;
		case FIXED:
			return new SandboxSurvivalMap1();
		default:
			Level.createStoryLevels();
			return LordHillsboroughsDomain.level;
			//level = LevelTester.level;
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
			x = JavaJesus.WINDOW_WIDTH / 2 - imageOff.getWidth() / 2;
			y = yPos;
			this.imageOff = imageOff;
			this.imageOn = imageOn;
			this.actionId = actionId;
		}

		public int getActionId() {
			return actionId;
		}

		public void draw(Graphics g) {
			if (mouseX > x && mouseX < x + imageOff.getWidth() && mouseY > y
					&& mouseY < y + imageOff.getHeight()) {
				g.drawImage(imageOn, x, y, null);
				if (!isClicked || selectedButton == this) {
					g.drawImage(sword_selector, x - swordStart + swordOffset, y, null);
				}
				if (mouseButton == 1) {
					mouseButton = 0;
					SoundHandler.play(SoundHandler.sheathe);
					isClicked = true;
					selectedButton = this;
				}

			} else {
				g.drawImage(imageOff, x, y, null);
			}
		}
	}

	/**
	 * Input checking for the window
	 */
	public void input(Window window) {
		mouseX = window.getMouseX();
		mouseY = window.getMouseY();
		mouseButton = window.getMouseButton();
	}

	@Override
	public void update() {
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
		
		if (isClicked) {
			swordOffset += 10;
			if (swordOffset > 100) {
				swordOffset = 0;
				isClicked = false;
				doAction();
			}
		}
		
	}

	@Override
	public void onClose() {
		
	}

	@Override
	public boolean running() {
		return running;
	}

}
