/**
 * 
 */
package javajesus.entities;

import javajesus.quest.Dialogue;

/**
 * @author JJGameStudios
 * The Actor Class is a class of entities that will act in Quests and in the 
 * over-world, hence the name. An actor can be of any entity type, they can 
 * have a base set of dialogue that is independent of quests. Any given quest
 * will have a set of Actors, and the Quest will assign dialogue to the correct
 * Actors. This class defines Actor specific behavior, the actual list of 
 * Actors will be handled either via a list or a database, implementation still
 * incomplete.
 *
 */
public class Actor {
	
	// Actor identification id
	private short id;
	// Actor name
	private String name;
	// Actor gender: 0 for male 1 for female
	private byte gender;
	// Actor Entity type
	private Entity performer;
	// Actor's base Dialogue
	private Dialogue baseConvo;

	/**
	 * ctor
	 * @param id - identification number for the Actor
	 * @param name - the actor's name to be displayed
	 * @param gender - the actor's gender, to be used for non-deterministic pronouns
	 * @param performer - the entity body used for the performance
	 */
	public Actor(short id, String name, byte gender, Entity performer) {
		// Set the values
		this.id = id;
		this.name = name;
		this.gender = gender;
		this.performer = performer;
	}
	
	/**
	 * 
	 * @return the calling actor's id number
	 */
	public short getID() {
		return this.id;
	}
	/**
	 * 
	 * @return the calling actor's name
	 */
	public String getName() {
		return this.name;
	}
	/**
	 * 
	 * @return the calling actor's gender
	 */
	public byte getGender() {
		return this.gender;
	}
	/**
	 * 
	 * @return the calling actor's entity body
	 */
	public Entity getActorEntity() {
		return this.performer;
	}
	/**
	 * @ return the calling actor's base Dialogue Conversation
	 */
	public Dialogue getBaseDialogue() {
		return baseConvo;
	}
}
