package ca.javajesus.level;

import java.awt.Point;

import ca.javajesus.game.entities.structures.CatholicChapel;
import ca.javajesus.game.entities.structures.CatholicChurch;
import ca.javajesus.game.entities.structures.CaveEntrance;
import ca.javajesus.game.entities.structures.GunStore;
import ca.javajesus.game.entities.structures.NiceHouse;
import ca.javajesus.game.entities.structures.NiceHouse2;
import ca.javajesus.game.entities.structures.PoorHouse;
import ca.javajesus.game.entities.structures.Prison;
import ca.javajesus.game.entities.structures.Projects;
import ca.javajesus.game.entities.structures.RancheroHouse;
import ca.javajesus.game.entities.structures.RefugeeTent;
import ca.javajesus.game.entities.structures.ShantyHouse;
import ca.javajesus.game.entities.structures.Warehouse;
import ca.javajesus.game.entities.structures.SequoiaCinema;
import ca.javajesus.game.entities.structures.SequoiaSchool;


public class BautistasDomain extends Level{
	public BautistasDomain(){
		super("/Levels/Cities/Domain_of_Ranchero_Bautista.png", true);
		this.spawnPoint = new Point(2896, 64);
		startingSpawnPoint = new Point(2896, 64);

	}

	protected void initNPCPlacement() {
		
	}

	 
	protected void initSpawnerPlacement() {
		 
		
	}

	 
	protected void initChestPlacement() {
		 
		
	}

	 
	protected void otherEntityPlacement() {
		//Sequoia City

	    //churches and chapels
	    this.addEntity(new CatholicChapel(this, 2312, 1280));
	    this.addEntity(new CatholicChurch(this, 920, 2048));
	    this.addEntity(new CatholicChurch(this, 1960, 136));
	    
	    //cave entrances
	    this.addEntity(new CaveEntrance(this, 176, 184));
	    this.addEntity(new CaveEntrance(this, 1808, 432));
	    
	    //cinema
	    this.addEntity(new SequoiaCinema(this, 2800, 1832));
	    
	    ///gunstore
	    this.addEntity(new GunStore(this, 2952, 1896));
	    
	    //houses
	    this.addEntity(new NiceHouse(this, 1880, 1808));
	    this.addEntity(new NiceHouse(this, 1880, 1944));
	    this.addEntity(new NiceHouse(this, 1912, 1488));
	    this.addEntity(new NiceHouse(this, 2080, 1632));
	    this.addEntity(new NiceHouse(this, 2080, 1808));
	    this.addEntity(new NiceHouse(this, 2080, 1944));
	    this.addEntity(new NiceHouse(this, 2904, 2048));
	    this.addEntity(new NiceHouse(this, 3016, 2072));
	    this.addEntity(new NiceHouse(this, 3128, 2088));
	    this.addEntity(new NiceHouse2(this, 1800, 1488));
	    this.addEntity(new NiceHouse2(this, 1848, 1632));
	    this.addEntity(new NiceHouse2(this, 1968, 1632));
	    this.addEntity(new NiceHouse2(this, 1992, 1808));
	    this.addEntity(new NiceHouse2(this, 1992, 1944));
	    this.addEntity(new NiceHouse2(this, 2048, 1488));
	    this.addEntity(new NiceHouse2(this, 2288, 1504));
	    this.addEntity(new NiceHouse2(this, 2400, 1504));
	    this.addEntity(new NiceHouse2(this, 2512, 1504));
	    this.addEntity(new NiceHouse2(this, 2624, 1504));
	    this.addEntity(new NiceHouse2(this, 2736, 1504));
	    this.addEntity(new NiceHouse2(this, 2848, 1504));
	    this.addEntity(new NiceHouse2(this, 2960, 1504));
	    this.addEntity(new PoorHouse(this, 2000, 616));
	    this.addEntity(new PoorHouse(this, 2088, 616));
	    this.addEntity(new PoorHouse(this, 2088, 920));
	    this.addEntity(new PoorHouse(this, 2176, 616));
	    this.addEntity(new PoorHouse(this, 2192, 920));
	    this.addEntity(new PoorHouse(this, 2288, 616));
	    this.addEntity(new PoorHouse(this, 2296, 920));
	    this.addEntity(new PoorHouse(this, 2400, 920));
	    this.addEntity(new PoorHouse(this, 2504, 920));
	    this.addEntity(new PoorHouse(this, 2504, 1264));
	    this.addEntity(new PoorHouse(this, 2520, 616));
	    this.addEntity(new PoorHouse(this, 2608, 920));
	    this.addEntity(new PoorHouse(this, 2624, 616));
	    this.addEntity(new PoorHouse(this, 2632, 1256));
	    this.addEntity(new PoorHouse(this, 2712, 920));
	    this.addEntity(new PoorHouse(this, 2736, 1248));
	    this.addEntity(new PoorHouse(this, 2816, 920));
	    this.addEntity(new PoorHouse(this, 2848, 1240));
	    this.addEntity(new PoorHouse(this, 2944, 1232));
        this.addEntity(new RancheroHouse(this, 520, 2112));
        this.addEntity(new ShantyHouse(this, 1688, 280));
        this.addEntity(new ShantyHouse(this, 1792, 248));
        this.addEntity(new ShantyHouse(this, 1888, 216));
        this.addEntity(new ShantyHouse(this, 2080, 160));
        this.addEntity(new ShantyHouse(this, 2144, 128));
        this.addEntity(new ShantyHouse(this, 2232, 104));
        this.addEntity(new ShantyHouse(this, 2280, 1200));
        this.addEntity(new ShantyHouse(this, 2304, 1096));
        this.addEntity(new ShantyHouse(this, 2312, 112));
        this.addEntity(new ShantyHouse(this, 2360, 1144));
        this.addEntity(new ShantyHouse(this, 2408, 112));
        this.addEntity(new ShantyHouse(this, 2448, 1216));
        this.addEntity(new ShantyHouse(this, 2464, 1120));
        this.addEntity(new ShantyHouse(this, 2472, 112));
        this.addEntity(new ShantyHouse(this, 2536, 1144));
        this.addEntity(new ShantyHouse(this, 2568, 120));
        this.addEntity(new ShantyHouse(this, 2632, 1120));
        this.addEntity(new ShantyHouse(this, 2648, 120));
        this.addEntity(new ShantyHouse(this, 2648, 1192));
        this.addEntity(new ShantyHouse(this, 2736, 128));

	    //african-american lodging
	    this.addEntity(new Prison(this, 1648, 592));
	    this.addEntity(new Projects(this, 1696, 896));
	    this.addEntity(new Projects(this, 1792, 1264));
	    this.addEntity(new Projects(this, 1832, 592));
	    this.addEntity(new Projects(this, 1840, 1088));
	    this.addEntity(new Projects(this, 1920, 896));
	    this.addEntity(new Projects(this, 2008, 1272));
	    this.addEntity(new Projects(this, 2376, 352));
	    this.addEntity(new Projects(this, 2704, 592));
	    this.addEntity(new RefugeeTent(this, 1912, 512));
	    this.addEntity(new RefugeeTent(this, 2048, 400));
	    this.addEntity(new RefugeeTent(this, 2128, 448));
	    this.addEntity(new RefugeeTent(this, 2264, 488));
	    this.addEntity(new RefugeeTent(this, 2288, 328));
	    this.addEntity(new RefugeeTent(this, 2424, 424));
	    this.addEntity(new RefugeeTent(this, 2528, 352));
	    this.addEntity(new RefugeeTent(this, 2616, 480));
	    this.addEntity(new RefugeeTent(this, 2744, 352));
	    this.addEntity(new RefugeeTent(this, 2768, 456));
	    this.addEntity(new RefugeeTent(this, 3320, 1680));
	    this.addEntity(new RefugeeTent(this, 3336, 1384));
	    this.addEntity(new RefugeeTent(this, 3432, 1504));

	    //caucasian lodging
	    this.addEntity(new SequoiaSchool(this, 2264, 1688));

	    //warehouses
	    this.addEntity(new Warehouse(this, 2968, 1408));
	    this.addEntity(new Warehouse(this, 3088, 648));
	    this.addEntity(new Warehouse(this, 3128, 800));
	    this.addEntity(new Warehouse(this, 3168, 1088));
	    this.addEntity(new Warehouse(this, 3200, 1232));
	    this.addEntity(new Warehouse(this, 3280, 752));
	    this.addEntity(new Warehouse(this, 3440, 712));
		
		
		
	}
}
