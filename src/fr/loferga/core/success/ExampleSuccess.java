package fr.loferga.core.success;

import fr.loferga.core.event.EventHandler;
import fr.loferga.core.event.jeu.CarteJoueeEvent;

public class ExampleSuccess extends Success {
	
	@EventHandler
	public void onCarteJouee(CarteJoueeEvent e) {
		System.out.println(e.getCoupJoue().getCarte() + " / " + e.getCoupJoue().getCible());
	}
	
}