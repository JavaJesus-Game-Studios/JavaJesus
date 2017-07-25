package javajesus.level.interior;

import java.awt.Point;
import java.io.IOException;

import javajesus.entities.transporters.Stairs;
import javajesus.entities.transporters.Transporter;
import javajesus.level.Level;
import javajesus.utility.Direction;

public class ProjectsTop extends Interior {

	public ProjectsTop(Point point, Level level, int floor) throws IOException {
		super("/VISUAL_DATA/STATICS/ARCHITECTURE/GENERIC/INTERIORS/Projects_Interiors/Projects_Top_Floor.png",
		        new Point(580, 680), level);
	}

	@Override
	public Transporter[] getTransporters() throws IOException {
		return new Transporter[] {
		        new Stairs(this, 344, 528, outside, Direction.EAST, Stairs.CARPET) };
	}
}