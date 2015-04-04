package ca.javajesus.level.interior;

import java.awt.Point;

import ca.javajesus.game.entities.Mob.Direction;
import ca.javajesus.game.entities.npcs.LordHillsborough;
import ca.javajesus.game.entities.structures.furniture.Throne;
import ca.javajesus.game.entities.structures.transporters.TransporterInterior;
import ca.javajesus.game.entities.structures.transporters.TransporterStairStone;
import ca.javajesus.level.Level;

public class CastleInteriorFloor1 extends Interior{
		private Point exitPoint;

		public CastleInteriorFloor1(Point point, Level level) {
			super("/Buildings/Generic Interiors/Castle_Interiors/Castle_1_Living_Quarters.png", new Point(504,
					464), level);
			this.exitPoint = point;
		}

		protected void initNPCPlacement() {
						
		}

		protected void initSpawnerPlacement() {

		}

		protected void initChestPlacement() {

		}

		protected void otherEntityPlacement() {
			this.addEntity(new TransporterInterior(this, 504, 472, nextLevel,
					exitPoint));
			
		}

	}




