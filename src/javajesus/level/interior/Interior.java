package javajesus.level.interior;

import java.awt.Point;
import java.io.IOException;

import javax.sound.sampled.Clip;

import javajesus.dataIO.EntityData;
import javajesus.dataIO.LevelData;
import javajesus.entities.transporters.Transporter;
import javajesus.level.CharacterFactoryFactory;
import javajesus.level.Level;
import javajesus.quest.QuestLoader;
import javajesus.quest.factories.CharacterFactory;

/*
 * An Interior is a level inside a building
 */
public abstract class Interior extends Level {

	// level that was exited to come here
	protected Level outside;

	/**
	 * Interior ctor()
	 * 
	 * @param path    - file path
	 * @param spawn   - where the player enters the interior
	 * @param outside - the level that was exited
	 * @throws IOException
	 */
	public Interior(final String path, final Point spawn, final Level outside) throws IOException {
		this(path, spawn, outside, -1);
	}

	public Interior(final String path, final Point spawn, final Level outside, int levelId) throws IOException {
		super(path, "Interior", spawn, 0, levelId);

		// make sure its not a png file
		if (path.contains(".png")) {
			throw new IOException("Cannot load png files");
		}

		// instance data
		this.outside = outside;

	}

	@Override
	public void generateLevel() throws IOException {

		// initialize tile array
		levelTiles = new int[LEVEL_WIDTH * LEVEL_HEIGHT];

		// load the tiles from the path
		LevelData.load(Interior.class.getResourceAsStream(path), levelTiles);

		// load the entity data
		EntityData.load(this, Interior.class.getResourceAsStream(path + ENTITY));

		// add all the transporters
		for (Transporter t : getTransporters()) {
			add(t);
		}
		
		// now add any non unique quest givers
		CharacterFactory cf = CharacterFactoryFactory.make(this.getLevelId());
		if (cf != null) {
			cf.setNonUniqueCharacters(this);
			QuestLoader.initializeQuests(this);
		}
	}

	/**
	 * @return a clip of the background music
	 */
	public Clip getBackgroundMusic() {
		return outside.getBackgroundMusic();
	}

	/**
	 * All interiors have transporters to the outside
	 * 
	 * @return list of transporters
	 */
	public abstract Transporter[] getTransporters() throws IOException;

}
