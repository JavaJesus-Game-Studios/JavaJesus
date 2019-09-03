package javajesus.quest.factories;

import javajesus.entities.npcs.NPC;
import javajesus.entities.npcs.aggressive.Orangutan;
import javajesus.level.Level;

public class AlphaCaveCharacterFactory extends CharacterFactory {
	
	// can use any ID > 255
	public static final int KEN = 256;
	
	private NPC[] nonUniques = new NPC[10];
	
	public void setNonUniqueCharacters(Level level) {
		nonUniques[KEN - UNIQUE_OFFSET] = new Orangutan(level, 944, 192); // turning the tide
		
		level.add(nonUniques[KEN - UNIQUE_OFFSET]);
	}
	
	@Override
	public NPC make(int id) {
		switch(id) {
		case KEN: return nonUniques[KEN - UNIQUE_OFFSET];
		default:
			return super.make(id);
		}
		
	}

}
