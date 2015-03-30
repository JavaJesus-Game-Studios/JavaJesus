package ca.javajesus.game.entities.structures.trees;

import java.util.ArrayList;

import ca.javajesus.game.entities.Entity;
import ca.javajesus.game.graphics.Screen;
import ca.javajesus.level.Level;

public class Forest extends Entity {
	
	public int width, height;
	private ArrayList<Tree> trees = new ArrayList<Tree>();

	public Forest(Level level, int x, int y, int width, int height) {
		super(level);
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		initForest();
	}
	
	private void initForest() {
		for (int i = 0; i < width; i += 20) { 
			for (int j = 0; j < height; j += 50) {
			   trees.add(new MediumSequoia(level, x + i, y + j));
			}
		}
	}

	public void tick() {

	}

	public void render(Screen screen) {
		for (Tree t: trees) {
			t.render(screen);
		}
	}
	
	

}
