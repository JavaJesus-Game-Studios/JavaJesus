package ca.javajesus.level;

import java.awt.Point;
import java.io.Serializable;

import ca.javajesus.game.Game;
import ca.javajesus.game.Game.GameMode;
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

	private Game.GameMode mode;

	public LevelList(GameMode mode) {
		
		this.mode = mode;

		if (mode == Game.GameMode.ADVENTURE) {
			lordHillsboroughsDomain = new LordHillsboroughsDomain();
			sanCisco = new SanCisco();
			edgeOfTheWoodsMain = new EdgeOfTheWoods();
			edgeOfTheWoodsTop = new EdgeOfTheWoodsTop();
			bautistasDomain = new BautistasDomain();

			techTopia = new TechTopia();
			sanJuan = new SanJuan();
			orchardValley = new OrchardValley();
		}
	}

	public Level getDefaultLevel() {
		switch (mode) {
		case MINI:
			return new RandomLevel(200, 200, new Point(500, 500), true);
		case SURVIVAL:
			return new ZombieMap1();
		case ADVENTURE:
			return this.lordHillsboroughsDomain;
		}
		return null;

	}

	public void setCurrentLevel(Level l) {
		this.playerLevel = l;
	}

}
