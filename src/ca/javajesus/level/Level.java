package ca.javajesus.level;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.imageio.ImageIO;

import ca.javajesus.game.entities.Entity;
import ca.javajesus.game.entities.Spawner;
import ca.javajesus.game.gfx.JJFont;
import ca.javajesus.game.gfx.Screen;
import ca.javajesus.level.tile.MultiTile;
import ca.javajesus.level.tile.Tile;

public class Level {
	protected int[] tiles;
	public int width;
	public int height;
	private List<Entity> entities = new CopyOnWriteArrayList<Entity>();
	private String imagePath;
	private BufferedImage image;

	/** temporary ints */
	protected byte grass = 0;
	protected byte sand = 1;
	protected byte rock = 2;
	protected byte dirt = 3;
	protected byte water = 4;

	protected byte road1 = 5;
	protected byte road2 = 8;
	protected byte road3 = 9;

	protected byte lily = 6;
	protected byte waterSand = 7;

	protected byte coniferTrees = 9;
	protected byte decidiousTrees = 10;
	
	protected int[] tileColours;

	public static Level level1 = new Level1("/Levels/tile_tester_level.png");

	public Level(String imagePath) {
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
		this.width = width;
		this.height = height;
		tiles = new int[width * height];
		generateLevel();
	}

	private void loadLevelFromFile() {
		try {
			this.image = ImageIO.read(Level.class.getResource(this.imagePath));
			this.width = this.image.getWidth();
			this.height = this.image.getHeight();
			tiles = new int[width * height];
			tileColours = this.image.getRGB(0, 0, width, height, null, 0,
					width);
			this.loadTiles();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private boolean tempBool = true;

	protected void loadTiles() {
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				tileCheck: for (Tile t : Tile.tiles) {
					if (tempBool && x == 6 && y == 6) {
						t = Tile.TREE1;
						tileColours[x + y * width] = t.getLevelColour();
						tempBool = false;
					}
					if (t != null
							&& t.getLevelColour() == tileColours[x + y * width]) {
						if (t instanceof MultiTile) {
							((MultiTile) t).initMultiBlock(this, x, y);
						} else {
							if (x == 7 && y == 6)
								System.out.println("OVERRIDE: "
										+ image.getRGB(x, y));
							this.tiles[x + y * width] = t.getId();
						}
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
		for (Entity e : getEntities()) {
			e.render(screen);
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
		this.getEntities().add(entity);

	}

	public void remEntity(Entity entity) {
		this.getEntities().remove(entity);

	}

	public List<Entity> getEntities() {
		return entities;
	}

	public void addSpawner(double x, double y, Entity entity, Screen screen) {
		this.addEntity(new Spawner(x, y, this, entity, screen));
	}

	public void initNPCPlacement() {

	}

}
