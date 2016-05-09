package level.interior;

import java.awt.Point;

import game.entities.structures.transporters.TransporterInterior;
import level.Level;

public class TreeHouseInterior extends Interior {

	private static final long serialVersionUID = -5998920750795035872L;

	private Point exitPoint;

	public TreeHouseInterior(Point point, Level level) {
		super("/Buildings/Unique_HippyVille_Interiors/Tree_House_Interior.png", new Point(392, 464), level);
		this.exitPoint = point;
	}

	protected void initNPCPlacement() {

	}

	protected void initSpawnerPlacement() {

	}

	protected void initChestPlacement() {

	}

	protected void otherEntityPlacement() {
		add(new TransporterInterior(this, 392, 464, nextLevel, exitPoint));
	}

}
