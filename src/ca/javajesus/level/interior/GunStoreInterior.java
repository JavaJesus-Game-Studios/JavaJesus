package ca.javajesus.level.interior;

import java.awt.Point;

import ca.javajesus.game.entities.structures.transporters.TransporterInterior;
import ca.javajesus.level.Level;

public class GunStoreInterior extends Interior {

	private Point exitPoint;

	public GunStoreInterior(Point point, Level level) {
		super("/Buildings/Generic Interiors/Gun_Store_Interior.png", new Point(256,
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
		this.addEntity(new TransporterInterior(this, 256, 272, nextLevel,
				exitPoint));
	}

}
