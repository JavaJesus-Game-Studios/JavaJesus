package level.interior;

import game.entities.Entity;
import game.entities.Spawner;
import game.entities.structures.transporters.TransporterInterior;

import java.awt.Point;

import level.Level;

public class RadarDishInterior extends Interior {

	private Point exitPoint;
	private Entity entity;
	private Spawner spawner;

	public RadarDishInterior(Point point, Level level) {
		super("/Buildings/Unique_TechTopia_Interiors/Radar_Dish_Interior.png",
				new Point(801, 920), level);
		this.exitPoint = point;
	}

	public RadarDishInterior(Point point, Level level, Entity entity,
			Spawner spawner) {
		super("/Buildings/Unique_TechTopia_Interiors/Radar_Dish_Interior.png",
				new Point(801, 920), level);
		this.exitPoint = point;
		this.entity = entity;
		entity.init(this);
		this.spawner = spawner;
		spawner.init(this);
	}

	public RadarDishInterior(Point point, Level level, Entity entity) {
		super("/Buildings/Unique_TechTopia_Interiors/Radar_Dish_Interior.png",
				new Point(801, 920), level);
		this.exitPoint = point;
		this.entity = entity;
		entity.init(this);
	}

	protected void initNPCPlacement() {

	}

	protected void initSpawnerPlacement() {

		if (spawner != null) {
			this.addEntity(spawner);
		}

	}

	protected void initChestPlacement() {

		if (entity != null) {
			this.addEntity(entity);
		}

	}

	protected void otherEntityPlacement() {
		this.addEntity(new TransporterInterior(this, 801, 920, nextLevel,
				exitPoint));
	}

}
