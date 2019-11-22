package javajesus;

import java.awt.AlphaComposite;
import java.awt.Canvas;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RadialGradientPaint;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.util.Random;

import javax.swing.JPanel;

import engine.GameEngine;
import engine.IGameLogic;
import engine.Input;
import engine.Window;
import javajesus.ai.AIManager;
import javajesus.entities.Player;
import javajesus.entities.npcs.NPC;
import javajesus.graphics.Screen;
import javajesus.gui.DialogueGUI;
import javajesus.gui.OverviewGUI;
import javajesus.gui.PauseGUI;
import javajesus.level.RandomCave;
import javajesus.level.tile.Tile;
import javajesus.utility.GameMode;
import javajesus.utility.JJStrings;

/**
 * @author Derek
 *
 * Game Logic for Java Jesus
 */
public class JavaJesus extends Canvas implements IGameLogic {
	
	// serialization
	private static final long serialVersionUID = 1L;

	// Window width Standard 16:9, 1080p HD
	public static final int WINDOW_WIDTH = 1280;

	// Window height
	public static final int WINDOW_HEIGHT = 720;
	
	// Game Screen width (Size of the In-Game Map displayed within the Actual
	// Frame)
	// NOTE: If we want/need to change this to support different resolutions, keep the
	// Aspect ratio the same as WINDOW_WIDTH:WINDOW_HEIGHT, otherwise you will introduce distortions
	public static final int IMAGE_WIDTH = WINDOW_WIDTH/6;

	// Game Screen height (Size of the In-Game Map displayed within the Actual
	// Frame)
	public static final int IMAGE_HEIGHT = WINDOW_HEIGHT/6;
	
	// a magic number that aligns the bottom components of the HUD
	public static final int HUD_OFFSET = 29;
	
	// Font name used in the game
	public final static String FONT_NAME = "Press Start 2P";

	// Maximum amount of entities rendered at once
	public final static int ENTITY_LIMIT = 10000;

	// Maximum amount of mobs rendered at once
	public final static int MOB_LIMIT = 300;

	// Starts the game based on the game mode
	public static GameMode mode;

	// True if special developer numbers should be displayed
	private boolean doDevOverlay;

	// the scaling difficulty over time
	public static float difficulty = 1;

	// Creates the buffered image to be rendered onto the game screen
	private BufferedImage image = new BufferedImage(IMAGE_WIDTH, IMAGE_HEIGHT, BufferedImage.TYPE_INT_RGB);

	// Pixel data to be used in the buffered image
	private int[] pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();

	// Processes the in-game screen
	private static Screen screen;

	// The graphical overlay of the player stats
	private PlayerHUD hud;

	// Instance of the Pause GUI
	private PauseGUI pause;

	// Instance of the Overview GUI
	private OverviewGUI overview;
	
	// instance of the Dialogue GUI
	private DialogueGUI dialogue;

	// Default JPanel container used to hold other GUI panels
	private JPanel display;

	// Instance of the card layout that holds other guis, used to display other
	// GUIs
	private CardLayout cardlayout;

	// guiID holds the value of the current GUI that is displayed */
	private int guiID;

	// Constants that identify the which screen is displayed
	private static final int GAME_DISPLAY = 0,  INVENTORY_DISPLAY = 1, PAUSE_DISPLAY = 2, DIALOGUE_DISPLAY = 3;

	// font used
	private static final Font DISPLAY_FONT = new Font(FONT_NAME, 0, 20);
	private static final Font DEATH_FONT = new Font(FONT_NAME, 0, 50);
	
	// manages AI
	private static AIManager aiManager;

	// the in game player
	private static Player player;
	
	// whether or not to load from a file
	private boolean load;
	
	// whether or not the game loop should run
	private static boolean running;
	
	// used for setting the overlay
	private int overlayRed = 255;
	private float overlayOpacity;
	
	// Displays the raining shader
	private boolean raining = false;
	
	// chance of rain each new time of day
	private static final double CHANCE_OF_RAIN = 0.30;
	
	// the time in ticks
	private static int time;
	
	// the number of ticks in a day
	private static final int LENGTH_OF_FULL_CYCLE = 24000;
	
	// constants for calculating time
	private static final int TIME_LENGTH = LENGTH_OF_FULL_CYCLE / 4;
	private static final float SHADER_TIME_STRENGTH = 0.5f;
	private static final float increment = (1f / TIME_LENGTH) * SHADER_TIME_STRENGTH;
	
	// center point, the player, on the screen in respect to level
	private int xOffset, yOffset;
	
	/**
	 * JavaJesus ctor()
	 * Initializes the gamemode and whether or not to load
	 * 
	 * @param m - the game mode
	 * @param load - whether or not to load
	 */
	public JavaJesus(final GameMode m, boolean load, final Player player) {
		
		// instance data
		mode = m;
		this.load = load;
		JavaJesus.player = player;
		// initialize a new game
		try {
			new GameEngine(JJStrings.NAME, WINDOW_WIDTH, WINDOW_HEIGHT, this).start();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * Initialize initial components
	 * and JavaJesus objects
	 */
	public void init() throws Exception {
		
		screen = new Screen(IMAGE_WIDTH, IMAGE_HEIGHT);
		pause = new PauseGUI();
		overview = new OverviewGUI(player);
		hud = new PlayerHUD(player);
		
		// initialize the display screens
		display = new JPanel(cardlayout = new CardLayout(0, 0));
		display.add(this, "Main");
		display.add(overview, "Inventory");
		display.add(pause, "Pause");
		display.add(dialogue = new DialogueGUI(), "Dialogue");
		
		// manage the dialogue gui with the dialogue handler
		new DialogueHandler(this);
		
		aiManager = new AIManager(player, screen);
		aiManager.start();
		
		running = true;
		
		MessageHandler.initialize();
		
		// do game initializations from save files
		if (load) {
			
			System.err.println("Loading");
			
			// contains the important player data
			//String[] data = (String[]) GameData.load("Player");
			
			// assign the player from the level mob list
			// get player level, assign player, etc.
			
			// set the player info for the game data 
			
			// inputhandler is not saved in file
			overview = new OverviewGUI(player);
			
			// sound is not saved in file
			player.initSound(); 
			
			// hud is not saved in save file
			hud = new PlayerHUD(player);
			
			// skip the player selection intro gui screen
			display.add(overview, "Inventory");
			
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
		
		// add input listeners
		window.addListeners(this, Input.KEY, Input.FOCUS);
		window.addListeners(overview, Input.KEY);
		window.addListeners(overview.getView(), Input.KEY);
		window.addListeners(pause, Input.KEY);
		window.addListeners(dialogue, Input.KEY);
		
		// show the game
		displayGame();
	}

	/**
	 * Delegate to the player for input
	 */
	public void input(Window window) {
		
		player.input(window);
		
		// toggle developer mode
		if (window.isKeyPressed(KeyEvent.VK_F3)) {
			doDevOverlay = !doDevOverlay;
			window.toggle(KeyEvent.VK_F3);
		}
		
		// switch to inventory screen
		if (window.isKeyPressed(KeyEvent.VK_I)) {
			window.toggle(KeyEvent.VK_I);
			if (inGame() && !player.isDead()) {
				displayOverview();
				window.disable(KeyEvent.VK_W);
				window.disable(KeyEvent.VK_A);
				window.disable(KeyEvent.VK_S);
				window.disable(KeyEvent.VK_D);
			} else {
				displayGame();
			}

		}

		// switch to pause menu
		if (window.isKeyPressed(KeyEvent.VK_ESCAPE)) {
			window.toggle(KeyEvent.VK_ESCAPE);
			if (inGame()) {
				displayPause();
			} else {
				displayGame();
			}
		}
		
		// input specific to dialogue screen
		if (guiID == DIALOGUE_DISPLAY) {
			
			if (window.isKeyPressed(KeyEvent.VK_UP)) {
				dialogue.up();
				window.toggle(KeyEvent.VK_UP);
			}
			
			if (window.isKeyPressed(KeyEvent.VK_W)) {
				dialogue.up();
				window.toggle(KeyEvent.VK_W);
			}
			
			if (window.isKeyPressed(KeyEvent.VK_DOWN)) {
				dialogue.down();
				window.toggle(KeyEvent.VK_DOWN);
			}
			
			if (window.isKeyPressed(KeyEvent.VK_S)) {
				dialogue.down();
				window.toggle(KeyEvent.VK_S);
			}
			
			if (window.isKeyPressed(KeyEvent.VK_ENTER)) {
				dialogue.doAction();
				window.toggle(KeyEvent.VK_ENTER);
			}
			
		}
		
	}

	/**
	 * Updates per second
	 */
	public void update() {
		
		// set level offsets
		player.getLevel().setOffset(xOffset, yOffset);
		
		// only update in the level
		if (inGame()) {
			
			// update the player
			player.getLevel().tick();
			
			// update the hud
			hud.update();
			
			// update the global offsets
			xOffset = player.getX() - (IMAGE_WIDTH / 2) + 8;
			yOffset = player.getY() - (IMAGE_HEIGHT / 2) + 8;
			if (player.isDriving()) {
				xOffset = player.getVehicle().getX() - (IMAGE_WIDTH / 2);
				yOffset = player.getVehicle().getY() - (IMAGE_HEIGHT / 2);
			}
			
		} else {
			
			// repaint inventory menus
			display.getComponent(guiID).repaint();
		}
		
		// update the time screen
		//updateTime();
	}
	
	
	/**
	 * Updates the time and shading factors of the screen
	 */
	public void updateTime() {
		
		// check for rain every segment
		if (time % TIME_LENGTH == 0) {
			if (Math.random() < CHANCE_OF_RAIN) {
				raining = !raining;
			}
		}
		
		// update time
		time = (time + 1) % LENGTH_OF_FULL_CYCLE;
		
		// normal
		if (time < TIME_LENGTH) {
			
			// and get more opaque
			if (overlayOpacity < SHADER_TIME_STRENGTH) {
				overlayOpacity += increment;
			}

			// dusk
		} else if (time < TIME_LENGTH * 2) {
			
			// screen will get more black
			if (overlayRed > 0) {
				overlayRed--;
			}

			// night
		} else if (time < TIME_LENGTH * 3) {
			
			// screen will get more red
			if (overlayRed < 127) {
				overlayRed++;
			}
			
			// dawn
		} else if (time < TIME_LENGTH * 4) {
			
			// screen will get lighter
			if (overlayOpacity >= increment) {
				overlayOpacity -= increment;
			}
			
		}
	}
	
	/**
	 * Displays graphics on the window
	 */
	public void render(Window window) {
		
		// only render if this screen is being displayed
		if (!inGame()) {
			return;
		}

		// create the graphics buffer
		BufferStrategy bs = getBufferStrategy();
		if (bs == null) {
			createBufferStrategy(3);
			return;
		}

		// render the level and everything on it
		player.getLevel().render(screen);
		
		//aiManager.render(screen);
		
		// render collision boxes
		if (doDevOverlay) {
			player.getLevel().renderCollisionBoxes(screen);
			player.renderSwordCollision(screen);
		}

		// set the pixels of the image in memory from the screen class
		for (int y = 0; y < screen.getHeight(); y++) {
			for (int x = 0; x < screen.getWidth(); x++) {
				pixels[x + y * IMAGE_WIDTH] = screen.getPixels()[x + y * screen.getWidth()];
			}

		}
		screen.clear();

		// draw from the buffer strategy
		Graphics g = bs.getDrawGraphics();
		g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
		
		// if player is in cave, draw cave shader
		if (player.getLevel() instanceof RandomCave) {
			((Graphics2D) g).setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.95f));
			((Graphics2D) g).setPaint(new RadialGradientPaint(WINDOW_WIDTH / 2, WINDOW_HEIGHT / 2, 
					WINDOW_WIDTH / 2, new float[] {0f, 0.85f}, new Color[] {new Color(255, 255, 255, 0), Color.BLACK}));
			g.fillRect(0, 0, WINDOW_WIDTH, WINDOW_HEIGHT);
			
			// back to full opacity
			((Graphics2D) g).setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1));
			
			// draw the time of day shader
		} else if (!raining) {
			/*((Graphics2D) g).setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, overlayOpacity));
			g.setColor(new Color(overlayRed, 0, 0));
			g.fillRect(0, 0, WINDOW_WIDTH, WINDOW_HEIGHT);
			
			// back to full opacity
			((Graphics2D) g).setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1));*/
			
			// draw the raining animation
		} else {
			((Graphics2D) g).setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f));
			g.setColor(Color.BLACK);
			g.fillRect(0, 0, WINDOW_WIDTH, WINDOW_HEIGHT);
			((Graphics2D) g).setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1));
			g.setColor(Color.BLUE);
			for (int i = 0; i < 40; i++) {
				g.drawRect((int) (Math.random() * WINDOW_WIDTH), (int) (Math.random() * WINDOW_HEIGHT), 2, 2);
			}
		}
		
		// draw the debug information
		if (doDevOverlay) {
			g.setFont(DISPLAY_FONT);
			g.setColor(Color.WHITE);
			g.drawString(player.toString(), 0, 20);
			g.drawString(
			        "Rounded: (" + Tile.snapToCorner(player.getX()) + ", " + Tile.snapToCorner(player.getY()) + ")", 0,
			        40);
		}
		
		// draw additional displays on the screen
		//hud.draw(g);
		MessageHandler.drawWindow(g);
		
		// draw death screen if player died
		if (player.isDead()) {
			g.setFont(DEATH_FONT);
			g.setColor(Color.RED);
			FontMetrics fm = g.getFontMetrics();
			String text = "You Died";
			g.drawString(text, WINDOW_WIDTH / 2 - fm.stringWidth(text) / 2, WINDOW_HEIGHT / 2);
			text = "Press ESC";
			g.drawString(text, WINDOW_WIDTH / 2 - fm.stringWidth(text) / 2, WINDOW_HEIGHT - fm.getHeight());
		}
		
		// swap the buffer to the front
		g.dispose();
		bs.show();
	}
	
	/**
	 * Called when the game loop stops
	 */
	public void onClose() {
		
		display = null;
		guiID = GAME_DISPLAY;
		
		// initialize a launcher
		try {
			new GameEngine(JJStrings.NAME, JavaJesus.WINDOW_WIDTH, JavaJesus.WINDOW_HEIGHT, new Launcher()).start();
		} catch (Exception e) {
			e.printStackTrace();
		}
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
	 * Displays the Overview GUI on the screen
	 */
	private void displayOverview() {
		guiID = INVENTORY_DISPLAY;
		
		// sort the player inventory first
		player.getInventory().sortItemsByID();
		
		// display the inventory
		cardlayout.show(display, "Inventory");
		overview.requestFocusInWindow();
	}

	/**
	 * Displays the Pause GUI on the screen
	 */
	private void displayPause() {
		guiID = PAUSE_DISPLAY;
		cardlayout.show(display, "Pause");
		pause.requestFocusInWindow();
	}

	/**
	 * Displays the main game on the screen
	 */
	public void displayGame() {
		guiID = GAME_DISPLAY;
		cardlayout.show(display, "Main");
		display.getComponent(GAME_DISPLAY).requestFocusInWindow();
	}
	
	/**
	 * Displays the dialogue screen
	 */
	public void displayDialogue(NPC character) {
		
		// update the dialogue gui first
		dialogue.update(character, player);
		
		// now display it
		guiID = DIALOGUE_DISPLAY;
		cardlayout.show(display, "Dialogue");
		display.getComponent(DIALOGUE_DISPLAY).requestFocusInWindow();
	}

	/**
	 * Sends a crash report to the main screen
	 * 
	 * @param e - the exception that was created
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
	
	/**
	 * Stops the game loop
	 */
	public static void stop() {
		aiManager.stop();
		running = false;
	}

	@Override
	public boolean running() {
		return running;
	}
	
	/**
	 * @return whether or not javajesus is running
	 */
	public static boolean isRunning() {
		return running;
	}
	
	/**
	 * @return whether or not the game display is shown
	 */
	private boolean inGame() {
		return guiID == GAME_DISPLAY;
	}
	
	public static Player getPlayer() {
		return player;
	}

}
