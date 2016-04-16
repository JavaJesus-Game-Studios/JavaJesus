package game;

import game.graphics.Screen;
import game.gui.PauseGUI;
import game.gui.ScreenGUI;
import game.gui.intro.IntroGUI;
import game.gui.overview.OverviewGUI;

import java.awt.Canvas;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import javax.swing.JFrame;
import javax.swing.JPanel;

/*
 * The canvas that puts the images together
 */
public class Display extends Canvas {

	private static final long serialVersionUID = -3366355134015679332L;

	public static final String NAME = "Java Jesus by the Coders of Anarchy";

	// Window width (Actual Size)
	public static final int FRAME_WIDTH = 720 * 12 / 9;

	// Window height (Actual Size)
	public static final int FRAME_HEIGHT = 720;

	// Game Screen width (Size of the In-Game Map displayed within the Actual
	// Frame)
	public static final int IMAGE_WIDTH = 225 * 12 / 9;

	// Game Screen height (Size of the In-Game Map displayed within the Actual
	// Frame)
	public static final int IMAGE_HEIGHT = 225;

	// Contains the canvas
	private static JFrame frame;

	// Creates the buffered image to be rendered onto the game screen
	private transient BufferedImage image = new BufferedImage(IMAGE_WIDTH,
			IMAGE_HEIGHT, BufferedImage.TYPE_INT_RGB);

	// Pixel data to be used in the buffered image
	private int[] pixels = ((DataBufferInt) image.getRaster().getDataBuffer())
			.getData();

	// Processes the in-game screen
	private Screen screen;

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
	
	/**
	 * Initializes the Screen processor and other GUIs
	 * Defaults the first screen to the introduction screen
	 */
	public Display() {
		screen = new Screen(IMAGE_WIDTH, IMAGE_HEIGHT);
		inventory = new OverviewGUI();
		hud = new PlayerHUD(Game.player);
		pause = new PauseGUI();
		introScreen = new IntroGUI();

		display = new JPanel(new CardLayout());
		display.add(introScreen, "Intro");
		display.add(this, "Main");
		display.add(inventory, "Inventory");
		display.add(pause, "Pause");

		cardlayout = (CardLayout) display.getLayout();

		setPreferredSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));

		frame = new JFrame(NAME);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.getContentPane().add(display);
		frame.pack();
		frame.requestFocus();
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setAlwaysOnTop(true);

		ChatHandler.initialize();
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
		g.fillRect(0, 0, FRAME_WIDTH, FRAME_HEIGHT);
		g.setFont(new Font(Game.FONT_NAME, 0, 20));
		g.setColor(Color.WHITE);
		g.drawString(e.toString(), 0, 50);
		for (int i = 0; i < e.getStackTrace().length; i++) {
			g.drawString(e.getStackTrace()[i].toString(), 0, 100 + 50 * i);
		}

	}

	/**
	 * Game logic that is performed 60 times a second
	 */
	public void tick() {
		frame.setTitle(NAME + " | FPS: " + Game.fps);
		int hours = Game.getHour();
		if (hours >= 6 && hours < 10) {
			screen.setShader(0x5C3D99);
		} else if (hours >= 10 && hours < 17) {
			screen.setShader(0);
		} else if (hours >= 17 && hours < 21) {
			screen.setShader(0xB24700);
		} else {
			screen.setShader(0x0A1433);
		}

		if (inGameScreen) {
			Game.getLevel().tick();
		} else {
			((ScreenGUI) display.getComponent(guiID)).tick();
		}
	}

	/**
	 * Process that displays an image on the screen as many times as possible
	 */
	protected void render() {
		BufferStrategy bs = getBufferStrategy();
		if (bs == null) {
			createBufferStrategy(3);
			return;
		}

		int xOffset = Game.player.getX() - (screen.getWidth() / 2);
		int yOffset = Game.player.getY() - (screen.getHeight() / 2);
		if (Game.player.isDriving) {
			xOffset = Game.player.vehicle.getX() - (screen.getWidth() / 2);
			yOffset = Game.player.vehicle.getY() - (screen.getHeight() / 2);
		}

		if (inGameScreen) {
			Game.getLevel().renderTile(screen, xOffset, yOffset);
			Game.getLevel().renderEntities(screen);
		}

		for (int y = 0; y < screen.getHeight(); y++) {
			for (int x = 0; x < screen.getWidth(); x++) {
				pixels[x + y * IMAGE_WIDTH] = screen.getPixels()[x + y
						* screen.getWidth()];
			}

		}
		screen.clear();

		Graphics g = bs.getDrawGraphics();
		g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
		g.setFont(new Font("Verdana", 0, 20));
		g.setColor(Color.YELLOW);
		if (Game.getDisplayDevScreen()) {
			g.drawString(Game.player + ": " + Game.player.getX() + ", "
					+ Game.player.getY() + " Time: " + Game.getHour() + ":"
					+ Game.getMinutes(), 5, 20);
		}
		hud.draw(g);
		ChatHandler.drawWindow(g);
		g.dispose();
		bs.show();
	}

	/**
	 * Disposes the screen
	 */
	public static void stop() {
		frame.dispose();
	}

}
