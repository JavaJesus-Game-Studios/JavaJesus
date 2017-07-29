package javajesus.level.interior;

import java.awt.Point;
import java.io.IOException;

import javajesus.entities.Spawner;
import javajesus.entities.npcs.NPC;
import javajesus.entities.solid.furniture.Chest;
import javajesus.entities.transporters.Stairs;
import javajesus.entities.transporters.Transporter;
import javajesus.entities.transporters.TransporterInterior;
import javajesus.level.Level;
import javajesus.utility.Direction;

/*
 * First interior of the skyscraper
 */
public class SkyscraperLobby extends Interior {

	/**
	 * @param point - entrance point
	 * @param level
	 * @throws IOException
	 */
	public SkyscraperLobby(Point point, Level level) throws IOException {
		super("/VISUAL_DATA/STATICS/ARCHITECTURE/GENERIC/INTERIORS/Skyscraper_Interiors/Skyscraper_Lobby", new Point(1936, 2040), level);
	}

	protected NPC[] getNPCPlacement() {
		return null;
	}

	protected Spawner[] getSpawnerPlacement() {
		return null;
	}

	protected Chest[] getChestPlacement() {
		return null;
	}

	@Override
	public Transporter[] getTransporters() throws IOException {
		return new Transporter[] { new TransporterInterior(this, 1944, 2048, outside), new Stairs(this, 2112, 1968,
		        new SkyscraperFloor(new Point(2116, 1971), this), Direction.EAST, Stairs.WOOD) };
	}

}
