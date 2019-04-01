package javajesus.level.interior;

import java.awt.Point;
import java.io.IOException;

import javax.sound.sampled.Clip;

import javajesus.SoundHandler;
import javajesus.entities.transporters.Transporter;
import javajesus.entities.transporters.TransporterInterior;
import javajesus.level.Level;
import javajesus.level.LevelFactory;

/*
 * Custom cave for the alpha level
 */
public class AlphaCaveInterior extends Interior {
	
	/**
	 * @param point - spawn point in the cave
	 * @param level - outside level
	 * @throws IOException
	 */
	public AlphaCaveInterior(Point point, Level level) throws IOException {
		super("/WORLD_DATA/INTERIOR_DATA/AlphaCaveInterior", new Point(912, 1392), level, LevelFactory.ALPHA_CAVE_INTERIOR);
	}

	@Override
	public Transporter[] getTransporters() throws IOException {
		return new Transporter[] { new TransporterInterior(this, 920, 1400, outside) };
	}
	/**
	 * @return a clip of the  background music
	 */
	@Override
	public Clip getBackgroundMusic() {
		return SoundHandler.combatMusic;
	}

	
}
