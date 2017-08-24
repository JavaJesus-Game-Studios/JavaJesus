package javajesus.utility;

import java.util.Comparator;

import javajesus.entities.Entity;

/*
 * Comparator used to compare two entities for layer checking
 */
public class EntityComparator implements Comparator<Entity> {

	@Override
	public int compare(Entity e1, Entity e2) {
		return e1.getLayer() - e2.getLayer();
	}

}
