package ca.javajesus.level;

import java.awt.Point;

import ca.javajesus.game.Game;
import ca.javajesus.game.entities.structures.CaveEntrance;
import ca.javajesus.game.entities.structures.Hotel;
import ca.javajesus.game.entities.structures.NiceHouse;
import ca.javajesus.game.entities.structures.PoorHouse;
import ca.javajesus.game.entities.structures.transporters.MapTransporter;

public class EdgeOfTheWoodsTop extends Level
{

    public EdgeOfTheWoodsTop()
    {
        super("/Levels/Wilderness_Areas/Edge_of_the_Woods_Top.png", true, "Edge of the Woods Top");
        this.spawnPoint = new Point(1832, 1544);
        startingSpawnPoint = new Point(1832, 1544);
    }

    protected void initNPCPlacement() {
        
    }

    protected void initSpawnerPlacement() {
        
    }

    protected void initChestPlacement() {
        
    }

    protected void otherEntityPlacement() {
        //caves
        this.addEntity(new CaveEntrance(this, 1856, 688));
        this.addEntity(new CaveEntrance(this, 2616, 1176));
        this.addEntity(new CaveEntrance(this, 2448, 2440));
        
        //hotel
        this.addEntity(new Hotel(this, 1872, 1408));

        //houses
        this.addEntity(new PoorHouse(this, 2040, 1536));
        this.addEntity(new PoorHouse(this, 2056, 1632));
        this.addEntity(new PoorHouse(this, 1920, 1568));
        this.addEntity(new PoorHouse(this, 1936, 1672));
        this.addEntity(new NiceHouse(this, 2048, 1416));
    }

	protected void initMapTransporters() {
		this.addEntity(new MapTransporter(this, (this.width * 8) - 8, 0, Game.levels.lordHillsboroughsDomain,
				new Point(16, Game.player.getY()),
				8, this.height * 8));
		
	}

}
