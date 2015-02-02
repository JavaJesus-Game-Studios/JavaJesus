package ca.javajesus.level;

import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.imageio.ImageIO;

import ca.javajesus.game.entities.Entity;
import ca.javajesus.game.entities.Mob;
import ca.javajesus.game.entities.Player;
import ca.javajesus.game.entities.Spawner;
import ca.javajesus.game.entities.particles.HealthBar;
import ca.javajesus.game.gfx.JJFont;
import ca.javajesus.game.gfx.Screen;
import ca.javajesus.level.tile.Tile;

public abstract class Level {
	protected int[] tiles;
	public int width;
	public int height;
	protected List<Entity> entities = new CopyOnWriteArrayList<Entity>();
	protected List<Mob> mobs = new CopyOnWriteArrayList<Mob>();
	protected List<Player> players = new CopyOnWriteArrayList<Player>();
	private String imagePath;
	private BufferedImage image;
	public Point spawnPoint;

	protected int[] tileColours;

	public static Level level1 = new Level1();
	public static Level random = new RandomLevel(level1.width, level1.height);
	public static Level random2 = new RandomLevel2(level1.width, level1.height);

	public Level(String imagePath) {
		spawnPoint = new Point(0, 0);
		if (imagePath != null) {
			this.imagePath = imagePath;
			this.loadLevelFromFile();
		} else {
			this.width = 64;
			this.height = 64;
			tiles = new int[width * height];
			this.generateLevel();
		}
	}

	public Level(int width, int height) {
		spawnPoint = new Point(0, 0);
		this.width = width;
		this.height = height;
		tiles = new int[width * height];
		generateLevel();
	}

	protected abstract void initNPCPlacement();

	protected abstract void initSpawnerPlacement();

	protected abstract void initChestPlacement();

	protected abstract void otherEntityPlacement();

	private void loadLevelFromFile() {
		try {
			this.image = ImageIO.read(Level.class.getResource(this.imagePath));
			this.width = this.image.getWidth();
			this.height = this.image.getHeight();
			tiles = new int[width * height];
			tileColours = this.image
					.getRGB(0, 0, width, height, null, 0, width);
			this.loadTiles();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	protected void loadTiles() {
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				tileCheck: for (Tile t : Tile.tiles) {
					if (t != null
							&& t.getLevelColour() == tileColours[x + y * width]) {
						this.tiles[x + y * width] = t.getId();
						break tileCheck;
					}
				}
			}
		}
	}

	protected void generateLevel() {
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				if (x * y % 10 < 10) {
					tiles[x + y * width] = Tile.GRASS.getId();
				} else {
					tiles[x + y * width] = Tile.STONE.getId();

				}

			}
		}

	}

	protected void saveLevelToFile() {
		try {
			ImageIO.write(image, "png",
					new File(Level.class.getResource(this.imagePath).getFile()));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void alterTile(int x, int y, Tile newTile) {
		this.tiles[x + y * width] = newTile.getId();
		if (image != null) {
			image.setRGB(x, y, newTile.getLevelColour());
			tileColours[x + y * width] = newTile.getId();
		}

	}

	public void tick() {
		for (Entity e : getEntities()) {
			e.tick();
		}

		for (Tile t : Tile.tiles) {
			if (t == null) {
				break;
			}
			t.tick();
		}
	}

	public void renderTile(Screen screen, int xOffset, int yOffset) {
		if (xOffset < 0)
			xOffset = 0;
		if (xOffset > ((width << 3) - screen.width))
			xOffset = ((width << 3) - screen.width);
		if (yOffset < 0)
			yOffset = 0;
		if (yOffset > ((height << 3) - screen.height))
			yOffset = ((height << 3) - screen.height);

		screen.setOffset(xOffset, yOffset);

		for (int y = (yOffset >> 3); y < (yOffset + screen.height >> 3) + 1; y++) {
			for (int x = (xOffset >> 3); x < (xOffset + screen.width >> 3) + 1; x++) {
				getTile(x, y).render(screen, this, x << 3, y << 3);
			}

		}
	}

	public void renderEntities(Screen screen) {
		for (Mob m : getMobs()) {
			if (!m.renderOnTop)
				m.render(screen);
		}
		for (Entity e : getEntities()) {
			if (!(e instanceof Mob)) {
				if (e instanceof HealthBar && ((HealthBar) e).renderOnTop) {
					e.render(screen);
				} else {
					e.render(screen);
				}
			}
		}
		for (Mob m : getMobs()) {
			if (m.renderOnTop)
				m.render(screen);
		}
	}

	public void renderFont(String msg, Screen screen, int x, int y, int colour,
			int scale) {
		JJFont.render(msg, screen, x, y, colour, scale);
	}

	public Tile getTile(int x, int y) {
		if (0 > x || x >= width || 0 > y || y >= height)
			return Tile.VOID;
		return Tile.tiles[tiles[x + y * width]];

	}

	public void addEntity(Entity entity) {
		this.entities.add(entity);
		if (entity instanceof Mob) {
			this.mobs.add((Mob) entity);
			if (entity instanceof Player) {
				this.players.add((Player) entity);
			}
		}

	}

	public void remEntity(Entity entity) {
		this.entities.remove(entity);
		if (entity instanceof Mob) {
			this.mobs.remove((Mob) entity);
			if (entity instanceof Player) {
				this.players.remove((Player) entity);
			}
		}
	}

	public List<Entity> getEntities() {
		return entities;
	}

	public List<Mob> getMobs() {
		return mobs;
	}

	public List<Player> getPlayers() {
		return players;
	}

	public void addSpawner(double x, double y, Entity entity) {
		this.addEntity(new Spawner(this, x, y, entity));
	}

	public void addSpawner(double x, double y, int id) {
		this.addEntity(new Spawner(this, x, y, id));
	}

	public void init() {
		if (this.mobs.size() <= 1) {
			initNPCPlacement();
			initSpawnerPlacement();
			initChestPlacement();
			otherEntityPlacement();
		}

	}

	// size is area
	public boolean tileCollision(double x, double y, double xa, double ya,
			int size) {

		boolean solid = false;
		for (int c = 0; c < 4; c++) {
			int xt = (((int) x + (int) xa) + c % 2 * size) / 8;
			int yt = (((int) y + (int) ya) + c / 2 * size) / 8;
			if (getTile(xt, yt).isSolid())
				solid = true;
		}
		return solid;

	}
}
