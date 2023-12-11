package fr.loferga.success;

import fr.loferga.event.EventManager;

public abstract class Success {
	
	public abstract void registerListeners(EventManager eventManager);
	
	@Override
	public String toString() {
		return this.getClass().getSimpleName();
	}
	
}
