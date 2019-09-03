package javajesus.quest.factories;

import javajesus.entities.npcs.NPC;
import javajesus.entities.npcs.Peasant;
import javajesus.entities.npcs.aggressive.Knight;
import javajesus.entities.npcs.aggressive.SWATOfficer;
import javajesus.level.Level;

public class AlphaCharacterFactory extends CharacterFactory {
	
	// can use any ID > 255
	public static final int OFFICER = 256, PEASANT = 257, KNIGHT = 258;
	
	private NPC[] nonUniques = new NPC[10];
	
	public void setNonUniqueCharacters(Level level) {
		nonUniques[PEASANT - UNIQUE_OFFSET] = new Peasant(level, 832, 112, 1); // liberate farm
		nonUniques[OFFICER - UNIQUE_OFFSET] = new  SWATOfficer(level, 1464, 16); // officer quest
		nonUniques[KNIGHT - UNIQUE_OFFSET] = new Knight(level, 1208, 1464); // knight quest
	}
	
	@Override
	public NPC make(int id) {
		switch(id) {
		case OFFICER: return nonUniques[OFFICER - UNIQUE_OFFSET];
		case PEASANT: return nonUniques[PEASANT - UNIQUE_OFFSET];
		case KNIGHT: return nonUniques[KNIGHT - UNIQUE_OFFSET];
		default:
			return super.make(id);
		}
		
	}
	
}
