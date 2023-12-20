package fr.loferga.core.success;

import fr.loferga.core.event.EventHandler;
import fr.loferga.core.event.EventPriority;
import fr.loferga.core.event.jeu.CarteJoueeEvent;

public class ExampleSuccess2 extends Success {
	
	@EventHandler(priority = EventPriority.HIGH)
	public void onCarteJouee(CarteJoueeEvent e) {
		System.out.println("example2 " + e.getCoupJoue().getCarte() + " / " + e.getCoupJoue().getCible());
	}

}
