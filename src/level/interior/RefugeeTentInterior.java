package level.interior;

import java.awt.Point;

import game.entities.structures.transporters.TransporterInterior;
import level.Level;

public class RefugeeTentInterior extends Interior {

	private static final long serialVersionUID = -6157769676491083039L;

	private Point exitPoint;

	public RefugeeTentInterior(Point point, Level level) {
		super("/Buildings/Generic Interiors/Refugee_Tent_Interior.png", new Point(288, 264), level);
		this.exitPoint = point;
	}

	protected void initNPCPlacement() {

	}

	protected void initSpawnerPlacement() {

	}

	protected void initChestPlacement() {

	}

	protected void otherEntityPlacement() {
		add(new TransporterInterior(this, 288, 264, nextLevel, exitPoint));
	}

}
