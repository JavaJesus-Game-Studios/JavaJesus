package game;

import javax.sound.sampled.Clip;

import game.entities.Player;
import game.gui.Launcher;
import level.Level;
import level.sandbox.SandboxSurvivalMap1;
import level.story.LordHillsboroughsDomain;
import save.GameData;

/*
 * Handles intialization of essential game components
 * Handles the processing of the Display canvas
 */
public class Game implements Runnable {

	public final static String FONT_NAME = "Impact";

	// Maximum amount of entities rendered at once
	public final static int ENTITY_LIMIT = 1000;

	// Maximum amount of mobs rendered at once
	public final static int MOB_LIMIT = 300;

	// Determines if the game is running or not
	private static boolean running;

	// Default Time of Day
	private static int hours = 10;

	// Default Time of day
	private static int minutes;

	// Instance of the single player
	public static Player player;

	// Starts the game based on the game mode
	public static GameMode mode;

	// The Display object that processes the image
	private Display display;

	// True if special developer numbers should be displayed
	private static boolean displayDevOverlay;

	// the gamescore
	public static int score;

	public static Integer fps;

	/**
	 * @author Derek
	 * @see Distinguishes the three different game modes available
	 */
	public enum GameMode {
		ADVENTURE, MINI, SURVIVAL
	}

	/**
	 * Constructor that creates a new instance of the game with a default
	 * Adventure type TODO should be IntroGui -> new game -> passes player
	 * parameters -> initialize player and level
	 */
	public Game(GameMode m, boolean load) {
		mode = m;

		Level level;

		switch (mode) {
		case MINI:
			level = Launcher.level;
			player = new Player(level, level.getSpawnPoint().x, level.getSpawnPoint().y);
			level.add(player);
			break;
		case SURVIVAL:
			level = new SandboxSurvivalMap1();
			player = new Player(level, level.getSpawnPoint().x, level.getSpawnPoint().y);
			level.add(player);
			break;
		default:
			if (load && (player = (Player) GameData.load("Player")) != null) {
				player.getInventory().getGun().initSound();
				level = player.getLevel();
			} else {
				System.out.println("Not loading game.");
				level = LordHillsboroughsDomain.level;
				level.reset();
				player = new Player(level, level.getSpawnPoint().x, level.getSpawnPoint().y);
				level.add(player);
			}
		}

		display = new Display();
		player.setInput(new InputHandler(display));

		level.getBackgroundMusic().loop(Clip.LOOP_CONTINUOUSLY);

		start();
	}

	/**
	 * Starts the game
	 */
	public synchronized void start() {

		running = true;
		new Thread(this, "Game").start();
	}

	/** Stops the game */
	public static synchronized void stop() {
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
					Game.fps = frames;
					frames = 0;
				}

			} catch (Exception e) {
				e.printStackTrace();
				display.sendCrashReportToScreen(e);
				running = false;
			}
		}

		Display.stop();
		display = null;
		new Launcher().start();

	}

	/** Called 60 times a second */
	public void tick() {
		if (!player.isDead()) {
			display.tick();
		} else {
			stop();
		}
	}

	/** Renders the screen */
	private void render() {
		display.render(player);

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
