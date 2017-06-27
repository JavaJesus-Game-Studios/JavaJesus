package javajesus;

import java.awt.Canvas;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.util.Random;

import javax.sound.sampled.Clip;
import javax.swing.JPanel;

import engine.IGameLogic;
import engine.Window;
import javajesus.entities.Player;
import javajesus.graphics.Screen;
import javajesus.gui.PauseGUI;
import javajesus.gui.ScreenGUI;
import javajesus.gui.intro.IntroGUI;
import javajesus.gui.overview.OverviewGUI;
import javajesus.level.Level;
import javajesus.level.sandbox.SandboxSurvivalMap1;
import javajesus.level.story.LordHillsboroughsDomain;
import javajesus.save.GameData;

/**
 * @author Derek
 *
 * Game Logic for Java Jesus
 */
public class JavaJesus extends Canvas implements IGameLogic {
	
	// Window width
	public static final int WINDOW_WIDTH = 720 * 12 / 9;

	// Window height
	public static final int WINDOW_HEIGHT = 720;
	
	// Game Screen width (Size of the In-Game Map displayed within the Actual
	// Frame)
	public static final int IMAGE_WIDTH = 225 * 12 / 9;

	// Game Screen height (Size of the In-Game Map displayed within the Actual
	// Frame)
	public static final int IMAGE_HEIGHT = 225;
	
	// Font name used in the game
	public final static String FONT_NAME = "Impact";

	// Maximum amount of entities rendered at once
	public final static int ENTITY_LIMIT = 1000;

	// Maximum amount of mobs rendered at once
	public final static int MOB_LIMIT = 300;

	// Determines if the game is running or not
	private static boolean running;

	// Starts the game based on the game mode
	public static GameMode mode;

	// True if special developer numbers should be displayed
	private static boolean displayDevOverlay;

	// the gamescore in survival mode
	public static int score;

	public static Integer fps;
	
	// name of the main player
	public static String PLAYER_NAME;
	
	// Creates the buffered image to be rendered onto the game screen
	private transient BufferedImage image = new BufferedImage(IMAGE_WIDTH, IMAGE_HEIGHT, BufferedImage.TYPE_INT_RGB);

	// Pixel data to be used in the buffered image
	private int[] pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();

	// Processes the in-game screen
	private static Screen screen;

	// The graphical overlay of the player stats
	private PlayerHUD hud;

	// Instance of the Pause GUI
	private static PauseGUI pause;

	// Instance of the Inventory GUI
	private static OverviewGUI inventory;

	// Default JPanel container used to hold other GUI panels
	private static JPanel display;

	// Instance of the Set-Up introduction screen GUI
	private static IntroGUI introScreen;

	// Instance of the card layout that holds other guis, used to display other
	// GUIs
	private static CardLayout cardlayout;

	// guiID holds the value of the current GUI that is displayed */
	private static int guiID;

	// Constant that identifies the pause display
	private static final int PAUSE_DISPLAY = 3;

	// Constant that identifies the game display
	private static final int GAME_DISPLAY = 1;

	// Constant that identifies the inventory display
	private static final int INVENTORY_DISPLAY = 2;

	// inGameScreen reveals if the game gui is being displayed
	public static boolean inGameScreen;
	
	// font used
	private static final Font DISPLAY_FONT = new Font("Verdana", 0, 20);

	// the in game player
	private Player player;
	
	// whether or not to load from a file
	private boolean load;
	
	/**
	 * @see Distinguishes the three different game modes available
	 */
	public enum GameMode {
		ADVENTURE, FIXED, RANDOM
	}
	
	public JavaJesus(GameMode m, boolean load) {
		
		mode = m;
		this.load = load;
		
	}
	
	/**
	 * Initialize initial components
	 * and JavaJesus objects
	 */
	public void init() throws Exception {
		
		screen = new Screen(IMAGE_WIDTH, IMAGE_HEIGHT);
		pause = new PauseGUI();
		
		display = new JPanel(new CardLayout());
		cardlayout = (CardLayout) display.getLayout();
		
		introScreen = new IntroGUI(this);
		display.add(introScreen, "Intro");
		display.add(this, "Main");
		
		ChatHandler.initialize();

		// do game initializtions from save files
		if (load) {
			
			System.err.println("Loading");
			
			// load all story levels
			GameData.loadLevels();
			
			// contains the important player data
			String[] data = (String[]) GameData.load("Player");
			
			// assign the player name
			PLAYER_NAME = data[GameData.NAME];
			
			// assign the player from the level mob list
			player = Level.getLevel(data[GameData.LEVEL]).getPlayer(data[GameData.NAME]);
			
			// set the player info for the game data 
			GameData.setPlayer(player);
			
			// inputhandler is not saved in file
			inventory = new OverviewGUI(player);
			
			// sound is not saved in file
			player.initSound(); 
			
			// hud is not saved in save file
			hud = new PlayerHUD(player);
			
			// skip the player selection intro gui screen
			display.add(inventory, "Inventory");
			display.add(pause, "Pause");
			
			displayGame();
			
		} else {
			System.err.println("Not loading game.");
		}
		
	}
	
	/**
	 * Make any additional modifications to the window
	 */
	public void modifyWindow(Window window) {
		window.getContentPane().add(display);
		window.pack();
		window.setLocationRelativeTo(null);
		window.requestFocus();
		
		addKeyListener(window.getInput());
		addFocusListener(window.getInput());
	}

	/**
	 * Delegate to the player for input
	 */
	public void input(Window window) {
		
		if (player != null) {
			player.input(window);
		}
	}

	/**
	 * Updates per second
	 */
	public void update() {

		if (inGameScreen) {
			player.getLevel().tick();
		} else {
			((ScreenGUI) display.getComponent(guiID)).tick();
		}

	}
	
	public static void playerDied() {
		
	}

	/**
	 * Displays graphics on the window
	 */
	public void render(Window window) {

		if (player == null)
			return;
		BufferStrategy bs = getBufferStrategy();
		if (bs == null) {
			createBufferStrategy(3);
			return;
		}

		int xOffset = player.getX() - (screen.getWidth() / 2);
		int yOffset = player.getY() - (screen.getHeight() / 2);
		if (player.isDriving()) {
			xOffset = player.getVehicle().getX() - (screen.getWidth() / 2);
			yOffset = player.getVehicle().getY() - (screen.getHeight() / 2);
		}

		if (inGameScreen) {
			player.getLevel().renderTile(screen, xOffset, yOffset);
			player.getLevel().renderEntities(screen, player);
		}

		for (int y = 0; y < screen.getHeight(); y++) {
			for (int x = 0; x < screen.getWidth(); x++) {
				pixels[x + y * IMAGE_WIDTH] = screen.getPixels()[x + y * screen.getWidth()];
			}

		}
		screen.clear();

		Graphics g = bs.getDrawGraphics();
		g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
		g.setFont(DISPLAY_FONT);
		g.setColor(Color.YELLOW);
		if (getDisplayDevScreen()) {
			g.drawString(player + ": " + player.getX() + ", " + player.getY(), 5, 20);
		}
		if (hud != null)
			hud.draw(g);
		g.setFont(DISPLAY_FONT);
		ChatHandler.drawWindow(g);
		g.dispose();
		bs.show();
	}
	
	public static Screen getScreen() {
		return screen;
	}
	
	/**
	 * Called when the game loop stops
	 */
	public void onClose() {
		display = null;
		new Launcher().start();
	}
	
	/**
	 * @return Returns true if the display should render the dev overlay
	 */
	public static boolean getDisplayDevScreen() {
		return displayDevOverlay;
	}

	public static void setDisplayDevScreen(boolean doesDisplay) {
		displayDevOverlay = doesDisplay;
	}
	
	/**
	 * utility method for getting random offset values
	 * @param distance value between 0 and distance
	 * @return random value between 0 and distance
	 */
	public static int getRandomOffset(int distance) {
		return (new Random()).nextInt(distance);
	}
	
	/**
	 * Called when the IntroGUI finishes selecting player preferences
	 */
	public synchronized void createPlayer(String name, int shirtColor, int skinColor) {

		Level level;
		
		switch (mode) {
		case RANDOM:
			level = Launcher.level;
			player = new Player(level, level.getSpawnPoint().x, level.getSpawnPoint().y);
			break;
		case FIXED:
			level = new SandboxSurvivalMap1();
			player = new Player(level, level.getSpawnPoint().x, level.getSpawnPoint().y);
			break;
		default:
			Level.createStoryLevels();
			level = LordHillsboroughsDomain.level;
			//level = LevelTester.level;
			player = new Player(level, level.getSpawnPoint().x, level.getSpawnPoint().y);
			
		}

		GameData.setPlayer(player);
		PLAYER_NAME = player.getName();
		
		level.reset();
		level.add(player);
		level.getBackgroundMusic().loop(Clip.LOOP_CONTINUOUSLY);

		player.setShirtColor(shirtColor);
		player.setSkinColor(skinColor);

		inventory = new OverviewGUI(player);

		hud = new PlayerHUD(player);
		
		display.add(inventory, "Inventory");
		display.add(pause, "Pause");
		
		displayGame();

	}
	
	/**
	 * Displays the Inventory GUI on the screen
	 */
	public static void displayInventory() {
		inGameScreen = false;
		guiID = INVENTORY_DISPLAY;
		cardlayout.show(display, "Inventory");
		inventory.requestFocusInWindow();
		inventory.getInventory().repaint();
	}

	/**
	 * Displays the Pause GUI on the screen
	 */
	public static void displayPause() {
		inGameScreen = false;
		guiID = PAUSE_DISPLAY;
		cardlayout.show(display, "Pause");
		pause.requestFocusInWindow();
	}

	/**
	 * Displays the main game on the screen
	 */
	public static void displayGame() {
		inGameScreen = true;
		guiID = GAME_DISPLAY;
		cardlayout.show(display, "Main");
		display.getComponent(GAME_DISPLAY).requestFocusInWindow();
	}

	/**
	 * Sends a crash report to the main screen
	 * 
	 * @param e
	 *            : the exception that was created
	 */
	public void sendCrashReportToScreen(Exception e) {

		Graphics g = getGraphics();
		g.fillRect(0, 0, WINDOW_WIDTH, WINDOW_HEIGHT);
		g.setFont(new Font(FONT_NAME, 0, 20));
		g.setColor(Color.WHITE);
		g.drawString(e.toString(), 0, 50);
		for (int i = 0; i < e.getStackTrace().length; i++) {
			g.drawString(e.getStackTrace()[i].toString(), 0, 100 + 50 * i);
		}

	}

}
