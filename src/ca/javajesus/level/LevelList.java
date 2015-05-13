package ca.javajesus.level;

import java.awt.Point;
import java.io.Serializable;

import ca.javajesus.level.zombie.ZombieMap1;

public class LevelList implements Serializable {

	private static final long serialVersionUID = -7172043005873698065L;

	public Level lordHillsboroughsDomain;
	public Level sanCisco;
	public Level edgeOfTheWoodsMain;
	public Level edgeOfTheWoodsTop;
	public Level bautistasDomain;
	public Level techTopia;
	public Level sanJuan;
	public Level orchardValley;

	public Level playerLevel;

	private int numDefault;

	public LevelList(int num) {

		numDefault = num;

		lordHillsboroughsDomain = new LordHillsboroughsDomain();
		sanCisco = new SanCisco();
		edgeOfTheWoodsMain = new EdgeOfTheWoods();
		edgeOfTheWoodsTop = new EdgeOfTheWoodsTop();
		bautistasDomain = new BautistasDomain();

		techTopia = new TechTopia();
		sanJuan = new SanJuan();
		orchardValley = new OrchardValley();
	}

	public Level getDefaultLevel() {
		switch (numDefault) {
		case 1: return new RandomLevel(200, 200, new Point(500,
				500), true);
		case 2: return new ZombieMap1();
		default:
			return this.lordHillsboroughsDomain;
		}

	}

	public void setSaveLevel(Level l) {
		this.playerLevel = l;
	}

}
