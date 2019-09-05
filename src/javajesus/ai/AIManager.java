package javajesus.ai;

import java.awt.Point;
import java.awt.Rectangle;
import java.util.List;

import javajesus.entities.Mob;
import javajesus.entities.Player;
import javajesus.graphics.JJFont;
import javajesus.graphics.Screen;
import javajesus.level.tile.TileAdapter;

public class AIManager implements Runnable {

	int ticks;

	private Player player;
	private static Screen screen;

	private static int[] policy = {};

	private static int width, height, currX, currY;

	private boolean running;

	public AIManager(Player player, Screen screen) {
		this.player = player;
		AIManager.screen = screen;

		width = (screen.getWidth() >> 3) + 1;
		height = screen.getHeight() >> 3;
	}

	public void start() {
		running = true;
		new Thread(this).start();
	}

	public void stop() {
		running = false;
	}

	public void run() {

		long lastTime = System.currentTimeMillis();

		while (running) {

			if (System.currentTimeMillis() - lastTime > 500) {
				lastTime = System.currentTimeMillis();

				int[] reduced = process(player.getLevel().getVisibleTiles(screen));
				currX = screen.getxOffset();
				currY = screen.getyOffset();

				policy = new MDP(computeTransitions(reduced, 1), computeTransitions(reduced, 2),
						computeTransitions(reduced, 3), computeTransitions(reduced, 4)).computeGreedyPolicy(reduced);

			}

		}

	}

	public static Point bestPointToMoveTo(Mob m) {
		Point best = new Point(m.getX(), m.getY());
		int zx = (m.getX() - currX) >> 3;
		int zy = (m.getY() - currY) >> 3;

		// take a vote for a policy based on all tiles occupied
		int xTiles = (m.getBounds().width + 7) >> 3;
		int yTiles = (m.getBounds().height + 7) >> 3;
		int tiles[] = new int[xTiles * yTiles];

		for (int i = 0; i < yTiles; i++) {
			for (int j = 0; j < xTiles; j++) {
				tiles[i * xTiles + j] = (zy + i) * width + (zx + j);
			}
		}

		int dx = 0, dy = 0;
		for (int i = 0; i < tiles.length; i++) {
			
			int tileIndex = tiles[i];

			if (tileIndex >= 0 && tileIndex < policy.length) {
				int action = policy[tileIndex];
				switch (action) {
				case 1:
					dy -= 8;
					break;
				case 2:
					dx += 8;
					break;
				case 3:
					dy += 8;
					break;
				case 4:
					dx -= 8;
					break;
				}

			}
		}
		if (dx > 8) {
			dx = 8;
		}
		if (dy > 8) {
			dy = 8;
		}
		if (dx < -8) {
			dx = -8;
		}
		if (dy < -8) {
			dy = -8;
		}
		best.translate(dx, dy);
		return best;
	}

	public void render(Screen screen) {
		for (int i = 0; i < policy.length; i++) {
			JJFont.render(actionToChar(policy[i]), screen, screen.getxOffset() + (i % width) * 8,
					screen.getyOffset() + (i / width) * 8, new int[] { 0xFF0000, 0xFF0000, 0xFF0000, 0xFF0000 }, 1);
		}
	}

	private String actionToChar(int action) {
		switch (action) {
		case 1:
			return "U";
		case 2:
			return "R";
		case 3:
			return "D";
		case 4:
			return "L";
		default:
			return ".";
		}
	}

	private double[][] computeTransitions(int[] tiles, int action) {
		double[][] transitions = new double[tiles.length][tiles.length];

		for (int row = 0; row < height; row++) {
			for (int col = 0; col < width; col++) {

				int current = col + row * width;

				int up = action == 1 ? -1 : 0;
				int right = action == 2 ? 1 : 0;
				int down = action == 3 ? 1 : 0;
				int left = action == 4 ? -1 : 0;

				int index = (col + left + right) + (row + up + down) * width;
				if (index >= 0 && index < tiles.length && current < tiles.length) {

					// transition probability from CURRENT to INDEX
					switch (tiles[current]) {

					// from OPEN to ...
					case 0:
						switch (tiles[index]) {
						// from OPEN to OPEN
						case 0:
							transitions[current][index] = .95;
							transitions[current][current] = 0.05;
							break;
						// from OPEN to WATER
						case 1:
							transitions[current][index] = .8;
							transitions[current][current] = .2;
							break;

						// from OPEN to OCCUPIED
						case 2:
							transitions[current][index] = .1;
							transitions[current][current] = .9;
							break;

						// from OPEN TO SOLID
						case 3:
							transitions[current][current] = 1;
							break;

						// from OPEN to OBJECTIVE
						case 4:
							transitions[current][index] = 1;
							break;
						}
						break;

					// from WATER to ...
					case 1:
						switch (tiles[index]) {
						// from WATER to OPEN
						case 0:
							transitions[current][index] = .95;
							transitions[current][current] = .05;
							break;
						// from WATER to WATER
						case 1:
							transitions[current][index] = .90;
							transitions[current][current] = .1;
							break;

						// from WATER to OCCUPIED
						case 2:
							transitions[current][index] = .2;
							transitions[current][current] = .8;
							break;

						// from WATER TO SOLID
						case 3:
							transitions[current][current] = 1;
							break;

						// from WATER to OBJECTIVE
						case 4:
							transitions[current][index] = 1;
							break;
						}
						break;

					// from OCCUPIED to ...
					case 2:
						switch (tiles[index]) {
						// from OCCUPIED to OPEN
						case 0:
							transitions[current][index] = .99;
							transitions[current][current] = .01;
							break;
						// from OCCUPIED to WATER
						case 1:
							transitions[current][index] = .99;
							transitions[current][current] = .01;
							break;

						// from OCCUPIED to OCCUPIED
						case 2:
							transitions[current][index] = 0.2;
							transitions[current][current] = 0.8;
							break;

						// from OCCUPIED TO SOLID
						case 3:
							transitions[current][current] = 1;
							break;

						// from OCCUPIED to OBJECTIVE
						case 4:
							transitions[current][index] = 1;
							break;
						}
						break;
					// from SOLID to ...
					// if you are in solid, you can't move at all
					case 3:
						transitions[current][current] = 1;
						break;

					// from OBJECTIVE to ...
					// if you are in OBJ, why would you leave?
					case 4:
						transitions[current][current] = 1;
						break;

					}

				} else {

					// no viable movement in this direction
					if (current < transitions.length) {
						transitions[current][current] = 1;
					} else {
						System.err.println("Unknown transition probability");
					}
				}

				// System.out.print(tiles[current] + " ");
			}
			// System.out.println();
		}
		// System.out.println();

		return transitions;
	}

	// Map Key: 0 = Not Water/Solid, 1 = Water, 2 = Has Mob, 3 = Solid/Solid
	// Entity, 4 = Open tile adjacent to player
	public int[] process(List<TileAdapter> tiles) {
		int[] reduced = new int[tiles.size()];

		Rectangle outer = new Rectangle(player.getX() - 8, player.getY() - 8, player.getBounds().width + 8,
				player.getBounds().height + 12);

		for (int i = 0; i < tiles.size(); i++) {

			if (tiles.get(i).isSolid()) {
				reduced[i] = 3;
			} else if (tiles.get(i).isOccupied()) {
				reduced[i] = 2;
			} else if (tiles.get(i).isWater()) {
				reduced[i] = 1;
			} else {
				reduced[i] = 0;
			}

			Rectangle rec = new Rectangle(tiles.get(i).getX() * 8, tiles.get(i).getY() * 8, 8, 8);

			if (outer.intersects(rec) && reduced[i] != 3 && reduced[i] != 2) {
				reduced[i] = 4;
			}

		}

		/*
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				System.out.print(reduced[i * width + j]);
			}
			System.out.println();
		}*/

		return reduced;
	}

}
