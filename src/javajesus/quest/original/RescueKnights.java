package javajesus.quest.original;

import java.awt.Rectangle;

import javajesus.entities.Mob;
import javajesus.entities.bosses.Cyclops;
import javajesus.entities.monsters.Demon;
import javajesus.entities.monsters.Skeleton;
import javajesus.entities.npcs.NPC;
import javajesus.level.Level;
import javajesus.quest.Quest;

public class RescueKnights extends Quest {

	// constants for the objectives
	private static final int BATTLEFIELD_CLEAR_1 = 0;
	private static final int BATTLEFIELD_CLEAR_2 = 1;
	private static final int BATTLEFIELD_CLEAR_3 = 2;

	// whether or not the spawn events have occurred
	private boolean phase2, phase3;

	// size of the battlefield
	private static final Rectangle battlefield = new Rectangle(0, 1216, 456, 384);

	public RescueKnights(NPC giver) {
		super(giver, "Rescue Knights", "/WORLD_DATA/QUEST_DATA/Rescue_Knights.json", 3);
	}

	@Override
	public void update() {

		// if a demon is not found, assume true
		boolean killed = true;

		// instance of the level
		Level level = giver.getLevel();

		// search for any demons initial wave
		if (!objectives[BATTLEFIELD_CLEAR_1]) {
			for (Mob m : level.getMobs()) {
				// added skeletons in case any decide to wander in following the
				// player from the forest
				if ((m instanceof Demon || m instanceof Cyclops || m instanceof Skeleton)
				        && m.getBounds().intersects(battlefield) && !m.isDead()) {
					killed = false;
					break;
				}
			}
		}
		// update the objective
		objectives[BATTLEFIELD_CLEAR_1] = killed;

		// If only the first objective is complete spawns in a new wave of
		// Demons
		if (objectives[BATTLEFIELD_CLEAR_1] && !phase2) {

			// demons have been spawned
			phase2 = true;

			// 20 Demons
			for (int i = 0; i < 5; i++) {
				level.add(new Demon(level, 264, 1544));
			}
			for (int i = 0; i < 5; i++) {
				level.add(new Demon(level, 208, 1576));
			}
			for (int i = 0; i < 5; i++) {
				level.add(new Demon(level, 144, 1576));
			}
			for (int i = 0; i < 5; i++) {
				level.add(new Demon(level, 120, 1536));
			}
		}

		// search for any demons in the second wave
		if (!objectives[BATTLEFIELD_CLEAR_2]) {
			for (Mob m : level.getMobs()) {
				if (m instanceof Demon && m.getBounds().intersects(battlefield) && !m.isDead()) {
					killed = false;
					break;
				}
			}
		}
		// update the objective
		objectives[BATTLEFIELD_CLEAR_2] = killed;

		// if the first and second objectives are complete but the third is not
		if ((objectives[BATTLEFIELD_CLEAR_2]) && !phase3) {

			// cyclops have been spawned
			phase3 = true;

			// spawn in the Developers to murder the Alpha Testers
			Cyclops stevie = new Cyclops(level, 184, 1536, 3000);
			Cyclops derek = new Cyclops(level, 280, 1544, 3500);
			level.add(stevie);
			level.add(derek);
		}

		if (!objectives[BATTLEFIELD_CLEAR_3]) {
			for (Mob m : level.getMobs()) {
				if (m instanceof Cyclops && m.getBounds().intersects(battlefield) && !m.isDead()) {
					killed = false;
					break;
				}
			}
		}
		// updates the final objective, the quest is now complete
		objectives[BATTLEFIELD_CLEAR_3] = killed;
	}
}
