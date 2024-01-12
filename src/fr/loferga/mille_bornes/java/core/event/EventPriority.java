package fr.loferga.mille_bornes.java.core.event;

/**
 * Represent the order in which the event should be executed
 * LOW meaning it execute first and HIGH meaning it execxute last
 */
public enum EventPriority{
	
	LOW(0), NORMAL(1), HIGH(2);
	
	private int slot;
	
	private EventPriority(int slot) {
		this.slot = slot;
	}
	
	public int getSlot() {
		return slot;
	}
	
}
