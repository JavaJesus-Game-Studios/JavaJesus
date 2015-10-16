package ca.javajesus.game;

import javax.sound.sampled.Clip;

import ca.javajesus.game.entities.Entity;
import ca.javajesus.game.entities.Player;
import ca.javajesus.game.gui.Launcher;
import ca.javajesus.level.Level;
import ca.javajesus.level.LevelList;
import ca.javajesus.saves.GameData;

public class Game implements Runnable {

	// Font name
	public final static String font_name = "Impact";

	// Maximum amount of entities rendered at once
	public final static int ENTITY_LIMIT = 1000;

	// Maximum amount of mobs rendered at once
	public final static int MOB_LIMIT = 300;

	// Determines if the game is running or not
	private boolean running = false;

	// Default Time of Day
	private static int hours = 10;

	// Default Time of day
	private static int minutes;

	// Stored List of all known levels
	public static LevelList levels;

	// Instance of the single player
	public static Player player;

	// Starts the game based on the game mode
	public static GameMode mode;

	// The Display object that processes the image
	private static Display display;

	private static boolean displayDevOverlay;

	/**
	 * @author Derek
	 * @see Distinguishes the three different game modes available
	 */
	public enum GameMode {
		ADVENTURE, MINI, SURVIVAL
	}

	/**
	 * Constructor that creates a new instance of the game with a default
	 * Adventure type
	 */
	public Game(GameMode m) {
		mode = m;
		levels = new LevelList(mode);
		player = new Player(getLevel(), getLevel().spawnPoint.x,
				getLevel().spawnPoint.y);
		display = new Display();
		player.setInput(new InputHandler(display));
		getLevel().getBackgroundMusic().loop(Clip.LOOP_CONTINUOUSLY);
		getLevel().addEntity(player);
		getLevel().init();
	}

	/**
	 * Starts the game
	 */
	public synchronized void start() {

		running = true;
		new Thread(this, "Game").start();
	}

	/**
	 * Starts the game by first checking for a save file
	 */
	public synchronized void startWithLoad() {
		if (GameData.load()) {
			player = getLevel().getPlayer();
			display = new Display();
			player.setInput(new InputHandler(display));
			Entity.initSound();
			player.gun.initSound();
			player.getLevel().getBackgroundMusic().loop(Clip.LOOP_CONTINUOUSLY);
			running = true;
			new Thread(this, "Game").start();
		} else {
			System.out.println("Save not found!");
			this.start();
		}

	}

	/** Stops the game */
	public synchronized void stop() {
		running = false;
	}

	/** Code executed during runtime */
	public synchronized void run() {
		long lastMinute = System.currentTimeMillis();
		long lastTime = System.nanoTime();
		double nsPerTick = 1000000000 / 60.0;
		int frames = 0;
		long lastTimer = System.currentTimeMillis();
		double delta = 0;

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
					tick();
					delta--;
				}
				frames++;
				render();

				if (System.currentTimeMillis() - lastTimer >= 1000) {
					lastTimer += 1000;
					// System.out.println(frames + " fps");
					frames = 0;
				}

			} catch (Exception e) {
				e.printStackTrace();
				display.sendCrashReportToScreen(e);
				running = false;
			}
		}

	}

	/** Called 60 times a second */
	public void tick() {
		if (player.isDead()) {
			stop();
			display.stop();
			display = null;
			start();
		} else
			display.tick();
	}

	/**
	 * @return returns the current level the game is rendering if the current
	 *         level is null, then the default level is chosen (for startup)
	 */
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
	private void render() {
		display.render();

	}

	/**
	 * @return Current Game Hour
	 */
	public static int getHour() {
		return hours;
	}

	/**
	 * @return Current Game minute
	 */
	public static int getMinutes() {
		return minutes;
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

}
