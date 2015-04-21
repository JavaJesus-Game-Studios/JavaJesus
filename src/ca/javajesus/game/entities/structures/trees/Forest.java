package ca.javajesus.game.entities.structures.trees;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Random;

import ca.javajesus.game.entities.Entity;
import ca.javajesus.game.entities.SolidEntity;
import ca.javajesus.game.graphics.Screen;
import ca.javajesus.level.Level;
import ca.javajesus.level.tile.Tile;

public class Forest extends Entity {

	private static final long serialVersionUID = 7226541615386170385L;
	
	public int width, height;
	private int treeHeight;
	private ArrayList<Tree> trees = new ArrayList<Tree>();
	private Random random = new Random();

	public Forest(Level level, int x, int y, int width, int height) {
		super(level);
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		treeHeight = 50;
		initForest();
	}

	private boolean checkTile(int x, int y, int height) {
		Tile t = level.getTile((this.x + x) >> 3, (this.y + y + height) >> 3);
		if (t != Tile.GRASS && t != Tile.GRASS2 && t != Tile.GRASS3 && t != Tile.GRASS_FLOWER) {
			return false;
		}
		for (Entity e: level.getEntities()) {
			if (e instanceof SolidEntity) {
				if (e.getBounds().intersects(new Rectangle(this.x, this.y, 23, height))) {
					return false;
				}
			}
		}
		return true;
	}

	private void initForest() {
		for (int i = 0; i < width; i += (23 + random.nextInt(6))) {
			for (int j = 0; j < height; j += treeHeight) {
				switch (random.nextInt(10)) {
				case 0: {
					if (checkTile(i, j, 58)) {
						trees.add(new DeadSequoia(level, x + i, y + j));
					}
					treeHeight = 58 + random.nextInt(10);
					break;
				}
				case 1:
				case 2:
				case 3: {
					if (checkTile(i, j, 58)) {
						trees.add(new LargeSequoia(level, x + i, y + j));
					}
					treeHeight = 58 + random.nextInt(10);
					break;
				}
				case 4:
				case 5:
				case 6: {
					if (checkTile(i, j, 44)) {
						trees.add(new MediumSequoia(level, x + i, y + j));
					}
					treeHeight = 44 + random.nextInt(10);
					break;
				}
				default: {
					if (checkTile(i, j, 32)) {
						trees.add(new SmallSequoia(level, x + i, y + j));
					}
					treeHeight = 32 + random.nextInt(10);
					break;
				}
				}
			}
		}

		for (Tree t : trees) {
			level.addEntity(t);
		}

	}

	public void tick() {

	}

	public void render(Screen screen) {
		/*
		 * for (Tree t : trees) { t.render(screen); }
		 */
	}

}
