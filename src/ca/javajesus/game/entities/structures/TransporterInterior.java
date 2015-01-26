package ca.javajesus.game.entities.structures;

import java.awt.Point;
import java.awt.Rectangle;

import ca.javajesus.game.entities.Player;
import ca.javajesus.game.entities.SolidEntity;
import ca.javajesus.game.gfx.Colors;
import ca.javajesus.game.gfx.Screen;
import ca.javajesus.game.gfx.SpriteSheet;
import ca.javajesus.level.Level;

public class TransporterInterior extends Transporter {
	
	public TransporterInterior(Level currentLevel, double x, double y, Level nextLevel) {
		super(currentLevel, x, y, nextLevel);
	}
	
	public TransporterInterior(Level currentLevel, double x, double y, Level nextLevel, Point point) {
		super(currentLevel, x, y, nextLevel, point);
	}

	public void render(Screen screen) {
		
	}

}
