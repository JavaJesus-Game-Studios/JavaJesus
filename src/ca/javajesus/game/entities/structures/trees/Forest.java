package ca.javajesus.game.entities.structures.trees;

import java.util.ArrayList;
import java.util.Random;

import ca.javajesus.game.entities.Entity;
import ca.javajesus.game.graphics.Screen;
import ca.javajesus.level.Level;

public class Forest extends Entity {

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

	private void initForest() {
		for (int i = 0; i < width; i += (23 + random.nextInt(6))) {
			for (int j = 0; j < height; j += treeHeight) {
				switch (random.nextInt(10)) {
				case 0: {					trees.add(new DeadSequoia(level, x + i, y + j));
					treeHeight = 58 + random.nextInt(10);
					break;
				}
				case 1: {
					trees.add(new LargeSequoia(level, x + i, y + j));
					treeHeight = 58 + random.nextInt(10);
					break;
				}
				case 2: {
					trees.add(new LargeSequoia(level, x + i, y + j));
					treeHeight = 58 + random.nextInt(10);
					break;
				}
				case 3: {
					trees.add(new LargeSequoia(level, x + i, y + j));
					treeHeight = 58 + random.nextInt(10);
					break;
				}
				case 4: {
					trees.add(new MediumSequoia(level, x + i, y + j));
					treeHeight = 44 + random.nextInt(10);
					break;
				}
				case 5: {
					trees.add(new MediumSequoia(level, x + i, y + j));
					treeHeight = 44 + random.nextInt(10);
					break;
				}
				case 6: {
					trees.add(new MediumSequoia(level, x + i, y + j));
					treeHeight = 44 + random.nextInt(10);
					break;
				}
				default: {
					trees.add(new SmallSequoia(level, x + i, y + j));
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
		for (Tree t : trees) {
			t.render(screen);
		}*/
	}

}
