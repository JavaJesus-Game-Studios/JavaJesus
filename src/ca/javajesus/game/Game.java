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

import javax.sound.sampled.Clip;
import javax.swing.JFrame;
import javax.swing.JPanel;

import ca.javajesus.game.entities.Entity;
import ca.javajesus.game.entities.Player;
import ca.javajesus.game.graphics.Screen;
import ca.javajesus.game.gui.Launcher;
import ca.javajesus.game.gui.PauseGUI;
import ca.javajesus.game.gui.ScreenGUI;
import ca.javajesus.game.gui.intro.IntroGUI;
import ca.javajesus.game.gui.inventory.InventoryGUI;
import ca.javajesus.level.Level;
import ca.javajesus.level.LevelList;
import ca.javajesus.saves.GameData;
import ca.javajesus.saves.SaveFile;

public class Game extends Canvas implements Runnable {

	private static final long serialVersionUID = -6951816659288924373L;

	/** Determines how long the loading screen lasts */
	protected static final int LOAD_SPEED = 10;

	public static final int WIDTH = 300;
	public static final int HEIGHT = WIDTH / 12 * 9;

	/** Scales the size of the screen */
	public static final int SCALE = 3;
	public static final String NAME = "Java Jesus by the Coders of Anarchy";

	public static int DELAY = 4;

	public final static int ENTITY_LIMIT = 1000;

	public final static int MOB_LIMIT = 300;

	public boolean running = false;

	public static int hours = 10;
	public static int minutes;

	public static LevelList levels;

	/** Creates the JFrame */
	protected static JFrame frame;

	public static boolean returnToMenu = false;

	/** Creates the tickCount var */
	public int tickCount;

	/** Creates the buffered image to be rendered onto the game screen */
	protected transient BufferedImage image = new BufferedImage(WIDTH, HEIGHT,
			BufferedImage.TYPE_INT_RGB);

	/** Pixel data to be used in the buffered image */
	protected int[] pixels = ((DataBufferInt) image.getRaster().getDataBuffer())
			.getData();

	/** Creates instance of the screen */
	public Screen screen;

	/** Creates instances of the handlers */
	public transient static InputHandler input;

	/** Creates instance of the player */
	public static Player player;

	protected PlayerHUD hud;

	/** Used for display variables */
	public static PauseGUI pause;
	public static InventoryGUI inventory;
	public static JPanel display;
	public static IntroGUI introScreen;

	public static boolean inGameScreen = false;
	private static int guiID = 0;

	public Launcher launcher;

	/** This starts the game */
	public Game(Launcher launcher) {
		boolean temp = Launcher.load;
		this.launcher = launcher;
		input = new InputHandler(this);
		new ChatHandler();
		screen = new Screen(WIDTH, HEIGHT, this);
		init();
		inventory = new InventoryGUI(player);
		hud = new PlayerHUD(player);
		pause = new PauseGUI();
		introScreen = new IntroGUI();
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
		if (temp) {
			displayGame();
		}
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
	public static void init() {

		if (Launcher.load && GameData.load()) {
			player = null;
			player = getLevel().getPlayer();
			player.setInput(input);
			Entity.initSound();
			player.gun.initSound();
			Launcher.load = false;
		} else {
			levels = new LevelList();
			player = new Player(getLevel(), getLevel().spawnPoint.x,
					getLevel().spawnPoint.y, input);
			getLevel().addEntity(player);
			getLevel().init();
		}

		player.getLevel().getBackgroundMusic().loop(Clip.LOOP_CONTINUOUSLY);
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
		long lastMinute = System.currentTimeMillis();
		long lastTime = System.nanoTime();
		double nsPerTick = 1000000000 / 60.0;
		int ticks = 0;
		int frames = 0;
		long lastTimer = System.currentTimeMillis();
		double delta = 0;
		launcher.end();

		while (running) {
			try {
				if (System.currentTimeMillis() > lastMinute + 1000) {
					lastMinute = System.currentTimeMillis();
					minutes++;
					if (minutes > 60) {
						minutes = 0;
						hours++;
						if (hours > 24) {
							hours = 0;
						}
					}
				}
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

				try {
					Thread.sleep(DELAY);
				} catch (Exception e) {

				}

			} catch (Exception e) {
				renderCrashReport(e);
			}
		}

	}

	private void renderCrashReport(Exception e) {

		e.printStackTrace();
		Graphics g = this.getGraphics();
		g.fillRect(0, 0, WIDTH * SCALE, HEIGHT * SCALE);
		g.setFont(new Font("Verdana", 0, 20));
		g.setColor(Color.WHITE);
		g.drawString(e.toString(), 0, 50);
		for (int i = 0; i < e.getStackTrace().length; i++) {
			g.drawString(e.getStackTrace()[i].toString(), 0, 100 + 50 * i);
		}
		stop();
	}

	/** Ticks the game */
	public void tick() {
		tickCount++;
		if (inGameScreen) {
			getLevel().tick();
		} else {
			((ScreenGUI) display.getComponent(guiID)).tick();
		}
		if (hours >= 6 && hours < 10) {
			screen.setShader(0x5C3D99);
		} else if (hours >= 10 && hours < 17) {
			screen.setShader(0);
		} else if (hours >= 17 && hours < 21) {
			screen.setShader(0xB24700);
		} else {
			screen.setShader(0x0A1433);
		}
	}

	/** Returns the instance of the current Level */
	protected static Level getLevel() {
		if (player != null) {
			return player.getLevel();
		}
		if (levels != null && levels.playerLevel != null) {
			return levels.playerLevel;
		}
		return levels.getDefaultLevel();

	}

	/** Renders the screen */
	protected void render() {

		// quad.retrieve(player.returnObjects, player.getBounds());
		BufferStrategy bs = getBufferStrategy();
		if (bs == null) {
			createBufferStrategy(3);
			return;
		}

		int xOffset = player.getX() - (screen.width / 2);
		int yOffset = player.getY() - (screen.height / 2);
		if (player.isDriving) {
			xOffset = player.vehicle.getX() - (screen.width / 2);
			yOffset = player.vehicle.getY() - (screen.height / 2);
		}

		if (inGameScreen) {
			getLevel().renderTile(screen, xOffset, yOffset);
			getLevel().renderEntities(screen);
		}

		for (int y = 0; y < screen.height; y++) {
			for (int x = 0; x < screen.width; x++) {
				pixels[x + y * WIDTH] = screen.pixels[x + y * screen.width];
			}

		}
		screen.clear();

		Graphics g = bs.getDrawGraphics();
		g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
		g.setFont(new Font("Verdana", 0, 20));
		g.setColor(Color.YELLOW);
		g.drawString(
				player + ": " + player.getX() + ", " + player.getY()
						+ " Time: " + hours + ":" + minutes, 5, 20);
		hud.draw(g);
		ChatHandler.drawMessages(g);
		if (player.isDead() || returnToMenu) {
			g.setFont(new Font("Verdana", 0, 50));
			g.setColor(Color.BLACK);
			g.drawString("RIP", WIDTH * SCALE / 2 - 50, HEIGHT * SCALE / 2);
			frame.dispose();
			inGameScreen = false;
			guiID = 0;
			returnToMenu = false;
			new Launcher(0).startMenu();
			stop();
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
