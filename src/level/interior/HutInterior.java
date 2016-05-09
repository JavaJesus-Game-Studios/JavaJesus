package level.interior;

import java.awt.Point;

import game.entities.structures.transporters.TransporterInterior;
import level.Level;

public class HutInterior extends Interior {

	private static final long serialVersionUID = -8643880355397556987L;

	private Point exitPoint;

	public HutInterior(Point point, Level level) {
		super("/Buildings/Generic Interiors/Hut_Interior.png", new Point(256, 272), level);
		this.exitPoint = point;
	}

	protected void initNPCPlacement() {

	}

	protected void initSpawnerPlacement() {

	}

	protected void initChestPlacement() {

	}

	protected void otherEntityPlacement() {
		add(new TransporterInterior(this, 252, 278, nextLevel, exitPoint));
	}

}
