package level;

import game.entities.Entity;
import game.entities.Spawner;
import game.entities.npcs.NPC;
import game.entities.structures.furniture.Chest;
import game.entities.structures.transporters.MapTransporter;

public class RoadLevel extends Level {

	private static final long serialVersionUID = 4367488134635605719L;

	public RoadLevel() {
		super("/Levels/Test_Levels/Road_Test_Level.png", false, "Road Level");
		setSpawnPoint(50, 50);
	}

	@Override
	protected NPC[] getNPCPlacement() {
		return null;
	}

	@Override
	protected Spawner[] getSpawnerPlacement() {
		return null;
	}

	@Override
	protected Chest[] getChestPlacement() {
		return null;
	}

	@Override
	protected MapTransporter[] getMapTransporterPlacement() {
		return null;
	}

	@Override
	protected Entity[] getOtherPlacement() {
		return null;
	}

}
