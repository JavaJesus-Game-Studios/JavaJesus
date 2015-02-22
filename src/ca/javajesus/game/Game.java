package ca.javajesus.game;

import java.awt.AlphaComposite;
import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.SplashScreen;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import javax.swing.JFrame;
import javax.swing.JPanel;

import ca.javajesus.game.entities.Player;
import ca.javajesus.game.gfx.Colors;
import ca.javajesus.game.gfx.Screen;
import ca.javajesus.game.gui.InventoryGUI;
import ca.javajesus.game.gui.Launcher;
import ca.javajesus.game.gui.PauseGUI;
import ca.javajesus.game.gui.ScreenGUI;
import ca.javajesus.game.gui.intro.IntroGUI;
import ca.javajesus.level.Level;

public class Game extends Canvas implements Runnable {

	protected static final long serialVersionUID = 1L;

	/** Determines how long the loading screen lasts */
	protected static final int LOAD_SPEED = 10;

	public static final int WIDTH = 300;
	public static final int HEIGHT = WIDTH / 12 * 9;

	/** Scales the size of the screen */
	public static final int SCALE = 3;
	public static final String NAME = "Java Jesus by the Coders of Anarchy";

	/** Entity limit per screen */
	public final static int ENTITY_LIMIT = 1000;
	public boolean running = false; // this is a change

	/** Creates the JFrame */
	protected static JFrame frame;

	/** Creates the tickCount var */
	public int tickCount;

	/** Temporary Solution to limit frames */
	private final int FRAMES_PER_SECOND = 60;
	private final int DELAY = 1000 / FRAMES_PER_SECOND;

	/** Creates the buffered image to be rendered onto the game screen */
	protected BufferedImage image = new BufferedImage(WIDTH, HEIGHT,
			BufferedImage.TYPE_INT_RGB);

	/** Pixel data to be used in the buffered image */
	protected int[] pixels = ((DataBufferInt) image.getRaster().getDataBuffer())
			.getData();

	/** Does something */
	protected int[] colors = new int[6 * 6 * 6];

	/** Creates instance of the screen */
	public Screen screen;

	/** Creates instances of the handlers */
	public InputHandler input;

	/** Creates instance of the player */
	public Player player;

	/** Used for display variables */
	public static PauseGUI pause;
	public static InventoryGUI inventory;
	public static JPanel display;
	public static IntroGUI introScreen;

	public static boolean inGameScreen = false;
	private static int guiID = 0;

	public boolean isLoaded = false;

	/** This starts the game */
	public Game() {
		input = new InputHandler(this);
		init();
		new ChatHandler();
		inventory = new InventoryGUI(player);
		pause = new PauseGUI();
		introScreen = new IntroGUI(this);
		display = new JPanel(new CardLayout());
		display.add(introScreen, "Intro");
		display.add(this, "Main");
		display.add(inventory, "Inventory");
		display.add(pause, "Pause");
		setMinimumSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
		setMaximumSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
		setPreferredSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
		frame = new JFrame(NAME);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new BorderLayout());
		frame.getContentPane().add(display);
		frame.pack();
		frame.requestFocus();
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		frame.toFront();
		frame.repaint();
	}

	public static void displayInventory() {
		guiID = 2;
		CardLayout cl = (CardLayout) display.getLayout();
		cl.show(display, "Inventory");
		inventory.requestFocusInWindow();
		inGameScreen = false;
		inventory.inventory.repaint();
	}

	public static void displayPause() {
		guiID = 3;
		CardLayout cl = (CardLayout) display.getLayout();
		cl.show(display, "Pause");
		pause.requestFocusInWindow();
		inGameScreen = false;
	}

	public static void displayGame() {
		inGameScreen = true;
		guiID = 1;
		CardLayout cl = (CardLayout) display.getLayout();
		cl.show(display, "Main");
		display.getComponent(1).requestFocusInWindow();
	}

	/** Initializes the image on the screen */
	public void init() {
		int index = 0;
		for (int r = 0; r < 6; r++) {
			for (int g = 0; g < 6; g++) {
				for (int b = 0; b < 6; b++) {
					int rr = (r * 255 / 5);
					int gg = (g * 255 / 5);
					int bb = (b * 255 / 5);

					colors[index++] = rr << 16 | gg << 8 | bb;
				}
			}
		}

		screen = new Screen(WIDTH, HEIGHT, this);
		player = new Player(getLevel(), getLevel().spawnPoint.x,
				getLevel().spawnPoint.y, input);
		getLevel().addEntity(player);
		getLevel().init();

	}

	public void updateLevel() {
		int index = 0;
		for (int r = 0; r < 6; r++) {
			for (int g = 0; g < 6; g++) {
				for (int b = 0; b < 6; b++) {
					int rr = (r * 255 / 5);
					int gg = (g * 255 / 5);
					int bb = (b * 255 / 5);

					colors[index++] = rr << 16 | gg << 8 | bb;
				}
			}
		}
	}

	public void redScreen() {
		int index = 0;
		for (int r = 0; r < 6; r++) {
			for (int g = 0; g < 6; g++) {
				for (int b = 0; b < 6; b++) {
					int rr = (r * 255 / 5);
					int gg = (g * 255 / 5);
					int bb = (b * 255 / 5);

					colors[index++] = Colors.blend(rr << 16 | gg << 8 | bb,
							16711680, 0.75);
				}
			}
		}
	}

	/** Starts the game */
	public synchronized void start() {
		running = true;
		new Thread(this, "Game").start();

	}

	/** Stops the game */
	public synchronized void stop() {
		running = false;
	}

	/** Code executed during runtime */
	public void run() {

		long lastTime = System.nanoTime();
		double nsPerTick = 1000000000D / 60D;
		int ticks = 0;
		int frames = 0;
		long lastTimer = System.currentTimeMillis();
		double delta = 0;

		while (running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / nsPerTick;
			lastTime = now;

			while (delta >= 1) {
				ticks++;
				tick();
				delta--;
			}
			frames++;
			render();

			if (System.currentTimeMillis() - lastTimer >= 1000) {
				lastTimer += 1000;
				frame.setTitle(NAME + "  |   " + ticks + " tps, " + frames
						+ " fps");
				frames = 0;
				ticks = 0;
			}

			// Temporary Frame Limiter
			try {
				Thread.sleep(DELAY / 2);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}

	/** Ticks the game */
	public void tick() {
		tickCount++;
		if (inGameScreen) {
			getLevel().tick();
		} else {
			((ScreenGUI) display.getComponent(guiID)).tick();
		}
	}

	/** Returns the instance of the current Level */
	protected Level getLevel() {
		if (player == null) {
			return Level.level1;
		}
		return player.getLevel();

	}

	/** Renders the screen */
	protected void render() {
		BufferStrategy bs = getBufferStrategy();
		if (bs == null) {
			createBufferStrategy(3);
			return;
		}

		int xOffset = (int) player.x - (screen.width / 2);
		int yOffset = (int) player.y - (screen.height / 2);
		if (player.isDriving) {
			xOffset = (int) player.vehicle.x - (screen.width / 2);
			yOffset = (int) player.vehicle.y - (screen.height / 2);
		}

		if (inGameScreen) {
			getLevel().renderTile(screen, xOffset, yOffset);
			getLevel().renderEntities(screen);
		}

		for (int y = 0; y < screen.height; y++) {
			for (int x = 0; x < screen.width; x++) {
				int colorCode = screen.pixels[x + y * screen.width];
				if (colorCode < 255)
					pixels[x + y * WIDTH] = colors[colorCode];
			}

		}
		Graphics g = bs.getDrawGraphics();
		g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
		g.setFont(new Font("Verdana", 0, 20));
		g.setColor(Color.YELLOW);
		g.drawString(player + ": " + (int) player.x + ", " + (int) player.y, 5,
				20);
		ChatHandler.drawMessages(g);
		if (player.isDead) {
			g.setFont(new Font("Verdana", 0, 50));
			g.setColor(Color.BLACK);
			g.drawString("RIP", WIDTH * SCALE / 2 - 50, HEIGHT * SCALE / 2);
			frame.dispose();
			new Launcher(0).startMenu();
			running = false;
			return;
		}
		g.dispose();
		bs.show();
	}

	/** Initializes a load screen */
	public static void loadScreen() {
		final SplashScreen loadingScreen = SplashScreen.getSplashScreen();
		if (loadingScreen == null) {
			System.out.println("Loading Screen is Null");
			return;
		}
		Graphics2D g = loadingScreen.createGraphics();
		if (g == null) {
			System.out.println("Loading Screen Graphis is Null");
			return;
		}

		g.setFont(new Font("Verdana", 0, 50));
		for (int i = 0; i < LOAD_SPEED; i++) {
			renderSplashFrame(g, i);
			loadingScreen.update();
			try {
				Thread.sleep(90);
			} catch (InterruptedException e) {

			}
		}
		loadingScreen.close();

	}

	/** Renders the loading screen */
	private static void renderSplashFrame(Graphics2D g, int frame) {
		final String[] comps = { "Coders", "of", "Anarchy" };
		g.setComposite(AlphaComposite.Clear);
		g.fillRect(0, 0, 1000, 1000);
		g.setPaintMode();
		g.setColor(Color.BLACK);
		g.drawString("Loading " + comps[(frame / 5) % 3] + "...", WIDTH * SCALE
				/ 5, HEIGHT * SCALE / 2);
		g.fillRect(WIDTH * SCALE / 5, HEIGHT * SCALE / 2, 10 * frame, 50);
	}

	/** Main Method Creation */
	public static void main(String[] args) {
		// loadingScreen();
		new Launcher(0).startMenu();

	}

}
