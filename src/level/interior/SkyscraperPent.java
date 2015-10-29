package level.interior;

import game.entities.Mob.Direction;
import game.entities.structures.transporters.TransporterStairWood;

import java.awt.Point;

import level.Level;

public class SkyscraperPent extends Interior {

	private Point exitPoint;
	private Level level;
	
	public SkyscraperPent(Point point, Level level) {
		super("/Buildings/Generic Interiors/Skyscraper_Interiors/Skyscraper_Floor_11.png", new Point(500, 500), level);
		this.exitPoint = point;
		this.level = level;
	}
	
	protected void initNPCPlacement() {

	}

	protected void initSpawnerPlacement() {

	}

	protected void initChestPlacement() {

	}

	protected void otherEntityPlacement() {
		this.addEntity(new TransporterStairWood(this, 2112, 2008, level, new Point(2105, 1975), Direction.SOUTH));
	}
}
