package ca.javajesus.level;

import java.awt.Point;
import java.io.Serializable;

public class LevelList implements Serializable {
	
	private static final long serialVersionUID = -7172043005873698065L;

	public Level level1;

	public Level lordHillsboroughsDomain;
	public Level sanCisco;
	public Level edgeOfTheWoodsMain;
	public Level edgeOfTheWoodsTop;
	public Level bautistasDomain;

	public Level roadlevel;
	public Level random;
	public Level random2;
	public Level randomCave;
	
	public Level playerLevel;
	
	public LevelList() {
		
		level1 = new Level1();
		lordHillsboroughsDomain = new LordHillsboroughsDomain();
		sanCisco = new SanCisco();
		edgeOfTheWoodsMain = new EdgeOfTheWoods();
		edgeOfTheWoodsTop = new EdgeOfTheWoodsTop();
		bautistasDomain = new BautistasDomain();

		roadlevel = new RoadLevel();
		random = new RandomLevel(level1.width, level1.height);
		random2 = new RandomLevel2(level1.width, level1.height,
				new Point(500, 500));
		randomCave = new RandomCave(3000,
				3000, 5, null, new Point(220, 79));
		
	}
	
	public Level getDefaultLevel() {
		
		return this.lordHillsboroughsDomain;
	}
	
	public void setSaveLevel(Level l) {
		this.playerLevel = l;
	}

}
