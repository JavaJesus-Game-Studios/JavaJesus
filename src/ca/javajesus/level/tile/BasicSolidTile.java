package ca.javajesus.level.tile;

import ca.javajesus.game.gfx.Sprite;

public class BasicSolidTile extends BaseTile {
	
	public BasicSolidTile(int id, int x, int y, int tileColour, int levelColour) {
		super(id, x, y, tileColour, levelColour);
		this.solid = true;
		
		}
	

}
