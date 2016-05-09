package level.interior;

import java.awt.Point;

import game.entities.structures.transporters.TransporterInterior;
import level.Level;

public class GunStoreInterior extends Interior {

	private static final long serialVersionUID = 5126895773468450413L;

	private Point exitPoint;

	public GunStoreInterior(Point point, Level level) {
		super("/Buildings/Generic Interiors/Gun_Store_Interior.png", new Point(256, 272), level);
		this.exitPoint = point;
	}

	protected void initNPCPlacement() {

	}

	protected void initSpawnerPlacement() {

	}

	protected void initChestPlacement() {

	}

	protected void otherEntityPlacement() {
		add(new TransporterInterior(this, 256, 272, nextLevel, exitPoint));
	}

}
