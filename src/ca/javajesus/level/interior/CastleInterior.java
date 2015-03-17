package ca.javajesus.level.interior;

import java.awt.Point;

import ca.javajesus.game.entities.structures.TransporterInterior;
import ca.javajesus.level.Level;

public class CastleInterior extends Interior {

	private Point exitPoint;

	public CastleInterior(Point point, Level level) {
		super("/Buildings/Generic Interiors/Castle_Tower_Interior.png",
				new Point(1935, 2088), level);
		this.exitPoint = point;
	}

	protected void initNPCPlacement() {

	}

	protected void initSpawnerPlacement() {

	}

	protected void initChestPlacement() {

	}

	protected void otherEntityPlacement() {
		this.addEntity(new TransporterInterior(this, 1935, 2088, nextLevel,
				exitPoint));
	}

}
