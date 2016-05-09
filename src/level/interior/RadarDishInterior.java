package level.interior;

import java.awt.Point;

import game.entities.structures.transporters.TransporterInterior;
import level.Level;

public class RadarDishInterior extends Interior {

	private static final long serialVersionUID = 277175021065186443L;

	private Point exitPoint;

	public RadarDishInterior(Point point, Level level) {
		super("/Buildings/Unique_TechTopia_Interiors/Radar_Dish_Interior.png", new Point(801, 920), level);
		this.exitPoint = point;
	}

	protected void initNPCPlacement() {

	}

	protected void initSpawnerPlacement() {

	}

	protected void initChestPlacement() {

	}

	protected void otherEntityPlacement() {
		add(new TransporterInterior(this, 801, 920, nextLevel, exitPoint));
	}

}
