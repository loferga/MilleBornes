package fr.loferga.success;

import fr.loferga.event.EventHandler;
import fr.loferga.event.EventPriority;
import fr.loferga.event.core.CarteJoueeEvent;

public class ExampleSuccess2 extends Success {
	
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onCarteJouee(CarteJoueeEvent e) {
		System.out.println("example2 " + e.getCoupJoue().getCarte() + " / " + e.getCoupJoue().getCible());
	}

}
