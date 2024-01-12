package fr.loferga.mille_bornes.java.core.event;

public abstract class Event {
	
	private EventManager eventManager;
	
	protected Event(EventManager eventManager) {
		this.eventManager = eventManager;
	}
	
	protected void trigger() {
		eventManager.trigger(this);
	}
	
	@Override
	public String toString() {
		return this.getClass().getSimpleName();
	}
	
}