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
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JPanel;

import ca.javajesus.saves.*;
import ca.javajesus.game.entities.Player;
import ca.javajesus.game.graphics.Screen;
import ca.javajesus.game.gui.Launcher;
import ca.javajesus.game.gui.PauseGUI;
import ca.javajesus.game.gui.ScreenGUI;
import ca.javajesus.game.gui.intro.IntroGUI;
import ca.javajesus.game.gui.inventory.InventoryGUI;
import ca.javajesus.items.Item;
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

	/** Entity limit per screen, currently doesnt do anything */
	public final static int ENTITY_LIMIT = 1000;

	public final static int MOB_LIMIT = 300;
	public boolean running = false; // this is a change
	
	public static int hours = 10;
	public static int minutes;

	/** Creates the JFrame */
	protected static JFrame frame;

	public static boolean returnToMenu = false;

	/** Creates the tickCount var */
	public int tickCount;

	/** Temporary Solution to limit frames */
	private final int FRAMES_PER_SECOND = 60;
	private final int DELAY = 500 / FRAMES_PER_SECOND;

	/** Creates the buffered image to be rendered onto the game screen */
	protected BufferedImage image = new BufferedImage(WIDTH, HEIGHT,
			BufferedImage.TYPE_INT_RGB);

	/** Pixel data to be used in the buffered image */
	protected int[] pixels = ((DataBufferInt) image.getRaster().getDataBuffer())
			.getData();

	/** Creates instance of the screen */
	public Screen screen;

	/** Creates instances of the handlers */
	public InputHandler input;

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

	public boolean isLoaded = false;

	/** Used for game saves */
	public static FileData saves = new FileData();
	public Launcher launcher;

	/** This starts the game */
	public Game(Launcher launcher) {
		this.launcher = launcher;
		input = new InputHandler(this);
		new ChatHandler();
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

		screen = new Screen(WIDTH, HEIGHT, this);
		String x;
		try {
			x = saves.data("res/Saves/JavaTest.txt");
			if (Launcher.load == true) {
				/*Convert con = new Convert();
				int xPos = Integer.parseInt(x.substring(0, x.indexOf("a")));
				int yPos = Integer.parseInt(x.substring(x.indexOf("a") + 1,
						x.indexOf("b")));
				player = new Player(getLevel(), xPos, yPos, input);
				player.setHealth(Integer.parseInt(x.substring(
						x.indexOf("b") + 1, x.indexOf("c"))));
				player.stamina = Double.parseDouble(x.substring(
						x.indexOf("c") + 1, x.indexOf("d")));
				player.score = Integer.parseInt(x.substring(x.indexOf("d") + 1,
						x.indexOf("e")));
				player.setName(con.binaryToString(x.substring(x.indexOf("e") +
				        1, x.indexOf("f"))));*/
			    
			 // Read from disk using FileInputStream
				
			    player = new Player(Level.loadData(), Level.loadData().spawnPoint.x,
                        Level.loadData().spawnPoint.y, input);

			} else {
				player = new Player(getLevel(), getLevel().spawnPoint.x,
						getLevel().spawnPoint.y, input);
			}
			getLevel().addEntity(player);
			getLevel().init();

		} catch (IOException e) {
			e.printStackTrace();
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

				// Temporary Frame Limiter
				try {
					Thread.sleep(DELAY);
				} catch (InterruptedException e) {
					e.printStackTrace();
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
		running = false;
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
		g.drawString(player + ": " + player.getX() + ", " + player.getY() + " Time: " + hours +":" + minutes, 5,
				20);
		hud.draw(g);
		ChatHandler.drawMessages(g);
		if (player.isDead() || returnToMenu) {
			g.setFont(new Font("Verdana", 0, 50));
			g.setColor(Color.BLACK);
			g.drawString("RIP", WIDTH * SCALE / 2 - 50, HEIGHT * SCALE / 2);
			frame.dispose();
			Level.level1.reset();
			inGameScreen = false;
			guiID = 0;
			returnToMenu = false;
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
