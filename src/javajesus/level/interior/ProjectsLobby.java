package javajesus.level.interior;

import java.awt.Point;
import java.io.IOException;

import javajesus.entities.transporters.Stairs;
import javajesus.entities.transporters.Transporter;
import javajesus.entities.transporters.TransporterInterior;
import javajesus.level.Level;
import javajesus.utility.Direction;

public class ProjectsLobby extends Interior {

	public ProjectsLobby(Point point, Level level) throws IOException {
		super("/VISUAL_DATA/STATICS/ARCHITECTURE/GENERIC/INTERIORS/Projects_Interiors/Projects_Lobby.png",
		        new Point(584, 680), level);
	}

	@Override
	public Transporter[] getTransporters() throws IOException {
		return new Transporter[] { new TransporterInterior(this, 585, 700, outside), new Stairs(this, 344, 472,
		        new ProjectsFloor(new Point(353, 536), this, 1), Direction.WEST, Stairs.CARPET) };
	}
}
