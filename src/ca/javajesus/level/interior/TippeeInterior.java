package ca.javajesus.level.interior;

import java.awt.Point;

import ca.javajesus.game.entities.structures.transporters.TransporterInterior;
import ca.javajesus.level.Level;

public class TippeeInterior extends Interior {

	private Point exitPoint;

	public TippeeInterior(Point point, Level level) {
		super("/Buildings/Generic Interiors/Tippee_Interior.png", new Point(256,
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
		this.addEntity(new TransporterInterior(this, 256, 278, nextLevel,
				exitPoint));
	}

}
