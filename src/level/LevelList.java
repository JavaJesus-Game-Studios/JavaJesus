package level;

import game.Game;
import game.Game.GameMode;

import java.awt.Point;
import java.io.Serializable;

import level.sandbox.SandboxSurvivalMap1;
import level.story.BautistasDomain;
import level.story.EdgeOfTheWoods;
import level.story.EdgeOfTheWoodsTop;
import level.story.LordHillsboroughsDomain;
import level.story.OrchardValley;
import level.story.SanCisco;
import level.story.SanJuan;
import level.story.TechTopia;

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
			return new SandboxSurvivalMap1();
		case ADVENTURE:
			return this.lordHillsboroughsDomain;
		}
		return null;

	}

	public void setCurrentLevel(Level l) {
		this.playerLevel = l;
	}

}
