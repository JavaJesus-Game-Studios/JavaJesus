package ca.javajesus.game;

import java.awt.AlphaComposite;
import java.awt.BorderLayout;
import java.awt.Canvas;
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

import ca.javajesus.game.entities.Demon;
import ca.javajesus.game.entities.NPC;
import ca.javajesus.game.entities.Player;
import ca.javajesus.game.gfx.Screen;
import ca.javajesus.level.Level;
import ca.javajesus.level.Level1;
import ca.javajesus.level.RandomLevel;

public class Game extends Canvas implements Runnable {

	/** Does something */
	private static final long serialVersionUID = 1L;

	/** Determines how long the loading screen lasts */
	private static final int LOAD_SPEED = 20;

	/** Width of the game */
	public static final int WIDTH = 300;

	/** Height of the game */
	public static final int HEIGHT = WIDTH / 12 * 9;

	/** Scales the size of the screen */
	public static final int SCALE = 3;

	/** Title of JFrame */
	public static final String NAME = "Java Jesus by the Coders of Anarchy";

	/** Entity limit per screen */
	public final int ENTITY_LIMIT = 10;

	/** Determines whether the game is running or not */
	public boolean running = false;

	/** Creates the JFrame */
	private static JFrame frame;

	/** Creates the tickCount var */
	public int tickCount;

	/** Temporary Solution to limit frames */
	private final int FRAMES_PER_SECOND = 60;
	private final int DELAY = 1000 / FRAMES_PER_SECOND;

	/** Loads an image from a file */
	private BufferedImage image = new BufferedImage(WIDTH, HEIGHT,
			BufferedImage.TYPE_INT_RGB);

	/** Pixel data of an image */
	private int[] pixels = ((DataBufferInt) image.getRaster().getDataBuffer())
			.getData();

	/** Does something */
	private int[] colours = new int[6 * 6 * 6];

	/** Creates instance of the screen */
	private Screen screen;

	/** Creates instance of the input */
	public InputHandler input;

	/** Creates instance of the player */
	public Player player;

	/** Creates instance of the random level */
	public Level randomLevel;

	/** This starts the game */
	public Game() {
		setMinimumSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
		setMaximumSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
		setPreferredSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
		frame = new JFrame(NAME);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new BorderLayout());
		frame.getContentPane().add(this);
		frame.pack();
		frame.requestFocus();
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		frame.toFront();
		frame.repaint();
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

					colours[index++] = rr << 16 | gg << 8 | bb;
				}
			}
		}

		screen = new Screen(WIDTH, HEIGHT, this);
		input = new InputHandler(this);
		initLevels();
		player = new Player(getLevel(), 50, 50, input);
		getLevel().addEntity(player);
	}

	public void updateLevel() {
		int index = 0;
		for (int r = 0; r < 6; r++) {
			for (int g = 0; g < 6; g++) {
				for (int b = 0; b < 6; b++) {
					int rr = (r * 255 / 5);
					int gg = (g * 255 / 5);
					int bb = (b * 255 / 5);

					colours[index++] = rr << 16 | gg << 8 | bb;
				}
			}
		}
		initLevels();
	}

	/** Initializes the level data */
	private void initLevels() {
		randomLevel = new RandomLevel(WIDTH, HEIGHT);
		Demon demon = new Demon(randomLevel, "Demon", 50, 50, 1, player);
		randomLevel.addSpawner(50, 50, demon, screen);
	}

	/** Starts the game */
	public synchronized void start() {
		running = true;
		new Thread(this).start();

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

		init();

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
		getLevel().tick();
	}

	/** Returns the instance of the current Level */
	private Level getLevel() {
		if (player == null) {
			return Level.level1;
		}
		return player.getLevel();

	}

	/** Renders the screen */
	private void render() {
		BufferStrategy bs = getBufferStrategy();
		if (bs == null) {
			createBufferStrategy(3);
			return;
		}

		int xOffset = (int) player.x - (screen.width / 2);
		int yOffset = (int) player.y - (screen.height / 2);

		getLevel().renderTile(screen, xOffset, yOffset);
		getLevel().renderEntities(screen);

		for (int y = 0; y < screen.height; y++) {
			for (int x = 0; x < screen.width; x++) {
				int colourCode = screen.pixels[x + y * screen.width];
				if (colourCode < 255)
					pixels[x + y * WIDTH] = colours[colourCode];
			}

		}
		Graphics g = bs.getDrawGraphics();
		g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
		if (player.isSwinging) {
			player.getSword().draw(g);
		}
		g.setFont(new Font("Verdana", 0, 20));
		g.setColor(Color.YELLOW);
		g.drawString("Player: " + (int) player.x + ", " + (int) player.y, 5, 20);
		if (player.hasDied) {
			g.setFont(new Font("Verdana", 0, 50));
			g.setColor(Color.BLACK);
			g.drawString("RIP", WIDTH * SCALE / 2 - 50, HEIGHT * SCALE / 2);
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
		loadScreen();
		new Game().start();

	}
	

}
