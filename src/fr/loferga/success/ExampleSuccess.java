package fr.loferga.success;

import fr.loferga.event.EventHandler;
import fr.loferga.event.EventManager;
import fr.loferga.event.core.CarteJoueeEvent;

public class ExampleSuccess extends Success {

	@Override
	public void registerListeners(EventManager eventManager) {
		eventManager.subscribe(CarteJoueeEvent.class, this::onCarteJouee);
	}
	
	@EventHandler
	public void onCarteJouee(CarteJoueeEvent e) {
		System.out.println(e.getCoupJoue().getCarte() + " / " + e.getCoupJoue().getCible());
	}
	
}