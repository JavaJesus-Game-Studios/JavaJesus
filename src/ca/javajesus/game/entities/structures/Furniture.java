package ca.javajesus.game.entities.structures;

import ca.javajesus.game.entities.SolidEntity;
import ca.javajesus.game.gfx.Colors;
import ca.javajesus.game.gfx.Screen;
import ca.javajesus.game.gfx.Sprite;
import ca.javajesus.level.Level;

public class Furniture extends SolidEntity {
	
	private Sprite sprite;
	public static Sprite diningTableSprite = new Sprite("/Buildings/Clutter/Furniture/Long_Castle_Dining_Table.png");

	public Furniture(Level level, int x, int y, Sprite sprite, int color) {
		super(level, x, y, sprite.xSize, sprite.ySize);
		this.sprite = sprite;
		this.color = color;
		this.bounds.setSize(sprite.xSize - 8, sprite.ySize);
		this.shadow.setSize(0, 0);
		this.bounds.setLocation(x, y);
	}
	
	public void init(Level level, int x, int y) {
		this.setX(x);
		this.setY(y);
		this.init(level);
	}
	
	public void render(Screen screen) {
		
		screen.render(x, y, color, sprite);
		
	}

}
