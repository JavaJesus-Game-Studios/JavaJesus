package ca.javajesus.game.entities.structures;

import java.awt.Point;
import ca.javajesus.game.gfx.Screen;
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
