package game.entities.structures.transporters;

import game.graphics.Screen;

import java.awt.Point;

import level.Level;

public class TransporterInterior extends Transporter {
	
	private static final long serialVersionUID = 2329949542471763366L;

	public TransporterInterior(Level currentLevel, int x, int y, Level nextLevel) {
		super(currentLevel, x, y, nextLevel);
	}
	
	public TransporterInterior(Level currentLevel, int x, int y, Level nextLevel, Point point) {
		super(currentLevel, x, y, nextLevel, point);
	}

	public void render(Screen screen) {
		
	}

}
