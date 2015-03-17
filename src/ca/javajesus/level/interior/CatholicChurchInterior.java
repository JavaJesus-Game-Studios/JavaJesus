package ca.javajesus.level.interior;

import java.awt.Point;

import ca.javajesus.game.entities.structures.TransporterInterior;
import ca.javajesus.level.Level;

public class CatholicChurchInterior extends Interior {

	private Point exitPoint;

	public CatholicChurchInterior(Point point, Level level) {
		super("/Buildings/Generic Interiors/Catholic_Church_Interior.png", new Point(252,
				272), level);
		this.exitPoint = point;
	}

	protected void initNPCPlacement() {

	}

	protected void initSpawnerPlacement() {

	}

	protected void initChestPlacement() {

	}

	protected void otherEntityPlacement() {
		this.addEntity(new TransporterInterior(this, 252, 278, nextLevel,
				exitPoint));
	}

}
