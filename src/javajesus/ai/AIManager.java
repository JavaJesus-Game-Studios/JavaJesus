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

	private static int width;

	private int height;

	private boolean running = true;
	
	public AIManager(Player player, Screen screen) {
		this.player = player;
		this.screen = screen;
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

			if (System.currentTimeMillis() - lastTime > 1000) {
				lastTime = System.currentTimeMillis();

				height = player.getLevel().getNumYTilesOnScreen(screen);
				width = player.getLevel().getNumXTilesOnScreen(screen);

				int[] reduced = process(player.getLevel().getVisibleTiles(screen));

				policy = new MDP(computeTransitions(reduced, 1), computeTransitions(reduced, 2),
				        computeTransitions(reduced, 3), computeTransitions(reduced, 4)).computeGreedyPolicy(reduced);
			}

		}

	}
	
	private static int test = 0;
	
	public static Point bestPointToMoveTo(Mob m) {
		Point best = new Point(m.getX(), m.getY());
		int tileIndex = m.getLevel().getVisibleTileIndexFromTileCoord(m.getX() >> 3, m.getY() >> 3, screen);
		if (tileIndex != -1) {
			int action = policy[tileIndex];
			test = tileIndex;
			switch (action) {
			case 1:
				best.translate(0, -8); break;
			case 2:
				best.translate(8, 0); break;
			case 3:
				best.translate(0, 8); break;
			case 4:
				best.translate(-8, 0); break;
			}
		}
		return best;
	}

	public void render(Screen screen) {
		if (test < policy.length)
			policy[test] = 6;
		for (int i = 0; i < policy.length; i++) {
			if (policy[i] == 6) {
				JJFont.render(actionToChar(policy[i]), screen, screen.getxOffset() + (i % width) * 8,
				        screen.getyOffset() + (i / width) * 8, new int[] { 0x0000FF, 0x0000FF, 0x0000FF, 0x0000FF }, 1);
			} else {
				JJFont.render(actionToChar(policy[i]), screen, screen.getxOffset() + (i % width) * 8,
				        screen.getyOffset() + (i / width) * 8, new int[] { 0xFF0000, 0xFF0000, 0xFF0000, 0xFF0000 }, 1);
			}
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
		case 6:
			return "!";
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
							transitions[current][current] = .05;
							break;
						// from OPEN to WATER
						case 1:
							transitions[current][index] = .95;
							transitions[current][current] = .05;
							break;

						// from OPEN to OCCUPIED
						// temporarily not possible to move to occupied, change
						// to .2 later
						case 2:
							transitions[current][index] = 0;
							transitions[current][current] = 1;
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
							transitions[current][index] = .95;
							transitions[current][current] = .05;
							break;

						// from WATER to OCCUPIED
						// temporarily not possible to move to occupied, change
						// to .2 later
						case 2:
							transitions[current][index] = 0;
							transitions[current][current] = 1;
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
						// temporarily set to 1 to make nicer rendering on
						// screen
						case 0:
							transitions[current][index] = 0;
							transitions[current][current] = 1;
							break;
						// from OCCUPIED to WATER
						case 1:
							transitions[current][index] = 0;
							transitions[current][current] = 1;
							break;

						// from OCCUPIED to OCCUPIED
						// temporarily not possible to move to occupied, change
						// to .2 later
						case 2:
							transitions[current][index] = 0;
							transitions[current][current] = 1;
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

		Rectangle outer = new Rectangle(player.getX() - 8, player.getY() - 8, player.getBounds().width + 16,
		        player.getBounds().height + 16);

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

			Point p = new Point(tiles.get(i).getX() * 8, tiles.get(i).getY() * 8);

			if (outer.contains(p) && !player.getBounds().contains(p) && 
					reduced[i] != 3 && reduced[i] != 2) {
				reduced[i] = 4;
			}

		}

		return reduced;
	}

}
