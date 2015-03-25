package ca.javajesus.game.entities.structures.furniture;

import ca.javajesus.game.entities.SolidEntity;
import ca.javajesus.game.gfx.Screen;
import ca.javajesus.game.gfx.Sprite;
import ca.javajesus.level.Level;

public class Furniture extends SolidEntity {
	
	private Sprite sprite;
	public static Sprite diningTableSprite = new Sprite("/Buildings/Clutter/Furniture/Long_Castle_Dining_Table.png");
	public static Sprite bed = new Sprite("/Buildings/Clutter/Furniture/Simple_Bed.png");
	public static Sprite squareTable = new Sprite("/Buildings/Clutter/Furniture/Simple_Square_Table.png");
	public static Sprite bench = new Sprite("/Buildings/Clutter/Furniture/Simple_Bench.png");
	public static Sprite chairFront = new Sprite("/Buildings/Clutter/Furniture/Simple_Chair_Front_Facing.png");
	public static Sprite chairSide = new Sprite("/Buildings/Clutter/Furniture/Simple_Chair.png");
	public static Sprite computerMonitor = new Sprite("/Buildings/Clutter/Furniture/Simple_Computer_monitor.png");
	public static Sprite computerTower = new Sprite("/Buildings/Clutter/Furniture/Simple_Computer_Tower.png");
	public static Sprite filingCabinet = new Sprite("/Buildings/Clutter/Furniture/Simple_Filing_Cabinet.png");
	public static Sprite longTable = new Sprite("/Buildings/Clutter/Furniture/Simple_Long_Table.png");
	public static Sprite nightstand = new Sprite("/Buildings/Clutter/Furniture/Simple_Nightstand.png");
	public static Sprite sofa = new Sprite("/Buildings/Clutter/Furniture/Simple_Sofa.png");
	public static Sprite stool = new Sprite("/Buildings/Clutter/Furniture/Simple_Stool.png");
	public static Sprite throne = new Sprite("/Buildings/Clutter/Furniture/Simple_Throne.png");
	public static Sprite television = new Sprite("/Buildings/Clutter/Furniture/Simple_TV.png");




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
