package ca.javajesus.game.entities.structures.trees;

import ca.javajesus.game.entities.SolidEntity;
import ca.javajesus.game.graphics.Screen;
import ca.javajesus.game.graphics.Sprite;
import ca.javajesus.level.Level;

public class Tree extends SolidEntity {
	
	public static Sprite DEAD_SEQUOIA = new Sprite("/Buildings/Trees/Coniferous_Trees/Dead_Sequoia.png");
	public static Sprite SMALL_SEQUOIA = new Sprite("/Buildings/Trees/Coniferous_Trees/Sequoia_Small.png");
	public static Sprite MEDIUM_SEQUOIA = new Sprite("/Buildings/Trees/Coniferous_Trees/Sequoia_Medium.png");
	public static Sprite LARGE_SEQUOIA = new Sprite("/Buildings/Trees/Coniferous_Trees/Sequioa_Large.png");
	public static Sprite SMALL_DECIDUOUS = new Sprite("/Buildings/Trees/Deciduous_Trees/Small_Tree.png");
	
	private Sprite sprite;

	public Tree(Level level, int x, int y, int height, Sprite sprite) {
		super(level, x, y, 17, height);
		this.sprite = sprite;
	}
	
	public void render(Screen screen) {
		screen.render(x, y, null, sprite);
	}

}
