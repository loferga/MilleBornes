package fr.loferga.core.event;

public abstract class Event implements Listener {
	
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