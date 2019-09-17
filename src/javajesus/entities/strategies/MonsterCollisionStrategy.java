package javajesus.entities.strategies;

import javajesus.entities.Mob;

public class MonsterCollisionStrategy extends LooseCollisionStrategy {
	
	public MonsterCollisionStrategy(Mob mob) {
		super(mob);
	}
	
	@Override
	public boolean isMobCollision(int dx, int dy) {
		return false;
	}

}
