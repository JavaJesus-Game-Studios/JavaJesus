package ca.javajesus.game;

import javax.sound.sampled.Clip;

import ca.javajesus.game.entities.Entity;
import ca.javajesus.game.entities.Player;
import ca.javajesus.game.gui.Launcher;
import ca.javajesus.level.Level;
import ca.javajesus.level.LevelList;
import ca.javajesus.saves.GameData;

public class Game implements Runnable {

	public final static int ENTITY_LIMIT = 1000;
	public final static int MOB_LIMIT = 300;

	private boolean running = false;

	public static int hours = 10;
	public static int minutes;

	public static LevelList levels;

	/** Creates instance of the player */
	public static Player player;

	public static GameMode mode;

	private static Display display;

	private Launcher launcher;

	public enum GameMode {
		ADVENTURE, MINI, SURVIVAL
	}

	public Game() {
		mode = GameMode.ADVENTURE;

	}

	/** Called before the game is initialized */
	public synchronized void start() {
		if (launcher == null) {
			launcher = new Launcher(this);
			launcher.start();
		} else {
			levels = new LevelList(mode);
			player = new Player(getLevel(), getLevel().spawnPoint.x,
					getLevel().spawnPoint.y);
			display = new Display();
			player.setInput(new InputHandler(display));
			getLevel().addEntity(player);
			getLevel().init();
			running = true;
			new Thread(this, "Game").start();
			launcher = null;
		}

	}

	public synchronized void startWithLoad() {
		if (GameData.load()) {
			player = null;
			player = getLevel().getPlayer();
			display = new Display();
			player.setInput(new InputHandler(display));
			Entity.initSound();
			player.gun.initSound();
			player.getLevel().getBackgroundMusic().loop(Clip.LOOP_CONTINUOUSLY);
			running = true;
			new Thread(this, "Game").start();
			launcher = null;
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
					//System.out.println(frames + " fps");
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
			display = null;
			start();
		} else
			display.tick();
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
	private void render() {
		display.render();

	}

	/** Main Method Creation */
	public static void main(String[] args) {
		new Game().start();

	}

}
