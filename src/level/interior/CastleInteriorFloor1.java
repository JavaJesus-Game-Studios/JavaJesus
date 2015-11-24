package level.interior;

import java.awt.Point;

import game.entities.structures.transporters.TransporterInterior;
import game.entities.structures.transporters.TransporterStairStone;
import level.Level;
import utility.Direction;

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
			
			//Up
			this.addEntity(new TransporterStairStone(this, 376, 224,
					new CastleBattlements(new Point(384, 320), this), new Point(384, 320), Direction.SOUTH));
			this.addEntity(new TransporterStairStone(this, 632, 224,
					new CastleBattlements(new Point(656, 320), this), new Point(656, 320), Direction.SOUTH));
			//Down
			this.addEntity(new TransporterStairStone(this, 336, 280,
					new CastleInterior(new Point(360, 248), this), new Point(360, 248), Direction.SOUTH));
			this.addEntity(new TransporterStairStone(this, 672, 280,
					new CastleInterior(new Point(672, 248), this), new Point(672, 248), Direction.SOUTH));

		}

	}




