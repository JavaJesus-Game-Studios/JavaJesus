package game.entities.structures.transporters;

import game.Game;
import game.entities.Player;
import game.graphics.Screen;
import level.Level;
import utility.Direction;

/*
 * A map transporter is placed on the edges of a level to transition  between two outisde levels
 */
public class MapTransporter extends Transporter {

	private static final long serialVersionUID = -788215519977991994L;

	// which side the map transporter is placed on a map
	private Direction dir;

	/**
	 * Creates a Map transporter that automatically transports a player that is
	 * on the edge of a map
	 * 
	 * @param currentLevel
	 *            the level it is on
	 * @param x
	 *            the x coord
	 * @param y
	 *            the y coord
	 * @param nextLevel
	 *            the next level
	 * @param dir
	 *            the side on the map
	 * @param width
	 *            the width of the transporter
	 * @param height
	 *            the height of the transporter
	 */
	public MapTransporter(Level currentLevel, int x, int y, Level nextLevel, Direction dir, int width, int height) {
		super(currentLevel, x, y, nextLevel);
		this.dir = dir;
		setBounds(getX(), getY(), width, height);
	}

	/**
	 * Updates the transporter
	 */
	public void tick() {

		for (Player player : getLevel().getPlayers()) {
			if (getBounds().intersects(player.getBounds()) && !player.isDriving()) {
				calcNewSpawn();
				player.updateLevel(getNextLevel());
			}
		}

	}
	
	/**
	 * Calculates the new spawnpoint for the next level
	 */
	private void calcNewSpawn() {

		// the x and y spawn coords
		int x, y;

		switch (dir) {

		// NORTH EDGE OF MAP
		case NORTH: {

			// X is proportional to width
			x = getNextLevel().getWidth() * Game.player.getX() / getLevel().getWidth();

			y = (getNextLevel().getHeight() * 8) - 16;
			break;
		}

		// SOUTH EDGE OF MAP
		case SOUTH: {

			// X is proportional to width
			x = getNextLevel().getWidth() * Game.player.getX() / getLevel().getWidth();

			y = 16;
			break;
		}

		// EAST EDGE OF MAP
		case EAST: {

			x = 16;

			// Y is proportional to height
			y = getNextLevel().getHeight() * Game.player.getY() / getLevel().getHeight();
			break;
		}

		// WEST EDGE OF MAP
		default: {

			x = (getNextLevel().getWidth() * 8) - 16;

			// Y is proportional to height
			y = getNextLevel().getHeight() * Game.player.getY() / getLevel().getHeight();
			break;
		}
		}

		// update the next level spawnpoint
		getNextLevel().setSpawnPoint(x, y);
	}

	/**
	 * Blank because it is invisible
	 */
	public void render(Screen screen) {
	}

}
