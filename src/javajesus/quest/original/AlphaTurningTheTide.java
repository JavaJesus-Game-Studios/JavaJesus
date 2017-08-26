package javajesus.quest.original;

import javajesus.entities.Mob;
import javajesus.entities.monsters.EvilOrangutan;
import javajesus.entities.npcs.NPC;
import javajesus.entities.npcs.characters.Jesus;
import javajesus.level.sandbox.SandboxOriginalLevel;
import javajesus.quest.Quest;

public class AlphaTurningTheTide extends Quest {

	// objective
	private static final int ISDEAD = 0;

	// whether or not jesus/orang is dead
	private boolean killedKen, killedJesus;

	// instance of jesus
	private Jesus jesus;

	public AlphaTurningTheTide(NPC giver) {
		super(giver, "Turning the Tide", "/WORLD_DATA/QUEST_DATA/ALPHA_TURNING_THE_TIDE.json", 1);
	}

	@Override
	public void update() {

		// check if orangutan is dead
		if (!killedKen) {
			for (Mob m : giver.getLevel().getMobs()) {
				if (m instanceof EvilOrangutan && m.isDead()) {
					killedKen = true;
				}
			}
		}

		if (killedKen) {
			// find jesus
			if (jesus == null) {
				for (Mob m : SandboxOriginalLevel.alpha.getMobs()) {
					if (m instanceof Jesus) {
						jesus = (Jesus) m;
						this.giver = jesus;
						jesus.addQuestAndSet(this);
					}
				}
			}
		}

		// check if orangutan is dead
		if (!killedJesus) {
			for (Mob m : SandboxOriginalLevel.alpha.getMobs()) {
				if (m instanceof Jesus && m.isDead()) {
					killedJesus = true;
				}
			}
		}

		// update the objective
		objectives[ISDEAD] = killedJesus || killedKen;
	}

}
