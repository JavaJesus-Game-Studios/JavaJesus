package javajesus.ai;

import java.awt.Point;
import java.util.List;

import javajesus.entities.Entity;
import javajesus.entities.Player;
import javajesus.graphics.JJFont;
import javajesus.graphics.Screen;
import javajesus.level.tile.Tile;

public class AIManager implements Runnable {
	
	int ticks;
	
	private Player player;
	private Screen screen;
	
	private int[] policy = {};
	
	public AIManager(Player player, Screen screen) {
		this.player = player;
		this.screen = screen;
	}
	
	public void run() {
		
		while (true) {
		
			if (ticks++ % Entity.secondsToTicks(2) == 0) {
				
				int[] reduced = process(player.getLevel().getVisibleTiles(screen), player.getLevel().getVisibleTileCoords(screen));
				
				policy = new MDP(computeTransitions(reduced, 1), computeTransitions(reduced, 2), computeTransitions(reduced, 3), computeTransitions(reduced, 4))
						.computeGreedyPolicy(reduced);
				
			}
		}
		
	}
	
	public void render(Screen screen) {
		int width = player.getLevel().getNumXTilesOnScreen(screen);
		for (int i = 0; i < policy.length; i++) {
			JJFont.render(actionToChar(policy[i]), screen, screen.getxOffset() + (i % width) * 8, screen.getyOffset() + (i / width) * 8, new int[] {0xFF0000, 0xFF0000, 0xFF0000, 0xFF0000}, 1);
		}
	}
	
	private String actionToChar(int action) {
		switch(action) {
		case 1: return "U";
		case 2: return "R";
		case 3: return "D";
		case 4: return "L";
		default: return ".";
		}
	}
	
	private double[][] computeTransitions(int[] tiles, int action) {
		double[][] transitions = new double[tiles.length][tiles.length];
		
		int height = player.getLevel().getNumYTilesOnScreen(screen);
		int width = player.getLevel().getNumXTilesOnScreen(screen);
		
		for (int row = 0; row < height; row++) {
			for (int col = 0; col < width; col++) {
				
				int current = col + row * width;
				
				if (current >= tiles.length) {
					continue;
				}
				
				// if solid, movement is stuck in current state
				if (tiles[current] == 3) {
					transitions[current][current] = 1;
				} else {
					
					int up = action == 1 ? -1 : 0;
					int right = action == 2 ? 1 : 0;
					int down = action == 3 ? 1 : 0;
					int left = action == 4 ? -1 : 0;
					
					int index = (col + left + right) + (row + up + down) * width;
					if (index >=0 && index < 1102) {
						
						switch (tiles[index]) {
						
							// open tile, likely to move here
							case 0: transitions[current][index] = .95; 
									transitions[current][current] = .05;
									break;
									
							// water, slow and not reliable
							case 1: transitions[current][index] = .70; 
									transitions[current][current] = .30;
									break;
									
							// mob already there, toss up
							case 2: transitions[current][index] = .50; 
									transitions[current][current] = .50;
									break;
									
							// solid, not possible
							case 3: transitions[current][current] = 1;
									break;
							
							// almost always move to the player
							case 4: transitions[current][index] = .99; 
									transitions[current][current] = .01;
									break;
						}
						
					} else {
						
						// no viable movement in this direction
						transitions[current][current] = 1;
					}
					
				}
				
				//System.out.print(tiles[current] + " ");
			}
			//System.out.println();
		}
		
		return transitions;
	}
	
	//Map Key: 0 = Not Water/Solid, 1 = Water, 2 = Has Mob, 3 = Solid/Solid Entity, 4 = Open tile adjacent to player
	public int[] process(List<Tile> tiles, List<Point> coords) {
		int[] reduced = new int[tiles.size()];
		
		for (int i = 0; i < tiles.size(); i++) {
			
			if (tiles.get(i).isSolid()) {
				reduced[i] = 3;
			} else if (tiles.get(i).hasMob) {
				reduced[i] = 2;
			} else if (Tile.isWater(tiles.get(i).getId())) {
				reduced[i] = 1;
			} else {
				reduced[i] = 0;
			}
			
			if (player.getBounds().contains(new Point(coords.get(i).x * 8 + 8, coords.get(i).y * 8 + 8))) {
				reduced[i] = 4;
			}
			
		}
		
		return reduced;
	}

}
