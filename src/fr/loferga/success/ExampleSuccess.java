package fr.loferga.success;

import fr.loferga.event.EventHandler;
import fr.loferga.event.core.CarteJoueeEvent;

public class ExampleSuccess extends Success {
	
	@EventHandler
	public void onCarteJouee(CarteJoueeEvent e) {
		System.out.println(e.getCoupJoue().getCarte() + " / " + e.getCoupJoue().getCible());
	}
	
}