package ca.javajesus.game.entities.structures.furniture;

import ca.javajesus.game.entities.SolidEntity;
import ca.javajesus.game.graphics.Screen;
import ca.javajesus.game.graphics.Sprite;
import ca.javajesus.level.Level;

public class Furniture extends SolidEntity {
	
	private static final long serialVersionUID = 8572895932183273902L;

	private Sprite sprite;
	
	public transient static Sprite diningTableSprite = new Sprite("/Buildings/Clutter/Furniture/Long_Castle_Dining_Table.png");
	public transient static Sprite bed = new Sprite("/Buildings/Clutter/Furniture/Simple_Bed.png");
	public transient static Sprite squareTable = new Sprite("/Buildings/Clutter/Furniture/Simple_Square_Table.png");
	public transient static Sprite bench = new Sprite("/Buildings/Clutter/Furniture/Simple_Bench.png");
	public transient static Sprite chairFront = new Sprite("/Buildings/Clutter/Furniture/Simple_Chair_Front_Facing.png");
	public transient static Sprite chairSide = new Sprite("/Buildings/Clutter/Furniture/Simple_Chair.png");
	public transient static Sprite computerMonitor = new Sprite("/Buildings/Clutter/Furniture/Simple_Computer_monitor.png");
	public transient static Sprite computerTower = new Sprite("/Buildings/Clutter/Furniture/Simple_Computer_Tower.png");
	public transient static Sprite filingCabinet = new Sprite("/Buildings/Clutter/Furniture/Simple_Filing_Cabinet.png");
	public transient static Sprite longTable = new Sprite("/Buildings/Clutter/Furniture/Simple_Long_Table.png");
	public transient static Sprite nightstand = new Sprite("/Buildings/Clutter/Furniture/Simple_Nightstand.png");
	public transient static Sprite sofa = new Sprite("/Buildings/Clutter/Furniture/Simple_Sofa.png");
	public transient static Sprite stool = new Sprite("/Buildings/Clutter/Furniture/Simple_Stool.png");
	public transient static Sprite throne = new Sprite("/Buildings/Clutter/Furniture/Simple_Throne.png");
	public transient static Sprite television = new Sprite("/Buildings/Clutter/Furniture/Simple_TV.png");

	public Furniture(Level level, int x, int y, Sprite sprite, int[] color) {
		super(level, x, y, sprite.xSize, sprite.ySize);
		this.sprite = sprite;
		this.color = color;
	}
	
	public Sprite getSprite() {
		return sprite;
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
