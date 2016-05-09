package level.interior;

import java.awt.Point;

import game.entities.structures.transporters.TransporterInterior;
import level.Level;

public class TippeeInterior extends Interior {

	private static final long serialVersionUID = -2800032767259549825L;

	private Point exitPoint;

	public TippeeInterior(Point point, Level level) {
		super("/Buildings/Generic Interiors/Tippee_Interior.png", new Point(256, 272), level);
		this.exitPoint = point;
	}

	protected void initNPCPlacement() {

	}

	protected void initSpawnerPlacement() {

	}

	protected void initChestPlacement() {

	}

	protected void otherEntityPlacement() {
		add(new TransporterInterior(this, 256, 278, nextLevel, exitPoint));
	}

}
